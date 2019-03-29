package com.example.userdetailsusingmvp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdetailsusingmvp.R;
import com.example.userdetailsusingmvp.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    List<Users> usersList = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        Users users = usersList.get(position);
        userViewHolder.bind(users);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void setUsersList(List<Users> users){
        if(!usersList.isEmpty()){
            usersList.clear();
        }
        usersList.addAll(users);
        notifyDataSetChanged();
    }
}
