package com.erzin.controller;

import com.erzin.core.model.User;
import com.erzin.core.service.UserService;
import com.erzin.core.utility.ResponseEnum;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/api")
public class UserListController {

    static Logger logger = Logger.getLogger(UserListController.class);

    @Autowired
    UserService userService;

    @Autowired
    ReCaptcha reCaptcha;


    /**
     * This method returns list of users as a json formatted response
     * when a get request is made to /userList url.
     *
     * @return
     */
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUserList() {
        List<User> userList = null;
        logger.debug("User List Requested.");
        try {
            userList = userService.getAllUsers(); //  get all the users
            logger.debug("User List successfully fetched with" + userList.size() + " users.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return userList;

    }

    /**
     * This Method will be used when a user deleted.
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            userService.deleteUser(id);
            return ResponseEnum.SUCCESS.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEnum.ERROR.getMessage();
        }
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phoneNumber");
        if (name == null || lastName == null) {
            return ResponseEnum.ERROR.getMessage();
        } else {
            try {
                User instance = new User(name, lastName);
                instance.setId(id);
                instance.setPhoneNumber(phone);
                userService.updateUser(instance);
                return ResponseEnum.SUCCESS.getMessage();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEnum.ERROR.getMessage();
            }
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");

            String capta = request.getParameter("recaptcha_challenge_field");
            String captaResponse = request.getParameter("recaptcha_response_field");
            String address = request.getRemoteAddr();

            ReCaptchaResponse response = reCaptcha.checkAnswer(address, capta, captaResponse);
            if (response.isValid()) {
                User instance = new User(name, lastName);
                instance.setPhoneNumber(phoneNumber);
                userService.saveUser(instance);
                return ResponseEnum.SUCCESS.getMessage();
            } else {
                return ResponseEnum.CAPTA_FAIL.getMessage();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEnum.ERROR.getMessage();
        }
    }


}
