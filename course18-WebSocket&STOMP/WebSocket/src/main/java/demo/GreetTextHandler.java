package demo;

import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * TextWebSocketHandler 是 AbstractWebSocketHandler 的子类，
 * 它重载了父类的 handleBinaryMessage() 方法，若收到二进制消息则断开连接。
 * 类似的消息类型还有 Binary 和 Pong
 *
 * @author justZero
 * @since 2019/1/5
 */
public class GreetTextHandler extends TextWebSocketHandler {}
