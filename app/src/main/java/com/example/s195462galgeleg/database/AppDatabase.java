package com.example.s195462galgeleg.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.s195462galgeleg.interfaces.PlayerDAO;
import com.example.s195462galgeleg.model.Player;

@Database(entities = {Player.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDAO playerDAO();
    //lazy initialization
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        //thread safe
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "playerLog")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

