package kk.qisheng.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.List;

import kk.qisheng.library.PhotoChooser;
import kk.qisheng.library.bean.PhotoResult;
import kk.qisheng.library.callback.OnGetPhotoCallback;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChooser();
            }
        });

    }

    private void openChooser() {
        PhotoChooser.getInstance().getPhotos(this, new OnGetPhotoCallback() {
            @Override
            public void onGetPhotos(List<PhotoResult> list) {

                if(list.size()==0){
                    Log.d("PhotoChooser", "用户没有选择任何图片");
                }

                for (PhotoResult photoResult : list) {
                    File imgFile = new File(photoResult.getPhotoPath());
                    File thumbnailFile = new File(photoResult.getThumbnailPath());
                    if (imgFile.exists() && thumbnailFile.exists()) {
                        Log.d("PhotoChooser", "原图路径: " + photoResult.getPhotoPath() + "缩略图路径: " + photoResult.getThumbnailPath());
                    }
                }
            }

        });
    }

}
