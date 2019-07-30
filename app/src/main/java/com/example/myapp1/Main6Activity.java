package com.example.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {
    Button b1;
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        b1=findViewById(R.id.buttonCOD);
        e1=findViewById(R.id.editTextCOD1);
        e2=findViewById(R.id.editTextCOD2);
        e3=findViewById(R.id.editTextCOD3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().equals("")||e2.getText().toString().equals("")||e3.getText().toString().equals("")){
                    Toast.makeText(Main6Activity.this,"Fill All Details",Toast.LENGTH_SHORT).show();
                }
                else if(e2.getText().toString().length()>10||e2.getText().toString().length()<10){
                    Toast.makeText(Main6Activity.this,"Enter Apt Mobile No",Toast.LENGTH_SHORT).show();
                }
                else{
                Toast.makeText(getApplicationContext(),"Order Successfully Placed!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main6Activity.this,BookListActivity.class);
                startActivity(intent);
            }}
        });
    }
}
