package com.dlnu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlnu.pojo.Guestbook;
import com.dlnu.pojo.User;
import com.dlnu.service.GuestbookService;
import com.dlnu.service.ReplyService;
import com.dlnu.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/guestbook/*")
public class GuestbookServlet extends BaseServlet {
	
	GuestbookService gbService = new GuestbookService();
	UserService uService = new UserService();
	ReplyService rService = new ReplyService();
	//��ȡ��ǰʱ��
	Calendar calendar= Calendar.getInstance(Locale.CHINA);// ���ñ���ʱ��
	//��ȡ��������ʱ��
	SimpleDateFormat dateFormat2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = dateFormat2.format(calendar.getTime());
	
	/**
	 * ɾ��������Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteComment (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ǰ̨��ȡ��Ϣ
		String StrGbid = request.getParameter("gbid");
		int gbid = Integer.parseInt(StrGbid);
		
		boolean flag = false;
		boolean flag1 = rService.deleteReply(gbid);
		boolean flag2 = gbService.deleteGuestbook(gbid);
		
		if (flag1 == true && flag2 == true) {
			flag = true;
		}
		
        //д�ؿͻ���
  		response.setContentType("text/html; charset=UTF-8");  
  		PrintWriter out = response.getWriter();
  		out.print(flag);
  		out.close();
	}
	
	
	/**
	 * ���������Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addComment (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ǰ̨��ȡ��Ϣ
		String content = request.getParameter("comment");
		String username = request.getParameter("username");
		//ͨ��username����uid(user)
		User user = uService.queryUserByName(username);
		//��װ��guestbookʵ������
		Guestbook guestbook = new Guestbook(user.getuID(), content, date);
		
		boolean flag = gbService.addGuestbook(guestbook);
		
        //д�ؿͻ���
  		response.setContentType("text/html; charset=UTF-8");  
  		PrintWriter out = response.getWriter();
  		out.print(flag);
  		out.close();
	}

	/**
	 * ��ʾ�������Ա���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Guestbook> guestbooks = gbService.getAll();
		
		System.out.println(guestbooks);
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonStr = gson.toJson(guestbooks);
//		jsonStr = jsonStr.substring(1, jsonStr.length()-1);
		//System.out.println(jsonStr);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	    out.close(); 
	}
}
