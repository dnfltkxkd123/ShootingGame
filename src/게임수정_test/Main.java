package ���Ӽ���_test;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Main {
	static int width = 800;//����ȭ�� �ʺ�
	static int height = 600;//����ȭ�� ����
	static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	//�������� �����쿡 ǥ�õɶ� ��ġ�� �����ϱ� ����
	//���� ������� �ػ� ���� �޾ƿɴϴ�.
	static int f_x = (int)(screen.getWidth()/2 - width/2);
	static int f_y = (int)(screen.getHeight()/2 - height/2);
	static int x=100;//�÷��̾ ������ ��ü�� �ʱ� ��ġ
	static int y=100;//�÷��̾ ������ ��ü�� �ʱ� ��ġ
	
	static ArrayList<Missile> MissileList = new ArrayList();//�÷��̾ ������ ��ü�� �߻��� �̻����� ���� ArrayList 
	static Missile ms;
	
	static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();//ǥ������ ���� ArrayList 
	static Enemy enemy;
	
	static ArrayList<EnemyAttack> attackList  = new ArrayList<EnemyAttack>();//ǥ���� �߻��� �̻����� ���� ArrayList 
	static EnemyAttack attack;
	
	static ArrayList<Could> couldList = new ArrayList<Could>();//������ ������ ���� ArrayList
	static GameFrame gameframe;//static���� ����� GameFrame
	
	static GameKey key = new GameKey();//Ű �̺�Ʈ�� ������ ��� �ִ� �ɹ�����
	static int time=0;//GameThread Ŭ������ �������� ���� �ð��� ����� ����
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
	}//������ �ٽ� �����Ҷ� �����ڸ� ����ؼ�, static ������ �ȿ� �ִ� �����͸� ���� ������ �ʱ�ȭ ��
	
	public static void main(String[] args) {
		//new GameFrame();
		gameframe = new GameFrame();
	}

}
