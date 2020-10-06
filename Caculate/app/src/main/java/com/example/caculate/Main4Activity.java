package com.example.caculate;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main4Activity extends AppCompatActivity {

    public static int select_id=0;
    public static int w=0;
    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        text = (EditText)findViewById(R.id.Schal_Input);
        text.addTextChangedListener(textWatcher);

        Spinner spinner=(Spinner)findViewById(R.id.Schal_Spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Schal_Items,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //下拉列表选择事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_id=position;
                if(w!=0){
                    Change();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            Change();
        }
    };
    public void Long_back_click(View view) {
        Intent intent=new Intent(Main4Activity.this,MainActivity.class);
        startActivity(intent);
    }


    public void Change(){
        w=1;
        EditText editText=findViewById(R.id.Schal_Input);
        String s=editText.getText().toString();

        TextView t1=findViewById(R.id.Schal_wait_2);
        TextView t2=findViewById(R.id.Schal_wait_4);
        TextView t3=findViewById(R.id.Schal_wait_8);
        TextView t4=findViewById(R.id.Schal_wait_10);
        TextView t5=findViewById(R.id.Schal_wait_16);

        switch (select_id){
            case 0: t1.setText(two2(s,2));
                    t2.setText(two2(s,4));
                    t3.setText(two2(s,8));
                    t4.setText(two2(s,10));
                    t5.setText(two2(s,16));
                    break;
            case 1:t1.setText(four2(s,2));
                   t2.setText(four2(s,4));
                   t3.setText(four2(s,8));
                   t4.setText(four2(s,10));
                   t5.setText(four2(s,16));
                   break;
            case 2:t1.setText(eight2(s,2));
                   t2.setText(eight2(s,4));
                   t3.setText(eight2(s,8));
                   t4.setText(eight2(s,10));
                   t5.setText(eight2(s,16));
                   break;
            case 3:t1.setText(sixteen2(s,2));
                   t2.setText(sixteen2(s,4));
                   t3.setText(sixteen2(s,8));
                   t4.setText(sixteen2(s,10));
                   t5.setText(sixteen2(s,16));
                   break;
        }
    }

    public String ten2(String num,int radix){
        String s=Integer.toString(Integer.parseInt(num),radix);
        return s;
    }

    public String two2(String num,int radix){
        //通过中间10进制转化
       Integer a= Integer.parseInt((String) num,2);
       String s=ten2(a+"",radix);
        return s;
    }
    public String four2(String num,int radix){
        //通过中间10进制转化
        int a= Integer.parseInt((String) num,4);
        String s=ten2(a+"",radix);
        return s;
    }

    public String eight2(String num,int radix){
        //通过中间10进制转化
        int a= Integer.parseInt((String) num,8);
        String s=ten2(a+"",radix);
        return s;
    }

    public String sixteen2(String num,int radix){
        //通过中间10进制转化
        int a= Integer.parseInt((String) num,16);
        String s=ten2(a+"",radix);
        return s;
    }


}
