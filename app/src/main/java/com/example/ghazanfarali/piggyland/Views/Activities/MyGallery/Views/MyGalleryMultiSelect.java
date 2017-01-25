package com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.Views;

import android.content.Intent;
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
import com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.PrintOrderActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Amir.jehangir on 1/15/2017.
 */
public class MyGalleryMultiSelect extends AppCompatActivity implements View.OnLongClickListener {
    public boolean is_in_action_mode = false;
    TextView counterTextView,tv_no_data_smart_tools;
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
        tv_no_data_smart_tools = (TextView)findViewById(R.id.tv_no_data_smart_tools);
     //   layoutManager = new LinearLayoutManager(this);
        lLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MyGallaryITemDecor(this));
        counterTextView = (TextView) findViewById(R.id.cnt_text);
        counterTextView.setVisibility(View.GONE);
        if(prepareListData() != null){

            adapter = new MyGallaryMultiSelectAdapter(prepareListData(),MyGalleryMultiSelect.this);
            recyclerView.setAdapter(adapter);
        }else {
            tv_no_data_smart_tools.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    private ArrayList<MyGallaryMultiSelectITems> prepareListData() {

        ArrayList<MyGallaryMultiSelectITems> temp = new ArrayList<>();
        List<File> files =loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand"));

        MyGallaryMultiSelectITems mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("1");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(0).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("2");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(1).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("3");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(2).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("4");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(3).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("5");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(4).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("6");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(5).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);

        mModel = new MyGallaryMultiSelectITems();
        mModel.setmygallaryId("7");
        mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(files.get(6).toString());
        mModel.setmygallarycheckbox(true);
        temp.add(mModel);


        arrayList.addAll(temp);
        return temp;
    }

//    public void generatePhotosObjects(List<File> files) {
//        photosList = new ArrayList<>();
//        for(int i=0;i<files.size();i++)
//        {
//            Photo photo1 = new Photo();
//            photo1.setImageUrl(files.get(i));
//            photo1.setDescription("Android  Ice Cream Sandwich");
//            photosList.add(photo1);
//        }
//
//    }


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
            AttachImagestoEmail();
            clearActionM();
        }
        else if(item.getItemId() == android.R.id.home)
        {
            clearActionM();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    private void AttachImagestoEmail(){
      //  Gson gson = new Gson();
       // String myJson = gson.toJson(selectionList);

        Intent i = new Intent(this, PrintOrderActivity.class);
        i.putExtra("MyClass", selectionList);
        startActivity(i);

      //  startActivity(new Intent(this, PrintOrderActivity.class));
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
