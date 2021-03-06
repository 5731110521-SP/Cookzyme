package com.example.cookzyme.cookzyme;

/**
 * Created by Panuvit on 20/7/2560.
 */

        import android.content.Context;
        import com.microsoft.windowsazure.mobileservices.*;

        import java.net.MalformedURLException;

public class AzureServiceAdapter {
private String mMobileBackendUrl = "https://cookzymeapp.azurewebsites.net";
private Context mContext;
private MobileServiceClient mClient;
private static AzureServiceAdapter mInstance = null;

private AzureServiceAdapter(Context context) throws MalformedURLException {
        mContext = context;
        mClient = new MobileServiceClient(mMobileBackendUrl, mContext);
        }

public static void Initialize(Context context) throws MalformedURLException {
        if (mInstance == null) {
        mInstance = new AzureServiceAdapter(context);
        } else {
        throw new IllegalStateException("AzureServiceAdapter is already initialized");
        }
        }

public static AzureServiceAdapter getInstance() {
        if (mInstance == null) {
        throw new IllegalStateException("AzureServiceAdapter is not initialized");
        }
        return mInstance;
        }

public MobileServiceClient getClient() {
        return mClient;
        }

        // Place any public methods that operate on mClient here.
        }