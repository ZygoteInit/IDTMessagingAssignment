package com.app.idtmessagingassignment.App.utility;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.ImageView;

import com.app.idtmessagingassignment.App.Constants;
import com.app.idtmessagingassignment.R;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mua on 3/15/2016.
 */
public class Utility {


    public static void createDirForImages(String dir) {

        File direct = new File(Environment.getExternalStorageDirectory()
                + "/" +dir);

        if (!direct.exists()) {
            direct.mkdirs();
        }
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static boolean isFileExists(String dir, String fileName) {

        File imgFile = returnFileIfExists(dir, fileName);
        return imgFile.exists();
    }

    private static File returnFileIfExists(String dir, String fileName) {

        File imgFile = new File(Environment.getExternalStorageDirectory()
                + "/" + dir + "/" + fileName);

        return imgFile;
    }

    private static boolean deleteFile(String dir, String fileName) {

        File imgFile = returnFileIfExists(dir, fileName);
        return imgFile.delete();
    }

    public static String getFileName(String dir, String filename) {
        if (isFileExists(dir, filename)) {
            deleteFile(dir, filename);
        }
        return Constants.fileName;
    }

    /**
     * This routines
     * checks if Image is downloaded and exists
     * populates image
     * if image is not downloaded or doesn't exists then puts the placeholder image into image
     * @param imgFetched
     * @param context
     */
    public static void showImage(ImageView imgFetched,Context context) {

        if (isFileExists(Constants.dirName, Constants.fileName)) {
            imgFetched.setImageURI(Uri.fromFile(returnFileIfExists(Constants.dirName, Constants.fileName)));
            imgFetched.setRotation(180);
        }
        else{
            if(android.os.Build.VERSION.SDK_INT >= 21){
                imgFetched.setImageDrawable(context.getResources().getDrawable(R.mipmap.noimagefound,context.getTheme()));

            } else {
                imgFetched.setImageDrawable(context.getResources().getDrawable(R.mipmap.noimagefound));
            }

        }
    }


    /**
     This routine validates the URL for following cases
     1- it shouldn't be empty
     2- it should have a valid url specially for images
     */
    public static boolean validateUrlInput(String url){

        if(!(url.length()>0)){
            return false;
        }

        if ((url.length()>0)) {

            Matcher matcher = Pattern.compile((Constants.pattern)).matcher(url);
            if(!matcher.matches()){
                return false;
            }
        }

        return true;
    }


    public static void showHideProgressDialog(ProgressDialog progressDialog,boolean showProgressDialog){

        if(showProgressDialog){
            progressDialog.show();
        }
        else {
            progressDialog.hide();
        }
    }
}
