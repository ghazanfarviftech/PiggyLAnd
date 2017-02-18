package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.Controls.MultiClickListner;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean.ShareArtWork;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.ShareArtWorkAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.SharedArt_Comments_fragment.SharedComment_Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Amir.jehangir on 2/5/2017.
 */
public class AllSharedActivity extends MasterFragment {
    private View view;
    RecyclerView rv_listview_shareArtWork;
    private LinearLayoutManager lLayout;
    ArrayList<ShareArtWork> smartToolsList;
    ShareArtWorkAdapter shareArtWorkAdapter;
    Toolbar toolbar;
    TextView counterTextView;
    public TextView counter_likes,tv_like,counter_comments,comments;
    public ImageView img_share;
    int a = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_all_shared2, container, false);
            // mContext.getApplicationContext();
            loadPhotosObjects();
            initUI();
        shareArtWorkAdapter = new ShareArtWorkAdapter(getActivity(),photosList);
        rv_listview_shareArtWork.setAdapter(shareArtWorkAdapter);
        shareArtWorkAdapter.setonMulticlickListener(new MultiClickListner() {
            @Override
            public void onLikeItemClick( int position) {
                counter_likes =(TextView) view.findViewById(R.id.counter_likes);
                a++;
                counter_likes.setText(""+a );
            }

            @Override
            public void onCommentItemClick(View view, int position) {
                userProfileActivity.replaceFragmnet(new SharedComment_Fragment(), R.id.frameLayout, true);

            }

            @Override
            public void onShareItemClick(View view, int position) {

            }
        });
//            startService();
        } else {
            if (view != null)
                //  userProfileActivity.hideHeaderLayout();
                userProfileActivity.setHeaderTitle("");
        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();

         counter_likes =(TextView) view.findViewById(R.id.counter_likes);
        tv_like =(TextView) view.findViewById(R.id.tv_like);
        counter_comments=(TextView) view.findViewById(R.id.counter_comments);
        comments=(TextView)view.findViewById(R.id.comments);
        img_share = (ImageView)view.findViewById(R.id.img_share);
        rv_listview_shareArtWork = (RecyclerView)view.findViewById(R.id.rv_listview_shareArtWork);

    }


    @Override
    public void onResume() {
        super.onResume();
        rv_listview_shareArtWork.addItemDecoration(new MyGallaryITemDecor(getActivity()));
        lLayout = new LinearLayoutManager(getActivity());
        rv_listview_shareArtWork.setHasFixedSize(true);
        rv_listview_shareArtWork.setLayoutManager(lLayout);
    }


//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        loadPhotosObjects();
//        setContentView(R.layout.activity_all_shared2);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("My Gallary");
//        setSupportActionBar(toolbar);
//        counterTextView = (TextView) findViewById(R.id.cnt_text);
//        counterTextView.setVisibility(View.VISIBLE);
//        counterTextView.setText("Shared Artworks");
//
//
//initUI();
//
//
//
//        //smartToolsList = new ArrayList<>();
//        rv_listview_shareArtWork = (RecyclerView)findViewById(R.id.rv_listview_shareArtWork);
//
//        rv_listview_shareArtWork.addItemDecoration(new MyGallaryITemDecor(this));
//        lLayout = new LinearLayoutManager(this);
//        rv_listview_shareArtWork.setHasFixedSize(true);
//        rv_listview_shareArtWork.setLayoutManager(lLayout);
//        //smartToolsList = prepareListData();
//        shareArtWorkAdapter = new ShareArtWorkAdapter(this,photosList);
//        rv_listview_shareArtWork.setAdapter(shareArtWorkAdapter);
//
//        shareArtWorkAdapter.setonMulticlickListener(new MultiClickListner() {
//            @Override
//            public void onLikeItemClick(View view, int position) {
//                tv_like = (TextView)view;
//                a++;
//                tv_like.setText(""+a );
//            }
//
//            @Override
//            public void onCommentItemClick(View view, int position) {
//
//            }
//
//            @Override
//            public void onShareItemClick(View view, int position) {
//
//            }
//        });
//
//
//
//    }


    private ArrayList<ShareArtWork> photosList;
    private void loadPhotosObjects() {
        photosList = new ArrayList<>();
        //generatePhotosObjects();
        generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare")));
        //photoViewSlider.initializePhotos(photosList);

    }


//    public void initUI(){
//        counter_likes =(TextView) findViewById(R.id.counter_likes);
//        tv_like =(TextView) findViewById(R.id.tv_like);
//        counter_comments=(TextView) findViewById(R.id.counter_comments);
//        comments=(TextView) findViewById(R.id.comments);
//        img_share = (ImageView)findViewById(R.id.img_share);
//    }

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
            Toast.makeText(getActivity(),"No Image Found",Toast.LENGTH_SHORT).show();
            getActivity().finish();
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

