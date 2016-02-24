import com.erzin.core.config.ApplicationConfig;
import com.erzin.core.dao.UserDao;
import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class AddUserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Test
    public void addUserTest() {

        long currentUserCount = userService.getUserCount(); // get the current user count

        User newUser = new User("name", "lastname"); // create a new user

        userService.saveUser(newUser); // persist new user

        long newUserCount = userService.getUserCount(); // get latest user count

        // we add 1 new user so latest user count must be 1 greater than the old user count
        Assert.assertEquals(currentUserCount + 1, newUserCount);

    }


}
