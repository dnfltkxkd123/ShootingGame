package 게임수정_test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyAttack{
	private int move = (int)(Math.random()*3);//표적의 미사일이 최소 0~3 의 속도로 이동하게 하는 변수
	private int x;//표적의 미사일의 x 축의 위치
	private int y;//표적의 미사일의 y 축의 위치
	Image missile = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임\\missile-hi-iloveimg-resized.png").getImage();//표적의 미사일의 이미지
	
	EnemyAttack(int x,int y){
		this.x =x;
		this.y =y;
	}
	
	public void missileDraw(Graphics g) {
		g.drawImage(missile, x ,y+10,null );
	}//표적의 미사일을 그려주게 함
	
	public void move() {
			x -= move+6;
	}//표적의 미사일이 6~9의 속도로 이동하게 함
	
	public int getX() {
		return x;
	}//표적의 x축의 위치를 반환

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}//표적의 y축의 위치를 반환

	public void setY(int y) {
		this.y = y;
	}


	public void enemyAttackEvent() {
			move();
	}//표적의 미사일 이벤트를 실행 해주는 메소드

}
