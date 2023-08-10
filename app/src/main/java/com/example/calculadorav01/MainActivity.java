package com.example.calculadorav01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Switch modoCientifica;
    private View tablaCientifica;
    private TextView tituloText;
    private View divider1;
    public TextView resultado, resultadoTrigo;
    public StringBuilder currentNumber;

    public ImageButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0,button_point;
    public ImageButton button_plus,button_minus,button_divide,button_pow,button_square,button_multiply;
    public ImageButton button_backspace,button_equals, button_clear;
    public ImageButton button_sin,button_cos,button_tan;
    public RadioGroup radioGroup;
    public RadioButton rbtnRad, rbtnGrad;
    public CheckBox chkMostrar;
    public LinearLayout layoutSpinner, layoutRbtns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modoCientifica= findViewById(R.id.modoCientifica);
        tablaCientifica = findViewById(R.id.scientific);
        tituloText = findViewById(R.id.tituloApp);
        divider1 = findViewById(R.id.divider1);

        ///---------------------------------------------\\
        chkMostrar = findViewById(R.id.extra);
        layoutSpinner = findViewById(R.id.layoutSpinner);
        layoutRbtns = findViewById(R.id.layoutRbtns);
        resultado = findViewById(R.id.textView1);
        resultadoTrigo = findViewById(R.id.textView2);


        button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(this);

        button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(this);

        button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(this);

        button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(this);

        button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(this);

        button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(this);

        button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(this);

        button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(this);

        button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(this);

        button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(this);

        button_plus = findViewById(R.id.button_plus);
        button_plus.setOnClickListener(this);

        button_minus = findViewById(R.id.button_minus);
        button_minus.setOnClickListener(this);

        button_multiply = findViewById(R.id.button_multiply);
        button_multiply.setOnClickListener(this);

        button_divide = findViewById(R.id.button_divide);
        button_divide.setOnClickListener(this);

        button_square = findViewById(R.id.button_raiz);
        button_square.setOnClickListener(this);

        button_equals = findViewById(R.id.button_equals);
        button_equals.setOnClickListener(this);

        button_clear = findViewById(R.id.button_clear);
        button_clear.setOnClickListener(this);

        button_pow = findViewById(R.id.button_pow);
        button_pow.setOnClickListener(this);

        button_sin = findViewById(R.id.button_seno);
        button_sin.setOnClickListener(this);

        button_cos = findViewById(R.id.button_cos);
        button_cos.setOnClickListener(this);
        button_tan = findViewById(R.id.button_tan);
        button_tan.setOnClickListener(this);

        rbtnGrad = findViewById(R.id.rbtnGrados);
        rbtnRad = findViewById(R.id.rbtnRad);
    // ----------------------------   spiner -----------------------------\\


        Spinner spinner = findViewById(R.id.Trigonometricas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.funciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        if (chkMostrar.isChecked()) {
            layoutSpinner.setVisibility(View.VISIBLE);

        } else {
            layoutSpinner.setVisibility(View.GONE);

        }
        chkMostrar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutSpinner.setVisibility(View.VISIBLE);
            } else {
                layoutSpinner.setVisibility(View.GONE);
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

        ImageButton button_raiz = findViewById(R.id.button_raiz);
        button_raiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentNumber = resultado.getText().toString();
                String expression = "Math.sqrt(" + currentNumber + ")";
                String result = getResult(expression);
                if (!result.equals("Err")) {
                    resultado.setText(result);
                }
            }
        });


        ImageButton button_pow = findViewById(R.id.button_pow);
        button_pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentNumber = resultado.getText().toString();
                String expression = "Math.pow(" + currentNumber + ", 2)";
                String result = getResult(expression);
                if (!result.equals("Err")) {
                    resultado.setText(result);
                }
            }
        });

        ImageButton button_seno = findViewById(R.id.button_seno);
        button_seno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentNumber = resultado.getText().toString();
                String expression = "";

                if (rbtnGrad.isChecked()) {
                    // Convertir de grados a radianes si es necesario
                    double grados = Double.parseDouble(currentNumber);
                    double radianes = Math.toRadians(grados);
                    expression = "Math.sin(" + radianes + ")";
                } else {
                    expression = "Math.sin(" + currentNumber + ")";
                }

                String result = getResult(expression);
                if (!result.equals("Err")) {
                    resultado.setText(result);
                }
            }
        });

        ImageButton button_cos = findViewById(R.id.button_cos);
        button_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentNumber = resultado.getText().toString();
                String expression = "";

                if (rbtnGrad.isChecked()) {
                    // Convertir de grados a radianes si es necesario
                    double grados = Double.parseDouble(currentNumber);
                    double radianes = Math.toRadians(grados);
                    expression = "Math.cos(" + radianes + ")";
                } else {
                    expression = "Math.cos(" + currentNumber + ")";
                }

                String result = getResult(expression);
                if (!result.equals("Err")) {
                    resultado.setText(result);
                }
            }
        });

        ImageButton button_tan = findViewById(R.id.button_tan);
        button_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentNumber = resultado.getText().toString();
                String expression = "";

                if (rbtnGrad.isChecked()) {
                    // Convertir de grados a radianes si es necesario
                    double grados = Double.parseDouble(currentNumber);
                    double radianes = Math.toRadians(grados);
                    expression = "Math.tan(" + radianes + ")";
                } else {
                    expression = "Math.tan(" + currentNumber + ")";
                }

                String result = getResult(expression);
                if (!result.equals("Err")) {
                    resultado.setText(result);
                }
            }
        });
