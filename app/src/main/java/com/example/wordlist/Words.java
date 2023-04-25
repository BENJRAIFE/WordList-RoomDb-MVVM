package com.example.wordlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Words {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wordName;
    private String wordMeaning;
    //@ColumnInfo(name = "type") if we wanna to change colomn name
    private String wordType;


    public Words( String wordName, String wordMeaning, String wordType) {
        this.wordName = wordName;
        this.wordMeaning = wordMeaning;
        this.wordType = wordType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }



    public String getWordMeaning() {
        return wordMeaning;
    }



    public String getWordType() {
        return wordType;
    }

}
