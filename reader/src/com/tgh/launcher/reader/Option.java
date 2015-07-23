package com.tgh.launcher.reader;

public interface Option<T>{
public T getValue();
public String getStringValue();
public void setValue(T value) throws IllegalArgumentException;
public void setStringValue(String value) throws IllegalArgumentException;
public String getName();
}
