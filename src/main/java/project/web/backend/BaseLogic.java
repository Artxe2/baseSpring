package project.web.backend;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.mybatis.Dao;
import org.apache.mybatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;

import project.web.base.HashMapBinder;
import project.web.base.Vpw;

public class BaseLogic {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseLogic.class);
	private SqlSessionTemplate sqlSessionTemplate;

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	protected String current_time(HttpServletRequest request) throws Exception {
		logger.info("BaseLogic - current_time");
		Map<String, Object> pMap = HashMapBinder.getParameterMap(request);
		return (String) MybatisDao.selectObject(sqlSessionTemplate, "current_time", pMap);
	}

	protected String txCurrent_time(HttpServletRequest request) throws Exception {
		logger.info("BaseLogic - current_time");
		Map<String, Object> pMap = HashMapBinder.getParameterMap(request);
		int test1 = 0;
		int test2 = 0;
		test1 = MybatisDao.insert(sqlSessionTemplate, "test1", pMap);
		System.out.println("test1: " + test1);
		test2 = MybatisDao.insert(sqlSessionTemplate, "test2", pMap);
		System.out.println("test2: " + test2);
		return (String) MybatisDao.selectObject(sqlSessionTemplate, "current_time", pMap);
	}

	protected String file_upload(HttpServletRequest request) throws Exception {
		logger.info("BaseLogic - file_upload");
		try {
			Map<String, Object> pMap = HashMapBinder.getMultipartMap(request, "resources/uploaded_files");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Vpw.redirect("base/file_upload");
	}
}
