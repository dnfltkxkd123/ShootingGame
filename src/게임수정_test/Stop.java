package 게임수정_test;

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
	private JButton btnInsert = new JButton("점수등록");//점수등록버튼
	private JButton btnClose = new JButton("종료");//종료버튼
	private JTextField text = new JTextField(10);//점수입력 텍스트필드
	private JButton btnRestart = new JButton("다시하기");//다시시작 버튼
	
	
	public Stop() {
		createUI();
		score();
		button();
		setLocation(600,200);
		setResizable(false);
		setSize(470,550);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("죽음 ㅅㄱ 점수 입력 가능요!");
		

		
		setVisible(true);
	}
	
	public void createUI() {
		DAO dao = new DAO();//DB에서 데이터를 가져올 객체
		Vector v = dao.getMemberList();//DB에서 점수기록자의 이름과 점수를 받아옴
		model = new DefaultTableModel(v,col());//테이블안에 데이터를 넣음
		jTable = new JTable(model);//
		scroll = new JScrollPane(jTable);//
		add(scroll,BorderLayout.SOUTH);//테이블을 가장밑에 넣음
	}
	
	public void reJTable() {
		DAO dao = new DAO();//DB에서 데이터를 가져올 객체
        DefaultTableModel model= new DefaultTableModel(dao.getMemberList()/*DB에서 점수기록자의 이름과 점수를 받아옴*/, col());//테이블안에 데이터를 넣음
        jTable.setModel(model);//테이블에을 model의 값으로 갱신
        text.setEnabled(false);//텍스트필드 비활성화
        btnInsert.setEnabled(false);//입력버튼 비활성화
	}//새로운 기록을 넣을때 실행되는 메소드
	
	public void score() {
		JLabel label = new JLabel("점수 :   " + GameFrame.myScore);//점수를 보여주는 JLabel
		label.setFont(new Font(null,Font.BOLD,30));//글자의 굵기 크기 설정
		label.setHorizontalAlignment(JLabel.CENTER);//글자가 중앙에 나오게함
		//label.setSize(100,100);
		JPanel panel = new JPanel();//label의 정보를 담을 JPanel생성
		panel.add(label);//panel에 label 추가
		add(panel,BorderLayout.CENTER);//점수창(panel)을 중앙에 출력
	}//플레이어의 점수를 알려주는 메소드
	
	public void button() {
		JLabel label = new JLabel("이름적기 : ");

		btnInsert.addActionListener(this);//리스너등록
		btnRestart.addActionListener(this);//리스너등록
		JPanel panel = new JPanel();//버튼의 정보를 담을 JPanel생성
		panel.add(label);//panel에 label추가
		panel.add(text);//panel에 텍스트필드 추가 추가
		panel.add(btnInsert);//panel에 입력버튼 추가
		panel.add(btnRestart);//panel에 다시시작버튼 추가
		//panel.add(btnClose);
		add(panel,BorderLayout.NORTH);//panel위치를 맨위로 설정
	}//다시시작 버튼과 입력버튼의 정보를 담는 메소드
	
	public Vector col() {
		Vector col = new Vector();//화면에 보여줄 칼럼의 내용을 저장할 Vector
		String id = "플레이어";//칼럼1
		String score = "점수";//칼럼2
		col.add(id);//col에 칼럼1를 추가
		col.add(score);//col에 칼럼2를 추가
		return col;//col반환
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) {//클릭을 했을때 입력버튼이면 실행
			String player = text.getText();//플레이어의 이름을 저장
			String sroce = Integer.toString(GameFrame.myScore);//플레이어의 점수를 저장
			DAO dao = new DAO();
			if(player==null) {//아무것도 입력 안하면 실행
				System.out.println("이름을 입력 안했습니다!!");//
				return;//종료
			}
			else {
				boolean ok = dao.insertData(player,sroce);
				if(ok) {
					reJTable();
					text.setText("");
					JOptionPane.showMessageDialog(this, "등록완료");
					
				}else {
					JOptionPane.showMessageDialog(this, "이름을 입력하세요!");
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
