package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

public class UserAddIsp extends AppCompatActivity {
    TextView name, area, packageName, price, contact;
    EditText username;
    DatabaseReference databaseReference;
    Button buttonUserAdd;
    Button buttonCall;
    Button buttonUserStatus;
    private static final int REQUEST_CALL = 1;
    User user;
    String key;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_isp);
       username=findViewById(R.id.userName);
        name = findViewById(R.id.editTextName);
        area = findViewById(R.id.editTextArea);
        packageName = findViewById(R.id.editTextPackage);
        //databaseReference= FirebaseDatabase.getInstance().getReference().child("User");
        price = findViewById(R.id.editTextPrice);
        contact = findViewById(R.id.editTextContact);
        buttonUserAdd = findViewById(R.id.buttonUserAdd);
        buttonCall = findViewById(R.id.buttonCall);
        buttonUserStatus=findViewById(R.id.buttonStatus);

       // user=new User();
        //buttonUpdate=findViewById(R.id.buttonUpdate);


        name.setText("ISP Name: " + getIntent().getStringExtra("name"));
        area.setText("Area: " + getIntent().getStringExtra("Area"));
        packageName.setText("PackageName :" + getIntent().getStringExtra("packageName"));
        price.setText("Price: " + getIntent().getStringExtra("Price"));

        contact.setText(  getIntent().getStringExtra("contact"));
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String key = getIntent().getExtras().get("key").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Member").child(key);

        //price.setText(getIntent().getIntExtra("Price",1));
        //contact.setText(getIntent().getLongExtra("contact",));
        buttonUserStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserAddIsp.this,UserStatus.class);
                startActivity(intent);
            }
        });
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + contact.getText().toString().trim();
                Intent callintent = new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse(number));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                startActivity(callintent);
            }
        });



       buttonUserAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               databaseReference= FirebaseDatabase.getInstance().getReference().child("User");
               user=new User();
               user.setUserName(username.getText().toString().trim());
               user.setPrice (price.getText().toString().trim());
               //user.setContact(contact.getText().toString().trim());
               user.setName(name.getText().toString().trim());
              user.setPackageName(packageName.getText().toString().trim());
               user.setArea(area.getText().toString().trim());

               // member.setPrice(price);
               // member.setContact(contact);

               databaseReference.push().setValue(user);
               username.setText("");
               Toast.makeText(UserAddIsp.this, "User Data Saved", Toast.LENGTH_SHORT).show();

           }
       });

    }


    }


