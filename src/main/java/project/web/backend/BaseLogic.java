package project.web.backend;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;

import project.web.base.HashMapBinder;
import project.web.base.Vpw;

public class BaseLogic {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseLogic.class);
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    protected String txCurrentTime(HttpServletRequest request) {
        logger.info("BaseLogic - currentTime");
        Map<String, Object> pMap = HashMapBinder.getParameterMap(request);
        return (String) MybatisDao.selectObject(sqlSessionTemplate, "currentTime", pMap);
    }

    protected String uploadFiles(HttpServletRequest request) throws ServletException, IOException {
        logger.info("BaseLogic - uploadFiles");
        HashMapBinder.getMultipartMap(request, "base_files");
        return Vpw.redirect("base/uploadFiles");
    }
}
