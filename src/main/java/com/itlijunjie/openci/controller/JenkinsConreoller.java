package com.itlijunjie.openci.controller;

import com.itlijunjie.openci.services.IJenkinsService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.util.DesUtils;
import com.itlijunjie.openci.vo.Jenkins;
import com.itlijunjie.openci.vo.exception.JenkinsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/jenkins")
public class JenkinsConreoller {

    private PageInfo pageInfo = null;

    private IJenkinsService jenkinsService;

    /**
     * @return the jenkinsService
     */
    public IJenkinsService getJenkinsService() {
        return jenkinsService;
    }

    /**
     * @param jenkinsService the jenkinsService to set
     */
    @Resource
    public void setJenkinsService(IJenkinsService jenkinsService) {
        this.jenkinsService = jenkinsService;
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
    public String add(@Valid Jenkins jenkins, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "jenkins/add";
        }
        DesUtils desUtils = new DesUtils();
        jenkins.setPassword(desUtils.encrypt(jenkins.getPassword()));
        jenkinsService.add(jenkins);
        return "redirect:/jenkins/jenkinses?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        jenkinsService.delete(id);
        return "redirect:/jenkins/jenkinses?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) throws Exception {
        Jenkins jenkins = jenkinsService.load(id);
        DesUtils desUtils = new DesUtils();
        jenkins.setPassword(desUtils.decrypt(jenkins.getPassword()));
        model.addAttribute("jenkins", jenkins);
        return "jenkins/update";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable int id, @Valid Jenkins jenkins, BindingResult result) throws Exception {
        jenkins.setId(id);
        DesUtils desUtils = new DesUtils();
        jenkins.setPassword(desUtils.encrypt(jenkins.getPassword()));
        jenkinsService.update(jenkins);
        return "redirect:/jenkins/jenkinses?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable int id, Model model) throws Exception {
        Jenkins jenkins = jenkinsService.load(id);
        DesUtils desUtils = new DesUtils();
        jenkins.setPassword(desUtils.decrypt(jenkins.getPassword()));
        model.addAttribute("jenkins", jenkins);
        return "jenkins/show";
    }

    @ExceptionHandler(JenkinsException.class)
    public String handlerException(Exception ex, HttpServletRequest req) {
        req.setAttribute("ex", ex);
        return "inc/error";
    }
}