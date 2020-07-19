package org.apache.mybatis;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

public final class Dao {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Dao.class);

	public int insert(SqlSession sqlSessionTemplate, String sql, Map<String, Object> pMap) throws DataAccessException {
		logger.info("insert - " + sql);
		return sqlSessionTemplate.insert(sql, pMap);
	}
}
