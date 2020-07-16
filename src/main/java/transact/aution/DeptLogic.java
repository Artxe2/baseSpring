package transact.aution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DeptLogic {
	Logger logger = LoggerFactory.getLogger(DeptLogic.class);
	@Autowired(required=false)
	private EmpDao empDao = null;
	@Autowired(required=false)
	private DeptDao deptDao = null;
	public void cudDept() {
		logger.info("cudDept 호출 성공");
	}

}
