package 게임수정_test;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameKey extends KeyAdapter{
	
	private boolean keyUp = false; 
	private boolean keyDown = false;
	private boolean keyLeft = false;
	private boolean keyRight = false;
	private boolean keySpace = false;
	
	
	@Override
	public void keyPressed(KeyEvent e) {//키를 눌렀을때 일어나는 이벤트를 담당하는 메소드
		switch(e.getKeyCode()) {//
		case KeyEvent.VK_UP:
			keyUp = true;//위로가기 버튼을 누르면 keyUp이 true가됨
			break;
		case KeyEvent.VK_DOWN:
			keyDown = true;//내려가기 버튼을 누르면 keyDown이 true가됨
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = true;//왼쪽 버튼을 누르면 keyLeft이 true가됨
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = true;//오른쪽 버튼을 누르면 keyRight이 true가됨
			break;
		case KeyEvent.VK_SPACE:
			keySpace = true;//스페이스 버튼을 누르면 keySpace이 true가됨
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {//버튼을 땠을때 일어나는 이벤트를 담당하는 메소드
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyUp = false;//위로가기 버튼을 때면 keyUp이 false가됨
			break;
		case KeyEvent.VK_DOWN://위로가기 버튼을 때면 keyDown이 false가됨
			keyDown = false;
			break;
		case KeyEvent.VK_LEFT://위로가기 버튼을 때면 keyLeft이 false가됨
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT://위로가기 버튼을 때면 keyRight이 false가됨
			keyRight = false;
			break;
		case KeyEvent.VK_SPACE://위로가기 버튼을 때면 keySpace이 false가됨
			keySpace = false;
			break;
		}
	}
	
	public void KeyProcess() {
		if(keyUp) {
			Main.y -=9;
		}
		if(keyDown) {
			Main.y +=9;
		}
		if(keyRight) {
			Main.x +=9;
		}
		if(keyLeft) {
			Main.x -=9;
		}
	}//키boolean값이 true이면 플레이어가 움직일수 있게 해주는 메소드
	
	public void keyStop() {
		this.keyUp = false;
		this.keyDown = false;
		this.keyLeft = false;
		this.keyRight = false;
		this.keySpace = false;
	}//게임을 다시 시작할때 플레이어가 마음대로 움직이지 않도록 모든 키의 boolean값을 false로 설정
	
	public void MissileProduct() {
		if(keySpace) {//스페이스를 누르면 플레이어가 발사하는 미사일을 생성
			for(int i=0 ; i<3 ; i++) {//일정시간 마다 3개의 미사일을 생성
				Main.ms = new Missile(Main.x,Main.y,i);//i가 0이면 위로,i가 1이면 중간,i가2면 아래로 움직이면 미사일 생성
				Main.MissileList.add(Main.ms);//미사일을 리스트에 등록
				break;
			}
		}
	}//플레이어가 발사 하는 미사일을 생성하는 메소드
	
	
	

	

	
	

}
