package com.example.wordlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {

    private WordsRepository mwordsRepository;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mwordsRepository=new WordsRepository(application);

    }

    public void insert(Words words){
        mwordsRepository.insert(words);
    }
    public void updat(Words words){
        mwordsRepository.updat(words);
    }

}
