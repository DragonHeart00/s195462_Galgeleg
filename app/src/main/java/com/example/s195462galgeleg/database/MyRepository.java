package com.example.s195462galgeleg.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.s195462galgeleg.interfaces.PlayerDAO;
import com.example.s195462galgeleg.model.Player;

import java.util.List;

public class MyRepository {
    private PlayerDAO playerDAO;
    private LiveData<List<Player>> allPlayer;

    public MyRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        playerDAO = database.playerDAO();
        allPlayer= playerDAO.getAllPlayers();
    }


    public void insert(Player player){
        new InsertPlayerAsyncTask(playerDAO).execute(player);

    }

    public void deleteAllPlayer(){
        new DeleteAllPlayerAsyncTask(playerDAO).execute();
    }


    public LiveData<List<Player>> getAllPlayer(){

        return allPlayer;
    }



    private static class InsertPlayerAsyncTask extends AsyncTask<Player,Void,Void> {

        private PlayerDAO playerDAO;
        private InsertPlayerAsyncTask(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDAO.insert(players[0]);
            return null;
        }
    }


    private static class DeleteAllPlayerAsyncTask extends AsyncTask<Void,Void,Void>{

        private PlayerDAO playerDAO;

        private DeleteAllPlayerAsyncTask(PlayerDAO playerDAO) {
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDAO.deleteAllPlayer();
            return null;
        }

    }




}
