package com.dlnu.service;

import java.util.List;

import com.dlnu.dao.GoodsDao;
import com.dlnu.dao.UserDao;
import com.dlnu.pojo.Goods;
import com.dlnu.pojo.User;
import com.dlnu.util.PageBean;

public class GoodsService {
	private GoodsDao dao = new GoodsDao();
	
	/**
	 * ������Ʒ
	 * @param goods
	 * @return
	 */
	public boolean addGoods(Goods goods){
		int result = dao.insert(goods);
		if (result != -1) { 
			return true; 
		}else {
			return false; 
		}
	}
	
	/**��ѯ������Ʒ��Ϣ
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<Goods> queryGoods(int currentPage, int pageSize){
		//��װpageBean
        PageBean<Goods> pb = new PageBean<Goods>();
        //���õ�ǰҳ��
        pb.setCurrentPage(currentPage);
        //����ÿҳ��ʾ����
        pb.setPageSize(pageSize);
        //�����ܼ�¼��
        int totalCount = dao.findAll();
        pb.setTotalCount(totalCount);
        //���õ�ǰҳ��ʾ�����ݼ���
        int start = (currentPage-1)*pageSize;   //��ʼ�ļ�¼��
        List<Goods> list = dao.query(start,pageSize);
        pb.setList(list);

        //������ҳ�� = �ܼ�¼��/ÿҳ��ʾ����
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);
		return pb;
	}
}
