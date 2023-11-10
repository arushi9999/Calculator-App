package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    Button b2;
    Button b3;
    Button plus;
    Button b4;
    Button b5;
    Button b6;
    Button minus;
    Button b7;
    Button b8;
    Button b9;
    Button mult;
    Button clear;
    Button b0;
    Button equals;
    Button div;
    TextView t;
    String err = "error";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.id_num1);
        b1.setOnClickListener(this);


        b2=findViewById(R.id.id_num2);
        b2.setOnClickListener(this);

        b3=findViewById(R.id.id_num3);
        b3.setOnClickListener(this);

        plus=findViewById(R.id.id_plus);
        plus.setOnClickListener(this);


        b4=findViewById(R.id.id_num4);
        b4.setOnClickListener(this);

        b5=findViewById(R.id.id_num5);
        b5.setOnClickListener(this);

        b6=findViewById(R.id.id_6);
        b6.setOnClickListener(this);

        minus=findViewById(R.id.id_minus);
        minus.setOnClickListener(this);

        b7=findViewById(R.id.id_num7);
        b7.setOnClickListener(this);

        b8=findViewById(R.id.id_num8);
        b8.setOnClickListener(this);

        b9=findViewById(R.id.id_num9);
        b9.setOnClickListener(this);

        mult=findViewById(R.id.id_mult);
        mult.setOnClickListener(this);

        clear=findViewById(R.id.id_clear);
        clear.setOnClickListener(this);

        b0=findViewById(R.id.id_num0);
        b0.setOnClickListener(this);

        equals=findViewById(R.id.id_equals);
        equals.setOnClickListener(this);

        div=findViewById(R.id.id_div);
        div.setOnClickListener(this);

        t=findViewById(R.id.id_num);
        t.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.id_num1){
            t.setText(t.getText()+"1");
        }

        if(v.getId()==R.id.id_num2){
            t.setText(t.getText()+"2");
        }
        if(v.getId()==R.id.id_num3){
            t.setText(t.getText()+"3");
        }
        if(v.getId()==R.id.id_plus){
            t.setText(t.getText()+"+");
        }
        if(v.getId()==R.id.id_num4){
            t.setText(t.getText()+"4");
        }
        if(v.getId()==R.id.id_num5){
            t.setText(t.getText()+"5");
        }
        if(v.getId()==R.id.id_6){
            t.setText(t.getText()+"6");
        }
        if(v.getId()==R.id.id_minus){
            t.setText(t.getText()+"-");
        }
        if(v.getId()==R.id.id_num7){
            t.setText(t.getText()+"7");
        }
        if(v.getId()==R.id.id_num8){
            t.setText(t.getText()+"8");
        }
        if(v.getId()==R.id.id_num9){
            t.setText(t.getText()+"9");
        }
        if(v.getId()==R.id.id_mult){
            t.setText(t.getText()+"*");
        }
        if(v.getId()==R.id.id_clear){
            t.setText("");
        }
        if(v.getId()==R.id.id_num0){
            t.setText(t.getText()+"0");
        }
        if(v.getId()==R.id.id_equals){
            String result = operations( (String) (t.getText()));
            t.setText(result);
        }
        if(v.getId()==R.id.id_div){
            t.setText(t.getText()+"/");
        }

    }//onClick end

    public String operations (String inputText){
        // check for error cases where operators are in end
        if(inputText == null || inputText.startsWith("+") || inputText.startsWith("-") || inputText.startsWith("*") || inputText.startsWith("/")) {
            return err;
        }

        if(inputText == null || inputText.endsWith("+") || inputText.endsWith("-") || inputText.endsWith("*") || inputText.endsWith("/")) {
            return err;
        }

        try { // catch exceptions due to formatting or any other exceptions
            // create a list of all the terms in the expression using Stringtokenizer

            StringTokenizer calcTokenizer = new StringTokenizer(inputText, "+//-//*///", true);
            ArrayList<String> calcTerms = new ArrayList<String>();

            while (calcTokenizer.hasMoreTokens()) {
                calcTerms.add(calcTokenizer.nextToken());
            }
            // iterate over list - first for '*' and '/' nd if you find that operator
            // perform that operation on before and after element. remove the next element
            // replace current element with result and decrease count as the list is now reduced by 1
            for (int i = 0; i < calcTerms.size(); i++) {
                String term = calcTerms.get(i);
                if (term.equalsIgnoreCase("*")
                        || term.equalsIgnoreCase("/")) {
                    double op1 = Double.parseDouble(calcTerms.get(i - 1));
                    double op2 = Double.parseDouble(calcTerms.get(i + 1));
                    double result = 0.0;
                    if (term.equals("*")) {
                        result = op1 * op2;
                    } else if (term.equals("/")) {
                        if(op2==0 || op2==0.0){
                            return err;
                        }
                        result = op1 / op2;
                    }
                    calcTerms.set(i, String.valueOf(result));
                    calcTerms.remove(i + 1);
                    calcTerms.remove(i - 1);
                    i--;
                }
            }

            //now second pass on the same list for '+' and '-'

            for (int i = 0; i < calcTerms.size(); i++) {
                String term = calcTerms.get(i);
                if (term.equalsIgnoreCase("+")
                        || term.equalsIgnoreCase("-")) {
                    double op1 = Double.parseDouble(calcTerms.get(i - 1));
                    double op2 = Double.parseDouble(calcTerms.get(i + 1));
                    double result = 0.0;
                    if (term.equals("+")) {
                        result = op1 + op2;
                    } else if (term.equals("-")) {
                        result = op1 - op2;
                    }
                    calcTerms.set(i, String.valueOf(result));
                    calcTerms.remove(i + 1);
                    calcTerms.remove(i - 1);
                    i--;
                }
            }
            // final list containing teh result
            if(calcTerms.size() == 1) {
                String finalRes = calcTerms.get(0);
                double check = Double.parseDouble(finalRes);
                if (check == (int) check ) {
                    return String.valueOf((int) check);
                } else {
                    return finalRes;
                }
            } else {
                return err;
            }
        } catch(Exception e) {
            return err; // if any exception return "Err";
        }
    }

}//class end