// Dentro del método onCreate()
        ImageButton button_backspace = findViewById(R.id.button_backspace);
        button_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = resultado.getText().toString();
                if (!currentText.isEmpty()) {
                    currentText = currentText.substring(0, currentText.length() - 1);
                    resultado.setText(currentText);
                }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        if(text.equals("Asen")){
            resultadoTrigo.setText("Asen");
        } else if(text.equals("ACos")){
            resultadoTrigo.setText("Acos");
        } else if(text.equals("ATan")){
            resultadoTrigo.setText("ATan");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        String numberText = view.getTag().toString();

        String numerosACalcular = resultado.getText().toString();

        if(numberText.equals("C")){
            resultado.setText("0");
            return;
        }



        if(numberText.equals("=")){
            String funcionTrigo = resultadoTrigo.getText().toString();
            if(!funcionTrigo.trim().isEmpty()){
                String expresion = "";

                if(rbtnGrad.isChecked()){
                    double grados = radAGrados(Double.parseDouble(numerosACalcular));
                    numerosACalcular = String.valueOf(grados);
                }

                switch (funcionTrigo) {
                    case "Sen":
                        expresion = "Math.sin(" + numerosACalcular + ")";
                        break;
                    case "Cos":
                        expresion = "Math.cos(" + numerosACalcular + ")";
                        break;
                    case "Tan":
                        expresion = "Math.tan(" + numerosACalcular + ")";
                        break;
                    case "ASen":
                        expresion = "Math.asin(" + numerosACalcular + ")";
                        break;
                    case "ACos":
                        expresion = "Math.acos(" + numerosACalcular + ")";
                        break;
                    case "ATan":
                        expresion = "Math.atan(" + numerosACalcular + ")";
                        break;
                    case "Raiz":
                        expresion = "Math.sqrt(" + numerosACalcular + ")";
                        break;
                    case "Potencia ^2":
                        expresion = "Math.pow(" + numerosACalcular + ", 2)";
                        break;

                }

                String finalResult = getResult(expresion);

                if(!finalResult.equals("Err")){
                    resultadoTrigo.setText("");
                    resultado.setText(finalResult);
                }
            }
            return;
        }

        numerosACalcular = numerosACalcular + numberText;
        resultado.setText(numerosACalcular);

        String finalResult = getResult(numerosACalcular);

        if(!finalResult.equals("Err")){
            resultado.setText(finalResult);
        }
    }

    public String getResult(String data){
        if (data.trim().isEmpty()) {
            return "0"; // Retorna 0 si no hay expresión
        }


        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            if(finalResult.equals("Infinity")){
                Toast.makeText(MainActivity.this, "Error: Division por cero", Toast.LENGTH_SHORT).show();
            } else if(finalResult.equals("NaN")){
                Toast.makeText(MainActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
            return finalResult;
        } catch (Exception e){
            return "Err";
        }
    }

    public double radAGrados(double numero){
        numero = Math.toDegrees(numero);
        return numero;
    }
}
