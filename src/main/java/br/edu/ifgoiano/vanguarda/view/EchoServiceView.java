package br.edu.ifgoiano.vanguarda.view;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoServiceView {

    @OnMessage
    public String handleMessage(String message) {
        return "Thanks for the message: " + message;
    }
}
