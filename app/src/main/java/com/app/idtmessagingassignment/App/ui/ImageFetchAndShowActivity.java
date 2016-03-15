package com.app.idtmessagingassignment.App.ui;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.idtmessagingassignment.App.Constants;
import com.app.idtmessagingassignment.App.services.DownloadImageService;
import com.app.idtmessagingassignment.App.utility.Utility;
import com.app.idtmessagingassignment.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mua on 3/15/2016.
 */
public class ImageFetchAndShowActivity extends Activity {

    @Bind(R.id.edtUrl)
    EditText edtUrl;

    @Bind(R.id.imgFetched)
    ImageView imgFetched;

    String mURL;

    IntentFilter mFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        registerReceiver(downloadReceiver, mFilter);
        mProgressDialog= new ProgressDialog(this);
        mProgressDialog.setMessage(getResources().getString(R.string.fetching_image));
        ButterKnife.bind(this);

    }

    /**
     * Handles the click event of fetch image button.
     * Check if Internet is available or not
     * Check if input is valid or not
     * start the Intent service
     */
    @OnClick(R.id.btnFetch)
    protected void fetchImageAndShow(){


        if(Utility.isNetworkAvailable(getApplicationContext())) {

            if (!(Utility.validateUrlInput(edtUrl.getText().toString())) ) {
                edtUrl.setError(Constants.ERROR_ENTER_URL);
            }

            else
            {
                mURL = edtUrl.getText().toString();
                Intent intent = new Intent(getApplicationContext(), DownloadImageService.class);
                intent.putExtra(Constants.URL_EXTRA, mURL);
                Utility.showHideProgressDialog(mProgressDialog,true);
                startService(intent);
            }

        }
        else {

            Toast.makeText(ImageFetchAndShowActivity.this, Constants.ERROR_NETWORK_NOT_AVAILABLE, Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick(R.id.btnRefresh)
    protected void refreshImage(){
        imgFetched.setImageURI(null);
    }


    /**
     * This broadcast reciever waits for DownloadManagerService to finish downloading
     * gets the file from stored Directory and puts it in ImageView
     */
    public BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Utility.showImage(imgFetched, ImageFetchAndShowActivity.this);
            Utility.showHideProgressDialog(mProgressDialog, false);
        }
    };
}
