package ruolan.com.livestreaming.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ruolan.com.livestreaming.R;
import ruolan.com.livestreaming.base.BaseActivity;
import ruolan.com.livestreaming.presenter.LoginPresenter;
import ruolan.com.livestreaming.presenter.ipresenter.ILoginPresenter;

public class LoginActivity extends BaseActivity implements ILoginPresenter.LoginView, View.OnClickListener {

    private LoginPresenter mLoginPresenter;

    private RelativeLayout mRlLoginRoot;
    private Button mBtnVerifyCode;
    private AutoCompleteTextView mEtLogin;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Button mTvPhoneLogin;
    private Button mBtnRegister;
    private ProgressBar mProgressbar;

    @Override
    protected void setListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        mRlLoginRoot = (RelativeLayout) findViewById(R.id.rl_login_root);
        mBtnVerifyCode = (Button) findViewById(R.id.btn_verify_code);
        mEtLogin = (AutoCompleteTextView) findViewById(R.id.et_login);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvPhoneLogin = (Button) findViewById(R.id.tv_phone_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar);



        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 启动该活动
     *
     * @param context  上下文
     */
    public static void invoke(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkError(String errorMsg) {

    }

    @Override
    public void verifyCodeError(String code) {

    }


    @Override
    public void phoneError(String code) {
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showMsg(String str) {

    }

    @Override
    public void showMsg(int resId) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String phone = mEtLogin.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                mLoginPresenter.phoneLogin(phone,password);
                break;

            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;

        }
    }
}
