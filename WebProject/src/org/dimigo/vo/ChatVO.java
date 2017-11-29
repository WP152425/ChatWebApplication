package org.dimigo.vo;

public class ChatVO {
	
	int chatID;
	String chatName;
	String chatContent;
	String chatTime;
	String chatCategory;
	
	public String getChatCategory() {
		return chatCategory;
	}
	public void setChatCategory(String chatCategory) {
		this.chatCategory = chatCategory;
	}
	public int getChatID() {
		return chatID;
	}
	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
	public String getChatName() {
		return chatName;
	}
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	
	
}
