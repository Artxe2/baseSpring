package project.web.base;

public class StringArray {

	public static void main(String[] args) {
		String url = "ws:\\\\\\\\192.168.0.244:5000\\\\echo?userid=test;room=kosmo59";
		String real = url.split("\\?")[1];
		String useridALL = real.split("\\;")[0];//roomCreate
		String userid = useridALL.substring(7);
		String roomALL = real.split("\\;")[1];//kosmo59
		String room = roomALL.substring(5);
		System.out.println(useridALL+", "+roomALL);
		System.out.println(userid+", "+room);
	}

}
