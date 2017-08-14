package com.itlijunjie.openci.component;

import com.itlijunjie.openci.services.IAppService;
import com.itlijunjie.openci.services.IBuildService;
import com.itlijunjie.openci.services.IJenkinsService;
import com.itlijunjie.openci.util.DesUtils;
import com.itlijunjie.openci.util.enums.BuildStatusEnum;
import com.itlijunjie.openci.vo.App;
import com.itlijunjie.openci.vo.Jenkins;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

public class BuildThread extends Thread {
    private int serverId;
    private int appId;
    private int buildId;
    private IAppService appService;
    private IBuildService buildService;
    private IJenkinsService jenkinsService;

    public BuildThread(){
        super();
    }

    public BuildThread(int serverId, int appId, int buildId) {
        super();
        this.serverId = serverId;
        this.appId = appId;
        this.buildId = buildId;
    }
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
    //1.监控成功失败状态。
    @Override
    public void run() {
        App app = appService.load(appId);
        com.itlijunjie.openci.vo.Build build = buildService.load(buildId);
        Jenkins jenkins = jenkinsService.load(app.getServerId());
        DesUtils desUtils = null;
        try {
            boolean i = true;
            while (i) {
                desUtils = new DesUtils();
                JenkinsServer jenkinsServer = new JenkinsServer(new URI(jenkins.getUrl()), jenkins.getUsername(), desUtils.decrypt(jenkins.getPassword()));
                Map<String, Job> jobs = jenkinsServer.getJobs();
                JobWithDetails jobWithDetails = jenkinsServer.getJob(app.getItemName());
                com.offbytwo.jenkins.model.Build b = jobWithDetails.getBuildByNumber(build.getBuildNumber());
                BuildWithDetails buildWithDetails = b.details();
                boolean isBuilding = buildWithDetails.isBuilding();
                if (isBuilding) {
                    System.out.println("监控中"+ "--------" + app.getItemName() + "--------" + build.getBuildNumber());
                    sleep(10);
                } else {
                    i = false;
                    BuildResult buildResult = buildWithDetails.getResult();
                    if (buildResult == BuildResult.SUCCESS) {
                        build.setStatus(BuildStatusEnum.Succeed.getValue());
                    } else {
                        build.setStatus(BuildStatusEnum.Fail.getValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
