package com.example.cookzyme.cookzyme;

import android.graphics.Bitmap;
import android.util.Base64;

import com.android.internal.http.multipart.MultipartEntity;
import com.android.internal.http.multipart.Part;
import com.squareup.okhttp.MultipartBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * Created by mintra nankongnaeb on 6/24/2017.
 */

public class run2 implements Runnable {
    private volatile String ans2;
    private Bitmap bitmap;
    public run2(Bitmap bitmap){
          this.bitmap = bitmap;
    }

    public byte[] getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        return byteFormat;
    }

    public void run() {
        HttpClient httpclient = HttpClients.createDefault();

        String ans = "";
        ans2="";
        try
        {
            URIBuilder builder = new URIBuilder("https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/e2677f6f-0e6c-437e-96f0-5d3baaf9bc4b/image");

            builder.setParameter("iterationId", "4744e864-1cae-47fc-92cc-1cd9a2af6222");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "multipart/form-data");
            request.setHeader("Prediction-key", "14adf81076e843c3a06f6f325337f291");

            // Request body
            System.out.println(getEncoded64ImageStringFromBitmap(bitmap));

            ByteArrayEntity reqEntity = new ByteArrayEntity( getEncoded64ImageStringFromBitmap(bitmap));
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);

            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                ans = EntityUtils.toString(entity);
               JSONObject obj = new JSONObject(ans);
               JSONArray jobj = obj.getJSONArray("Predictions");
                ans2=((JSONObject)jobj.get(0)).getString("Tag");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public String getAns2() {
        return ans2;
    }
}

