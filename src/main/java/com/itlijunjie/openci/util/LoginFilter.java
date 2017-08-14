package com.itlijunjie.openci.util;

import com.itlijunjie.openci.vo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter extends HttpServlet implements Filter {

    private static final long serialVersionUID = -8527942053687062625L;

    public void destroy() {

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpSession session = request.getSession(true);
        User usercode = (User) session.getAttribute("loginUser");
        String url = request.getRequestURI();

        //静态资源不做处理 和 接口调用不审核
        if (url.startsWith(ConstUtil.SERVER_NAME + ConstUtil.SERVER_RESOURCES_NAME) ||
                url.startsWith(ConstUtil.SERVER_NAME + ConstUtil.JSON_TEST_PATH_NAME) ||
                url.startsWith(ConstUtil.SERVER_NAME + ConstUtil.SDEMO_PATH_NAME) ||
                url.startsWith(ConstUtil.SERVER_NAME + ConstUtil.DDEMO_PATH_NAME) ||
                url.startsWith(ConstUtil.SERVER_NAME + ConstUtil.UPLOAD_RESOURCE_NAME)
                ) {
            arg2.doFilter(arg0, arg1);
            return;
        }

        //查看session看看是否登录了
        if (usercode == null) {
            /**
             * 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
             * 当请求登录和验证码是时不进行过滤其余情况均进行过滤
             */
            if (url != null && !url.equals("") && ((url.indexOf("login") >= 0 && url.indexOf("user") >= 0) || url.indexOf("drawCheckcode") >= 0)) {
                //没有登录成功，但是准备登录
            } else {
                //非法操作
                response.sendRedirect("/openci/user/login");
                return;
            }
        }
        // 已通过验证，用户访问继续
        arg2.doFilter(arg0, arg1);
        return;
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
