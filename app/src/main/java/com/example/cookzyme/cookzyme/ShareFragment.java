package com.example.cookzyme.cookzyme;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

public class ShareFragment extends Fragment {
    private EditText editText;
    private Button share;
    private Switch mySwitch;
    private static final int CAMERA_REQUEST = 1888;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private Uri imageUri;
    private ImageView imageView;
    private  View rootView;

    public static ShareFragment newInstance() {
        ShareFragment fragment = new ShareFragment();
        return fragment;
    }

    public ShareFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_share, container, false);

        imageView = (ImageView ) rootView.findViewById(R.id.foodPic);
        editText = (EditText) rootView.findViewById(R.id.editText);
//        // change color button
//        share = (Button ) rootView.findViewById(R.id.share);
//        int colorButton1 = getResources().getColor(R.color.com_facebook_blue);
//        GradientDrawable sd1 = (GradientDrawable) share.getBackground();
//        sd1.setColor(colorButton1);
        //listener
//        rootView.findViewById(R.id.share).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//
//            }
//        });
        rootView.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                if (checkPermissionWRITE_EXTERNAL_STORAGE(getActivity())) {
                    imageUri = getActivity().getApplicationContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                }else{
                    System.out.println("******************************************");
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
        });
        mySwitch = (Switch) rootView.findViewById(R.id.switchFacebook);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
//                    Toast.makeText(getActivity(), "Check", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(getActivity(), "Un check", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return rootView;
    }


    public boolean checkPermissionWRITE_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (requestCode == CAMERA_REQUEST)
                    if (resultCode == Activity.RESULT_OK) {
                        try {

                            Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver()
                                    , imageUri);
                            imageView.setImageBitmap(thumbnail);
                            SharePhoto photo = new SharePhoto.Builder()
                                    //.setImageUrl(Uri.parse("https://static.pexels.com/photos/126407/pexels-photo-126407.jpeg"))
                                    .setBitmap(thumbnail)
                                    .build();
                            SharePhotoContent content2 = new SharePhotoContent.Builder()
                                    .addPhoto(photo)
                                    .build();

                            ShareButton shareButton2 = (ShareButton) rootView.findViewById(R.id.fb_share_button2);
                            shareButton2.setShareContent(content2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
        }

    }


}

