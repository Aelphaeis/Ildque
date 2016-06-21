package com.crusnikatelier.discord.pojos;

import javax.xml.bind.annotation.XmlElement;

public class Operation {
	
	private int op;
	
	@XmlElement
	public int getOp() {
		return op;
	}
	public void setOp(int op) {
		this.op = op;
	}

}
