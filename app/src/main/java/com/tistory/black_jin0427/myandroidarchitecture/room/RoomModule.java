package com.tistory.black_jin0427.myandroidarchitecture.room;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    UserDao provideUserDao(UserDatabase db) {
        return db.getUserDao();
    }

    @Provides
    @Singleton
    UserDatabase provideUserDatabase(@Named("appContext") Context context) {
        return Room.databaseBuilder(context, UserDatabase.class,
                "black_jin.db")
                .build();
    }
}
