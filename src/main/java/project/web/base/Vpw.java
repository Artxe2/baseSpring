package project.web.base;

import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Vpw {
	// ViewPathWrapper
	private static final Gson gson = new Gson();

	public static String forward(String path) {
		return "forward:/" + path + ".jsp";
	}

	public static String redirect(String path) {
		return "redirect:/" + path + ".jsp";
	}

	public static String web_inf(String path) {
		return "forward:/WEB-INF/jsp/" + path + ".jsp";
	}

	public static void printString(HttpServletResponse response, String data) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printJson(HttpServletResponse response, Object jsonElement) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(gson.toJson(jsonElement));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printJsonIncludeNull(HttpServletResponse response, Object jsonElement) {
		Iterator<String> i = Arrays.asList(gson.toJson(jsonElement).split("\"null\"")).iterator();
		StringBuilder json = new StringBuilder(i.next());
		while (i.hasNext()) {
			json.append("null");
			json.append(i.next());
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
