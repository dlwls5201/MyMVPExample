package com.tistory.black_jin0427.myandroidarchitecture.view.detail;

import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;
import com.tistory.black_jin0427.myandroidarchitecture.rxeventbus.RxEventBus;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void clickLikeEvent(User user) {

        user.likeCnt++;

        // RxEventBus 신호를 보냄
        RxEventBus.getInstance().sendEvent(user);

        // 좋아요 갯수
        view.setTvLikeCount(user.getLikeCnt());
    }
}
