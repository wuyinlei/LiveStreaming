package ruolan.com.livestreaming;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ruolan.com.livestreaming.base.BaseActivity;

public class PublishSettingActivity extends BaseActivity {



    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_setting;
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context,PublishSettingActivity.class);
        context.startActivity(intent);
    }
}
