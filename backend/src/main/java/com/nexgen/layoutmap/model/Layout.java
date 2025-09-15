package com.nexgen.layoutmap.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "layouts")
public class Layout {
    @Id
    private String id; // layoutId
    private String name;
    private String svgPath;
    private List<Plot> plots;
    
    
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the svgPath
	 */
	public String getSvgPath() {
		return svgPath;
	}
	/**
	 * @param svgPath the svgPath to set
	 */
	public void setSvgPath(String svgPath) {
		this.svgPath = svgPath;
	}
	/**
	 * @return the plots
	 */
	public List<Plot> getPlots() {
		return plots;
	}
	/**
	 * @param plots the plots to set
	 */
	public void setPlots(List<Plot> plots) {
		this.plots = plots;
	}
	
}

