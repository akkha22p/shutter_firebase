package com.example.shutter_firebase.adapterAndView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shutter_firebase.ExploreActivity;
import com.example.shutter_firebase.ProfileActivity;
import com.example.shutter_firebase.R;
import com.example.shutter_firebase.data.User;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    ExploreActivity exploreActivity;
    ArrayList<User> userArrayList;

    public RecyclerViewAdapter(ExploreActivity exploreActivity, ArrayList<User> userArrayList) {
        this.exploreActivity = exploreActivity;
        this.userArrayList = userArrayList;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(exploreActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.mUserName.setText(userArrayList.get(position).getName());
        holder.mUserCountry.setText(userArrayList.get(position).getCountry());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewAdapter.this.exploreActivity, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("USER", userArrayList.get(position));
                exploreActivity.startActivity(intent);
            }

        });

        Glide.with(exploreActivity)
                .load(userArrayList.get(position).getImageUrl())
                .into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

}
