package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserStatus extends AppCompatActivity {
    private ListView listView;
    private String username;
    DatabaseReference databaseReference;
    Button buttonUserLogOut;
    //TextView username,name,area,packageName,Price,payment;
    private List<User> memberList;
    private CustomUserAdapter customAdapter;
    FirebaseDatabase firebaseDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_status);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        listView=findViewById(R.id.ListViewData);



        //FirebaseAuth auth = FirebaseAuth.getInstance();
        buttonUserLogOut=findViewById(R.id.buttonUserLogOut);


        databaseReference=FirebaseDatabase.getInstance().getReference("User");

        memberList=new ArrayList<>();
        customAdapter=new CustomUserAdapter(UserStatus.this,memberList);
        firebaseDatabase=FirebaseDatabase.getInstance();
        buttonUserLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserStatus.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memberList.clear();
                for (DataSnapshot datasnapshot1:dataSnapshot.getChildren())
                {
                    User member=datasnapshot1.getValue(User.class);
                    member.setKey(datasnapshot1.getKey());

                    memberList.add(member);

                }
                listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });



        super.onStart();


    }

}


