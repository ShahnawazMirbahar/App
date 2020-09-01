package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDelete extends AppCompatActivity {
    EditText name,area,packageName,price,contact;
    DatabaseReference databaseReference;
    Button buttonDelete;
    Button buttonUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        name=findViewById(R.id.editTextName);
        area=findViewById(R.id.editTextArea);
        packageName=findViewById(R.id.editTextPackage);
        price=findViewById(R.id.editTextPrice);
        contact=findViewById(R.id.editTextContact);
        buttonDelete=findViewById(R.id.buttonDelete);
        buttonUpdate=findViewById(R.id.buttonUpdate);
        name.setText(getIntent().getStringExtra("name"));
        area.setText(getIntent().getStringExtra("Area"));
        packageName.setText(getIntent().getStringExtra("packageName"));
        price.setText(getIntent().getStringExtra("Price"));
        contact.setText(getIntent().getStringExtra("contact"));
        databaseReference= FirebaseDatabase.getInstance().getReference();
        String key=getIntent().getExtras().get("key").toString();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Member").child(key);

        //price.setText(getIntent().getIntExtra("Price",1));
        //contact.setText(getIntent().getLongExtra("contact",));
     buttonUpdate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     dataSnapshot.getRef().child("name").setValue(name.getText().toString());
                     dataSnapshot.getRef().child("area").setValue(area.getText().toString());
                     dataSnapshot.getRef().child("packageName").setValue(packageName.getText().toString());
                     dataSnapshot.getRef().child("contact").setValue(contact.getText().toString());
                     dataSnapshot.getRef().child("price").setValue(price.getText().toString());
                     Toast.makeText(UpdateDelete.this, "Data Updated", Toast.LENGTH_SHORT).show();
                     Intent i=new Intent(UpdateDelete.this,ShowDatabase.class);
                     startActivity(i);

                     UpdateDelete.this.finish();

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
             Toast.makeText(UpdateDelete.this, "Content Deleted", Toast.LENGTH_SHORT).show();
             Intent i=new Intent(UpdateDelete.this,ShowDatabase.class);
             startActivity(i);
             UpdateDelete.this.finish();



                 }
             });



         }

}
