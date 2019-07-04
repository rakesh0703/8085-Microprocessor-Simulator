package com.example.rakesh.a8085simulator;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    private String hexlocation;
    private String hexvalue;
    private int focus =0;
    private int hl =0;
    private int hv =0;
    private int location=0;
    private short st;
    private String start_add;
    private short data[] = new short[65535];
    EditText editText1;
    EditText editText2;
    private Hashtable<Integer, String> h = new Hashtable<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editshow1();
    }
    public void editshow1(){
        hexlocation="-UPS";
        hexvalue="85";
        EditText editText1 = (EditText) findViewById(R.id.loc);
        EditText editText2 = (EditText) findViewById(R.id.val);
        editText1.setShowSoftInputOnFocus(false);
        editText2.setShowSoftInputOnFocus(false);
        editText1.setHint(hexlocation);
        editText2.setHint(hexvalue);
        hexlocation="";
        hexvalue="";
    }
    @SuppressLint("ClickableViewAccessibility")
    public void editshow(String s){
        editText1 = (EditText) findViewById(R.id.loc);
        editText2 = (EditText) findViewById(R.id.val);
        editText1.setShowSoftInputOnFocus(false);
        editText2.setShowSoftInputOnFocus(false);
        editText1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hexlocation="";
                focus=0;
                return false;
            }
        });
        editText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                show();
                hexvalue="";
                focus=1;
                return false;
            }
        });
        if(focus==0){
            if(hl>4|| hl==0){
                hl=1;
                hexlocation="";
            }
                hexlocation += s;
                hl++;
                editText1.setHint(hexlocation);
        }
        else{
            if(hv>2 || hv==0){
                hv=1;
                hexvalue="";
            }
            hexvalue+=s;
            hv++;
            editText2.setHint(hexvalue);
        }
    }

    private void show() {
        editText2.setHint(Integer.toHexString(data[Integer.parseInt(hexlocation,16)]));
    }

    public void onclick_c(View view) {
        editshow("C");
        }

    public void onclick_d(View view) {
        editshow("D");
    }

    public void onclick_e(View view) {
        editshow("E");
    }

    public void onclick_f(View view) {
        editshow("F");
    }

    public void onclick_eight(View view) {
        editshow("8");
    }

    public void onclick_nine(View view) {
        editshow("9");
    }

    public void onclick_a(View view) {
        editshow("A");
    }

    public void onclick_b(View view) {
        editshow("B");
    }

    public void onclick_four(View view) {
        editshow("4");
    }

    public void onclick_five(View view) {
        editshow("5");
    }

    public void onclick_six(View view) {
        editshow("6");
    }

    public void onclick_seven(View view) {
        editshow("7");
    }

    public void onclick_zero(View view) {
        editshow("0");
    }

    public void onclick_one(View view) {
        editshow("1");
    }

    public void onclick_two(View view) {
        editshow("2");
    }

    public void onclick_three(View view) {
        editshow("3");
    }

    public void onclick_next(View view) {
        if(focus==0){
            editText2.requestFocus();
            focus=1;
            editText2.setHint(Integer.toHexString(data[Integer.parseInt(hexlocation,16)]));
        }
        else if(focus==1 && hexvalue!=""){
            location = Integer.parseInt(hexlocation, 16);
            st = (short) Integer.parseInt(hexvalue, 16);
            data[location] = st;
            location++;
            hexvalue = Integer.toHexString(data[location]);
            hexlocation = Integer.toHexString(location);
            editText1.setHint(hexlocation);
            editText2.setHint(hexvalue);
        }
        else {
            location = Integer.parseInt(hexlocation, 16);
            hexvalue="0";
            st = (short) Integer.parseInt(hexvalue, 16);
            data[location] = st;
            location++;
            hexvalue = Integer.toHexString(data[location]);
            hexlocation = Integer.toHexString(location);
            editText1.setHint(hexlocation);
            editText2.setHint(hexvalue);
        }


    }

    public void onclick_prev(View view) {
        location = Integer.parseInt(hexlocation,16);
        location--;
        hexlocation=Integer.toHexString(location);
        hexvalue=Integer.toHexString(data[location]);
        editText1.setHint(hexlocation);
        editText2.setHint(hexvalue);
    }

    public void onclick_exam_mem(View view) {
        hexlocation="";
        hexvalue="";
        editText1.setHint(hexlocation);
        editText2.setHint(hexvalue);
        editText1.requestFocus();
        focus=0;
    }

    public void onclick_reset(View view) {
        editshow1();
        editText1.requestFocus();
        focus=0;

    }

    public void onclick_go(View view) {
        hexlocation="";
        hexvalue="";
        editText1.setHint(hexlocation);
        editText2.setHint(hexvalue);
//        editText1.requestFocus();
    }

    public void onclick_exec(View view) {
        location = Integer.parseInt(hexlocation,16);
        int a=data[location=location+1];
        int b=data[location=location+2];
        start_add=Integer.toHexString(data[location+4])+Integer.toHexString(data[location+3]);
        location = Integer.parseInt(start_add, 16);
        data[location]=(short)(a+b);
        Log.v("$$$$$$$$$$$$$ ",start_add );
    }

    public void showinst(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instructions");
        builder.setMessage("This App is under development");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}
