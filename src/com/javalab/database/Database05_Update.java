package com.javalab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database05_Update {
	
	public static void main(String[] args) {
		
		/*
		 * 상품 가격 변경 (수정)
		 * 탱크로리 상품의 가격을 500,000으로 수정하시오.
		 */
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String dbId = "tempdb";
		String dbPwd = "1234";

		Connection con = null;
		// PreparedStatement 객체 변수 pstmt를 선언
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select의 결과 객체 저장

		// SQL문을 저장할 변수 선언
		String sql;

		try {
			// 1. 드라이버 로딩
			Class.forName(driver);
			System.out.println("1. 드라이버 로드 성공!");

			// 2. 데이터베이스 커넥션 연결
			con = DriverManager.getConnection(url, dbId, dbPwd);
			System.out.println("2. 커넥션 객체 생성 성공!");
			
			// update query
			int productId = 12;
			int price = 500000;
			sql = "update product set price = ? ";
			sql += "where product_id = ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, price);
			pstmt.setInt(2, productId);
			
			int resultNo = pstmt.executeUpdate();
			
			if (resultNo > 0) {
				System.out.println("수정 성공!!!");
			}else {
				System.out.println("수정 실패!!!");
			}
			
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 ERR! : " + e.getMessage());
		}catch (SQLException e) {
			System.out.println("SQL ERR! : " + e.getMessage());
		}finally {
			try {
				//자원 해제 (반납)
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("자원해제 ERR! : " + e.getMessage());
			}
		}
	}
}
