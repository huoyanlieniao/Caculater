package com.example.caculate;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.caculate.Logic.Input_Logic;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {

        private static final String TAG = "Sun";
        //显示
        private static String Show = "";
        private static String lin="";
        //检校
        private Input_Logic logic = new Input_Logic();

        //中间存储
        private static String Num = "";
        //判断,小数点
        private boolean IsPoint = false;
        //判断，左括号
        private boolean IsBrackets=false;
        //判断，右括号
        private boolean IsBrackets_Right=false;
        //后缀转化栈
        private static Stack<String> stack = new Stack<>();
        //存储
        private ArrayList<String> Save = new ArrayList<>();
        //后缀的转化结果
        private ArrayList<String> Result = new ArrayList<>();
        //计算栈
        private static Stack<Double> caculate = new Stack<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                TextView textView=findViewById(R.id.Show_2);
                textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        }

        //菜单
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater menuInflater = new MenuInflater(this);
                menuInflater.inflate(R.menu.menu, menu);
                return true;
        }
        //菜单点击事件
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle item selection
                switch (item.getItemId()) {
                        case R.id.Help:
                                break;
                        case R.id.LongTra:
                                Log.v(TAG,"点击long");
                                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                                startActivity(intent);
                                break;

                }
                return true;
        }

//按钮点击

        /**
         * @return void
         * @Author sun
         * @Description 点击数字
         * @Date 9:26 2020/9/22
         * @Param [view]
         **/
        public void onClick_Number(View view) {
                if(!IsBrackets_Right) {
                        Button b = (Button) view;
                        String name = getResources().getResourceEntryName(view.getId());
                        String text = b.getText().toString();
                        //Log.v(TAG, "onClick_Number:" +b.getText().toString());
                        //实际存储，更新
                        if (Num != "") {//前面是数字
                                //数字存储
                                Num = Num + text;
                                //更新存储
                                UpdateSave(Num);
                                //计算
                                Trans();
                                Double d = setCaculate();
                                //Log.v(TAG,"res"+d);
                                //显示存储
                                Show = Show + text;
                                lin = d.toString();
                                //Log.v(TAG, Show);
                                setShow_1();


                        } else {//前面是符号
                                //数字存储
                                Num = Num + text;
                                //存储
                                Save.add(Num);
                                //计算
                                Trans();
                                Double d = setCaculate();
                                Log.v(TAG, "res" + d);
                                //显示存储
                                Show = Show + text;
                                lin = d.toString();
                                //Log.v(TAG, Show);

                                setShow_1();
                        }
                        IsPoint = false;
                }

        }


        /**
         * @return void
         * @Author sun
         * @Description 点击功能
         * @Date 9:26 2020/9/22
         * @Param [view]
         **/
        public void onClick_Function(View view) {
                Button b = (Button) view;
                String name = getResources().getResourceEntryName(view.getId());
                switch (name) {
                        case "button_Square":
                                Square();
                                break;
                        case "button_Rooting":
                                Squrt();
                                break;
                        case "button_Fenzhi":
                                Fenzhi();
                                break;
                        case "Button_Sin":
                                Sin();
                                break;
                        case "Button_Cos":
                                Cos();
                                break;

                }

        }


        /**
         * @return void
         * @Author sun
         * @Description 点击计算符号
         * @Date 9:26 2020/9/22
         * @Param [view]
         **/
        public void onClick_Calculation(View view) {
                if (!IsPoint){
                        if(IsBrackets==true){
                              if(logic.Brackets_left_behind(Show.substring(Show.length()-1))){
                                      Button b = (Button) view;
                                      String name = getResources().getResourceEntryName(view.getId());
                                      //Log.v(TAG, "onClick_Calculation:" + name);
                                      Num="";
                                      Show = Show + b.getText().toString();
                                      Save.add(b.getText().toString());
                                      setShow_1();
                              }
                        }else{
                                Button b = (Button) view;
                                String name = getResources().getResourceEntryName(view.getId());
                                //Log.v(TAG, "onClick_Calculation:" + name);
                                Num="";
                                Show = Show + b.getText().toString();
                                Save.add(b.getText().toString());
                                setShow_1();
                        }

                }
        }


        /**
         * @return void
         * @Author sun
         * @Description 点击符号按钮
         * @Date 9:27 2020/9/22
         * @Param [view]
         **/
        public void onClick_Symbol(View view) {

                Button b = (Button) view;
                String name = getResources().getResourceEntryName(view.getId());
                Log.v(TAG, "onClick_Symbol:" + name);
                switch (name) {
                        case "Button_c":
                                cleanShow();
                                break;
                        case "Button_sum":
                                sumShow();
                                break;
                        case "Button_Point":
                                Point();
                                break;
                        case "Button_Del":
                                Del();
                                break;
                        case "Button_Left_Brackets":
                                Left_Brackets(view);
                                break;
                        case "Button_Right_Brackets":
                                Log.v(TAG,"panduan");
                                Right_Brackets(view);
                }


        }

        //展示
        public void setShow_1() {
                TextView textView = findViewById(R.id.Show_1);
                textView.setText(Show+"\n"+lin);
        }
        public void setShow_1_1(String str) {
                TextView textView = findViewById(R.id.Show_1);
                textView.setText(str);
        }
        public void setShow_2(String str) {
                TextView textView = findViewById(R.id.Show_2);
                textView.setText(str);
        }


        //清除
        public void cleanShow() {
                //清除显示
                TextView textView_1 = findViewById(R.id.Show_1);
                textView_1.setText("");
                TextView textView_2 = findViewById(R.id.Show_2);
                textView_2.setText("");
                //清除显示存储
                Show = "";
                //清除数字存储
                Num = "";
                //清除列表
                Save.clear();
                //
                Result.clear();
                //
                stack.clear();
                //
                IsBrackets_Right=false;
                //
                IsBrackets=false;
                //
                IsPoint=false;

        }

        //等于出结果
        public void sumShow() {
                TextView textView = findViewById(R.id.Show_2);
                String str=textView.getText().toString()+"\n"+Show+"\n="+lin;
                textView.setText(str);
                int offset=textView.getLineCount()*textView.getLineHeight();
                 if(offset>textView.getHeight()){
                       textView.scrollTo(0,offset-textView.getHeight());
                 }
                setShow_1_1(lin);
                Num = lin;
                Show="";
                Show=Show+Num;
                Save.clear();
                Save.add(Num);

        }

        //点前数字验证
        public void Point() {
                String s = Show.substring(Show.length() - 1, Show.length());
                Log.v(TAG, s);
                if (!Num.equals("")) {//前面为数字
                        Num=Num+".";
                        Show = Show + ".";
                        setShow_1();
                        IsPoint = true;
                } else {//前面不为数字，不操作

                }
        }

        /**
         * @Author sun
         * @Description 字符串转换为double类型，并进行存储
         * @Date 9:29 2020/9/22
         * @Param [str]
         **/
        public Double Str2dou(String str) {
                Double d;
                d = Double.parseDouble(str);
                return d;
        }

        /**
         * @return void
         * @Author sun
         * @Description 更新数字列表最后一个数字
         * @Date 8:10 2020/9/25
         * @Param [str]
         **/
        public void UpdateSave(String str) {
                Save.remove(Save.size()-1);
                Save.add(str);
        }

        /**
         * @return void
         * @Author sun
         * @Description 删除
         * @Date 8:19 2020/9/25
         * @Param []
         **/
        public void Del() {
                Show=Show.substring(0, Show.length()-1);
                setShow_1();
                String str=Save.get(Save.size());
                str=str.substring(0,str.length());
                Save.remove(Save.size());
                if(str.equals("")){

                }else{
                        Save.add(str);
                }

        }

        /**
         * @return void
         * @Author sun
         * @Description 左括号处理
         * @Date 8:23 2020/9/25
         * @Param []
         **/
        public void Left_Brackets(View view) {
                //是否符合规定
               // Log.v(TAG, Show.substring(Show.length()));
                if(IsBrackets==false) {
                        if (Show==""||logic.Brackets_left_front(Show.substring(Show.length()-1))) {
                                Log.v(TAG, Show.substring(Show.length()));
                                Show = Show + ((Button) view).getText().toString();
                                setShow_1();
                                Save.add("(");
                                IsBrackets = true;
                        }
                }

        }

        public void Right_Brackets(View view){
                Log.v(TAG,"执行");
                if(IsBrackets==false){//无左括号
                        Log.v(TAG,"无");
                }else if(logic.Brackets_empty(Show)){//空括号判断
                        Log.v(TAG,"empty");
                }else if(logic.Brackets_right_front(Show.substring(Show.length()-1))){
                        Show = Show + ((Button) view).getText().toString();
                        setShow_1();
                        Save.add(")");
                        IsBrackets=false;
                        IsBrackets_Right=true;
                }
        }

        /**
         * @return void
         * @Author sun
         * @Description 中缀转后缀
         * @Date 9:03 2020/9/25
         * @Param [s]
         **/

        public void Transbehind(){
                for(String str:Save){
                        //Log.v(TAG,"Save:"+str);
                        if(IsDouble(str)){//遇到数字直接输出
                                Result.add(str);
                        }
                        else{//符号
                                if(stack.isEmpty()){//空直接入栈
                                        stack.push(str);
                                }else if(str.equals("(")){//左括号直接入栈
                                        stack.push(str);
                                }else if(str.equals(")")){//右括号一值弹出直到左括号
                                        while(!stack.peek().equals("(")){
                                                Result.add(stack.pop());
                                        }
                                        stack.pop();
                                }else if(getInt(str)>getInt(stack.peek())){
                                        stack.push(str);
                                }else if(getInt(str)<=getInt(stack.peek())){
                                        while(!stack.isEmpty()&&getInt(str)<=getInt(stack.peek())){
                                                Result.add(stack.pop());
                                        }
                                        stack.add(str);
                                }


                        }
                }
                while(!stack.isEmpty()){
                        Result.add(stack.pop());
                }

        }
        public void Trans() {
                if(IsBrackets){//临时补全括号
                        Save.add(")");
                        Transbehind();
                        Save.remove(Save.size()-1);
                }else{
                      Transbehind();
                }


        }

        /**
         * @Author sun
         * @Description 判断是否是double
         * @Date 9:05 2020/9/25
         * @Param
         * @return
         **/
        public boolean IsDouble(String str) {

               boolean d=true;
               switch(str){
                       case "+": d=false; break;
                       case "-": d=false; break;
                       case "*": d=false; break;
                       case "/": d=false; break;
                       case "(": d=false; break;
                       case ")": d=false; break;
               }

               return d;
        }

        public int getInt(String str){
                int t=0;
                switch(str){
                        case "+": t=1;break;
                        case "-": t=1;break;
                        case "*": t=2;break;
                        case "/": t=2;break;
                }
                return t;
        }

        /**
         * @Author sun
         * @Description 栈计算
         * @Date 10:03 2020/9/27
         * @Param []
         * @return java.lang.Double
         **/
        public Double setCaculate(){

                //清空栈
                caculate.clear();
                if(Result.size()>=3) {
                        for (String str : Result) {
                                Log.v(TAG, "TransRes:" + str);
                                if (IsDouble(str)) {
                                        //如果是数字则进栈
                                        caculate.add(Str2dou(str));
                                } else {
                                        Double b = caculate.pop();
                                        Double a = caculate.pop();
                                        Double c = logic.Calculate(str, a, b);
                                        caculate.add(c);
                                }

                        }

                        Double result = caculate.peek();
                        //再次清空栈
                        caculate.clear();
                        Result.clear();

                        return result;

                }
                String str=Result.get(0);
                caculate.clear();
                Result.clear();
                return Str2dou(str);

        }

        //给Num平方
        public void Square(){
                if(Num!=""){
                        double a=Str2dou(Num)*Str2dou(Num);
                        Num=a+"";
                        UpdateSave(Num);
                        Show="";
                        Trans();
                        Double d = setCaculate();
                        for(String str:Save){
                                Show=Show+str;
                        }
                        lin = d.toString();
                        //Log.v(TAG, Show);
                        setShow_1();
                }
        }

        //开方
        public void Squrt(){
                if(Num!=""){
                        double a=Math.sqrt(Str2dou(Num));
                        Num=a+"";
                        UpdateSave(Num);
                        Show="";
                        Trans();
                        Double d = setCaculate();
                        for(String str:Save){
                                Show=Show+str;
                        }
                        lin = d.toString();
                        //Log.v(TAG, Show);
                        setShow_1();
                }
        }

        //分之
        public void Fenzhi(){
                if(Num!=""){
                        if (Num != "0") {
                                double a=1/(Str2dou(Num));
                                Num=a+"";
                                UpdateSave(Num);
                                Show="";
                                Trans();
                                Double d = setCaculate();
                                for(String str:Save){
                                        Show=Show+str;
                                }
                                lin = d.toString();
                                //Log.v(TAG, Show);
                                setShow_1();
                        }

                }
        }

        //Sin,给lin sin，默认,java sin 参数是弧度所以需要转化
        public void Sin(){
                if(lin!=""){
                        sumShow();
                        Show="Sin"+lin;
                        double a=Math.sin(Str2dou(Num)/180*Math.PI);
                        lin = a+"";
                        setShow_1();

                }
        }

        public void Cos(){
                if(lin!=""){
                        sumShow();
                        Show="Sin"+lin;
                        double a=Math.cos(Str2dou(Num)/180*Math.PI);
                        lin = a+"";
                        setShow_1();

                }
        }
}



