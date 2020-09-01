package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
FirebaseAuth mAuth;
    EditText editTextEmail,editTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);


        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        findViewById(R.id.textViewSignUp).setOnClickListener(this);
        findViewById(R.id.buttonSignIn).setOnClickListener(this);

    }
    private void userLogin()
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

        if (email.equals("admin@gmail.com")&& password.equals("admin123")){



        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                {
                    Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });}
        else
        {mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful())
                {
                    Intent intent=new Intent(MainActivity.this,UserPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.textViewSignUp:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.buttonSignIn:
                userLogin();
                break;
        }

    }
}
