package 게임수정_test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Boom{
	Graphics g;
	Image img1 = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임\\boom1.png").getImage();//첫번째 폭파 이미지
	Image img2 = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임\\boom2.png").getImage();//두번째 폭파 이미지
	Image img3 = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임\\boom3.png").getImage();//세번째 폭파 이미지
	int x;;//폭파 이미지의 x축의 위치
	int y;;//폭파 이미지의 y축의 위치
	int time=0;//폭파 이미지가 실행시간을 기록하면 변수
	Point point;
	
	Boom(int x , int y) {
		this.x = x;
		this.y = y;
	}//표적이 폭파이미지의 위치를 담아주는 생성자
	
	Boom(){
		
	}//내가 조종하는 기체의 폭파이미지를 GameFrame의 exposuredList에 담아줄 Boom객체를 인스턴스하는 생성자
	
	public void boom(Graphics g) {//GameFrame의 Paint(Graphics g)는 1초당 1000번 이미지를 출력하기 때문에 time은 1초당 +1000이된다
		
		if(time<150) {//실행시간이 0.15초까지 첫번째 이미지 출력
			g.drawImage(img1,x,y,null);
		}else if(time<300){//실행시간이 0.30초까지 두번째 이미지 출력
			g.drawImage(img2,x,y,null);
		}else if(time<450) {//실행시간이 0.45초까지 세번째 이미지 출력
			g.drawImage(img3,x,y,null);
		}
	}//표적의 폭파 이미지를 그려주는 메소드
	
	
	public void exposured(Graphics g) {
	
		if(time<200) {
			g.drawImage(img1,Main.x,Main.y,null);
			//System.out.println(time);
		}//0.2초 첫번째 이미지 출력
	}//플레이어의 기체가 폭파하는 이벤트를 그려주는 메소드
	
	
	public int getTime() {
		return time;
	}//Boom 클래스의 실행시간을 알려주는 메소드

	public void setTime(int time) {
		this.time = +time;
	}
	
	public void increaseTime() {
		time += 20;
	}


}
