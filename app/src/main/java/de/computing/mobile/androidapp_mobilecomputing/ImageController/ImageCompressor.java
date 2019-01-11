package de.computing.mobile.androidapp_mobilecomputing.ImageController;

import android.graphics.Bitmap;
import android.util.Log;

public class ImageCompressor {

    public String changeToImageString(Bitmap bitmap){
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 16, 16, true);
        int[] bitmapArray = createBlackAndWhite(newBitmap);
        return changeToString(bitmapArray);
    }

    private String changeToString(int[] image){
        String s = "";
        String[] sec = new String[image.length];
        int point = 0;
        for (int i:image) {
            if(i <= -2){
                i = 0;
            } else {
                i = 1;
            }
            s += Integer.toString(i);
            sec[point] = Integer.toString(i);
            point++;
        }
        Log.d("Image", s);
        drawMap(sec);
        return s;
    }

    private void drawMap(String[] s){
        int counter = 0;
        for (String a:s) {
            System.out.print(a);
            if(counter == 15){
                System.out.println();
                counter = 0;
            }
            counter++;
        }
    }

    private static int[] createBlackAndWhite(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();

        final float factor = 255f;
        final float redBri = 0.2126f;
        final float greenBri = 0.2126f;
        final float blueBri = 0.0722f;

        int length = width * height;
        int[] inpixels = new int[length];
        int[] outpixels = new int[length];

        src.getPixels(inpixels, 0, width, 0, 0, width, height);

        float lumenSum = 0;
        for(int pix: inpixels) {
            int R = (pix >> 16) & 0xFF;
            int G = (pix >> 8) & 0xFF;
            int B = pix & 0xFF;

            float lum = (redBri * R / factor) + (greenBri * G / factor) + (blueBri * B / factor);
            lumenSum += lum;
        }
        lumenSum /= length;

        Log.d("LumenSum", Float.toString(lumenSum));

        int point = 0;
        for(int pix: inpixels){
            int R = (pix >> 16) & 0xFF;
            int G = (pix >> 8) & 0xFF;
            int B = pix & 0xFF;

            float lum = (redBri * R / factor) + (greenBri * G / factor) + (blueBri * B / factor);

            if (lum > lumenSum) {
                outpixels[point] = 0xFFFFFFFF;
            }else{
                outpixels[point] = 0xFF000000;
            }
            point++;
        }

        return outpixels;
    }

}
