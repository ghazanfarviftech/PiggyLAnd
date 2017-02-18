package com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.SaveToGalleryResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.Defines;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.MygallaryAdapter_Grid;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.adapter.MyGallaryITemDecor;
import com.example.ghazanfarali.piggyland.Views.Activities.MyGallery.beans.mygallarylist;
import com.example.ghazanfarali.piggyland.Views.Fragments.BaseMasterFragment.MasterFragment;
import com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment.ShareDrawing.ShareYourDrawing;
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

/**
 * Created by Amir.jehangir on 2/17/2017.
 */
public class MainDrawingFragment extends MasterFragment {
    private View view;
    private CanvasView canvas = null;
    Button btn_write_text, btn_draw, btn_pallete, btn_undo, btn_save, btn_load_image;
    private int currentBackgroundColor = 0xffffffff;
    ImageView back, undo, draw, colorPicker, rubber, save, camera_pick, shapes, text_write;
    boolean bottom_sheet = false;
    FrameLayout canvas_frame, main_frame;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_drawing, container, false);
            initUI();
            HideMainHeader();
            userProfileActivity.fragmentType = "100";

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // finish();
                    userProfileActivity.onBackPressed();
                }
            });


            draw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    canvas.setMode(CanvasView.Mode.DRAW);
                }
            });

            colorPicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorPickerDialogBuilder
                            .with(getActivity())
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

            undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    canvas.undo();
                }
            });


            rubber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    canvas.setMode(CanvasView.Mode.ERASER);


                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAttachmentSave();


                }
            });


            camera_pick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAttachmentDialog();
                }
            });


            shapes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!bottom_sheet) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        TextView view_set_basicshapes = (TextView) view.findViewById(R.id.view_set_basicshapes);
                        view_set_basicshapes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LinearLayout View_Accessories = (LinearLayout) view.findViewById(R.id.View_Accessories);
                                View_Accessories.setVisibility(View.INVISIBLE);
                                LinearLayout View_BasicShapes = (LinearLayout) view.findViewById(R.id.View_BasicShapes);
                                View_BasicShapes.setVisibility(View.VISIBLE);

                                ImageView draw_circle = (ImageView) view.findViewById(R.id.draw_circle);
                                draw_circle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        canvas.setMode(CanvasView.Mode.DRAW);
                                        canvas.setDrawer(CanvasView.Drawer.CIRCLE);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });

                                ImageView draw_square = (ImageView) view.findViewById(R.id.draw_square);
                                draw_square.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        canvas.setMode(CanvasView.Mode.DRAW);
                                        canvas.setDrawer(CanvasView.Drawer.RECTANGLE);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });


                                ImageView draw_triangle = (ImageView) view.findViewById(R.id.draw_triangle);
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
                        TextView view_set_accessorries = (TextView) view.findViewById(R.id.view_set_accessorries);
                        view_set_accessorries.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LinearLayout View_Accessories = (LinearLayout) view.findViewById(R.id.View_Accessories);
                                View_Accessories.setVisibility(View.VISIBLE);
                                LinearLayout View_BasicShapes = (LinearLayout) view.findViewById(R.id.View_BasicShapes);
                                View_BasicShapes.setVisibility(View.INVISIBLE);
                            }
                        });
                        RecyclerView rv_list_gallary;
                        GridLayoutManager lLayout;
                        ArrayList<mygallarylist> smartToolsList;
                        MygallaryAdapter_Grid mygallaryAdapter;
                        smartToolsList = new ArrayList<>();
                        rv_list_gallary = (RecyclerView) view.findViewById(R.id.rv_listview_mygallary);
                        rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(getActivity()));

                        smartToolsList = prepareListData();
                        mygallaryAdapter = new MygallaryAdapter_Grid(getActivity(), smartToolsList);
                        rv_list_gallary.setAdapter(mygallaryAdapter);

                        mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_LONG).show();
                            }
                        });
                        lLayout = new GridLayoutManager(getActivity(), 4);
                        rv_list_gallary.setHasFixedSize(true);
                        rv_list_gallary.setLayoutManager(lLayout);

                    } else {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        bottom_sheet = false;
                    }

                }
            });


        } else {
            if (view != null)
                userProfileActivity.setHeaderTitle("Main");
        }
        return view;

    }

    @Override
    public void initUI() {
        super.initUI();
        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottomSheetLayout));

        canvas = (CanvasView) view.findViewById(R.id.canvas);

        canvas_frame = (FrameLayout) view.findViewById(R.id.frame_main);

        main_frame = (FrameLayout) view.findViewById(R.id.main_frame);
        back = (ImageView) view.findViewById(R.id.back);
        draw = (ImageView) view.findViewById(R.id.draw);
        colorPicker = (ImageView) view.findViewById(R.id.colorPicker);
        rubber = (ImageView) view.findViewById(R.id.rubber);
        save = (ImageView) view.findViewById(R.id.save);
        camera_pick = (ImageView) view.findViewById(R.id.camera_pick);
        shapes = (ImageView) view.findViewById(R.id.shapes);

        undo = (ImageView) view.findViewById(R.id.undo);

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


    String imageMain, imageName;
    String encodedImage, encodedImage2;

    public void showAttachmentSave() {
        final Dialog addImageDialog;
        // popup..
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_chooser_save, null);
        addImageDialog = new Dialog(getActivity());

        TextView txt = (TextView) addImageDialog.findViewById(android.R.id.title);

