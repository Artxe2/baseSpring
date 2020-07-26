package project.web.base;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class HashMapBinder {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HashMapBinder.class);
	private static final String ROOTPATH= "D:\\workspace_jsp\\baseSpring\\src\\main\\webapp\\static\\uploaded_files";

	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> pMap = new HashMap<>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			logger.info(name + ": " + request.getParameter(name));
			pMap.put(name, request.getParameter(name));
		}
		return pMap;
	}

	public static Map<String, Object> getMultipartMap(HttpServletRequest request, String folder) throws ServletException, IOException{
		Map<String, Object> pMap = getParameterMap(request);
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("i_file");
		java.io.File saveFile;
		String filePath;
		folder += "/";
		int j = 0;
		for (MultipartFile f : files) {
			filePath = folder + f.getOriginalFilename();
			saveFile = new java.io.File(ROOTPATH, filePath);
			int i = 0;
			while (saveFile.exists()) {
				saveFile = new java.io.File(ROOTPATH, filePath.substring(0, filePath.lastIndexOf('.')) + ++i + '.' + filePath.substring(filePath.lastIndexOf('.') + 1));
			}
			if (i > 0) {
				filePath = filePath.substring(0, filePath.lastIndexOf('.')) + ++i + '.' + filePath.substring(filePath.lastIndexOf('.') + 1);
			}
			f.transferTo(saveFile);
			String name = f.getName() + j++;
			pMap.put(name, filePath);
			logger.info(name + ": " + filePath + " - " + f.getSize());
		}
		return pMap;
	}
}