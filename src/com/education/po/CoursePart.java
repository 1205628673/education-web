package com.education.po;

public class CoursePart {
	private Integer pid;//章节id
	private Integer id;//课程id
	private String partname;//课程章节名
	private String html;//章节html
	public Integer getId() {
		return id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPartname() {
		return partname;
	}
	public void setPartname(String partname) {
		this.partname = partname;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	
}
