package com.sisyphe.bookstore.interceptor;

import com.sisyphe.bookstore.constant.Constant;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@Component
public class ApplicationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        httpServletResponse.setHeader("Access-Control-Allow-Private-Network","true");
        httpServletResponse.setHeader("Origin-Trial", Constant.CHROME_TOKEN);
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Access-Token");
//        httpServletResponse.setHeader("Vary","Origin");
//        httpServletResponse.setHeader("Access-Control-Request-Method","GET,HEAD,POST,PUT,DELETE,CONNECT,OPTIONS,TRACE");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
