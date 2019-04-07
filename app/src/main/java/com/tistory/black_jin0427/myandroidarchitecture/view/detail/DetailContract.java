package com.tistory.black_jin0427.myandroidarchitecture.view.detail;

import com.tistory.black_jin0427.myandroidarchitecture.BaseContract;
import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;

public interface DetailContract {

    interface View extends BaseContract.View {

        void setTvLikeCount(String text);
    }

    interface Presenter extends BaseContract.Presenter {

        void clickLikeEvent(User user);
    }
}
