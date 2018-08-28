package com.example.wang.livedetectionapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.wang.livedetectionapp.adapter.MenuRecyclerViewAdapter;
import com.example.wang.livedetectionapp.common.AppUtil;
import com.example.wang.livedetectionapp.common.BaseActivity;
import com.example.wang.livedetectionapp.common.LogUtil;
import com.tzutalin.dlib.CameraActivity;

import java.util.ArrayList;
import java.util.List;

public class LiveMethodActivity extends BaseActivity {


    private static final int REQUEST_CODE_PERMISSION = 2;

    // 权限请求
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };


    /**
     * 检查应用程序是否有权写入设备存储或打开相机
     * 如果该应用程序没有权限，则会提示用户授予权限
     */
    private static boolean verifyPermissions(Activity activity) {

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(activity, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.
                permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.
                    size()]);
            ActivityCompat.requestPermissions(activity, permissions, 1);
            //ActivityCompat.requestPermissions(activity, PERMISSIONS_REQ, 1);
            return false;
        } else {
            return true;
            //申请权限后进行操作
        }

        // 检测是否有如下权限
        /*int write_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read_persmission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int camera_permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (write_permission != PackageManager.PERMISSION_GRANTED ||
                read_persmission != PackageManager.PERMISSION_GRANTED ||
                camera_permission != PackageManager.PERMISSION_GRANTED) {
            // 没有权限反馈给用户
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_REQ,
                    REQUEST_CODE_PERMISSION
            );
            return false;
        } else {
            return true;
        }*/
    }

    /* 检查外部存储是否可用于读取和写入*/
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* 检查外部存储是否至少可用于读取*/
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LiveMethodActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Just use hugo to print log
        isExternalStorageWritable();
        isExternalStorageReadable();

        // For API 23+ you need to request the read/write permissions even if they are already in your manifest.
        int currentapiVersion = Build.VERSION.SDK_INT;

        if (currentapiVersion >= Build.VERSION_CODES.M) {
            if (verifyPermissions(this)) {
                Intent intent = new Intent(LiveMethodActivity.this, CameraActivity.class);
                startActivityForResult(intent, 11);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11 && resultCode == -1) {
            Toast.makeText(this, "检测到活体", Toast.LENGTH_SHORT).show();
            //IndexUIActivity.startActivity(this);
            AppUtil.finishCurrentActivity();
        }
    }


    /*public boolean hasPermission(String permission) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
    public boolean hasPermission(String permission){
        PackageManager pm = getPackageManager();
        boolean flag = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permission, "packageName"));
        if (flag) {
            //有这个权限，做相应处理
            return true;
        }else {
            //没有权限
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0){
                    //for (int result : grantResults) {
                        //if (result != PackageManager.PERMISSION_GRANTED) {
                            //Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            if ( hasPermission("android.permission.WRITE_EXTERNAL_STORAGE") && hasPermission("android.permission.CAMERA") ){
                                if (MenuRecyclerViewAdapter.LiveMethodTimes == 1) {
                                    MenuRecyclerViewAdapter.LiveMethodTimes++;
                                    AppUtil.finishCurrentActivity();
                                    LiveMethodActivity.startActivity(this);
                                }
                            }else {
                                AppUtil.finishCurrentActivity();
                                Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            }
                            //AppUtil.finishCurrentActivity();
                            //LiveMethodActivity.startActivity(this);
                        //}
                    //}
                }else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}
