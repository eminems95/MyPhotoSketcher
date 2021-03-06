package com.example.marcin.myphotosketcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends Activity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Pic preview";
    private Uri imageUri;

    Button takePictureButton, savePictureButton;
    ImageView bitmapImageView;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bitmapDrawable = (BitmapDrawable) findViewById(R.id.imageViewDrawable);
        takePictureButton = (Button) findViewById(R.id.takePicture);
        savePictureButton = (Button) findViewById(R.id.saveImage);

        takePictureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 captureImage();
            }
        });

//        savePictureButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                try {
//                    setSavePictureButton(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
    }

    public void setSavePictureButton(Bitmap bitmap) throws IOException {

    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    private void captureImage() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //imageUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        try {
            Bundle extras = data.getExtras();

            Bitmap bitmap = (Bitmap) extras.get("data");

            ImageView bitImageView = (ImageView) findViewById(R.id.imageViewDrawable);
            bitImageView.setImageBitmap(bitmap);

        }catch(Exception e) {
            Toast.makeText(this.getBaseContext(),"Empty photo "+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void previewCapturedImage() {
        try {

            bitmapImageView.setVisibility(View.VISIBLE);

            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath(),
                    options);

        } catch (NullPointerException e) {
            e.printStackTrace();
    }


//    public Uri getOutputMediaFileUri(int type) {
//        return Uri.fromFile(getOutputMediaFile(type));
//    }
//
//    private static File getOutputMediaFile(int type) {
//
//        // External sdcard location
//        File mediaStorageDir = new File(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                IMAGE_DIRECTORY_NAME);
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
//                        + IMAGE_DIRECTORY_NAME + " directory");
//                return null;
//            }
//        }
//
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
//                Locale.getDefault()).format(new Date());
//        File mediaFile;
//        if (type == MEDIA_TYPE_IMAGE) {
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator
//                    + "IMG_" + timeStamp + ".jpg");
//
//        } else {
//            return null;
//        }
//
//        return mediaFile;
//    }
}
}