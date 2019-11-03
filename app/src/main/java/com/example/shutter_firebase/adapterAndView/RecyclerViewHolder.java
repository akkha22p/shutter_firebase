package com.example.shutter_firebase.adapterAndView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shutter_firebase.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mUserName, mUserCountry;
    public ImageView mImage;

    public RecyclerViewHolder(View itemView){
         super(itemView);
         mUserName = itemView.findViewById(R.id.userName);
         mUserCountry = itemView.findViewById(R.id.userCountry);
         mImage = itemView.findViewById(R.id.profileImage);
    }

}
