package com.example.s195462galgeleg.interfaces;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.s195462galgeleg.model.Player;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM playList")
    List<Player> getAllPlayers();

    @Insert
    void insertAll(Player... players);
}
