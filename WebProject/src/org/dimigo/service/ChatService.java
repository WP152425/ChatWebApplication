package org.dimigo.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.dimigo.dao.chatDAO;
import org.dimigo.vo.ChatVO;

public class ChatService extends AbstractService{
	
	public String getToday() throws Exception{
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer result = new StringBuffer("");
			result.append("{\"result\":[");
			chatDAO dao = new chatDAO(conn);
			ArrayList<ChatVO> chatList = dao.getChatList(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			for (int i = 0; i < chatList.size(); i++){
				result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
				result.append("{\"value\" : \"" + chatList.get(i).getChatContent()+ "\"},");
				result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"}]");
				if (i != chatList.size() -1) result.append(",");
			}
			result.append("], \"last\" : \"" + chatList.get(chatList.size() -1).getChatID() + "\"}");
			return result.toString();
		} finally {
			if (conn != null) conn.close();
		}
	}
	
	public String getTen(String chatCategory) throws Exception{
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer result = new StringBuffer("");
			result.append("{\"result\":[");
			chatDAO dao = new chatDAO(conn);
			ArrayList<ChatVO> chatList = dao.getChatListByRecent(10, chatCategory);
			for (int i = 0; i < chatList.size(); i++){
				result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
				result.append("{\"value\" : \"" + chatList.get(i).getChatContent()+ "\"},");
				result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"}]");
				if (i != chatList.size() -1) result.append(",");
			}
			result.append("], \"last\" : \"" + chatList.get(chatList.size() - 1).getChatID() + "\"}");
			return result.toString();
		} finally {
			if (conn != null) conn.close();
		}
	}

	public String getID(String chatID, String chatCategory) throws Exception{
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer result = new StringBuffer("");
			result.append("{\"result\":[");
			chatDAO dao = new chatDAO(conn);
			ArrayList<ChatVO> chatList = dao.getChatListByRecent(chatID, chatCategory);
			for (int i = 0; i < chatList.size(); i++){
				result.append("[{\"value\" : \"" + chatList.get(i).getChatName() + "\"},");
				result.append("{\"value\" : \"" + chatList.get(i).getChatContent()+ "\"},");
				result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"}]");
				if (i != chatList.size() -1) result.append(",");
			}
			result.append("], \"last\" : \"" + chatList.get(chatList.size() -1).getChatID() + "\"}");
			return result.toString();
		} finally {
			if (conn != null) conn.close();
		}
	}
	
	public String chatSubmit(String chatName, String chatContent, String chatCategory) throws Exception {
		
		Connection conn = null;
		
		try {
			conn = getConnection();
			chatDAO dao = new chatDAO(conn);
			
			return dao.submit(chatName, chatContent, chatCategory) + "";
		} finally {
			if (conn != null) conn.close();
		}
	}
}
