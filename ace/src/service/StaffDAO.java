package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;


public class StaffDAO extends HttpServlet {
	private final String driverClassName = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3306/ace?useUnicode=true&characterEncoding=euckr";
    private final String username = "root";
    private final String password = "java0000";
    Connection connection = null;
    PreparedStatement statement = null;
    StaffDTO staffdto = new StaffDTO();
    
public int insertStaff(StaffDTO stdto) {
	System.out.println("insertStaff DAO");
    int rowCount = 0;
    String sql = "INSERT INTO staff(st_no, st_name, st_sn, st_graduateday, schoolno, religionno) values(?,?,?,?,?,?)";
    try {
        connection = this.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1,stdto.getSt_no());
        statement.setString(2,stdto.getSt_name());
        statement.setInt(3,stdto.getSt_sn());
        statement.setInt(4,stdto.getSt_graduateday());
        statement.setInt(5,stdto.getSt_schoolno());
        statement.setInt(6,stdto.getSt_religionno());
        rowCount = statement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        this.close(connection, statement, null);
    }
    return rowCount;
}
    

//등록 화면에 학력 뿌려주는 메서드
public ArrayList<SchoolDTO> selectSchool(){
	System.out.println("selectSchool 메서드 실행");
	ArrayList<SchoolDTO> sclist = new ArrayList<SchoolDTO>();
    ResultSet resultset = null;
    String sql = "SELECT * FROM school ORDER BY sc_no ASC";
    try {
        connection = this.getConnection();
        
        statement = connection.prepareStatement(sql);
        System.out.println("stmt is " + statement);
        
        resultset = statement.executeQuery();
        System.out.println("쿼리실행 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        while(resultset.next()) {
        	SchoolDTO scdto = new SchoolDTO();
        	scdto.setSc_no(resultset.getInt("sc_no"));
        	scdto.setSc_graduate(resultset.getString("sc_graduate"));
            sclist.add(scdto);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        this.close(connection, statement, resultset);
    }
    return sclist;
}


//등록 화면에 종교 뿌려주는 메서드
public ArrayList<ReligionDTO> selectReligion(){
	System.out.println("selectReligion 메서드 실행");
	ResultSet resultset = null;
	ArrayList<ReligionDTO> relist = new ArrayList<ReligionDTO>();
    String sql = "SELECT * FROM religion ORDER BY re_no ASC";
    try {
        connection = this.getConnection();
        
        statement = connection.prepareStatement(sql);
        System.out.println("stmt is " + statement);
        
        resultset = statement.executeQuery();
        System.out.println("쿼리실행 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        while(resultset.next()) {
        	ReligionDTO redto = new ReligionDTO();
        	System.out.println("1111111111");
        	redto.setRe_no(resultset.getInt("re_no"));
        	redto.setRe_name(resultset.getString("re_name"));
            relist.add(redto);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        this.close(connection, statement, resultset);
    }
    return relist;
}
	

//등록 화면에 기술 뿌려주는 메서드
public ArrayList<SkillDTO> selectSkill() {
	System.out.println("selectSkill 메서드 실행");
	ArrayList<SkillDTO> sklist = new ArrayList<SkillDTO>();
    ResultSet resultset = null;
    String sql = "SELECT * FROM skill ORDER BY sk_no ASC";
    try {
        connection = this.getConnection();
        
        statement = connection.prepareStatement(sql);
        System.out.println("stmt is " + statement);
        
        resultset = statement.executeQuery();
        System.out.println("쿼리실행 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        while(resultset.next()) {
        	SkillDTO skdto = new SkillDTO();
        	System.out.println("1111111111");
        	skdto.setSk_no(resultset.getInt("sk_no"));
        	skdto.setSk_name(resultset.getString("sk_name"));
            sklist.add(skdto);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        this.close(connection, statement, resultset);
    }
    return sklist;
}

private Connection getConnection() {
    try {
        Class.forName(this.driverClassName);
        connection = DriverManager.getConnection(this.url, this.username, this.password);
    } catch(Exception e) {
        e.printStackTrace();
    }
    return connection;
}

private void close(Connection connection, Statement statement, ResultSet resultset) {
    if(resultset != null) {
        try {
            resultset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(statement != null) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(connection != null) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
