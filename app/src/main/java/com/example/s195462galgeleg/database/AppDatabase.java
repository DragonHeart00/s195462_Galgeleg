package com.example.s195462galgeleg.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.s195462galgeleg.interfaces.PlayerDAO;
import com.example.s195462galgeleg.model.Player;

@Database(entities = {Player.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDAO playerDAO();


}

