package com.project.hrtsx.Controller;

import com.project.hrtsx.Model.Task;
import com.project.hrtsx.Model.User;
import com.project.hrtsx.Service.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class HomePageController {

    //  private static final Logger LOG = Logger.getLogger(HomePageController.class);

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        User sessionUser = (User) request.getSession().getAttribute("user");

        if (Objects.isNull(sessionUser)) return "/homepage";

        List<Task> userTasks = new ArrayList<>();
        if ("ADMIN".equalsIgnoreCase(sessionUser.getUserRole())) {
            userTasks = taskService.findAll();
        } else {
            userTasks = taskService.findTaskByUsers(sessionUser);
        }
        model.addAttribute("userTask", userTasks);
        return "welcome";
    }
}
