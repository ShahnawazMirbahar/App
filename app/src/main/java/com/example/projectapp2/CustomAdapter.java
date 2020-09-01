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

public class CustomAdapter extends ArrayAdapter<Member> {
 private Activity context;
 private List<Member> memberList;

    public CustomAdapter( Activity context, List<Member> memberList) {
        super(context, R.layout.sample_layout, memberList);
        this.context =  context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
       View v= layoutInflater.inflate(R.layout.sample_layout,null,true);
       Member member=memberList.get(position);
        TextView t1=v.findViewById(R.id.name1);
        TextView t2=v.findViewById(R.id.packageName1);
        TextView t3=v.findViewById(R.id.area1);
        TextView t4=v.findViewById(R.id.price1);
        TextView t5=v.findViewById(R.id.contact1);
     t1.setText("Name:= "+member.getName());
        t2.setText("Area:= "+member.getArea());
        t3.setText("PackageName:= "+member.getPackageName());
        t4.setText("Price:= "+member.getPrice());
        t5.setText("Contact:= "+member.getContact());
        return v;
    }
}
