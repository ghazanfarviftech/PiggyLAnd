package com.example.ghazanfarali.piggyland;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.manuelpeinado.multichoiceadapter.extras.actionbarcompat.MultiChoiceBaseAdapter;

import java.util.ArrayList;

/**
 * Created by ghazanfarali on 30/12/2016.
 */

public class GalleryAdapter extends MultiChoiceBaseAdapter {
    ArrayList<Photo> photosList;

    public GalleryAdapter(Bundle savedInstanceState, ArrayList<Photo> photosList) {
        super(savedInstanceState);
        this.photosList = photosList;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.my_action_mode, menu);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            //Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Create Book");
            builder.setMessage("Are you sure you want to create a book ?");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent is = new Intent(getContext(),MallActivity.class);
                    getContext().startActivity(is);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create();
            builder.show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public int getCount() {
        return photosList.size();
    }

    @Override
    public Photo getItem(int position) {
        return photosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected View getViewImpl(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int layout = R.layout.item_layout;
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView imageView = (ImageView) convertView;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photosList.get(position).getImageUrl().getAbsolutePath(), options);
        //selected_photo.setImageBitmap(bitmap);
        //holder.photo.setImageBitmap(bitmap);
       // holder.mImageView.setImageBitmap(bitmap);
        imageView.setImageBitmap(bitmap);
        return imageView;
    }
}
