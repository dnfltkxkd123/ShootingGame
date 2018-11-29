package 게임수정_test;
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
	

	Image img = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임수정\\missile-hi-iloveimg-resizedR.png").getImage();
	Image me = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임수정\\디바60x60.png").getImage();//플레이어가 조작할 기체의 이미지
	Image background = new ImageIcon("C:\\Users\\dnflt\\Desktop\\test\\Shotting\\src\\게임수정\\배경2.png").getImage();//배경 이미지
	Image screenImage;//
	Graphics screenGraphic;//
	GameThread thr;//게임의 스레드
	static ArrayList<Boom> boomList = new ArrayList<Boom>();//표적이 폭파 하는 이미지를 담을 ArrayList
	static ArrayList<Boom> exposuredList = new ArrayList<Boom>();//플레이어가 폭파 하는 이미지를 담을 ArrayList
	static int myScore=0;//초기점수
	static int myLife=10;//생명포인트
	private int exit = 0;

	
	GameFrame(){
		setTitle("2학년 1학기 개인 프로젝트");
		setSize(Main.width,Main.height);//gui의 사이즈
		setLocation(Main.f_x,Main.f_y);//gui의 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//이 클래스의 gui를 닫으면 모든 gui가 닫힘
		setResizable(false);//크기 조절 못하게 함
		thr = new GameThread();//게임의 스레드를 담당할 객체를 인스턴스함
		addKeyListener(Main.key);//키 이벤트를 gui에 등록함
		thr.start();//스레드를 실행시킴
		setVisible(true);//gui를 보이게함
		new Main();
	}
	
	
	public void screenDraw(Graphics g) {
		
		try {
			delete(g);//표적을 삭제시킴
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		g.drawImage(background,0,0,this);//배경을 그려줌
		limitPos();
		//g.clearRect(0, 0, Main.width, Main.height);// 0,0 에서 위에서 정한 해상도 크기만큼 화면을 지웁니다.
		drawCould(g);//구름을 그림
		g.drawImage(me,Main.x,Main.y,this);//내가 조종하는 기체를 그려줌
		MissileDraw(g);//미사일을 그려줌
		enemyDraw(g);//표적을 그려줌
		boomDraw(g);//표젹이 폭파하는 이미지를 그려줌
		exposured(g);//내가 폭파하는 이미지를 그려줌
		drawString((Graphics2D)g);//텍스트를 그려줌
		for(int i=0 ; i<Main.attackList.size() ; i++) {
			Main.attackList.get(i).missileDraw(g);//미사일을 그려줌
		}
	}//화면에 그려질 이벤트를 담당하는 메소드
	
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
	}//플레이어가 조작하는 기체가 x축 기준:0~760,y축 기준:20~540까지만 움직일수 있게함
	
	

	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.width,Main.height);//너비 800,높이 600의 빈이미지(백지)를 생성
		screenGraphic = screenImage.getGraphics();//screenImage를 Graphics으로 만듬
		screenGraphic.drawImage(background,0,0,this);//screenImage위에 배경을 그림
		screenDraw(screenGraphic);//screenImage(빈여백)을 얻은 screenGraphic를 screenDraw의 매개변수로 넣음
		g.drawImage(screenImage,0,0,null);//화면에 screenImage(빈여백)에 그려진 이미지를 화면에 출력
		repaint();//0.001초 마다 새로 설정된 이미지(screenImage)를 그림
	}
	
	public void MissileDraw(Graphics g) {
		for(int i=0 ; i<Main.MissileList.size() ; i++) {//Main.MissileList의 길이 만큼 for문 실행
			Main.ms = Main.MissileList.get(i);
			if(Main.ms.pos.x >800) {
				Main.MissileList.remove(i);//미사일의 x위치가800보다 크면 ArrayList에서 제거
			}else {
				Main.ms.missileDraw(g);//미사일의 x위치가800이하 이면 미사일을 그림
			}
		}
	}//플레이어가 조종하는 기체의 미사일 이벤트를 담당하는 메소드
	
	public void enemyDraw(Graphics g) {
		for(int i=0 ; i<Main.enemyList.size() ; i++) {//Main.enemyList 길이만큼 for문 실행
			Main.enemy = Main.enemyList.get(i);//Main.enemy 의 주소를 Main.enemyList.get(i)로 설정
			if(Main.enemy.getX()==-40) {//i번째 표적의 위치가 -40이면 리스트에서 삭제
				Main.enemyList.remove(i);
				myScore-=10;//표젹을 놓치면 점수가 10점 감소
				if(myScore<0){//점수가 0보다 작아지지 않게함
					myScore=0;
				}
			}else {//i번째 표적의 위치가 -40이 아니면 표적을 화면에 그림
				Main.enemy.enemyDraw(g);
			}
		}
	}
	
	
	public void delete(Graphics g) {
		
		for(int i=0 ; i<Main.MissileList.size() ; i++) {
			for(int j=0 ; j<Main.enemyList.size() ; j++) {
				int myMissileX = Main.MissileList.get(i).pos.x;//i번째 나의 미사일의 x축 위치 
				int myMissileY = Main.MissileList.get(i).pos.y+10;//i번째 나의 미사일의 y축 위치 
				int enemyX = Main.enemyList.get(j).getX();//j번째 표적의 x축 위치 
				int enemyY = Main.enemyList.get(j).getY();//j번째 표적의 y축 위치
				int delX = myMissileX-enemyX;//i번째 나의미사일의 x축위치 - j번째 표적의 x축 위치 
				int delY = myMissileY-enemyY;//i번째 나의 미사일의 y축 위치 - j번째 표적의 y축 위치
				if((delX>-50 && delX<90) && (delY>-10 && delY<50)) {//i번째 미사일과 j번째 미사일의 삭제 판정 설정
					Main.MissileList.remove(i);
					Main.enemyList.remove(j);
					boomList.add(new Boom(enemyX,enemyY));//표적을 맞추면 표적의x,y위치를 받고 폭파이미지를 그릴 객체 인스턴스 하고 boomList에 추가
					myScore +=10;//표적을 맞추면 점수가 10점 증가
				}

				
			}
		}
		
		for(int i=0 ; i <Main.attackList.size() ; i++) {
			EnemyAttack enemyAttack = Main.attackList.get(i);
			int del = enemyAttack.getX();
			if(del==-40) {
				Main.attackList.remove(i);
			}
		}//표적이 발사하는 미사일의 x축 위치가 -40이면 리스트에서 삭제
		
	}
	
	public void boomDraw(Graphics g) {
		for(int i=0 ; i<boomList.size() ; i++) {//boomList의 길이 만큼 for문 실행
			Boom boom = boomList.get(i);//boom의 주소를 i번째 boomList로 설정
			if(boom.getTime() > 450) {
				boomList.remove(i);//표적이 폭파되고 나서 0.24초 지나면 폭파하는 이미지를 그려주는 객체를 boomList에서 삭제
			}else {
				boom.boom(g);//표적이 폭파되고 나서 0.24초이하면 폭파하는 이미지를 그려준다
			}
		}
	}//표적이 폭파하는 이미지를 그리는 메소드
	
	public void exposured(Graphics g) {
		for(int i=0 ; i<Main.attackList.size() ; i++) {
			int enemyAttackX = Main.attackList.get(i).getX();//표적이 발사하는 미사일의 x축의 위치
			int enemyAttackY = Main.attackList.get(i).getY();//표적이 발사하는 미사일의 y축의 위치
			int exposureX = enemyAttackX - Main.x;//표적이 발사하는 미사일의 x축의 위치 - 나의x축 위치
			int exposureY = enemyAttackY - Main.y;//표적이 발사하는 미사일의 y축의 위치 - 나의y축 위치
			if((exposureX>-50 &&exposureX<50) && (exposureY>-5 &&exposureY<45)) {//표적의 미사일과 내가(내가 조종하는 기체) 부딪히면 판정 설정
				Boom boom = new Boom();//exposuredList에 등록할 boom을 인스턴스함
				//boom.start();
				exposuredList.add(boom);//boom을 exposuredList에 추가함
				Main.attackList.remove(i);//표적의 미사일과 내가(내가 조종하는 기체) 부딪히면 그 미사일을 리스트에서 제거
				myLife--;//미사일에 맞으면 라이프 감소
				if(myLife==0) {
					thr.close();//라이프가 0이되면 스레드를 종료시킴
					Main.time=0;//스레드의 실행시간을 0으로 초기화
					//this.dispose();
					new Stop();//게임이 끝나면(점수가0이되면) 점수창 출력
				}
			}
		}
		
		for(int i=0 ; i<Main.enemyList.size() ; i++) {
			int enemyX = Main.enemyList.get(i).getX();//표적의 x축의 위치
			int enemyY = Main.enemyList.get(i).getY()+10;//표적의 y축의 위치
			int exposureX = enemyX - Main.x;//표적과 내가(내가 조종하는 기체) 부딪힌것을 알려주는 x축의 판정
			int exposureY = enemyY - Main.y;//표적과 내가(내가 조종하는 기체) 부딪힌것을 알려주는 y축의 판정
			if((exposureX>-50 &&exposureX<50) && (exposureY>0 &&exposureY<60)) {//폭파판정
				Boom boom = new Boom();//exposuredList에 등록할 boom을 인스턴스함
				//boom.start();
				exposuredList.add(boom);//boom을 exposuredList에 추가함
				Main.enemyList.remove(i);//표적의 미사일과 내가(내가 조종하는 기체) 부딪히면 그 표적을 리스트에서 제거
				boomList.add(new Boom(enemyX,enemyY));//표적을 맞추면 표적의x,y위치를 받고 폭파이미지를 그릴 객체 인스턴스 하고 boomList에 추가
				myLife--;//라이프 감소
				if(myLife==0) {
					thr.close();//라이프가 0이되면 스레드를 종료시킴
					Main.time=0;//스레드의 실행시간을 0으로 초기화
					//this.dispose();
					new Stop();//게임이 끝나면(점수가0이되면) 점수창 출력
				}
			}
			
		}
		
		for(int i=0 ; i<exposuredList.size() ; i++) {//exposuredList의 길이 만큼 for문 실행
			Boom boom = exposuredList.get(i);//boom의 주소를 i번째 exposuredList로 설정
			if(boom.getTime() > 200) {
				exposuredList.remove(i);//표적이 폭파되고 나서 0.10초 지나면 폭파하는 이미지를 그려주는 객체를 exposuredList에서 삭제
			}else {
				boom.exposured(g);//표적이 폭파되고 나서 0.10초이하면 폭파하는 이미지를 그려준다
			}
		}
	}
	
	public void drawString(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//글씨가 안깨지게 해준다
		g.setFont(new Font("Arial",Font.BOLD,24));//글씨체와 글씨의 굵기 크기 설정
		g.drawString("MyScore : ", 570 , 70);//MyScore를 x=570,y=70의 위치에 그려줌
		g.drawString(Integer.toString(myScore), 700, 70);//나의 점수를 x=700,y=70의 위치에 그려줌
		g.drawString("MyLife : ", 20 , 70);//MyLife를 x=20,y=70의 위치에 그려줌
		g.drawString(Integer.toString(myLife), 110, 70);//나의 라이프를 x=100,y=70의 위치에 그려줌
	}
