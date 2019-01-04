package com.dy.cmls.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author tangji
 * @date 2018/9/3 15:12
 */

public class PhoneCameraUtil {
    private static PhoneCameraUtil INSTANCE;
    private static final int PICK_FROM_CAMERA = 1;//拍照整张图
    private static final int CROP_FROM_CAMERA = 2;//裁剪
    private static final int PICK_FROM_FILE = 3;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private boolean isCrop = false;//图片是否裁剪,默认不裁剪
    private static Uri imageCaptureUri;
    private File imgCaptureFile;

    public static PhoneCameraUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PhoneCameraUtil();
        }
        return INSTANCE;
    }

    public interface OnBackListener {

        void onCamera(String filePath);

        void onPhotoAlbum(String filePath);

        void onError(String message);
    }

    /**
     * 显示选择dialog
     */
    public void showSelectDialog(@NonNull final Activity activity, boolean isCrop, String lookUrl) {
        this.isCrop = isCrop;
        final Dialog dialog = new Dialog(activity, R.style.dialog_bottom);

        @SuppressLint("InflateParams") View dialogView =
            LayoutInflater.from(activity).inflate(R.layout.dialog_update_avatar, null);
        TextView tvCamera;
        TextView tvPhotoAlbum;
        TextView tvLook;
        TextView tvClose;
        View view=dialogView.findViewById(R.id.vYuanTu);
        tvCamera = (TextView) dialogView.findViewById(R.id.tvPaiZhao);
        tvPhotoAlbum = (TextView) dialogView.findViewById(R.id.tvXiangCe);
        tvLook = (TextView) dialogView.findViewById(R.id.tvYuanTu);
        tvClose = (TextView) dialogView.findViewById(R.id.tvClose);

        if (TextUtils.isEmpty(lookUrl)) {
            tvLook.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        } else {
            tvLook.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        }

        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                PermissionUtils.getInstance()
                    .requestPermission(activity, PermissionUtils.TAG_CAMERA, new PermissionUtils.OnRequestPermissionResult() {
                        @Override
                        public void onSucceed() {
                            getImageCamera(activity);
                        }

                        @Override
                        public void onRequestSucceedTag(int tag, String other) {

                        }

                        @Override
                        public void onError(int tag, String error) {

                        }
                    });
            }
        });
        tvPhotoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                PermissionUtils.getInstance()
                    .requestPermission(activity, PermissionUtils.TAG_RW_SDCARD, new PermissionUtils.OnRequestPermissionResult() {
                        @Override
                        public void onSucceed() {
                            openPictureFiles(activity);
                        }

                        @Override
                        public void onRequestSucceedTag(int tag, String other) {

                        }

                        @Override
                        public void onError(int tag, String error) {

                        }
                    });
            }
        });
        tvLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }

    /**
     * 授权回调
     */
    public void onRequestPermissionsResult(@NonNull final Activity activity, int requestCode, String permissions[],
                                           int[] grantResults) {
        PermissionUtils.getInstance()
            .onRequestPermissionsResult(activity, requestCode, permissions, grantResults,
                new PermissionUtils.OnRequestPermissionResult() {
                    @Override
                    public void onSucceed() {

                    }

                    @Override
                    public void onRequestSucceedTag(int tag, String other) {
                        switch (tag) {
                            case PermissionUtils.TAG_CAMERA:
                                getImageCamera(activity);
                                break;
                            case PermissionUtils.TAG_RW_SDCARD:
                                openPictureFiles(activity);
                                break;
                        }
                    }

                    @Override
                    public void onError(int tag, String error) {

                    }
                });
    }

    /**
     * 页面Activity,返回处理，
     */
    public void onActivityResult(@NonNull Activity activity, int requestCode, int resultCode, @NonNull Intent data,
                                 @NonNull final OnBackListener onSelectListener) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_FROM_CAMERA) {
            this.returnZipPicture(activity, data, PICK_FROM_CAMERA, onSelectListener);
        } else if (resultCode == Activity.RESULT_OK && requestCode == CROP_FROM_CAMERA) {
            onSelectListener.onCamera(imgCaptureFile.getAbsolutePath());
            onSelectListener.onPhotoAlbum(imgCaptureFile.getAbsolutePath());
        } else if (resultCode == Activity.RESULT_OK && requestCode == PICK_FROM_FILE) {
            this.returnZipPicture(activity, data, PICK_FROM_FILE, onSelectListener);
        }
    }

    /**
     * 拍照
     */
    public void getImageCamera(@NonNull Activity activity) {
        imageCaptureUri = getOutputMediaFileUri(activity, MEDIA_TYPE_IMAGE);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)) {
            //通过FileProvider创建一个content类型的Uri
            //添加权限,添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        //            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        //将拍取的照片保存到指定URI
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCaptureUri);
        activity.startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    /**
     * Create a file Uri for saving an image or video
     */
    private Uri getOutputMediaFileUri(@NonNull Activity activity, int type) {
        //解决Android 7.0之后的Uri安全问题
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imgCaptureFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/"
                + CMLSConstant.FOLDER
                + "/"
                + System.currentTimeMillis()
                + ".jpg");
            imgCaptureFile.getParentFile().mkdirs();
            //改变Uri  com.xx.xxx.fileProvider注意和xml中的一致
            uri = FileProvider.getUriForFile(activity, CMLSConstant.FILE_PROVIDER, imgCaptureFile);
        } else {
            uri = Uri.fromFile(getOutputMediaFile(activity, type));
        }
        return uri;
    }

    /**
     * 手机相册
     */
    public void openPictureFiles(@NonNull Activity activity) {
        // pick from file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
    }

    /**
     * 原图路径压缩，返回压缩后路径
     *
     * @param data 原图路径
     */
    private void returnZipPicture(@NonNull Activity activity, @NonNull Intent data, int type,
                                  @NonNull OnBackListener onBackListener) {
        String filePathImg = null;
        if (type == PICK_FROM_CAMERA) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LogUtil.e("SDK", "大于等于7.0");
                filePathImg = imgCaptureFile.getAbsolutePath();
                imgCaptureFile = null;
            } else {
                LogUtil.e("SDK", "小于7.0");
                if (data != null) {
                    imageCaptureUri = data.getData();
                }
                if (imageCaptureUri != null) {
                    filePathImg = imageCaptureUri.getPath();
                }
            }
        } else if (type == PICK_FROM_FILE) {
            if (data.getData() != null) {
                filePathImg = getPath(activity, data.getData());
            }
        } else {
            LogUtil.e("type", "错误");
        }

        //获取相册图片路径，
        imageCaptureUri = null;

        if (!isPicture(filePathImg)) {
            onBackListener.onError("请选择png或者jpg格式图片");
            return;
        }
        try {
            if (TextUtils.isEmpty(filePathImg)) {
                onBackListener.onError("图片地址为空");
                return;
            }
            File file = new File(filePathImg);
            LogUtil.e("图片大小:", String.valueOf(file.length()));

            if (file.length() > (1024 * 50)) {
                if (isCrop) {
                    fileSizeSmallCrop(activity, file, data);
                } else {
                    fileSizeSmallLuBan(activity, filePathImg, onBackListener);
                }
            } else {
                LogUtil.e("图片:", "小于50KB");
                onBackListener.onCamera(filePathImg);
                onBackListener.onPhotoAlbum(filePathImg);
            }
        } catch (Exception e) {
            LogUtil.e("图片路径异常:", e.toString());
            onBackListener.onError("图片路径异常:" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 裁切图片
     */
    private void fileSizeSmallCrop(@NonNull Activity activity, File file, Intent data) {
        Uri uriOut;
        Uri uriFile;
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)) {
            //改变Uri  com.xx.xxx.fileProvider注意和xml中的一致
            imgCaptureFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/"
                + CMLSConstant.FOLDER
                + "/"
                + System.currentTimeMillis()
                + ".jpg");
            imgCaptureFile.getParentFile().mkdirs();
            uriOut = Uri.fromFile(imgCaptureFile);
            uriFile = FileProvider.getUriForFile(activity, CMLSConstant.FILE_PROVIDER, file);
        } else {
            imgCaptureFile = getOutputMediaFile(activity, MEDIA_TYPE_IMAGE);
            uriOut = Uri.fromFile(imgCaptureFile);
            if (data != null && data.getData() != null) {
                uriFile = data.getData();
            } else {
                uriFile = Uri.fromFile(file);
            }
        }
        //调用系统照片的裁剪功能，修改编辑头像的选择模式(适配Android7.0)
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uriFile, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriOut);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        activity.startActivityForResult(intent, CROP_FROM_CAMERA);
    }

    /**
     * 压缩图片
     */
    private void fileSizeSmallLuBan(@NonNull Activity activity, final String filePathYt,
                                    @NonNull final OnBackListener onBackListener) {
        Luban.with(activity).load(filePathYt)                                   // 传人要压缩的图片列表
            .ignoreBy(400)                                  // 忽略不压缩图片的大小
            //.setTargetDir(imgpath)                        // 设置压缩后文件存储位置
            .setCompressListener(new OnCompressListener() { //设置回调
                @Override
                public void onStart() {
//                    onBackListener.onError("图片加载中");
                }

                @Override
                public void onSuccess(File file) {
                    //压缩成功后调用，返回压缩后的图片文件
                    onBackListener.onPhotoAlbum(file.getAbsolutePath());
                    onBackListener.onCamera(file.getAbsolutePath());
                }

                @Override
                public void onError(Throwable e) {
                    //当压缩过程出现问题时调用
                    onBackListener.onError("图片压缩失败");
                }
            }).launch();
    }

    private boolean isPicture(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        } else if (filePath.endsWith(".png")) {
            return true;
        } else if (filePath.endsWith(".jpg")) {
            return true;
        } else if (filePath.endsWith(".jpeg")) {
            return true;
        }
        return false;
    }

    private String getDocumentId(Uri uri) {
        String res = null;
        try {
            Class<?> c = Class.forName("android.provider.DocumentsContract");
            Method get = c.getMethod("getDocumentId", Uri.class);
            res = (String) get.invoke(c, uri);
        } catch (Exception ignored) {
            ignored.getMessage();
        }
        return res;
    }

    private String getPath(@NonNull Activity activity, final Uri uri) {
        if (isExternalStorageDocument(uri)) {
            final String docId = getDocumentId(uri);
            final String[] split = docId.split(":");
            final String type = split[0];
            if ("primary".equalsIgnoreCase(type)) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } // MediaProvider  handle non-primary volumes
        else if (isMediaDocument(uri)) {
            final String docId = getDocumentId(uri);
            final String[] split = docId.split(":");
            final String type = split[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            final String selection = "_id=?";
            final String[] selectionArgs = new String[] { split[1] };
            return getDataColumn(activity, contentUri, selection, selectionArgs);
        } else if (isDownloadsDocument(uri)) {

            final String id = getDocumentId(uri);
            final Uri contentUri =
                ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

            return getDataColumn(activity, contentUri, null, null);
        } else if (isDownloadsDocument(uri)) {
            final String id = getDocumentId(uri);
            final Uri contentUri =
                ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

            return getDataColumn(activity, contentUri, null, null);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {

            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(activity, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(activity, uri, null, null);
        } else {
            return getRealPathFromUriMinusKitKat(activity, uri);
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String[] projection = {
            MediaStore.Images.Media.DATA, MediaStore.Images.Media.ORIENTATION
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                return cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private String getRealPathFromUriMinusKitKat(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //路径信息  picturePath;
                return cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public String getFileAddress(int state, String appName, Context mContext) {
        String Address = "";
        if (GetSDState()) {
            Address = Environment.getExternalStorageDirectory().getPath() + "/" + appName + "/";
        } else {
            Address = mContext.getCacheDir().getAbsolutePath() + "/" + appName + "/";
        }
        switch (state) {
            case 1:
                Address = Address + "cache1/";
                break;
            case 2:
                Address = Address + "video/";
                break;
            case 3:
                Address = Address + "voice/";
                break;
            case 4:
                Address = Address + "file/";
                break;
            case 5:
                Address = Address + "photos/";
                break;
            default:
                Address = Address + "cache/";
                break;
        }
        File baseFile = new File(Address);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        return Address;
    }

    // 返回是否有SD卡
    private boolean GetSDState() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * Create a File for saving an image or video
     */
    @SuppressLint("SimpleDateFormat") private File getOutputMediaFile(@NonNull Activity activity, int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaFile = null;
        if (GetSDState()) {// ----判断是否有SD卡
            File mediaStorageDir =
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), CMLSConstant.FOLDER);
            // This location works best if you want the created images to be shared
            // between applications and persist after your app has been uninstalled.
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    // Log.d("MyCameraApp", "failed to create directory");
                    return null;
                }
            }
            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
            }
        } else {
            mediaFile = new File(getFileAddress(type, CMLSConstant.FOLDER, activity),
                CMLSConstant.FOLDER + System.currentTimeMillis() + ".jpg");
        }
        return mediaFile;
    }
}