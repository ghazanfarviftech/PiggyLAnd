package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryMultiSelectAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.MyGallaryMultiSelectITems;

import java.util.ArrayList;

/**
 * Created by Amir.jehangir on 1/15/2017.
 */
public class MyGalleryMultiSelect extends AppCompatActivity implements View.OnLongClickListener {
    public boolean is_in_action_mode = false;
    TextView counterTextView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Toolbar toolbar;
    ArrayList<MyGallaryMultiSelectITems> arrayList = new ArrayList<MyGallaryMultiSelectITems>();
    ArrayList<MyGallaryMultiSelectITems> selectionList = new ArrayList<MyGallaryMultiSelectITems>();
    private GridLayoutManager lLayout;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygallary_multi_select);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Gallary");
        setSupportActionBar(toolbar);
        // initialise recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
     //   layoutManager = new LinearLayoutManager(this);
        lLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MyGallaryITemDecor(this));
        counterTextView = (TextView) findViewById(R.id.cnt_text);
        counterTextView.setVisibility(View.GONE);
        String [] Name=getResources().getStringArray(R.array.names);
        int i=0;
//        for(String NAME:Name)
//        {
//            MyGallaryMultiSelectITems contact = new MyGallaryMultiSelectITems();
//            contact.se
//            arrayList.add(contact);
//            i++;
//        }
        adapter = new MyGallaryMultiSelectAdapter(prepareListData(),MyGalleryMultiSelect.this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<MyGallaryMultiSelectITems> prepareListData() {

        ArrayList<MyGallaryMultiSelectITems> temp = new ArrayList<>();

        MyGallaryMultiSelectITems mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("1");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("2");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.photodefault);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("3");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.car_1);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("4");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("5");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.photodefault);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("6");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.car_1);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("7");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);


        arrayList.addAll(temp);
        return temp;
    }


    //adding menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        is_in_action_mode = true;
        counterTextView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        // home button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareselection(View view,int position)
    {
        //change view to checkbox
        if(((CheckBox)view).isChecked())
        {
            selectionList.add(arrayList.get(position));
            counter++;
            updateCnt(counter);
        }
        else {
            selectionList.remove(arrayList.get(position));
            counter--;
            updateCnt(counter);
        }
    }

    public void updateCnt(int counter)
    {
        if(counter==0)
        {
            counterTextView.setText("0 item selected");
        }
        else {
            counterTextView.setText(counter + " item selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.it_delete)
        {

            MyGallaryMultiSelectAdapter recyclerAdapter = (MyGallaryMultiSelectAdapter) adapter;
            recyclerAdapter.updateAdapter(selectionList);
            clearActionM();
        }
        else if(item.getItemId() == android.R.id.home)
        {
            clearActionM();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearActionM()
    {
        is_in_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        //remove home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counterTextView.setVisibility(View.GONE);
        counterTextView.setText("0 item selected");
        counter = 0;
        selectionList.clear();
    }

    @Override
    public void onBackPressed() {
        if(is_in_action_mode)
        {
            clearActionM();
            adapter.notifyDataSetChanged();
        }
        else {
            super.onBackPressed();
        }

    }
}