//        txt.setVisibility(View.GONE);

        /*addImageDialog.setTitle("Image Picker");*/
        addImageDialog.setContentView(getActivity().getLayoutInflater().inflate(
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
                byte[] bytess = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
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
                File file = new File(android.os.Environment.getExternalStorageDirectory() + "/", "PiggyLandShare");
                File f = null;
                if (!file.exists()) {
                    file.mkdirs();
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                } else {
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    fos.write(bytesain);
                    fos.flush();
                    fos.close();
                    Toast.makeText(getActivity(), "File Saved", Toast.LENGTH_SHORT).show();
                    main_frame.destroyDrawingCache();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Intent i = new Intent(getActivity(), SaveDrawingActivity.class);
//                i.putExtra("fileName",f.getAbsolutePath());
//                i.putExtra("fileDir","shareToPublic");
//                startActivityForResult(i, 3);

                Bundle bundle = new Bundle();
                bundle.putString("fileName", f.getAbsolutePath());
                bundle.putString("fileDir", "shareToPublic");
                bundle.putString("imagename", imageName);
                ShareYourDrawing fragobj = new ShareYourDrawing();
                fragobj.setArguments(bundle);
                setTargetFragment(fragobj, 3);
                userProfileActivity.replaceFragmnet(fragobj, R.id.frameLayout, true);

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
                byte[] bytess = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
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

                byte[] combined = ArrayUtils.addAll(bytess, bytes);

                File file = new File(android.os.Environment.getExternalStorageDirectory() + "/", "PiggyLand");
                File f = null;
                if (!file.exists()) {
                    file.mkdirs();
                    f = new File(file + "/", imageMain + ".jpg");
                    imageName = f.getName();
                } else {
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

                    Toast.makeText(getActivity(), "File Saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Intent i = new Intent(getActivity(), SaveDrawingActivity.class);
//                i.putExtra("fileName",f.getAbsolutePath());
//                i.putExtra("fileDir","saveToGallery");
//                startActivityForResult(i, 4);

                Bundle bundle = new Bundle();
                bundle.putString("fileName", f.getAbsolutePath());
                bundle.putString("fileDir", "saveToGallery");
                ShareYourDrawing fragobj = new ShareYourDrawing();
                fragobj.setArguments(bundle);
//setTargetFragment(fragobj,4);
                userProfileActivity.replaceFragmnet(fragobj, R.id.frameLayout, true);
                //  finish();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        if (Defines.is_shareToPublic) {
            //   Defines.is_shareToPublic = false;

            ShareActvityResult();

        } else if (Defines.is_saveToGallery) {
            SaveToGalleryActivityResult();
        }


    }


    private void ResetShareDefines() {

        Defines.is_shareToPublic = false;
        Defines.is_saveToGallery = false;
        Defines.Share_title = "";
        Defines.Share_description = "";

    }


    private void ShareActvityResult() {
        // Toast.makeText(getActivity(),"got result",Toast.LENGTH_SHORT).show();


        if (!Defines.is_shareToPublic) {

        } else {
            String imageTitle = Defines.Share_title;
            String Description = Defines.Share_description;
            String FullPath = imageName;//bundle.getString("FullPath");


            try {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<SaveToGalleryResponse> call = apiService.saveImageToServer(encodedImage, FullPath, Description, imageTitle, "1");

                call.enqueue(new Callback<SaveToGalleryResponse>() {
                    @Override
                    public void onResponse(Call<SaveToGalleryResponse> call, Response<SaveToGalleryResponse> response) {
                        SaveToGalleryResponse statusCode = response.body();//code();
                        if (statusCode.getStatus().contentEquals("Success")) {

                            Toast.makeText(getActivity(), "image send", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(LoginActivity.this, StartActivity.class));
                        } else {
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


            //  userProfileActivity.onBackPressed();
        }

        ResetShareDefines();
    }

    private void SaveToGalleryActivityResult() {


        if (!Defines.is_saveToGallery) {

        } else {
            String imageTitle = Defines.Share_title;
            String Description = Defines.Share_description;
            String FullPath = imageName;//bundle.getString("FullPath");


            try {
                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<SaveToGalleryResponse> call = apiService.saveImageToServer(encodedImage2, FullPath, Description, imageTitle, "1");

                call.enqueue(new Callback<SaveToGalleryResponse>() {
                    @Override
                    public void onResponse(Call<SaveToGalleryResponse> call, Response<SaveToGalleryResponse> response) {
                        SaveToGalleryResponse statusCode = response.body();//code();
                        if (statusCode.getStatus().contentEquals("Success")) {
                            Toast.makeText(getActivity(), "File send tp server", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(LoginActivity.this, StartActivity.class));
                        } else {
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

        // userProfileActivity.onBackPressed();

        ResetShareDefines();


    }


    public void showAttachmentDialog() {
        final Dialog addImageDialog;
        // popup..
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_chooser, null);
        addImageDialog = new Dialog(getActivity());

        addImageDialog.setTitle("Image Picker");
        addImageDialog.setContentView(getActivity().getLayoutInflater().inflate(
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
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
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
                            .with( getActivity() ) // safer!
                            .load( selectedImagePath )
                            .asBitmap()
                            .into( target2 );


                }
                //imageview.setImageURI(selectedImage);


                break;
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    //imageview.setImageURI(selectedImage);
                    SimpleTarget target2 = new SimpleTarget<Bitmap>( canvas.getWidth(), canvas.getHeight() ) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                            //imageView2.setImageBitmap( bitmap );
                            canvas.drawBitmap(bitmap);
                        }
                    };
                    Glide
                            .with( getActivity() ) // safer!
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


        }
    }
}
