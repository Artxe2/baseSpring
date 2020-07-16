package project.web.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/common/*",produces="text/plain;charset=UTF-8")
public class RestCommonController {
	@Autowired
	private CommonLogic commonLogic = null;	
	@GetMapping("restTest")
	public String restTest() {
		return "테스트 페이지 입니다.";
	}
	@GetMapping(value="currentTime")
	public String currentTime() {
		String cdate = commonLogic.currentTime();
		return cdate;
	}
}
