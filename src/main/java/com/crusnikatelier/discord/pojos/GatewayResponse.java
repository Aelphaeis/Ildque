package com.crusnikatelier.discord.pojos;

import javax.xml.bind.annotation.XmlElement;

public class GatewayResponse {
	
	public static final String REQUEST_PATH = "/gateway";

	private String url;

	@XmlElement(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
