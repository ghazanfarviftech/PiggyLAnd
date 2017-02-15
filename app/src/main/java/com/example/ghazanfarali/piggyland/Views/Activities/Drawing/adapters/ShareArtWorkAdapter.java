package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean.ShareArtWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghazanfarali on 19/01/2017.
 */

public class ShareArtWorkAdapter extends RecyclerView.Adapter<ShareArtWorkAdapter.ViewHolder>{

    private List<ShareArtWork> smartToolsList;
    com.example.ghazanfarali.piggyland.Controls.OnItemClickListener onItemClickListener;
    com.example.ghazanfarali.piggyland.Controls.MultiClickListner onMulticlickListener;
    private Activity activity;
    int a = 0;
    public ShareArtWorkAdapter(Activity dockActivity, ArrayList<ShareArtWork> sections) {

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
        final View sView = mInflater.inflate(R.layout.share_art_work_list_item, parent, false);

        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


//        if (eLibraryList.get(position).geteServicesImageURL() != null && !ValidationUtils.testEmpty(eLibraryList.get(position).geteServicesImageURL()))
//            ImageLoader.getInstance().displayImage(eLibraryList.get(position).geteServicesImageURL(), holder.image);

        Log.e("Title smart",smartToolsList.get(position).getmygallaryTitle()+ " ");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // Bitmap bitmap = BitmapFactory.decodeFile(smartToolsList.get(position).getImageUrl().getAbsolutePath(), options);
        //selected_photo.setImageBitmap(bitmap);
        //holder.photo.setImageBitmap(bitmap);
        // holder.mImageView.setImageBitmap(bitmap);
        //imageView.setImageBitmap(bitmap);
        /*SimpleTarget target2 = new SimpleTarget<Bitmap>( holder.image.getWidth(), holder.image.getHeight() ) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                //imageView2.setImageBitmap( bitmap );
                holder.image.setImageBitmap(bitmap);
            }
        };*/
        Glide
                .with( activity ) // safer!
                .load( smartToolsList.get(position).getmygallaryImageURL().getAbsolutePath() )
                .asBitmap()
                .into( holder.image );

        //holder.image.setImageResource(smartToolsList.get(position).getmygallaryImageURL());
        holder.title.setText(String.valueOf(smartToolsList.get(position).getmygallaryTitle()));



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

        public TextView title;
        public ImageView image;
        public ImageView line;
        public RelativeLayout container;
        public ImageView img_share;
        //  public ImageView list_share;
        public TextView counter_likes,tv_like,counter_comments,comments;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.thumbnail);

            counter_likes =(TextView) view.findViewById(R.id.counter_likes);
            tv_like =(TextView) view.findViewById(R.id.tv_like);
            counter_comments=(TextView) view.findViewById(R.id.counter_comments);
            comments=(TextView) view.findViewById(R.id.comments);
            img_share = (ImageView)view.findViewById(R.id.img_share);
            //  list_share = (ImageView)view.findViewById(R.id.list_share);
            //line = (ImageView) view.findViewById(R.id.line);
            //  container = (RelativeLayout) view.findViewById(R.id.container);
            tv_like.setOnClickListener(this);
            comments.setOnClickListener(this);
            img_share.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {

            int id = view.getId();
            switch (id){

                case R.id.counter_likes:
                    if (onMulticlickListener != null) {
                        onMulticlickListener.onLikeItemClick(view, getPosition());
//                        a++;
//                        counter_likes.setText(""+a );
                    }

                    break;
                case R.id.comments:
                    if (onMulticlickListener != null) {
                        onMulticlickListener.onCommentItemClick(view, getPosition());
                    }

                    break;
                case R.id.list_edit:
                    if (onMulticlickListener != null) {
                        onMulticlickListener.onShareItemClick(view, getPosition());
                    }

                    break;
                default:{
                    Log.e("no click","click not identify");
                }
            }


        }
    }


    public interface onMulticlickListener{

        public void onLikeItemClick(View view, int position);

        public void onCommentItemClick(View view, int position);

        public void onShareItemClick(View view, int position);
    }

    public void setonMulticlickListener(final com.example.ghazanfarali.piggyland.Controls.MultiClickListner mItemClickListener) {
        this.onMulticlickListener = mItemClickListener;
    }

//    public interface OnItemClickListener {
//        public void onItemClick(View view, int position);
//    }
//
//    public void setOnItemClickListener(final com.example.ghazanfarali.piggyland.Controls.OnItemClickListener mItemClickListener) {
//        this.onItemClickListener = mItemClickListener;
//    }
}