package com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment;

/**
 * Created by ghazanfarali on 12/02/2017.
 */

//public class Drawing2Fragment extends MasterFragment{
//
//    public CanvasView canvas = null;
//    Button btn_write_text,btn_draw,btn_pallete,btn_undo,btn_save,btn_load_image;
//    private int currentBackgroundColor = 0xffffffff;
//    ImageView back,undo,draw,colorPicker,rubber,save,camera_pick,shapes,text_write;
//    boolean bottom_sheet = false,like_bottomsheet=false;
//    FrameLayout canvas_frame,main_frame;
//    LinearLayout like_bottomsheet_layout;
//    RecyclerView rv_list_gallary;
//    GridLayoutManager lLayout;
//    ArrayList<StickerImg> smartToolsList;
//    StickerAdapter mygallaryAdapter;
//
//    private BottomSheetBehavior bottomSheetBehavior;
//
//    int color=-16777216;
//    private View view;
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        if (view == null) {
//            view = inflater.inflate(R.layout.activity_drawing, container, false);
//
//            initUI();
//
//        } else {
//            if (view != null)
//            {
//
//            }
//            //  userProfileActivity.hideHeaderLayout();
//            // userProfileActivity.setHeaderTitle("");
//        }
//        return view;
//
//    }
//
//    @Override
//    public void initUI() {
//        super.initUI();
//
//        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottomSheetLayout));
//
//        canvas = (CanvasView)view.findViewById(R.id.canvas);
//
//        canvas_frame = (FrameLayout)view.findViewById(R.id.frame_main);
//
//        main_frame = (FrameLayout)view.findViewById(R.id.main_frame);
//
//        like_bottomsheet_layout = (LinearLayout)view.findViewById(R.id.like_bottomsheet);
//
//        back = (ImageView)view.findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //finish();
//            }
//        });
//        undo = (ImageView)view.findViewById(R.id.undo);
//        undo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                canvas.undo();
//            }
//        });
//
//        draw = (ImageView)view.findViewById(R.id.draw);
//        draw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!like_bottomsheet)
//                {
//
//                    like_bottomsheet_layout.setVisibility(View.VISIBLE);
//                    Button brush = (Button)view.findViewById(R.id.brush);
//                    brush.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            canvas.setOpacity(100);
//                            canvas.setBlur(10F);
//                            canvas.setLineCap(Paint.Cap.SQUARE);
//                            canvas.setPaintStyle(Paint.Style.STROKE);
//                            canvas.setPaintStrokeWidth(20F);
//                            canvas.setDrawer(CanvasView.Drawer.PEN);
//                            canvas.setMode(CanvasView.Mode.DRAW);
//                            like_bottomsheet = false;
//                            like_bottomsheet_layout.setVisibility(View.GONE);
//                        }
//                    });
//
//                    Button pen = (Button)view.findViewById(R.id.pen);
//                    pen.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            canvas.setOpacity(255);
//                            canvas.setBlur(0F);
//                            canvas.setPaintStyle(Paint.Style.STROKE);
//                            canvas.setPaintStrokeWidth(3F);
//                            canvas.setDrawer(CanvasView.Drawer.PEN);
//                            canvas.setMode(CanvasView.Mode.DRAW);
//                            like_bottomsheet = false;
//                            like_bottomsheet_layout.setVisibility(View.GONE);
//                        }
//                    });
//
//                    Button crayon = (Button)view.findViewById(R.id.crayon);
//                    crayon.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            canvas.setOpacity(255);
//                            canvas.setBlur(0F);
//
//                            canvas.setLineCap(Paint.Cap.ROUND);
//                            canvas.setPaintStyle(Paint.Style.STROKE);
//                            canvas.setPaintStrokeWidth(20F);
//                            canvas.setDrawer(CanvasView.Drawer.PEN);
//                            canvas.setMode(CanvasView.Mode.DRAW);
//                            like_bottomsheet = false;
//                            like_bottomsheet_layout.setVisibility(View.GONE);
//                        }
//                    });
//
//
//                    Button paint = (Button)view.findViewById(R.id.paint);
//                    paint.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            canvas.setOpacity(255);
//                            canvas.setBlur(0F);
//
//                            canvas.setLineCap(Paint.Cap.BUTT);
//                            canvas.setPaintStyle(Paint.Style.STROKE);
//                            canvas.setPaintStrokeWidth(30F);
//                            canvas.setDrawer(CanvasView.Drawer.PEN);
//                            canvas.setMode(CanvasView.Mode.DRAW);
//
//                            like_bottomsheet = false;
//                            like_bottomsheet_layout.setVisibility(View.GONE);
//                        }
//                    });
//
//
//
//                    like_bottomsheet = true;
//                }else{
//
//
//                    like_bottomsheet = false;
//                    like_bottomsheet_layout.setVisibility(View.GONE);
//                }
//
//
//            }
//        });
//        colorPicker = (ImageView)view.findViewById(R.id.colorPicker);
//        colorPicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ColorPickerDialogBuilder
//                        .with(getActivity())
//                        .setTitle("Choose color")
//                        .initialColor(currentBackgroundColor)
//                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                        .density(12)
//                        .setOnColorSelectedListener(new OnColorSelectedListener() {
//                            @Override
//                            public void onColorSelected(int selectedColor) {
//                                // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
//                            }
//                        })
//                        .setPositiveButton("ok", new ColorPickerClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                                //changeBackgroundColor(selectedColor);
//
//                                color = selectedColor;
//                                canvas.setPaintStrokeColor(selectedColor);
//                            }
//                        })
//                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
//                        .build()
//                        .show();
//            }
//        });
//        rubber = (ImageView)view.findViewById(R.id.rubber);
//        rubber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                canvas.setMode(CanvasView.Mode.ERASER);
//
//
//            }
//        });
//        save = (ImageView)view.findViewById(R.id.save);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAttachmentSave();
//
//
//            }
//        });
//
//        camera_pick = (ImageView)view.findViewById(R.id.camera_pick);
//        camera_pick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showAttachmentDialog();
//            }
//        });
//
//        shapes = (ImageView)view.findViewById(R.id.shapes);
//        shapes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(!bottom_sheet) {
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    TextView view_set_basicshapes = (TextView)view.findViewById(R.id.view_set_basicshapes);
//                    view_set_basicshapes.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            LinearLayout View_Accessories = (LinearLayout)view.findViewById(R.id.View_Accessories);
//                            View_Accessories.setVisibility(View.INVISIBLE);
//                            LinearLayout View_BasicShapes = (LinearLayout)view.findViewById(R.id.View_BasicShapes);
//                            View_BasicShapes.setVisibility(View.VISIBLE);
//                            ImageView draw_square = (ImageView)view.findViewById(R.id.draw_square);
//                            draw_square.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    //  canvas.setMode(CanvasView.Mode.DRAW);
//                                    //  canvas.setDrawer(CanvasView.Drawer.RECTANGLE);
//
//                                    StickerImageView tv_sticker = new StickerImageView(getActivity());
//                                    // tv_sticker.iv_main
//                                    DrawableCompat.setTint(tv_sticker.iv_main.getDrawable(), color);
//                                    //setText(editText.getText().toString());
//
//                                    canvas_frame.addView(tv_sticker);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//
//                          /*  ImageView draw_triangle = (ImageView)findViewById(R.id.draw_triangle);
//                            draw_triangle.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//
//                                    canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                   *//* StickerImageView tv_sticker = new StickerImageView(Drawing2Activity.this);
//                                    tv_sticker.setBackgroundResource(R.drawable.triangle_myimage);//setText(editText.getText().toString());
//
//                                    canvas_frame.addView(tv_sticker);
//                                   *//**//* canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);*//**//*
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;*//*
//                                }
//                            });
//
//
//                            ImageView draw_ninty = (ImageView)findViewById(R.id.draw_ninty);
//                            draw_ninty.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                   *//* canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);*//*
//
//                                    StickerImageView tv_sticker = new StickerImageView(Drawing2Activity.this);
//                                    tv_sticker.setBackgroundResource(R.drawable.square_myimage);//setText(editText.getText().toString());
//
//                                    canvas_frame.addView(tv_sticker);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//                            ImageView draw_fourangle = (ImageView)findViewById(R.id.draw_fourangle);
//                            draw_fourangle.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                   *//* canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);*//*
//
//                                    StickerImageView tv_sticker = new StickerImageView(Drawing2Activity.this);
//                                    tv_sticker.setBackgroundResource(R.drawable.circle_myshape);//setText(editText.getText().toString());
//
//                                    canvas_frame.addView(tv_sticker);
//
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//
//                            ImageView draw_pentagone = (ImageView)findViewById(R.id.draw_pentagone);
//                            draw_pentagone.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    *//*canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);*//*
//                                    StickerImageView tv_sticker = new StickerImageView(Drawing2Activity.this);
//                                    tv_sticker.setBackgroundResource(R.drawable.triangle_myimage);//setText(editText.getText().toString());
//
//                                    canvas_frame.addView(tv_sticker);
//                                   *//* canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);*//*
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//                            ImageView draw_sixgone = (ImageView)findViewById(R.id.draw_sixgone);
//                            draw_sixgone.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//                            ImageView draw_sevengone = (ImageView)findViewById(R.id.draw_sevengone);
//                            draw_sevengone.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//                            ImageView draw_eightgone = (ImageView)findViewById(R.id.draw_eightgone);
//                            draw_eightgone.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });
//
//                            ImageView draw_ninegone = (ImageView)findViewById(R.id.draw_ninegone);
//                            draw_ninegone.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    canvas.setMode(CanvasView.Mode.DRAW);
//                                    canvas.setDrawer(CanvasView.Drawer.TRIANGLE);
//                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                    bottom_sheet = false;
//                                }
//                            });*/
//
//
//
//                        }
//                    });
//                    TextView view_set_accessorries = (TextView)view.findViewById(R.id.view_set_accessorries);
//                    view_set_accessorries.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            LinearLayout View_Accessories = (LinearLayout)view.findViewById(R.id.View_Accessories);
//                            View_Accessories.setVisibility(View.VISIBLE);
//                            LinearLayout View_BasicShapes = (LinearLayout)view.findViewById(R.id.View_BasicShapes);
//                            View_BasicShapes.setVisibility(View.INVISIBLE);
//                        }
//                    });
//
//                    rv_list_gallary = (RecyclerView)view.findViewById(R.id.rv_listview_mygallary);
//                    rv_list_gallary.addItemDecoration(new MyGallaryITemDecor(getActivity()));
//                    smartToolsList = new ArrayList<>();
//                    smartToolsList = prepareListData();
//                    mygallaryAdapter = new StickerAdapter(smartToolsList,getActivity());
//                    rv_list_gallary.setAdapter(mygallaryAdapter);
//
//                    mygallaryAdapter.setOnItemClickListener(new OnItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            /*Toast.makeText(Drawing2Activity.this,""+position,Toast.LENGTH_LONG).show();*/
//                            StickerImageView tv_sticker = new StickerImageView(getActivity());
//                            Drawable drawable = settingDrawable(smartToolsList.get(position));
//                            tv_sticker.setImageDrawable(drawable);
//                            canvas_frame.addView(tv_sticker);
//                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                            bottom_sheet = false;
//                        }
//                    });
//                    lLayout = new GridLayoutManager(getActivity(), 4);
//                    rv_list_gallary.setHasFixedSize(true);
//                    rv_list_gallary.setLayoutManager(lLayout);
//
//                    bottom_sheet = true;
//                }else{
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                    bottom_sheet = false;
//                }
//
//            }
//        });
//
//        text_write = (ImageView)view.findViewById(R.id.text_write);
//        text_write.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater = LayoutInflater.from(getActivity());
//                final View views = inflater.inflate(R.layout.dialog_change_text,null);
//                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
//                builder.setTitle("Write Text");
//                builder.setView(views);
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        EditText editText = (EditText) views.findViewById(R.id.editText);
//                        //canvas.setDrawer(CanvasView.Drawer.QUADRATIC_BEZIER);
//                        //canvas.setText(editText.getText().toString());
//                        StickerTextView tv_sticker = new StickerTextView(getActivity());
//                        tv_sticker.setText(editText.getText().toString());
//
//                        canvas_frame.addView(tv_sticker);
//                    }
//                });
//                builder.create();
//                builder.show();
//            }
//        });
//
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//
//
//    public Drawable settingDrawable(StickerImg img)
//    {
//        if(img.getImg()==0)
//        {
//            return this.getResources().getDrawable(R.drawable.sticker1);
//        }else if(img.getImg()==1){
//            return this.getResources().getDrawable(R.drawable.sticker2);
//        }else if(img.getImg()==1){
//            return this.getResources().getDrawable(R.drawable.sticker3);
//        }else if(img.getImg()==2){
//            return this.getResources().getDrawable(R.drawable.sticker4);
//        }else if(img.getImg()==3){
//            return this.getResources().getDrawable(R.drawable.sticker5);
//        }else if(img.getImg()==4){
//            return this.getResources().getDrawable(R.drawable.sticker6);
//        }else if(img.getImg()==5){
//            return this.getResources().getDrawable(R.drawable.sticker7);
//        }else if(img.getImg()==6){
//            return this.getResources().getDrawable(R.drawable.sticker8);
//        }/*else if(img.getImg()==7){
//                return context.getResources().getDrawable(R.drawable.sticker2);
//            }else if(img.getImg()==8){
//                return context.getResources().getDrawable(R.drawable.sticker2);
//            }*/
//
//        return  null;
//
//    }
//
//
//    String imageMain,imageName;
//    String encodedImage,encodedImage2;
//    public void showAttachmentSave() {
//        final Dialog addImageDialog;
//        // popup..
//        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_chooser_save, null);
//        addImageDialog = new Dialog(getActivity());
//
//        TextView txt = (TextView) addImageDialog.findViewById(android.R.id.title);
//        txt.setVisibility(View.GONE);
//        /*addImageDialog.setTitle("Image Picker");*/
//        addImageDialog.setContentView(getActivity().getLayoutInflater().inflate(
//                R.layout.dialog_chooser_save, null));
//        addImageDialog.show();
//
//        Button shareToPublic = (Button) addImageDialog
//                .findViewById(R.id.shareToPublic);
//        Button saveToGallery = (Button) addImageDialog
//                .findViewById(R.id.saveToGallery);
//
//        shareToPublic.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                addImageDialog.dismiss();
//                imageMain = Long.toString(System.currentTimeMillis());
//
//                canvas_frame.setDrawingCacheEnabled(true);
//                canvas_frame.buildDrawingCache();
//                Bitmap bm = canvas_frame.getDrawingCache();
//                byte[]  bytess= canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] bytes = stream.toByteArray();
//                main_frame.setDrawingCacheEnabled(true);
//                main_frame.buildDrawingCache();
//                Bitmap bmMain = main_frame.getDrawingCache();
//                ByteArrayOutputStream streambmMain = new ByteArrayOutputStream();
//                bmMain.compress(Bitmap.CompressFormat.PNG, 100, streambmMain);
//
//                byte[] bytesain = streambmMain.toByteArray();
//                encodedImage = Base64.encodeToString(bytesain, Base64.DEFAULT);
//                // byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
//                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
//                File f = null;
//                if(!file.exists()) {
//                    file.mkdirs();
//                    f = new File(file + "/", imageMain + ".jpg");
//                    imageName = f.getName();
//                }else{
//                    f = new File(file + "/", imageMain + ".jpg");
//                    imageName = f.getName();
//                }
//                FileOutputStream fos = null;
//                try {
//                    fos = new FileOutputStream(f);
//                    fos.write(bytesain);
//                    fos.flush();
//                    fos.close();
//                    Toast.makeText(getActivity(),"File Saved",Toast.LENGTH_SHORT).show();
//                    main_frame.destroyDrawingCache();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Bundle bundle=new Bundle();
//                bundle.putString("fileName",f.getAbsolutePath());
//                bundle.putString("fileDir","shareToPublic");
//                //"fileDir","shareToPublic"
//                //set Fragmentclass Arguments
//                SaveDrawingFragment fragobj=new SaveDrawingFragment();
//                fragobj.setArguments(bundle);
//
//                userProfileActivity.replaceFragmnet(new SaveDrawingFragment(), R.id.frameLayout, true);
//                //Intent i = new Intent(getActivity(), SaveDrawingActivity.class);
//                //i.putExtra("fileName",f.getAbsolutePath());
//                //i.putExtra("fileDir","shareToPublic");
//                //startActivityForResult(i, 3);
//                /*String image = Long.toString(System.currentTimeMillis());
//                byte[] bytes = canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
//                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLandShare");
//                File f = null;
//                if(!file.exists()) {
//                    file.mkdirs();
//                    f = new File(file + "/", image + ".jpg");
//                }else{
//                    f = new File(file + "/", image + ".jpg");
//                }
//                FileOutputStream fos = null;
//                try {
//                    fos = new FileOutputStream(f);
//                    fos.write(bytes);
//                    fos.flush();
//                    fos.close();
//                    Toast.makeText(Drawing2Activity.this,"File Saved",Toast.LENGTH_SHORT).show();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                finish();*/
//
//            }
//        });
//        saveToGallery.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                addImageDialog.dismiss();
//                // Intent i = new Intent(Drawing2Activity.this, SaveDrawingActivity.class);
//                // startActivityForResult(i, 4);
//                imageMain = Long.toString(System.currentTimeMillis());
//                canvas_frame.setDrawingCacheEnabled(true);
//                canvas_frame.buildDrawingCache();
//                Bitmap bm = canvas_frame.getDrawingCache();
//                byte[]  bytess= canvas.getBitmapAsByteArray(Bitmap.CompressFormat.PNG, 100);
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] bytes = stream.toByteArray();
//                main_frame.setDrawingCacheEnabled(true);
//                main_frame.buildDrawingCache();
//                Bitmap bmMain = main_frame.getDrawingCache();
//                ByteArrayOutputStream streambmMain = new ByteArrayOutputStream();
//                bmMain.compress(Bitmap.CompressFormat.PNG, 100, streambmMain);
//
//                byte[] bytesain = streambmMain.toByteArray();
//
//                encodedImage2 = Base64.encodeToString(bytesain, Base64.DEFAULT);
//                //byte[] c = new byte[bytes.length + bytess.length];
//                // System.arraycopy(bytes,0,c,0,bytes.length);
//                //System.arraycopy(bytess,0,c,bytes.length,bytess.length);
//                // List<Byte> list = new ArrayList<Byte>(Arrays.<Byte>asList(bytess));
//                //list.addAll(Arrays.<Byte>asList(bytes));
//
//                byte[] combined = ArrayUtils.addAll(bytess,bytes);
//
//                /*byte[] one = bytess;
//                byte[] two = bytes;
//                byte[] combined = new byte[one.length + two.length];
//
//                for (int i = 0; i < combined.length; ++i)
//                {
//                    combined[i] = i < one.length ? one[i] : two[i - one.length];
//                }*/
//
//                File file = new File(android.os.Environment.getExternalStorageDirectory()+"/","PiggyLand");
//                File f = null;
//                if(!file.exists()) {
//                    file.mkdirs();
//                    f = new File(file + "/", imageMain + ".jpg");
//                    imageName = f.getName();
//                }else{
//                    f = new File(file + "/", imageMain + ".jpg");
//                    imageName = f.getName();
//                    main_frame.destroyDrawingCache();
//                }
//                FileOutputStream fos = null;
//                try {
//                    fos = new FileOutputStream(f);
//                    fos.write(bytesain);
//                    // fos.write(bytes);
//                    fos.flush();
//                    fos.close();
//
//                    Toast.makeText(getActivity(),"File Saved",Toast.LENGTH_SHORT).show();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//               // Intent i = new Intent(getActivity(), SaveDrawingActivity.class);
//              //  i.putExtra("fileName",f.getAbsolutePath());
//              //  i.putExtra("fileDir","saveToGallery");
//              //  startActivityForResult(i, 4);
//
//                Bundle bundle=new Bundle();
//                bundle.putString("fileName",f.getAbsolutePath());
//                bundle.putString("fileDir","saveToGallery");
//                //"fileDir","shareToPublic"
//                //set Fragmentclass Arguments
//                SaveDrawingFragment fragobj=new SaveDrawingFragment();
//                fragobj.setArguments(bundle);
//
//                userProfileActivity.replaceFragmnet(new SaveDrawingFragment(), R.id.frameLayout, true);
//                //  finish();
//            }
//        });
//
//    }
//
//
//    private ArrayList<StickerImg> prepareListData() {
//
//        ArrayList<StickerImg> temp = new ArrayList<>();
//
//        StickerImg mModel = new StickerImg();
//        mModel.setImg(0);
//        /*mModel.setmygallaryTitle("Favorite road trip");
//        mModel.setmygallaryImageURL(R.drawable.piggylandbg);*/
//        temp.add(mModel);
//
//        mModel = new StickerImg();
//        mModel.setImg(1);
//        temp.add(mModel);
//
//        mModel = new StickerImg();
//        mModel.setImg(2);
//        temp.add(mModel);
//
//        mModel = new StickerImg();
//        mModel.setImg(3);
//        temp.add(mModel);
//
//        mModel = new StickerImg();
//        mModel.setImg(4);
//        temp.add(mModel);
//
//        mModel = new StickerImg();
//        mModel.setImg(5);
//        temp.add(mModel);
//
//        mModel = new StickerImg();
//        mModel.setImg(6);
//        temp.add(mModel);
//
//        return temp;
//    }
//
//
//    public void showAttachmentDialog() {
//        final Dialog addImageDialog;
//        // popup..
//        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_chooser, null);
//        addImageDialog = new Dialog(getActivity());
//
//        addImageDialog.setTitle("Image Picker");
//        addImageDialog.setContentView(getActivity().getLayoutInflater().inflate(
//                R.layout.dialog_chooser, null));
//        addImageDialog.show();
//
//        ImageButton ibCamera = (ImageButton) addImageDialog
//                .findViewById(R.id.add_suggestion_dialog_ib_camera);
//        ImageButton ibGallery = (ImageButton) addImageDialog
//                .findViewById(R.id.add_suggestion_dialog_ib_gallery);
//
//        ibCamera.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                addImageDialog.dismiss();
//                //startCameraOrGallery("camera");
//                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
//                getActivity().startActivityForResult(takePicture, 0);
//            }
//        });
//        ibGallery.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                addImageDialog.dismiss();
//                // startCameraOrGallery("gallery");
//                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                getActivity().startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
//
//            }
//        });
//
//    }
//
//    String imgPath;
//    String selectedImagePath;
//    public Uri setImageUri() {
//        // Store image in dcim
//        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
//        Uri imgUri = Uri.fromFile(file);
//        this.imgPath = file.getAbsolutePath();
//        return imgUri;
//    }
//
//
//
//}
