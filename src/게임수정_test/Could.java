package 게임수정_test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Could {
	private int x;//구름의 x축의 위치
	private int y;//구름의 y축의 위치
	private Image img= new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\bin\\게임수정\\구름2.png").getImage();//구름 이미지
	private int move = (int)(Math.random()*2);//구름의 이동속도를 0~2정도의 차이를 줌
	
	public Could(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void drawCould(Graphics g) {
		g.drawImage(img, x, y,null);//구름의 이미지를 그려줌
	}
	
	public void move() {
		this.x -=4+move;//0.02초 마다 4+(0~2)움직이게 함
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
