package com.dlnu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlnu.dao.PurchaseHistoryDao;
import com.dlnu.pojo.Goods;
import com.dlnu.pojo.PurchaseHistory;
import com.dlnu.pojo.ShoppingCar;
import com.dlnu.pojo.User;
import com.dlnu.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PurchaseHistoryService {
	
	PurchaseHistoryDao dao = new PurchaseHistoryDao();
	
	/**
	 * ������Ѽ�¼
	 * @param ph
	 * @return
	 */
	public boolean addPurchaseHistory(PurchaseHistory ph) {
		return dao.insert(ph);
	}
	
	/**
	 * ��ѯ�������Ѽ�¼
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<PurchaseHistory> query(int currentPage, int pageSize, int uid){
		//��װpageBean
        PageBean<PurchaseHistory> pb = new PageBean<PurchaseHistory>();
        //���õ�ǰҳ��
        pb.setCurrentPage(currentPage);
        //����ÿҳ��ʾ����
        pb.setPageSize(pageSize);
        //�����ܼ�¼��
        int totalCount = dao.findAll(uid);
        pb.setTotalCount(totalCount);
        //���õ�ǰҳ��ʾ�����ݼ���
        int start = (currentPage-1)*pageSize;   //��ʼ�ļ�¼��
        List<PurchaseHistory> list = dao.query(start,pageSize,uid);
        pb.setList(list);

        //������ҳ�� = �ܼ�¼��/ÿҳ��ʾ����
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);
		return pb;
	}
	

}
