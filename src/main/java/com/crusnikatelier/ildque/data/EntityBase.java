package com.crusnikatelier.ildque.data;

import com.crusnikatelier.utilities.MarshalHelper;

public class EntityBase {
	
	@Override
	public String toString(){
		try{
			return MarshalHelper.marshallJson(this);
		}
		catch(Exception e){
			return super.toString();
		}
	}
}
