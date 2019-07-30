package com.example.myapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword, editTextConfirmPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonRegister=findViewById(R.id.button);
        editTextEmail=findViewById(R.id.editText2);
        editTextPassword=findViewById(R.id.editText3);
        editTextConfirmPassword=findViewById(R.id.editText4);
        fireBaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registeration
                registerUser();
            }
        });

    }

    private void registerUser() {
        //will register user
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String confirmPassword=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Enter Email ID",Toast.LENGTH_SHORT).show();
            //stop execution
            return;
        }
        if(TextUtils.isEmpty(password)){
            //empty password
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            //stop execution
            return;
        }
        if(!editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString())){
            Toast.makeText(this,"Password Dont Match!",Toast.LENGTH_SHORT).show();
            return;
        }
        //if(confirmPassword.toString().equals())
        //if validations are okay
        //we will show progress dialogue message
        progressDialog.setMessage("Registering User");
        progressDialog.show();
        fireBaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //USER is registered
                            Toast.makeText(Main2Activity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Main2Activity.this,"Registeration Failed. Please try again!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}
