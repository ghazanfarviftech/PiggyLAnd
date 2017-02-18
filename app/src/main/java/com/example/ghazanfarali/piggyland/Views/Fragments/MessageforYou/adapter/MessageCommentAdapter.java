package com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.MessageforYou.Bean.MessagesComments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir.jehangir on 2/18/2017.
 */
public class MessageCommentAdapter extends RecyclerView.Adapter<MessageCommentAdapter.ViewHolder>{
    private List<MessagesComments> smartToolsList;
    com.example.ghazanfarali.piggyland.Controls.OnItemClickListener onItemClickListener;
    private MasterFragment activity;

    public MessageCommentAdapter(MasterFragment dockActivity, ArrayList<MessagesComments> sections) {

        Log.e("Adp",sections.size()+" ");



        this.smartToolsList = sections;

        for(int i=0;i<smartToolsList.size();i++)
        {
            //  Log.e("adap Title",smartToolsList.get(i).getmygallaryTitle()+" ");
        }

        this.activity = dockActivity;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.messagecomment_list_item, parent, false);

        return new ViewHolder(sView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Log.e("Title smart",smartToolsList.get(position).getcommentsMessages()+ " ");

        //holder.image.setImageResource(smartToolsList.get(position).getcommentsImageURL().toString());
        holder.firstLine.setText(String.valueOf(smartToolsList.get(position).getcommentsUserName()));
        holder.title.setText(String.valueOf(smartToolsList.get(position).getcommentsMessages()));

//        int arrylst = (smartToolsList.size() -1);
//if (msglast(arrylst)){
//    holder.view_Line_Message.setVisibility(View.GONE);
//}

    }



//    private boolean msglast(int value){
//        boolean abc = true;
//        if(value < 0){
//            abc = false;
//        }
//
//
//        return abc;
//    }

    @Override
    public int getItemCount() {
        //  Log.e("Count size",smartToolsList.size()+" ");
        return smartToolsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView firstLine;
        public ImageView image;
        public TextView title;
        public View view_Line_Message;

        public ViewHolder(View view) {
            super(view);
            firstLine = (TextView) view.findViewById(R.id.firstLine);
            title = (TextView) view.findViewById(R.id.title);
            view_Line_Message = (View)view.findViewById(R.id.view_Line_Message);

            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final com.example.ghazanfarali.piggyland.Controls.OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }
}
