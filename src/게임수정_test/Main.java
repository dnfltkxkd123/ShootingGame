package 게임수정_test;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Main {
	static int width = 800;//게임화면 너비
	static int height = 600;//게임화면 높이
	static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	//프레임이 윈도우에 표시될때 위치를 세팅하기 위해
	//현재 모니터의 해상도 값을 받아옵니다.
	static int f_x = (int)(screen.getWidth()/2 - width/2);;
	static int f_y = (int)(screen.getHeight()/2 - height/2);
	static int x=100;//플레이어가 조작할 기체의 초기 위치
	static int y=100;//플레이어가 조작할 기체의 초기 위치
	
	static ArrayList<Missile> MissileList = new ArrayList();//플레이어가 조작할 기체가 발사할 미사일을 담을 ArrayList 
	static Missile ms;
	
	static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();//표적들을 담을 ArrayList 
	static Enemy enemy;
	
	static ArrayList<EnemyAttack> attackList  = new ArrayList<EnemyAttack>();//표적이 발사할 미사일을 담을 ArrayList 
	static EnemyAttack attack;
	
	static ArrayList<Could> couldList = new ArrayList<Could>();//구름의 정보를 담을 ArrayList
	static GameFrame gameframe;//static으로 사용할 GameFrame
	
	static GameKey key = new GameKey();//키 이벤트의 정보를 담고 있는 맴버변수
	static int time=0;//GameThread 클래스의 스레드의 실행 시간을 기록할 변수
	static int playTime=0;//
	
	Main(){
		for(int i=0 ; i<MissileList.size() ; i++) {
			MissileList.remove(i);
			i--;
		}
		for(int i=0 ; i<enemyList.size() ; i++) {
			enemyList.remove(i);
			i--;
		}
		for(int i=0 ; i<attackList.size() ; i++) {
			attackList.remove(i);
			i--;
		}
		for(int i=0 ; i<couldList.size() ; i++) {
			couldList.remove(i);
			i--;
		}
	}//게임을 다시 시작할때 생성자를 사용해서, static 변수들 안에 있는 데이터를 전부 지워서 초기화 함
	
	public static void main(String[] args) {
		//new GameFrame();
		gameframe = new GameFrame();
	}

}
