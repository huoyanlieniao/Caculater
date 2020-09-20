package com.example.caculate;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Sun";
    private static String Show="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    //按钮点击
    public void onClick_Number(View view) {
        Button b=(Button) view;
        String name=getResources().getResourceEntryName(view.getId());
        Log.v(TAG,"onClick_Number:"+name);
        Show=Show+((Button) view).getText().toString();
        setShow();
    }

    public void onClick_Function(View view) {
        Button b=(Button) view;
        String name=getResources().getResourceEntryName(view.getId());
        Log.v(TAG,"onClick_Function:"+name);
        Show=Show+((Button) view).getText().toString();
        setShow();
    }

    public void onClick_Calculation(View view) {
        Button b=(Button) view;
        String name=getResources().getResourceEntryName(view.getId());
        Log.v(TAG,"onClick_Calculation:"+name);
        Show=Show+((Button) view).getText().toString();
        setShow();
    }

    public void onClick_Symbol(View view) {
        Button b=(Button) view;
        String name=getResources().getResourceEntryName(view.getId());
        Log.v(TAG,"onClick_Symbol:"+name);
        Show=Show+((Button) view).getText().toString();
        setShow();
    }

    //展示
    public void setShow(){
        TextView textView=findViewById(R.id.Show);
        textView.setText(Show);
    }


}
