package com.example.caculate.Model;

/**
 * @Author sun
 * @Description 类型，包括数字，运算符,符号和方法
 * @Date 19:33 2020/9/19
 * @Param
 * @return
**/
public enum Model {

    Number,Calculation,function,symbol;



    private String kind;
    private String toshow;

    Model(String kind, String toshow) {
        this.kind = kind;
        this.toshow = toshow;
    }

    Model() {

    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getToshow() {
        return toshow;
    }

    public void setToshow(String toshow) {
        this.toshow = toshow;
    }

}
