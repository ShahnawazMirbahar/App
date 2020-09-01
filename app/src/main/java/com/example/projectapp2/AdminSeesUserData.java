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

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminSeesUserData extends AppCompatActivity {
    private ListView listView;
    private String key;
    DatabaseReference databaseReference;
    Button buttonUserLogOut;
    private List<User> memberList;
    private CustomUserAdapter customAdapter;
    FirebaseDatabase firebaseDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sees_user_data);
        listView=findViewById(R.id.ListViewData);
        buttonUserLogOut=findViewById(R.id.buttonUserLogOut);

        databaseReference=FirebaseDatabase.getInstance().getReference("User");
        memberList=new ArrayList<>();
        customAdapter=new CustomUserAdapter(AdminSeesUserData.this,memberList);
        firebaseDatabase=FirebaseDatabase.getInstance();
        buttonUserLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminSeesUserData.this,MainActivity.class);
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
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Intent updateDelete=new Intent(AdminSeesUserData.this,UpdateDeleteUser.class);
                        User m=(User) adapterView.getItemAtPosition(position);
                        updateDelete.putExtra("userName",m.getUserName());
                        //updateDelete.putExtra("payment",m.getPayment());

                        updateDelete.putExtra("key",m.getKey());
                        startActivity(updateDelete);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        });



        super.onStart();


    }

}

