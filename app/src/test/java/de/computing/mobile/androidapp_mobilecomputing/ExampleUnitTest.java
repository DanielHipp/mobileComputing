package de.computing.mobile.androidapp_mobilecomputing;

import android.graphics.Bitmap;

import org.junit.Test;

import de.computing.mobile.androidapp_mobilecomputing.ImageController.ImageCompressor;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void testifString(){
        ImageCompressor compressor = new ImageCompressor();

        Bitmap bitmapTest = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);

        assertNotNull(compressor.changeToImageString(bitmapTest));
    }
}