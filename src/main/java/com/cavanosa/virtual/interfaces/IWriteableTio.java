package com.cavanosa.virtual.interfaces;

public interface IWriteableTio<T, S> {
	public boolean update(T model);
	public S create(T model);
}
