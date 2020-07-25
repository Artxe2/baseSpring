package project.web.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class HashMapBinder {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HashMapBinder.class);
	private static final String ROOTPATH= "D:\\workspace_jsp\\basePojo\\WebContent\\resources\\uploaded_files";
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

	public static Map<String, Object> getMultipartMap(HttpServletRequest request, String path) throws ServletException, IOException{
		Iterator<String> i = Arrays.asList(path.split("/")).iterator();
		StringBuilder fullPath = new StringBuilder(ROOTPATH);
		while (i.hasNext()) {
			fullPath.append("\\");
			fullPath.append(i.next());
		}
		Map<String, Object> pMap = new HashMap<>();
		MultipartRequest multi = new MultipartRequest(request, fullPath.toString(), 50 * 1024 * 1024, "UTF-8",
				new DefaultFileRenamePolicy());
		Enumeration<String> names = multi.getParameterNames();
		String name;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			logger.info(name + ": " + multi.getParameter(name));
			pMap.put(name, multi.getParameter(name));
		}
		try {
			Enumeration<String> files = multi.getFileNames();
			java.io.File f;
			String filePath;
			if (files != null) {
				while (files.hasMoreElements()) {
					name = files.nextElement();
					filePath = path + "/" + multi.getFilesystemName(name);
					if (filePath != null && filePath.length() > 1) {
						f = new java.io.File(filePath);
						pMap.put(name, filePath);
						logger.info(name + ": " + filePath + " - " + f.length());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pMap;
	}
}