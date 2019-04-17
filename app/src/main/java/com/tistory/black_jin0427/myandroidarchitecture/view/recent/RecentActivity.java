package com.tistory.black_jin0427.myandroidarchitecture.view.recent;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.tistory.black_jin0427.myandroidarchitecture.BaseActivity;
import com.tistory.black_jin0427.myandroidarchitecture.R;
import com.tistory.black_jin0427.myandroidarchitecture.adapter.MainAdapter;
import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RecentActivity extends BaseActivity
        implements RecentContract.View, MainAdapter.OnItemClickListener  {

    @Inject
    MainAdapter adapter;

    @Inject
    RecentPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_view)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        setTitle("RECENT USER");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setRoomItems(ArrayList<User> items) {
        adapter.setItems(items);
    }

    /**
     * adapter CLickListener
     */
    @Override
    public void onItemClick(User user) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("삭제하시겠습니까?");
        builder.setPositiveButton("확인",
                (dialog, which) -> {
                    presenter.deleteUser(user);
                });
        builder.setNegativeButton("취소",
                (dialog, which) -> {

                });
        builder.show();
    }

    /**
     * ButterKnife ClickListener
     */
    @OnClick(R.id.btn_clear_all)
    void onClickClearAll() {
        presenter.clearAll();
    }
}
