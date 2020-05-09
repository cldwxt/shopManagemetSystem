package com.dlnu.service;

import java.util.List;

import com.dlnu.dao.ShoppingCarDao;
import com.dlnu.pojo.ShoppingCar;
import com.dlnu.pojo.User;
import com.dlnu.util.PageBean;

public class ShoppingCarService {
	
	ShoppingCarDao dao = new ShoppingCarDao();
	
	/**
	 * 	��ӵ����ﳵ
	 * @param shoppingcar
	 * @return
	 */
	public boolean addShoppingCar(ShoppingCar shoppingcar){
		
		if (dao.queryByIDs(shoppingcar) == null) {
			//���ﳵ���޴���Ʒ��Ϣ,�������Ϣ
			return dao.insert(shoppingcar);
	
		} else {
			//���ﳵ�����д���Ʒ��������Ϣ
			return dao.updateScountByGid(shoppingcar);
		}
		
	}
	
	/**
	 * 	ɾ�����ﳵһ����Ϣ
	 * @param shoppingcar
	 * @return
	 */
	public boolean delShoppingCar(int uid, int gid){
		return dao.delete(uid, gid);
		
	}
	
	/**��ѯ���й��ﳵ��Ϣ
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean<ShoppingCar> queryShoppingCarByUid(int currentPage, int pageSize, int uid){
		//��װpageBean
        PageBean<ShoppingCar> pb = new PageBean<ShoppingCar>();
        //���õ�ǰҳ��
        pb.setCurrentPage(currentPage);
        //����ÿҳ��ʾ����
        pb.setPageSize(pageSize);
        //�����ܼ�¼��
        int totalCount = dao.findAll(uid);
        pb.setTotalCount(totalCount);
        //���õ�ǰҳ��ʾ�����ݼ���
        int start = (currentPage-1)*pageSize;   //��ʼ�ļ�¼��
        List<ShoppingCar> list = dao.query(start,pageSize,uid);
        pb.setList(list);

        //������ҳ�� = �ܼ�¼��/ÿҳ��ʾ����
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);
		return pb;
	}
	
	public ShoppingCar queryOneShoppingCarByIDs(ShoppingCar shoppingcar){
		return dao.queryByIDs(shoppingcar);
		
	}
	
}
