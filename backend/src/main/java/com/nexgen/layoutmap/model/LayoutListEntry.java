package com.nexgen.layoutmap.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "layoutLists")
public class LayoutListEntry {
    @Id
    private String layoutId;
    private String name;
    private String svgFilePath;
    /**
	 * @return the layoutId
	 */
	public String getLayoutId() {
		return layoutId;
	}
	/**
	 * @param layoutId the layoutId to set
	 */
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
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
	 * @return the svgFilePath
	 */
	public String getSvgFilePath() {
		return svgFilePath;
	}
	/**
	 * @param svgFilePath the svgFilePath to set
	 */
	public void setSvgFilePath(String svgFilePath) {
		this.svgFilePath = svgFilePath;
	}
	
}
