package ���Ӽ���_test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;





public class Stop extends JFrame implements ActionListener{
	private DefaultTableModel model;//
	private JTable jTable;//
	private JScrollPane scroll;//
	private JButton btnInsert = new JButton("�������");//������Ϲ�ư
	private JButton btnClose = new JButton("����");//�����ư
	private JTextField text = new JTextField(10);//�����Է� �ؽ�Ʈ�ʵ�
	private JButton btnRestart = new JButton("�ٽ��ϱ�");//�ٽý��� ��ư
	
	
	public Stop() {
		createUI();
		score();
		button();
		setLocation(600,200);
		setResizable(false);
		setSize(470,550);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("���� ���� ���� �Է� ���ɿ�!");
		

		
		setVisible(true);
	}
	
	public void createUI() {
		DAO dao = new DAO();//DB���� �����͸� ������ ��ü
		Vector v = dao.getMemberList();//DB���� ����������� �̸��� ������ �޾ƿ�
		model = new DefaultTableModel(v,col());//���̺�ȿ� �����͸� ����
		jTable = new JTable(model);//
		scroll = new JScrollPane(jTable);//
		add(scroll,BorderLayout.SOUTH);//���̺��� ����ؿ� ����
	}
	
	public void reJTable() {
		DAO dao = new DAO();//DB���� �����͸� ������ ��ü
        DefaultTableModel model= new DefaultTableModel(dao.getMemberList()/*DB���� ����������� �̸��� ������ �޾ƿ�*/, col());//���̺�ȿ� �����͸� ����
        jTable.setModel(model);//���̺��� model�� ������ ����
        text.setEnabled(false);//�ؽ�Ʈ�ʵ� ��Ȱ��ȭ
        btnInsert.setEnabled(false);//�Է¹�ư ��Ȱ��ȭ
	}//���ο� ����� ������ ����Ǵ� �޼ҵ�
	
	public void score() {
		JLabel label = new JLabel("���� :   " + GameFrame.myScore);//������ �����ִ� JLabel
		label.setFont(new Font(null,Font.BOLD,30));//������ ���� ũ�� ����
		label.setHorizontalAlignment(JLabel.CENTER);//���ڰ� �߾ӿ� ��������
		//label.setSize(100,100);
		JPanel panel = new JPanel();//label�� ������ ���� JPanel����
		panel.add(label);//panel�� label �߰�
		add(panel,BorderLayout.CENTER);//����â(panel)�� �߾ӿ� ���
	}//�÷��̾��� ������ �˷��ִ� �޼ҵ�
	
	public void button() {
		JLabel label = new JLabel("�̸����� : ");

		btnInsert.addActionListener(this);//�����ʵ��
		btnRestart.addActionListener(this);//�����ʵ��
		JPanel panel = new JPanel();//��ư�� ������ ���� JPanel����
		panel.add(label);//panel�� label�߰�
		panel.add(text);//panel�� �ؽ�Ʈ�ʵ� �߰� �߰�
		panel.add(btnInsert);//panel�� �Է¹�ư �߰�
		panel.add(btnRestart);//panel�� �ٽý��۹�ư �߰�
		//panel.add(btnClose);
		add(panel,BorderLayout.NORTH);//panel��ġ�� ������ ����
	}//�ٽý��� ��ư�� �Է¹�ư�� ������ ��� �޼ҵ�
	
	public Vector col() {
		Vector col = new Vector();//ȭ�鿡 ������ Į���� ������ ������ Vector
		String id = "�÷��̾�";//Į��1
		String score = "����";//Į��2
		col.add(id);//col�� Į��1�� �߰�
		col.add(score);//col�� Į��2�� �߰�
		return col;//col��ȯ
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) {//Ŭ���� ������ �Է¹�ư�̸� ����
			String player = text.getText();//�÷��̾��� �̸��� ����
			String sroce = Integer.toString(GameFrame.myScore);//�÷��̾��� ������ ����
			DAO dao = new DAO();
			if(player==null) {//�ƹ��͵� �Է� ���ϸ� ����
				System.out.println("�̸��� �Է� ���߽��ϴ�!!");//
				return;//����
			}
			else {
				boolean ok = dao.insertData(player,sroce);
				if(ok) {
					reJTable();
					text.setText("");
					JOptionPane.showMessageDialog(this, "��ϿϷ�");
					
				}else {
					JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���!");
				}
			}
		}else if(e.getSource() == btnClose){
			
		}
		
		if(e.getSource()== btnRestart) {
			//new GameFrame();
			Main.gameframe.reFresh();
			this.dispose();
		}
		
	}
	
	public static void main(String[] args) {
		new Stop();
	}

}
