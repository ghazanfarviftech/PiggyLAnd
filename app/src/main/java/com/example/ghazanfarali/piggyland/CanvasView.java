package com.example.ghazanfarali.piggyland;

/**
 * Created by ghazanfarali on 25/12/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.ghazanfarali.piggyland.CustomViews.GetterAndSetter;
import com.example.ghazanfarali.piggyland.Views.Fragments.DrawingFragment.MainDrawingFragment;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
// import android.util.Log;
// import android.widget.Toast;

/**
 * This class defines fields and methods for drawing.
 */
public class CanvasView extends View {

    // Enumeration for Mode
    public enum Mode {
        DRAW,
        TEXT,
        ERASER;
    }

    // Enumeration for Drawer
    public enum Drawer {
        PEN,
        LINE,
        RECTANGLE,
        CIRCLE,
        ELLIPSE,
        QUADRATIC_BEZIER,
        TRIANGLE,
        NINTY,
        OCTAGONE,
        QUBIC_BEZIER;
    }

    private Context context = null;
    private Canvas canvas   = null;
    private Bitmap bitmap   = null;

    private List<Path>  pathLists  = new ArrayList<Path>();
    private List<Paint> paintLists = new ArrayList<Paint>();

    // for Eraser
    private int baseColor = Color.WHITE;

    // for Undo, Redo
    private int historyPointer = 0;

    // Flags
    private Mode mode      = Mode.DRAW;
    private Drawer drawer  = Drawer.PEN;
    private boolean isDown = false;

    // for Paint
    private Paint.Style paintStyle = Paint.Style.STROKE;
    private int paintStrokeColor   = Color.BLACK;
    private int paintFillColor     = Color.BLACK;
    private float paintStrokeWidth = 3F;
    private int opacity            = 255;
    private float blur             = 0F;
    private Paint.Cap lineCap      = Paint.Cap.ROUND;

    // for Text
    private String text           = "";
    private Typeface fontFamily   = Typeface.DEFAULT;
    private float fontSize        = 32F;
    private Paint.Align textAlign = Paint.Align.RIGHT;  // fixed
    private Paint textPaint       = new Paint();
    private float textX           = 0F;
    private float textY           = 0F;

    // for Drawer
    private float startX   = 0F;
    private float startY   = 0F;
    private float controlX = 0F;
    private float controlY = 0F;


    private int canvasBackgroundColor = Color.WHITE;

