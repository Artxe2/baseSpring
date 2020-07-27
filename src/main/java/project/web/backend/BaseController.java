package project.web.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import project.web.base.Vpw;

public class BaseController extends MultiActionController {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	private BaseLogic baseLogic;

	public void setBaseLogic(BaseLogic baseLogic) {
		this.baseLogic = baseLogic;
	}

	public void current_time(HttpServletRequest request, HttpServletResponse response) {
		logger.info("BaseController - current_time");
		String s;
		try {
			s = baseLogic.current_time(request);
		} catch (Exception e) {
			e.printStackTrace();
			s = "";
		}
		Vpw.printString(response, s);
	}

	public String file_upload(HttpServletRequest request, HttpServletResponse response) {
		logger.info("BaseController - file_upload");
		String s;
		try {
			s = baseLogic.file_upload(request);
		} catch (Exception e) {
			e.printStackTrace();
			s = "";
		}
		return Vpw.redirect("base/file_upload");
	}

	public String file_download(HttpServletRequest request, HttpServletResponse response) {
		logger.info("BaseController - file_download");
		return "base/file_download";
	}
}
