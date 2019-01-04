package com.dy.cmls.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.dy.cmls.CMLSConstant;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class WechatShareManager {
    private static final int THUMB_SIZE = 150;
    public static final int WECHAT_SHARE_WAY_TEXT = 1;   //文字
    public static final int WECHAT_SHARE_WAY_PICTURE = 2; //图片
    public static final int WECHAT_SHARE_WAY_WEBPAGE = 3;  //链接
    public static final int WECHAT_SHARE_WAY_VIDEO = 4; //视频
    public static final int WECHAT_SHARE_TYPE_TALK = SendMessageToWX.Req.WXSceneSession;  //会话
    public static final int WECHAT_SHARE_TYPE_FRENDS = SendMessageToWX.Req.WXSceneTimeline; //朋友圈

    private static WechatShareManager mInstance;
    private AbstractShareContent mShareContentText, mShareContentPicture, mShareContentWebpag, mShareContentVideo;
    private IWXAPI mWXApi;
    private Context mContext;

    private WechatShareManager(Context context) {
        this.mContext = context;
        //初始化数据
        //初始化微信分享代码
        initWechatShare(context);
    }

    /**
     * 获取WeixinShareManager实例
     * 非线程安全，请在UI线程中操作
     */
    public static WechatShareManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WechatShareManager(context);
        }
        return mInstance;
    }

    private void initWechatShare(Context context) {
        if (mWXApi == null) {
            mWXApi = WXAPIFactory.createWXAPI(context, CMLSConstant.WX_APP_ID, true);
        }
        mWXApi.registerApp(CMLSConstant.WX_APP_ID);
    }

    /**
     * 通过微信分享
     *
     * @param shareType 分享的类型（朋友圈，会话）
     */
    public void shareByWebchat(AbstractShareContent AbstractShareContent, int shareType) {
        switch (AbstractShareContent.getShareWay()) {
            case WECHAT_SHARE_WAY_TEXT:
                shareText(AbstractShareContent, shareType);
                break;
            case WECHAT_SHARE_WAY_PICTURE:
                sharePicture(AbstractShareContent, shareType);
                break;
            case WECHAT_SHARE_WAY_WEBPAGE:
                shareWebPage(AbstractShareContent, shareType);
                break;
            case WECHAT_SHARE_WAY_VIDEO:
                shareVideo(AbstractShareContent, shareType);
                break;
            default:
                break;
        }
    }

    private abstract class AbstractShareContent {
        protected abstract int getShareWay();

        protected abstract String getContent();

        protected abstract String getTitle();

        protected abstract String getURL();

        protected abstract int getPictureResource();
    }

    /**
     * 设置分享文字的内容
     *
     * @author chengcj1
     */
    public class ShareContentText extends AbstractShareContent {
        private String content;

        /**
         * 构造分享文字类
         * 分享的文字内容
         */
        public ShareContentText(String content) {
            this.content = content;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_TEXT;
        }

        @Override
        protected String getContent() {
            return content;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getURL() {
            return null;
        }

        @Override
        protected int getPictureResource() {
            return -1;
        }
    }

    /*
     * 获取文本分享对象
     */
    public AbstractShareContent getShareContentText(String content) {
        mShareContentText = new ShareContentText(content);
        return (ShareContentText) mShareContentText;
    }

    /**
     * 设置分享图片的内容
     *
     * @author chengcj1
     */
    public class ShareContentPicture extends AbstractShareContent {
        private int pictureResource;

        public ShareContentPicture(int pictureResource) {
            this.pictureResource = pictureResource;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_PICTURE;
        }

        @Override
        protected int getPictureResource() {
            return pictureResource;
        }

        @Override
        protected String getContent() {
            return null;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getURL() {
            return null;
        }
    }

    /*
     * 获取图片分享对象
     */
    public AbstractShareContent getShareContentPicture(int pictureResource) {
        mShareContentPicture = new ShareContentPicture(pictureResource);
        return (ShareContentPicture) mShareContentPicture;
    }

    /**
     * 设置分享链接的内容
     *
     * @author chengcj1
     */
    public class ShareContentWebpage extends AbstractShareContent {
        private String title;
        private String content;
        private String url;
        private int pictureResource;

        public ShareContentWebpage(String title, String content, String url, int pictureResource) {
            this.title = title;
            this.content = content;
            this.url = url;
            this.pictureResource = pictureResource;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_WEBPAGE;
        }

        @Override
        protected String getContent() {
            return content;
        }

        @Override
        protected String getTitle() {
            return title;
        }

        @Override
        protected String getURL() {
            return url;
        }

        @Override
        protected int getPictureResource() {
            return pictureResource;
        }
    }

    /*
     * 获取网页分享对象
     */
    public AbstractShareContent getShareContentWebpag(String title, String content, String url,
                                                      int pictureResource) {
        mShareContentWebpag = new ShareContentWebpage(title, content, url, pictureResource);
        return (ShareContentWebpage) mShareContentWebpag;
    }

    /**
     * 设置分享视频的内容
     *
     * @author chengcj1
     */
    public class ShareContentVideo extends AbstractShareContent {
        private String url;

        public ShareContentVideo(String url) {
            this.url = url;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_VIDEO;
        }

        @Override
        protected String getContent() {
            return null;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getURL() {
            return url;
        }

        @Override
        protected int getPictureResource() {
            return -1;
        }
    }

    /*
     * 获取视频分享内容
     */
    public AbstractShareContent getShareContentVideo(String url) {
        mShareContentVideo = new ShareContentVideo(url);
        return (ShareContentVideo) mShareContentVideo;
    }

    /*
     * 分享文字
     */
    private void shareText(AbstractShareContent AbstractShareContent, int shareType) {
        String text = AbstractShareContent.getContent();
        //初始化一个WXTextObject对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        //用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        //构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //transaction字段用于唯一标识一个请求
        req.transaction = buildTransaction("textshare");
        req.message = msg;
        //发送的目标场景， 可以选择发送到会话 WXSceneSession 或者朋友圈 WXSceneTimeline。 默认发送到会话。
        req.scene = shareType;
        mWXApi.sendReq(req);
    }

    /*
     * 分享图片
     */
    private void sharePicture(AbstractShareContent AbstractShareContent, int shareType) {
        Bitmap bitmap =
            BitmapFactory.decodeResource(mContext.getResources(), AbstractShareContent.getPictureResource());
        WXImageObject imgObj = new WXImageObject(bitmap);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBitmap = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
        bitmap.recycle();
        msg.thumbData = bmpToByteArray(thumbBitmap, true);  //设置缩略图

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("imgshareappdata");
        req.message = msg;
        req.scene = shareType;
        mWXApi.sendReq(req);
    }

    /*
     * 分享链接
     */
    private void shareWebPage(AbstractShareContent AbstractShareContent, int shareType) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = AbstractShareContent.getURL();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = AbstractShareContent.getTitle();
        msg.description = AbstractShareContent.getContent();

        Bitmap thumb =
            BitmapFactory.decodeResource(mContext.getResources(), AbstractShareContent.getPictureResource());
        if (thumb == null) {
            Toast.makeText(mContext, "图片不能为空", Toast.LENGTH_SHORT).show();
        } else {
            msg.thumbData = bmpToByteArray(thumb, true);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = shareType;
        mWXApi.sendReq(req);
    }

    /*
     * 分享视频
     */
    private void shareVideo(AbstractShareContent AbstractShareContent, int shareType) {
        /*WXVideoObject video = new WXVideoObject();
        video.videoUrl = AbstractShareContent.getURL();

        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = AbstractShareContent.getTitle();
        msg.description = AbstractShareContent.getContent();
        Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.kf_arrows_left_black);
        //      BitmapFactory.decodeStream(new URL(video.videoUrl).openStream());
        *//**
         * 测试过程中会出现这种情况，会有个别手机会出现调不起微信客户端的情况。造成这种情况的原因是微信对缩略图的大小、title、description
         * 等参数的大小做了限制，所以有可能是大小超过了默认的范围。
         * 一般情况下缩略图超出比较常见。Title、description都是文本，一般不会超过。
         *//*
        Bitmap thumbBitmap = Bitmap.createScaledBitmap(thumb, THUMB_SIZE, THUMB_SIZE, true);
        thumb.recycle();
        msg.thumbData = bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = shareType;
        mWXApi.sendReq(req);*/
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
