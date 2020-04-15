package com.dlnu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dlnu.pojo.User;
import com.dlnu.service.UserService;
import com.sun.org.apache.bcel.internal.generic.DALOAD;


@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	
	//��ȡ��ǰʱ��
	Calendar calendar= Calendar.getInstance();
	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
	String date = dateFormat.format(calendar.getTime());
	
	/**loginServlet
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		
		//����ҵ�����
		UserService service = new UserService();
		
		//���õ�¼����
		int result = service.checkLogin(username, password);
		System.out.println(result);
		//�����½�ɹ�,��session��Χ�ڴ洢�û���
		/*
		if(result==4){		
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
		}*/
		
		out.print(result);
	}
	
	/**����û����Ƿ��Ѵ���
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUsernameExist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����û���
		String username = request.getParameter("username_input");
		
		PrintWriter out = response.getWriter();
		
		//����ҵ�����
		UserService service = new UserService();
		
		/**
		 *  �ж����ݿ����Ƿ���ڴ��û���
		 * return: true / false
		 */
		boolean result = service.checkUsernameExist(username);
		
		out.print(result);
	}

	/**registerServlet
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		UserService service = new UserService();
		boolean flag = false;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String question = request.getParameter("select");
		String answer = request.getParameter("answer");
		String signuptime = date;
		
		System.out.println(" username:"+username+"\n signuptime"+signuptime);
		User user = new User(username, password, realname, sex, birth, address, tel, email, question, answer, signuptime);
		
		flag = service.addUser(user);
		out.print(flag);
	}
}
