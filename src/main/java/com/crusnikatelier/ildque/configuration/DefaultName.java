package com.crusnikatelier.ildque.configuration;

import java.util.Enumeration;

import javax.naming.InvalidNameException;
import javax.naming.Name;

public class DefaultName implements Name{

	private static final long serialVersionUID = 1L;
	
	String name;
	
	public void setValue(String name){
		this.name = name;
	}

	@Override
	public int compareTo(Object obj) {
		return String.valueOf(obj).compareTo(name);
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return name == null;
	}

	@Override
	public Enumeration<String> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String get(int posn) {
		if(!(posn < size())){
			throw new IllegalArgumentException("Position must not exceed size");
		}
		return name;
	}

	@Override
	public Name getPrefix(int posn) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Name getSuffix(int posn) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean startsWith(Name n) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean endsWith(Name n) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Name addAll(Name suffix) throws InvalidNameException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Name addAll(int posn, Name n) throws InvalidNameException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Name add(String comp) throws InvalidNameException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Name add(int posn, String comp) throws InvalidNameException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(int posn) throws InvalidNameException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Object clone(){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return String.valueOf(name);
	}
}
