package com.itlijunjie.openci.util;

import java.util.List;

public class PageInfo {

	@SuppressWarnings("rawtypes")
	public List list; // 返回记录集
	public int totalCount = 0; // 总记录数
	public int pageCount = 0; // 每页记录数
	public int pageTotalNum = 0; // 总页数
	public int curPage = 0; // 当前页
	public boolean haveNext = false; // 是否有下一页
	public boolean havePre = false; // 是否有上一页

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageTotalNum() {
		return pageTotalNum;
	}

	public void setPageTotalNum(int pageTotalNum) {
		this.pageTotalNum = pageTotalNum;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public boolean isHaveNext() {
		return haveNext;
	}

	public void setHaveNext(boolean haveNext) {
		this.haveNext = haveNext;
	}

	public boolean isHavePre() {
		return havePre;
	}

	public void setHavePre(boolean havePre) {
		this.havePre = havePre;
	}
}