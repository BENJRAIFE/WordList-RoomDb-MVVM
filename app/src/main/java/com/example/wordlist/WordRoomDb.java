package com.example.wordlist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = Words.class,version = 1)
public abstract class WordRoomDb extends RoomDatabase {

    private static WordRoomDb instance;

    public abstract wordsDao wordDao();

    //singleton
    public static synchronized WordRoomDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDb.class, "word-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTASK(instance).execute();

        }


        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTASK extends AsyncTask<Void,Void,Void>{

        private final wordsDao mwordDao;

        PopulateDataAsyncTASK(WordRoomDb db){
            mwordDao=db.wordDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mwordDao.insert(new Words("book","Book","noun"));
            mwordDao.insert(new Words("book","Book","noun"));
            mwordDao.insert(new Words("book","Book","noun"));
            return null;
        }
    }

}
