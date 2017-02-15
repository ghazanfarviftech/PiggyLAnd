package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean.StickerImg;

import java.util.List;

/**
 * Created by ghazanfarali on 11/02/2017.
 */

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.MyViewHolder> {

    private List<StickerImg> moviesList;
    Context context;
    com.example.ghazanfarali.piggyland.Controls.OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       // public TextView title, year, genre;
        ImageView img_sticker;

        public MyViewHolder(View view) {
            super(view);
            img_sticker = (ImageView)view.findViewById(R.id.img_sticker);
           /* title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);*/
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getPosition());
            }
        }
    }


    public StickerAdapter(List<StickerImg> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context =context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_sticker, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StickerImg movie = moviesList.get(position);

        Drawable drawable =settingDrawable(movie);// context.getDrawable(R.drawable.)
        holder.img_sticker.setImageDrawable(drawable);
        /*holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public Drawable settingDrawable(StickerImg img)
        {
            if(img.getImg()==0)
            {
                return context.getResources().getDrawable(R.drawable.sticker1);
            }else if(img.getImg()==1){
                return context.getResources().getDrawable(R.drawable.sticker2);
            }else if(img.getImg()==1){
                return context.getResources().getDrawable(R.drawable.sticker3);
            }else if(img.getImg()==2){
                return context.getResources().getDrawable(R.drawable.sticker4);
            }else if(img.getImg()==3){
                return context.getResources().getDrawable(R.drawable.sticker5);
            }else if(img.getImg()==4){
                return context.getResources().getDrawable(R.drawable.sticker6);
            }else if(img.getImg()==5){
                return context.getResources().getDrawable(R.drawable.sticker7);
            }else if(img.getImg()==6){
                return context.getResources().getDrawable(R.drawable.sticker8);
            }/*else if(img.getImg()==7){
                return context.getResources().getDrawable(R.drawable.sticker2);
            }else if(img.getImg()==8){
                return context.getResources().getDrawable(R.drawable.sticker2);
            }*/

            return  null;

        }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final com.example.ghazanfarali.piggyland.Controls.OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }
}
