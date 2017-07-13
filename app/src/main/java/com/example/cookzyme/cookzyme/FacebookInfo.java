package com.example.cookzyme.cookzyme;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
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
import android.util.Base64;
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
import com.facebook.HttpMethod;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import org.json.JSONArray;
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
    private String profilepic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_info);
        try {
            mClient = new MobileServiceClient(
                    "https://cookzymeapp.azurewebsites.net",
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

        Button logout = (Button) this.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent in = new Intent(FacebookInfo.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });

        Button friendlist = (Button) this.findViewById(R.id.friendlist);
        friendlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getFriendList();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setFacebookData(final LoginResult loginResult)
    {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
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
                          //  Log.i("Link",link);
                            if (Profile.getCurrentProfile()!=null)
                            {
                                profilepic = "" + Profile.getCurrentProfile().getProfilePictureUri(500, 500);
                                Log.i("Login", "" + Profile.getCurrentProfile().getProfilePictureUri(500, 500));
                            }
                            Log.i("Login" + "Email", email);
                            Log.i("Login"+ "FirstName", firstName);
                            Log.i("Login" + "LastName", lastName);
                            Log.i("Login" + "Gender", gender);
                            mClient.getTable(Users.class).insert(new Users(email,null,firstName+" "+lastName,null,profilepic,0,0), new TableOperationCallback<Users>() {
                                public void onCompleted(Users entity, Exception exception, ServiceFilterResponse response) {
                                    if (exception == null) {
                                        // Insert succeeded
                                        System.out.println("eieieieieieieieieieieieieieieieiei");
                                    } else {
                                        // Insert failed
                                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                                        exception.printStackTrace();
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



    private void myNewGraphReq(String friendlistId) {
        final String graphPath = "/"+friendlistId+"/members/";
        AccessToken token = AccessToken.getCurrentAccessToken();
        GraphRequest request = new GraphRequest(token, graphPath, null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                JSONObject object = graphResponse.getJSONObject();
                try {
                    JSONArray arrayOfUsersInFriendList= object.getJSONArray("data");
                /* Do something with the user list */
                /* ex: get first user in list, "name" */
                    JSONObject user = arrayOfUsersInFriendList.getJSONObject(0);
                    String usersName = user.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "name");
        request.setParameters(param);
        request.executeAsync();
    }

    private void getFriendList(){
        System.out.println("22222222222222222222222222222222222222");
        AccessToken token = AccessToken.getCurrentAccessToken();
        System.out.println("11111111111111111111111111111111111111");
        GraphRequest graphRequest = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                try {
                    JSONArray jsonArrayFriends = jsonObject.getJSONObject("user_friends").getJSONArray("data");
                    JSONObject friendlistObject = jsonArrayFriends.getJSONObject(0);
                    String friendListID = friendlistObject.getString("id");
                    myNewGraphReq(friendListID);
                    System.out.println("*************************************************"+friendListID);

                } catch (JSONException e) {
                    System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "friendlist" );
        graphRequest.setParameters(param);
        graphRequest.executeAsync();
    }

}