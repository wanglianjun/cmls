package com.dy.cmls.common.web.library;

import android.content.Intent;

/**
 * Created by cenxiaozhong on 2017/5/22.
 */

public interface IFileUploadChooser {



    void openFileChooser();

    void fetchFilePathFromIntent(int requestCode, int resultCode, Intent data);
}
