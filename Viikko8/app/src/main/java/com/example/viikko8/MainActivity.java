package com.example.viikko8;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar moneySeek;
    TextView moneyTxt;
    TextView screenTxt;
    Spinner productChoice;
    BottleDispenser bd;
    ArrayList<Bottle> productArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = BottleDispenser.getInstance();
        moneySeek = findViewById(R.id.moneySeekBar);
        moneyTxt = findViewById(R.id.moneyView);
        screenTxt = findViewById(R.id.Screen);
        productChoice = findViewById(R.id.productListing);
        productArray = new ArrayList(6);

        productArray.add(new Bottle("Pepsi Max", "Pepsi", (float) 0.3,
                (float) 0.5, (float) 1.8));
        productArray.add(new Bottle("Pepsi Max", "Pepsi", (float) 0.3,
                (float) 1.5, (float) 2.2));
        productArray.add(new Bottle("Coca-Cola Zero", "Coca-Cola", (float) 0.3,
                (float) 0.5, (float) 2.0));
        productArray.add(new Bottle("Coca-Cola Zero", "Coca-Cola", (float) 0.3,
                (float) 1.5, (float) 2.5));
        productArray.add(new Bottle("Fanta Zero", "Fanta", (float) 0.3,
                (float) 0.5, (float) 1.95));
        productArray.add(new Bottle("Fanta Zero", "Fanta", (float) 0.3,
                (float) 1.5, (float) 2.5));
        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,productArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productChoice.setAdapter(adapter);
        moneySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
            public void onProgressChanged(SeekBar moneySeek, int progress, boolean fromUser) {
                float val = (float)(progress);
                moneyTxt.setText(String.format("Money: %.02f",val/100));
            }
            @Override
            public void onStopTrackingTouch(SeekBar moneySeek) {
               ;
            }
            @Override
            public void onStartTrackingTouch(SeekBar moneySeek) {
               ;
            }
        });
    }
    public void addMoneyPress(View v) {
        float moneyAmount = (float) moneySeek.getProgress()/100;
        if (moneyAmount != 0) {
            bd.addMoney(moneyAmount);
            screenTxt.setText(String.format("Added %.02f to the machine",moneyAmount));
            moneySeek.setMax(200);
            moneySeek.setProgress(0);
        } else {
            screenTxt.setText(String.format("No money added.\nGet a job :)."));
        }
    }
    public void buyProductPress(View v) {
        Bottle selectedProduct = (Bottle) productChoice.getSelectedItem();
        for (int i = 0; i < bd.getArrayLength(); i++) {
            if (selectedProduct.toString().equals(bd.getBottle(i).toString())) {
                String txt = bd.buyBottle(i);
                screenTxt.setText(txt);
                return;
            }
        }
        if (bd.getArrayLength() == 0){
            screenTxt.setText(String.format("No more bottles in the machine. Returned %.02f from the machine",bd.getMoney()));
            bd.returnMoney();
        }
        else {
            screenTxt.setText("Product is out!");
        }
    }
    public void returnMoneyPress(View v) {
        float returnedMoney = bd.getMoney();
        if (returnedMoney != 0) {
            screenTxt.setText(String.format("Returned %.02f€ from the machine.", returnedMoney));
            bd.returnMoney();
        } else {
            screenTxt.setText("No money in the machine!");
        }
    }
}
