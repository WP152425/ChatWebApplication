package org.dimigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.dimigo.vo.ChatVO;

public class chatDAO {
	
	private Connection conn = null;
	
	public chatDAO(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<ChatVO> getChatList (String nowTime){
		ArrayList<ChatVO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CHAT WHERE chatTime > ? ORDER BY chatTime";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nowTime);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatVO>();
			while(rs.next()){
				ChatVO chat = new ChatVO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if (Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16) + "");
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<ChatVO> getChatListByRecent (int number, String chatCategory){
		ArrayList<ChatVO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CHAT WHERE chatCategory = ? AND chatID > (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY chatTime";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, number);
			pstmt.setString(1, chatCategory);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatVO>();
			while(rs.next()){
				ChatVO chat = new ChatVO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if (Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16) + "");
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<ChatVO> getChatListByRecent (String chatID, String chatCategory){
		ArrayList<ChatVO> chatList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CHAT WHERE chatCategory = ? AND chatID > ? ORDER BY chatTime";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, Integer.parseInt(chatID));
			pstmt.setString(1, chatCategory);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatVO>();
			while(rs.next()){
				ChatVO chat = new ChatVO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if (Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14,16) + "");
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public int submit(String chatName, String chatContent, String chatCategory){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO CHAT VALUES(NULL, ?, ?, now(), ?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chatName);
			pstmt.setString(2, chatContent);
			pstmt.setString(3, chatCategory);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return -1;
	}
}
