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
import com.dlnu.pojo.Reply;
import com.dlnu.service.GuestbookService;
import com.dlnu.service.ReplyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/reply/*")
public class ReplyServlet extends BaseServlet {
	ReplyService rService = new ReplyService();
	GuestbookService gbService = new GuestbookService();
	
	//��ȡ��ǰʱ��
	Calendar calendar= Calendar.getInstance(Locale.CHINA);// ���ñ���ʱ��
	//��ȡ��������ʱ��
	SimpleDateFormat dateFormat2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = dateFormat2.format(calendar.getTime());
	
	/**
	 * ���һ���ظ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String strGbid = request.getParameter("gbid");
		String strUid = request.getParameter("uid");
		String content = request.getParameter("content");
		int gbid = Integer.parseInt(strGbid);
		int uid = Integer.parseInt(strUid);
		Reply reply = new Reply(gbid, uid, content, date);
		
		boolean flag = false;
		boolean flag1 = rService.addReply(reply);
		boolean flag2 = gbService.updateFlag(gbid);
		
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
	 * ��ȡһ���ظ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String gbidStr = request.getParameter("gbid");
		Integer gbid = Integer.parseInt(gbidStr);
		Reply reply = rService.getByGbid(gbid);
		
//		System.out.println(reply);
		
		//���л�����
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonStr = gson.toJson(reply);
//		System.out.println(jsonStr);
		//���ݷ���ǰ̨
		PrintWriter out = response.getWriter();
		out.print(jsonStr);	
		out.close();
	}

}
