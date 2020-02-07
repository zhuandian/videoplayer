package com.example.videoplayer.business;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videoplayer.R;
import com.example.videoplayer.entity.UserEntity;
import com.zhuandian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * desc :
 * author：xiedong
 * date：2020/02/07
 */
public class PersonalDataActivity extends BaseActivity {
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.et_user_desc)
    EditText etUserDesc;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private UserEntity userEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void setUpView() {
        setTitle("个人信息");
        userEntity = BmobUser.getCurrentUser(UserEntity.class);
        etNickName.setText(userEntity.getNikeName()==null?userEntity.getUsername():userEntity.getNikeName());
        etUserDesc.setText(userEntity.getUserInfo()==null?"":userEntity.getUserInfo());
    }

    @OnClick(R.id.tv_submit)
    public void onClick() {
        if (!TextUtils.isEmpty(etNickName.getText().toString()) || !TextUtils.isEmpty(etUserDesc.getText().toString())) {
            userEntity.setNikeName(etNickName.getText().toString());
            userEntity.setUserInfo(etUserDesc.getText().toString());
            userEntity.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(PersonalDataActivity.this, "更新成功...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
