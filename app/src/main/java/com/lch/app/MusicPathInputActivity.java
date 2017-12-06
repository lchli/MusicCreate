package com.lch.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lch.netkit.NetKit;
import com.lch.netkit.common.base.BaseActivity;
import com.lch.netkit.file.helper.FileOptions;
import com.lch.netkit.file.helper.FileResponse;
import com.lch.netkit.file.helper.FileTransferListener;
import com.lch.netkit.file.helper.NetworkError;
import com.lch.netkit.file.helper.UploadFileParams;

import java.lang.ref.WeakReference;

public class MusicPathInputActivity extends BaseActivity {

    private static final String TAG = "MusicPathInputActivity";

    private EditText etFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_path_input);
        setTitle("文件选择");

        etFilePath = f(R.id.etFilePath);

    }

    public void upload(View view) {
        String fpath = etFilePath.getText().toString();

        UploadFileParams param = UploadFileParams.newInstance()
                .addFile(new FileOptions().setFilePath(fpath).setFileKey("test"))
                .setUrl("https://api.github.com/markdown/raw");

        NetKit.fileRequest().uploadFile(param, new UploadFileTransferListener(this));
    }


    private static class UploadFileTransferListener implements FileTransferListener {

        private WeakReference<MusicPathInputActivity> activityRef;

        public UploadFileTransferListener(MusicPathInputActivity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onResponse(FileResponse fileResponse) {
            final MusicPathInputActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }
            if (fileResponse == null) {
                return;
            }
            String json = fileResponse.getReponseString();
            ToastUtils.showLong("上传完成，json:" + json);
        }

        @Override
        public void onError(NetworkError networkError) {
            ToastUtils.showLong(networkError.msg + "");
        }

        @Override
        public void onProgress(double v) {
            LogUtils.e(TAG, "onProgress:" + v);
        }
    }
}
