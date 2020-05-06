package com.dlnu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlnu.dao.ShoppingCarDao;
import com.dlnu.pojo.Goods;
import com.dlnu.pojo.ShoppingCar;
import com.dlnu.pojo.User;
import com.dlnu.service.GoodsService;
import com.dlnu.service.ShoppingCarService;
import com.dlnu.service.UserService;
import com.dlnu.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/shoppingCar/*")
public class ShoppingCarServlet extends BaseServlet {
	
	/**
	 * ��ӹ��ﳵ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean flag = false;
		
		//��ǰ̨��ȡ����
		String username = request.getParameter("username");
		String gID_str = request.getParameter("gid");
		String sCount_str = request.getParameter("scount");
		
		int gID = Integer.parseInt(gID_str);
		int sCount = Integer.parseInt(sCount_str);
		
		//����ҵ�����
		ShoppingCarService sService = new ShoppingCarService();
		
		//ͨ��username����uid(user)
		UserService uService = new UserService();
		User user = uService.queryUserByName(username);
		
		//�����ݷ�װ��ShoppingCarʵ����
		ShoppingCar car = new ShoppingCar(user.getuID(), gID, sCount);
		
		//������ӵ����ﳵ����
		flag = sService.addShoppingCar(car);

		out.print(flag);
	}
	
	/**
	 * ��ѯ���й��ﳵ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAllShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		PrintWriter out = response.getWriter();
		//1.���ղ���
		String username = request.getParameter("username");	
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
        //ͨ��username����uid(user)
  		UserService uService = new UserService();
  		User user = uService.queryUserByName(username);
        //3. ����service��ѯPageBean����
        ShoppingCarService sService = new ShoppingCarService();
        PageBean<ShoppingCar> pb = sService.queryShoppingCar(currentPage,pageSize,user.getuID());
        //4.���л�����
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonStr = gson.toJson(pb);
        //5. ��pageBean�������л���д�ؿͻ���
        out.print(jsonStr);
	}
	
	/**
	 * ɾ�����ﳵ��һ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delShoppingCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		boolean flag = false;
		
		//��ǰ̨��ȡ����
		String username = request.getParameter("username");
		String gID_str = request.getParameter("gid");
		
		int gID = Integer.parseInt(gID_str);
		
		//����ҵ�����
		ShoppingCarService sService = new ShoppingCarService();
		
		//ͨ��username����uid(user)
		UserService uService = new UserService();
		User user = uService.queryUserByName(username);
		
		//�����ݷ�װ��ShoppingCarʵ����
		ShoppingCar car = new ShoppingCar(user.getuID(), gID);
		
		//������ӵ����ﳵ����
		flag = sService.delShoppingCar(car);

		out.print(flag);
	}

}
