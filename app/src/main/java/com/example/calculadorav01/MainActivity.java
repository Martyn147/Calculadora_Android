package com.example.calculadorav01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Switch modoCientifica;
    private View tablaCientifica;
    private TextView tituloText;
    private View divider1;
    private TextView textView1, textView2;
    private Calculator calculator;
    CheckBox chkMostrar;
    LinearLayout layoutSpinner, layoutRbtns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modoCientifica= findViewById(R.id.modoCientifica);
        tablaCientifica = findViewById(R.id.scientific);
        tituloText = findViewById(R.id.tituloApp);
        divider1 = findViewById(R.id.divider1);

        chkMostrar = findViewById(R.id.extra);
        layoutSpinner = findViewById(R.id.layoutSpinner);
        layoutRbtns = findViewById(R.id.layoutRbtns);
        // ----------------------------   Calculator -----------------------------\\
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        calculator = new Calculator();




        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button_" + i, "id", getPackageName());
            ImageButton button = findViewById(buttonId);
            final String number = String.valueOf(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleInput(number);
                }
            });
        }
        // ----------------------------   Operaciones -----------------------------\\
        ImageButton buttonAdd = findViewById(R.id.button_plus);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("+");
            }
        });
        ImageButton buttonRest = findViewById(R.id.button_minus);
        buttonRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("-");
            }
        });
        ImageButton buttonDivide = findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("÷");
            }
        });

        ImageButton buttonNegative = findViewById(R.id.button_negative);
        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("(-1)");
            }
        });

        ImageButton buttonPercent = findViewById(R.id.button_percent);
        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("%");
            }
        });
        ImageButton buttonEquals = findViewById(R.id.button_equals);
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("=");
            }
        });
        ImageButton buttonMultiply = findViewById(R.id.button_multiply);
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("×");
            }
        });
        ImageButton buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput("C");
            }
        });

        ImageButton buttonDecimal = findViewById(R.id.button_point);
        buttonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleInput(".");
            }
        });











        // ----------------------------   Switch -----------------------------\\

        modoCientifica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                updateTableLayoutVisibility();
            }
        });
        updateTableLayoutVisibility();

        //-------------------------------  Spinner ------------------------------\\
        Spinner spinner = findViewById(R.id.Trigonometricas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.funciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        if (chkMostrar.isChecked()) {
            layoutSpinner.setVisibility(View.VISIBLE);
            layoutRbtns.setVisibility(View.VISIBLE);
        } else {
            layoutSpinner.setVisibility(View.GONE);
            layoutRbtns.setVisibility(View.GONE);
        }

        chkMostrar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutSpinner.setVisibility(View.VISIBLE);
                layoutRbtns.setVisibility(View.VISIBLE);
            } else {
                layoutSpinner.setVisibility(View.GONE);
                layoutRbtns.setVisibility(View.GONE);
            }
        });
    }



    // ----------------------------   Switch -----------------------------\\
    private void updateTableLayoutVisibility() {
        boolean isOrientationHorizontal = getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE;
        boolean isSwitchChecked = modoCientifica.isChecked();
        if (isOrientationHorizontal && isSwitchChecked) {
            tablaCientifica.setVisibility(View.VISIBLE);
            tituloText.setVisibility(View.GONE);
            divider1.setVisibility(View.GONE);
        } else {
            tablaCientifica.setVisibility(View.GONE);
            tituloText.setVisibility(View.VISIBLE);
            divider1.setVisibility(View.VISIBLE);
        }
    }

    // ----------------------------   Calculadora -----------------------------\\
    private void handleInput(String input) {
        String result = calculator.handleInput(input);
        textView1.setText(result);
    }

    //------------------------------- Spinner-----------------------------------\\

}