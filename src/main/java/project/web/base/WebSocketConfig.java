package project.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import project.web.base.EchoHandler;

public class WebSocketConfig implements WebSocketConfigurer {
	Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		logger.info("registerWebSocketHandlers 호출 성공");
		registry.addHandler(myHandler(), "/echo");
	}
	@Bean
	public WebSocketHandler myHandler() {
		logger.info("myHandler 호출 성공");
		return new EchoHandler();
	}
}
