package project.web.base;

import java.io.File;
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
	private static final String rootPath= "C:\\workspace\\baseSpring\\src\\main\\webapp";

	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		logger.info("getParameterMap() ");
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
		StringBuilder fullPath = new StringBuilder(rootPath);
		while (i.hasNext()) {
			fullPath.append("\\");
			fullPath.append(i.next());
		}
		Map<String, Object> pMap = new HashMap<>();
		logger.info("request.getContentType() : " + request.getContentType());

		logger.info("getMultipartMap() ");
		MultipartRequest multi = new MultipartRequest(request, fullPath.toString(), 10 * 1024 * 1024, "UTF-8",
				new DefaultFileRenamePolicy());
		Enumeration<String> names = multi.getParameterNames();
		logger.info(rootPath + path);
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			logger.info(name + ": " + multi.getParameter(name));
			pMap.put(name, multi.getParameter(name));
		}

		try {
			Enumeration<String> files = multi.getFileNames();
			File file;
			if (files != null) {
				while (files.hasMoreElements()) {
					//					logger.info("files.hasMoreElements()");
					String fname = files.nextElement();
					logger.info("fname:" + fname);
					String filePathName = path + "/" + multi.getFilesystemName(fname);
					if (filePathName != null && filePathName.length() > 1) {
						file = new File(filePathName);
						pMap.put(fname, filePathName);
						double size = file.length() / 1024;//kb
						pMap.put("file_size", String.valueOf(size));
						logger.info("file: " + file);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pMap;
	}
}
