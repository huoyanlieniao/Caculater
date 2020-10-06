package com.example.caculate;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class Main2Activity extends AppCompatActivity {

    public static int select_id=0;
    public static int w=0;
    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (EditText)findViewById(R.id.Long_Input);
        text.addTextChangedListener(textWatcher);

        Spinner spinner=(Spinner)findViewById(R.id.Long_Spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Long_Items,android.R.layout.simple_spinner_dropdown_item);
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
        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }


    public void Change(){
        w=1;
        EditText editText=findViewById(R.id.Long_Input);
        double b=Double.valueOf(editText.getText()+"");

        TextView t1=findViewById(R.id.wait_mm);
        double b1=Math.pow(10,select_id);
        t1.setText(b*b1+"");

        TextView t2=findViewById(R.id.wait_cm);
        double b2=Math.pow(10,select_id-1);
        t2.setText(b*b2+"");

        TextView t3=findViewById(R.id.wait_dm);
        double b3=Math.pow(10,select_id-2);
        t3.setText(b*b3+"");

        TextView t4=findViewById(R.id.wait_m);
        double b4=Math.pow(10,select_id-3);
        t4.setText(b*b4+"");

        TextView t5=findViewById(R.id.wait_km);
        double b5=Math.pow(10,select_id-4);
        t5.setText(b*b5+"");
    }

}

