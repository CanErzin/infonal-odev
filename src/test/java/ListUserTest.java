import com.erzin.core.config.ApplicationConfig;
import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ApplicationConfig.class)
public class ListUserTest {

    @Autowired
    UserService userService;

    @Test
    public void listUsers() {


        Long userCount  ;

        //get the count of users in the db
        userCount = userService.getUserCount();

        // try to fecth all the users
        List<User> userList = userService.getAllUsers();

        // check if userService.getAllUsers() returns  all the users in the db.
        Assert.assertEquals(userCount, new Long(userList.size()));

    }
}
