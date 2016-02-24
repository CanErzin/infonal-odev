import com.erzin.core.config.ApplicationConfig;
import com.erzin.core.dao.UserDao;
import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CheckUserPersistedTest {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Test
    public void checkUser() {
        // create a new user which is not persisted
        User instance = new User("test","test");
        instance.setId(Long.MIN_VALUE); //  set id to min value. Id must be positive so this user cant' be in db.
        // check if user exist
        boolean response = userDao.isPersisted(instance.getId());

        Assert.assertEquals(Boolean.FALSE, response); // user not persisted yet so response must be false.

        // create a new user and persist it

        User newUser = new User("test", "test");
        userService.saveUser(newUser);

        // check if user exist in the db.
        response = userDao.isPersisted(newUser.getId());

        //Expected result must be true
        Assert.assertEquals(Boolean.TRUE, response);

    }
}
