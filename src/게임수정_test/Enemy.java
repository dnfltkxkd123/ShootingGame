package ���Ӽ���_test;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy{
	
	Image enemy = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\���Ӽ���\\�Ķ�50x38.png").getImage();//ǥ���� �̹���
	private int x;//ǥ���� x ��ġ
	private int y;//ǥ���� y ��ġ
	private int randomX = (int)(Math.random()*3+3);//ǥ����ü�� �̵��ӵ��� 3~6���� ����
	private EnemyAttack attack;//Main��ü�� attackList�� ���� EnemyAttack(ǥ���� �̻���) Ŭ������ �ν��Ͻ��� ��ü�� �Ͻ������� ���� ����
	
	Enemy(int x , int y){
		this.x =x;
		this.y =y;
	}
	
	public void enemyDraw(Graphics g) {
		g.drawImage(enemy, x ,y,null );//ǥ���� �׸�
	}
	
	public void Enemymissile() {
		if(Main.time%1000==0 || Main.time==100) {
				attack = new EnemyAttack(x-20,y);
				Main.attackList.add(attack);
		}
	}//������ ���۵ǰ� ���� 0.1�� �Ǵ� 1�ʸ��� ǥ���� �߻��� �̻��� ��ü��  Main.attackList�� ������ �ְ� ���ִ� �޼ҵ�
	

	public void process() {
		this.x -=randomX;
	}//0.02�� ���� ǥ���� �̻����� 3~6�̵��ϰ� ��
	

	public void enemyEvent() {
		process();
		Enemymissile();
	}//ǥ���� �̺�Ʈ�� ����ϴ� �޼ҵ带 ��� �ִ� �޼ҵ�

	public int getX() {
		return x;
	}//ǥ���� x���� ��ġ ��ȯ

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}//ǥ���� y���� ��ġ ��ȯ

	public void setY(int y) {
		this.y = y;
	}
	
	

}
