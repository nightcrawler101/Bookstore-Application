package com.example.myapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.text.InputFilter;
import android.text.Spanned;



public class Main5Activity extends AppCompatActivity {
    Button b;
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        b = findViewById(R.id.button4);
        e1 = findViewById(R.id.editText6);
        e2 = findViewById(R.id.editText7);
        e3 = findViewById(R.id.editText8);
        e1.setFilters(new InputFilter[]{new InputFilterMinMax("1", "12")});
        //e2.setFilters(new InputFilter[]{new InputFilterMinMax("2020", "2030")});
        e3.setFilters(new InputFilter[]{new InputFilterMinMax("0000", "9999")});



                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (e1.getText().toString().equals("") || e2.getText().toString().equals("") || e3.getText().toString().equals("")) {
                            Toast.makeText(Main5Activity.this, "Complete all the fields!", Toast.LENGTH_SHORT).show();
                        }
                        else if(Integer.parseInt(e2.getText().toString())<2019 || Integer.parseInt(e2.getText().toString())>2030) {
                            Toast.makeText(Main5Activity.this,"Fill apt year",Toast.LENGTH_SHORT).show();
                        }
                        else if(e1.getText().toString().length()>10){
                            Toast.makeText(Main5Activity.this,"Fill appropriate card no",Toast.LENGTH_SHORT).show();
                        }
                            else
                         {
                            Toast.makeText(v.getContext(), "Money Received!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), Main6Activity.class);
                            startActivity(intent);
                        }


                    }
                });

            }
        }

