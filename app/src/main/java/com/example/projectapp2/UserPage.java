package com.example.projectapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
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

public class UserPage extends AppCompatActivity {
    private ListView listView;
    private String key;
    DatabaseReference databaseReference;
    Button buttonUserLogOut;
    Button buttonUserStatus;
    private List<Member> memberList;
    private CustomAdapter customAdapter;
    FirebaseDatabase firebaseDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        listView=findViewById(R.id.ListViewData);
        buttonUserLogOut=findViewById(R.id.buttonUserLogOut);
        buttonUserStatus=findViewById(R.id.buttonUserStatus);
        databaseReference=FirebaseDatabase.getInstance().getReference("Member");
        memberList=new ArrayList<>();
        customAdapter=new CustomAdapter(UserPage.this,memberList);
        firebaseDatabase=FirebaseDatabase.getInstance();
        buttonUserLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserPage.this,MainActivity.class);
                startActivity(intent);

            }
        });
        buttonUserStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserPage.this,UserStatus.class);
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
                    Member member=datasnapshot1.getValue(Member.class);
                    member.setKey(datasnapshot1.getKey());
                    memberList.add(member);
                }
                listView.setAdapter(customAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Intent updateDelete=new Intent(UserPage.this,UserAddIsp.class);
                        Member m=(Member) adapterView.getItemAtPosition(position);
                        updateDelete.putExtra("name",m.getName());
                        updateDelete.putExtra("Area",m.getArea());
                        updateDelete.putExtra("packageName",m.getPackageName());
                        updateDelete.putExtra("Price",m.getPrice());
                        updateDelete.putExtra("contact",m.getContact());
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


