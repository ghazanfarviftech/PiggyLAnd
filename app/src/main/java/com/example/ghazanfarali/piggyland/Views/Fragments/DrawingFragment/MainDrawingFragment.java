package com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.example.ghazanfarali.piggyland.CanvasView;
import com.example.ghazanfarali.piggyland.Controls.OnItemClickListener;
import com.example.ghazanfarali.piggyland.CustomViews.DrawingControls.StickerImageView;
import com.example.ghazanfarali.piggyland.CustomViews.DrawingControls.StickerTextView;
import com.example.ghazanfarali.piggyland.EndPoint.ApiClient;
import com.example.ghazanfarali.piggyland.EndPoint.ApiInterface;
import com.example.ghazanfarali.piggyland.EndPoint.DataResponse.SaveToGalleryResponse;
import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Utils.Defines;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean.StickerImg;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Drawing2Activity;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.MygallaryAdapter_Grid;
import com.example.ghazanfarali.piggyland.Views.Activities.Drawing.adapters.StickerAdapter;
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
    FrameLayout canvas_frame, main_frame;

    private BottomSheetBehavior bottomSheetBehavior;

    boolean bottom_sheet = false,like_bottomsheet=false;
    LinearLayout like_bottomsheet_layout;
    int color=-16777216;
    float brush_stroke_width,brush_sec_stroke_width,brush_third_stroke_width,brush_fourth_stroke_width;
    RecyclerView rv_list_gallary;
    GridLayoutManager lLayout;
    StickerAdapter mygallaryAdapter;
    ArrayList<StickerImg> smartToolsList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_drawing, container, false);
            initUI();
            HideMainHeader();
            userProfileActivity.fragmentType = "100";



            View view1 = (View)view.findViewById(R.id.view1);
            View view2 = (View)view.findViewById(R.id.view2);
            View view3 = (View)view.findViewById(R.id.view3);
            View view4 = (View)view.findViewById(R.id.view4);
            view1.setBackgroundColor(color);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    final View views = inflater.inflate(R.layout.dialog_stroke_width_change,null);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setTitle("Set Stroke Width");
                    builder.setView(views);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                       /* CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                        seekbar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
                            @Override
                            public void finalValue(Number value) {
                                brush_stroke_width = value.floatValue();//intValue();
                            }
                        });*/
                            dialogInterface.dismiss();
                        }
                    });

                    CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                    seekbar.setMinStartValue(brush_stroke_width);
                    seekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                        @Override
                        public void valueChanged(Number value) {
                            brush_stroke_width = value.floatValue();
                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
            view2.setBackgroundColor(color);
            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    final View views = inflater.inflate(R.layout.dialog_stroke_width_change,null);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setTitle("Set Stroke Width");
                    builder.setView(views);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            dialogInterface.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();

                    CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                    seekbar.setMinStartValue(brush_sec_stroke_width);
                    seekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                        @Override
                        public void valueChanged(Number value) {
                            brush_sec_stroke_width = value.floatValue();
                        }
                    });
                }
            });
            view3.setBackgroundColor(color);
            view3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    final View views = inflater.inflate(R.layout.dialog_stroke_width_change,null);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setTitle("Set Stroke Width");
                    builder.setView(views);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                     /*   CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                        seekbar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
                            @Override
                            public void finalValue(Number value) {
                                brush_third_stroke_width = value.floatValue();//intValue();
                            }
                        });*/

                            dialogInterface.dismiss();
                        }
                    });

                    CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                    seekbar.setMinStartValue(brush_third_stroke_width);
                    seekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                        @Override
                        public void valueChanged(Number value) {
                            brush_third_stroke_width = value.floatValue();
                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
            view4.setBackgroundColor(color);
            view4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    final View views = inflater.inflate(R.layout.dialog_stroke_width_change,null);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setTitle("Set Stroke Width");
                    builder.setView(views);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                       /* CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                        seekbar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
                            @Override
                            public void finalValue(Number value) {
                                brush_fourth_stroke_width = value.floatValue();//intValue();
                            }
                        });*/
                            dialogInterface.dismiss();
                        }
                    });

                    CrystalSeekbar seekbar = (CrystalSeekbar)views.findViewById(R.id.rangeSeekbar1);
                    seekbar.setMinStartValue(brush_fourth_stroke_width);
                    seekbar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                        @Override
                        public void valueChanged(Number value) {
                            brush_fourth_stroke_width = value.floatValue();
                        }
                    });
                    builder.create();
                    builder.show();
                }
            });

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
                    if(!like_bottomsheet)
                    {

                        like_bottomsheet_layout.setVisibility(View.VISIBLE);
                        ImageView brush = (ImageView)view.findViewById(R.id.brush);
                        brush.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                canvas.setOpacity(100);
                                canvas.setBlur(10F);
                                canvas.setLineCap(Paint.Cap.SQUARE);
                                canvas.setPaintStyle(Paint.Style.STROKE);
                                canvas.setPaintStrokeWidth(brush_stroke_width);
                                canvas.setDrawer(CanvasView.Drawer.PEN);
                                canvas.setMode(CanvasView.Mode.DRAW);
                                like_bottomsheet = false;
                                like_bottomsheet_layout.setVisibility(View.GONE);
                            }
                        });

                        ImageView pen = (ImageView)view.findViewById(R.id.pen);
                        pen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                canvas.setOpacity(255);
                                canvas.setBlur(0F);
                                canvas.setLineCap(Paint.Cap.ROUND);
                                canvas.setPaintStyle(Paint.Style.STROKE);
                                canvas.setPaintStrokeWidth(brush_sec_stroke_width);
                                canvas.setDrawer(CanvasView.Drawer.PEN);
                                canvas.setMode(CanvasView.Mode.DRAW);
                                like_bottomsheet = false;
                                like_bottomsheet_layout.setVisibility(View.GONE);
                            }
                        });

                        ImageView crayon = (ImageView)view.findViewById(R.id.crayon);
                        crayon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                canvas.setOpacity(255);
                                canvas.setBlur(0F);

                                canvas.setLineCap(Paint.Cap.ROUND);
                                canvas.setPaintStyle(Paint.Style.STROKE);
                                canvas.setPaintStrokeWidth(brush_third_stroke_width);
                                canvas.setDrawer(CanvasView.Drawer.PEN);
                                canvas.setMode(CanvasView.Mode.DRAW);
                                like_bottomsheet = false;
                                like_bottomsheet_layout.setVisibility(View.GONE);
                            }
                        });


                        ImageView paint = (ImageView)view.findViewById(R.id.paint);
                        paint.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                canvas.setOpacity(255);
                                canvas.setBlur(0F);

                                canvas.setLineCap(Paint.Cap.BUTT);
                                canvas.setPaintStyle(Paint.Style.STROKE);
                                canvas.setPaintStrokeWidth(brush_fourth_stroke_width);
                                canvas.setDrawer(CanvasView.Drawer.PEN);
                                canvas.setMode(CanvasView.Mode.DRAW);

                                like_bottomsheet = false;
                                like_bottomsheet_layout.setVisibility(View.GONE);
                            }
                        });



                        like_bottomsheet = true;
                    }else{


                        like_bottomsheet = false;
                        like_bottomsheet_layout.setVisibility(View.GONE);
                    }
                   // canvas.setMode(CanvasView.Mode.DRAW);
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

                                    color = selectedColor;
                                    View view1 = (View)view.findViewById(R.id.view1);
                                    View view2 = (View)view.findViewById(R.id.view2);
                                    View view3 = (View)view.findViewById(R.id.view3);
                                    View view4 = (View)view.findViewById(R.id.view4);
                                    view1.setBackgroundColor(selectedColor);
                                    view2.setBackgroundColor(selectedColor);
                                    view3.setBackgroundColor(selectedColor);
                                    view4.setBackgroundColor(selectedColor);
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
                public void onClick(View views) {

                    if(!bottom_sheet) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        TextView view_set_basicshapes = (TextView)view.findViewById(R.id.view_set_basicshapes);
                        view_set_basicshapes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View views) {
                                LinearLayout View_Accessories = (LinearLayout)view.findViewById(R.id.View_Accessories);
                                View_Accessories.setVisibility(View.INVISIBLE);
                                LinearLayout View_BasicShapes = (LinearLayout)view.findViewById(R.id.View_BasicShapes);
                                View_BasicShapes.setVisibility(View.VISIBLE);


                                ImageView draw_square = (ImageView)view.findViewById(R.id.draw_square);
                                draw_square.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //  canvas.setMode(CanvasView.Mode.DRAW);
                                        //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);

                                        StickerImageView tv_sticker = new StickerImageView(getActivity());
                                        // tv_sticker.iv_main
                                        DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
                                        //setText(editText.getText().toString());

                                        canvas_frame.addView(tv_sticker);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });


                                ImageView draw_circle = (ImageView)view.findViewById(R.id.draw_circle);
                                draw_circle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View views) {
                                        //  canvas.setMode(CanvasView.Mode.DRAW);
                                        //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);

                                        StickerImageView tv_sticker = new StickerImageView(getActivity());
                                        Drawable img = getResources().getDrawable(R.drawable.ic_circle_shape_outline_f);
                                        tv_sticker.setImageDrawable(img);
                                        // tv_sticker.iv_main
                                        DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
                                        //setText(editText.getText().toString());

                                        canvas_frame.addView(tv_sticker);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });



                                ImageView draw_triangle = (ImageView)view.findViewById(R.id.draw_triangle);
                                draw_triangle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View views) {
                                        //  canvas.setMode(CanvasView.Mode.DRAW);
                                        //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);

                                        StickerImageView tv_sticker = new StickerImageView(getActivity());
                                        Drawable img = getResources().getDrawable(R.drawable.ic_bleach_f);
                                        tv_sticker.setImageDrawable(img);
                                        // tv_sticker.iv_main
                                        DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
                                        //setText(editText.getText().toString());

                                        canvas_frame.addView(tv_sticker);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });


                                ImageView poly_sec = (ImageView)view.findViewById(R.id.poly_sec);
                                poly_sec.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View views) {
                                        //  canvas.setMode(CanvasView.Mode.DRAW);
                                        //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);

                                        StickerImageView tv_sticker = new StickerImageView(getActivity());
                                        Drawable img = getResources().getDrawable(R.drawable.ic_polygon_sec);
                                        tv_sticker.setImageDrawable(img);
                                        // tv_sticker.iv_main
                                        DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
                                        //setText(editText.getText().toString());

                                        canvas_frame.addView(tv_sticker);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });


                                ImageView poly_first = (ImageView)view.findViewById(R.id.poly_first);
                                poly_first.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View views) {
                                        //  canvas.setMode(CanvasView.Mode.DRAW);
                                        //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);

                                        StickerImageView tv_sticker = new StickerImageView(getActivity());
                                        Drawable img = getResources().getDrawable(R.drawable.ic_polygon);
                                        tv_sticker.setImageDrawable(img);
                                        // tv_sticker.iv_main
                                        DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
                                        //setText(editText.getText().toString());

                                        canvas_frame.addView(tv_sticker);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });

                                ImageView poly_star = (ImageView)view.findViewById(R.id.poly_star);
                                poly_star.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View views) {
                                        //  canvas.setMode(CanvasView.Mode.DRAW);
                                        //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);

                                        StickerImageView tv_sticker = new StickerImageView(getActivity());
                                        Drawable img = getResources().getDrawable(R.drawable.ic_single_star);
                                        tv_sticker.setImageDrawable(img);
                                        // tv_sticker.iv_main
                                        DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
                                        //setText(editText.getText().toString());

                                        canvas_frame.addView(tv_sticker);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        bottom_sheet = false;
                                    }
                                });

                            }
                        });
                        TextView view_set_accessorries = (TextView)view.findViewById(R.id.view_set_accessorries);
                        view_set_accessorries.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View views) {
                                LinearLayout View_Accessories = (LinearLayout)view.findViewById(R.id.View_Accessories);
                                View_Accessories.setVisibility(View.VISIBLE);
                                LinearLayout View_BasicShapes = (LinearLayout)view.findViewById(R.id.View_BasicShapes);
                                View_BasicShapes.setVisibility(View.INVISIBLE);
                            }
                        });

                        rv_list_gallary = (RecyclerView)view.findViewById(R.id.rv_listview_mygallary);
                        rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(getActivity()));
                        smartToolsList = new ArrayList<>();
                        smartToolsList = prepareListData();
                        mygallaryAdapter = new StickerAdapter(smartToolsList,getActivity());
                        rv_list_gallary.setAdapter(mygallaryAdapter);

                        mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View views, int position) {
                            /*Toast.makeText(Drawing2Activity.this,""+position,Toast.LENGTH_LONG).show();*/
                                StickerImageView tv_sticker = new StickerImageView(getActivity());
                                Drawable drawable = settingDrawable(smartToolsList.get(position));
                                tv_sticker.setImageDrawable(drawable);
                                canvas_frame.addView(tv_sticker);
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                bottom_sheet = false;
                            }
                        });
                        lLayout = new GridLayoutManager(getActivity(), 4);
                        rv_list_gallary.setHasFixedSize(true);
                        rv_list_gallary.setLayoutManager(lLayout);

                        bottom_sheet = true;
                    }else{
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        bottom_sheet = false;
                    }


                }
            });

            text_write = (ImageView)view.findViewById(R.id.text_write);
            text_write.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    final View views = inflater.inflate(R.layout.dialog_change_text,null);
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                    builder.setTitle("Write Text");
                    builder.setView(views);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditText editText = (EditText) views.findViewById(R.id.editText);
                            //canvas.setDrawer(CanvasView.Drawer.QUADRATIC_BEZIER);
                            //canvas.setText(editText.getText().toString());
                            StickerTextView tv_sticker = new StickerTextView(getActivity());
                            tv_sticker.setText(editText.getText().toString());

                            canvas_frame.addView(tv_sticker);
                        }
                    });
                    builder.create();
                    builder.show();
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

        like_bottomsheet_layout = (LinearLayout)view.findViewById(R.id.like_bottomsheet);
        brush_stroke_width = 20F;
        brush_sec_stroke_width = 3F;
        brush_third_stroke_width = 20F;
        brush_fourth_stroke_width = 30F;
    }


    private ArrayList<StickerImg>  prepareListData() {

        ArrayList<StickerImg> temp = new ArrayList<>();

        StickerImg mModel = new StickerImg();
        mModel.setImg(0);
        /*mModel.setmygallaryTitle("Favorite road trip");
        mModel.setmygallaryImageURL(R.drawable.piggylandbg);*/
        temp.add(mModel);

        mModel = new StickerImg();
        mModel.setImg(1);
        temp.add(mModel);

        mModel = new StickerImg();
        mModel.setImg(2);
        temp.add(mModel);

        mModel = new StickerImg();
        mModel.setImg(3);
        temp.add(mModel);

        mModel = new StickerImg();
        mModel.setImg(4);
        temp.add(mModel);

        mModel = new StickerImg();
        mModel.setImg(5);
        temp.add(mModel);

        mModel = new StickerImg();
        mModel.setImg(6);
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

        //TextView txt = (TextView) addImageDialog.findViewById(android.R.id.title);

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
                            userProfileActivity.onBackPressed();
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
                            Toast.makeText(getActivity(), "image send", Toast.LENGTH_SHORT).show();
                            userProfileActivity.onBackPressed();
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


    public Drawable settingDrawable(StickerImg img)
    {
        if(img.getImg()==0)
        {
            return this.getResources().getDrawable(R.drawable.sticker1);
        }else if(img.getImg()==1){
            return this.getResources().getDrawable(R.drawable.sticker2);
        }else if(img.getImg()==1){
            return this.getResources().getDrawable(R.drawable.sticker3);
        }else if(img.getImg()==2){
            return this.getResources().getDrawable(R.drawable.sticker4);
        }else if(img.getImg()==3){
            return this.getResources().getDrawable(R.drawable.sticker5);
        }else if(img.getImg()==4){
            return this.getResources().getDrawable(R.drawable.sticker6);
        }else if(img.getImg()==5){
            return this.getResources().getDrawable(R.drawable.sticker7);
        }else if(img.getImg()==6){
            return this.getResources().getDrawable(R.drawable.sticker8);
        }/*else if(img.getImg()==7){
                return context.getResources().getDrawable(R.drawable.sticker2);
            }else if(img.getImg()==8){
                return context.getResources().getDrawable(R.drawable.sticker2);
            }*/

        return  null;

    }
}
