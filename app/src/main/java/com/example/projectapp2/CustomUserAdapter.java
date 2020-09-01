package com.example.projectapp2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomUserAdapter extends ArrayAdapter<User> {
    private Activity context;
    private List<User> memberList;

    public CustomUserAdapter( Activity context, List<User> memberList) {
        super(context, R.layout.sample_layout, memberList);
        this.context =  context;
        this.memberList = memberList;
    }



    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View v= layoutInflater.inflate(R.layout.sample_layout_user,null,true);
        User member=memberList.get(position);

        TextView t0=v.findViewById(R.id.Username1);
        TextView t1=v.findViewById(R.id.name1);
        TextView t2=v.findViewById(R.id.packageName1);
        TextView t3=v.findViewById(R.id.area1);
        TextView t4=v.findViewById(R.id.price1);
        TextView t5=v.findViewById(R.id.payment);
        t1.setText(member.getName());
        t2.setText(member.getArea());
        t3.setText(member.getPackageName());
        t4.setText("Due:= "+member.getPrice());
        t0.setText("UserName: "+member.getUserName());
        t5.setText("Payment Status:= "+member.getPayment());
        return v;
    }
}
