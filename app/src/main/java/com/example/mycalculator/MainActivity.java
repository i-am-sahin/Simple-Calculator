package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;

import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackclose;
    MaterialButton buttonDivide,buttonMultiply,buttonAddition,buttonSubtraction,buttonEqualls;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAc,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignBtn(buttonC,R.id.button_C);
        assignBtn(buttonBrackOpen,R.id.button_open_bracket);
        assignBtn(buttonBrackclose,R.id.button_close_bracket);
        assignBtn(buttonDivide,R.id.button_divide);
        assignBtn(buttonMultiply,R.id.button_multiply);
        assignBtn(buttonAddition,R.id.button_addition);
        assignBtn(buttonSubtraction,R.id.button_subtraction);
        assignBtn(buttonEqualls,R.id.button_equells);
        assignBtn(button0,R.id.button_0);
        assignBtn(button1,R.id.button_1);
        assignBtn(button2,R.id.button_2);
        assignBtn(button3,R.id.button_3);
        assignBtn(button4,R.id.button_4);
        assignBtn(button5,R.id.button_5);
        assignBtn(button6,R.id.button_6);
        assignBtn(button7,R.id.button_7);
        assignBtn(button8,R.id.button_8);
        assignBtn(button9,R.id.button_9);
        assignBtn(buttonAc,R.id.button_ac);
        assignBtn(buttonDot,R.id.button_dot);
    }

    void assignBtn(MaterialButton btn,int id){
        btn =  findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
//        solutionTv.setText(buttonText);


        String DataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")){
            DataToCalculate = DataToCalculate.substring(0,DataToCalculate.length()-1);
        }
        else{
            DataToCalculate = DataToCalculate + buttonText;
        }

        solutionTv.setText(DataToCalculate);


        String finalResult = getResult(DataToCalculate);
        if(!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }
    }



    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return  "Error";
        }
    }
}