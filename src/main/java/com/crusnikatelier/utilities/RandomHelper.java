package com.crusnikatelier.utilities;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;

public class RandomHelper {

	private final static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	SecureRandom generator;
	
	public RandomHelper(){
		this(new SecureRandom());
	}
	
	public RandomHelper(SecureRandom random){
		generator = random;
	}
	
	public byte getByte(){
		byte [] bArr = new byte[1];
		generator.nextBytes(bArr);
		return bArr[0];
	}
	
	public short getShort(){
		byte [] bArr = new byte[2];
		generator.nextBytes(bArr);
		return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort();
	}
	
	public int getInt(){
		return generator.nextInt();
	}
	
	public int getAbsInt(){
		return Math.abs(getInt());
	}
	
	public long getLong(){
		byte [] bArr = new byte[8];
		generator.nextBytes(bArr);
		return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getLong();
	}
	
	public float getFloat(){
		byte[] bArr = new byte[4];
		generator.nextBytes(bArr);
		return  ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
	
	public double getDouble(){
		byte [] bArr = new byte[8];
		generator.nextBytes(bArr);
		return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getDouble();
	}
	
	public char getChar(){
		byte [] bArr = new byte[2];
		generator.nextBytes(bArr);
		return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getChar();
	}
	
	public String getString(int characterCount){
		return getString(characterCount, chars);
	}
	
	public String getString(int characterCount, String characterSource){
		String s = "";
		for(int i = 0; i < characterCount; i++){
			s += chars.charAt(generator.nextInt(characterSource.length()));
		}
		return s;
	}
}
