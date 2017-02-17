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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ghazanfarali.piggyland.Controls.GallaryClickListner;
import com.example.ghazanfarali.piggyland.Photo;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MygallaryAdapter;
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

    ArrayList<Photo> photosList = new ArrayList<Photo>();
    ArrayList<Photo> selectionList = new ArrayList<Photo>();
    private GridLayoutManager lLayout;
    int counter = 0;
    MasterFragment mContext;
    public static boolean is_in_action_mode = false;

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
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            tv_no_data_smart_tools = (TextView) view.findViewById(R.id.tv_no_data_smart_tools);
            lLayout = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(lLayout);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new MyGallaryITemDecor(getActivity()));
            counterTextView = (TextView) view.findViewById(R.id.cnt_text);
            counterTextView.setVisibility(View.GONE);
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
//        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        toolbar.setTitle("My Gallary");
//        setSupportActionBar(toolbar);
        // initialise recycler view

        if (prepareListData() != null) {

            adapter = new MygallaryAdapter(photosList, mContext);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new GallaryClickListner() {
                @Override
                public void onItemClick(View view, int position) {

                    is_in_action_mode = true;

                    userProfileActivity.InflateTool();

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

        //smartToolsList = new ArrayList<>();

        /*rv_list_gallary = (RecyclerView)findViewById(R.id.recyclerview);
        rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(this));

      //  smartToolsList = prepareListData();
        mygallaryAdapter = new MygallaryAdapter(photosList,this);
        rv_list_gallary.setAdapter(mygallaryAdapter);*/

        /*mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Toast.makeText(MyGallery.this,smartToolsList.indexOf(position),Toast.LENGTH_LONG).show();
                generatePhotoDetail();
                Photo image = photosList.get(position);
                showImage(image.getImageUrl().getAbsolutePath(),image.getDescription(),position);
            }
        });*/


//                .setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                if (position == 0) {
//                    //getDockActivity().addDockableFragment(getString(R.string.Smarttool), new QrReaderFragment().newInstance());
//                    Toast.makeText(MyGallery.this,smartToolsList.indexOf(position),Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });


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

    private void showImage(String url, String description, int currentPositions) {
        txtDescriptionGallery.setText(description);
        txtCurrentPosition.setText(String.valueOf(currentPositions + 1));
        txtPhotosTotal.setText(String.valueOf(photosList.size()));

        Glide
                .with(MyGallery.this) // safer!
                .load(url)
                .asBitmap()
                .into(imgPhoto);

       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(url, options);
        //selected_photo.setImageBitmap(bitmap);
        imgPhoto.setImageBitmap(bitmap);*/
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewDialog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                if (action == MotionEvent.ACTION_DOWN) {
                    dx = motionEvent.getX();
                    dy = motionEvent.getY();
                    return true;

                } else if (action == MotionEvent.ACTION_UP) {
                    ux = motionEvent.getX();
                    uy = motionEvent.getY();

                    float deltaX = dx - ux;
                    float deltaY = dy - uy;

                    if (Math.abs(deltaX) > Math.abs(deltaY))
                        if (Math.abs(deltaX) > MIN_DISTANCE)
                            // Before
                            if (deltaX < 0) {
                                if (currentPosition == 0)
                                    currentPosition = photosList.size() - 1;
                                else
                                    currentPosition--;

                                Photo photo = photosList.get(currentPosition);
                                showImage(photo.getImageUrl().getAbsolutePath(), photo.getDescription(), currentPosition);
                                return true;
                            }

                    // Next
                    if (deltaX > 0) {
                        if (currentPosition == photosList.size() - 1)
                            currentPosition = 0;
                        else
                            currentPosition++;

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

    }
   /* @Override
    public void onResume() {
        super.onResume();
        lLayout = new GridLayoutManager(this, 2);
        rv_list_gallary.setHasFixedSize(true);
        rv_list_gallary.setLayoutManager(lLayout);
//        int viewHeight = (int) (getResources().getDimension(R.dimen.x150) * 2);
//        rv_list_gallary.getLayoutParams().height = viewHeight;

//        if (prefHelper.getApplicationLanguage().equalsIgnoreCase(CommonConstants.LANG_ARABIC)) {
//            lLayout.setReverseLayout(true);
//        }
    }*/

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
            for (int i = 0; i < files.size(); i++) {
                Photo photo1 = new Photo();
                photo1.setImageUrl(files.get(i));
                photo1.setDescription("Android");
                photo1.setmygallarycheckbox(true);
                photosList.add(photo1);
            }
        }
        //SampleGalleryAdapter adapter = new SampleGalleryAdapter(Main2Activity.this,photosList);
        //mMultiChoiceRecyclerView.setAdapter(adapter);
    }

    //adding menu to toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
//        return true;
//    }


//    @Override
//    public boolean onLongClick(View v) {
//     //   userProfileActivity.InflateTool();
//        is_in_action_mode = true;
//        counterTextView.setVisibility(View.VISIBLE);
//        adapter.notifyDataSetChanged();
//        // home button on action bar
//        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        return true;
//    }

    public void prepareselection(View view, int position) {
       // userProfileActivity.InflateTool();
        //change view to checkbox
        if (((CheckBox) view).isChecked()) {
            selectionList.add(photosList.get(position));
           // counter++;
           // updateCnt(counter);
        } else {
            selectionList.remove(photosList.get(position));
           // counter--;
          //  updateCnt(counter);
        }
    }



    public void SendDataToAttachment(){
        MygallaryAdapter recyclerAdapter = (MygallaryAdapter) adapter;
        recyclerAdapter.updateAdapter(selectionList);
        AttachImagestoEmail();
       userProfileActivity.clearActionMain();
    }


    public void updateCnt(int counter) {
        if (counter == 0) {
            counterTextView.setText("0 item selected");
        } else {
            counterTextView.setText(counter + " item selected");
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

        Intent i = new Intent(getActivity(), PrintOrderActivity.class);
        i.putExtra("MyClass", selectionList);
        startActivity(i);

        //  startActivity(new Intent(this, PrintOrderActivity.class));
    }

    public void clearActionM() {
        is_in_action_mode = false;
//        toolbar.getMenu().clear();
//        toolbar.inflateMenu(R.menu.menu_activity_main);
        //remove home button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counterTextView.setVisibility(View.GONE);
        counterTextView.setText("0 item selected");
        counter = 0;
        selectionList.clear();
    }

//    @Override
//    public void onBackPressed() {
//        if(is_in_action_mode)
//        {
//            clearActionM();
//            adapter.notifyDataSetChanged();
//        }
//        else {
//            super.onBackPressed();
//        }
//
//    }

}


