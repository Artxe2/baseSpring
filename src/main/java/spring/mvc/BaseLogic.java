package spring.mvc;

import java.util.Map;

import org.apache.ibatis.MybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLogic {
	Logger logger = LoggerFactory.getLogger(BaseLogic.class);
	private SqlSessionTemplate sqlSessionTemplate = null;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Object current_time(Map<String, Object> pMap) {
		logger.info("BaseLogic - current_time");
		return MybatisDao.selectObject(sqlSessionTemplate, "current_time", pMap);
	}
}
