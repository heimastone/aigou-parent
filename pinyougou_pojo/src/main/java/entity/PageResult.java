package entity;

import java.io.Serializable;
import java.util.List;
public class PageResult implements Serializable {
	
	private long total;
	public long getTotal() {
		return total;
	}
	public PageResult(long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	private List  rows;

}
