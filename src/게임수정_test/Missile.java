package 게임수정_test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Missile{
	
	
	Point pos;//미사일 좌표 변수
	private int move;//상중하 위치의 미사일의 설정을 위한 변수
	Image img = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임수정\\missile-hi-iloveimg-resizedR.png").getImage();//미사일의 이미지
	
	Missile(int x, int y,int move){
		pos = new Point(x,y+30);//미사일의 처음위치 설정
		this.move = move;
	}
	
	public void move() {
		/*
		if(move==0) {
			pos.x += 15;
			pos.y -=2;
		}//move의 값이 0인 Missile은 0.02초당 x축으로+15 y축으로-2만큼 ㅇ이동
		else if(move==1) {
			pos.x += 15;
		}//move의 값이 1인 Missile은 0.02초당 x축으로+15 y축으로 0만큼 이동
		else if(move==2) {
			pos.x += 15;
			pos.y +=2;
		}//move의 값이 2인 Missile은 0.02초당 x축으로+15 y축으로 +2만큼 이동
		*/
		pos.x += 15;
	}
	
	public void nomalMove() {
		pos.x += 15;
	}
	
	public void missileDraw(Graphics g) {
		g.drawImage(img, pos.x+10 , pos.y , null);
	}//미사일을 그려주는 메소드

	

	

}
