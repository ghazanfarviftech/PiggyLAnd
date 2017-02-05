package com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.CustomViews.CustomEditText;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.BaseMasterActivity.MasterActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.MyGallaryMultiSelectITems;
import com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.adapter.AttachmentAdapter;
import com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.beans.Attachment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir.jehangir on 1/23/2017.
 */
public class PrintOrderActivity extends MasterActivity implements OnItemClickListener {
    Toolbar toolbar;
    EditText ed_subject,ed_email,ed_descriptiveText;
    RecyclerView rv_sendImageEmail_attachment;

    ArrayList<Attachment> sitePlanList = new ArrayList<>();
    AttachmentAdapter sitePlanAdapter;
    ArrayList<MyGallaryMultiSelectITems> selectionList = new ArrayList<MyGallaryMultiSelectITems>();
    private GridLayoutManager lLayout;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> arrlistImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_order_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar_sendEmail);
      //  toolbar.setTitle("My Gallary");
        setSupportActionBar(toolbar);

      //  Gson gson = new Gson();
      //  ArrayList<MyGallaryMultiSelectITems> abc = gson.fromJson(getIntent().getStringExtra("MyClass"),  MyGallaryMultiSelectITems.class);
        Intent intent=getIntent();
        selectionList= (ArrayList<MyGallaryMultiSelectITems>) intent.getSerializableExtra("MyClass");
        if(selectionList != null){



        }

        initUI();
        initListner();

    }

    @Override
    public void initUI() {
        super.initUI();
        ed_email = (EditText)findViewById(R.id.ed_email);
        ed_subject = (EditText)findViewById(R.id.ed_subject);
        ed_descriptiveText = (EditText)findViewById(R.id.ed_descriptiveText);
        rv_sendImageEmail_attachment= (RecyclerView) findViewById(R.id.rv_sendImageEmail_attachment);
    }


    private void initListner(){
        arrlistImages = new ArrayList<>();


        lLayout = new GridLayoutManager(this, 2);
        rv_sendImageEmail_attachment.setLayoutManager(lLayout);
        rv_sendImageEmail_attachment.setHasFixedSize(true);
        rv_sendImageEmail_attachment.addItemDecoration(new MyGallaryITemDecor(this));


      //  rv_sendImageEmail_attachment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
     for(int i =0; i<selectionList.size(); i++){
         Attachment att = new Attachment(selectionList.get(i).getmygallaryTitle(),selectionList.get(i).getmygallaryImageURL(),selectionList.get(i).getmygallaryImageURL());
         sitePlanList.add(att);
         arrlistImages.add(selectionList.get(i).getmygallaryImageURL());
     }

        sitePlanAdapter = new AttachmentAdapter(this, sitePlanList);
        rv_sendImageEmail_attachment.setAdapter(sitePlanAdapter);




    }

    //adding menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_email, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.it_send_email)
        {
            Toast.makeText(this,"send",Toast.LENGTH_LONG).show();
            CustomEditText ed_subject = (CustomEditText)findViewById(R.id.ed_subject);

            Intent emailIntent;
            emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "print@piggyland.co.kr" });
            // emailIntent.setType("application/zip");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, ed_subject.getText().toString());
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                    ed_descriptiveText.getText().toString());
            ArrayList<Uri> uris = new ArrayList<Uri>();
            for (String  filepath: arrlistImages) {
                File file = new File(filepath);
                Uri csvURI = Uri.fromFile(file);
                uris.add(csvURI);
            }
            emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            emailIntent.setType("text/html");
            try {
                //  CommonMethods.openGmailAppIntent(this, emailIntent);
                openGmailAppIntent(this, emailIntent);
            } catch (Exception e) {

            }

//            Intent emailIntent;
//            emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
//            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "amirjehangir007@gmail.com" });
//            // emailIntent.setType("application/zip");
//            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "mydata");
//            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
//                    "Images attach...");
//            ArrayList<Uri> uris = new ArrayList<Uri>();
//            for (String  filepath: arrlistImages) {
//                File file = new File(filepath);
//                Uri csvURI = Uri.fromFile(file);
//                uris.add(csvURI);
//            }
//            emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
//            emailIntent.setType("text/html");
//            try {
//              //  CommonMethods.openGmailAppIntent(this, emailIntent);
//                openGmailAppIntent(this, emailIntent);
//            } catch (Exception e) {
//
//            }

        }
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.rv_sendImageEmail_attachment: {

            }

        }
    }



    public static void openGmailAppIntent(Context context, Intent intent) {
        final PackageManager pm = context.getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
        ResolveInfo best = null;
        for (final ResolveInfo info : matches)
            if (info.activityInfo.packageName.endsWith(".gm")
                    || info.activityInfo.name.toLowerCase().contains("gmail"))
                best = info;
        if (best != null)
            intent.setClassName(best.activityInfo.packageName,
                    best.activityInfo.name);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(Intent
        // .createChooser(emailIntent, "Email to Send"));
        try {

            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {

//            CommonMethods.showMessageForValiDation(context,
//                    CommonVariable.VALIDATION_NO_GMAIL_APP,
//                    CommonVariable.iXPENSE_APP_FOLDER);

        } catch (Exception e) {

        }

    }


}
