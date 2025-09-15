package com.nexgen.layoutmap.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PlotDTO {
    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the dimensions
	 */
	public String getDimensions() {
		return dimensions;
	}
	/**
	 * @param dimensions the dimensions to set
	 */
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	/**
	 * @return the metadata
	 */
	public Map<String, String> getMetadata() {
		return metadata;
	}
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	private String id;
    private String status;
    private String dimensions;
    private Map<String, String> metadata;
    private String layoutname;
	
    
    
}