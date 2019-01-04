package com.dy.cmls.common;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;


import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.utils.URLImageParser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.tvContent) TextView tvContent;
    @BindView(R.id.scrollView) ScrollView scrollView;
    @BindView(R.id.tvTitleTitle) TextView tvTitleTitle;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initView();
    }

    @Override protected void initView() {
        findViewById(R.id.ivLeft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String title = getIntent().getStringExtra("title");
        if (title == null || title.isEmpty()) {
            tvTitleTitle.setText("");
        } else {
            tvTitleTitle.setText(title);
        }
        String url = getIntent().getStringExtra("url");
        if (url == null || url.isEmpty()) {
            errorOut();
            return;
        }
        showLog("WebViewActivity:", "url="+url);
        if (url.startsWith("http")) {
            webView.setVisibility(View.VISIBLE);
            tvContent.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);
            setWebData(url, webView);
        } else {
            webView.setVisibility(View.GONE);
            tvContent.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            URLImageParser imageGetter = new URLImageParser(tvContent);
            tvContent.setText(Html.fromHtml(url, imageGetter, null));
        }
        showLog("url:", url);
    }



    @Override public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void setWebData(String goodsDesc, WebView bWebView) {
        if (bWebView == null) {
            return;
        }
        WebSettings webSettings = bWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(false); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        bWebView.getSettings().setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        }

        bWebView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                showLog("shouldOverrideUrlLoading:", url);
                if (url.contains("/index.php/Api/Index/recharge_back/info/")) {
                    WebViewActivity.this.finish();
                }
                return true;
            }

            @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        bWebView.setWebChromeClient(new WebChromeClient() {

            @Override public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        if (goodsDesc.startsWith("http")) {
            bWebView.loadUrl(goodsDesc);
        } else {
            bWebView.loadData(getHtmlData(goodsDesc), "text/html; charset=utf-8", "utf-8");
        }
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
