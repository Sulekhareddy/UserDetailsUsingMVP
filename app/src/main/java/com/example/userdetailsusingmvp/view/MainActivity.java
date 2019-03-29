package com.example.userdetailsusingmvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.userdetailsusingmvp.R;
import com.example.userdetailsusingmvp.model.Users;
import com.example.userdetailsusingmvp.model.repo.Repository;
import com.example.userdetailsusingmvp.presenter.UserPresenerContract;
import com.example.userdetailsusingmvp.presenter.UserPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UserPresenerContract.View {

    private Button user_Button;
    private UserAdapter adapter;
    private RecyclerView recyclerView;
    private UserPresenter userPresenter;
    private Repository repository;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository(this);
        userPresenter = new UserPresenter(this, this);

        initViews();
    }

    private void initViews() {

        user_Button = findViewById(R.id.user_button);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_circular);
        adapter = new UserAdapter();

        user_Button.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        userPresenter.onUserButtonClicked();

    }

    @Override
    public void setUserItems(List<Users> userList) {
        adapter.setUsersList(userList);
    }

    @Override
    public void setProgressVisibility(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showToast() {
        Toast.makeText(this, "Empty Database", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userPresenter.showLocalUsersList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.onDestroy();
    }
}
