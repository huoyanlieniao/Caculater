package com.example.caculate.Logic;

import android.icu.util.Output;
import android.util.Log;
import androidx.appcompat.widget.DialogTitle;
import com.example.caculate.Model.Model;

import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

/**
 * @author sun
 * @Classname Input_Logic
 * @TODN 输入逻辑检查
 * @Date 2020/9/19 18:56
 **/
public class Input_Logic {
    private static final String TAG = "Sun";






    //运算符系列

    //不可连接
    public static boolean Unconnect(String a,String b){
        if(a.equals("+")||a.equals("-")||a.equals("*")||a.equals("/")){
            if(b.equals("-")||b.equals("(")){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }




    //除法

    /**
     * @Author sun
     * @Description 除法之后不能为0
     * @Date 11:02 2020/9/20
     * @Param [a]
     * @return boolean
     **/
    public static boolean Except_behind(String a){
        if(a.equals("0")){
            return false;
        }else{
            return true;
        }
    }




    //点
    /**
     * @Author sun
     * @Description 点前面是数字
     * @Date 14:10 2020/9/20
     * @Param
     * @return
    **/
    public static boolean Point_front(String a){
        if (!Character.isDigit(a.charAt(0))){
                return false;
        }
        else{
            return true;
        }
    }

    /**
     * @Author sun
     * @Description 点后面也是数字
     * @Date 14:10 2020/9/20
     * @Param
     * @return
    **/
    public static boolean Point_behind(String a){
        if (!Character.isDigit(a.charAt(0))){
            return false;
        }
        else{
            return true;
        }
    }


    //括号系列
    /**
     * @Author sun
     * @Description 输入时调用，判定是否时空括号
     * @Date 9:43 2020/9/20
     * @Param [a]
     * @return boolean
     **/
    public static boolean Brackets_empty(String a) {
        String b=a.substring(a.length()-1,a.length());
        if(b.equals("(")){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * @Author sun
     * @Description 输入时检测调用，检测右括号是否匹配，在方法里如果输入了一个左括号则为true，这里返回true，在将主方法里的判定改为false
     * @Date 9:49 2020/9/20
     * @Param [a]
     * @return boolean
     **/
    public static boolean Brackets_match(boolean a){
        if(a==true){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @Author sun
     * @Description 前一个符号是+-“*”/时输入-号补充括号，这里之后需要再主方法里将布尔值调整
     * @Date 11:25 2020/9/20
     * @Param [a]
     * @return java.lang.String
     **/

    public static String Brackets_supplement(String a){

        String c="";
        if(a.equals(".")||a.equals("(")||a.equals(")")){

        }else{
            c=a+"(-";
        }
        return c;
    }

    /**
     * @Author sun
     * @Description 左括号后面是符号的化只能跟数字
     * @Date 14:23 2020/9/20
     * @Param
     * @return
    **/
    public static boolean Brackets_left_behind(String a){
        if(a.equals("-")){
            return true;
        }
        return false;
    }
}
