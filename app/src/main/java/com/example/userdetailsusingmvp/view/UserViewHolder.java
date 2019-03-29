package com.example.userdetailsusingmvp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.userdetailsusingmvp.R;
import com.example.userdetailsusingmvp.model.Users;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView userName;
    private TextView userEmail;
    private TextView userAddress;
    private TextView userCompanyName;
    private TextView userPhone;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.user_Name);
        userEmail = itemView.findViewById(R.id.user_Email);
        userAddress = itemView.findViewById(R.id.user_Address);
        userCompanyName = itemView.findViewById(R.id.user_CompanyName);
        userPhone = itemView.findViewById(R.id.user_Phone);
    }

    public void bind(Users users){

        userName.setText(users.getName());
        userEmail.setText(users.getEmail());
        userAddress.setText(users.getAddress().getCity());
        userCompanyName.setText(users.getCompany().getName());
        userPhone.setText(users.getPhone());
    }
}
