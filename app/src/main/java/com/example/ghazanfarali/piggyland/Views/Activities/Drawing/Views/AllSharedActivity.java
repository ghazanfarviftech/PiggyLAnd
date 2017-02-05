package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean.ShareArtWork;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.ShareArtWorkAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Amir.jehangir on 2/5/2017.
 */
public class AllSharedActivity extends AppCompatActivity {

    RecyclerView rv_listview_shareArtWork;
    private LinearLayoutManager lLayout;
    ArrayList<ShareArtWork> smartToolsList;
    ShareArtWorkAdapter shareArtWorkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPhotosObjects();
        setContentView(R.layout.activity_all_shared2);
        //smartToolsList = new ArrayList<>();
        rv_listview_shareArtWork = (RecyclerView)findViewById(R.id.rv_listview_shareArtWork);

        rv_listview_shareArtWork.addItemDecoration(new MyGallaryITemDecor(this));
        lLayout = new LinearLayoutManager(this);
        rv_listview_shareArtWork.setHasFixedSize(true);
        rv_listview_shareArtWork.setLayoutManager(lLayout);
        //smartToolsList = prepareListData();
        shareArtWorkAdapter = new ShareArtWorkAdapter(this,photosList);
        rv_listview_shareArtWork.setAdapter(shareArtWorkAdapter);

       /* rv_listview_shareArtWork.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(AllSharedActivity.this,""+position, Toast.LENGTH_LONG).show();
            }
        });*/

    }

    /* private ArrayList<ShareArtWork> prepareListData() {

         ArrayList<ShareArtWork> temp = new ArrayList<>();

         ShareArtWork mModel = new ShareArtWork();
         //mModel.setmygallaryId("1");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.piggylandbg);
         temp.add(mModel);

         mModel = new ShareArtWork();
        // mModel.setmygallaryId("2");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.photodefault);
         temp.add(mModel);

         mModel = new ShareArtWork();
        // mModel.setmygallaryId("3");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.car_1);
         temp.add(mModel);

         mModel = new ShareArtWork();
       //  mModel.setmygallaryId("4");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.piggylandbg);
         temp.add(mModel);

         mModel = new ShareArtWork();
         //mModel.setmygallaryId("5");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.photodefault);
         temp.add(mModel);

         mModel = new ShareArtWork();
         //mModel.setmygallaryId("6");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.car_1);
         temp.add(mModel);

         mModel = new ShareArtWork();
         //mModel.setmygallaryId("7");
         mModel.setmygallaryTitle("Drawing");
         mModel.setmygallaryImageURL(R.drawable.piggylandbg);
         temp.add(mModel);

         return temp;
     }*/
    private ArrayList<ShareArtWork> photosList;
    private void loadPhotosObjects() {
        photosList = new ArrayList<>();
        //generatePhotosObjects();
        generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare")));
        //photoViewSlider.initializePhotos(photosList);

    }

    private List<File> loadAllFilesFromFolder(File parentDir)
    {


        List<File> inFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        if(parentDir.listFiles() == null)
        {

        }else {
            files.addAll(Arrays.asList(parentDir.listFiles()));
            while (!files.isEmpty()) {
                File file = files.remove();
                if (file.isDirectory()) {
                    files.addAll(Arrays.asList(file.listFiles()));
                } else if (file.getName().endsWith(".jpg")) {
                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    public void generatePhotosObjects(List<File> files) {
        if(files == null|| files.size() == 0)
        {
            Toast.makeText(AllSharedActivity.this,"No Image Found",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            photosList = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                ShareArtWork photo1 = new ShareArtWork();
                photo1.setmygallaryImageURL(files.get(i));
                photo1.setmygallaryTitle("Android");
                photosList.add(photo1);
            }
        }
        //SampleGalleryAdapter adapter = new SampleGalleryAdapter(Main2Activity.this,photosList);
        //mMultiChoiceRecyclerView.setAdapter(adapter);
    }
}

