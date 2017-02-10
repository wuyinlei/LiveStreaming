package ruolan.com.livestreaming.activity;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.lang.ref.WeakReference;

import ruolan.com.livestreaming.R;
import ruolan.com.livestreaming.base.BaseActivity;
import ruolan.com.livestreaming.presenter.RegisterPresenter;
import ruolan.com.livestreaming.presenter.ipresenter.IRegisterPresenter;
import ruolan.com.livestreaming.utils.OtherUtils;
import ruolan.com.livestreaming.utils.ToastUtils;

public class RegisterActivity extends BaseActivity implements IRegisterPresenter.IRegisterView {



    //手机验证注册控件

    //动画
    private AlphaAnimation fadeInAnimation, fadeOutAnimation;

    private RegisterPresenter mIRegisterPresenter;
    private RelativeLayout mRlRegisterRoot;
    private TextView mTvVerifyCode;
    private TextView mTvBack;
    private TextInputLayout mTilRegister;
    private EditText mEtRegister;
    private TextInputLayout mTilPassword;
    private EditText mEtPassword;
    private TextInputLayout mTilPasswordVerify;
    private EditText mEtPasswordVerify;
    private TextView mTvPhoneRegister;
    private Button mBtnRegister;
    private ProgressBar mProgressbar;

    String mPassword;

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        if (null != mRlRegisterRoot) {
            ViewTarget<RelativeLayout, GlideDrawable> viewTarget = new ViewTarget<RelativeLayout, GlideDrawable>(mRlRegisterRoot) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    this.view.setBackgroundDrawable(resource.getCurrent());
                }
            };
            Glide.with(getApplicationContext()) // safer!
                    .load(R.drawable.bg_dark)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(viewTarget);
        }

        mIRegisterPresenter= new RegisterPresenter(this);
        fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        fadeInAnimation.setDuration(250);
        fadeOutAnimation.setDuration(250);
        LayoutTransition layoutTransition = new LayoutTransition();
        mRlRegisterRoot.setLayoutTransition(layoutTransition);
    }

    /**
     * 显示loading的时候组件是否可点击
     * @param active  false  true
     */
    public void showOnLoading(boolean active) {
        if (active) {
            mProgressbar.setVisibility(View.VISIBLE);
            mBtnRegister.setVisibility(View.INVISIBLE);
            mEtPassword.setEnabled(false);
            mEtPasswordVerify.setEnabled(false);
            mEtRegister.setEnabled(false);
            mTvPhoneRegister.setClickable(false);
            mTvPhoneRegister.setTextColor(getResources().getColor(R.color.colorLightTransparentGray));
            mBtnRegister.setEnabled(false);
        } else {
            mProgressbar.setVisibility(View.GONE);
            mBtnRegister.setVisibility(View.VISIBLE);
            mEtPassword.setEnabled(true);
            mEtPasswordVerify.setEnabled(true);
            mEtRegister.setEnabled(true);
            mTvPhoneRegister.setClickable(true);
            mTvPhoneRegister.setTextColor(getResources().getColor(R.color.colorTransparentGray));
            mBtnRegister.setEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        userNameRegisterViewInit();
    }

    /**
     * 点击手机注册界面逻辑
     */
    private void phoneRegisterViewHint(){
        mEtRegister.setText("");
        mEtRegister.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        mEtPassword.setText("");
        mTvPhoneRegister.setText(getString(R.string.activity_register_normal_register));
        mTilRegister.setHint(getString(R.string.login_phone_num));
        mTilPassword.setHint(getString(R.string.activity_register_verify_code));
        mEtPasswordVerify.setVisibility(View.GONE);

        mTvVerifyCode.setVisibility(View.VISIBLE);
        mTvVerifyCode.bringToFront();
        mTvVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIRegisterPresenter.sendVerifyCode(mEtRegister.getText().toString().trim());
            }
        });
        mTvPhoneRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转换为用户名注册界面
                userNameRegisterViewInit();
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //手机注册逻辑
                mPassword = null;
                mIRegisterPresenter.phoneRegister(mEtRegister.getText().toString(), mEtPassword.getText().toString());
            }
        });
    }

    /**
     * 普通用户注册界面
     */
    private void userNameRegisterViewInit() {
        mEtRegister.setText("");
        mEtRegister.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        mEtPassword.setText("");
        mEtPasswordVerify.setText("");
        mEtPasswordVerify.setVisibility(View.VISIBLE);
        mTvVerifyCode.setVisibility(View.GONE);
        mTilPasswordVerify.setVisibility(View.VISIBLE);
        mTvPhoneRegister.setText(getString(R.string.activity_register_phone_register));
        mTilRegister.setHint(getString(R.string.activity_register_username));
        mTilPassword.setHint(getString(R.string.activity_register_password));
        mTilPasswordVerify.setHint(getString(R.string.activity_register_password_verify));
        mTvPhoneRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转换为手机注册界面
                phoneRegisterViewHint();
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用normal注册逻辑
                mPassword = mEtPassword.getText().toString();
                mIRegisterPresenter.normalRegister(mEtRegister.getText().toString(),
                        mPassword, mEtPasswordVerify.getText().toString());
            }
        });

        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 跳转到本界面
     * @param context  上下文
     */
    public static void invoke(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {


        mRlRegisterRoot = (RelativeLayout) findViewById(R.id.rl_register_root);
        mTvVerifyCode = (TextView) findViewById(R.id.btn_verify_code);
        mTvBack = (TextView) findViewById(R.id.tv_back);
        mTilRegister = (TextInputLayout) findViewById(R.id.til_register);
        mEtRegister = (EditText) findViewById(R.id.et_register);
        mTilPassword = (TextInputLayout) findViewById(R.id.til_password);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTilPasswordVerify = (TextInputLayout) findViewById(R.id.til_password_verify);
        mEtPasswordVerify = (EditText) findViewById(R.id.et_password_verify);
        mTvPhoneRegister = (TextView) findViewById(R.id.tv_phone_register);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onSuccess(String username) {
        Toast.makeText(getApplicationContext(), "成功注册", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailure(int code, String msg) {
        showMsg(msg);
    }

    @Override
    public void showPasswordError(String errorMsg) {
        showMsg("密码错误");
    }

    @Override
    public void showPhoneError(String errorMsg) {
        showMsg("手机号错误");
    }

    @Override
    public void showRegisterError(String errorMsg) {
        showMsg("注册失败");
    }

    @Override
    public void verifyCodeError(String errorMsg) {
        showMsg("验证码错误");
    }

    @Override
    public void verifyCodeFailed(String errorMsg) {
        showMsg("获取验证码失败");
    }

    @Override
    public void verifyCodeSuccess(int reaskDuration, int expireDuration) {
        showMsg("注册短信下发,验证码" + expireDuration / 60 + "分钟内有效");
        OtherUtils.startTimer(new WeakReference<>(mTvVerifyCode), "验证码", reaskDuration, 1);
    }

    @Override
    public void showLoading() {
        showOnLoading(true);
    }

    @Override
    public void dismissLoading() {
        showOnLoading(false);
    }

    @Override
    public void showMsg(String str) {
        ToastUtils.showShort(this,str);
    }

    @Override
    public void showMsg(int resId) {
        ToastUtils.showShort(this,resId);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