    /**
     * Copy Constructor
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setup(context);
    }

    /**
     * Copy Constructor
     *
     * @param context
     * @param attrs
     */
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setup(context);
    }

    /**
     * Copy Constructor
     *
     * @param context
     */
    public CanvasView(Context context) {
        super(context);
        this.setup(context);
    }

    /**
     * Common initialization.
     *
     * @param context
     */
    private void setup(Context context) {
        this.context = context;

        this.pathLists.add(new Path());
        this.paintLists.add(this.createPaint());
        this.historyPointer++;

        this.textPaint.setARGB(0, 255, 255, 255);
    }

    /**
     * This method creates the instance of Paint.
     * In addition, this method sets styles for Paint.
     *
     * @return paint This is returned as the instance of Paint
     */
    private Paint createPaint() {
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(this.paintStyle);
        paint.setStrokeWidth(this.paintStrokeWidth);
        paint.setStrokeCap(this.lineCap);
        paint.setStrokeJoin(Paint.Join.MITER);  // fixed

        // for Text
        if (this.mode == Mode.TEXT) {
            paint.setTypeface(this.fontFamily);
            paint.setTextSize(this.fontSize);
            paint.setTextAlign(this.textAlign);
            paint.setStrokeWidth(0F);
        }

        if (this.mode == Mode.ERASER) {

            // paint.setColor(this.baseColor);
            // paint.setShadowLayer(this.blur, 0F, 0F, this.baseColor);
            paint.setColor(canvasBackgroundColor);
            paint.setStrokeWidth(MainDrawingFragment.brush_eraser_stroke_width);
            paint.setShadowLayer(this.blur, 0F, 0F, this.paintStrokeColor);
            paint.setAlpha(this.opacity);
        } else {
            // Otherwise
            paint.setColor(this.paintStrokeColor);
            paint.setShadowLayer(this.blur, 0F, 0F, this.paintStrokeColor);
            paint.setAlpha(this.opacity);
        }

        return paint;
    }

    /**
     * This method initialize Path.
     * Namely, this method creates the instance of Path,
     * and moves current position.
     *
     * @param event This is argument of onTouchEvent method
     * @return path This is returned as the instance of Path
     */
    private Path createPath(MotionEvent event) {
        Path path = new Path();

        // Save for ACTION_MOVE
        this.startX = event.getX();
        this.startY = event.getY();
        System.out.println("createPath before move to position startX "+ event.getX()+" startY "+ event.getY());

        path.moveTo(this.startX, this.startY);

        System.out.println("createPath after move to position startX "+ this.startX+" startY "+ this.startY);
        return path;
    }

    /**
     * This method updates the lists for the instance of Path and Paint.
     * "Undo" and "Redo" are enabled by this method.
     *
     * @param path the instance of Path
     * @parampaints the instance of Paint
     */
    private void updateHistory(Path path) {
        if (this.historyPointer == this.pathLists.size()) {
            this.pathLists.add(path);
            this.paintLists.add(this.createPaint());
            this.historyPointer++;
            System.out.println("updateHistory to create a new paint and to add path in pathLists "+ path);
        } else {
            // On the way of Undo or Redo
            this.pathLists.set(this.historyPointer, path);
            this.paintLists.set(this.historyPointer, this.createPaint());
            this.historyPointer++;

            for (int i = this.historyPointer, size = this.paintLists.size(); i < size; i++) {
                this.pathLists.remove(this.historyPointer);
                this.paintLists.remove(this.historyPointer);
            }
        }
    }

    /**
     * This method gets the instance of Path that pointer indicates.
     *
     * @return the instance of Path
     */
    private Path getCurrentPath() {
        return this.pathLists.get(this.historyPointer - 1);
    }

    /**
     * This method draws text.
     *
     * @param canvas the instance of Canvas
     */

    ArrayList<String> multitext = new ArrayList<>();
    ArrayList<Float> textXs = new ArrayList<>();
    ArrayList<Float> textYs = new ArrayList<>();
    //  float[] textYs = new float[1000] ;
    View views;
    public static boolean settingnewtxt = false;
    public static boolean settingfortextmode = false;
    private void drawText(Canvas canvas) {
        if (this.text.length() <= 0) {
            return;
        }

        if (this.mode == Mode.TEXT) {
            this.textX = this.startX;
            this.textY = this.startY;

            this.textPaint = this.createPaint();

            if(settingfortextmode)
            {
                for(int l =0; l<textXs.size();l++)
                {
                    textXs.set(textXs.size()-1,textX);// = textX;
                    textYs.set(textXs.size()-1,textY);
                }
            }else {
                multitext.add(text);
                textXs.add(textX);// = textX;
                textYs.add(textY);
                settingfortextmode = true;
            }

        }





        for(int i=0;i<multitext.size();i++)
        {
            float textX = textXs.get(i);//this.textX;
            float textY = textYs.get(i);//this.textY;

            Paint paintForMeasureText = new Paint();

            // Line break automatically
            float textLength   = paintForMeasureText.measureText(multitext.get(i));
            float lengthOfChar = textLength / (float)multitext.get(i).length();
            float restWidth    = this.canvas.getWidth() - textX;  // text-align : right
            int numChars       = (lengthOfChar <= 0) ? 1 : (int)Math.floor((double)(restWidth / lengthOfChar));  // The number of characters at 1 line
            int modNumChars    = (numChars < 1) ? 1 : numChars;
            float y            = textY;

            for (int k = 0, len = multitext.get(i).length(); k < len; k += modNumChars) {
                String substring = "";

                if ((k + modNumChars) < len) {
                    substring = multitext.get(i).substring(k, (k + modNumChars));
                } else {
                    substring = multitext.get(i).substring(k, len);
                }

                y += this.fontSize;

                canvas.drawText(substring, textX, y, this.textPaint);
            }
        }






    }


    ArrayList<GetterAndSetter> myPAthForSmooth;
    /**
     * This method defines processes on MotionEvent.ACTION_DOWN
     *
     * @param event This is argument of onTouchEvent method
     */
    private void onActionDown(MotionEvent event) {
        System.out.println("onActionDown ");
        System.out.println("mode "+this.mode);

        switch (this.mode) {
            case DRAW   :
            case ERASER :
                System.out.println("in switch case mode DRAW ERASER "+this.mode);
                myPAthForSmooth =  new ArrayList<>();
                if ((this.drawer != Drawer.QUADRATIC_BEZIER) && (this.drawer != Drawer.QUBIC_BEZIER)) {

                    System.out.println("this.drawer != Drawer.QUADRATIC_BEZIER this.isDown = true");


                    // Oherwise
                    this.updateHistory(this.createPath(event));
                    this.isDown = true;
                    GetterAndSetter getterAndSetter = new GetterAndSetter();
                    getterAndSetter.setX(event.getX());
                    getterAndSetter.setY(event.getY());

                    myPAthForSmooth.add(getterAndSetter);
                } else {
                    System.out.println("Bezier ");
                    // Bezier
                    if ((this.startX == 0F) && (this.startY == 0F)) {
                        // The 1st tap

                        System.out.println("The 1st tap this.startX "+this.startX+" this.startY "+this.startY+
                                " event getX() "+event.getX()+" event getX() "+event.getY());

                        this.updateHistory(this.createPath(event));
                    } else {
                        // The 2nd tap
                        System.out.println("// The 2nd tap this.startX "+this.startX+" this.startY "+this.startY+
                                " event getX() "+event.getX()+" event getX() "+event.getY());

                        this.controlX = event.getX();
                        this.controlY = event.getY();

                        System.out.println("// The 2nd tap  this.isDown = true this.controlX "+this.controlX+" this.controlY "+this.controlY+
                                " event getX() "+event.getX()+" event getX() "+event.getY());

                        this.isDown = true;
                    }
                }

                break;
            case TEXT   :
                this.startX = event.getX();
                this.startY = event.getY();

                break;
            default :
                break;
        }
    }

    /**
     * This method defines processes on MotionEvent.ACTION_MOVE
     *
     * @param event This is argument of onTouchEvent method
     */
    private void onActionMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        System.out.println("onActionMove  event.getX() "+event.getX()+" event.getY() "+event.getY());

        switch (this.mode) {
            case DRAW   :
            case ERASER :

                System.out.println("onActionMove  this.mode "+this.mode);
                if ((this.drawer != Drawer.QUADRATIC_BEZIER) && (this.drawer != Drawer.QUBIC_BEZIER)) {

                    System.out.println("this.drawer != Drawer.QUADRATIC_BEZIER");


                    if (!isDown) {
                        System.out.println("this.drawer != Drawer.QUADRATIC_BEZIER isDown "+isDown);

                        return;
                    }

                    Path path = this.getCurrentPath();
                    System.out.println("path "+path+" isDown "+isDown);

                    Double halfWidth;
                    int i;
                    switch (this.drawer) {
                        case PEN :
                            path.lineTo(x, y);

                            GetterAndSetter getterAndSetter = new GetterAndSetter();
                            getterAndSetter.setX(x);
                            getterAndSetter.setY(y);

                            myPAthForSmooth.add(getterAndSetter);

                            System.out.println("PEN path.lineTo(x, y);"+x+" "+y);
                            break;
                        case LINE :
                            path.reset();
                            System.out.println("LINE  path.reset()"+x+" "+y);
                            path.moveTo(this.startX, this.startY);
                            System.out.println("LINE  path.moveTo(this.startX, this.startY)"+this.startX +" "+ this.startY);
                            path.lineTo(x, y);

                            System.out.println("LINE  path.lineTo(x, y)"+x+" "+y);


                            break;
                        case RECTANGLE :
                            path.reset();
                            System.out.println("RECTANGLE  path.reset()"+x+" "+y);
                            path.addRect(this.startX, this.startY, x, y, Path.Direction.CCW);

                            System.out.println("LINE  path.addRect(this.startX, this.startY, x, y, Path.Direction.CCW)"+this.startX +" "+ this.startY
                                    +" "+x+" "+y);
                            break;
                        case CIRCLE :
                            double distanceX = Math.abs((double)(this.startX - x));
                            System.out.println("CIRCLE  distanceX "+distanceX+ " Math.abs((double)(this.startX - x) "+this.startX+" "+x);

                            double distanceY = Math.abs((double)(this.startX - y));

                            System.out.println("CIRCLE  distanceY "+distanceY+ " Math.abs((double)(this.startX - y) "+this.startX+" "+y);


                            double radius    = Math.sqrt(Math.pow(distanceX, 2.0) + Math.pow(distanceY, 2.0));

                            System.out.println("CIRCLE  radius "+radius+ " Math.sqrt(Math.pow(distanceX, 2.0) + Math.pow(distanceY, 2.0) ");

                            path.reset();
                            System.out.println("CIRCLE   path.reset() ");
                            path.addCircle(this.startX, this.startY, (float)radius, Path.Direction.CCW);

                            System.out.println("CIRCLE    path.addCircle(this.startX, this.startY, (float)radius, Path.Direction.CCW) "+
                                    this.startX+" "+ this.startY +" " +(float)radius+ " "+ Path.Direction.CCW);


                            break;
                        case ELLIPSE :
                            RectF rect = new RectF(this.startX, this.startY, x, y);

                            path.reset();
                            path.addOval(rect, Path.Direction.CCW);
                            break;
                        case TRIANGLE:
                            double distanceXT = Math.abs((double)(this.startX - x));
                            System.out.println("CIRCLE  distanceX "+distanceXT+ " Math.abs((double)(this.startX - x) "+this.startX+" "+x);

                            double distanceYT = Math.abs((double)(this.startY - y));

                            System.out.println("CIRCLE  distanceY "+distanceYT+ " Math.abs((double)(this.startX - y) "+this.startX+" "+y);
                            halfWidth    = Math.sqrt(Math.pow(distanceXT, 2.0) + Math.pow(distanceYT, 2.0));
                          /*  int halfWidth = */
                            i = Integer.valueOf(halfWidth.intValue());//100 / 2;
                            path.reset();
                            path.moveTo(this.startX, this.startX); // Top
                            path.lineTo(x - i, y + i); // Bottom left
                            path.lineTo(x + i, y + i); // Bottom right
                            path.lineTo(x, y - i); // Back to Top
                            path.close();
                           /* Paint paint = new Paint();

                            paint.setAntiAlias(true);
                            paint.setStyle(this.paintStyle);
                            paint.setStrokeWidth(this.paintStrokeWidth);
                            paint.setStrokeCap(this.lineCap);
                            paint.setStrokeJoin(Paint.Join.MITER);*/
                            path.addPath(path);//drawPath(path,paint);

                            break;
                        case NINTY:
                            double distanceXNIN = Math.abs((double)(this.startX - x));
                            System.out.println("CIRCLE  distanceX "+distanceXNIN+ " Math.abs((double)(this.startX - x) "+this.startX+" "+x);

                            double distanceYNIN = Math.abs((double)(this.startY - y));

                            System.out.println("CIRCLE  distanceY "+distanceYNIN+ " Math.abs((double)(this.startX - y) "+this.startX+" "+y);
                            halfWidth    = Math.sqrt(Math.pow(distanceXNIN, 2.0) + Math.pow(distanceYNIN, 2.0));
                          /*  int halfWidth = */
                            i = Integer.valueOf(halfWidth.intValue());//100 / 2;
                            path.reset();
                            path.moveTo(this.startX, this.startY); // Top
                            path.lineTo(x - i, y + i); // Bottom left
                            path.lineTo(x + i, y + i); // Bottom right
                            path.lineTo(x, y - i); // Back to Top
                            path.close();

                            path.addPath(path);
                            break;

                        case OCTAGONE:
                            double distanceXOCT = Math.abs((double)(this.startX - x));
                            System.out.println("CIRCLE  distanceX "+distanceXOCT+ " Math.abs((double)(this.startX - x) "+this.startX+" "+x);

                            double distanceYOCT = Math.abs((double)(this.startY - y));

                            System.out.println("CIRCLE  distanceY "+distanceYOCT+ " Math.abs((double)(this.startX - y) "+this.startX+" "+y);
                            halfWidth    = Math.sqrt(Math.pow(distanceXOCT, 2.0) + Math.pow(distanceYOCT, 2.0));
                          /*  int halfWidth = */
                            i = Integer.valueOf(halfWidth.intValue());//100 / 2;
                            path.reset();
                            path.moveTo(this.startX, this.startY); // Top
                            path.lineTo(this.startX, y + i); // Bottom left
                            path.lineTo(x + i, y + i); // Bottom right
                            path.lineTo(x, y - i); // Back to Top
                            path.close();

                            path.addPath(path);
                            break;
                        default :
                            break;
                    }
                } else {

                    System.out.println("isDown "+isDown+"if this.drawer == Drawer.QUADRATIC_BEZIER");

                    if (!isDown) {
                        return;
                    }

                    Path path = this.getCurrentPath();

                    path.reset();
                    path.moveTo(this.startX, this.startY);
                    path.quadTo(this.controlX, this.controlY, x, y);

                    System.out.println(" path.reset(); "+isDown+"path.moveTo(this.startX, this.startY) "
                            +this.startX+" "+this.startY+"path.quadTo(this.controlX, this.controlY, x, y) "+this.controlX+" "
                            +this.controlY +" "+x +" "+y);

                }

                break;
            case TEXT :
                this.startX = x;
                this.startY = y;

                break;
            default :
                break;
        }
    }

    /**
     * This method defines processes on MotionEvent.ACTION_DOWN
     *
     * @param event This is argument of onTouchEvent method
     */
    private void onActionUp(MotionEvent event) {
        System.out.println(" onActionUp ");
        if (isDown) {
            System.out.println(" onActionUp isDown " + isDown);
            this.startX = 0F;
            this.startY = 0F;
            this.isDown = false;
            Path path = new Path();
            for(int i=0;i< myPAthForSmooth.size();i++)
            {
                if(i==0)
                {
                    System.out.println(" onActionUp ifi==0 ");
                    path.moveTo(myPAthForSmooth.get(i).getX(),myPAthForSmooth.get(i).getY());

                }else{
                    System.out.println(" onActionUp if else ");
                    path.lineTo(myPAthForSmooth.get(i).getX(),myPAthForSmooth.get(i).getY());
                }
            }
        }
    }

    /**
     * This method updates the instance of Canvas (View)
     *
     * @param canvas the new instance of Canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Before "drawPath"
        canvas.drawColor(this.baseColor);

        if (this.bitmap != null) {
            canvas.drawBitmap(this.bitmap, 0F, 0F, new Paint());
        }

        for (int i = 0; i < this.historyPointer; i++) {
            Path path   = this.pathLists.get(i);
            Paint paint = this.paintLists.get(i);

            canvas.drawPath(path, paint);
        }

        this.drawText(canvas);

        this.canvas = canvas;
    }

    /**
     * This method set event listener for drawing.
     *
     * @param event the instance of MotionEvent
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                System.out.println("MotionEvent.ACTION_DOWN");


                this.onActionDown(event);
                break;
            case MotionEvent.ACTION_MOVE :
                System.out.println("MotionEvent.ACTION_MOVE");
                this.onActionMove(event);
                break;
            case MotionEvent.ACTION_UP :

                System.out.println("MotionEvent.ACTION_UP");


                this.onActionUp(event);
                break;
            default :
                break;
        }

        System.out.println("this.invalidate()");

        // Re draw
        this.invalidate();

        return true;
    }

    /**
     * This method is getter for mode.
     *
     * @return
     */
    public Mode getMode() {
        return this.mode;
    }

    /**
     * This method is setter for mode.
     *
     * @param mode
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * This method is getter for drawer.
     *
     * @return
     */
    public Drawer getDrawer() {
        return this.drawer;
    }

    /**
     * This method is setter for drawer.
     *
     * @param drawer
     */
    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    /**
     * This method draws canvas again for Undo.
     *
     * @return If Undo is enabled, this is returned as true. Otherwise, this is returned as false.
     */
    public boolean undo() {
        if (this.historyPointer > 1) {
            this.historyPointer--;
            this.invalidate();

            return true;
        } else {
            return false;
        }
    }

    /**
     * This method draws canvas again for Redo.
     *
     * @return If Redo is enabled, this is returned as true. Otherwise, this is returned as false.
     */
    public boolean redo() {
        if (this.historyPointer < this.pathLists.size()) {
            this.historyPointer++;
            this.invalidate();

            return true;
        } else {
            return false;
        }
    }

    /**
     * This method initializes canvas.
     *
     * @return
     */
    public void clear() {
        Path path = new Path();
        path.moveTo(0F, 0F);
        path.addRect(0F, 0F, 1000F, 1000F, Path.Direction.CCW);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        if (this.historyPointer == this.pathLists.size()) {
            this.pathLists.add(path);
            this.paintLists.add(paint);
            this.historyPointer++;
        } else {
            // On the way of Undo or Redo
            this.pathLists.set(this.historyPointer, path);
            this.paintLists.set(this.historyPointer, paint);
            this.historyPointer++;

            for (int i = this.historyPointer, size = this.paintLists.size(); i < size; i++) {
                this.pathLists.remove(this.historyPointer);
                this.paintLists.remove(this.historyPointer);
            }
        }

        this.text = "";

        // Clear
        this.invalidate();
    }

    /**
     * This method is getter for canvas background color
     *
     * @return
     */
    public int getBaseColor() {
        return this.baseColor;
    }

    /**
     * This method is setter for canvas background color
     *
     * @param color
     */
    public void setBaseColor(int color) {
        this.baseColor = color;
    }

    /**
     * This method is getter for drawn text.
     *
     * @return
     */
    public String getText() {
        return this.text;
    }

    /**
     * This method is setter for drawn text.
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * This method is getter for stroke or fill.
     *
     * @return
     */
    public Paint.Style getPaintStyle() {
        return this.paintStyle;
    }

    /**
     * This method is setter for stroke or fill.
     *
     * @param style
     */
    public void setPaintStyle(Paint.Style style) {
        this.paintStyle = style;
    }

    /**
     * This method is getter for stroke color.
     *
     * @return
     */
    public int getPaintStrokeColor() {
        return this.paintStrokeColor;
    }

    /**
     * This method is setter for stroke color.
     *
     * @param color
     */
    public void setPaintStrokeColor(int color) {
        this.paintStrokeColor = color;
    }

    /**
     * This method is getter for fill color.
     * But, current Android API cannot set fill color (?).
     *
     * @return
     */
    public int getPaintFillColor() {
        return this.paintFillColor;
    };

    /**
     * This method is setter for fill color.
     * But, current Android API cannot set fill color (?).
     *
     * @param color
     */
    public void setPaintFillColor(int color) {
        this.paintFillColor = color;
    }

    /**
     * This method is getter for stroke width.
     *
     * @return
     */
    public float getPaintStrokeWidth() {
        return this.paintStrokeWidth;
    }

    /**
     * This method is setter for stroke width.
     *
     * @param width
     */
    public void setPaintStrokeWidth(float width) {
        if (width >= 0) {
            this.paintStrokeWidth = width;
        } else {
            this.paintStrokeWidth = 3F;
        }
    }

    /**
     * This method is getter for alpha.
     *
     * @return
     */
    public int getOpacity() {
        return this.opacity;
    }

    /**
     * This method is setter for alpha.
     * The 1st argument must be between 0 and 255.
     *
     * @param opacity
     */
    public void setOpacity(int opacity) {
        if ((opacity >= 0) && (opacity <= 255)) {
            this.opacity = opacity;
        } else {
            this.opacity= 255;
        }
    }

    /**
     * This method is getter for amount of blur.
     *
     * @return
     */
    public float getBlur() {
        return this.blur;
    }

    /**
     * This method is setter for amount of blur.
     * The 1st argument is greater than or equal to 0.0.
     *
     * @param blur
     */
    public void setBlur(float blur) {
        if (blur >= 0) {
            this.blur = blur;
        } else {
            this.blur = 0F;
        }
    }

    /**
     * This method is getter for line cap.
     *
     * @return
     */
    public Paint.Cap getLineCap() {
        return this.lineCap;
    }

    /**
     * This method is setter for line cap.
     *
     * @param cap
     */
    public void setLineCap(Paint.Cap cap) {
        this.lineCap = cap;
    }

    /**
     * This method is getter for font size,
     *
     * @return
     */
    public float getFontSize() {
        return this.fontSize;
    }

    /**
     * This method is setter for font size.
     * The 1st argument is greater than or equal to 0.0.
     *
     * @param size
     */
    public void setFontSize(float size) {
        if (size >= 0F) {
            this.fontSize = size;
        } else {
            this.fontSize = 32F;
        }
    }

    /**
     * This method is getter for font-family.
     *
     * @return
     */
    public Typeface getFontFamily() {
        return this.fontFamily;
    }

    /**
     * This method is setter for font-family.
     *
     * @param face
     */
    public void setFontFamily(Typeface face) {
        this.fontFamily = face;
    }

    /**
     * This method gets current canvas as bitmap.
     *
     * @return This is returned as bitmap.
     */
    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(false);
        this.setDrawingCacheEnabled(true);

        return Bitmap.createBitmap(this.getDrawingCache());
    }

    /**
     * This method gets current canvas as scaled bitmap.
     *
     * @return This is returned as scaled bitmap.
     */
    public Bitmap getScaleBitmap(int w, int h) {
        this.setDrawingCacheEnabled(false);
        this.setDrawingCacheEnabled(true);

        return Bitmap.createScaledBitmap(this.getDrawingCache(), w, h, true);
    }

    /**
     * This method draws the designated bitmap to canvas.
     *
     * @param bitmap
     */
    public void drawBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.invalidate();
    }

    /**
     * This method draws the designated byte array of bitmap to canvas.
     *
     * @param byteArray This is returned as byte array of bitmap.
     */
    public void drawBitmap(byte[] byteArray) {
        this.drawBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
    }

    /**
     * This static method gets the designated bitmap as byte array.
     *
     * @param bitmap
     * @param format
     * @param quality
     * @return This is returned as byte array of bitmap.
     */
    public static byte[] getBitmapAsByteArray(Bitmap bitmap, CompressFormat format, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * This method gets the bitmap as byte array.
     *
     * @param format
     * @param quality
     * @return This is returned as byte array of bitmap.
     */
    public byte[] getBitmapAsByteArray(CompressFormat format, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.getBitmap().compress(format, quality, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * This method gets the bitmap as byte array.
     * Bitmap format is PNG, and quality is 100.
     *
     * @return This is returned as byte array of bitmap.
     */
    public byte[] getBitmapAsByteArray() {
        return this.getBitmapAsByteArray(CompressFormat.PNG, 100);
    }

}
