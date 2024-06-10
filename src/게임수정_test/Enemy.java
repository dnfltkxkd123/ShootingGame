package 게임수정_test;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy{
	
	Image enemy = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임수정\\파라50x38.png").getImage();//표적의 이미지
	private int x;//표적의 x 위치
	private int y;//표적의 y 위치
	private int randomX = (int)(Math.random()*3+3);//표적객체의 이동속도를 3~6으로 설정
	private EnemyAttack attack;//Main객체의 attackList에 담을 EnemyAttack(표적의 미사일) 클래스를 인스턴스한 객체를 일시적으로 담을 변수
	
	Enemy(int x , int y){
		this.x =x;
		this.y =y;
	}
	
	public void enemyDraw(Graphics g) {
		g.drawImage(enemy, x ,y,null );//표적을 그림
	}
	
	public void Enemymissile() {
		if(Main.time%1000==0 || Main.time==100) {
				attack = new EnemyAttack(x-20,y);
				Main.attackList.add(attack);
		}
	}//게임이 시작되고 나서 0.1초 또는 1초마다 표적이 발사할 미사일 객체를  Main.attackList에 담을수 있게 해주는 메소드
	

	public void process() {
		this.x -=randomX;
	}//0.02초 마다 표적의 미사일이 3~6이동하게 함
	

	public void enemyEvent() {
		process();
		Enemymissile();
	}//표적의 이벤트를 담당하는 메소드를 담고 있는 메소드

	public int getX() {
		return x;
	}//표적의 x축의 위치 반환

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}//표적의 y축의 위치 반환

	public void setY(int y) {
		this.y = y;
	}
	
	

}
