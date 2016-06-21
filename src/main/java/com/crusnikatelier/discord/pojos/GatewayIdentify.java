package com.crusnikatelier.discord.pojos;

import javax.xml.bind.annotation.XmlElement;

public class GatewayIdentify {
	private String token;
	
	private boolean compress;
	private int largeThreshold;
	private int [] shard;

	public GatewayIdentify(){
		shard = new int [2];
	}

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@XmlElement
	public boolean isCompress() {
		return compress;
	}

	public void setCompress(boolean compress) {
		this.compress = compress;
	}

	@XmlElement(name="large_threshold")
	public int getLargeThreshold() {
		return largeThreshold;
	}

	public void setLargeThreshold(int largeThreshold) {
		this.largeThreshold = largeThreshold;
	}

	@XmlElement
	public int[] getShard() {
		return shard;
	}

	public void setShard(int[] shard) {
		this.shard = shard;
	}
}
