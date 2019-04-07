package com.tistory.black_jin0427.myandroidarchitecture.view.main;

import com.tistory.black_jin0427.myandroidarchitecture.BaseContract;
import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;

import java.util.ArrayList;

public interface MainContract {

    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void goToDetailActivity(User user);

        void goToRecentActivity();

        void setItems(ArrayList<User> items);

        void updateView(User user);

        void showViewToast(String msg);

    }

    interface Presenter extends BaseContract.Presenter {

        void loadData();

        void initRxEvent();

        void addUser(User user);
    }
}
