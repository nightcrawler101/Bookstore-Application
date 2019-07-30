package com.example.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main4Activity extends AppCompatActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        radioGroup = findViewById(R.id.radiobuttongroup);
        Button button;
        Intent intent=new Intent();
        intent=getIntent();
//        RadioButton rb = findViewById(radioGroup.getCheckedRadioButtonId());
        button=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id){
                    case R.id.creditCard:
                        Intent intent1=new Intent(Main4Activity.this,Main5Activity.class);
                        startActivity(intent1);

                        break;
                    case R.id.cashOnDelivery:
                        Intent intent2=new Intent(Main4Activity.this,Main6Activity.class);
                        startActivity(intent2);

                        break;
                }

            }
        });

    }
}
