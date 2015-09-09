/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plugin.gallery;

import android.os.Environment;
import android.util.Log;
import android.content.Context;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ChooseGalleryImage extends CordovaPlugin {

    public static final String TAG = "ChooseGalleryImage";
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    CallbackContext callbackContext;

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action The action to execute.
     * @param args JSONArry of arguments for the plugin.
     * @param callbackId The callback id used when calling back into JavaScript.
     * @return A PluginResult object with a status and message.
     */
    private Context getApplicationContext() {
        return this.cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.callbackContext = callbackContext;
        this.cordova.getActivity().startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == Activity.RESULT_OK && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                // Get the cursor
//                Cursor cursor = Activity.getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                // Move to first row
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imgDecodableString = cursor.getString(columnIndex);
//
//                cursor.close();
                JSONObject obj = new JSONObject();
                obj.put("path", selectedImage.getPath());
                this.callbackContext.success(obj);
//                ImageView imgView = (ImageView) findViewById(R.id.imgView);
//                // Set the Image in ImageView after decoding the String
//                imgView.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));

            } else {
                this.callbackContext.error("You haven't picked Image");

            }
        } catch (Exception e) {
            this.callbackContext.error("Something went wrong");

        }

    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

    }
}
