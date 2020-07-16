package transact.aution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/erp/*")
public class EmpController {
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	@Autowired(required=false)
	private EmpLogic empLogic = null;
	@GetMapping("cudEmp.do")
	public String cudEmp() {
		logger.info("cudEmp 호출 성공");
		int result = 0;
		result = empLogic.eudEmp();
		return "redirect:empAccount.jsp";
	}
}
