package com.example.ghazanfarali.piggyland.Views.Activities.Drawing;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ghazanfarali.piggyland.CanvasView;
import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.CustomViews.DrawingControls.StickerTextView;
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.SaveToGalleryResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Views.SaveDrawingActivity;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.MygallaryAdapter_Grid;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Drawing2Activity extends AppCompatActivity {
    private CanvasView canvas = null;
    Button btn_write_text,btn_draw,btn_pallete,btn_undo,btn_save,btn_load_image;
    private int currentBackgroundColor = 0xffffffff;
    ImageView back,undo,draw,colorPicker,rubber,save,camera_pick,shapes,text_write;
    boolean bottom_sheet = false;
    FrameLayout canvas_frame,main_frame;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));

        canvas = (CanvasView)findViewById(R.id.canvas);

        canvas_frame = (FrameLayout)findViewById(R.id.frame_main);

        main_frame = (FrameLayout)findViewById(R.id.main_frame);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        undo = (ImageView)findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.undo();
            }
        });

        draw = (ImageView)findViewById(R.id.draw);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.DRAW);
            }
        });
        colorPicker = (ImageView)findViewById(R.id.colorPicker);
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Drawing2Activity.this)
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
        rubber = (ImageView)findViewById(R.id.rubber);
        rubber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setMode(CanvasView.Mode.ERASER);
                /*AlertDialog.Builder builder = new AlertDialog.Builder(Drawing2Activity.this);
                builder.setTitle("Image Picker");
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
                builder.show();*/
                //showAttachmentDialog();

            }
        });
        save = (ImageView)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAttachmentSave();
                /* String image = Long.toString(System.currentTimeMillis());
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
                    Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });

        camera_pick = (ImageView)findViewById(R.id.camera_pick);
        camera_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAttachmentDialog();
            }
        });

        shapes = (ImageView)findViewById(R.id.shapes);
        shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!bottom_sheet) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    TextView view_set_basicshapes = (TextView)findViewById(R.id.view_set_basicshapes);
                    view_set_basicshapes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinearLayout View_Accessories = (LinearLayout)findViewById(R.id.View_Accessories);
                            View_Accessories.setVisibility(View.INVISIBLE);
                            LinearLayout View_BasicShapes = (LinearLayout)findViewById(R.id.View_BasicShapes);
                            View_BasicShapes.setVisibility(View.VISIBLE);

                            ImageView draw_circle = (ImageView)findViewById(R.id.draw_circle);
                            draw_circle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    canvas.setMode(CanvasView.Mode.DRAW);
                                    canvas.setDrawer(CanvasView.Drawer.CIRCLE);
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                    bottom_sheet = false;
                                }
                            });

                            ImageView draw_square = (ImageView)findViewById(R.id.draw_square);
                            draw_square.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    canvas.setMode(CanvasView.Mode.DRAW);
                                    canvas.setDrawer(CanvasView.Drawer.RECTANGLE);
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                    bottom_sheet = false;
                                }
                            });


                            ImageView draw_triangle = (ImageView)findViewById(R.id.draw_triangle);
                            draw_triangle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    canvas.setMode(CanvasView.Mode.DRAW);
                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                    bottom_sheet = false;
                                }
                            });

                        }
                    });
                    TextView view_set_accessorries = (TextView)findViewById(R.id.view_set_accessorries);
                    view_set_accessorries.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinearLayout View_Accessories = (LinearLayout)findViewById(R.id.View_Accessories);
                            View_Accessories.setVisibility(View.VISIBLE);
                            LinearLayout View_BasicShapes = (LinearLayout)findViewById(R.id.View_BasicShapes);
                            View_BasicShapes.setVisibility(View.INVISIBLE);
                        }
                    });
                    RecyclerView rv_list_gallary;
                    GridLayoutManager lLayout;
                    ArrayList<mygallarylist> smartToolsList;
                    MygallaryAdapter_Grid mygallaryAdapter;
                    smartToolsList = new ArrayList<>();
                    rv_list_gallary = (RecyclerView)findViewById(R.id.rv_listview_mygallary);
                    rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(Drawing2Activity.this));

                    smartToolsList = prepareListData();
                    mygallaryAdapter = new MygallaryAdapter_Grid(Drawing2Activity.this,smartToolsList);
                    rv_list_gallary.setAdapter(mygallaryAdapter);

                    mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(Drawing2Activity.this,""+position,Toast.LENGTH_LONG).show();
                        }
                    });
                    lLayout = new GridLayoutManager(Drawing2Activity.this, 4);
                    rv_list_gallary.setHasFixedSize(true);
                    rv_list_gallary.setLayoutManager(lLayout);
                    /*TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
                    TabWidget tw = (TabWidget)tabHost.findViewById(android.R.id.tabs);
                    for (int i = 0; i < tw.getChildCount(); i++)
                    {
                        final View tabView = tw.getChildTabViewAt(i);
                        final TextView tv = (TextView)tabView.findViewById(android.R.id.title);
                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setTextColor(getResources().getColor(R.color.white));
                    }
                    tabHost.setup();

                    TabHost.TabSpec spec1 = tabHost.newTabSpec("Basic Shapes");
                    spec1.setContent(R.id.tab1_Units);
                    spec1.setIndicator(" " + "Basic Shapes"
                            + " ");


                    TabHost.TabSpec spec2 = tabHost.newTabSpec("Accessories");
                    spec2.setContent(R.id.tab2_Map);
                    spec2.setIndicator("  " + "Accessories"
                            + " ");


                    tabHost.addTab(spec1);
                    tabHost.addTab(spec2);

                    RecyclerView rv_list_gallary;
                    GridLayoutManager lLayout;
                    ArrayList<mygallarylist> smartToolsList;
                    MygallaryAdapter_Grid mygallaryAdapter;
                    smartToolsList = new ArrayList<>();
                    rv_list_gallary = (RecyclerView)findViewById(R.id.rv_listview_mygallary);
                    rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(Drawing2Activity.this));

                    smartToolsList = prepareListData();
                    mygallaryAdapter = new MygallaryAdapter_Grid(Drawing2Activity.this,smartToolsList);
                    rv_list_gallary.setAdapter(mygallaryAdapter);

                    mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(Drawing2Activity.this,""+position,Toast.LENGTH_LONG).show();
                        }
                    });
                    lLayout = new GridLayoutManager(Drawing2Activity.this, 4);
                    rv_list_gallary.setHasFixedSize(true);
                    rv_list_gallary.setLayoutManager(lLayout);

                    ImageView draw_circle = (ImageView) findViewById(R.id.draw_circle);
                    draw_circle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CircleShapeView circleShapeView = new CircleShapeView(Drawing2Activity.this);

                            canvas_frame.addView(circleShapeView);
                        }
                    });*/
                    // bottom_sheet = true;
                }else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    bottom_sheet = false;
                }

            }
        });

        text_write = (ImageView)findViewById(R.id.text_write);
        text_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                final View views = inflater.inflate(R.layout.dialog_change_text,null);
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Drawing2Activity.this);
                builder.setTitle("Write Text");
                builder.setView(views);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = (EditText) views.findViewById(R.id.editText);
                        //canvas.setDrawer(CanvasView.Drawer.QUADRATIC_BEZIER);
                        //canvas.setText(editText.getText().toString());
                        StickerTextView tv_sticker = new StickerTextView(Drawing2Activity.this);
                        tv_sticker.setText(editText.getText().toString());

                        canvas_frame.addView(tv_sticker);
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
    public void showAttachmentDialog() {
        final Dialog addImageDialog;
        // popup..
        View view = this.getLayoutInflater().inflate(R.layout.dialog_chooser, null);
        addImageDialog = new Dialog(Drawing2Activity.this);

        addImageDialog.setTitle("Image Picker");
        addImageDialog.setContentView(this.getLayoutInflater().inflate(
                R.layout.dialog_chooser, null));
        addImageDialog.show();

        ImageButton ibCamera = (ImageButton) addImageDialog
                .findViewById(R.id.add_suggestion_dialog_ib_camera);
        ImageButton ibGallery = (ImageButton) addImageDialog
                .findViewById(R.id.add_suggestion_dialog_ib_gallery);

        ibCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addImageDialog.dismiss();
                //startCameraOrGallery("camera");
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                startActivityForResult(takePicture, 0);
            }
        });
        ibGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addImageDialog.dismiss();
                // startCameraOrGallery("gallery");
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code

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


    public String getImagePath() {
        return imgPath;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    // Uri selectedImage = imageReturnedIntent.getData();
                    //try {
                    selectedImagePath = getImagePath();
                    //imgUser.setImageBitmap(decodeFile(selectedImagePath));
                   /* BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, bmOptions);
                    // Bitmap bitmap = null;//MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                    this.canvas.drawBitmap(bitmap);*/
                    SimpleTarget target2 = new SimpleTarget<Bitmap>( canvas.getWidth(), canvas.getHeight() ) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            //imageView2.setImageBitmap( bitmap );
                            canvas.drawBitmap(bitmap);
                        }
                    };
                    Glide
                            .with( Drawing2Activity.this ) // safer!
                            .load( selectedImagePath )
                            .asBitmap()
                            .into( target2 );


                }
                //imageview.setImageURI(selectedImage);


                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    //imageview.setImageURI(selectedImage);
                    SimpleTarget target2 = new SimpleTarget<Bitmap>( canvas.getWidth(), canvas.getHeight() ) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            //imageView2.setImageBitmap( bitmap );
                            canvas.drawBitmap(bitmap);
                        }
                    };
                    Glide
                            .with( Drawing2Activity.this ) // safer!
                            .load( selectedImage )
                            .asBitmap()
                            .into( target2 );
                    /* Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        this.canvas.drawBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                }
                break;
            case 3:
                if(imageReturnedIntent != null)
                {
                    Bundle bundle = imageReturnedIntent.getExtras();
                    if(bundle.isEmpty())
                    {

                    }else {
                        String imageTitle = bundle.getString("Title");
                        String Description = bundle.getString("Description");
                        String FullPath = imageName;//bundle.getString("FullPath");


                        try {
                            ApiInterface apiService =
                                    ApiClient.getClient().create(ApiInterface.class);
                           /* *//*Login task = new Login("fazila", "fazila", "123444");*//*
                            Gson gson = new Gson();
                            gson.toJson(task);*/



                            Call<SaveToGalleryResponse> call = apiService.saveImageToServer(encodedImage,FullPath,Description,imageTitle,"1");

                            call.enqueue(new Callback<SaveToGalleryResponse>() {
                                @Override
                                public void onResponse(Call<SaveToGalleryResponse> call, Response<SaveToGalleryResponse> response) {
                                    SaveToGalleryResponse statusCode = response.body();//code();
                                    if(statusCode.getStatus().contentEquals("Success"))
                                    {
                                        Toast.makeText(Drawing2Activity.this,"image send",Toast.LENGTH_LONG).show();
                                        //startActivity(new Intent(LoginActivity.this, StartActivity.class));
                                    }else{
                                       // Toast.makeText(Drawing2Activity.this,"UserName/Password Incorrect",Toast.LENGTH_SHORT).show();
                                    }

                                    //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                                }

                                @Override
                                public void onFailure(Call<SaveToGalleryResponse> call, Throwable t) {
                                    // Log error here since request failed
                                    Log.e("", t.toString());
                                }
                            });
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
                    }
                    /*String[] MainName =FullPathimageName.split("\\.");
                    String image = MainName[0]+"_"+imageName;
                    //byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                    File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
                    File f = null;
                    if(!file.exists()) {
                        file.mkdirs();
                        f = new File(MainName[0], image + ".jpg");
                    }else{
                        f = new File(MainName[0], image + ".jpg");
                    }*/
                    /*FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    finish();
                }
                break;
            case 4:

                if(imageReturnedIntent != null)
                {
                    Bundle bundle = imageReturnedIntent.getExtras();
                    if(bundle.isEmpty())
                    {

                    }else {
                        String imageTitle = bundle.getString("Title");
                        String Description = bundle.getString("Description");
                        String FullPath = imageName;//bundle.getString("FullPath");


                        try {
                            ApiInterface apiService =
                                    ApiClient.getClient().create(ApiInterface.class);
                           /* *//*Login task = new Login("fazila", "fazila", "123444");*//*
                            Gson gson = new Gson();
                            gson.toJson(task);*/



                            Call<SaveToGalleryResponse> call = apiService.saveImageToServer(encodedImage2,FullPath,Description,imageTitle,"1");

                            call.enqueue(new Callback<SaveToGalleryResponse>() {
                                @Override
                                public void onResponse(Call<SaveToGalleryResponse> call, Response<SaveToGalleryResponse> response) {
                                    SaveToGalleryResponse statusCode = response.body();//code();
                                    if(statusCode.getStatus().contentEquals("Success"))
                                    {
                                        //startActivity(new Intent(LoginActivity.this, StartActivity.class));
                                    }else{
                                       // Toast.makeText(Drawing2Activity.this,"UserName/Password Incorrect",Toast.LENGTH_SHORT).show();
                                    }

                                    //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                                }

                                @Override
                                public void onFailure(Call<SaveToGalleryResponse> call, Throwable t) {
                                    // Log error here since request failed
                                    Log.e("", t.toString());
                                }
                            });
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
                    }
                    /*String[] MainName =FullPathimageName.split("\\.");
                    String image = MainName[0]+"_"+imageName;
                    //byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                    File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
                    File f = null;
                    if(!file.exists()) {
                        file.mkdirs();
                        f = new File(MainName[0], image + ".jpg");
                    }else{
                        f = new File(MainName[0], image + ".jpg");
                    }*/
                    /*FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    finish();
                }

               /* if(imageReturnedIntent.hasExtra("Title"))
                {

                    Bundle bundle = imageReturnedIntent.getExtras();
                    if(bundle.isEmpty())
                    {

                    }else {
                        String imageName = bundle.getString("Title");
                        String Description = bundle.getString("Description");
                        String FullPath = bundle.getString("FullPath");
                    }
                    *//*String[] MainName =FullPathimageName.split("\\.");
                    String image = MainName[0]+"_"+imageName;
                    //byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                    File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand");
                    File f = null;
                    if(!file.exists()) {
                        file.mkdirs();
                        f = new File(MainName[0], image + ".jpg");
                    }else{
                        f = new File(MainName[0], image + ".jpg");
                    }*//*
                    *//*FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(bytes);
                        fos.flush();
                        fos.close();
                        Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//*
                    finish();
                }*/
                break;
        }
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


    String imageMain,imageName;
    String encodedImage,encodedImage2;
    public void showAttachmentSave() {
        final Dialog addImageDialog;
        // popup..
        View view = this.getLayoutInflater().inflate(R.layout.dialog_chooser_save, null);
        addImageDialog = new Dialog(Drawing2Activity.this);

        TextView txt = (TextView) addImageDialog.findViewById(android.R.id.title);

//        txt.setVisibility(View.GONE);

        /*addImageDialog.setTitle("Image Picker");*/
        addImageDialog.setContentView(this.getLayoutInflater().inflate(
                R.layout.dialog_chooser_save, null));
        addImageDialog.show();

        Button shareToPublic = (Button) addImageDialog
                .findViewById(R.id.shareToPublic);
        Button saveToGallery = (Button) addImageDialog
                .findViewById(R.id.saveToGallery);

        shareToPublic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addImageDialog.dismiss();
                imageMain = Long.toString(System.currentTimeMillis());

                canvas_frame.setDrawingCacheEnabled(true);
                canvas_frame.buildDrawingCache();
                Bitmap bm = canvas_frame.getDrawingCache();
                byte[]  bytess= canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                main_frame.setDrawingCacheEnabled(true);
                main_frame.buildDrawingCache();
                Bitmap bmMain = main_frame.getDrawingCache();
                ByteArrayOutputStream streambmMain = new ByteArrayOutputStream();
                bmMain.compress(Bitmap.CompressFormat.PNG, 100, streambmMain);

                byte[] bytesain = streambmMain.toByteArray();
                encodedImage = Base64.encodeToString(bytesain, Base64.DEFAULT);
                // byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
                File f = null;
                if(!file.exists()) {
                    file.mkdirs();
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                }else{
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    fos.write(bytesain);
                    fos.flush();
                    fos.close();
                    Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                    main_frame.destroyDrawingCache();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Drawing2Activity.this, SaveDrawingActivity.class);
                i.putExtra("fileName",f.getAbsolutePath());
                i.putExtra("fileDir","shareToPublic");
                startActivityForResult(i, 3);
                /*String image = Long.toString(System.currentTimeMillis());
                byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
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
                    Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();*/

            }
        });
        saveToGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addImageDialog.dismiss();
                // Intent i = new Intent(Drawing2Activity.this, SaveDrawingActivity.class);
                // startActivityForResult(i, 4);
                imageMain = Long.toString(System.currentTimeMillis());
                canvas_frame.setDrawingCacheEnabled(true);
                canvas_frame.buildDrawingCache();
                Bitmap bm = canvas_frame.getDrawingCache();
                byte[]  bytess= canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                main_frame.setDrawingCacheEnabled(true);
                main_frame.buildDrawingCache();
                Bitmap bmMain = main_frame.getDrawingCache();
                ByteArrayOutputStream streambmMain = new ByteArrayOutputStream();
                bmMain.compress(Bitmap.CompressFormat.PNG, 100, streambmMain);

                byte[] bytesain = streambmMain.toByteArray();

                encodedImage2 = Base64.encodeToString(bytesain, Base64.DEFAULT);
                //byte[] c = new byte[bytes.length + bytess.length];
                // System.arraycopy(bytes,0,c,0,bytes.length);
                //System.arraycopy(bytess,0,c,bytes.length,bytess.length);
                // List<Byte> list = new ArrayList<Byte>(Arrays.<Byte>asList(bytess));
                //list.addAll(Arrays.<Byte>asList(bytes));

                byte[] combined = ArrayUtils.addAll(bytess,bytes);

                /*byte[] one = bytess;
                byte[] two = bytes;
                byte[] combined = new byte[one.length + two.length];

                for (int i = 0; i < combined.length; ++i)
                {
                    combined[i] = i < one.length ? one[i] : two[i - one.length];
                }*/

                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand");
                File f = null;
                if(!file.exists()) {
                    file.mkdirs();
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                }else{
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                    main_frame.destroyDrawingCache();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    fos.write(bytesain);
                    // fos.write(bytes);
                    fos.flush();
                    fos.close();

                    Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Drawing2Activity.this, SaveDrawingActivity.class);
                i.putExtra("fileName",f.getAbsolutePath());
                i.putExtra("fileDir","saveToGallery");
                startActivityForResult(i, 4);
                //  finish();
            }
        });

    }
}
