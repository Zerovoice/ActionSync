/* 
 * Copyright (C) 2011 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY HISENSE ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH HISENSE ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.action.fragments;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.sharesdk.framework.FakeActivity;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareCore;

import com.zeroapp.action.R;
import com.zeroapp.action.activity.MainActivity;
import com.zeroapp.action.database.DBUtils;
import com.zeroapp.action.models.CategoryInfo;
import com.zeroapp.action.models.ShareDataBuilder;
import com.zeroapp.action.models.ZeroAppApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

/**
 * 
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-29.
 * @version $Id$
 */

public class ShareFragment extends Fragment implements PlatformActionListener, OnClickListener,
        TextWatcher, Callback {

    private static final String TAG = "ShareFragment";

    private MainActivity mainActivity;
    private CategoryInfo categoryInfo;
    private View mainView;
    private EditText shareContent;
    private ImageView photo2Share;
    private ImageButton camera;
    private ImageButton photos;
    private ImageButton write;
    private ImageView cancel;
    private ImageView share;
    private String PATH = Environment.getExternalStorageDirectory() + "/DCIM";
    private String name;
    private String sharePath;
    private HashMap<String, Object> reqData;

    private static final int MAX_TEXT_COUNT = 140;
    private static final int MSG_PLATFORM_LIST_GOT = 1;
    private static final int MSG_AFTER_IMAGE_DOWNLOAD = 2;
    private OnekeyShare parent;
    private LinearLayout llPage;
    private TitleLayout llTitle;
    // 文本编辑框
    private EditText etContent;
    // 字数计算器
    private TextView tvCounter;
    // 别针图片
    private ImageView ivPin;
    // 输入区域的图片
    private ImageView ivImage;
    private Bitmap image;
    private boolean shareImage;
    private LinearLayout llPlat;
    private LinearLayout llAt;
    // 平台列表
    private Platform[] platformList;
    private View[] views;
    // 设置显示模式为Dialog模式
    private boolean dialogMode;

    private FakeActivity fakeActivity;


    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "onAttach");
        super.onAttach(activity);
        mainActivity = (MainActivity) getActivity();
        categoryInfo = mainActivity.getFocusCategory();
        reqData = new HashMap<String, Object>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainView = inflater.inflate(R.layout.fragment_share, null);
        shareContent = (EditText) mainView.findViewById(R.id.main_content_et);
        photo2Share = (ImageView) mainView.findViewById(R.id.photo_to_share);
        camera = (ImageButton) mainView.findViewById(R.id.btn_one);
        camera.setOnClickListener(this);
        photos = (ImageButton) mainView.findViewById(R.id.btn_two);
        photos.setOnClickListener(this);
        write = (ImageButton) mainView.findViewById(R.id.btn_three);
        write.setOnClickListener(this);
        cancel = (ImageView) mainView.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);
        share = (ImageView) mainView.findViewById(R.id.btn_share);
        share.setOnClickListener(this);


        return mainView;
    }


    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onError(Platform platform, int arg1, Throwable t) {
        Log.i(TAG, "updateData--->onError:Platform-" + platform.getName());
        t.printStackTrace();

    }

    @Override
    public void onComplete(Platform platform, int arg1, HashMap<String, Object> arg2) {
        Log.i(TAG, "updateData--->onComplete:platform-" + platform);

    }

    @Override
    public void onCancel(Platform arg0, int arg1) {
        Log.i(TAG, "updateData--->onCancel");

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param arg0
     * @return
     */
    @Override
    public boolean handleMessage(Message arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param arg0
     */
    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_one:
                Log.i(TAG, "onClick:" + "btn_one");
                // 指定开启系统相机的Action
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                new DateFormat();
                name = DateFormat.format("yyyyMMdd_hhmmss",
                        Calendar.getInstance(Locale.CHINA))
                        + ".jpg";
                Uri imageUri = Uri.fromFile(new File(PATH, name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_two:
                Log.i(TAG, "onClick:" + "btn_two");
//                intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
                intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
                startActivityForResult(intent, 1);

                break;
            case R.id.btn_three:
                Log.i(TAG, "onClick:" + "btn_three");

                break;
            case R.id.btn_cancel:
                Log.i(TAG, "onClick:" + "btn_cancel");
                back2guide();
                break;
            case R.id.btn_share:
                Log.i(TAG, "onClick" + "btn_share");
                buildDataAndShare();
                back2guide();

                break;

            default:
                break;
        }

    }

    /**
     * <p>
     * Title: go back.
     * </p>
     * <p>
     * Description: go back to guide fragment.
     * </p>
     * 
     */
    private void back2guide() {
        FragmentTransaction t = mainActivity.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fl_container, new GuideFragment()).commit();

    }

    /**
     * <p>
     * Title: build share data and share it.
     * </p>
     * <p>
     * Description: new a thread to build share data then share it on all
     * authorized platforms.
     * </p>
     * 
     */
    private void buildDataAndShare() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ShareDataBuilder shareDataBuilder = new ShareDataBuilder();
                shareDataBuilder.setNotification(R.drawable.ic_launcher,
                        mainActivity.getString(R.string.app_name));
                shareDataBuilder.setAddress("12345678901");
                shareDataBuilder.setTitle(null);
                shareDataBuilder.setTitleUrl("http://weibo.com/u/5094864897");
                shareDataBuilder.setText(shareContent.getText().toString());
                shareDataBuilder.setImagePath(sharePath);
                shareDataBuilder.setImageUrl("");
                shareDataBuilder.setUrl("http://weibo.com/u/5094864897");
                shareDataBuilder.setFilePath(sharePath);
                shareDataBuilder.setComment(mainActivity.getString(R.string.app_name));
                shareDataBuilder.setSite(mainActivity.getString(R.string.app_name));
                shareDataBuilder.setSiteUrl("http://weibo.com/u/5094864897");
                shareDataBuilder.setVenueName("Action");
                shareDataBuilder.setVenueDescription("This is a beautiful place!");
                shareDataBuilder.setLatitude(23.056081f);
                shareDataBuilder.setLongitude(113.385708f);
//                if (platform != null) {
//                    shareDataBuilder.setPlatform(platform);
//                }
                reqData = shareDataBuilder.getMap();
                // 循环分享
                doShare(getSharePlatforms());
            }
        }).start();
    }

    private HashMap<Platform, HashMap<String, Object>> getSharePlatforms() {
        HashMap<Platform, HashMap<String, Object>> editRes = new HashMap<Platform, HashMap<String, Object>>();
        List<CategoryInfo> data = ZeroAppApplication.mDatas;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isLogin()) {
                Platform p = ShareSDK.getPlatform(mainActivity,
                        DBUtils.categoryManager.get(data.get(i).getType()));
                editRes.put(p, reqData);
            }
        }
        Log.i(TAG, "getSharePlatforms-->count:" + editRes.size());
        return editRes;

    }

    private void doShare(HashMap<Platform, HashMap<String, Object>> shareData) {
        for (Entry<Platform, HashMap<String, Object>> ent : shareData.entrySet()) {
            Platform plat = ent.getKey();
//            plat.SSOSetting(disableSSO);
            String name = plat.getName();
//            boolean isWechat = "WechatMoments".equals(name) || "Wechat".equals(name)
//                    || "WechatFavorite".equals(name);
//            if (isWechat && !plat.isValid()) {
//                Message msg = new Message();
//                msg.what = MSG_TOAST;
//                int resId = getStringRes(getContext(), "wechat_client_inavailable");
//                msg.obj = activity.getString(resId);
//                UIHandler.sendMessage(msg, this);
//                continue;
//            }

//            boolean isGooglePlus = "GooglePlus".equals(name);
//            if (isGooglePlus && !plat.isValid()) {
//                Message msg = new Message();
//                msg.what = MSG_TOAST;
//                int resId = getStringRes(getContext(), "google_plus_client_inavailable");
//                msg.obj = activity.getString(resId);
//                UIHandler.sendMessage(msg, this);
//                continue;
//            }

//            boolean isQQ = "QQ".equals(name);
//            if (isQQ && !plat.isValid()) {
//                Message msg = new Message();
//                msg.what = MSG_TOAST;
//                int resId = getStringRes(getContext(), "qq_client_inavailable");
//                msg.obj = activity.getString(resId);
//                UIHandler.sendMessage(msg, this);
//                continue;
//            }

//            boolean isPinterest = "Pinterest".equals(name);
//            if (isPinterest && !plat.isValid()) {
//                Message msg = new Message();
//                msg.what = MSG_TOAST;
//                int resId = getStringRes(getContext(), "pinterest_client_inavailable");
//                msg.obj = activity.getString(resId);
//                UIHandler.sendMessage(msg, this);
//                continue;
//            }

//            if ("Instagram".equals(name)) {
//                Intent test = new Intent(Intent.ACTION_SEND);
//                test.setPackage("com.instagram.android");
//                test.setType("image/*");
//                ResolveInfo ri = activity.getPackageManager().resolveActivity(test, 0);
//                if ( ri == null) {
//                    Message msg = new Message();
//                    msg.what = MSG_TOAST;
//                    int resId = getStringRes(getContext(), "instagram_client_inavailable");
//                    msg.obj = activity.getString(resId);
//                    UIHandler.sendMessage(msg, this);
//                    continue;
//                }
//            }

//            boolean isYixin = "YixinMoments".equals(name) || "Yixin".equals(name);
//            if (isYixin && !plat.isValid()) {
//                Message msg = new Message();
//                msg.what = MSG_TOAST;
//                int resId = getStringRes(getContext(), "yixin_client_inavailable");
//                msg.obj = activity.getString(resId);
//                UIHandler.sendMessage(msg, this);
//                continue;
//            }

            HashMap<String, Object> data = ent.getValue();
            int shareType = Platform.SHARE_TEXT;
            String imagePath = String.valueOf(data.get("imagePath"));
            if (imagePath != null && (new File(imagePath)).exists()) {
                shareType = Platform.SHARE_IMAGE;
                if (imagePath.endsWith(".gif")) {
                    shareType = Platform.SHARE_EMOJI;
                } else if (data.containsKey("url")
                        && !TextUtils.isEmpty(data.get("url").toString())) {
                    shareType = Platform.SHARE_WEBPAGE;
                }
            } else {
                Object imageUrl = data.get("imageUrl");
                if (imageUrl != null && !TextUtils.isEmpty(String.valueOf(imageUrl))) {
                    shareType = Platform.SHARE_IMAGE;
                    if (String.valueOf(imageUrl).endsWith(".gif")) {
                        shareType = Platform.SHARE_EMOJI;
                    } else if (data.containsKey("url")
                            && !TextUtils.isEmpty(data.get("url").toString())) {
                        shareType = Platform.SHARE_WEBPAGE;
                    }
                }
            }
            data.put("shareType", shareType);

//            if (!started) {
//                started = true;
//                if (equals(callback)) {
//                    int resId = getStringRes(getContext(), "sharing");
//                    if (resId > 0) {
//                        showNotification(2000, getContext().getString(resId));
//                    }
//                }
//            }
            plat.setPlatformActionListener(this);
            ShareCore shareCore = new ShareCore();
//            shareCore.setShareContentCustomizeCallback(customizeCallback);
            shareCore.share(plat, data);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "resultCode=" + resultCode);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Log.i(TAG, "photo path:" + PATH + "/" + name);
            Bitmap photo = BitmapFactory.decodeFile(PATH + "/" + name);
            photo2Share.setImageBitmap(photo);
            sharePath = PATH + "/" + name;
        } else if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // 使用ContentProvider通过Uri获取原始图片
            try {
                Bitmap photo = MediaStore.Images.Media.getBitmap(mainActivity.getContentResolver(),
                        data.getData());
                photo2Share.setImageBitmap(photo);
                sharePath = getRealPathFromURI(data.getData());
                Log.i(TAG, "photo URI:" + data.getData());
                Log.i(TAG, "photo path:" + sharePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * <p>
     * Title: get absolute path of an URI.
     * </p>
     * <p>
     * Description: get absolute path of an URI .
     * </p>
     * 
     * @param contentUri
     * @return
     */
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(mainActivity, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
