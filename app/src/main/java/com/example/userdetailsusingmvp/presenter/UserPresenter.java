package com.example.userdetailsusingmvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.userdetailsusingmvp.model.repo.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter implements UserPresenerContract.Presenter {

    private UserPresenerContract.View view;
    private Repository repository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public UserPresenter(Context context, UserPresenerContract.View view) {
        this.view = view;
        repository = new Repository(context);
    }


    @Override
    public void onUserButtonClicked() {
        view.setProgressVisibility(true);
        add(repository.getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(users -> view.setProgressVisibility(false))
                .subscribe(userList ->{
                    view.setUserItems(userList);
                    repository.insertUsers(userList);
                }, throwable -> {
                    Log.d("TAG_1", throwable.getMessage());
                    view.setProgressVisibility(false);
                })
        );


    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        repository.onDestroy();
    }

    @Override
    public void showLocalUsersList() {

        add(repository.getLocalUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    if(!users.isEmpty()){
                        view.setUserItems(users);
                    }else{
                        view.showToast();
                    }
                }, throwable -> Log.d("TAG_1", throwable.getMessage()))
                );

    }

    private void add(Disposable disposable){
        compositeDisposable.add(disposable);
    }


}
