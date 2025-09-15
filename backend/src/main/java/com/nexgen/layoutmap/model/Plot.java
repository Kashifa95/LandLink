package com.nexgen.layoutmap.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plots")
	public class Plot {
	    @Id
		private String plotId;
		private String status; // available, sold
	    private String dimension;
	    private Map<String, String> metadata;
	    private String direction;
		private String number;
		private int area;
		
	    /**
		 * @return the area
		 */
		public int getArea() {
			return area;
		}
		/**
		 * @param area 
		 * @param i the area to set
		 */
		public void setArea(int area) {
			this.area = area;
		}
		/**
		 * @return the direction
		 */
		public String getDirection() {
			return direction;
		}
		/**
		 * @param direction the direction to set
		 */
		public void setDirection(String direction) {
			this.direction = direction;
		}
		/**
		 * @return the number
		 */
		public String getNumber() {
			return number;
		}
		/**
		 * @param number the number to set
		 */
		public void setNumber(String number) {
			this.number = number;
		}
	
	    
	    /**
		 * @return the id
		 */
		public String getplotId() {
			return plotId;
		}
		/**
		 * @param id the id to set
		 */
		public void setPlotId(String id) {
			this.plotId = id;
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
		public String getDimension() {
			return dimension;
		}
		/**
		 * @param dimensions the dimensions to set
		 */
		public void setDimension(String dimension) {
			this.dimension = dimension;
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
	
	}

