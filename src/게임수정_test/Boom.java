package ���Ӽ���_test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Boom{
	Graphics g;
	Image img1 = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\����\\boom1.png").getImage();//ù��° ���� �̹���
	Image img2 = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\����\\boom2.png").getImage();//�ι�° ���� �̹���
	Image img3 = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\����\\boom3.png").getImage();//����° ���� �̹���
	int x;//���� �̹����� x���� ��ġ
	int y;//���� �̹����� y���� ��ġ
	int time=0;//���� �̹����� ����ð��� ����ϸ� ����
	Point point;
	
	Boom(int x , int y) {
		this.x = x;
		this.y = y;
	}//ǥ���� �����̹����� ��ġ�� ����ִ� ������
	
	Boom(){
		
	}//���� �����ϴ� ��ü�� �����̹����� GameFrame�� exposuredList�� ����� Boom��ü�� �ν��Ͻ��ϴ� ������
	
	public void boom(Graphics g) {//GameFrame�� Paint(Graphics g)�� 1�ʴ� 1000�� �̹����� ����ϱ� ������ time�� 1�ʴ� +1000�̵ȴ�
		
		if(time<150) {//����ð��� 0.15�ʱ��� ù��° �̹��� ���
			g.drawImage(img1,x,y,null);
		}else if(time<300){//����ð��� 0.30�ʱ��� �ι�° �̹��� ���
			g.drawImage(img2,x,y,null);
		}else if(time<450) {//����ð��� 0.45�ʱ��� ����° �̹��� ���
			g.drawImage(img3,x,y,null);
		}
	}//ǥ���� ���� �̹����� �׷��ִ� �޼ҵ�
	
	
	public void exposured(Graphics g) {
	
		if(time<200) {
			g.drawImage(img1,Main.x,Main.y,null);
			//System.out.println(time);
		}//0.2�� ù��° �̹��� ���
	}//�÷��̾��� ��ü�� �����ϴ� �̺�Ʈ�� �׷��ִ� �޼ҵ�
	
	
	public int getTime() {
		return time;
	}//Boom Ŭ������ ����ð��� �˷��ִ� �޼ҵ�

	public void setTime(int time) {
		this.time = +time;
	}
	
	public void increaseTime() {
		time += 20;
	}


}
