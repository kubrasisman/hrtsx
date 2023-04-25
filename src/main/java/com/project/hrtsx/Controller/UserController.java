package com.project.hrtsx.Controller;

import com.project.hrtsx.Model.Task;
import com.project.hrtsx.Model.User;
import com.project.hrtsx.Service.TaskService;
import com.project.hrtsx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    // private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register_form";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(User user, Model model) {
        User userModel = userService.findUserByEmail(user.getEmail());
        if (Objects.nonNull(userModel)) {
            model.addAttribute("error", "User is already exist");
            model.addAttribute("user", new User());
            return "register_form";
        }
        userService.saveUser(user);
        return "register_success";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "loginpage";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(HttpServletRequest request, HttpSession session, User user, Model model) {
        User userModel = userService.findUserByEmail(user.getEmail());
        if (Objects.isNull(userModel)) {
            model.addAttribute("error", "User not found");
            return "loginpage";
        }
        session.setAttribute("user", userModel);
        userModel.setLoggedIn(true);
        userService.updateUser(userModel);

        List<Task> userTasks = new ArrayList<>();

        if ("ADMIN".equalsIgnoreCase(userModel.getUserRole())) {
            userTasks = taskService.findAll();
        } else {
            userTasks = taskService.findTaskByUsers(userModel);
        }

        model.addAttribute("userTask", userTasks);
        return "welcome";
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");

        if (Objects.isNull(sessionUser)) return "homepage";

        if ("ADMIN".equalsIgnoreCase(sessionUser.getUserRole())) {
            List<User> listUsers = userService.allUsers();
            model.addAttribute("listUsers", listUsers);
        } else {
            model.addAttribute("warning", "To see all user please have ADMIN role. ");
            model.addAttribute("listUsers", sessionUser);
        }
        return "users";
    }

    @RequestMapping(value = "/createTask", method = RequestMethod.GET)
    public String addTask(HttpServletRequest request, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        User userModel = userService.findUserByEmail(sessionUser.getEmail());
        if (Boolean.FALSE.equals(userModel.getLoggedIn())) {
            model.addAttribute("logout", true);
            return "homepage";
        }
        model.addAttribute("task", new Task());
        return "task_form";
    }

    @RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public String saveTask(HttpServletRequest request, Task task, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        task.setUser(sessionUser);
        taskService.saveTask(task);
        List<Task> userTasks = new ArrayList<>();

        if ("ADMIN".equalsIgnoreCase(sessionUser.getUserRole())) {
            List<User> listUsers = userService.allUsers();
            userTasks = taskService.findAll();
        } else {
            userTasks = taskService.findTaskByUsers(sessionUser);
        }
        model.addAttribute("userTask", userTasks);
        return "welcome";
    }

    @RequestMapping(value = "/showUpdateForm/{id}", method = RequestMethod.GET)
    public ModelAndView showUpdateForm(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        User userModel = userService.findUserByEmail(sessionUser.getEmail());
        ModelAndView editView = new ModelAndView("updateTaskForm");
        if (Boolean.FALSE.equals(userModel.getLoggedIn())) {
            model.addAttribute("logout", true);
            return new ModelAndView("homepage");
        }
        Task task = taskService.findById(id);
        editView.addObject("userTask", task);
        return editView;
    }

    @RequestMapping(value = "/userDetail/{id}", method = RequestMethod.GET)
    public ModelAndView userDetail(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        User user = userService.findUserById(id);
        User sessionUser = (User) request.getSession().getAttribute("user");
        ModelAndView editView = new ModelAndView("userDetailForm");
        if (Boolean.FALSE.equals(sessionUser.getLoggedIn())) {
            model.addAttribute("logout", true);
            return new ModelAndView("homepage");
        }
        List<Task> task = taskService.findTaskByUsers(user);
        editView.addObject("userTask", task);
        return editView;
    }

    @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("userTask") Task task, Model model) {
        taskService.updateTask(task);
        List<Task> taskList = taskService.findTaskByUsers(task.getUser());
        model.addAttribute("userTask", taskList);
        return "welcome";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        sessionUser.setLoggedIn(false);
        userService.updateUser(sessionUser);
        request.getSession().removeAttribute("user");
        model.addAttribute("logout", true);
        return "homepage";
    }
}
