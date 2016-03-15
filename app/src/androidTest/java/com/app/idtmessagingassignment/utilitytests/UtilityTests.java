package com.app.idtmessagingassignment.utilitytests;

import android.test.AndroidTestCase;

import com.app.idtmessagingassignment.App.Constants;
import com.app.idtmessagingassignment.App.utility.Utility;

/**
 * Created by mua on 3/15/2016.
 */
public class UtilityTests extends AndroidTestCase {


    public void testValidateInputShouldReturnTrue(){

        //Arrange
        String url = Constants.testUrl;

        //Act
        boolean result= Utility.validateUrlInput(url);

        //Assert
        assertTrue("Input is invalid",result);

    }

    public void testIsFileExistsShallReturnFalse(){

        //Arrange
        String filename = "testfile";
        String dirname = "testdir";

        //Act
        boolean result = Utility.isFileExists(dirname,filename);

        //Assert
        assertFalse("Director and file doesnt exist",result);

    }

    public void testIsNetworkAvailableShallReturnTrue(){

        //Arrange
        //Act
        boolean isConnected = Utility.isNetworkAvailable(mContext);

        //Assert
        assertTrue("Netowrk isnt available", isConnected);

    }

}