/*
	public int getMyScore() {
		return myScore;
	}
	*/
	
	public void drawCould(Graphics g) {
		for(int i=0 ; i<Main.couldList.size() ; i++) {//Main.couldList의 길이만큼 for문 실행
			int x = Main.couldList.get(i).getX();//i번째 구름의 x축의 위치
			if(x<-170) {
				Main.couldList.remove(i);//i번째 구름의 x축의 위치가 -170보다 작으면 리스트에서 삭제
			}
			else {
				Main.couldList.get(i).drawCould(g);//i번째 구름의 x축의 위치가 -170보다 크면 구름 이미지를 그려줌
			}
		}
	}
	
   public void reFresh() {
	    Main.key.keyStop();//게임이 다시 실행되면 키관련 이벤트를 담당하면 변수들을 false로 설정
		//setTitle("2학년 1학기 개인 프로젝트");
		//setSize(Main.width,Main.height);
		//setLocation(Main.f_x,Main.f_y);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
	  //addKeyListener(Main.key);
		thr = new GameThread();//새로운 스레드 객체를 인스턴스함
		thr.start();//인스턴스한 스레드객체를 실행시킴
		setVisible(true);
		myLife=10;//라이프를 초기값(10)으로 설정
		myScore=0;//점수를 초기값(0)으로 설정
		Main.x = 100;//나의 x축 위치를 100으로 초기화
		Main.y = 100;//나의 y축 위치를 100으로 초기화
		new Main();//ArrayList를 등록한 모든 객체를 삭제시킴
   }
	

}
