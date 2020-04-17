package com.dlnu.service;

import java.util.ArrayList;
import java.util.List;

import com.dlnu.dao.UserDao;
import com.dlnu.pojo.User;
import com.dlnu.util.PageBean;

public class UserService {
	private UserDao dao = new UserDao();
	
	public int userCount() {
		return dao.findAll();
	}
	
	/**
	 * ��ѯdate1-date2�� �û�ע������
	 * @param time1
	 * @param time2
	 * @return
	 */
	public int userCountByTime(String time1, String time2) {
		return dao.findAllByTime(time1, time2);
	}
	
	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	public boolean addUser(User user){
		int result = dao.insert(user);
		if (result != -1) { 
			return true; 
		}else {
			return false; 
		}
	}

	
	/**
	 * ͨ��uid�����û���Ϣ
	 * @param userId
	 * @return
	 */
	public User queryUserById(Integer userId){
		return dao.queryById(userId);
	}
	
	/**
	 * ͨ���û��������û���Ϣ
	 * @param username
	 * @return
	 */
	public User queryByName(String username){
		return dao.queryByName(username);
	}
	
	/**��ѯ�����û���Ϣ
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<User> queryUser(int currentPage, int pageSize){
		//��װpageBean
        PageBean<User> pb = new PageBean<User>();
        //���õ�ǰҳ��
        pb.setCurrentPage(currentPage);
        //����ÿҳ��ʾ����
        pb.setPageSize(pageSize);
        //�����ܼ�¼��
        int totalCount = dao.findAll();
        pb.setTotalCount(totalCount);
        //���õ�ǰҳ��ʾ�����ݼ���
        int start = (currentPage-1)*pageSize;   //��ʼ�ļ�¼��
        List<User> list = dao.query(start,pageSize);
        pb.setList(list);

        //������ҳ�� = �ܼ�¼��/ÿҳ��ʾ����
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);
		return pb;
	}
	
	
	/**�鿴�û����Ƿ����
	 * 
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
	
	
	
	/**����û����������Ƿ���ȷ
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
