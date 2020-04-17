package com.dlnu.util;

import java.util.List;
/*
* ��ҳ����
* */
public class PageBean<T> {
    private int totalCount;//�ܼ�¼��
    private int totalPage;//��ҳ��
    private int currentPage;//��ǰҳ��
    private int pageSize;//ÿҳ��ʾ������
    private int firstPage=1; //��ҳ
    private int prePage;//��һҳ
    private int nextPage;//��һҳ

    private List<T> list;//ÿҳ��ʾ�����ݼ���

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
//        totalPage = totalCount % pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        prePage = this.currentPage - 1;
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        nextPage = this.currentPage + 1;
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

	@Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", totalPage=" + totalPage + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", firstPage=" + firstPage + ", prePage=" + prePage + ", nextPage="
				+ nextPage + ", list=" + list + "]";
	}
    
}
