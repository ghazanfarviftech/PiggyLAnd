package com.example.ghazanfarali.piggyland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn_gallery,btn_new,btn_mall,btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_gallery = (Button)findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(reportsClickListener);/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        btn_new = (Button)findViewById(R.id.btn_new);
        btn_new.setOnClickListener(btn_newClickListener);/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DrawingActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    boolean bottom_sheet = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            if(!bottom_sheet)
            {
                LinearLayout bottomSheet = (LinearLayout)findViewById(R.id.bottomSheetLayout);
                bottomSheet.setVisibility(View.VISIBLE);
                //Adapter.setOnItemClickListener(null);
                btn_gallery.setOnClickListener(null);
                btn_new.setOnClickListener(null);
                LinearLayout contact_us = (LinearLayout) bottomSheet.findViewById(R.id.contact_us);
                contact_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                });
                LinearLayout about_us = (LinearLayout) bottomSheet.findViewById(R.id.about_uss);
                about_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                });

                LinearLayout homes = (LinearLayout) bottomSheet.findViewById(R.id.homes);
                homes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(i);
                    }
                });

                bottom_sheet = true;
            }else{

                LinearLayout bottomSheet = (LinearLayout)findViewById(R.id.bottomSheetLayout);
                bottomSheet.setVisibility(View.INVISIBLE);
               // Adapter.setOnItemClickListener(onItemClickListener);
                bottom_sheet = false;
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener reportsClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btn_newClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(MainActivity.this,DrawingActivity.class);
//            startActivity(intent);
        }
    };
}
