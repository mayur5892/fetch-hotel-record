package com.tavisca.travel.core.hotelrecordfetch.model;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	private String height;
	private String width;
	private String horizontalResolution;
	private String verticalResolution;
	private String imageCaption;
	private String url;
	private Attributes attributes;

	public String getHeight() {
		return height;
	}

	public void setHeight(final String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(final String width) {
		this.width = width;
	}

	public String getHorizontalResolution() {
		return horizontalResolution;
	}

	public void setHorizontalResolution(final String horizontalResolution) {
		this.horizontalResolution = horizontalResolution;
	}

	public String getVerticalResolution() {
		return verticalResolution;
	}

	public void setVerticalResolution(final String verticalResolution) {
		this.verticalResolution = verticalResolution;
	}

	public String getImageCaption() {
		return imageCaption;
	}

	public void setImageCaption(final String imageCaption) {
		this.imageCaption = imageCaption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(final Attributes attributes) {
		this.attributes = attributes;
	}

}
