package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteUser extends AppCompatActivity {
    EditText payment;
    TextView userName;
    DatabaseReference databaseReference;
    Button buttonDelete;
    Button buttonUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_user);
       userName=findViewById(R.id.editTextUserName);
       payment=findViewById(R.id.editTextPayment);
        buttonDelete=findViewById(R.id.buttonDelete);
        buttonUpdate=findViewById(R.id.buttonUpdate);
        userName.setText(getIntent().getStringExtra("userName"));
        payment.setText(payment.getText().toString().trim());


        //payment.setText(getIntent().getStringExtra("payment"));


        databaseReference= FirebaseDatabase.getInstance().getReference();
        String key=getIntent().getExtras().get("key").toString();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("User").child(key);

        //price.setText(getIntent().getIntExtra("Price",1));
        //contact.setText(getIntent().getLongExtra("contact",));
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("userName").setValue(userName.getText().toString());
                        dataSnapshot.getRef().child("payment").setValue(payment.getText().toString());






                        Toast.makeText(UpdateDeleteUser.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(UpdateDeleteUser.this,AdminSeesUserData.class);
                        startActivity(i);

                        UpdateDeleteUser.this.finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue();
                Toast.makeText(UpdateDeleteUser.this, "Content Deleted", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(UpdateDeleteUser.this,ShowDatabase.class);
                startActivity(i);
                UpdateDeleteUser.this.finish();



            }
        });



    }

}