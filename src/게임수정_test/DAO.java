package ���Ӽ���_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class DAO {
	private final String DRIVE = "oracle.jdbc.driver.OracleDriver"; //DB����̹� �ּ�
	private final String URL = "jdbc:oracle:thin:@localhost:1522:XE"; // URL�ּ�
	private final String ID = "s1701281"; // ID
	private final String PWD = "p1701281"; //PWD
	
	
	public Connection getConn() {
		Connection con = null;
        try {
            Class.forName(DRIVE); //1. ����̹� �ε�
            con = DriverManager.getConnection(URL,ID,PWD); //2. ����̹� ����
            System.out.println("���Ӽ���");//���� ���� ����
        } catch (Exception e) {
        	System.out.println("���ӽ���");//���� ���� ����
            System.out.println(e.getMessage());
        }
       
        return con;
	}//db�� �����ϴ� �޼ҵ�
	
	public boolean insertData(String name/*��� ������� �̸��� �޴� �Ű�����*/, String score/*��� ������� ������ �޴� �Ű�����*/) {
		boolean ok = false;//��� �߰��� ���п� ������ �˷��ִ� ��
		Connection con = null;
		String sql = "insert into game_score(" +
                "name,score" +
                ") "+
                "values(?,?)";//DB���� ������ �����͸� ���̺�� ǥ�� ���ִ� ��ɾ�
		PreparedStatement ps = null;//DB����  sql���� ��������ټ� �ֵ��� �����ִ� ����
		int r = 0;//��� �߰��� ���� �Ǵ� ���� ������ ��ȯ�Ǵ� ���� �޾��ִ� ����
		try {
			con = getConn();//DB����
			ps = con.prepareStatement(sql);//DB�� ������ ��ɾ� ���
			ps.setString(1, name);//ù��° ?���� name���� ����
			ps.setString(2, score);//�ι�° ?���� score���� ����
			r = ps.executeUpdate();//��ɾ� ����
			if(r>0) {
				System.out.println("��ϼ���");//r==1�̸� ���༺��
				ok = true;//
			}else {
				System.out.println("��Ͻ���");//r==0�̸� ���� ����
				ok = false;//
			}
			con.close();//���� ����
			ps.close();//���� ����
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		return ok;
	}
	
	public Vector getMemberList() {
		Connection con = null;
		String sql = "select name,to_number(SCORE)\r\n" + 
				"from game_score\r\n" + 
				"order by to_number(SCORE) desc";//DB���� ������ �����͸� ���̺�� ǥ�� ���ִ� ��ɾ�
		ResultSet rs = null;//DB���� ��ɾ�(sql)�� ���� �Ҽ� �ְ� ���ִ� ����
		PreparedStatement ps = null;//DB����  sql���� ��������ټ� �ֵ��� �����ִ� ����
		Vector v = new Vector();//ȭ�鿡 ������ �����͸� �����ϴ� Vector
        try {
        	con = getConn();//DB�� ����
        	ps = con.prepareStatement(sql);//DB�� ������ ��ɾ� ���
        	rs = ps.executeQuery();//DB���� ��ɾ�(sql)�� ����
        	while(rs.next()) {//v�� ��� ���� ���� ���������� �ݺ�
        		Vector row = new Vector();//v�� �����͸� �ű� Vector
        		row.add(rs.getString("name"));//name Į���� ���� row�� ����
        		row.add(rs.getInt("to_number(SCORE)"));//to_number(SCORE) Į���� ���� row�� ����
        		v.add(row);//���� v�� �߰�
        	}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return v;//���� ȭ�鿡 ������ �����͸� ��ȯ
	}

}
