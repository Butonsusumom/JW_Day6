package com.tsybulko.model.bookproject.util;


public class IdGenerator {

    private static final IdGenerator instance = new IdGenerator();

    private int  id;

    public IdGenerator(){
        id = 1;
    }

    public static IdGenerator getInstance(){
        return instance;
    }

    public int generateNewId(){
        return id++;
    }
}