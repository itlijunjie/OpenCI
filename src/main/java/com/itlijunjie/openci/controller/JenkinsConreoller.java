package com.itlijunjie.openci.controller;

import com.itlijunjie.openci.services.IJenkinsService;
import com.itlijunjie.openci.util.CaptchaUtil;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.util.StringUtil;
import com.itlijunjie.openci.vo.Jenkins;
import com.itlijunjie.openci.vo.exception.JenkinsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/jenkins")
public class JenkinsConreoller {

    private PageInfo pageInfo = null;

    private IJenkinsService jenkinsService;

    /**
     * @return the userService
     */
    public IJenkinsService getJenkinsService() {
        return jenkinsService;
    }

    /**
     * @param userService the userService to set
     */
    @Resource
    public void setJenkinsService(IJenkinsService userService) {
        this.jenkinsService = userService;
    }

    @RequestMapping(value = "/jenkinses", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        pageInfo = jenkinsService.pageList(null, pageNo, 20);
        model.addAttribute("jenkinses", pageInfo);
        return "jenkins/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Jenkins());
        //这里是跳转的jsp页面
        return "jenkins/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid Jenkins jenkins, BindingResult result) {
        if (result.hasErrors()) {
            return "jenkins/add";
        }
        jenkins.setPassword(StringUtil.MD5(jenkins.getPassword()));
        jenkinsService.add(jenkins);
        return "redirect:/jenkins/jenkinses?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        jenkinsService.delete(id);
        return "redirect:/jenkins/jenkinses?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        model.addAttribute(jenkinsService.load(id));
        return "jenkins/update";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable int id, @Valid Jenkins jenkins, BindingResult result) {
        jenkins.setId(id);
        jenkins.setPassword(StringUtil.MD5(jenkins.getPassword()));
        jenkinsService.update(jenkins);
        return "redirect:/jenkins/jenkinses?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute(jenkinsService.load(id));
        return "jenkins/show";
    }

    @ExceptionHandler(JenkinsException.class)
    public String handlerException(Exception ex, HttpServletRequest req) {
        req.setAttribute("ex", ex);
        return "inc/error";
    }
}