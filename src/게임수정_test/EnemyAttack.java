package ���Ӽ���_test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyAttack{
	private int move = (int)(Math.random()*3);//ǥ���� �̻����� �ּ� 0~3 �� �ӵ��� �̵��ϰ� �ϴ� ����
	private int x;//ǥ���� �̻����� x ���� ��ġ
	private int y;//ǥ���� �̻����� y ���� ��ġ
	Image missile = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\����\\missile-hi-iloveimg-resized.png").getImage();//ǥ���� �̻����� �̹���
	
	EnemyAttack(int x,int y){
		this.x =x;
		this.y =y;
	}
	
	public void missileDraw(Graphics g) {
		g.drawImage(missile, x ,y+10,null );
	}//ǥ���� �̻����� �׷��ְ� ��
	
	public void move() {
			x -= move+6;
	}//ǥ���� �̻����� 6~9�� �ӵ��� �̵��ϰ� ��
	
	public int getX() {
		return x;
	}//ǥ���� x���� ��ġ�� ��ȯ

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}//ǥ���� y���� ��ġ�� ��ȯ

	public void setY(int y) {
		this.y = y;
	}


	public void enemyAttackEvent() {
			move();
	}//ǥ���� �̻��� �̺�Ʈ�� ���� ���ִ� �޼ҵ�

}
