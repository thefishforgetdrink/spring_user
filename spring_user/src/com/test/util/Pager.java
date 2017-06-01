package com.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
  *�������:Pager
  *����:qyb
  *��������:2015-4-25 ����11:26:13
  *�޸���
  *�޸�����
  *�޸�ԭ������
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Pager {
	
	private int curPage = 1;	//��ǰҳ��
	//ÿҳ��ʾ��������
	private int pageRows = 2;	//һҳ��ʾ��������¼
	private int totalPages;		//��ҳ��
	private int totalRows;		//������
	private List data;			//�������
	private Map params;			//��Ų���
	
	public Pager() {
		data=new ArrayList();
		params=new HashMap();
	}
	
	public Pager(int pageNum, int rowNumPerPage, int count,List list){
		this.curPage 	= pageNum;
		this.pageRows	= rowNumPerPage;
		this.totalRows	=count;
		data = list;
	}

	public int getCurPage() {
		return this.curPage;
	}

	public void setCurPage(int curPage) {
		if(curPage<1)
			curPage=1;
		this.curPage = curPage;		
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		if(totalPages<1)
			totalPages=1;
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	//��ҳ
	public int getFirstPage() {
		return 1;
	}
	//βҳ
	public int getLastPage() {
		return this.getTotalPages();
	}
	//��һҳ
	public int getNextPage() {
		return Math.min(curPage+1,getTotalPages());
	}
	//��һҳ
	public int getPreviousPage() {
		return Math.max(curPage-1,1);
	}

	public int getFirstRow() {
		return (getCurPage() - 1) * this.pageRows;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
	
	public Object removeParam(Object key){
		return this.params.remove(key);
	}
	
	public Object putParam(Object key,Object value){
		return this.params.put(key, value);
	}
	
	public void cleanParams(){
		this.params.clear();		
	}
}