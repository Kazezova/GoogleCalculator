package com.example.googlecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView resultShow;
    EditText inputNumber;
    Double operand = null;
    String lastOperation = "=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultShow =findViewById(R.id.resultShow);
        inputNumber =findViewById(R.id.inputNumber);
    }


    public void onNumberClick(View view){
        Button button = (Button)view;
        inputNumber.append(button.getText());
        if(lastOperation.equals("C") && operand!=null){
            operand = null;
        }
    }

    public void onOperationClick(View view){
        Button button = (Button)view;
        String op = button.getText().toString();
        String number = inputNumber.getText().toString();
        if(number.length()>0){
            try{
                doOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                inputNumber.setText("");
            }
        }
        lastOperation = op;
        if(lastOperation.equals("C")){
            resultShow.setText("0");
        }
    }

    private void doOperation(Double number, String operation){
        if(operand==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
                case "x":
                    operand *=number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "=":
                    operand=number;
                    break;
            }
        }
        resultShow.setText(operand.toString());
        inputNumber.setText("");
    }
}
