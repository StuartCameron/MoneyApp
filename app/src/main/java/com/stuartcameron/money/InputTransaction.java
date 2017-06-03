package com.stuartcameron.money;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


public class InputTransaction extends AppCompatActivity {


    public static double cash = 0;
    ArrayAdapter adapter;
    int posOrNeg = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        adapter = ArrayAdapter.createFromResource(this, R.array.catagorieOptions,android.R.layout.simple_spinner_item);
        Spinner catSpin = (Spinner) findViewById(R.id.catagorieSpiner);

        catSpin.setAdapter(adapter);

        Button input = (Button) findViewById(R.id.btnInput);
        Button back = (Button) findViewById(R.id.btnBack);
        Switch inOrOut = (Switch) findViewById(R.id.transType);

        inOrOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    posOrNeg = -1;
                }else {
                    posOrNeg = 1;
                }
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View inputView) {
                cash =  takeCashInput(posOrNeg) ;
                Snackbar.make(inputView, "$" + cash +" inputed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                sendCash();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View back) {
                Intent moveBack = new Intent(InputTransaction.this,HomeScreen .class);
                startActivity(moveBack);
            }

        });


    }

    public double takeCashInput(int t){
        double cashIn = 0;
        EditText amont = (EditText) findViewById(R.id.amountInput);
        cashIn  = Double.valueOf(amont.getText().toString());
        return (cashIn*t);
    }

    public static double sendCash(){
        return cash;
    }


}
