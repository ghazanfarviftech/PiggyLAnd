package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.Controls.GallaryClickListner;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.GlobalUtils;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MygallaryAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.MyGallaryMultiSelectITems;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;
import com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.PrintOrderActivity;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static android.view.View.inflate;

/**
 * Created by Amir.jehangir on 1/11/2017.
 */
public class MyGallery extends MasterFragment {
    TextView counterTextView, tv_no_data_smart_tools;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MygallaryAdapter adapter;

    ArrayList<MyGallaryMultiSelectITems> photosList = new ArrayList<MyGallaryMultiSelectITems>();
    ArrayList<MyGallaryMultiSelectITems> selectionList = new ArrayList<MyGallaryMultiSelectITems>();
    private GridLayoutManager lLayout;
    int counter = 0;
    MasterFragment mContext;
    public static boolean is_in_action_mode = false;
    Button btn_menuimg,btn_menuBack;
    ImageView btnSelectedImgSend;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mygallery_activity);
//        initUI();
//
//
//    }
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.mygallery_activity, container, false);
            // mContext.getApplicationContext();
            userProfileActivity.fragmentType = "102";
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            tv_no_data_smart_tools = (TextView) view.findViewById(R.id.tv_no_data_smart_tools);
            lLayout = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(lLayout);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new MyGallaryITemDecor(getActivity()));
            counterTextView = (TextView) view.findViewById(R.id.cnt_text);
            counterTextView.setVisibility(View.GONE);
            btn_menuimg = (Button) view.findViewById(R.id.btn_menuimg);
            btn_menuBack = (Button) view.findViewById(R.id.btn_menuBack);
            btn_menuBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearActionM();
                }
            });
            btnSelectedImgSend = (ImageView) view.findViewById(R.id.btnSelectedImgSend);
            btn_menuimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userProfileActivity.clickEventSlide();
                }
            });

            btnSelectedImgSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // AttachImagestoEmail();
                    SendDataToAttachment();
                }
            });
            initUI();


//            startService();
        } else {
            if (view != null)
                //  userProfileActivity.hideHeaderLayout();
                userProfileActivity.setHeaderTitle("");
        }
        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        //   layoutManager = new LinearLayoutManager(this);

    }

    public void initUI() {
        super.initUI();
        loadPhotosObjects();

        if (prepareListData() != null) {

            adapter = new MygallaryAdapter(photosList, getActivity());
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new GallaryClickListner() {
                @Override
                public void onItemClick(View view, int position) {

                    is_in_action_mode = true;

                    //  userProfileActivity.InflateTool();
                    counterTextView.setVisibility(View.VISIBLE);
                    btn_menuimg.setVisibility(View.GONE);
                    btn_menuBack.setVisibility(View.VISIBLE);
                    counterTextView.setText("0 " + R.string.itemSelectedMYGallary);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCheckBoxClick(View view, int position) {
                    prepareselection(view, position);
                }
            });


        } else {
            tv_no_data_smart_tools.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }


    }

    View viewDialog;
    ImageView imgPhoto;
    TextView txtDescriptionGallery, txtCurrentPosition, txtPhotosTotal;
    ImageButton btnShare;
    Dialog builder;


    private float dx, dy, ux, uy;
    private final int MIN_DISTANCE = 100;


    private void generatePhotoDetail() {
        viewDialog = inflate(getActivity(), R.layout.photo_detail, null);
        imgPhoto = (ImageView) viewDialog.findViewById(R.id.img_photo_gallery_detail);
        txtDescriptionGallery = (TextView) viewDialog.findViewById(R.id.txt_photo_gallery_description);
        txtCurrentPosition = (TextView) viewDialog.findViewById(R.id.txt_photo_current_position);
        txtPhotosTotal = (TextView) viewDialog.findViewById(R.id.txt_photo_total);
        btnShare = (ImageButton) viewDialog.findViewById(R.id.btn_share);

        builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    int currentPosition = 0;




    private ArrayList<mygallarylist> prepareListData() {

        ArrayList<mygallarylist> temp = new ArrayList<>();

        mygallarylist mModel = new mygallarylist();
        mModel.setmygallaryId("1");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("2");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("3");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("4");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("5");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.photodefault);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("6");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.car_1);
        temp.add(mModel);

        mModel = new mygallarylist();
        mModel.setmygallaryId("7");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        temp.add(mModel);

        return temp;
    }

    //private ArrayList<Photo> photosList;
    private void loadPhotosObjects() {
        photosList = new ArrayList<>();
        //generatePhotosObjects();
        generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory() + "/", "PiggyLand")));
        //photoViewSlider.initializePhotos(photosList);

    }

    private List<File> loadAllFilesFromFolder(File parentDir) {


        List<File> inFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        if (parentDir.listFiles() == null) {

        } else {
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
        if (files == null || files.size() == 0) {
            Toast.makeText(getActivity(), "No Image Found", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        } else {
            photosList = new ArrayList<>();
            for (int i = files.size()-1; i >= 0; i--) {
                MyGallaryMultiSelectITems photo1 = new MyGallaryMultiSelectITems();
                photo1.setmygallaryImageURL(files.get(i).toString());
                photo1.setmygallaryTitle("Android");
                photo1.setmygallarycheckbox(true);
                photosList.add(photo1);
            }
        }

    }



    public void prepareselection(View view, int position) {
        // userProfileActivity.InflateTool();
        //change view to checkbox
        if (((CheckBox) view).isChecked()) {
            selectionList.add(photosList.get(position));
             counter++;
             updateCnt(counter);
        } else {
            selectionList.remove(photosList.get(position));
             counter--;
              updateCnt(counter);
        }
    }


    public void SendDataToAttachment() {

        MygallaryAdapter recyclerAdapter = (MygallaryAdapter) adapter;
        recyclerAdapter.updateAdapter(selectionList);
        AttachImagestoEmail();
        clearActionM();
        //  userProfileActivity.clearActionMain();
    }


    public void updateCnt(int counter) {
        if (counter == 0) {
            counterTextView.setText("0 " + R.string.itemSelectedMYGallary);
        } else {
            counterTextView.setText(counter + " "+ R.string.itemSelectedMYGallary);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.it_delete) {

            MygallaryAdapter recyclerAdapter = (MygallaryAdapter) adapter;
            recyclerAdapter.updateAdapter(selectionList);
            AttachImagestoEmail();
            clearActionM();
        } else if (item.getItemId() == android.R.id.home) {
            clearActionM();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    private void AttachImagestoEmail() {
        //  Gson gson = new Gson();
        // String myJson = gson.toJson(selectionList);
if(selectionList.size() >0){
    MygallaryAdapter recyclerAdapter = (MygallaryAdapter) adapter;
    recyclerAdapter.updateAdapter(selectionList);


    Intent i = new Intent(getActivity(), PrintOrderActivity.class);
    i.putExtra("MyClass", selectionList);
    startActivity(i);
    clearActionM();
}else {
    GlobalUtils.showErrorDialog(getActivity(),"Please Select Image","Alert");
}

        //  startActivity(new Intent(this, PrintOrderActivity.class));
    }

    public void clearActionM() {
        is_in_action_mode = false;
        btn_menuimg.setVisibility(View.VISIBLE);
        btn_menuBack.setVisibility(View.GONE);
        counterTextView.setVisibility(View.GONE);
        counterTextView.setText("0 item selected");
        counter = 0;
        selectionList.clear();
        adapter.notifyDataSetChanged();
    }



}


