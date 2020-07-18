package spring.mvc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class BaseController extends MultiActionController {
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);
	private BaseLogic baseLogic;

	public void setBaseLogic(BaseLogic baseLogic) {
		this.baseLogic = baseLogic;
	}

	@GetMapping("current_time")
	@ResponseBody
	public Object current_time(@RequestParam Map<String, Object> pMap) {
		logger.info("BaseController - current_time");
		return baseLogic.current_time(pMap);
	}

	@PostMapping("fileAdd")
	public String fileAdd(@RequestParam Map<String, Object> pMap,
			@RequestParam(value = "bs_file", required = false) MultipartFile bs_file) {
		logger.info("boardList 호출 성공");
		logger.info("사용자가 선택한 파일 : " + bs_file.getOriginalFilename());
//		String savePath = "C:\\workspace_spring\\baseSpring\\src\\main\\webapp\\pds";
		String savePath = "C:\\workspace_sts3\\baseSpring\\src\\main\\webapp\\pds";
		String filename = null;
		String fullPath = null;
		if (bs_file != null) {
			filename = bs_file.getOriginalFilename();
			fullPath = savePath + "\\" + filename;
		}
		// 첨부 파일이 존재하니?
		if (bs_file != null && !bs_file.isEmpty()) {
			try {
				File file = new File(fullPath);
				byte[] bytes = bs_file.getBytes();
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(bytes);
				bos.close();
				// 파일 크기
				long size = file.length();
				double d_size = Math.floor(size / 1024.0);
				pMap.put("bs_file", filename);
				pMap.put("bs_size", d_size);
				Object keys[] = pMap.keySet().toArray();
				for (int i = 0; i < keys.length; i++) {
					logger.info(pMap.get(keys[i]).toString());
				}
			} catch (Exception e) {

			}
		}
		return "forward:list.jsp";
	}
}
