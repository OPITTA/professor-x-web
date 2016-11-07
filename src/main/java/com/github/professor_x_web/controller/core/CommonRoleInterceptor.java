package com.github.professor_x_web.controller.core;

import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.constent.UserRole;
import com.github.professor_x_web.util.DateUtil;
import com.github.professor_x_web.util.VolecityUIUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 暂时实现一种角色（登录用户）
 *
 * @author xin.cao@100credit.com
 */
public class CommonRoleInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CommonRoleInterceptor.class);
    private static final String defaultUrl = "/forbidden";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        boolean isOk = true;
        if (session.getAttribute(Config.USER_ROLE) != null) {
            String userRole = (String) session.getAttribute(Config.USER_ROLE);
            isOk = userRole.contains(UserRole.COMMON.getKey());
        } else {
            isOk = false;
        }
        if (!isOk) {
            ModelAndView mv = new ModelAndView(defaultUrl);
            mv.addObject("forward", url);
            throw new ModelAndViewDefiningException(mv);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        if (modelAndView != null) {
            if (session.getAttribute(Config.USER_ROLE) != null) {
                String userRole = (String) session.getAttribute(Config.USER_ROLE);
                if (userRole.contains(UserRole.COMMON.getKey())) {
                    modelAndView.addObject(UserRole.COMMON.getKey(), UserRole.COMMON.getKey());
                }
                if (userRole.contains(UserRole.ADMIN.getKey())) {
                    modelAndView.addObject(UserRole.ADMIN.getKey(), UserRole.ADMIN.getKey());
                }
                if (userRole.contains(UserRole.SUPER_ADMIN.getKey())) {
                    modelAndView.addObject(UserRole.SUPER_ADMIN.getKey(), UserRole.SUPER_ADMIN.getKey());
                }
            }
            modelAndView.addObject("DateUtil", DateUtil.getInstance());
            modelAndView.addObject("VolecityUIUtil", VolecityUIUtil.getInstance());
        }
    }

}
