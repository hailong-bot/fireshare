package com.hailong.fireshare.exception;

public class NotSameFileExpection extends Exception{
    public NotSameFileExpection(){
        super("File MD5 Different");
    }
}
