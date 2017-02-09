package com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment.Bean.CommentsMsgs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir.jehangir on 2/9/2017.
 */
public class commentsAdapter extends RecyclerView.Adapter<commentsAdapter.ViewHolder>{
    private List<CommentsMsgs> smartToolsList;
    com.example.ghazanfarali.piggyland.Controls.OnItemClickListener onItemClickListener;
    private MasterFragment activity;

    public commentsAdapter(MasterFragment dockActivity, ArrayList<CommentsMsgs> sections) {

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
        final View sView = mInflater.inflate(R.layout.messagelistitems, parent, false);

        return new ViewHolder(sView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


//        if (eLibraryList.get(position).geteServicesImageURL() != null && !ValidationUtils.testEmpty(eLibraryList.get(position).geteServicesImageURL()))
//            ImageLoader.getInstance().displayImage(eLibraryList.get(position).geteServicesImageURL(), holder.image);

        Log.e("Title smart",smartToolsList.get(position).getcommentsMessages()+ " ");

        //holder.image.setImageResource(smartToolsList.get(position).getcommentsImageURL().toString());
        holder.name.setText(String.valueOf(smartToolsList.get(position).getcommentsUserName()));
//        if (basePreferenceHelper.getApplicationLanguage().equalsIgnoreCase(CommonConstants.LANG_ENGLISH)) {
//            holder.name.setText(smartToolsList.get(position).getsmartToolsTitle());
//        } else {
//            holder.name.setText(smartToolsList.get(position).getsmartToolsTitle());
//        }
//
//        if (colorDecider(position) == 1 || colorDecider(position) == 2) {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                holder.line.setImageResource(R.color.colorblack);
//            } else {
//                holder.line.setBackgroundResource(R.color.colorblack);
//            }
//        }

//        if (ValidationUtils.testEmpty(smartToolsList.get(position).getmygallaryTitle())) {
//            holder.name.setVisibility(View.GONE);
//            holder.image.setVisibility(View.GONE);
////            holder.line.setVisibility(View.GONE);
//        } else {
//            holder.name.setVisibility(View.VISIBLE);
//            holder.image.setVisibility(View.VISIBLE);
////            holder.line.setVisibility(View.VISIBLE);
//        }


    }

    int colorDecider(int i) {
        int colorDecider = i % 4;
        return colorDecider;
    }



    @Override
    public int getItemCount() {
        //  Log.e("Count size",smartToolsList.size()+" ");
        return smartToolsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public ImageView image;
        public ImageView line;
        // public RelativeLayout container;
//        public ImageView list_edit;
//        public ImageView list_share;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.thumbnail);
//            list_edit = (ImageView)view.findViewById(R.id.list_edit);
//            list_share = (ImageView)view.findViewById(R.id.list_share);
            //line = (ImageView) view.findViewById(R.id.line);
            //  container = (RelativeLayout) view.findViewById(R.id.container);
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
