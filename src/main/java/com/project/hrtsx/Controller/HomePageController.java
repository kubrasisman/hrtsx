package com.project.hrtsx.Controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomePageController {

  //  private static final Logger LOG = Logger.getLogger(HomePageController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home() {
        return "/homepage";
    }
}
