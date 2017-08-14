package com.itlijunjie.openci.controller;

import com.itlijunjie.openci.services.IAppService;
import com.itlijunjie.openci.services.IBuildService;
import com.itlijunjie.openci.services.IJenkinsService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.exception.BuildException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/build")
public class BuildController {

    private PageInfo pageInfo = null;

    private IAppService appService;
    private IBuildService buildService;
    private IJenkinsService jenkinsService;

    public IBuildService getBuildService() {
        return buildService;
    }

    @Resource
    public void setBuildService(IBuildService buildService) {
        this.buildService = buildService;
    }

    public IAppService getAppService() {
        return appService;
    }

    @Resource
    public void setAppService(IAppService appService) {
        this.appService = appService;
    }

    public IJenkinsService getJenkinsService() {
        return jenkinsService;
    }

    @Resource
    public void setJenkinsService(IJenkinsService jenkinsService) {
        this.jenkinsService = jenkinsService;
    }

    @RequestMapping(value = "/builds", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        pageInfo = buildService.pageList(null, pageNo, 20);
        model.addAttribute("builds", pageInfo);
        return "build/list";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        appService.delete(id);
        return "redirect:/build/builds?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute(appService.load(id));
        return "build/show";
    }

    @ExceptionHandler(BuildException.class)
    public String handlerException(Exception ex, HttpServletRequest req) {
        req.setAttribute("ex", ex);
        return "inc/error";
    }
}