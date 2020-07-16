package mvc.board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/*
 * 패키지가 등록되어 있지 않으면 url을 찾을 수 없다. - 404
 * 별도로 ModelAndView를 사용하지 않고도 String타입으로  WEB-INF아래 있는 jsp페이지를 호출할 수 있다.
 * 
 */
@Controller
@RequestMapping("/board/*")//192.168.0.244:5000/ver/board/*
public class BoardController {
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	private BoardLogic boardLogic = null;
	public void setBoardLogic(BoardLogic boardLogic) {
		this.boardLogic = boardLogic;
	}
	@PostMapping("fileAdd")
	public String fileAdd(@RequestParam Map<String,Object> pMap, @RequestParam(value="bs_file", required=false) MultipartFile bs_file) {
		logger.info("boardList 호출 성공");
		logger.info("사용자가 선택한 파일 : "+bs_file.getOriginalFilename());
		String savePath = "D:\\workspace_sts3\\springVer2\\src\\main\\webapp\\pds";
		String filename = null;
		String fullPath = null;
		if(bs_file !=null) {
			filename = bs_file.getOriginalFilename();
			fullPath = savePath+"\\"+filename;
		}
		//첨부 파일이 존재하니?
		if(bs_file !=null && !bs_file.isEmpty()) {
			try {
				File file = new File(fullPath);
				byte[] bytes = bs_file.getBytes();
				BufferedOutputStream bos = 
						new BufferedOutputStream(
								new FileOutputStream(file));
				bos.write(bytes);
				bos.close();
				//파일 크기
				long size = file.length();
				double d_size = Math.floor(size/1024.0);
				pMap.put("bs_file", filename);
				pMap.put("bs_size", d_size);
				Object keys[] = pMap.keySet().toArray();
				for(int i=0;i<keys.length;i++) {
					logger.info(pMap.get(keys[i]).toString());
				}
			}catch(Exception e) {
				
			}
		}
		return "forward:list.jsp";
	}
	@GetMapping("boardList")
	public String boardList(Model model, @RequestParam Map<String,Object> pMap) {
		logger.info("boardList 호출 성공");
		logger.info("사용자가 선택한 제목 : "+pMap.get("bm_title"));
		List<Map<String,Object>> list = null;
		Map<String,Object> rmap = new HashMap<>();
		rmap.put("b_title","프로젝트에 관해서....");
		//list.add(rmap);
		list = boardLogic.boardList();
		model.addAttribute("list", list);
		return "forward:list.jsp";
	}
	@GetMapping("jsonBoardList")
	public String jsonBoardList(Model model) {
		logger.info("jsonBoardList 호출 성공");
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> rmap = new HashMap<>();
		rmap.put("b_title","프로젝트에 관해서....");
		list.add(rmap);
		model.addAttribute("list", list);
		//WEB-INF/jsp/board/jsonBoardList.jsp
		return "board/jsonBoardList";
	}
}
