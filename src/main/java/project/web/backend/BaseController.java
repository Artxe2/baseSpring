package project.web.backend;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import project.web.base.HashMapBinder;
import project.web.base.Vpw;

public class BaseController extends MultiActionController {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	private BaseLogic baseLogic;

	public void setBaseLogic(BaseLogic baseLogic) {
		this.baseLogic = baseLogic;
	}

	public void current_time(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("BaseController - current_time");
		Map<String, Object> pMap = HashMapBinder.getParameterMap(request);
		Vpw.printString(response, baseLogic.current_time_tx(pMap));
	}

	public String file_upload(HttpServletRequest request, HttpServletResponse response) {
		logger.info("BaseController - file_upload");
		Map<String, Object> pMap = HashMapBinder.getParameterMap(request);
		return baseLogic.file_upload(request);
	}
}
