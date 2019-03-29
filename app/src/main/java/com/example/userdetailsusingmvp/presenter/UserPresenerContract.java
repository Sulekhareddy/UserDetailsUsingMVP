package com.example.userdetailsusingmvp.presenter;

import com.example.userdetailsusingmvp.model.Users;

import java.util.List;

public interface UserPresenerContract {

    interface View{

        void setUserItems(List<Users> userList);

        void setProgressVisibility(boolean b);

        void showToast();
    }

    interface Presenter{

        void onUserButtonClicked();

        void onDestroy();

        void showLocalUsersList();
    }
}
