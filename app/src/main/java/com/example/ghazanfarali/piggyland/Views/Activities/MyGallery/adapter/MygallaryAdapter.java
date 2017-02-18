package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.MyGallery;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.MyGallaryMultiSelectITems;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

import java.util.ArrayList;

/**
 * Created by Amir.jehangir on 1/11/2017.
 */
public class MygallaryAdapter  extends RecyclerView.Adapter<MygallaryAdapter.RecyclerViewHolder>{
    ArrayList<MyGallaryMultiSelectITems> adapter_list = new ArrayList<MyGallaryMultiSelectITems>();
    Activity mainActivity;
    MasterFragment ctx;
    com.example.ghazanfarali.piggyland.Controls.GallaryClickListner onItemClickListener;

    public MygallaryAdapter(ArrayList<MyGallaryMultiSelectITems> adapter_list, Activity ctx) {
        this.adapter_list = adapter_list;
        this.mainActivity = ctx;

       // mainActivity =  ctx;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
//        RecyclerViewHolder recyclerViewHolder = new MygallaryAdapter.RecyclerViewHolder(view, mainActivity);
//        return recyclerViewHolder;

        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.card_view_layout, parent, false);

        return new RecyclerViewHolder(sView,mainActivity);
    }





    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Glide
                .with( mainActivity ) // safer!
                .load( adapter_list.get(position).getmygallaryImageURL())
                .asBitmap()
                .into( holder.thumbnail );
    //    holder.thumbnail.setImageBitmap(getBit(adapter_list.get(position).getmygallaryImageURL().toString()));
        holder.title.setText(adapter_list.get(position).getmygallaryTitle());
        if (!MyGallery.is_in_action_mode) {
            // holder.checkBox.setVisibility(View.GONE);
            holder.ly_share_edit.setVisibility(View.VISIBLE);
            holder.ly_select.setVisibility(View.GONE);
            holder.checkBox.setChecked(false);
        } else {
            //  holder.checkBox.setVisibility(View.VISIBLE);


            holder.ly_share_edit.setVisibility(View.GONE);
            holder.ly_select.setVisibility(View.VISIBLE);
        }
    }


    private Bitmap getBit(String getpath){

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(getpath,bmOptions);
        //   bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return adapter_list.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView textView;
        CheckBox checkBox;
        Activity mainActivity;
        // cardview for long click
        CardView cardView;
        LinearLayout ly_share_edit, ly_select;
        TextView title;
        ImageView list_edit, list_share,thumbnail;

        public RecyclerViewHolder(View itemview, Activity mainActivity) {
            // mainActivity is used for handling the click events of the checkboxes
            super(itemview);
            textView = (TextView) itemview.findViewById(R.id.textView);
            checkBox = (CheckBox) itemview.findViewById(R.id.checkBox);

            ly_share_edit = (LinearLayout)itemview.findViewById(R.id.ly_share_edit);
            ly_select = (LinearLayout)itemview.findViewById(R.id.ly_select);
            title =(TextView)itemview.findViewById(R.id.title);
            list_edit = (ImageView)itemview.findViewById(R.id.list_edit);
            list_share = (ImageView)itemview.findViewById(R.id.list_share);
            thumbnail = (ImageView)itemview.findViewById(R.id.thumbnail);


            this.mainActivity = mainActivity;
            cardView = (CardView) itemview.findViewById(R.id.card_view);
           // cardView.setOnLongClickListener(mainActivity);
            checkBox.setOnClickListener((View.OnClickListener) this);
           // itemview.setOnClickListener(this);
            itemview.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
           switch (v.getId()){

               case R.id.checkBox : {

                   if (onItemClickListener != null) {
                       onItemClickListener.onCheckBoxClick(v, getPosition());
                   }

               }


           }

//            mainActivity.prepareselection(v, getAdapterPosition());
//            if (onItemClickListener != null) {
//                onItemClickListener.onItemClick(v, getPosition());
//            }
        }


        @Override
        public boolean onLongClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
            return false;

        }
    }

    public void updateAdapter(ArrayList<MyGallaryMultiSelectITems> list) {
        for (MyGallaryMultiSelectITems contact : list) {
            adapter_list.remove(contact);
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final com.example.ghazanfarali.piggyland.Controls.GallaryClickListner mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }


}

