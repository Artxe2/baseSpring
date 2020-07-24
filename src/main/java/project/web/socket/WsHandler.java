package project.web.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WsHandler extends TextWebSocketHandler {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WsHandler.class);
	List<WebSocketSession> sessionList = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished({})", session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("handleTextMessage({},{})", session.getId(), message.getPayload());
		session.sendMessage(message);
		String msg_chat = "100";// 일반대화(1:n대화)
		String msg_null = "101";// 빈 문자가 넘어올때
		String msg_exit = "500";// 방 나갈때

		StringTokenizer st = new StringTokenizer(message.getPayload(), "#");
		String kind = st.nextToken();
		// 메시지가 null체크
		if (msg_null.equals(kind)) {
			String info = "빈 공간을 입력했습니다.";
			for (WebSocketSession sess : sessionList) {
				sess.sendMessage(new TextMessage(info));
			}
		}
		// 메시지가 null 이 아닌 경우
		else {
			// 100번 일반대화 일때
			if (msg_chat.equals(kind)) {
				String msg = st.nextToken();
				for (WebSocketSession sess : sessionList) {
					sess.sendMessage(new TextMessage(session + msg));
				}
			}
			// 500번 나가기 일 때
			if (msg_exit.equals(kind)) {
				String msg = st.nextToken();
				for (WebSocketSession sess : sessionList) {
					sess.sendMessage(new TextMessage(session + msg));
				}
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("afterConnectionClosed({}, {})", session, status);
	}
}
