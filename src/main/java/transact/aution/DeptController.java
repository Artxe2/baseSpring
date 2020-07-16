package transact.aution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	@Autowired(required=false)
	private DeptLogic deptLogic = null;
	public String cudDept() {
		deptLogic.cudDept();
		return "redirect:deptAccount.jsp";
	}
}
