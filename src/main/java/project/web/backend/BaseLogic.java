package project.web.backend;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;

import project.web.base.HashMapBinder;
import project.web.base.Vpw;


public class BaseLogic {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseLogic.class);
	private SqlSessionTemplate sqlSessionTemplate = null;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	protected String current_time(Map<String, Object> pMap) {
		logger.info("BaseLogic - current_time");
		return (String) MybatisDao.selectObject(sqlSessionTemplate, "current_time", pMap);
	}

	protected String file_upload(HttpServletRequest request) {
		logger.info("BaseLogic - file_upload");
		try {
			Map<String, Object> pMap = HashMapBinder.getMultipartMap(request, "resources/uploaded_files");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Vpw.redirect("base/file_upload");
	}
}
