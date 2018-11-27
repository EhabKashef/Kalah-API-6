package com.backbase.assessment.dto;

import java.util.Map;

/**
 * Created by Ehab ElKashef
 */
public class GameDTO {
	private int id;
	private String url;
	private Map<Integer, Integer> status;

	public GameDTO() {
	}

	public GameDTO(int id, String url, Map<Integer, Integer> status) {
		this.id = id;
		this.url = url;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, Integer> getStatus() {
		return status;
	}

	public void setStatus(Map<Integer, Integer> status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
