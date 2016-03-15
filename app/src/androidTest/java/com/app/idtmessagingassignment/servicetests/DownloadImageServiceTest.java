package com.app.idtmessagingassignment.servicetests;

import android.content.Intent;
import android.test.ServiceTestCase;

import com.app.idtmessagingassignment.App.services.DownloadImageService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by mua on 3/15/2016.
 */
public class DownloadImageServiceTest extends ServiceTestCase<DownloadImageService> {
    /**
     * Constructor
     *
     * @param serviceClass The type of the service under test.
     */
    public DownloadImageServiceTest(Class<DownloadImageService> serviceClass) {
        super(serviceClass);
    }

    public static final String LOG_TAG = "RssFetcherServiceTest";
    private CountDownLatch latch;


    @Override
    protected void setupService() {
        super.setupService();

        latch = new CountDownLatch(1);
    }


    public void testHandleIntent() throws InterruptedException {
        try {
            startService(new Intent(getSystemContext(), DownloadImageService.class));
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            fail("Waiting for the worker thread took too long.");
        }
    }
}
