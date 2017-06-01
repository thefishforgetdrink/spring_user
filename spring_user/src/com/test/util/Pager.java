package com.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
  *类的描述:Pager
  *作者:qyb
  *创建日期:2015-4-25 上午11:26:13
  *修改人
  *修改日期
  *修改原因描述
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Pager {
	
	private int curPage = 1;	//当前页数
	//每页显示数据行数
	private int pageRows = 2;	//一页显示多少条记录
	private int totalPages;		//总页数
	private int totalRows;		//总条数
	private List data;			//存放数据
	private Map params;			//存放参数
	
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

	//首页
	public int getFirstPage() {
		return 1;
	}
	//尾页
	public int getLastPage() {
		return this.getTotalPages();
	}
	//下一页
	public int getNextPage() {
		return Math.min(curPage+1,getTotalPages());
	}
	//上一页
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