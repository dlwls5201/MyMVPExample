package com.tistory.black_jin0427.myandroidarchitecture;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //TODO duration 을 추가하여 코드를 분리해 주자
    public void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

}
