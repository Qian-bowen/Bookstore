package com.sisyphe.bookstore.websocket;

import com.sisyphe.bookstore.constant.Constant;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class WsHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception
    {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession session = servletRequest.getServletRequest().getSession();
        if(session!=null&&session.getAttribute(Constant.USER_ID)!=null)
        {
            attributes.put(Constant.USER_ID,session.getAttribute(Constant.USER_ID));
            attributes.put(Constant.USERNAME,session.getAttribute(Constant.USERNAME));
            attributes.put(Constant.USER_TYPE,session.getAttribute(Constant.USER_TYPE));
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
    }

}
