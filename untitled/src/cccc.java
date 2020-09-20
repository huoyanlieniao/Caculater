import java.util.Scanner;

/**
 * @author sun
 * @Classname cccc
 * @TODN 要办的事
 * @Date 2020/9/20 9:28
 **/
public class cccc {

    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.println("start:");
        String a=input.nextLine();
        Brackets_complete(a);

    }

    public static boolean Brackets_complete(String a){

        int w=0;
        System.out.println(a);
        for(int i=0;i<a.length();i++){
            String str=a.substring(i, i+1);
            if(str.equals("(")){
                w=w+1;
                System.out.println("(");
            }
            else if(str.equals(")")&&w>0){
                w=w-1;
                System.out.println(")");
            }
            else if(str.equals(")")&&w<=0){
                System.out.println("缺少左括号");
                return false;
            }

        }


        if(w!=0){
            System.out.println("无法闭合");
            return false;
        }else{
            System.out.println("正确");
            return true;
        }
    }

}
