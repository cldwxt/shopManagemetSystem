package com.dlnu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.dlnu.pojo.Card;
import com.dlnu.pojo.User;
import com.dlnu.service.CardService;
import com.dlnu.service.UserService;
import com.dlnu.util.DateUtil;
import com.dlnu.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.bcel.internal.generic.DALOAD;


@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	
	//��ȡ��ǰʱ��
	Calendar calendar= Calendar.getInstance(Locale.CHINA);// ���ñ���ʱ��
	//��ȡ����00��00
	SimpleDateFormat dateFormat1= new SimpleDateFormat("yyyy-MM-dd");
	//String date1 = dateFormat1.format(calendar.getTime());
	//��ȡ��������ʱ��
	SimpleDateFormat dateFormat2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//String date2 = dateFormat2.format(calendar.getTime());
	
	public void queryUserCountByWeek(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, Object> hashMap1 = new LinkedHashMap<String, Object>();

		Gson gson = new Gson();
		;
		for (int i = -7 ,c=1; i < 0; i++,c++) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			//���� yyyy-MM-dd
			String beginDate = dateFormat1.format(DateUtil.getBeginDayOfNDay(i));
			String endDate = dateFormat2.format(DateUtil.getEndDayOfNDay(i));
			//����
			String weekDay = DateUtil.dateToWeek(beginDate);
			//��һ��ע������
			int count = allUserCountByDay(beginDate, endDate);
			
			hashMap.put("beginDate", beginDate);
			hashMap.put("weekDay", weekDay);
			hashMap.put("count", count);
			hashMap1.put("day"+c, hashMap);
					
		}
		
	//	System.out.println(hashMap1);
		String jsonStr = gson.toJson(hashMap1);
	//	System.out.println(jsonStr);
		
		//���ݷ���ǰ̨
		response.setContentType("text/html; charset=UTF-8");  
		PrintWriter out = response.getWriter();
		out.print(jsonStr);	
		out.close();
	}
	
	
	/**
	 * �޸��û���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean flag = false;
		
		//��ǰ̨��ȡ����
		String username = request.getParameter("username");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		//��װʵ����
		User user = new User(username, realname, sex, birth, address, tel, email);
		//����ҵ�����
		UserService uService = new UserService();
		flag = uService.updateUser(user);
		
		out.print(flag);
		out.close();
	}
	
	/**
	 * ��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int flag = -1;
		
		//��ǰ̨��ȡ����
		String username = request.getParameter("username");
		String oldpwd = request.getParameter("oldpwd");
		
		
		//����ҵ�����
		UserService uService = new UserService();
		User user = uService.queryUserByName(username);
		
		//�жϾ����������Ƿ�һ��
		if (user.getPassword().equals(oldpwd)) {
			flag = 1;
		} 
		
		//���ؿͻ���
		out.print(flag);
		out.close();
		
	}
	
	/**
	 * �޸�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean flag = false;
		
		//��ǰ̨��ȡ����
		String username = request.getParameter("username");
		String newpwd = request.getParameter("newpwd");
		System.out.println("newpwd:" + newpwd);
		
		//��װʵ����
		User user = new User(username,newpwd);
		//����ҵ�����
		UserService uService = new UserService();
		flag = uService.updatePwd(user);
		
		out.print(flag);
		out.close();
	}
	
	/**
	 * ͨ��username�����û���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findUserByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		PrintWriter out = response.getWriter();
		User user = new User();
		//��ȡ�û���
		String username = request.getParameter("username");
		//����ҵ�����
		UserService service = new UserService();
		user = service.queryUserByName(username);
		//���л�����
		Gson gson = new Gson();
		String jsonStr = gson.toJson(user);
		//System.out.println(jsonStr);
		//���ݷ���ǰ̨
		out.print(jsonStr);	
		out.close();
	}
	
	/**
	 * ͨ��uid�����û���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findUserByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		PrintWriter out = response.getWriter();
		User user = new User();
		//��ȡ�û�id
		String uID_str = request.getParameter("uID");
		int uID = Integer.parseInt(uID_str); 
		//����ҵ�����
		UserService service = new UserService();
		user = service.queryUserById(uID);
		//���л�����
		Gson gson = new Gson();
		String jsonStr = gson.toJson(user);
		//System.out.println(jsonStr);
		//���ݷ���ǰ̨
		out.print(jsonStr);	
		out.close();
	}
	
	
	
	/**
	 * ��ѯ�����û���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		PrintWriter out = response.getWriter();
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
        out.close();
	}

	
	/**
	 * ��ѯ��ע���û�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAllUserCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        // ����service
        UserService service = new UserService();
        int count = service.userCount();
        //д�ؿͻ���
        out.print(count);
        out.close();
	}
	
	
	/**
	 * ��ѯ����ע���û�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAllUserCountByDay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String todayBegin = dateFormat1.format(calendar.getTime());
		String todayEnd = dateFormat2.format(calendar.getTime());
//		String todayBegin = dateFormat1.format(DateUtil.getDayBegin());
//		String todayEnd = dateFormat1.format(DateUtil.getDayEnd());
        int count = allUserCountByDay(todayBegin, todayEnd);
        //д�ؿͻ���
        out.print(count);
        out.close();
	}
	
	public int allUserCountByDay(String date1, String date2) {
		int count = 0;
		// ����service
        UserService service = new UserService();
        count = service.userCountByTime(date1, date2);
		return count;
	}
	
	
	/**
	 * ��¼Servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("auto_login");
		
		PrintWriter out = response.getWriter();
		
		//����ҵ�����
		UserService service = new UserService();
		
		//���õ�¼����
		int result = service.checkLogin(username, password);
//		System.out.println(result);
		//�����½�ɹ�,��session��Χ�ڴ洢�û���
		
		if(result==4){		
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
	
			if (autoLogin != null) {	
				Cookie cookie = new Cookie("AUTO_LOGIN",username+'-'+password);
                //����cookie���ʱ�䲢��·��
                cookie.setMaxAge(60*60*24*3);
                cookie.setPath(request.getContextPath());
                //����Ӧ�����cookie�������ظ������
                response.addCookie(cookie);
			}
		}
		
		out.print(result);
		out.close();
	}
	 
	
	
	/**
	 * ע��Servlet
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
		String signuptime = dateFormat2.format(calendar.getTime());;
		
//		System.out.println(" username:"+username+"\n signuptime"+signuptime);
		//�����ݷ�װ��Userʵ���� ��userû��uid
		User user = new User(username, password, realname, sex, birth, address, tel, email, question, answer, signuptime);
		//���������û�����
		flag = uService.addUser(user);
		
		/*add��Ա����ʼ*/
		//���� ͨ��username����uid��  ��user2��uid��������userһ��
		User user2 = uService.queryUserByName(username);
		Card card = new Card(user2.getuID());
		cService.addCard(card);
		/*add��Ա������*/
		
		out.print(flag);
		out.close();
	}

	
	
	/**
	 * ����û����Ƿ��Ѵ���
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
		out.close();
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.����session
        HttpSession session = request.getSession();
        session.removeAttribute("username");

        //2.����cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie c : cookies) {
                if (c.getName().equals("AUTO_LOGIN")){
                    //����cookie���ʱ��Ϊ0
                    c.setMaxAge(0);
                    c.setPath(request.getContextPath());
                    //��cookie��Ӧ��ǰ̨
                    response.addCookie(c);
                    break;
                }
            }
        }

        //3.��ת��¼ҳ��
        response.sendRedirect(request.getContextPath()+"/login.html");

	}
}
