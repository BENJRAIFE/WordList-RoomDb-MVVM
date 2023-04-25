package com.example.wordlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //floatingactionButton

    //view model
    private WordViewModel mwordViewModel;
    //RecyclerView
    private RecyclerView mRecyclerView;
    public WordAdapter mWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to th add activity
                Intent intnt = new Intent(MainActivity.this, AddNewWordActivity.class);
                startActivityForResult(intnt, 1);
            }
        });

        //recyclerView
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        //connect recyclerview with adapter
        mWordAdapter = new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);


        mwordViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(WordViewModel.class);
        mwordViewModel.getAllwords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {
                //update UI
                //recycler View

                mWordAdapter.setWord(words);
                //Toast.makeText(MainActivity.this, "onchanged worked", Toast.LENGTH_SHORT).show();
            }
        });


        mWordAdapter.onItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Words words) {
                Intent intent = new Intent(MainActivity.this, AddNewWordActivity.class);
                intent.putExtra(AddNewWordActivity.EXTRA_ID, words.getId());
                intent.putExtra(AddNewWordActivity.EXTRA_WORD, words.getWordName());
                intent.putExtra(AddNewWordActivity.EXTRA_MEANING, words.getWordMeaning());
                intent.putExtra(AddNewWordActivity.EXTRA_TYPE, words.getWordType());
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
              int position=  viewHolder.getAdapterPosition();
                mwordViewModel.delete(mWordAdapter.getWordAtIndex(position));
            }
        }).attachToRecyclerView(mRecyclerView);

    }

}