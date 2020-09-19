package com.example.caculate.Logic;

import android.icu.util.Output;
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

    public static void main(String[] args){
        Scanner input =new Scanner(System.in);
        String w=input.next();
        System.out.println(Brackets_complete(w));



    }


    public static boolean Brackets_complete(String a){

        int w=0;

        for(int i=0;i<a.length();i++){
            String str=a.substring(i, i+1);
            if(str=="("){
                w=w+1;
            }
            else if(str==")"&&w>0){
                w=w-1;
            }
            else if(str==")"&&w<=0){
                return false;
            }

        }


        if(w!=0){
            return false;
        }else{
            return true;
        }
    }




}
