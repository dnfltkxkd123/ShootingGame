package 게임수정_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class DAO {
	private final String DRIVE = "oracle.jdbc.driver.OracleDriver"; //DB드라이버 주소
	private final String URL = "jdbc:oracle:thin:@localhost:1522:XE"; // URL주소
	private final String ID = "s1701281"; // ID
	private final String PWD = "p1701281"; //PWD
	
	
	public Connection getConn() {
		Connection con = null;
        try {
            Class.forName(DRIVE); //1. 드라이버 로딩
            con = DriverManager.getConnection(URL,ID,PWD); //2. 드라이버 연결
            System.out.println("접속성공");//접속 성공 문구
        } catch (Exception e) {
        	System.out.println("접속실패");//접속 실패 문구
            System.out.println(e.getMessage());
        }
       
        return con;
	}//db에 접속하는 메소드
	
	public boolean insertData(String name/*기록 등록자의 이름을 받는 매개변수*/, String score/*기록 등록자의 점수를 받는 매개변수*/) {
		boolean ok = false;//기록 추가의 실패와 성공을 알려주는 값
		Connection con = null;
		String sql = "insert into game_score(" +
                "name,score" +
                ") "+
                "values(?,?)";//DB에서 가져올 데이터를 테이블로 표현 해주는 명령어
		PreparedStatement ps = null;//DB에서  sql값을 실행시켜줄수 있도록 도와주는 변수
		int r = 0;//기록 추가의 실패 또는 성공 했을때 반환되는 값을 받아주는 변수
		try {
			con = getConn();//DB접속
			ps = con.prepareStatement(sql);//DB에 실행할 명령어 등록
			ps.setString(1, name);//첫번째 ?값을 name으로 수정
			ps.setString(2, score);//두번째 ?값을 score으로 수정
			r = ps.executeUpdate();//명령어 실행
			if(r>0) {
				System.out.println("등록성공");//r==1이면 실행성공
				ok = true;//
			}else {
				System.out.println("등록실패");//r==0이면 실행 실패
				ok = false;//
			}
			con.close();//접속 종료
			ps.close();//접속 종료
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		return ok;
	}
	
	public Vector getMemberList() {
		Connection con = null;
		String sql = "select name,to_number(SCORE)\r\n" + 
				"from game_score\r\n" + 
				"order by to_number(SCORE) desc";//DB에서 가져올 데이터를 테이블로 표현 해주는 명령어
		ResultSet rs = null;//DB에서 명령어(sql)를 실행 할수 있게 해주는 변수
		PreparedStatement ps = null;//DB에서  sql값을 실행시켜줄수 있도록 도와주는 변수
		Vector v = new Vector();//화면에 보여줄 데이터를 저장하는 Vector
        try {
        	con = getConn();//DB에 접속
        	ps = con.prepareStatement(sql);//DB에 실행할 명령어 등록
        	rs = ps.executeQuery();//DB에서 명령어(sql)를 실행
        	while(rs.next()) {//v가 모든 행의 값을 받을때까지 반복
        		Vector row = new Vector();//v에 데이터를 옮길 Vector
        		row.add(rs.getString("name"));//name 칼럼의 값을 row에 저장
        		row.add(rs.getInt("to_number(SCORE)"));//to_number(SCORE) 칼럼의 값을 row에 저장
        		v.add(row);//행을 v에 추가
        	}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return v;//점수 화면에 보여줄 데이터를 반환
	}

}
