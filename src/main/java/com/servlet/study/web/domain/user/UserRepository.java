package com.servlet.study.web.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.text.DateFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servlet.study.web.domain.db.DBConnectionMgr;

public class UserRepository {
	
	private DBConnectionMgr pool;
	
	public UserRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public List<Map<String, Object>> getUserList() {
		String sql = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		// list 생성
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			// 여기서 모든 작업을 해야 하는거임 ~ 
			con = pool.getConnection();
			sql = "SELECT\r\n"
					+ "	um.user_code,\r\n"
					+ "	um.user_id,\r\n"
					+ "	um.user_password,\r\n"
					+ "	um.user_name,\r\n"
					+ "	um.user_email,\r\n"
					+ "	ud.user_phone,\r\n"
					+ "	ud.user_address\r\n"
					+ "FROM\r\n"
					+ "	user_mst um\r\n"
					+ "	LEFT OUTER JOIN user_dtl ud ON(ud.user_code = um.user_code);";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			
			while(rs.next()) {
				Map<String, Object> map = new TreeMap<String, Object>();
				
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					int index = i + 1;
					Object value = null;
					
					if(rsmd.getColumnType(index) == Types.INTEGER) {
						value = rs.getInt(index); // getInt 때문에 잡아주는거임요 string 이면 int 못 들고와요.. 
						
					}else if(rsmd.getColumnType(index) == Types.VARCHAR) {
						value = rs.getString(index);
						
					}else if(rsmd.getColumnType(index) == Types.TIMESTAMP) {
						value = rs.getTimestamp(index).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					}
					
					map.put(rsmd.getColumnName(index), value);
				}
				
				list.add(map);
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			System.out.println(gson.toJson(list));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // 연결 해제 하겠다 ! 꼭 해줘야 해용
		}
		
		return list;
		
	}
	
	public int checkUserId(String userId) {
		String sql = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			con = pool.getConnection();
			sql = "select count(*) from user_mst where user_id = ?"; // 첫번째 물음표가 1번 물음표, 두번째가 2번 물음표  and user_id = ?
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId); // 1번 물음표에다가 userId를 set해라 ~ 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return result;
		
	}
	
	public int save(User user) {
		int result = 0;
		String sql = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		// insert는 result set 이 필요없겠죠? 가져올 게 없음. insert update delete는 result set 필요없음. 
		
		try {
			con = pool.getConnection();
			sql = "insert into user_mst values(0, ?, ?, ?, ?)"; // 첫번째 열은 0: auto increment
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getUser_password());
			pstmt.setString(3, user.getUser_name());
			pstmt.setString(4, user.getUser_email()); // ctrl + alt + 화살표
			result = pstmt.executeUpdate(); // executeUpdate() 는 int를 반환함 ; 영향받은 행
			// executeQuery() ; result set 반환함. 
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		return result; // 영향 받은 행 갯수 
	}
}
