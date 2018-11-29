package ���Ӽ���_test;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.paint.Color;

public class GameFrame extends JFrame{
	

	Image img = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\���Ӽ���\\missile-hi-iloveimg-resizedR.png").getImage();
	Image me = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\���Ӽ���\\���60x60.png").getImage();//�÷��̾ ������ ��ü�� �̹���
	Image background = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\���Ӽ���\\���2.png").getImage();//��� �̹���
	Image screenImage;//
	Graphics screenGraphic;//
	GameThread thr;//������ ������
	static ArrayList<Boom> boomList = new ArrayList<Boom>();//ǥ���� ���� �ϴ� �̹����� ���� ArrayList
	static ArrayList<Boom> exposuredList = new ArrayList<Boom>();//�÷��̾ ���� �ϴ� �̹����� ���� ArrayList
	static int myScore=0;//�ʱ�����
	static int myLife=10;//��������Ʈ
	private int exit = 0;

	
	GameFrame(){
		setTitle("2�г� 1�б� ���� ������Ʈ");
		setSize(Main.width,Main.height);//gui�� ������
		setLocation(Main.f_x,Main.f_y);//gui�� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�� Ŭ������ gui�� ������ ��� gui�� ����
		setResizable(false);//ũ�� ���� ���ϰ� ��
		thr = new GameThread();//������ �����带 ����� ��ü�� �ν��Ͻ���
		addKeyListener(Main.key);//Ű �̺�Ʈ�� gui�� �����
		thr.start();//�����带 �����Ŵ
		setVisible(true);//gui�� ���̰���
		new Main();
	}
	
	
	public void screenDraw(Graphics g) {
		
		try {
			delete(g);//ǥ���� ������Ŵ
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		g.drawImage(background,0,0,this);//����� �׷���
		limitPos();
		//g.clearRect(0, 0, Main.width, Main.height);// 0,0 ���� ������ ���� �ػ� ũ�⸸ŭ ȭ���� ����ϴ�.
		drawCould(g);//������ �׸�
		g.drawImage(me,Main.x,Main.y,this);//���� �����ϴ� ��ü�� �׷���
		MissileDraw(g);//�̻����� �׷���
		enemyDraw(g);//ǥ���� �׷���
		boomDraw(g);//ǥ���� �����ϴ� �̹����� �׷���
		exposured(g);//���� �����ϴ� �̹����� �׷���
		drawString((Graphics2D)g);//�ؽ�Ʈ�� �׷���
		for(int i=0 ; i<Main.attackList.size() ; i++) {
			Main.attackList.get(i).missileDraw(g);//�̻����� �׷���
		}
	}//ȭ�鿡 �׷��� �̺�Ʈ�� ����ϴ� �޼ҵ�
	
	public void limitPos() {
		if(Main.x<0) {
			Main.x=0;
		}
		else if(Main.x>760) {
			Main.x=760;
		}
		
		if(Main.y<20) {
			Main.y=20;
		}
		else if(Main.y>540) {
			Main.y=540;
		}
	}//�÷��̾ �����ϴ� ��ü�� x�� ����:0~760,y�� ����:20~540������ �����ϼ� �ְ���
	
	

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.width,Main.height);//�ʺ� 800,���� 600�� ���̹���(����)�� ����
		screenGraphic = screenImage.getGraphics();//screenImage�� Graphics���� ����
		screenGraphic.drawImage(background,0,0,this);//screenImage���� ����� �׸�
		screenDraw(screenGraphic);//screenImage(�󿩹�)�� ���� screenGraphic�� screenDraw�� �Ű������� ����
		g.drawImage(screenImage,0,0,null);//ȭ�鿡 screenImage(�󿩹�)�� �׷��� �̹����� ȭ�鿡 ���
		repaint();//0.001�� ���� ���� ������ �̹���(screenImage)�� �׸�
	}
	
	public void MissileDraw(Graphics g) {
		for(int i=0 ; i<Main.MissileList.size() ; i++) {//Main.MissileList�� ���� ��ŭ for�� ����
			Main.ms = Main.MissileList.get(i);
			if(Main.ms.pos.x >800) {
				Main.MissileList.remove(i);//�̻����� x��ġ��800���� ũ�� ArrayList���� ����
			}else {
				Main.ms.missileDraw(g);//�̻����� x��ġ��800���� �̸� �̻����� �׸�
			}
		}
	}//�÷��̾ �����ϴ� ��ü�� �̻��� �̺�Ʈ�� ����ϴ� �޼ҵ�
	
	public void enemyDraw(Graphics g) {
		for(int i=0 ; i<Main.enemyList.size() ; i++) {//Main.enemyList ���̸�ŭ for�� ����
			Main.enemy = Main.enemyList.get(i);//Main.enemy �� �ּҸ� Main.enemyList.get(i)�� ����
			if(Main.enemy.getX()==-40) {//i��° ǥ���� ��ġ�� -40�̸� ����Ʈ���� ����
				Main.enemyList.remove(i);
				myScore-=10;//ǥ���� ��ġ�� ������ 10�� ����
				if(myScore<0){//������ 0���� �۾����� �ʰ���
					myScore=0;
				}
			}else {//i��° ǥ���� ��ġ�� -40�� �ƴϸ� ǥ���� ȭ�鿡 �׸�
				Main.enemy.enemyDraw(g);
			}
		}
	}
	
	
	public void delete(Graphics g) {
		
		for(int i=0 ; i<Main.MissileList.size() ; i++) {
			for(int j=0 ; j<Main.enemyList.size() ; j++) {
				int myMissileX = Main.MissileList.get(i).pos.x;//i��° ���� �̻����� x�� ��ġ 
				int myMissileY = Main.MissileList.get(i).pos.y+10;//i��° ���� �̻����� y�� ��ġ 
				int enemyX = Main.enemyList.get(j).getX();//j��° ǥ���� x�� ��ġ 
				int enemyY = Main.enemyList.get(j).getY();//j��° ǥ���� y�� ��ġ
				int delX = myMissileX-enemyX;//i��° ���ǹ̻����� x����ġ - j��° ǥ���� x�� ��ġ 
				int delY = myMissileY-enemyY;//i��° ���� �̻����� y�� ��ġ - j��° ǥ���� y�� ��ġ
				if((delX>-50 && delX<90) && (delY>-10 && delY<50)) {//i��° �̻��ϰ� j��° �̻����� ���� ���� ����
					Main.MissileList.remove(i);
					Main.enemyList.remove(j);
					boomList.add(new Boom(enemyX,enemyY));//ǥ���� ���߸� ǥ����x,y��ġ�� �ް� �����̹����� �׸� ��ü �ν��Ͻ� �ϰ� boomList�� �߰�
					myScore +=10;//ǥ���� ���߸� ������ 10�� ����
				}

				
			}
		}
		
		for(int i=0 ; i <Main.attackList.size() ; i++) {
			EnemyAttack enemyAttack = Main.attackList.get(i);
			int del = enemyAttack.getX();
			if(del==-40) {
				Main.attackList.remove(i);
			}
		}//ǥ���� �߻��ϴ� �̻����� x�� ��ġ�� -40�̸� ����Ʈ���� ����
		
	}
	
	public void boomDraw(Graphics g) {
		for(int i=0 ; i<boomList.size() ; i++) {//boomList�� ���� ��ŭ for�� ����
			Boom boom = boomList.get(i);//boom�� �ּҸ� i��° boomList�� ����
			if(boom.getTime() > 450) {
				boomList.remove(i);//ǥ���� ���ĵǰ� ���� 0.24�� ������ �����ϴ� �̹����� �׷��ִ� ��ü�� boomList���� ����
			}else {
				boom.boom(g);//ǥ���� ���ĵǰ� ���� 0.24�����ϸ� �����ϴ� �̹����� �׷��ش�
			}
		}
	}//ǥ���� �����ϴ� �̹����� �׸��� �޼ҵ�
	
	public void exposured(Graphics g) {
		for(int i=0 ; i<Main.attackList.size() ; i++) {
			int enemyAttackX = Main.attackList.get(i).getX();//ǥ���� �߻��ϴ� �̻����� x���� ��ġ
			int enemyAttackY = Main.attackList.get(i).getY();//ǥ���� �߻��ϴ� �̻����� y���� ��ġ
			int exposureX = enemyAttackX - Main.x;//ǥ���� �߻��ϴ� �̻����� x���� ��ġ - ����x�� ��ġ
			int exposureY = enemyAttackY - Main.y;//ǥ���� �߻��ϴ� �̻����� y���� ��ġ - ����y�� ��ġ
			if((exposureX>-50 &&exposureX<50) && (exposureY>-5 &&exposureY<45)) {//ǥ���� �̻��ϰ� ����(���� �����ϴ� ��ü) �ε����� ���� ����
				Boom boom = new Boom();//exposuredList�� ����� boom�� �ν��Ͻ���
				//boom.start();
				exposuredList.add(boom);//boom�� exposuredList�� �߰���
				Main.attackList.remove(i);//ǥ���� �̻��ϰ� ����(���� �����ϴ� ��ü) �ε����� �� �̻����� ����Ʈ���� ����
				myLife--;//�̻��Ͽ� ������ ������ ����
				if(myLife==0) {
					thr.close();//�������� 0�̵Ǹ� �����带 �����Ŵ
					Main.time=0;//�������� ����ð��� 0���� �ʱ�ȭ
					//this.dispose();
					new Stop();//������ ������(������0�̵Ǹ�) ����â ���
				}
			}
		}
		
		for(int i=0 ; i<Main.enemyList.size() ; i++) {
			int enemyX = Main.enemyList.get(i).getX();//ǥ���� x���� ��ġ
			int enemyY = Main.enemyList.get(i).getY()+10;//ǥ���� y���� ��ġ
			int exposureX = enemyX - Main.x;//ǥ���� ����(���� �����ϴ� ��ü) �ε������� �˷��ִ� x���� ����
			int exposureY = enemyY - Main.y;//ǥ���� ����(���� �����ϴ� ��ü) �ε������� �˷��ִ� y���� ����
			if((exposureX>-50 &&exposureX<50) && (exposureY>0 &&exposureY<60)) {//��������
				Boom boom = new Boom();//exposuredList�� ����� boom�� �ν��Ͻ���
				//boom.start();
				exposuredList.add(boom);//boom�� exposuredList�� �߰���
				Main.enemyList.remove(i);//ǥ���� �̻��ϰ� ����(���� �����ϴ� ��ü) �ε����� �� ǥ���� ����Ʈ���� ����
				boomList.add(new Boom(enemyX,enemyY));//ǥ���� ���߸� ǥ����x,y��ġ�� �ް� �����̹����� �׸� ��ü �ν��Ͻ� �ϰ� boomList�� �߰�
				myLife--;//������ ����
				if(myLife==0) {
					thr.close();//�������� 0�̵Ǹ� �����带 �����Ŵ
					Main.time=0;//�������� ����ð��� 0���� �ʱ�ȭ
					//this.dispose();
					new Stop();//������ ������(������0�̵Ǹ�) ����â ���
				}
			}
			
		}
		
		for(int i=0 ; i<exposuredList.size() ; i++) {//exposuredList�� ���� ��ŭ for�� ����
			Boom boom = exposuredList.get(i);//boom�� �ּҸ� i��° exposuredList�� ����
			if(boom.getTime() > 200) {
				exposuredList.remove(i);//ǥ���� ���ĵǰ� ���� 0.10�� ������ �����ϴ� �̹����� �׷��ִ� ��ü�� exposuredList���� ����
			}else {
				boom.exposured(g);//ǥ���� ���ĵǰ� ���� 0.10�����ϸ� �����ϴ� �̹����� �׷��ش�
			}
		}
	}
	
	public void drawString(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//�۾��� �ȱ����� ���ش�
		g.setFont(new Font("Arial",Font.BOLD,24));//�۾�ü�� �۾��� ���� ũ�� ����
		g.drawString("MyScore : ", 570 , 70);//MyScore�� x=570,y=70�� ��ġ�� �׷���
		g.drawString(Integer.toString(myScore), 700, 70);//���� ������ x=700,y=70�� ��ġ�� �׷���
		g.drawString("MyLife : ", 20 , 70);//MyLife�� x=20,y=70�� ��ġ�� �׷���
		g.drawString(Integer.toString(myLife), 110, 70);//���� �������� x=100,y=70�� ��ġ�� �׷���
	}
