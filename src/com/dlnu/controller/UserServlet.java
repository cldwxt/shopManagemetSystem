package com.dlnu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.dlnu.pojo.Card;
import com.dlnu.pojo.User;
import com.dlnu.service.CardService;
import com.dlnu.service.UserService;
import com.dlnu.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.bcel.internal.generic.DALOAD;


@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	
	//��ȡ��ǰʱ��
	Calendar calendar= Calendar.getInstance();
	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
	String date = dateFormat.format(calendar.getTime());
	
	/**��ѯ�����û���Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		//1.���ղ���
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int currentPage = 0;
        int pageSize = 0;
        //2.�������
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 10;
        }
        //3. ����service��ѯPageBean����
        UserService service = new UserService();
        PageBean<User> pb = service.queryUser(currentPage,pageSize);
        //4.���л�����
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonStr = gson.toJson(pb);
        //5. ��pageBean�������л���д�ؿͻ���
        out.print(jsonStr);
	}
	
	
	/**��¼Servlet
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

	/**ע��Servlet
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		boolean flag = false;
		//����ҵ�����
		UserService uService = new UserService();
		CardService cService = new CardService();
		
		//��ǰ̨��ȡ����
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
		//�����ݷ�װ��Userʵ���� ��userû��uid
		User user = new User(username, password, realname, sex, birth, address, tel, email, question, answer, signuptime);
		//���������û�����
		flag = uService.addUser(user);
		
		/*add��Ա����ʼ*/
		//���� ͨ��username����uid��  ��user2��uid��������userһ��
		User user2 = uService.queryByName(username);
		Card card = new Card(user2);
		cService.addCard(card);
		/*add��Ա������*/
		
		out.print(flag);
	}
}
