package transact.aution;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
@Service
public class DeptDao {
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate = null;
	public int deptINS(Map<String, Object> pMap) throws DataAccessException{
		int result = 0;
		result = sqlSessionTemplate.insert("deptINS", pMap);
		return result;
	}

}
