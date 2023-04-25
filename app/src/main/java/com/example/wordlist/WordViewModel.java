package com.example.wordlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WordViewModel extends AndroidViewModel {

    private WordsRepository mwordsRepository;

    private LiveData<List<Words>> mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        mwordsRepository=new WordsRepository(application);
        mAllWords= mwordsRepository.getAllWords();
    }

    public void insert(Words words){
        mwordsRepository.insert(words);
    }
    public void updat(Words words){
        mwordsRepository.updat(words);
    }
    public void delete(Words words){
        mwordsRepository.delete(words);
    }
    public void deleteAllwords(){
        mwordsRepository.deleteAllWords();
    }
    public LiveData<List<Words>> getAllwords(){
        return mAllWords;
    }
}
