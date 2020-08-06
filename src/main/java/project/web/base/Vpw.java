package project.web.base;

public class Vpw {
	// ViewPathWrapper
	private static final com.google.gson.Gson gson = new com.google.gson.Gson();

	public static String forward(String path) {
		return "forward:/" + path + ".jsp";
	}

	public static String redirect(String path) {
		return "redirect:/" + path + ".jsp";
	}

	public static String web_inf(String path) {
		return "forward:/WEB-INF/jsp/" + path + ".jsp";
	}

	public static void printString(javax.servlet.http.HttpServletResponse response, String data) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printJson(javax.servlet.http.HttpServletResponse response, Object jsonElement) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(gson.toJson(jsonElement));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printJsonIncludeNull(javax.servlet.http.HttpServletResponse response, Object jsonElement) {
		java.util.Iterator<String> i = java.util.Arrays.asList(gson.toJson(jsonElement).split("\"null\"")).iterator();
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
