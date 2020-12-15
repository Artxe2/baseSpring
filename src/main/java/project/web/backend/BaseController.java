package project.web.backend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import project.web.base.Vpw;

public class BaseController extends MultiActionController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
    private BaseLogic baseLogic;

    public void setBaseLogic(BaseLogic baseLogic) {
        this.baseLogic = baseLogic;
    }

    public void currentTime(HttpServletRequest request, HttpServletResponse response) {
        logger.info("BaseController - currentTime");
        String s = "";
        try {
            s = baseLogic.txCurrentTime(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Vpw.printString(response, s);
    }

    public String uploadFiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("BaseController - uploadFiles");
        baseLogic.uploadFiles(request);
        return Vpw.redirect("base/uploadFiles");
    }
}
