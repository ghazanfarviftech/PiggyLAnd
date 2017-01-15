package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MygallaryAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;

import java.util.ArrayList;

/**
 * Created by Amir.jehangir on 1/11/2017.
 */
public class MyGallery extends MasterActivity {

    RecyclerView rv_list_gallary;
    private GridLayoutManager lLayout;
    ArrayList<mygallarylist> smartToolsList;
    MygallaryAdapter mygallaryAdapter;


//    public MyGallery() {
//        // Required empty public constructor
//    }

    public static MyGallery newInstance() {

        return new MyGallery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygallery_activity);
        initUI();


    }

    @Override
    public void initUI() {
        super.initUI();
        smartToolsList = new ArrayList<>();
        rv_list_gallary = (RecyclerView)findViewById(R.id.rv_listview_mygallary);
        rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(this));

        smartToolsList = prepareListData();
        mygallaryAdapter = new MygallaryAdapter(this,smartToolsList);
        rv_list_gallary.setAdapter(mygallaryAdapter);

        mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MyGallery.this,""+position,Toast.LENGTH_LONG).show();
            }
        });



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
    @Override
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
    }

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

}
