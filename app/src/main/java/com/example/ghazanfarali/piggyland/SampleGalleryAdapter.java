package com.example.ghazanfarali.piggyland;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.davidecirillo.multichoicerecyclerview.MultiChoiceAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ghazanfar A on 12/29/2016.
 */
class SampleGalleryAdapter extends MultiChoiceAdapter<SampleGalleryAdapter.SampleGalleryViewHolder> {

    private final Context mContext;
    private ArrayList<Photo> imageList;
    List<Photo> photosLists;
   /* private Integer[] images = new Integer[]{
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };*/
    private ScaleAnimation mSelectScaleAnimation;
    private ScaleAnimation mDeselectScaleAnimation;

    SampleGalleryAdapter(Context mContext,ArrayList<Photo> photosList) {
        this.mContext = mContext;
        imageList = photosList;
       // setUpImageList();
    }

  /*  private void setUpImageList() {
        imageList = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < getItemCount(); i++) {
            imageList.add(images[r.nextInt(5)]);
        }
    }*/

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public SampleGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SampleGalleryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(SampleGalleryViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(imageList.get(position).getImageUrl().getAbsolutePath(), options);
        //selected_photo.setImageBitmap(bitmap);
        //holder.photo.setImageBitmap(bitmap);
        holder.mImageView.setImageBitmap(bitmap);

    }


    @Override
    public void setActive(@NonNull View view, boolean state) {

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        final ImageView tickImage = (ImageView) view.findViewById(R.id.tick_image);

        if (state) {
            imageView.startAnimation(getSelectScaleAnimation(view));
            tickImage.setVisibility(View.VISIBLE);
        } else {
            imageView.startAnimation(getDeselectScaleAnimation(view));
            tickImage.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected View.OnClickListener defaultItemViewClickListener(SampleGalleryViewHolder holder, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Click on item " + position, Toast.LENGTH_SHORT).show();
            }
        };
    }

    class SampleGalleryViewHolder extends RecyclerView.ViewHolder {


        ImageView mImageView;

        SampleGalleryViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }

    private ScaleAnimation getSelectScaleAnimation(View view) {
        if (mSelectScaleAnimation == null) {
            mSelectScaleAnimation = new ScaleAnimation(
                    view.getScaleX(), 0.7f,
                    view.getScaleY(), 0.7f,
                    view.getWidth() / 2,
                    view.getHeight() / 2
            );
            mSelectScaleAnimation.setFillAfter(true);
            mSelectScaleAnimation.setFillEnabled(true);
            mSelectScaleAnimation.setDuration(80);
        }
        return mSelectScaleAnimation;
    }

    private ScaleAnimation getDeselectScaleAnimation(View view) {
        if (mDeselectScaleAnimation == null) {
            mDeselectScaleAnimation = new ScaleAnimation(
                    view.getScaleX(), 1f,
                    view.getScaleY(), 1f,
                    view.getWidth() / 2,
                    view.getHeight() / 2
            );
            mDeselectScaleAnimation.setFillAfter(true);
            mDeselectScaleAnimation.setFillEnabled(true);
            mDeselectScaleAnimation.setDuration(80);
        }
        return mDeselectScaleAnimation;
    }
}
