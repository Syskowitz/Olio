package com.example.appi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    TextView autoTxt;
    TextView fileTxt;
    EditText inputTxt;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        txt = findViewById(R.id.textView);
        inputTxt = findViewById(R.id.editText2);
        autoTxt = findViewById(R.id.textView2);
        fileTxt = findViewById(R.id.textViewFile);
        txt.setText("Moikkulis");
        inputTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = inputTxt.getText().toString();
                autoTxt.setText(content);
            }
            @Override
            public void afterTextChanged(Editable s) {
                ;
            }
        });
    }

    public void greet(View v) {
        System.out.println("Hello world!");
        txt.setText("Hello World!");
    }
    public void changeText(View v) {
        String content = inputTxt.getText().toString();
        txt.setText(content);
    }
    public void readFile(View v) {
        try {
            String s = "";
            String fs = "";
            InputStream ins = context.openFileInput("testi.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            while ((s = br.readLine()) != null) {
                fs += s;
                System.out.println(s);
            }
            fileTxt.setText(fs);
            ins.close();
        } catch (IOException e) {
            Log.e("IOException","Homma men vituiks :(");
        } finally {
            System.out.println("Read from file.");
        }
    }

    public void writeFile(View v) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("testi.txt",context.MODE_PRIVATE));
            String s = inputTxt.getText().toString();
            ows.write(s);
            ows.close();
        } catch (IOException e) {
            Log.e("IOException","Homma men vituiks :(");
        } finally {
            System.out.println("Written to file.");
        }
    }
}
