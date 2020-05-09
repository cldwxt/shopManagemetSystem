package com.dlnu.service;

import java.util.List;

import com.dlnu.dao.CardDao;
import com.dlnu.pojo.Card;
import com.dlnu.util.PageBean;

public class CardService {
	private CardDao dao = new CardDao();
	
	/**
	 */
	public boolean updatePoint(int uid, int point) {
		return dao.updatePoint(uid, point);
	}
	
	/**
	 * ͨ��uid���һ�Ա����Ϣ
	 * @param uid
	 * @return
	 */
	public Card queryCardByUid(Integer uid){
		return dao.queryByUid(uid);
	}
	
	/**
	 * ��ӻ�Ա��
	 * @param card
	 */
	public void addCard(Card card){
		dao.insert(card);
	}
	
	/**
	 * ��ѯ���л�Ա����Ϣ
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<Card> queryCard(int currentPage, int pageSize){
		//��װpageBean
        PageBean<Card> pb = new PageBean<Card>();
        //���õ�ǰҳ��
        pb.setCurrentPage(currentPage);
        //����ÿҳ��ʾ����
        pb.setPageSize(pageSize);
        //�����ܼ�¼��
        int totalCount = dao.findAll();
        pb.setTotalCount(totalCount);
        //���õ�ǰҳ��ʾ�����ݼ���
        int start = (currentPage-1)*pageSize;   //��ʼ�ļ�¼��
        List<Card> list = dao.query(start,pageSize);
        pb.setList(list);

        //������ҳ�� = �ܼ�¼��/ÿҳ��ʾ����
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);
		return pb;
	}
}
