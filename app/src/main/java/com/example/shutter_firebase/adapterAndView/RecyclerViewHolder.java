package com.example.shutter_firebase.adapterAndView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shutter_firebase.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mUserName, mUserMajor;

    public RecyclerViewHolder(View itemView){
         super(itemView);
         mUserName = itemView.findViewById(R.id.userName);
         mUserMajor = itemView.findViewById(R.id.userMajor);
    }

}
