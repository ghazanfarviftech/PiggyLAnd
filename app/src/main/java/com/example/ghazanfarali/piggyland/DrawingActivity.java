/*
package com.example.ghazanfarali.piggyland;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class DrawingActivity extends AppCompatActivity {

    private CanvasView canvas = null;
    Button btn_write_text,btn_draw,btn_pallete,btn_undo,btn_save,btn_load_image;
    private int currentBackgroundColor = 0xffffffff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        canvas = (CanvasView)findViewById(R.id.canvas);
        //this.canvas.setLineCap(Paint.Cap.BUTT);
        btn_write_text = (Button)findViewById(R.id.btn_write_text);
        btn_write_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                canvas.setMode(CanvasView.Mode.TEXT);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                final View views = inflater.inflate(R.layout.dialog_change_text,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(DrawingActivity.this);
                builder.setTitle("Change text");
                builder.setView(views);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = (EditText) views.findViewById(R.id.editText);

                        canvas.setText(editText.getText().toString());
                    }
                });
                builder.create();
                builder.show();
                //canvas.setText("Canvas View");
            }
        });

        btn_write_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                final View views = inflater.inflate(R.layout.dialog_change_text,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(DrawingActivity.this);
                builder.setTitle("Change text");
                builder.setView(views);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = (EditText) views.findViewById(R.id.editText);
                        //canvas.setDrawer(CanvasView.Drawer.QUADRATIC_BEZIER);
                        canvas.setText(editText.getText().toString());
                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });

        btn_draw = (Button)findViewById(R.id.btn_draw);
        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvas.setMode(CanvasView.Mode.DRAW);
            }
        });

        btn_pallete = (Button)findViewById(R.id.btn_palette);
        btn_pallete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(DrawingActivity.this)
                        .setTitle("Choose color")
                        .initialColor(currentBackgroundColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                               // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                //changeBackgroundColor(selectedColor);

                                canvas.setPaintStrokeColor(selectedColor);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });

        btn_undo = (Button)findViewById(R.id.btn_undo);
        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.undo();
            }
        });
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image = Long.toString(System.currentTimeMillis());
                byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand");
                File f = null;
                if(!file.exists()) {
                    file.mkdirs();
                    f = new File(file + "/", image + ".jpg");
                }else{
                    f = new File(file + "/", image + ".jpg");
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        btn_load_image = (Button) findViewById(R.id.btn_load_image);
        btn_load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DrawingActivity.this);
                builder.setTitle("Change text");
                //builder.setView(views);
                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                        startActivityForResult(takePicture, 0);
                    }
                });
                builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
                    }
                });
                builder.create();
                builder.show();
            }
        });

    }
    String imgPath;
    String selectedImagePath;
    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawing, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.set) {

            CanvasView.settingfortextmode = false;
            canvas.setMode(CanvasView.Mode.DRAW);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getImagePath() {
        return imgPath;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                   // Uri selectedImage = imageReturnedIntent.getData();
                    //try {
                        selectedImagePath = getImagePath();
                        //imgUser.setImageBitmap(decodeFile(selectedImagePath));
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath,bmOptions);
                       // Bitmap bitmap = null;//MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                        this.canvas.drawBitmap(bitmap);
                    */
/*} catch (IOException e) {
                        e.printStackTrace();
                    }*//*

                    //imageview.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    //imageview.setImageURI(selectedImage);
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                        this.canvas.drawBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }
}
*/
