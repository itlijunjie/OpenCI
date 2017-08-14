package com.itlijunjie.openci.controller;

import com.itlijunjie.openci.services.IAppService;
import com.itlijunjie.openci.services.IBuildService;
import com.itlijunjie.openci.services.IJenkinsService;
import com.itlijunjie.openci.util.DesUtils;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.App;
import com.itlijunjie.openci.vo.Jenkins;
import com.itlijunjie.openci.vo.exception.AppException;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppController {

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

    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        pageInfo = appService.pageList(null, pageNo, 20);
        model.addAttribute("apps", pageInfo);
        return "app/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new App());
        //这里是跳转的jsp页面
        return "app/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid App app, BindingResult result) {
        if (result.hasErrors()) {
            return "app/add";
        }
        appService.add(app);
        return "redirect:/app/apps?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        appService.delete(id);
        return "redirect:/app/apps?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        model.addAttribute(appService.load(id));
        return "app/update";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable int id, @Valid App app, BindingResult result) {
        app.setId(id);
        appService.update(app);
        return "redirect:/app/apps?pageNo=" + pageInfo.getCurPage();
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute(appService.load(id));
        return "app/show";
    }

    @RequestMapping(value = "build/{appId}")
    public void build(@PathVariable int appId) throws Exception, URISyntaxException, IOException {
        App app = appService.load(appId);
        Jenkins jenkins = jenkinsService.load(app.getServerId());
        DesUtils desUtils = new DesUtils();
        JenkinsServer jenkinsServer = new JenkinsServer(new URI(jenkins.getUrl()), jenkins.getUsername(), desUtils.decrypt(jenkins.getPassword()));
        Map<String, Job> jobs = jenkinsServer.getJobs();
        JobWithDetails job = jobs.get(app.getItemName()).details();
        int buildNumber = job.getNextBuildNumber();
        int serverId = app.getServerId();
        if (buildService.load(serverId, appId, buildNumber) != null) {
            throw new AppException("创建中，请稍后再试。");
        }
        /*
          如果正在build还允不允许新建任务
         */
        if (job.isBuildable()) {
            com.offbytwo.jenkins.model.Build build = job.getLastBuild();
            if (build != null) {
                BuildWithDetails details = build.details();
                if (!details.isBuilding()) {
                    appBuild(job, serverId, appId, buildNumber);
                } else {
                    throw new AppException("该项目构建中，稍后再来");
                }
            } else {
                appBuild(job, serverId, appId, buildNumber);
            }
        } else {
            throw new AppException("该项目不可构建");
        }
    }

    private void appBuild(JobWithDetails job, int serverId, int appId, int buildNumber) throws IOException {
        com.itlijunjie.openci.vo.Build b = new com.itlijunjie.openci.vo.Build();
        b.setServerId(serverId);
        b.setBuildNumber(buildNumber);
        b.setAppId(appId);
        b.setStatus(1);
        b = buildService.add(b);
        job.build();
//        BuildThread t = new BuildThread(serverId, appId, b.getId());
//        t.start();
    }

    @ExceptionHandler(AppException.class)
    public String handlerException(Exception ex, HttpServletRequest req) {
        req.setAttribute("ex", ex);
        return "inc/error";
    }
}
