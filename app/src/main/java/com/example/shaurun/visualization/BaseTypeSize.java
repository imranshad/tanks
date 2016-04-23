package com.example.shaurun.visualization;

/**
 * Created by Shaurun on 28.02.2016.
 */
public enum BaseTypeSize {
    FLOAT_SIZE(4),
    SHORT_SIZE(2);

    private int value;

    BaseTypeSize(int size){
        this.value = size;
    }

    public int value(){
        return value;
    }
}
