package project.web.backend;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class DownloadController extends MultiActionController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DownloadController.class);
    private static final String ROOTPATH = "D:\\workspace_jsp\\baseSpring\\src\\main\\webapp\\static\\";

    public void download(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DownloadController - currentTime");
        String filePath = request.getParameter("filePath");
        java.io.File file = new java.io.File(ROOTPATH, filePath);
        try (
                java.io.BufferedInputStream bis = new java.io.BufferedInputStream(new java.io.FileInputStream(file));
                java.io.OutputStream os = response.getOutputStream();
                ) {
            request.setCharacterEncoding("UTF-8");
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader ("Content-Length", String.valueOf(file.length()));
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1) + "\"");
            response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
            int i = -1;
            while((i = bis.read()) > -1){
                os.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
