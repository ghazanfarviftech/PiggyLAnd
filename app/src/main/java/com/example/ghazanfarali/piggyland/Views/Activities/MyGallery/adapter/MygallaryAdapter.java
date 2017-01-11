package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.MyGallery;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir.jehangir on 1/11/2017.
 */
public class MygallaryAdapter extends RecyclerView.Adapter<MygallaryAdapter.ViewHolder>{

    private List<mygallarylist> smartToolsList;
    com.example.ghazanfarali.piggyland.Controls.OnItemClickListener onItemClickListener;
    private MyGallery activity;

    public MygallaryAdapter(MyGallery dockActivity, ArrayList<mygallarylist> sections) {

        Log.e("Adp",sections.size()+" ");



        this.smartToolsList = sections;

        for(int i=0;i<smartToolsList.size();i++)
        {
            Log.e("adap Title",smartToolsList.get(i).getmygallaryTitle()+" ");
        }

        this.activity = dockActivity;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.gallery_list_item, parent, false);

        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


//        if (eLibraryList.get(position).geteServicesImageURL() != null && !ValidationUtils.testEmpty(eLibraryList.get(position).geteServicesImageURL()))
//            ImageLoader.getInstance().displayImage(eLibraryList.get(position).geteServicesImageURL(), holder.image);

        Log.e("Title smart",smartToolsList.get(position).getmygallaryTitle()+ " ");

        holder.image.setImageResource(smartToolsList.get(position).getmygallaryImageURL());
        holder.name.setText(smartToolsList.get(position).getmygallaryTitle());
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
        public RelativeLayout container;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.thumbnail);
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

