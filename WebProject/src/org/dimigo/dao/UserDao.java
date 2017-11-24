package org.dimigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dimigo.vo.UserVO;

public class UserDao {
	
	private Connection conn = null;
	
	public UserDao(Connection conn){
		this.conn = conn;
	}
	
	public UserVO searchUser(UserVO vo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USER WHERE ID = ? AND PWD = ?";
				
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			
			rs = pstmt.executeQuery();
			
			UserVO result = null;
			if(rs.next()){
				result = new UserVO();
				result.setId(rs.getString(1));
				result.setName(rs.getString(3));
				result.setNickname(rs.getString(4));
			}
			
			System.out.println(result);
			return result;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("사용자 조회시 오류가 발생했습니다.");
		} finally {
			if(rs != null) rs.close();
			if (pstmt!= null) pstmt.close();
		}
	}

	public UserVO searchUserById(UserVO vo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USER WHERE ID = ?";
				
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			
			rs = pstmt.executeQuery();
			
			UserVO result = null;
			if(rs.next()){
				result = new UserVO();
				result.setId(rs.getString(1));
				result.setName(rs.getString(3));
				result.setNickname(rs.getString(4));
			}
			
			System.out.println(result);
			return result;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("사용자 조회시 오류가 발생했습니다.");
		} finally {
			if(rs != null) rs.close();
			if (pstmt!= null) pstmt.close();
		}
	}
	
	public UserVO searchUserByNickname(UserVO vo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USER WHERE NICKNAME = ?";
				
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNickname());
			
			rs = pstmt.executeQuery();
			
			UserVO result = null;
			if(rs.next()){
				result = new UserVO();
				result.setId(rs.getString(1));
				result.setName(rs.getString(3));
				result.setNickname(rs.getString(4));
			}
			
			System.out.println(result);
			return result;
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("사용자 조회시 오류가 발생했습니다.");
		} finally {
			if(rs != null) rs.close();
			if (pstmt!= null) pstmt.close();
		}
	}

	public void insertUser(UserVO vo) throws Exception{
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO USER VALUES(?,?,?,?)";
				
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			
			int count = pstmt.executeUpdate();
			
			if (count == 0) throw new Exception("회원가입에 실패하였습니다.");
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("사용자 회원가입시 오류가 발생했습니다.");
		} finally {
			if (pstmt!= null) pstmt.close();
		}
	}

	public void updateUser(UserVO vo, HttpServletRequest request) throws Exception{
		PreparedStatement pstmt = null;
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		
		
		String sql = "UPDATE USER SET ID = ?, PWD = ?, NAME = ?, NICKNAME = ? WHERE ID = ?";
				
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, user.getId());
			
			int count = pstmt.executeUpdate();
			
			if (count == 0) throw new Exception("정보저장에 실패하였습니다.");
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("사용자 정보수정시 오류가 발생했습니다.");
		} finally {
			if (pstmt!= null) pstmt.close();
		}
	}
	
}
