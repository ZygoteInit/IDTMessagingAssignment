package com.app.idtmessagingassignment.App.services;

import android.app.DownloadManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.app.idtmessagingassignment.App.Constants;
import com.app.idtmessagingassignment.App.utility.Utility;

/**
 * Created by mua on 3/15/2016.
 */
public class DownloadImageService extends IntentService {

    private static final String TAG = DownloadImageService.class.getSimpleName();
    DownloadManager mgr;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *  TAG Used to name the worker thread, important only for debugging.
     */
    public DownloadImageService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra(Constants.URL_EXTRA);
        downloadFile(url, this);
    }

    /**
     * This routine start the DownloadManagerService and enqueues the request for @param url
     * sets the destination to DirName and FilName in constants file
     * @param url
     * @param context
     */
    private void downloadFile(String url,Context context) {

        Utility.createDirForImages(Constants.dirName);
        mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setDestinationInExternalPublicDir("/" + Constants.dirName, Utility.getFileName(Constants.dirName,Constants.fileName));
        mgr.enqueue(request);

    }

}
