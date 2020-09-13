package cn.tedu.db.common.pojo;

import java.util.List;

/**
 * 用于封装分页查询结果的VO类
 * @param <T>
 */
public class PageObjectVO<T> {
	
	private Integer rowCount; // 总数据条数
	private Integer pageCount; // 总页数
	private Integer currentPage; // 当前页编号
	private Integer pageSize; // 每页数据条数
	private List<T> pageRecords; //当前页数据
	
	public PageObjectVO() {
		
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getPageRecords() {
		return pageRecords;
	}

	public void setPageRecords(List<T> pageRecords) {
		this.pageRecords = pageRecords;
	}

	@Override
	public String toString() {
		return "PageObjectVO [rowCount=" + rowCount + ", pageCount=" + pageCount + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", pageRecords=" + pageRecords + "]";
	}

}
