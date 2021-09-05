package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumberDecimal;
    TextView textViewseekBar, textViewTip, textViewTotal, textViewtotalperson;
    RadioGroup radioGroupTip, radioGroupSplit;
    Button buttonClear;
    SeekBar seekBar;
    double input = 0, tipAmount = 0, totalAmount = 0, totalTotalPersonAmount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumberDecimal = findViewById(R.id.editTextNumberBill);
        textViewseekBar = findViewById(R.id.textViewSeekBar);
        textViewTip = findViewById(R.id.textViewTipAmount);
        textViewTotal = findViewById(R.id.textViewTotalAmount);
        textViewtotalperson = findViewById(R.id.textViewTotalPersonAmount);
        radioGroupTip = findViewById(R.id.radioGroupTip);
        radioGroupSplit = findViewById(R.id.radiogroupSplit);
        buttonClear = findViewById(R.id.buttonClear);
        seekBar = findViewById(R.id.seekBar);



        radioGroupTip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                calculate();
            }
        });
        radioGroupSplit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                calculate();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                calculate();
                seekBar.setEnabled(true);
                textViewseekBar.setText(progress +"%");



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        editTextNumberDecimal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNumberDecimal.setText("");
                radioGroupTip.check(R.id.radioButton10Percent);
                radioGroupSplit.check(R.id.radioButtonOne);
                seekBar.setProgress(40);
                textViewTotal.setText("$0.00");
                textViewTip.setText("$0.00");
                textViewseekBar.setText("40%");
                textViewtotalperson.setText("$0.00");

            }
        });


    }
    public void calculate(){
        input = getInput();
        tipAmount = Tip() * input;
        textViewTip.setText("$" + String.format("%.2f", tipAmount));
        totalAmount = tipAmount + getInput();
        textViewTotal.setText("$" + String.format("%.2f", totalAmount));
        totalTotalPersonAmount = totalAmount/Split();
        textViewtotalperson.setText("$" + String.format("%.2f", totalTotalPersonAmount));

    }

    public int Split(){
        if(radioGroupSplit.getCheckedRadioButtonId() == R.id.radioButtonOne)
            return 1;
        else if(radioGroupSplit.getCheckedRadioButtonId() == R.id.radioButtonTwo)
            return 2;
        else if(radioGroupSplit.getCheckedRadioButtonId() == R.id.radioButtonThree)
            return 3;
        else{
            return 4;
        }


    }

    public double Tip() {
        if(radioGroupTip.getCheckedRadioButtonId()== R.id.radioButton10Percent){
            return 0.10;
        }
         else if(radioGroupTip.getCheckedRadioButtonId()== R.id.radioButton15Percent){
            return 0.15;
        }
       else if(radioGroupTip.getCheckedRadioButtonId()== R.id.radioButton18Percent){
            return 0.18;
        }
        else{
           double i = (double) seekBar.getProgress()/100;
           return i;
        }


    }


        public double getInput() {
            try {
                String str = (editTextNumberDecimal.getText().toString());
                if (str.isEmpty()) return 0.0;
                input = Double.parseDouble(str);
                return input;


            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Enter A Value", Toast.LENGTH_SHORT).show();
                return 0.0;
            }


        }
    }
