package com.itlijunjie.openci.controller;

import com.itlijunjie.openci.services.IProductInterfaceService;
import com.itlijunjie.openci.vo.ProductInterface;
import com.itlijunjie.openci.vo.exception.ProductInterfaceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/call")
public class JsonConreoller {
    private IProductInterfaceService productInterfaceService;

    public IProductInterfaceService getProductInterfaceService() {
        return productInterfaceService;
    }

    @Resource
    public void setProductInterfaceService(
            IProductInterfaceService productInterfaceService) {
        this.productInterfaceService = productInterfaceService;
    }

    @RequestMapping(value = "/json/{productname}/{parameter}")
    public void getJSON(@PathVariable String productname, @PathVariable String parameter, HttpServletResponse response, HttpServletRequest request) {
        String url = request.getRequestURI();
        try {
            this.charReader(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] l = url.split("/");
        parameter = l[l.length - 1];
        Map<String, String[]> params = request.getParameterMap();
        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                parameter = parameter + "?" + params.keySet().toArray()[i].toString() + "=" + request.getParameter(params.keySet().toArray()[i].toString());
            } else {
                parameter = parameter + "&" + params.keySet().toArray()[i].toString() + "=" + request.getParameter(params.keySet().toArray()[i].toString());
            }
        }
        ProductInterface p = productInterfaceService.getProductInterface(productname, parameter);
        response.setContentType("text/plain");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(p.getIfresule());
    }

    void charReader(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println(wholeStr);
    }

    @ExceptionHandler(ProductInterfaceException.class)
    public void handlerException(Exception ex, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(ex.getMessage());
    }
}