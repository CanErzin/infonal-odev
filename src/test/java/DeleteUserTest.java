import com.erzin.core.config.ApplicationConfig;
import com.erzin.core.dao.UserDao;
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
public class DeleteUserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Test
    public void deleteUser() throws NotPersistedException {
        // create a new user and save it
        User instance = new User("test", "test");
        userService.saveUser(instance);
        // delete the persisted user.
        try {
            userService.deleteUser(instance.getId());
        } catch (NotPersistedException e) {
            e.printStackTrace();
        }

        //check if the user in the db. it shouldn't be after deletion.
        boolean isUserExist = userDao.isPersisted(instance.getId());

        Assert.assertEquals(Boolean.FALSE, isUserExist);

        // create a new user and not persist it.
        User notPersistedUser = new User("test", "test");
        notPersistedUser.setId(Long.MIN_VALUE);
        //try to delete not persisted user it must throw a custom NotPersistedExcepiton
        try {
            userService.deleteUser(notPersistedUser.getId());
        } catch (Throwable throwable) {
            Assert.assertEquals(throwable.getClass(), NotPersistedException.class);
        }

    }
}
