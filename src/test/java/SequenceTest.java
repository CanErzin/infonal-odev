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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SequenceTest {

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Test
    public void sequenceTest() {
        List<User> userList = userDao.findAll();

        Long max= 0L;

        for (User user : userList) {
            if (user.getId() > max) {
                max = user.getId();
            }
        }

        Long sequence = userService.getSequence();

        Assert.assertEquals(max, sequence);



    }

}

