package com.example.dosecue;

import static android.app.ProgressDialog.show;
import static com.example.dosecue.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    TextView btn;
    Button btnRegister, buttonBack;
    private EditText inputUsername, inputPassword, inputEmail, inputConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);
        btn = findViewById(id.alreadyhaveaccount);
        btnRegister = findViewById(id.btnRegister);
        inputUsername = findViewById(id.inputUsername);
        inputEmail = findViewById(id.inputEmail);
        inputPassword = findViewById(id.inputPassword);
        inputConfirmPassword = findViewById(id.inputPassword);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkCredentials();
            }
        });
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    private void checkCredentials(){
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpassword = inputConfirmPassword.getText().toString();
        if(username.isEmpty() || username.length()<7){
            showError(inputUsername, "Your username is not valid...!!!");
        }
        else if(email.isEmpty() || !email.contains("@")){
            showError(inputEmail, "Email is not valid");
        }
        else if(password.isEmpty() || password.length()<7){
            showError(inputPassword, "Password must be 7 characters...");
        }
        else if(confirmpassword.isEmpty() || !confirmpassword.equals(password)){
            showError(inputConfirmPassword, "Password does not match...");
        }
        else{
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }
    }
    public void openMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }
}