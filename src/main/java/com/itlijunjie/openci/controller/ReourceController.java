package com.itlijunjie.openci.controller;

import com.itlijunjie.openci.services.IReourceService;
import com.itlijunjie.openci.util.ConstUtil;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.Reource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created by ljj on 13/12/2016.
 * 资源管理控制器
 */
@Controller
@RequestMapping("/reource")
public class ReourceController {

    private PageInfo pageInfo = null;

    private IReourceService reourceService;

    public IReourceService getReourceService() {
        return reourceService;
    }

    @Resource
    public void setReourceService(IReourceService reourceService) {
        this.reourceService = reourceService;
    }

    /**
     * 查看所有资源
     * @param pageNo 页码
     * @param model model
     * @return 返回查看资源界面
     */
    @RequestMapping(value = "/reources", method = RequestMethod.GET)
    public String index(@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model) {
        if (pageNo == null) {
            pageNo = 1;
        }
        pageInfo = reourceService.pageList(null, pageNo, 20);
        model.addAttribute("reources", pageInfo);
        return "reource/list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "reource/add";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description,
            HttpServletRequest request, ModelMap model) {

        System.out.println("开始");
//        String path = request.getSession().getServletContext().getRealPath("/")
//                + "upload/";
        String path = ConstUtil.UPLOAD_PATH;
        String fileName = new Date().getTime() + file.getOriginalFilename();
        System.out.println("path======" + path + fileName);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        Reource reource = new Reource();
        reource.setName(fileName);
        reource.setDescription(description);
        reourceService.add(reource);
        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("fileUrl", request.getContextPath() + "/upload/"
                + fileName);

        return "reource/list";
    }
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        reourceService.delete(id);
        return "redirect:/reource/reources?pageNo=  " + pageInfo.getCurPage();
    }
}
