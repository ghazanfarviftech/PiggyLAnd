package com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.GlobalUtils;
import com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.beans.Attachment;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amir.jehangir on 1/24/2017.
 */
public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.ViewHolder>{

    private LayoutInflater inflater;

    Context mContext;

    ArrayList<Attachment> listImages;
    private int leftImageWidth;

    public AttachmentAdapter(Context context, ArrayList<Attachment> attachments) {
        this.mContext = context;
        this.listImages = attachments;
        leftImageWidth = GlobalUtils.dpToPx(mContext,
                (int) mContext.getResources().getDimension(R.dimen.x60));
    }

    public void showDailog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("")
                .setMessage(mContext.getResources().getString(R.string.label_image_delete_msg))
                .setCancelable(true)
                .setPositiveButton(mContext.getResources().getString(R.string.alert_button_cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        })
                .setNegativeButton(
                        mContext.getResources().getString(R.string.lbl_ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // dialog.cancel();

                                listImages.remove(position);
                                notifyDataSetChanged();
                            }
                        });
        builder.setView(null);

        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.row_item_attachment_request_for_info, parent, false);

        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Attachment att = (Attachment) listImages.get(position);
        final int tempPosition = position;
        if (att!=null && att.getFileName()!=null && att.getFileName().isEmpty()) {
            File imgFile = new File(att.getFilePath());

            if (imgFile.exists()) {

                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap myBitmap = BitmapFactory.decodeFile(
                        imgFile.getAbsolutePath(), options);

                myBitmap = rotateImage(imgFile.getAbsolutePath());

                BitmapDrawable ob = new BitmapDrawable(mContext.getResources(), myBitmap);
                holder.ivImage.setImageBitmap(myBitmap);
                //viewHolder.ivImage.setImageBitmap(myBitmap);

            }
        } else {
            String url = att.getServerURL();
            if (url.contains(".pdf") || url.contains(".PDF")) {
                Picasso.with(mContext).load(R.drawable.pdf_icon)
                        .resize(leftImageWidth - 10, leftImageWidth - 10)
                        .into(holder.ivImage);

            } else {
                Ion.with(mContext)
                        .load(url)
                        .withBitmap()
                        .resize(leftImageWidth, leftImageWidth)
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder)
                        .intoImageView(holder.ivImage);
            }
        }


        holder.ivCross.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDailog(tempPosition);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        ImageView ivCross;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView
                    .findViewById(R.id.iv_row_item_attachment);

            ivCross = (ImageView) itemView.findViewById(R.id.iv_row_item_attachment_cross);
        }
    }


    //rotoate image for samsung devices
    public Bitmap rotateImage(String filePath) {

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bounds);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 8;
        opts.inJustDecodeBounds = false;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inDither = true;
        Bitmap bm = BitmapFactory.decodeFile(filePath, opts);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;

        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = null;
        try {
            //rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
            rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth() - 1, bm.getHeight() - 1, matrix, true);
        } catch (Exception e) {
          //  Log("Bitmap", e.toString() + "");
        }

        return rotatedBitmap;
    }
}
