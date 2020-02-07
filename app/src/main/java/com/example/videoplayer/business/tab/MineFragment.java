package com.example.videoplayer.business.tab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.widget.TextView;

import com.example.videoplayer.R;
import com.example.videoplayer.business.PersonalDataActivity;
import com.example.videoplayer.business.VideoHistoryActivity;
import com.example.videoplayer.utils.PictureSelectorUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhuandian.base.BaseFragment;
import com.zhuandian.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc :
 * author：xiedong
 * date：2020/02/05
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.cir_header)
    CircleImageView civHeader;
    private SharedPreferences sharedPreferences;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        sharedPreferences = actitity.getSharedPreferences("config", Context.MODE_PRIVATE);
        String headerPath = sharedPreferences.getString("header_path", "");
        if (!headerPath.isEmpty()){
            decodePath2Bitmap(headerPath);
        }
    }

    @OnClick({R.id.tv_history, R.id.tv_personal,R.id.cir_header})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_history:
                startActivity(new Intent(actitity, VideoHistoryActivity.class));
                break;
            case R.id.tv_personal:
                startActivity(new Intent(actitity, PersonalDataActivity.class));
                break;
            case R.id.cir_header:
                PictureSelectorUtils.selectHeaderImg(this);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() > 0) {
                    String imagePath = selectList.get(0).getCompressPath();
                     sharedPreferences = actitity.getSharedPreferences("config", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("header_path",imagePath).commit();
                    decodePath2Bitmap(imagePath);
                }
            }
        }
    }

    /**
     * 把指定路径的image资源转成Bitmap
     *
     * @param path
     */
    private void decodePath2Bitmap(String path) {
        Bitmap bm = BitmapFactory.decodeFile(path);
        if (bm != null) {
            bm = ImageCrop(bm);
            civHeader.setImageBitmap(bm);
        }
    }


    public Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int wh = w > h ? h : w;// 裁切后所取的正方形区域边长
        int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;

        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f); //长和宽放大缩小的比例
        Bitmap result = Bitmap.createBitmap(bitmap, retX, retY, wh, wh, matrix, false);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
            System.gc();
        }
        return result;
    }
}
