package ���Ӽ���_test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Missile{
	
	
	Point pos;//�̻��� ��ǥ ����
	private int move;//������ ��ġ�� �̻����� ������ ���� ����
	Image img = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\���Ӽ���\\missile-hi-iloveimg-resizedR.png").getImage();//�̻����� �̹���
	
	Missile(int x, int y,int move){
		pos = new Point(x,y+30);//�̻����� ó����ġ ����
		this.move = move;
	}
	
	public void move() {
		/*
		if(move==0) {
			pos.x += 15;
			pos.y -=2;
		}//move�� ���� 0�� Missile�� 0.02�ʴ� x������+15 y������-2��ŭ ���̵�
		else if(move==1) {
			pos.x += 15;
		}//move�� ���� 1�� Missile�� 0.02�ʴ� x������+15 y������ 0��ŭ �̵�
		else if(move==2) {
			pos.x += 15;
			pos.y +=2;
		}//move�� ���� 2�� Missile�� 0.02�ʴ� x������+15 y������ +2��ŭ �̵�
		*/
		pos.x += 15;
	}
	
	public void nomalMove() {
		pos.x += 15;
	}
	
	public void missileDraw(Graphics g) {
		g.drawImage(img, pos.x+10 , pos.y , null);
	}//�̻����� �׷��ִ� �޼ҵ�

	

	

}
