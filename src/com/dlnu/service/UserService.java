package com.dlnu.service;

import java.util.ArrayList;

import com.dlnu.dao.UserDao;
import com.dlnu.pojo.User;

public class UserService {
	private UserDao dao = new UserDao();
	
	
	public boolean addUser(User user){
		int result = dao.insert(user);
		if (result != -1) { 
			return true; 
		}else {
			return false; 
		}
	}
	
	
	/*
	public User queryUserById(Integer userId){
		return dao.queryById(userId);
	}
	public User queryByName(String username){
		return dao.queryByName(username);
	}
	public ArrayList<User> queryUser(){
		return dao.query();
	}
	
	*/
	/**
	 * �鿴�û����Ƿ����
	 * @param username
	 * @return
	 */
	public boolean checkUsernameExist(String username) {
		User user = dao.queryByName(username);
		if(user.getUserName().equals(username)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	/**����û����������Ƿ����
	 * @param username
	 * @param password
	 * @return
	 * 1.����Ա���˺�����
	 * 2.�û���������
	 * 3.�������
	 * 4.������ȷ���û�������
	 * -1:������
	 */
	public int checkLogin(String username,String password){
		
		User user = dao.queryByName(username);
		if(username.equals("admin") && password.equals("123admin")) {
			return 1;
		}else if(user == null){
			return 2;
		}else if(user.getPassword().equals(password) == false){
			return 3;
		}else if(user.getUserName().equals(username) == true){
			return 4;
		}else {
			return -1;
		}
	}
}
