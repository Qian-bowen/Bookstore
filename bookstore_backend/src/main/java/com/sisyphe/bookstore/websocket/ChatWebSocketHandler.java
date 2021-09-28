package com.sisyphe.bookstore.websocket;

import com.alibaba.fastjson.JSONObject;
import com.sisyphe.bookstore.constant.Constant;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/*
WS_PRIVATE_CHAT
{
 type:0,to:123,msg:"hello world"
}
WS_BROADCAST_CHAT
{
 type:1,,msg:"hello world"
}
 */
@Component
@Log4j2
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static ConcurrentHashMap<Integer,WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, Queue<String>> pendingMsgMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer userId= (Integer) session.getAttributes().get(Constant.USER_ID);
        if(userId==null) return;
        if(sessionMap.containsKey(userId))
        {
            sessionMap.replace(userId,session);
        }
        else
        {
            sessionMap.put(userId,session);
        }

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",100);
        jsonObject.put("type",0);
        jsonObject.put("msg","welcome!");
        jsonObject.put("from","System");
        jsonObject.put("time", LocalDateTime.now().toString());
        String str=jsonObject.toJSONString();

        session.sendMessage(new TextMessage(str));
        //send pending msg
        Queue<String> pendingMsg=pendingMsgMap.get(userId);
        if(pendingMsg!=null&&!pendingMsg.isEmpty())
        {
            log.info("pending not empty");
            while(!pendingMsg.isEmpty())
            {
                String msg=pendingMsg.poll();
                session.sendMessage(new TextMessage(msg));
                log.info("send pending");
            }
        }
        log.info("connect");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, @NotNull CloseStatus closeStatus) throws Exception
    {
        Map<String, Object> attributes=session.getAttributes();
        Integer userId= (Integer) attributes.get(Constant.USER_ID);
        if(sessionMap.containsKey(userId))
        {
            sessionMap.remove(userId);
            log.info("remove");
        }
        else
        {
            log.error("remove session fail");
        }
    }

    @Override
    public void handleMessage(@NotNull WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println(message.getPayload());
        String msg=((TextMessage)message).getPayload();
        JSONObject jsonObject=JSONObject.parseObject(msg);
        Integer type= jsonObject.getInteger("type");
        switch(type)
        {
            case -1:
                return;
            case 0://private chat
            {
                Integer to= jsonObject.getInteger("to");
                String toMsg=jsonObject.getString("msg");
                String fromName= (String) session.getAttributes().get(Constant.USERNAME);
                unicast(to,fromName,toMsg);
                break;
            }
            case 1:
            {
                String allMsg=jsonObject.getString("msg");
                String fromName= (String) session.getAttributes().get(Constant.USERNAME);
                broadcast(fromName,allMsg);
                break;
            }
        }
    }

    private synchronized void unicast(int userIdTo,String fromName,String msg)throws Exception
    {
        WebSocketSession to=sessionMap.get(userIdTo);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",100);
        jsonObject.put("type",0);
        jsonObject.put("msg",msg);
        jsonObject.put("from",fromName);
        jsonObject.put("time", LocalDateTime.now().toString());
        String str=jsonObject.toJSONString();
        if(to==null||!to.isOpen())
        {
            addToPending(userIdTo,str);
            return;
        }
        if(to.isOpen())
        {
            to.sendMessage(new TextMessage(str));
            log.info("unicast success:"+str);
        }
    }

    private synchronized void broadcast(String fromName,String msg) throws IOException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",100);
        jsonObject.put("type",1);
        jsonObject.put("msg",msg);
        jsonObject.put("from",fromName);
        jsonObject.put("time", LocalDateTime.now().toString());
        String str=jsonObject.toJSONString();
        TextMessage text=new TextMessage(str);

        for(Map.Entry<Integer,WebSocketSession> s: sessionMap.entrySet())
        {
            if(s.getValue().isOpen())
            {
                s.getValue().sendMessage(text);
                log.info("broadcast success to "+s.getKey()+" "+text);
            }
            else
            {
                addToPending(s.getKey(),str);
            }
        }
    }

    private boolean addToPending(int userId,String msg)
    {
        //check is user
        if(pendingMsgMap.containsKey(userId))
        {
            pendingMsgMap.get(userId).add(msg);
            log.info("add pending to "+userId);
        }
        else
        {
            Queue<String> q=new LinkedList<String>();
            q.add(msg);
            pendingMsgMap.put(userId,q);
            log.info("add pending to "+userId);
        }
        return true;
    }

}
