package com.education.util;

import org.springframework.stereotype.Component;

@Component

public class Page{
	private int currentPage=1;//当前页数
	private int totalPage;//总页数
	private int totalCourse;//总记录数
	private int pageSize;//每页记录数
	private int nextPage;//下一页
	private int prePage;//上一页
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		try {		
			if(pageSize>0)
				totalPage = totalCourse%pageSize == 0 ? totalCourse/pageSize : totalCourse/pageSize+1;
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return totalPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getTotalCourse() {
		return totalCourse;
	}
	public void setTotalCourse(int totalCourse) {
		this.totalCourse = totalCourse;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNextPage() {
		if(currentPage < totalPage) {
			nextPage = currentPage+1;
		}else {
			nextPage = currentPage;
		}
		return nextPage;
	}
	public int getPrePage() {
		if(currentPage > 1) {
			prePage = currentPage-1;
		}else {
			prePage = currentPage;
		}
		return prePage;
	}
}
