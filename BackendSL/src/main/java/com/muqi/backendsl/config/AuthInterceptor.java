package com.muqi.backendsl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muqi.backendsl.constant.UserConstant;
import com.muqi.backendsl.enums.StatusCodeEnum;
import com.muqi.backendsl.model.vo.ResultVO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("\n-------- LogInterception.preHandle --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Start Time: " + System.currentTimeMillis());
        String token = (String) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        System.out.println("token: " + token + "\nuser-Token: " + request.getHeader("User-Token"));
        if (token == null || !Objects.equals(token, request.getHeader("User-Token"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultVO<Object> result = ResultVO.fail(StatusCodeEnum.NO_LOGIN);
            // Convert the result object to JSON and write it to the response
            response.setContentType("application/json");
            objectMapper.writeValue(response.getWriter(), result);
            return false;
//            PrintWriter out;
//            JSONObject res = new JSONObject();
//            res.put("code", 401);
//            res.put("message", "访问无权限");
//            out = response.getWriter();
//            out.append(res.toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("\n-------- LogInterception.postHandle --- ");
        System.out.println("Request URL: " + request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("\n-------- LogInterception.afterCompletion --- ");
        System.out.println("Request URL: " + request.getRequestURL());
    }
}
