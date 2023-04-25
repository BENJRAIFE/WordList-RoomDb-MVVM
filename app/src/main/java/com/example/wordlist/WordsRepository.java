package com.example.wordlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordsRepository {
    private wordsDao mwordsDao;
    private LiveData<List<Words>> getAllWords;
    WordsRepository(Application app){
        WordRoomDb db= WordRoomDb.getInstance(app);
        mwordsDao=db.wordDao();
        getAllWords=mwordsDao.getAllWords();
    }
    //operation
    //insert
    public void insert(Words word){
        new InsertAsyncTask(mwordsDao).execute(word);
    }
    //update
    public void updat(Words words){
        new UpdateAsyncTask(mwordsDao).execute(words);

    }
    //delete
    public void delete(Words words){
        new DeleteAsyncTask(mwordsDao).execute(words);

    }
    //getAllWords
    public LiveData<List<Words>> getAllWords(){
        return getAllWords;
    }
    //delete all words
    public void deleteAllWords(){
        new DeleteAllWordsAsyncTask(mwordsDao).execute();

    }



    private static  class  InsertAsyncTask extends AsyncTask<Words,Void,Void>{

        private wordsDao mwordDao;
        public InsertAsyncTask(wordsDao wordsDao){
            mwordDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mwordDao.insert(words[0]);
            return null;
        }
    }

    private static  class  DeleteAsyncTask extends AsyncTask<Words,Void,Void>{

        private wordsDao mwordDao;
        public DeleteAsyncTask(wordsDao wordsDao){
            mwordDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mwordDao.delete(words[0]);
            return null;
        }
    }
    private static  class  UpdateAsyncTask extends AsyncTask<Words,Void,Void>{

        private wordsDao mwordDao;
        public  UpdateAsyncTask(wordsDao wordsDao){
            mwordDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mwordDao.update(words[0]);
            return null;
        }
    }

    private static  class  DeleteAllWordsAsyncTask extends AsyncTask<Void,Void,Void>{

        private wordsDao mwordDao;
        public DeleteAllWordsAsyncTask(wordsDao wordsDao){
            mwordDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mwordDao.deleteAllWords();
            return null;
        }
    }

}
