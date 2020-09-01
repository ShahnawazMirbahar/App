package com.example.projectapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
 DatabaseReference databaseReference;
 EditText  editTextName,editTextPackage,editTextPrice,editTextContact,editTextArea;
 Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Member");
        editTextName=findViewById(R.id.editTextName);
        editTextPackage=findViewById(R.id.editTextPackage);
        editTextPrice=findViewById(R.id.editTextPrice);
        editTextContact=findViewById(R.id.editTextContact);
        editTextArea=findViewById(R.id.editTextArea);
        findViewById(R.id.buttonShowUserData).setOnClickListener(this);
        findViewById(R.id.buttonSignOut).setOnClickListener(this);
        findViewById(R.id.buttonSave).setOnClickListener(this);
        findViewById(R.id.buttonShowData).setOnClickListener(this);
        member=new Member();


    }
    private void saveUserInformation()
    {
        //String name=editTextName.getText().toString().trim();
        //String packageName=editTextPackage.getText().toString().trim();
       member.setPrice (editTextPrice.getText().toString().trim());
       member.setContact(editTextContact.getText().toString().trim());
        member.setName(editTextName.getText().toString().trim());
        member.setPackageName(editTextPackage.getText().toString().trim());
        member.setArea(editTextArea.getText().toString().trim());
       // member.setPrice(price);
       // member.setContact(contact);

        databaseReference.push().setValue(member);
        Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();
        editTextName.setText("");
        editTextPackage.setText("");
        editTextArea.setText("");
        editTextPrice.setText("");
        editTextContact.setText("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonSignOut:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.buttonSave:
                saveUserInformation();
                break;
            case R.id.buttonShowData:
                startActivity(new Intent(this,ShowDatabase.class));
                break;
            case R.id.buttonShowUserData:
                startActivity(new Intent(this,AdminSeesUserData.class));


        }


    }
}
