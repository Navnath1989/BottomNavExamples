package com.ondevice.bottomnavexamples;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VideoRecordingActivity extends AppCompatActivity {

    Button btnRecord;
    //private static final int VIDEO_CAPTURE = 101;
    private static final int VIDEO_CAPTURE = 2607;
    long fileSizeInBytes = 0;
    Uri videoUri;
    VideoView videoView;
    long fileVideo;

    Handler mHandler = new Handler();
    String recVideoPath;
    MediaController mc;
    int file_size;
    File mediaFile;

    MediaRecorder recorder;
    Runnable my_runnable;
    private boolean runningThread = false;

    String abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recording);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        btnRecord = findViewById(R.id.btnRecord);
        videoView = findViewById(R.id.videoView);

        if (checkPermission()) {
        } else {
            requestPermission();
        }

        mc = new MediaController(this);
        mc.setAnchorView(videoView);
        videoView.setMediaController(mc);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
                startActivityForResult(intent, VIDEO_CAPTURE);*/

                mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                //+ "/navnath_video.mp4");
                recVideoPath = Environment.getExternalStorageDirectory().getAbsolutePath();

                File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM), "Camera");

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                videoUri = Uri.fromFile(mediaFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 10485760L);

                //File picFile = new File(getCacheDir(), recVideoPath);
                /*try {
                    File picFile = File.createTempFile("navnath_video", ".mp4", storageDir);
                    file_size = Integer.parseInt(String.valueOf(picFile.length()/1024));
                    System.out.println("This is the file size: " + file_size);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                //file_size = Integer.parseInt(String.valueOf(mediaFile.length()/1024));
                //file_size = Integer.parseInt(String.valueOf(picFile.length()/1024));
                HandlerTime();

                startActivityForResult(intent, VIDEO_CAPTURE);

            }
        });

        //getFileSize(recVideoPath);

    }


    public long getFileSize(String filepath) {
        File file = new File(filepath);
        if (file.exists()) {
            //fileVideo = file.length();
            Log.d("Print Size=", String.valueOf(file.length()));
            return file.length();
        }else {
            return 0;
        }
    }

    public void HandlerTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(5000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                // Write your code here to update the UI.
                                //System.out.println("This is the file size: " + file_size);

                                        /*File mediaFlo = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                                        String fileName = recVideoPath.substring(recVideoPath.lastIndexOf('/') + 1);
                                        File file = new File(mediaFile + "/" + fileName);
                                        System.out.println("file zieeee="+ file.length());*/

                                //file_size = Integer.parseInt(String.valueOf(storageDir.length()/1024));
                                //System.out.println("This is the file size: " + file_size);

                                        /*try {
                                            File picFile = File.createTempFile("navnath_video", ".mp4", storageDir);
                                            file_size = Integer.parseInt(String.valueOf(picFile.length()/1024));
                                            System.out.println("This is the file size: " + file_size);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }*/

                                //file_size = Integer.parseInt(String.valueOf(mediaFile.length()/1024));

                                getFileSize(recVideoPath);

                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                VIDEO_CAPTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(VideoRecordingActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            Uri videoUri = data.getData();
            //videoView.setVideoURI(data.getData());
            //videoView.start();

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                videoView.setVideoURI(data.getData());

                if (data.getData().getScheme().equals("file")) {
                    File file = new File(videoUri.toString());
                    int fileSize = (int) file.length()/1024;
                    BigDecimal fileVideo1 = BigDecimal.valueOf((fileSize / 1024));
                    //Float.parseFloat(String.valueOf(fileSize/1024))
                    System.out.println("This is the file size: " + Long.parseLong(String.valueOf(fileSize/1024))+"MB");
                } else if (data.getData().getScheme().equals("content")) {
                    Cursor returnCursor = this.getContentResolver().query(videoUri, null, null, null, null);
                    assert returnCursor != null;
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    returnCursor.moveToFirst();
                    String fileSize = returnCursor.getString(sizeIndex);
                    int fileSizee = Integer.parseInt(fileSize)/1024;
                    //double fileVideo2 = fileSizee / 1024;
                    BigDecimal fileVideo2 = BigDecimal.valueOf((fileSizee / 1024));
                    System.out.println("This is the file size: " +Long.parseLong(String.valueOf(fileSizee/1024 ))+"MB");
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}