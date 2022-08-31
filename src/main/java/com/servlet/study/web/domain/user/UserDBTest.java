package com.servlet.study.web.domain.user;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import com.servlet.study.web.domain.db.DBConnectionMgr;

public class UserDBTest {
	
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance(); // DBConnectionMgr 이 MariaDB랑 연결해주는 클래스임. 
		// 싱글톤 패턴으로 되어 있어서 getInstance를 통해 인스턴스를 가져와서 사용하는 방식.
		
		try {
			Connection con = pool.getConnection();
			
//			String sql = "select * from product_mst";
			String sql = "SELECT o.order_code, o.order_user, u.user_id, o.order_product, p.product_name, p.product_category, c.category_name, p.product_price, o.order_datetime\r\n"
					+ "FROM order_mst o\r\n"
					+ "	inner JOIN user_mst u ON(u.user_code = o.order_user)\r\n"
					+ "	inner JOIN product_mst p ON(p.product_code = o.order_product)\r\n"
					+ "	inner JOIN category_mst c ON(c.category_code = p.product_category)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			int colCount = metaData.getColumnCount();
			
			for(int i = 0; i < colCount; i++) {
				System.out.printf("%-50s", metaData.getColumnName(i+1)); // -는 왼쪽 정렬
			}
			System.out.println();
			
			while(rs.next()) {
//				System.out.print("product code: ");
//				System.out.println(rs.getInt(1));
//				System.out.print("product name: ");
//				System.out.println(rs.getString(2));
//				System.out.print("product category: ");
//				System.out.println(rs.getInt(3));
//				System.out.print("product price: ");
//				System.out.println(rs.getInt(4));
//				System.out.println();
				
				for(int i = 0; i < colCount; i++) {
					int index = i + 1;
					
					int scale = metaData.getScale(index);
					int colType = metaData.getColumnType(index);
					
					if(colType == Types.INTEGER && scale == 0) { // scale == 0 이라는 건 정수라는 거임. 
						System.out.printf("%-50d",rs.getInt(index));
					}else if(colType == Types.VARCHAR) {
						System.out.printf("%-50s",rs.getString(index));
					}else if(colType == Types.TIMESTAMP) {
						System.out.printf("%-50s",rs.getTimestamp(index).toLocalDateTime());
					}
				}
				
				System.out.println();
				
//				System.out.print("주문번호: ");
//				System.out.println(rs.getInt(1));
//				System.out.print("주문고객: ");
//				System.out.println(rs.getInt(2));
//				System.out.print("유저: ");
//				System.out.println(rs.getString(3));
//				System.out.print("주문상품번호: ");
//				System.out.println(rs.getInt(4));
//				System.out.print("주문상품: ");
//				System.out.println(rs.getString(5));
//				System.out.print("상품카테고리: ");
//				System.out.println(rs.getInt(6));
//				System.out.print("카테고리명: ");
//				System.out.println(rs.getString(7));
//				System.out.print("상품가격: ");
//				System.out.println(rs.getString(8));
//				System.out.print("주문시간: ");
//				System.out.println(rs.getString(9));
//				System.out.println();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
//		DBConnectionMgr pool = DBConnectionMgr.getInstance();
//		
//		try {
//			Connection con = pool.getConnection(); // DB와 연결됨
//			
//			String sql = "select * from user_mst";	// 
//			
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				System.out.print("코드: ");
//				System.out.println(rs.getInt(1));
//				System.out.print("아이디: ");
//				System.out.println(rs.getString(2));
//				System.out.print("비밀번호: ");
//				System.out.println(rs.getString(3));
//				System.out.print("이름: ");
//				System.out.println(rs.getString(4));
//				System.out.print("이메일: ");
//				System.out.println(rs.getString(5));
//			}
//			
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		
	}
	
}
