package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.R;

public class SaveDrawingActivity extends AppCompatActivity {

    String filePath,fileDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_drawing);
        ImageView image_preview = (ImageView)findViewById(R.id.image_preview);
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        filePath =bundle.getString("fileName");
        fileDir =bundle.getString("fileDir");

        Glide
                .with( SaveDrawingActivity.this ) // safer!
                .load( filePath)
                .asBitmap()
                .into( image_preview );
        /*intent.putExtra("MESSAGE","data");
        setResult(2,intent);
        finish();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

           /* case android.R.id.home:
                Intent parentActivityIntent = new Intent(this, MallActivity.class);
                parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                return true;*/
            case R.id.save_image:
                //   selectAll();
                EditText title_enter = (EditText)findViewById(R.id.title_enter);
               // title_enter.getText().toString();
                if(title_enter.getText().length() >0)
                {
                    if(fileDir.contentEquals("shareToPublic")) {


                        Intent intent = new Intent();
                        intent.putExtra("MESSAGE", title_enter.getText().toString());
                        intent.putExtra("FullPath", filePath);
                        setResult(3, intent);
                        finish();
                    }else if(fileDir.contentEquals("saveToGallery")){
                        Intent intent = new Intent();
                        intent.putExtra("MESSAGE", title_enter.getText().toString());
                        intent.putExtra("FullPath", filePath);
                        setResult(3, intent);
                        finish();
                    }
                }else{
                    Toast.makeText(SaveDrawingActivity.this,"Enter the title please",Toast.LENGTH_SHORT).show();
                }

                /*Intent p = new Intent(SaveDrawingActivity.this,StartActivity.class);
                startActivity(p);*/
                return true;
          /*  case R.id.menu_reset_list:
                // rebuildList(null);
                return true;*/
        }
        return false;
    }
}
