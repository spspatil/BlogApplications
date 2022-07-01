package com.BikkadIt.BlogApp.Payloads;

import java.util.List;

public class PostResponse {
	
	private List<PostDTO> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Long totalElement;
	
	private Integer totalPages;
	
	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean lastPage;

	public List<PostDTO> getContent() {
		return content;
	}

	public void setContent(List<PostDTO> content) {
		this.content = content;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(Long totalElement) {
		this.totalElement = totalElement;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

}
