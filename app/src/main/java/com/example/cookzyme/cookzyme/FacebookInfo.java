package com.example.cookzyme.cookzyme;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;


public class FacebookInfo extends AppCompatActivity {

    Context MyActivity = this;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private Bitmap image1;
    private TextView textView;
    private Bitmap image;
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private ArrayList<String> tag = new ArrayList<String>();
    public static ArrayList<String> recipeName = new ArrayList<String>();
    String nameFood[] = {"Pork Ball", "ก๋วยเตี๋ยวราดหน้าหมูสับ", "ข้าวต้มหมูทรงเครื่อง", "ข้าวผัดกุ้ง", "ข้าวมันไก่", "ข้าวไก่กระเทียม", "น้ำพริกอ่องอกไก่", "ผัดกะเพราไก่", "ฟักทองผัดไข่", "สุกี้กุ้งสดแห้ง", "แกงจืดไข่", "ไก่ทอดหาดใหญ่", "ไข่เจียว เห็ดเข็มทอง"};
    private Uri imageUri;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private  MobileServiceClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        try {
            mClient = new MobileServiceClient(
                    "https://cookzyme.azurewebsites.net",
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        callbackManager = CallbackManager.Factory.create();

        Button infoface = (Button)this.findViewById(R.id.btnCamera);
        infoface.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                importFbProfilePhoto();
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                LoginManager.getInstance().logInWithReadPermissions(FacebookInfo.this, Arrays.asList("user_friends", "email", "public_profile"));


                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        setFacebookData(loginResult);
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });

        Button checklogin = (Button) this.findViewById(R.id.btnCooking);
        checklogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
                    @Override
                    public void onInitialized() {
                        if(AccessToken.getCurrentAccessToken() == null){
                            System.out.println("not logged in yet");
                        } else {
                            System.out.println("Logged in");
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void importFbProfilePhoto() {
        System.out.println("--------------------------------------------");
        if (AccessToken.getCurrentAccessToken() != null) {
            System.out.println("0000000000000000000000000000");
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject me, GraphResponse response) {
                            System.out.println("1111111111111111111111111111111111111111111111");
                            if (AccessToken.getCurrentAccessToken() != null) {
                                System.out.println("222222222222222222222222222222222222222222");
                                if (me != null) {

                                    String profileImageUrl = ImageRequest.getProfilePictureUri(me.optString("id"), 500, 500).toString();
                                    //Log.i(LOG_TAG, profileImageUrl);
                                    System.out.println("33333333333333333333333333333333333333");
                                    System.out.println(profileImageUrl);

                                }
                            }
                        }
                    });
            GraphRequest.executeBatchAsync(request);
        }
        System.out.println("4444444444444444444444444444");

    }

    private void setFacebookData(final LoginResult loginResult)
    {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.i("Response",response.toString());

                            String email = response.getJSONObject().getString("email");
                            String firstName = response.getJSONObject().getString("first_name");
                            String lastName = response.getJSONObject().getString("last_name");
                            String gender = response.getJSONObject().getString("gender");

                            Profile profile = Profile.getCurrentProfile();
                            String id = profile.getId();
                            String link = profile.getLinkUri().toString();
                            Log.i("Link",link);
                            if (Profile.getCurrentProfile()!=null)
                            {
                                Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                            }

                            Log.i("Login" + "Email", email);
                            Log.i("Login"+ "FirstName", firstName);
                            Log.i("Login" + "LastName", lastName);
                            Log.i("Login" + "Gender", gender);
                            mClient.getTable(Users.class).insert(new Users(email,null,firstName+" "+lastName,link,null), new TableOperationCallback<Users>() {
                                public void onCompleted(Users entity, Exception exception, ServiceFilterResponse response) {
                                    if (exception == null) {
                                        // Insert succeeded
                                    } else {
                                        // Insert failed
                                    }
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }
}