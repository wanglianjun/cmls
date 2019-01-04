package com.dy.cmls.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;


import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.utils.URLImageParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lcjing on 2018/12/12.
 */

public class WebViewFragment extends BaseFragment {


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.scrollView)
    ScrollView scrollView;



    public static WebViewFragment getInstance(String url){
        WebViewFragment webViewFragment=new WebViewFragment();
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_webview,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    protected void initView(View view) {
        String url = getArguments().getString("url");
        if (url == null || url.isEmpty()) {
            return;
        }
        if (url.startsWith("http")) {
            webView.setVisibility(View.VISIBLE);
            tvContent.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);
            setWebData(url);
        } else {
            webView.setVisibility(View.GONE);
            tvContent.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            URLImageParser imageGetter = new URLImageParser(tvContent);
            tvContent.setText(Html.fromHtml(url, imageGetter, null));
        }
        showLog("url:", url);
    }


    public static String getNewContent(String htmltext){
        try {
            Document doc= Jsoup.parse(htmltext);
            Elements elements=doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width","100%").attr("height","auto");
            }

            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }
//---------------------
//    作者：潇潇凤儿
//    来源：CSDN
//    原文：https://blog.csdn.net/smileiam/article/details/72123227
//    版权声明：本文为博主原创文章，转载请附上博文链接！


    @SuppressLint("SetJavaScriptEnabled")
    private void setWebData(String goodsDesc) {//bWebView
//        if (bWebView == null) {
//            return;
//        }
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(false); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        } else {
            webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        }

        webView.setWebChromeClient(new WebChromeClient() {

            @Override public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });


        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new JavaScriptInterface(getContext()), "imagelistner");//这个是给图片设置点击监听的，如果你项目需要webview中图片，点击查看大图功能，可以这么添加
        if (goodsDesc.startsWith("http")) {
            webView.loadUrl(goodsDesc);
        } else {
            webView.loadData(getHtmlData(goodsDesc), "text/html; charset=utf-8", "utf-8");
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
            progressBar.setVisibility(View.GONE);
        }



        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
//            imgReset();//重置webview中img标签的图片大小
//            // html加载完成之后，添加监听图片的点击js函数
//            addImageClickListner();
        }
    }


    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }
    public static class JavaScriptInterface {

        private Context context;

        public JavaScriptInterface(Context context) {
            this.context = context;
        }

        //点击图片回调方法
        //必须添加注解,否则无法响应
        @JavascriptInterface
        public void openImage(String img) {
            Log.i("TAG", "响应点击事件!");
            Intent intent = new Intent();
            intent.putExtra("url", img);
            intent.setClass(context, PhotoViewActivity.class);//BigImageActivity查看大图的类，自己定义就好
            context.startActivity(intent);
        }
    }


    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.width = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }


    // 绘制HTML
    private String getHtmlData(String bodyHTML) {
        String head = "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                + "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
