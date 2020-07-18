package spring.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDao {
	Logger logger = LoggerFactory.getLogger(BaseDao.class);
	private SqlSessionTemplate sqlSessionTemplate = null;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public List<Map<String, Object>> boardList() {
		logger.info("boardList 호출 성공");
		List<Map<String, Object>> list = new ArrayList<>();//list.size()=0
		list = sqlSessionTemplate.selectList("boardList");
		logger.info(""+list.size());
		return list;
	}
}
