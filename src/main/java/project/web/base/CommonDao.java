package project.web.base;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate = null;

	public String currentTime() {
		String cdate = null;
		cdate = sqlSessionTemplate.selectOne("currentTime");
		return cdate;
	}
	
	
}
