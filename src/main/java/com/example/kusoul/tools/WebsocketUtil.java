package com.example.kusoul.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
@ServerEndpoint(value = "message_websocket{userId}")
@Component
public class WebsocketUtil {

    // 记录连接的人数
    private static AtomicInteger onLineCount = new AtomicInteger();

    /** 存放所有在线的客户端 */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    //当前发消息的人员编号
    private String userid = "";


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) {
        onLineCount.incrementAndGet(); // 在线人数+1
        log.info("有新的连接加入： ", session.getId(), onLineCount.get());
        userid = userId;
        clients.put(userId, session);//加入map中
    }

    /**
     * 关闭连接
     * @param session
     */
    @OnClose
    public  void onClose(Session session) {
        onLineCount.decrementAndGet(); // 减少人数
        log.info("有一个关闭连接退出了此次连接", session.getId(),onLineCount.get());
        clients.remove(userid);  //从set中删除
    }

    /**
     * 收到客户端的消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务器收到客户端消息:",  message);
    }

    /**
     * 服务器发送消息给客户端
     * @param message
     * @param toSession
     */
    private static void sendMessage(String message, Session toSession) {
        try {
            // 同步
            toSession.getBasicRemote().sendText(message);
//            // 异步sendMessage
//            toSession.getAsyncRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务器发送消息给客户端失败：", e.getMessage());
        }
    }


    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     */
    public static void sendMessage(String sessionId,String message) {
        Session session = null;
        for (String userId : clients.keySet()) {
            if(userId.equals(sessionId)){
                session = clients.get(userId);
                break;
            }
        }
        if(session!=null){
            sendMessage(message, session);
        }
        else{
            log.warn("没有找到你指定ID的会话：{}",sessionId);
        }
    }

    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     */
    public static void sendAllMessage(String sessionId,String message) {
        Session session = null;
        for (String userId : clients.keySet()) {
            if(!userId.equals(sessionId)){
                session = clients.get(userId);
                sendMessage(message, session);
            }
        }
    }
}
