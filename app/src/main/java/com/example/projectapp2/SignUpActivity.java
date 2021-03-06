package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail,editTextPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar=findViewById(R.id.progressbar);

        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewSignin).setOnClickListener(this);
    }
    private void registerUser()
    {
        String email= editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if (email.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        //if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           // editTextEmail.setError("enter a valid email");
           // editTextEmail.requestFocus();
            //return;
        //}
        if (password.isEmpty())
        {
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6)
        {
            editTextPassword.setError("Minimum length should be 6 ");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                {
                    Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this,UserPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if (task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignUp:
                registerUser();
                break;
            case R.id.textViewSignin:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

    }
}
