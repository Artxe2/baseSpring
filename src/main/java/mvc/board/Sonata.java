package mvc.board;

public class Sonata {
	String carColor = "black";
	int speed = 0;
	int wheelNum = 0;
	public Sonata() {
		
	}
	//herCar
	public Sonata(String carColor, int speed) {
		this.carColor = carColor;
		this.speed = speed;;
	}
	//himCar
	public Sonata(String carColor, int speed, int wheelNum) {
		this.carColor = carColor;
		this.speed = speed;
		this.wheelNum = wheelNum;
	}
	@Override
	public String toString() {
		return "그 사람의 자동차 색상은 "+carColor
			  +" 이고 현재 속도는 "+speed+" 이고 바퀴수는 "
			  +wheelNum+" 입니다.";
	}
}









