package com.yqdhhh.bean;

import java.util.Map;

public class Customer extends User{
    private Map<String, Integer> byNumber;

    public Map<String, Integer> getByNumber() {
        return byNumber;
    }

    public void setByNumber(Map<String, Integer> byNumber) {
        this.byNumber = byNumber;
    }
}
