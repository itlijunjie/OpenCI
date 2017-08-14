package com.itlijunjie.openci.controller;

import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by itlijunjie on 14/08/2017.
 */
@Controller
@RequestMapping("/app")
public class AppController {
    @RequestMapping(value = "/index")
    public String index() {
        return "app/index";
    }

    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public void build() throws URISyntaxException, IOException {
        System.out.print("开始编译");
        JenkinsServer jenkins = new JenkinsServer(new URI("http://localhost:8080/"), "sysadmin", "admin");
        Map<String, Job> jobs = jenkins.getJobs();
        JobWithDetails job = jobs.get("QQHeader").details();
        job.build();
    }
}
