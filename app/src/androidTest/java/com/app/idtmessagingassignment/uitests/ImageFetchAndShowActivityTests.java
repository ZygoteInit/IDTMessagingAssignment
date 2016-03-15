package com.app.idtmessagingassignment.uitests;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.idtmessagingassignment.App.ui.ImageFetchAndShowActivity;
import com.app.idtmessagingassignment.R;

/**
 * Created by mua on 3/15/2016.
 */
public class ImageFetchAndShowActivityTests extends ActivityInstrumentationTestCase2<ImageFetchAndShowActivity> {


    public ImageFetchAndShowActivityTests(Class<ImageFetchAndShowActivity> activityClass) {
        super(activityClass);
    }

    public void testPreconditions(){

        //Arrange
        Activity mActivity = getActivity();
        Button btnFetch =  (Button) mActivity.findViewById(R.id.btnFetch);
        Button btnRefresh =  (Button) mActivity.findViewById(R.id.btnRefresh);
        EditText edtUrl = (EditText) mActivity.findViewById(R.id.edtUrl);
        ImageView imgView = (ImageView) mActivity.findViewById(R.id.imgFetched);

        //Act

        //Assert
        assertNotNull("mTestActivity is null", mActivity);
        assertNotNull("btnFetch is null", btnFetch);
        assertNotNull("btnRefresh is null", btnRefresh);
        assertNotNull("edtUrl is null", edtUrl);
        assertNotNull("imgView is null", imgView);

    }
}
