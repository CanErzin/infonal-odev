import com.erzin.core.config.ApplicationConfig;
import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import com.erzin.core.exception.NotPersistedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class UpdateUserTest {

    @Autowired
    UserService userService;



    @Test
    public void updateUser() throws NotPersistedException {

        String newName = "newName";
        String newLastName = "newLastName";
        String newPhoneNumber = "newPhoneNumber";

        // create a new user.
        User instance = new User("test", "test");

        // persist new instance.
        userService.saveUser(instance);

        // set new values for the instance.
        instance.setName(newName);
        instance.setLastName(newLastName);
        instance.setPhoneNumber(newPhoneNumber);

        //update the instance.
        userService.updateUser(instance);

        //get instance from db.
        User updatedUser = userService.findUser(instance.getId());

        Assert.assertEquals(updatedUser.getName(),newName);
        Assert.assertEquals(updatedUser.getLastName(),newLastName);
        Assert.assertEquals(updatedUser.getPhoneNumber(),newPhoneNumber);


        // try to update a not persisted user . this operation must throw a NotPersistedException

            //create a user.
        User notPersistedUser = new User("test", "test");

        try {
            // user not persisted so this operation should throw a NotPersistedException exception
            userService.updateUser(notPersistedUser);
        } catch (Throwable throwable) {
            // check  the exception
            Assert.assertEquals(throwable.getClass(), NotPersistedException.class);
        }


    }

}
