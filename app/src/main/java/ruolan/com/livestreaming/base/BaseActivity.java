package ruolan.com.livestreaming.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by wuyinlei on 2017/1/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Context mContext;
    private Handler mHandler = new Handler();

    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBeforeLayout();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        mContext = this;
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
        }

        initView();
        initData();
        setListener();
    }

    /**
     * 控件监听
     */
    protected abstract void setListener();

    /**
     * 请求数据
     */
    protected abstract void initData();

    /**
     * 控件查找
     */
    protected abstract void initView();

    /**
     * 加载布局
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 在加载布局之前需要做的事情
     */
    protected void setBeforeLayout(){}


    /**
     * 显示toast
     *
     * @param resId
     */
    public void showToast(final int resId) {
        showToast(getString(resId));
    }

    /**
     * 显示toast
     *
     * @param resStr
     * @return Toast对象，便于控制toast的显示与关闭
     */
    public Toast showToast(final String resStr) {

        if (TextUtils.isEmpty(resStr)) {
            return null;
        }

        Toast toast = null;

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(BaseActivity.this, resStr,
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return toast;
    }

}
