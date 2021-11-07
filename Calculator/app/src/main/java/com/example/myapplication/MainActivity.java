package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int mCount = 0;
    private TextView input;
    private TextView output;
    private StringBuilder stringBuilder;
    private String param1;
    private String param2;
    private String operator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringBuilder=new StringBuilder();
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        output=findViewById(R.id.output);
    }
    public void del(View view){
        if (stringBuilder.length()>0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            input.setText(stringBuilder.toString());
        }
    }
    public void buttonC(View view){
        stringBuilder.setLength(0);
        input.setText(stringBuilder.toString());
    }
    public void addNumber(View view) {
        switch (view.getId()){
            case R.id.num0:
                stringBuilder.append(0);
                break;
            case R.id.num1:
                stringBuilder.append(1);
                break;
            case R.id.num2:
                stringBuilder.append(2);
                break;
            case R.id.num3:
                stringBuilder.append(3);
                break;
            case R.id.num4:
                stringBuilder.append(4);
                break;
            case R.id.num5:
                stringBuilder.append(5);
                break;
            case R.id.num6:
                stringBuilder.append(6);
                break;
            case R.id.num7:
                stringBuilder.append(7);
                break;
            case R.id.num8:
                stringBuilder.append(8);
                break;
            case R.id.num9:
                stringBuilder.append(9);
                break;
        }
        input.setText(stringBuilder.toString());
    }
    public void addSymbol(View view){
        if(view.getId()!=R.id.dot) {
            param1=stringBuilder.toString();
        }
        switch (view.getId()){
            case R.id.dot: {
                stringBuilder.append(".");
                operator=".";
            }
                break;
            case R.id.multiply: {
                stringBuilder.append("x");
                operator="x";
            }
                break;
            case R.id.divide: {
                stringBuilder.append("÷");
                operator="÷";
            }
                break;
            case R.id.plus: {
                stringBuilder.append("+");
                operator="+";
            }
                break;
            case R.id.minus: {
                stringBuilder.append("-");
                operator="-";
            }
                break;
            case R.id.percent: {
                stringBuilder.append("%");
                operator="%";
            }
                break;
            case R.id.sqrt: {
                stringBuilder.append("√");
                operator="√";
            }
                break;
            case R.id.exp: {
                stringBuilder.append("^");
                operator="exp";
            }
                break;
            case R.id.frac: {
                {
                    stringBuilder.append("1/");
                    operator="1/";
                }
                break;
            }
        }
        input.setText(stringBuilder.toString());
    }
    public void caculate(View view){
        String text=stringBuilder.toString();
        if(Pattern.matches("[-]?[0-9]+[\\.]*[0-9]*[x÷+\\-^][-]?[0-9]+[\\.]*[0-9]*",text)){
            String result="0";
            if(text.contains("+")){
                text=text.replace("+","_");

                if(text.contains(".")){
                    result=String.valueOf(Math.floor((Double.valueOf(text.split("_")[0])+Double.valueOf(text.split("_")[1])) * 100000000) / 100000000);
                }else {
                    result=String.valueOf(Integer.valueOf(text.split("_")[0])+Integer.valueOf(text.split("_")[1]));
                }
            }else if(text.contains("-")){
                String p1,p2;
                if(text.contains("--")){
                    p2=text.split("--")[1];
                    p1=text.split("--")[0];
                    if(text.contains("."))
                        result=String.valueOf(Math.floor((Double.valueOf(p1)+Double.valueOf(p2)) * 100000000) / 100000000);
                    else
                        result=String.valueOf(Integer.valueOf(p1)+Integer.valueOf(p2));
                 }else {
                    if(text.substring(0,1).equals("-")){
                        text=text.substring(1,text.length());
                        text=text.replace("-","_");
                        if(text.contains(".")){
                            result=String.valueOf(Math.floor((-Double.valueOf(text.split("_")[0])-Double.valueOf(text.split("_")[1])) * 100000000) / 100000000);
                        }else {
                            result=String.valueOf(-Integer.valueOf(text.split("_")[0])-Integer.valueOf(text.split("_")[1]));
                        }
                    }else {
                        text=text.replace("-","_");
                        if(text.contains(".")){
                            result=String.valueOf(Math.floor((Double.valueOf(text.split("_")[0])-Double.valueOf(text.split("_")[1])) * 100000000) / 100000000);
                        }else {
                            result=String.valueOf(Integer.valueOf(text.split("_")[0])-Integer.valueOf(text.split("_")[1]));
                        }
                    }
                }
            }
            else if(text.contains("x")){
                text=text.replace("x","_");
                if(text.contains(".")){
                    result=String.valueOf(Math.floor((Double.valueOf(text.split("_")[0])*Double.valueOf(text.split("_")[1])) * 100000000) / 100000000);
                }else {
                    result=String.valueOf(Integer.valueOf(text.split("_")[0])*Integer.valueOf(text.split("_")[1]));
                }
            }else if(text.contains("÷")){
                text=text.replace("÷","_");
                result=String.valueOf(Math.floor((Double.valueOf(text.split("_")[0])/Double.valueOf(text.split("_")[1])) * 100000000) / 100000000);
            }
            else if(text.contains("^")){
                text=text.replace("^","_");
                result=String.valueOf(Math.floor(Math.pow(Double.valueOf(text.split("_")[0]),Double.valueOf(text.split("_")[1]))));
            }
            output.setText(result);
        }
    }

}