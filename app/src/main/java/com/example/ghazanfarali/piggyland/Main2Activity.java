package com.example.ghazanfarali.piggyland;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.davidecirillo.multichoicerecyclerview.MultiChoiceRecyclerView;
import com.davidecirillo.multichoicerecyclerview.MultiChoiceToolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static android.view.View.inflate;

public class Main2Activity extends ActionBarActivity {
    MultiChoiceRecyclerView mMultiChoiceRecyclerView;
    GalleryAdapter  adapter;
    int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);
        loadPhotosObjects();
        GridView gridView = (GridView)findViewById(android.R.id.list);
        //List<Building> buildings = // Create list of buildings, check sample project to see how it's done
        adapter = new GalleryAdapter(savedInstanceState, photosList);
        adapter.setAdapterView(gridView);
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, "Item click: " + adapter.getItem(position).getDescription(), Toast.LENGTH_SHORT).show();
                generatePhotoDetail();
                showImage(adapter.getItem(position).getImageUrl().getAbsolutePath(),adapter.getItem(position).getDescription(),position);
            }
        });

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMultiChoiceRecyclerView = (MultiChoiceRecyclerView) findViewById(R.id.multiChoiceRecyclerView);
        mMultiChoiceRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        //Toolbar  toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        MultiChoiceToolbar multiChoiceToolbar = new MultiChoiceToolbar.Builder(this, null)
                .setDefaultToolbarTitle("gallery")
                .setSelectedToolbarTitle("Selected")
                .setMultiPrimaryColor(R.color.colorPrimary)
                .setMultiPrimaryColorDark(R.color.colorPrimaryDark)
                .setIcon(R.drawable.ic_arrow_back_white_24dp, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // onBackPressed();
                        finish();
                    }
                })
                .build();
        mMultiChoiceRecyclerView.setMultiChoiceToolbar(multiChoiceToolbar);
        loadPhotosObjects();*/
    }
    private ArrayList<Photo> photosList;
    private void loadPhotosObjects() {
        photosList = new ArrayList<>();
        //generatePhotosObjects();
        generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand")));
        //photoViewSlider.initializePhotos(photosList);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        adapter.save(outState);
    }


    private List<File> loadAllFilesFromFolder(File parentDir)
    {


        List<File> inFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.addAll(Arrays.asList(parentDir.listFiles()));
        while (!files.isEmpty()) {
            File file = files.remove();
            if (file.isDirectory()) {
                files.addAll(Arrays.asList(file.listFiles()));
            } else if (file.getName().endsWith(".jpg")) {
                inFiles.add(file);
            }
        }
        return inFiles;
    }

    public void generatePhotosObjects(List<File> files) {
        photosList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            Photo photo1 = new Photo();
            photo1.setImageUrl(files.get(i));
            photo1.setDescription("Android  Ice Cream Sandwich");
            photosList.add(photo1);
        }

        //SampleGalleryAdapter adapter = new SampleGalleryAdapter(Main2Activity.this,photosList);
        //mMultiChoiceRecyclerView.setAdapter(adapter);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_SHORT).show();
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentActivityIntent = new Intent(this, MallActivity.class);
                parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(parentActivityIntent);
                finish();
                return true;

        }
        return false;
    }

    View viewDialog;
    ImageView imgPhoto;
    TextView txtDescriptionGallery,txtCurrentPosition,txtPhotosTotal;
    ImageButton btnShare;
    Dialog builder;


    private float dx, dy, ux, uy;
    private final int MIN_DISTANCE = 100;


    private void generatePhotoDetail() {
        viewDialog = inflate(Main2Activity.this, R.layout.photo_detail, null);
        imgPhoto = (ImageView) viewDialog.findViewById(R.id.img_photo_gallery_detail);
        txtDescriptionGallery = (TextView) viewDialog.findViewById(R.id.txt_photo_gallery_description);
        txtCurrentPosition = (TextView) viewDialog.findViewById(R.id.txt_photo_current_position);
        txtPhotosTotal = (TextView) viewDialog.findViewById(R.id.txt_photo_total);
        btnShare = (ImageButton) viewDialog.findViewById(R.id.btn_share);

        builder = new Dialog(Main2Activity.this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


    private void showImage(String url, String description, int currentPositions) {
        txtDescriptionGallery.setText(description);
        txtCurrentPosition.setText(String.valueOf(currentPositions+1));
        txtPhotosTotal.setText(String.valueOf(photosList.size()));
       /* Picasso.with(getContext()).load(url).fit()
                .placeholder(R.drawable.photodefault)
                .into(imgPhoto);*/
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(url, options);
        //selected_photo.setImageBitmap(bitmap);
        imgPhoto.setImageBitmap(bitmap);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewDialog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                if (action == MotionEvent.ACTION_DOWN){
                    dx = motionEvent.getX();
                    dy = motionEvent.getY();
                    return true;

                }else if (action == MotionEvent.ACTION_UP) {
                    ux = motionEvent.getX();
                    uy = motionEvent.getY();

                    float deltaX = dx - ux;
                    float deltaY = dy - uy;

                    if (Math.abs(deltaX) > Math.abs(deltaY))
                        if (Math.abs(deltaX) > MIN_DISTANCE)
                            // Before
                            if (deltaX < 0) {
                                if(currentPosition == 0)
                                    currentPosition = photosList.size() -1;
                                else
                                    currentPosition --;

                                Photo photo = photosList.get(currentPosition);
                                showImage(photo.getImageUrl().getAbsolutePath(), photo.getDescription(), currentPosition);
                                return true;
                            }

                    // Next
                    if (deltaX > 0) {
                        if(currentPosition == photosList.size()-1)
                            currentPosition = 0;
                        else
                            currentPosition ++;

                        Photo photo = photosList.get(currentPosition);
                        showImage(photo.getImageUrl().getAbsolutePath(), photo.getDescription(), currentPosition);
                        return true;
                    }
                }
                return false;
            }
        });

        builder.setContentView(viewDialog);
        builder.show();
       /* YoYo.with(techniqueAnimation)
                .duration(700)
                .playOn(imgPhoto);*/
    }

}
