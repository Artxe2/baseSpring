package transact.aution;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogic {
	Logger logger = LoggerFactory.getLogger(EmpLogic.class);
	@Autowired(required=false)
	private EmpDao empDao = null;
	@Autowired(required=false)
	private DeptDao deptDao = null;
	//@Transactional(propagation=Propagation.REQUIRED, rollbackFor= {DataAccessException.class})
	//@Pointcut(value="execution(* transact.aution.*Logic.*(..)")
	public int eudEmp() {
//	public int cudEmp() {
		int result = 0;
		int r1=0;
		int r2=0;
		try {
			Map<String,Object> pMap = new HashMap<>();
			pMap.put("empno", 8000);
			pMap.put("deptno", 40);			
			r1 = empDao.empINS(pMap);
			Map<String,Object> pMap1 = new HashMap<>();
			pMap1.put("deptno", 410);
			pMap1.put("dname", "공통팀");				
			pMap1.put("loc", "가산동");				
			r2 = deptDao.deptINS(pMap1);
			
		}catch(DataAccessException e) {
			throw e;
		}
		return result;
	}

}
