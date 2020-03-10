package example.spring.controller;

import example.spring.domain.User;
import example.spring.domain.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Hashtable;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "current", method = RequestMethod.GET)
    @ModelAttribute("currentUser")
    public User userHome()
    {
        User user = new User();
        user.setUserId(1234987234L);
        user.setUserNo("A0932");
        user.setName("Adam Johnson");
        return user;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String displayUsers(Map<String, Object> model)
    {
        System.out.println("[Info]: displayUsers is called");
        model.put("userList", this.userDatabase.values());
        return "user/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String createUser(Map<String, Object> model)
    {
        System.out.println("[Info]: createUser(get) is called");
        model.put("userForm", new UserForm());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public View createUser(UserForm form)
    {
        System.out.println("[Info]: createUser(post) is called");
        User user = new User();
        user.setUserId(this.getNextUserId());
        user.setUserNo(form.getUserNo());
        user.setName(form.getName());
        this.userDatabase.put(user.getUserId(), user);

        return new RedirectView("/user/list", true, false);
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String editUser(Map<String, Object> model,
                           @PathVariable("userId") long userId)
    {
        System.out.println("[Info]: editUser(get) is called");
        User user = this.userDatabase.get(userId);
        UserForm form = new UserForm();
        form.setUserNo(user.getUserNo());
        form.setName(user.getName());
        model.put("userForm", form);

        return "user/edit";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.POST)
    public View editUser(UserForm form, @PathVariable("userId") long userId)
    {
        System.out.println("[Info]: editUser(post) is called");
        User user = this.userDatabase.get(userId);
        user.setUserNo(form.getUserNo());
        user.setName(form.getName());

        return new RedirectView("/user/list", true, false);
    }

    private final Map<Long, User> userDatabase = new Hashtable<>();
    private volatile long userIdSequence = 1L;

    private synchronized long getNextUserId()
    {
        return this.userIdSequence++;
    }
}
