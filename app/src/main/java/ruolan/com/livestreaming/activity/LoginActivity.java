package ruolan.com.livestreaming.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import ruolan.com.livestreaming.R;
import ruolan.com.livestreaming.base.BaseActivity;
import ruolan.com.livestreaming.presenter.LoginPresenter;
import ruolan.com.livestreaming.presenter.ipresenter.ILoginPresenter;
import ruolan.com.livestreaming.utils.AsimpleCache.ACache;
import ruolan.com.livestreaming.utils.AsimpleCache.CacheConstants;
import ruolan.com.livestreaming.utils.OtherUtils;

public class LoginActivity extends BaseActivity implements ILoginPresenter.ILoginView, View.OnClickListener {


    private static final String TAG = LoginActivity.class.getSimpleName();

//	private IMLogin mTCLoginMgr;

    //共用控件
    private RelativeLayout rootRelativeLayout;
    private ProgressBar progressBar;
    private EditText etPassword;
    private EditText etLogin;
    private Button btnLogin;
    private Button btnPhoneLogin;
    private TextInputLayout tilLogin, tilPassword;
    private Button btnRegister;

    //手机验证登陆控件
    private TextView tvVerifyCode;

    private boolean isPhoneLogin = false;

    private LoginPresenter mLoginPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mLoginPresenter = new LoginPresenter(this);
        rootRelativeLayout = (RelativeLayout) findViewById(R.id.rl_login_root);

        etLogin = (EditText) findViewById(R.id.et_login);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnPhoneLogin = (Button) findViewById(R.id.btn_phone_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        tilLogin = (TextInputLayout) findViewById(R.id.til_login);
        tilPassword = (TextInputLayout) findViewById(R.id.til_password);
        tvVerifyCode = (TextView) findViewById(R.id.btn_verify_code);
        userNameLoginViewInit();
    }

    @Override
    protected void initData() {
        etLogin.setText(ACache.get(this).getAsString(CacheConstants.LOGIN_USERNAME));
        etPassword.setText(ACache.get(this).getAsString(CacheConstants.LOGIN_PASSWORD));
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置登录回调,resume设置回调避免被registerActivity冲掉
        mLoginPresenter.setIMLoginListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //删除登录回调
        mLoginPresenter.removeIMLoginListener();
    }

    /**
     * 短信登录界面init
     */
    public void phoneLoginViewinit() {
        isPhoneLogin = true;
        tvVerifyCode.setVisibility(View.VISIBLE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(250);
        tvVerifyCode.setAnimation(alphaAnimation);

        //设定点击优先级于最前（避免被EditText遮挡的情况）
        tvVerifyCode.bringToFront();

        etLogin.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        btnPhoneLogin.setText(getString(R.string.login_normal_login));

        tilLogin.setHint(getString(R.string.login_phone_num));

        tilPassword.setHint(getString(R.string.login_verify_code_edit));

        tvVerifyCode.setOnClickListener(this);

        btnPhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转换为用户名登录界面
                userNameLoginViewInit();
            }
        });

        btnLogin.setOnClickListener(this);

    }

    /**
     * 用户名密码登录界面init
     */
    public void userNameLoginViewInit() {
        isPhoneLogin = false;
        tvVerifyCode.setVisibility(View.GONE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(250);
        tvVerifyCode.setAnimation(alphaAnimation);

        etLogin.setInputType(EditorInfo.TYPE_CLASS_TEXT);

        etLogin.setText("");
        etPassword.setText("");

        btnPhoneLogin.setText(getString(R.string.btn_phone_login));

        tilLogin.setHint(getString(R.string.login_username));

        tilPassword.setHint(getString(R.string.login_password));

        btnRegister.setOnClickListener(this);

        btnPhoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转换为手机登录界面
                phoneLoginViewinit();
            }
        });

        btnLogin.setOnClickListener(this);

    }

    /**
     * trigger loading模式
     *
     * @param active
     */
    public void showOnLoading(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            etLogin.setEnabled(false);
            etPassword.setEnabled(false);
            btnPhoneLogin.setClickable(false);
            btnPhoneLogin.setTextColor(getResources().getColor(R.color.colorLightTransparentGray));
            btnRegister.setClickable(false);
        } else {
            progressBar.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            etLogin.setEnabled(true);
            etPassword.setEnabled(true);
            btnPhoneLogin.setClickable(true);
            btnPhoneLogin.setTextColor(getResources().getColor(R.color.colorTransparentGray));
            btnRegister.setClickable(true);
            btnRegister.setTextColor(getResources().getColor(R.color.colorTransparentGray));
        }

    }

    public void showLoginError(String errorString) {
        etLogin.setError(errorString);
        showOnLoading(false);
    }

    public void showPasswordError(String errorString) {
        etPassword.setError(errorString);
        showOnLoading(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (isPhoneLogin) {
                    //手机登录逻辑
                    mLoginPresenter.phoneLogin(etLogin.getText().toString(), etPassword.getText().toString());
                } else {
                    //调用normal登录逻辑
                    mLoginPresenter.usernameLogin(etLogin.getText().toString(), etPassword.getText().toString());
                }

                break;
            case R.id.btn_verify_code:
                mLoginPresenter.sendVerifyCode(etLogin.getText().toString());
                break;
            case R.id.btn_register:
                RegisterActivity.invoke(this);
                break;
        }
    }

    @Override
    public void loginSuccess() {
        dismissLoading();
        MainActivity.invoke(this);
        finish();
    }

    @Override
    public void loginFailed(int status, String msg) {
        dismissLoading();
        showMsg("登陆失败:" + msg);
    }

    @Override
    public void usernameError(String errorMsg) {
        etLogin.setError(errorMsg);
    }

    @Override
    public void phoneError(String errorMsg) {
        etLogin.setError(errorMsg);
    }

    @Override
    public void passwordError(String errorMsg) {
        etPassword.setError(errorMsg);
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
        OtherUtils.startTimer(new WeakReference<>(tvVerifyCode), "验证码", reaskDuration, 1);
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
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void showMsg(int resId) {
        showToast(resId);
    }

    @Override
    public Context getContext() {
        return this;
    }


    public static void invoke(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
