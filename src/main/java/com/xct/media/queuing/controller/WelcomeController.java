package com.xct.media.queuing.controller;

import com.xct.media.queuing.service.JobUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/30 14:46.
 * @Description:
 */
@Controller
public class WelcomeController {

    @Autowired
    private JobUserService jobUserService;

    @RequestMapping("/{page}")
    public String toPage(@PathVariable(value = "page") String page) {
        return page;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {

        if (StringUtils.isBlank(username)) {
            model.addAttribute("u_message", "请输入用户名！");
            return "login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("p_message", "请输入密码！");
            return "login";
        }

        boolean flag = jobUserService.userLogin(username, password);
        if (!flag) {
            model.addAttribute("message", "用户密码和密码输入错误！");
            return "login";
        }
        return "redirect:/queue/handler/dashboard";
    }


    @RequestMapping("/")
    public String welcome() {
        return "redirect:/login";
    }
}
