package com.example.wordlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.wordViewHolder> {

    private static OnItemClickListener mlistener;

    private static List<Words> mwordList=new ArrayList<>();
    @NonNull
    @Override
    public wordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item,parent,false);
        return new wordViewHolder(itemView);
    }
    public void setWord(List<Words> words){
        mwordList=words;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull wordViewHolder holder, int position) {
        Words currentWord=mwordList.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
        holder.textViewType.setText(currentWord.getWordType());
    }

    @Override
    public int getItemCount() {
        return mwordList.size();
    }

    public static class wordViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;

        public wordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord=itemView.findViewById(R.id.word_textview);
            textViewMeaning=itemView.findViewById(R.id.meaning_textview);
            textViewType=itemView.findViewById(R.id.text_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=getAdapterPosition();
                    if(mlistener!=null && index!=RecyclerView.NO_POSITION){
                        mlistener.onItemClick(mwordList.get(index));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Words words);
    }
    public void onItemClickListener(OnItemClickListener listener){
        mlistener=listener;

    }
    public Words getWordAtIndex(int position){
        return mwordList.get(position);
    }
}
