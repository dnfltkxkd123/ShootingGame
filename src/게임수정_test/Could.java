package ���Ӽ���_test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Could {
	private int x;//������ x���� ��ġ
	private int y;//������ y���� ��ġ
	private Image img= new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\bin\\���Ӽ���\\����2.png").getImage();//���� �̹���
	private int move = (int)(Math.random()*2);//������ �̵��ӵ��� 0~2������ ���̸� ��
	
	public Could(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void drawCould(Graphics g) {
		g.drawImage(img, x, y,null);//������ �̹����� �׷���
	}
	
	public void move() {
		this.x -=4+move;//0.02�� ���� 4+(0~2)�����̰� ��
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
