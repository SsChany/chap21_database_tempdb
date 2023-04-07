package com.javalab.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Database02_Select {
	public static void main(String[] args) {
		
		//3. 가격이 25,000원 이상인 상품들의 이름과 가격을 조회하세요.
		//오라클 드라이버 로딩 문자열
		String driver = "oracle.jdbc.driver.OracleDriver";
		//데이터베이스 연결 문자열
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		//데이터베이스명 (계정명)
		String dbId = "tempdb";
		//데이터베이스 비밀번호
		String dbPwd = "1234";
				
		//데이터베이스 연결 객체
		Connection con = null;
		//커넥션 객체를 통해서 데이터베이스에 쿼리(SQL)를 실행해주는 객체
		PreparedStatement pstmt = null;
		//실행된 쿼리문의 결과를 반환 받는 객체
		ResultSet rs = null;
		String sql;
		try {
			// 1. 드라이버 로딩
			Class.forName(driver);
			System.out.println("1. 드라이버 로드 성공!");
					
			// 2. 데이터베이스 커넥션 (연결)
			con = DriverManager.getConnection(url, dbId, dbPwd);
			System.out.println("2. 커넥션 객체 생성 성공!");
					
					
			/*3. 생성한 prepareStatement 객체를 통해서 쿼리하기 위한 
			SQL 쿼리 문장 만들기 (삽입, 수정, 삭제, 조회)
			........쿼리문 구현하세요...............*/		
			int wherePrice = 25000;
			
			sql = "select  p.PRODUCT_NAME, p.PRICE";
			sql += " from product p";
			sql += " where p.price >= ? ";
			sql += "order by p.price desc";
			
			
			//4. 커넥션 객체를 통해서 데이터베이스에 쿼리(SQL)를 실행해주는 
			// 	 PerpareStatement 객체 얻음
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wherePrice);
			System.out.println("3. stmt 객체 생성 성공 : ");
			
			//5. Statment 객체의 executeQuery() 메소드를 통해서 쿼리 실행
			//	 데이터 베이스에서 조회된 결과가  ResultSet 객체에 담겨옴
			rs = pstmt.executeQuery();
			System.out.println();
			
			//6. rs.next()의 의미 설명
			while (rs.next()) {
				System.out.println(rs.getString("PRODUCT_NAME")
					     + "\t" +  rs.getInt("PRICE")		
						);
				
				
				
				
				
			}
		 } catch (ClassNotFoundException e) {
            System.out.println("드라이버 ERR! : " + e.getMessage());

         } catch (SQLException e) {
            System.out.println("SQL ERR! : " + e.getMessage());
         } finally {
            try {
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
	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
