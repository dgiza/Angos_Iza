package com.example.angos_iza_calculadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.angos_iza_calculadora.databinding.ActivityMainBinding;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
    private double Valor1= Double.NaN;
    private double Valor2;

    private static final char ADDITION='+';
    private static final char SUBSTRACTION='-';
    private static final char MULTIPLY='X';
    private static final char DIVISION='/';

    private char CURRENT_ACTION;

    DecimalFormat decimalFormat =new DecimalFormat("#.##########");

}
