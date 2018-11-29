package 게임수정_test;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameThread extends Thread{
	
	
	private EnemyAttack attack;

	
	@Override
	public void run() {
		try {
			while(true) {
				if(Main.time%120==0) {
					Main.key.MissileProduct();
				}
				if(Main.time%1000==0 && Main.time != 0) {
					Main.playTime++;
				}
				Main.key.KeyProcess();
				productionEnemy();
				missileMove();
				enemyEvent();
				moveCould();
				productionCould();
				Thread.sleep(20);
				boomTime();
				Main.time+=20;
				if(Main.time == 1000000000) {Main.time=0;}
			}
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	public void missileMove() {
		for(int i=0 ; i<Main.MissileList.size() ; i++) { 
			//Main.MissileList.get(i).move();
			Main.MissileList.get(i).nomalMove();
		}
	}
	
	public void productionEnemy() {
		if(Main.time%3000 == 0 || Main.time==100) {
			for(int i=0 ; i<4 ; i++) {
				Main.enemy = new Enemy(800,40 + i*170);
				Main.enemyList.add(Main.enemy);
			}
		}
	}
	
	public void productionCould() {
		if(Main.time%2000 == 0 || Main.time==100) {
			for(int i=0 ; i<5 ; i++) {
				int random = (int)(Math.random()*200);
				Could could = new Could(800+random,50+i*100);
				Main.couldList.add(could);
			}
		}
	}
	
	public void moveCould() {
		for(int i=0 ; i<Main.couldList.size() ; i++) {
			Main.couldList.get(i).move();
		}
	}
	
	public void enemyEvent() {
		for(int i=0 ; i<Main.enemyList.size() ; i++) {
			Main.enemyList.get(i).enemyEvent();
		}
		for(int i=0 ; i<Main.attackList.size() ; i++) {
			Main.attackList.get(i).enemyAttackEvent();
		}
	}
	
	public void boomTime() {
		for(int i=0 ; i<GameFrame.boomList.size() ; i++) {	
			GameFrame.boomList.get(i).increaseTime();
		}
         for(int i=0 ; i<GameFrame.exposuredList.size() ; i++) {
        	 GameFrame.exposuredList.get(i).increaseTime();
		}
	}
	
	public void close() {
		this.interrupt();
	}
}
