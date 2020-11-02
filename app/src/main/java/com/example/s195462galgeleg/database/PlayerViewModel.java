package com.example.s195462galgeleg.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.s195462galgeleg.database.MyRepository;
import com.example.s195462galgeleg.model.Player;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private MyRepository myRepository;
    private LiveData<List<Player>> players;


    public PlayerViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        players = myRepository.getAllPlayer();
    }

    public void insert(Player player){
        myRepository.insert(player);
    }


    public void deleteAll(){
        myRepository.deleteAllPlayer();
    }

    public LiveData<List<Player>> getAllPlayer(){
        return players;
    }
}
