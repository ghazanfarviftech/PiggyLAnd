package com.example.ghazanfarali.piggyland.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Debug;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.ghazanfarali.piggyland.R;
import com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.models.ImageModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class GlobalUtils {

	/** Defines **/
	public static final String LANG_AR = "ar";
	public static final String LANG_EN = "en";
	public static final String APP_LANG_EN = "en_US";
	/***********/

	public static int dpToPx(Context c,int dp) {
		DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
		int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}
	
	public static boolean isTablet(Activity myacActivity) {
		int screenSize = myacActivity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

		switch (screenSize) {
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			return true;

		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			return false;

		default:
			return true;
		}
	}

	public static boolean checkPlayServices(Context context) {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(context);

		if (resultCode != ConnectionResult.SUCCESS) {
			return false;
		}
		return true;

	}
	
	public static SharedPreferences getGCMPreferences(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				Defines.REGISTRATION_KEY, Context.MODE_PRIVATE);
		return prefs;
	}
	
	
	public static boolean isConnected(Object context, boolean showDialogBox) {
		boolean HaveConnectedWifi = false;
		boolean HaveConnectedMobile = false;
		Context localContextObject = null;
		Fragment localFragment;
		if(context instanceof Fragment) {
			localFragment = (Fragment) context;
			localContextObject = localFragment.getActivity();
		}else if(context instanceof Activity){
			localContextObject = (Context) context;
		} else if( context instanceof Context){
			localContextObject = (Context) context;
		}

		ConnectivityManager cm = (ConnectivityManager) localContextObject.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					HaveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					HaveConnectedMobile = true;
		}
		if(!HaveConnectedMobile && !HaveConnectedWifi && showDialogBox){
			showErrorDialog(localContextObject, localContextObject.getString(R.string.internetconnectionerror), localContextObject.getString(R.string.no_network_access));
		}
		return HaveConnectedWifi || HaveConnectedMobile;
	}

	public static void showErrorDialog(Context context, String message, String title){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title)
				.setMessage(message)
				.setCancelable(false)
				.setNegativeButton(
						context.getResources().getString(R.string.lbl_ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		builder.setView(null);

		AlertDialog alert = builder.create();
		alert.show();
	}
	public static void changeAppLanguage(Context context, String lang) {
		Resources res = context.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = new Locale(lang);
		res.updateConfiguration(conf, dm);
	}

	public static void restartActivity(Activity myActivity, int animIn,	int animOut, int carousel_position) {
		Intent intent = myActivity.getIntent();
		intent.putExtra("carousel_position", carousel_position);
		myActivity.finish();
		myActivity.startActivity(intent);
		myActivity.overridePendingTransition(animIn, animOut);
	}

	public static boolean isTelephone(Context context) {
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = mTelephonyMgr.getDeviceId();
		if (deviceId == null) {
			return false;
		}
		return true;
	}

	public static void setPreferenceKey(Context context, String prefName, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(prefName, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getPrefenceKey(Context context, String prefName, String key) {
		SharedPreferences settings = context.getSharedPreferences(prefName, 0);
		return settings.getString(key, "");
	}

	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	
	public static ImageModel getAttachedImage(Intent data, Context context){
		
//		Map<String, ImageModel> imageObject = new HashMap<String, ImageModel>();
		
		Uri selectedImage       = data.getData();	
		String[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor           = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
		cursor.moveToFirst();
		
		int columnIndex         = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath      = cursor.getString(columnIndex);
		cursor.close();	
		
		/** get image name **/
		String imageName    = Uri.parse(picturePath).getLastPathSegment().toString();
		
		/** get file content-type or mime-type **/
		ContentResolver contentResolver = context.getContentResolver();
		MimeTypeMap mime                = MimeTypeMap.getSingleton();
		String imageType                = contentResolver.getType(selectedImage);
		
		/** convert to Bitmap **/
//		Bitmap pic           = BitmapFactory.decodeFile(picturePath);543725 - 46500 167076
		Bitmap pic = null;
		try {
			pic = decodeUri(context, selectedImage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/** scale the image to 50*50 **/
//		Bitmap resizedBitmap = Bitmap.createScaledBitmap(pic, 50, 50, false);
		
		
		/** convert Bitmap to string **/
//		String imageString = GlobalUtils.getStringFromBitmap(pic);
		
		/** convert Bitmap to byte **/
        byte[] b           = GlobalUtils.getByteFromBitmap(pic);
        
        System.out.print("length = "+b.length);
        
        ImageModel model = new ImageModel(imageName, picturePath, imageType, b);
        return model;
	}     
		
	public static ImageModel getImageDescription(Intent data, Context context){
		
		Uri selectedImage       = data.getData();	
		String[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor           = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null);
		cursor.moveToFirst();
		
		int columnIndex         = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath      = cursor.getString(columnIndex);
		cursor.close();	
		
		/** get image name **/
		String imageName    = Uri.parse(picturePath).getLastPathSegment().toString();
		
		/** get file content-type or mime-type **/
		ContentResolver contentResolver = context.getContentResolver();
		MimeTypeMap mime                = MimeTypeMap.getSingleton();
		String imageType                = contentResolver.getType(selectedImage);
		
        ImageModel model = new ImageModel(imageName, picturePath, imageType, selectedImage.toString());
        return model;
	}     

	public static byte[] getAttachedImageByte(Uri selectedImage, Context context){
		
		/** convert to Bitmap **/
		Bitmap pic = null;
		try {
			pic = decodeUri(context, selectedImage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/** convert Bitmap to byte **/
        byte[] b           = GlobalUtils.getByteFromBitmap(pic);
        System.out.println("Utils " + b);
        return b;
	}   
	
	private static String getStringFromBitmap(Bitmap bitmapPicture) {
		 final int COMPRESSION_QUALITY = 100;
		 String encodedImage;
		 ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		 bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY, byteArrayBitmapStream);
		 byte[] b = byteArrayBitmapStream.toByteArray();
		 encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
		 return encodedImage;
	}
	
	private static byte[] getByteFromBitmap(Bitmap bitmapPicture){
		 final int COMPRESSION_QUALITY = 100;
		 ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		 bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,byteArrayBitmapStream);
		 byte[] b = byteArrayBitmapStream.toByteArray();
		 return b;
	}
		
	private static String getHashkey(Context context){
		String hashKey = null;
		try {
	    PackageInfo info = context.getPackageManager().getPackageInfo(
	            "com.tawasol.Sharka", //your unique package name here
	            PackageManager.GET_SIGNATURES);
	    for (Signature signature : info.signatures) {
	        MessageDigest md = MessageDigest.getInstance("SHA");
	        md.update(signature.toByteArray());
	        hashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT);
	        Log.d("KeyHash:",hashKey);// this line  gives your keyhash
	        }
	} catch (NameNotFoundException e) {

	} catch (NoSuchAlgorithmException e) {

	}
		return hashKey;
	}
	
	public static String parseLanguage(String language){
		if (language.contains("en"))
			return "en";
		else
			return language;
	}
	
	/**
     * Tests if a string is numeric, i.e. contains only digit characters
     * @param string string to test
     * @return true if only digit chars, false if empty or null or contains non-digit chrs
     */
    public static boolean isNumeric(String string) {
        if (string == null || string.length() == 0)
            return false;

        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(string.codePointAt(i)))
                return false;
        }
        return true;
    }
    
    private static Bitmap decodeUri(Context context, Uri selectedImage) throws FileNotFoundException {
    
    	getDeviceMemory();
    	
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, option);

        int width_tmp  = option.outWidth;
        int height_tmp = option.outHeight;
        
        long bitmapSize  = width_tmp * height_tmp;
        
        System.out.println("bitmapSize "  + formatMemoeryText(bitmapSize));
        
        if(bitmapSize < (Runtime.getRuntime().maxMemory() / 16) && bitmapSize < Runtime.getRuntime().freeMemory()){
        	
        	System.out.println("Size < 16");
        	Bitmap pic = null;
			try {
				pic = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectedImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return pic;
        }else{
        	System.out.println("Size > ........");
        	final int REQUIRED_SIZE = 100;
        	int scale = 1;
        	while (true) {
        		if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
        			break;
        		}
        		width_tmp  /= 2;
        		height_tmp /= 2;
        		scale *= 2;
        	}

        	BitmapFactory.Options option2 = new BitmapFactory.Options();
        	option2.inSampleSize = scale;
        	return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, option2);
        }
    }
    
    public static void getDeviceMemory(){
    	
    	Runtime runtime = Runtime.getRuntime();
    	
		long coreSize           = runtime.availableProcessors();
		
		long maxMemory          = runtime.maxMemory();
		long freeSize           = runtime.freeMemory();
		long totalSizeOfVMHeap  = runtime.totalMemory();
		
		long vmAlloc     = totalSizeOfVMHeap - freeSize;
		long nativeAlloc = Debug.getNativeHeapAllocatedSize();
		
		long totalAllocated = vmAlloc + nativeAlloc;
		
		System.out.println("coreSize "          + coreSize);
		System.out.println("freeSize "          + formatMemoeryText(freeSize));
		System.out.println("totalSizeOfVMHeap " + formatMemoeryText(totalSizeOfVMHeap));
		System.out.println("maxMemory "         + formatMemoeryText(maxMemory));
		System.out.println("vmAlloc "           + formatMemoeryText(vmAlloc));
		System.out.println("nativeAlloc "       + formatMemoeryText(nativeAlloc));
		System.out.println("totalAllocated "    + formatMemoeryText(totalAllocated));
    }
    
    public static Bitmap rotateImage(Bitmap src) {
       
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }
    
    private static String formatMemoeryText(long memory) {
		float memoryInMB = memory * 1f / 1024 / 1024;
		return String.format("%.1f MB", memoryInMB);
	}

	private void showErrorToast(Context context, OutOfMemoryError error) {
		String message = error.getMessage();
		if(message==null||message.equals("null")){
			message = "";
		}
		Toast.makeText(context, "OutOfMemoryError:" + message, Toast.LENGTH_LONG).show();
	}

	/**
	 * returns the bytesize of the give bitmap
	 */
	public static int byteSizeOf(Bitmap bitmap) {
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
	        return bitmap.getByteCount();
	    } else {
	        return bitmap.getRowBytes() * bitmap.getHeight();
	    }
	}
	
	/**
	 * Hide soft keyboard 
	 */
	public static void hideSoftKeyboard(Activity activity, View view) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
