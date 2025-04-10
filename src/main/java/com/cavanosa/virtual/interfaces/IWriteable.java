package com.cavanosa.virtual.interfaces;

public interface IWriteable<T> {
	public boolean update(T model);
	public boolean create(T model);
}
