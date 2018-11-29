package ���Ӽ���_test;

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
	public void keyPressed(KeyEvent e) {//Ű�� �������� �Ͼ�� �̺�Ʈ�� ����ϴ� �޼ҵ�
		switch(e.getKeyCode()) {//
		case KeyEvent.VK_UP:
			keyUp = true;//���ΰ��� ��ư�� ������ keyUp�� true����
			break;
		case KeyEvent.VK_DOWN:
			keyDown = true;//�������� ��ư�� ������ keyDown�� true����
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = true;//���� ��ư�� ������ keyLeft�� true����
			break;
		case KeyEvent.VK_RIGHT:
			keyRight = true;//������ ��ư�� ������ keyRight�� true����
			break;
		case KeyEvent.VK_SPACE:
			keySpace = true;//�����̽� ��ư�� ������ keySpace�� true����
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {//��ư�� ������ �Ͼ�� �̺�Ʈ�� ����ϴ� �޼ҵ�
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keyUp = false;//���ΰ��� ��ư�� ���� keyUp�� false����
			break;
		case KeyEvent.VK_DOWN://���ΰ��� ��ư�� ���� keyDown�� false����
			keyDown = false;
			break;
		case KeyEvent.VK_LEFT://���ΰ��� ��ư�� ���� keyLeft�� false����
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT://���ΰ��� ��ư�� ���� keyRight�� false����
			keyRight = false;
			break;
		case KeyEvent.VK_SPACE://���ΰ��� ��ư�� ���� keySpace�� false����
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
	}//Űboolean���� true�̸� �÷��̾ �����ϼ� �ְ� ���ִ� �޼ҵ�
	
	public void keyStop() {
		this.keyUp = false;
		this.keyDown = false;
		this.keyLeft = false;
		this.keyRight = false;
		this.keySpace = false;
	}//������ �ٽ� �����Ҷ� �÷��̾ ������� �������� �ʵ��� ��� Ű�� boolean���� false�� ����
	
	public void MissileProduct() {
		if(keySpace) {//�����̽��� ������ �÷��̾ �߻��ϴ� �̻����� ����
			for(int i=0 ; i<3 ; i++) {//�����ð� ���� 3���� �̻����� ����
				Main.ms = new Missile(Main.x,Main.y,i);//i�� 0�̸� ����,i�� 1�̸� �߰�,i��2�� �Ʒ��� �����̸� �̻��� ����
				Main.MissileList.add(Main.ms);//�̻����� ����Ʈ�� ���
				break;
			}
		}
	}//�÷��̾ �߻� �ϴ� �̻����� �����ϴ� �޼ҵ�
	
	
	

	

	
	

}
