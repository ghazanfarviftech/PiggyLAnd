package com.example.ghazanfarali.piggyland;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class GalleryActivity extends AppCompatActivity {

    private PhotosViewSlider photoViewSlider;
    private List<Photo> photosList;
    private ArrayList<String> stringsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        photoViewSlider = (PhotosViewSlider) findViewById(R.id.photosViewSlider);


        //generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand")));
       // loadPhotosUrls();
        //loadPhotosObjects();
        //loadPhotosDynamically();
        loadPhotosObjects();
        //photoViewSlider.setTechniqueAnimation(Techniques.Landing);
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
        for(int i=0;i<files.size();i++)
        {
            Photo photo1 = new Photo();
            photo1.setImageUrl(files.get(i));
            photo1.setDescription("Android  Ice Cream Sandwich");
            photosList.add(photo1);
        }
       /* Photo photo1 = new Photo();
        photo1.setImageUrl("http://modmyi.com/attachments/forums/iphone-4-4s-new-skins-themes-launches/555329d1322802429-ice-cream-sandwich-android-4-0-a-android_ice_cream_sandwich_electronic_bytes.png");
        photo1.setDescription("Android  Ice Cream Sandwich");

        Photo photo2 = new Photo();
        photo2.setImageUrl("http://cdn.gigjets.com/wp-content/uploads/2012/10/Android-Jelly-Bean-Logo-Sort-Of.jpg");
        photo2.setDescription("Jelly Bean");

        Photo photo3 = new Photo();
        photo3.setImageUrl("http://cdn.ndtv.com/tech/images/gadgets/android-4.4-teaser-big.jpg");
        photo3.setDescription("Kit Kat");

        Photo photo4 = new Photo();
        photo4.setImageUrl("http://thenextweb.com/wp-content/blogs.dir/1/files/2015/08/android-marshmallow-mascot.jpg");
        photo4.setDescription("Android Marshmallow");

        Photo photo5 = new Photo();
        photo5.setImageUrl("http://static.trustedreviews.com/94/000039bf9/f93f_orh370w630/n.jpg");
        photo5.setDescription("Android Nougat");

        photosList.add(photo1);
        photosList.add(photo2);
        photosList.add(photo3);
        photosList.add(photo4);
        photosList.add(photo5);*/
    }
    private void loadPhotosObjects() {
        photosList = new ArrayList<>();
        //generatePhotosObjects();
        generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand")));
        photoViewSlider.initializePhotos(photosList);
    }

    private void loadPhotosUrls() {
        stringsList = new ArrayList<>();
        /*generatePhotosUrls();
        photoViewSlider.initializePhotosUrls(stringsList);*/
    }

    private void loadPhotosDynamically() {
        /*photoViewSlider.setPhotoUrl("http://www.ford.com/resources/ford/general/newvehicles/Future_Landing_Page/fordGT_large.jpg?v=1451486780000");
        photoViewSlider.setPhotoUrl("http://cdn.wallpapersafari.com/16/87/UAHQ9m.jpeg", "Android 3D");
        photoViewSlider.setPhotoUrl("http://kingofwallpapers.com/3d-wallpapers-for-android/3d-wallpapers-for-android-027.jpg");*/
        photoViewSlider.initializePhotos();
    }


   /* private void generatePhotosUrls() {
        stringsList.add("data:image/jpeg;base64,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");
        stringsList.add("data:image/jpeg;base64,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");
        stringsList.add("https://i.ytimg.com/vi/3BNo2cNVp9o/maxresdefault.jpg");
        stringsList.add("data:image/jpeg;base64,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");
    }

    public void generatePhotosObjects() {
        Photo photo1 = new Photo();
        photo1.setImageUrl("http://modmyi.com/attachments/forums/iphone-4-4s-new-skins-themes-launches/555329d1322802429-ice-cream-sandwich-android-4-0-a-android_ice_cream_sandwich_electronic_bytes.png");
        photo1.setDescription("Android  Ice Cream Sandwich");

        Photo photo2 = new Photo();
        photo2.setImageUrl("http://cdn.gigjets.com/wp-content/uploads/2012/10/Android-Jelly-Bean-Logo-Sort-Of.jpg");
        photo2.setDescription("Jelly Bean");

        Photo photo3 = new Photo();
        photo3.setImageUrl("http://cdn.ndtv.com/tech/images/gadgets/android-4.4-teaser-big.jpg");
        photo3.setDescription("Kit Kat");

        Photo photo4 = new Photo();
        photo4.setImageUrl("http://thenextweb.com/wp-content/blogs.dir/1/files/2015/08/android-marshmallow-mascot.jpg");
        photo4.setDescription("Android Marshmallow");

        Photo photo5 = new Photo();
        photo5.setImageUrl("http://static.trustedreviews.com/94/000039bf9/f93f_orh370w630/n.jpg");
        photo5.setDescription("Android Nougat");

        photosList.add(photo1);
        photosList.add(photo2);
        photosList.add(photo3);
        photosList.add(photo4);
        photosList.add(photo5);
    }*/
}