package com.nexgen.layoutmap.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "svg_layouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SVGLayout {

    @Id
    private String layoutId;        // Extracted from SVG or generated
    private String name;            // Original filename
    private String svgFilePath;     // Local or cloud path
    private int plotCount;      // Count of <path> or <polygon> elements
    private String owner;           // Optional: uploader or embedded metadata
    private LocalDateTime uploadedAt;
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
	/**
	 * @return the plotCount
	 */
	public int getplotCount() {
		return plotCount;
	}
	/**
	 * @param plotCount the plotCount to set
	 */
	public void setplotCount(int plotCount) {
		this.plotCount = plotCount;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public void setUploadedAt(LocalDateTime now) {
		this.uploadedAt = now;
		
	}
}