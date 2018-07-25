package com.example.wang.livedetectionapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int GET_IMAGE = 1;



    private EditText mLoginText;
    private EditText mPasswordText;
    private EditText mNameText;
    private EditText mGenderText;
    private Button mResiterButton;
    private ImageView mRegisterImage;
    public static String imagePath;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        mLoginText = findViewById(R.id.register_login_text);
        mPasswordText = findViewById(R.id.register_password_text);
        mNameText = findViewById(R.id.register_name_text);
        mGenderText = findViewById(R.id.register_gender_text);

        mResiterButton = findViewById(R.id.register_button);
        mResiterButton.setOnClickListener(this);

        mRegisterImage = findViewById(R.id.register_update_image);
        mRegisterImage.setOnClickListener(this);

        /*mResiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginText.getText().length() > 0 && mPasswordText.getText().length() > 0 && mNameText.getText().length() > 0 && mGenderText.getText().length() > 0) {
                    MainActivity.mLogin = mLoginText.getText().toString();
                    MainActivity.mPassword = mPasswordText.getText().toString();
                    finish();
                }
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                if (mLoginText.getText().length() > 0 && mPasswordText.getText().length() > 0 && mNameText.getText().length() > 0 && mGenderText.getText().length() > 0) {
                    MainActivity.mLogin = mLoginText.getText().toString();
                    MainActivity.mPassword = mPasswordText.getText().toString();

                    finish();
                }
                break;
            case R.id.register_update_image:
                if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else {
                    openAlbum();
                }
                break;
            default:
                break;
        }
    }

    //打开相册
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, GET_IMAGE);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    //获取图片路径
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //显示图片
    private void displayImage(String imagePath) {
        if (imagePath != null) {

            //mContactsMsgTable.insertDatabase(textName.getText().toString(), imagePath, MessageList.IMAGE, MessageList.SEND);
            /*writeDatabase(imagePath, MessageList.MessageType.IMAGE, MessageList.MessageStatus.SEND);

            MessageList msg = new MessageList(imagePath, MessageList.MessageType.IMAGE, MessageList.MessageStatus.SEND);
            msgLists.add(msg);
            adapter.notifyItemInserted(msgLists.size() - 1);
            msgRecyclerView.scrollToPosition(msgLists.size() - 1);*/

            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mRegisterImage.setImageBitmap(bitmap);

        } else {
            Toast.makeText(this, "Failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //从相册获得图片
            case GET_IMAGE:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case GET_IMAGE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