/*
	public int getMyScore() {
		return myScore;
	}
	*/
	
	public void drawCould(Graphics g) {
		for(int i=0 ; i<Main.couldList.size() ; i++) {//Main.couldList�� ���̸�ŭ for�� ����
			int x = Main.couldList.get(i).getX();//i��° ������ x���� ��ġ
			if(x<-170) {
				Main.couldList.remove(i);//i��° ������ x���� ��ġ�� -170���� ������ ����Ʈ���� ����
			}
			else {
				Main.couldList.get(i).drawCould(g);//i��° ������ x���� ��ġ�� -170���� ũ�� ���� �̹����� �׷���
			}
		}
	}
	
   public void reFresh() {
	    Main.key.keyStop();//������ �ٽ� ����Ǹ� Ű���� �̺�Ʈ�� ����ϸ� �������� false�� ����
		//setTitle("2�г� 1�б� ���� ������Ʈ");
		//setSize(Main.width,Main.height);
		//setLocation(Main.f_x,Main.f_y);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
	  //addKeyListener(Main.key);
		thr = new GameThread();//���ο� ������ ��ü�� �ν��Ͻ���
		thr.start();//�ν��Ͻ��� �����尴ü�� �����Ŵ
		setVisible(true);
		myLife=10;//�������� �ʱⰪ(10)���� ����
		myScore=0;//������ �ʱⰪ(0)���� ����
		Main.x = 100;//���� x�� ��ġ�� 100���� �ʱ�ȭ
		Main.y = 100;//���� y�� ��ġ�� 100���� �ʱ�ȭ
		new Main();//ArrayList�� ����� ��� ��ü�� ������Ŵ
   }
	

}
