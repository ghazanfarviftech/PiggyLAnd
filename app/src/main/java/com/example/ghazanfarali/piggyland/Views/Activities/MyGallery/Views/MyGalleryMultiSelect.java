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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryMultiSelectAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.MyGallaryMultiSelectITems;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;
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
    Button btn_menuimg;
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

        //initUI();
    }


    public void initUI(){
        loadPhotosObjects();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Gallary");
        setSupportActionBar(toolbar);

        // initialise recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        tv_no_data_smart_tools = (TextView)findViewById(R.id.tv_no_data_smart_tools);
        btn_menuimg = (Button)findViewById(R.id.btn_menuimg);
        //   layoutManager = new LinearLayoutManager(this);
        lLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MyGallaryITemDecor(this));
        counterTextView = (TextView) findViewById(R.id.cnt_text);
        counterTextView.setVisibility(View.VISIBLE);
        counterTextView.setText("My Gallery");
        adapter = new MyGallaryMultiSelectAdapter(arrayList,MyGalleryMultiSelect.this);
        recyclerView.setAdapter(adapter);
       /* if(prepareListData() != null){

            adapter = new MyGallaryMultiSelectAdapter(arrayList,MyGalleryMultiSelect.this);
            recyclerView.setAdapter(adapter);
        }else {
            tv_no_data_smart_tools.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }*/
    }
    private void loadPhotosObjects() {
        arrayList = new ArrayList<>();
        //generatePhotosObjects();
        generatePhotosObjects(loadAllFilesFromFolder(new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand")));
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
            Toast.makeText(MyGalleryMultiSelect.this,"No Image Found",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            arrayList = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                MyGallaryMultiSelectITems photo1 = new MyGallaryMultiSelectITems();
                photo1.setmygallaryImageURL(files.get(i).toString());
                photo1.setmygallaryTitle("Android");
                photo1.setmygallarycheckbox(true);
                arrayList.add(photo1);
            }
        }
        //SampleGalleryAdapter adapter = new SampleGalleryAdapter(Main2Activity.this,photosList);
        //mMultiChoiceRecyclerView.setAdapter(adapter);
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

    @Override
    protected void onResume() {
        super.onResume();

        initUI();
        //adapter.notifyDataSetChanged();
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
        btn_menuimg.setVisibility(View.GONE);
        counterTextView.setText("0 item selected");

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
        MyGallaryMultiSelectAdapter recyclerAdapter = (MyGallaryMultiSelectAdapter) adapter;
        recyclerAdapter.updateAdapter(selectionList);


        Intent i = new Intent(this, PrintOrderActivity.class);
        i.putExtra("MyClass", selectionList);
        startActivity(i);
        clearActionM();
    }

    public void clearActionM()
    {
        is_in_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        //remove home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counterTextView.setVisibility(View.VISIBLE);
        btn_menuimg.setVisibility(View.VISIBLE);
        counterTextView.setText("My Gallery");
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
