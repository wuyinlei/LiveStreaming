package ruolan.com.livestreaming.presenter;


import android.system.ErrnoException;
import android.text.TextUtils;
import android.util.Log;

import ruolan.com.livestreaming.activity.LoginActivity;
import ruolan.com.livestreaming.http.AsyncHttp;
import ruolan.com.livestreaming.http.data.UserInfo;
import ruolan.com.livestreaming.http.request.LoginRequest;
import ruolan.com.livestreaming.http.request.PhoneLoginRequest;
import ruolan.com.livestreaming.http.request.RequestComm;
import ruolan.com.livestreaming.http.response.Response;
import ruolan.com.livestreaming.logic.IMLogin;
import ruolan.com.livestreaming.logic.IUserInfoMgrListener;
import ruolan.com.livestreaming.logic.UserInfoMgr;
import ruolan.com.livestreaming.presenter.ipresenter.ILoginPresenter;
import ruolan.com.livestreaming.utils.AsimpleCache.ACache;
import ruolan.com.livestreaming.utils.AsimpleCache.CacheConstants;
import ruolan.com.livestreaming.utils.Constants;
import ruolan.com.livestreaming.utils.OtherUtils;
import ruolan.com.livestreaming.utils.RegularUtils;


public class LoginPresenter extends ILoginPresenter implements IMLogin.IMLoginLinstener{

    ILoginPresenter.ILoginView mLoginView;

    private IMLogin mIMLogin = IMLogin.getInstance();

    public LoginPresenter(LoginActivity loginView) {
        super(loginView);
        mLoginView = loginView;
    }

    @Override
    public boolean checkPhoneLogin(String phone, String vertifyCode) {
        if (!RegularUtils.isMobileExact(phone)) {
            //mLoginView.getContext()
            mLoginView.phoneError("手机号码不正确");
            return false;
        }

        if (vertifyCode.length() != 4 && vertifyCode == null) {
            mLoginView.verifyCodeError("验证码输入错误");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkUserNameLogin(String username, String password) {
        if (OtherUtils.isUsernameVaild(username)) {
            if (OtherUtils.isPasswordValid(password)) {
                if (OtherUtils.isNetworkAvailable(mLoginView.getContext())) {
                    return true;
                } else {
                    mLoginView.showMsg("当前无网络连接");
                }
            } else {
                mLoginView.passwordError("密码过短");
            }
        } else {
            mLoginView.usernameError("用户名不符合规范");
        }
        mLoginView.dismissLoading();
        return false;
    }

    @Override
    public void phoneLogin(final String phone, String verifyCode) {
        if (checkPhoneLogin(phone, verifyCode)) {
            final PhoneLoginRequest request = new PhoneLoginRequest(1000, phone, verifyCode);
            AsyncHttp.instance().postJson(request, new AsyncHttp.IHttpListener() {
                @Override
                public void onStart(int requestId) {
                    mLoginView.showLoading();
                }

                @Override
                public void onSuccess(int requestId, Response response) {
                    if (response.status == RequestComm.SUCCESS) {
                        mLoginView.loginSuccess();
                        ACache.get(mLoginView.getContext()).put(CacheConstants.LOGIN_USERNAME, phone);
                    } else {
                        mLoginView.loginFailed(response.status,response.msg);
                    }
                }

                @Override
                public void onFailure(int requestId, int httpStatus, Throwable error) {
                    mLoginView.loginFailed(httpStatus,error.toString());
                }
            });
        }
    }

    @Override
    public void usernameLogin(final String userName, final String password) {
        if (checkUserNameLogin(userName, password)) {
            final LoginRequest request = new LoginRequest(RequestComm.register, userName, password);
            AsyncHttp.instance().postJson(request, new AsyncHttp.IHttpListener() {
                @Override
                public void onStart(int requestId) {
                    mLoginView.showLoading();
                }

                @Override
                public void onSuccess(int requestId, Response response) {
                    if (response.status == RequestComm.SUCCESS) {
                        UserInfo info = (UserInfo) response.data;
                        if (!TextUtils.isEmpty(info.sdkAppId) && !TextUtils.isEmpty(info.sdkAccountType)) {
                            try {
                                Constants.IMSDK_APPID = Integer.parseInt(info.sdkAppId);
                                Constants.IMSDK_ACCOUNT_TYPE = Integer.parseInt(info.sdkAccountType);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        UserInfo.saveCache(mLoginView.getContext(),info);
                        UserInfoMgr.getInstance().setUserInfo(info);
                        mIMLogin.imLogin(info.userId,info.sigId);
                        mIMLogin.setIMLoginLinstener(LoginPresenter.this);
                        ACache.get(mLoginView.getContext()).put(CacheConstants.LOGIN_USERNAME, userName);
                        ACache.get(mLoginView.getContext()).put(CacheConstants.LOGIN_PASSWORD, password);
                        ACache.get(mLoginView.getContext()).put("user_id", info.userId);
                    }
                }

                @Override
                public void onFailure(int requestId, int httpStatus, Throwable error) {
                    mLoginView.loginFailed(httpStatus,error.toString());
                    mLoginView.dismissLoading();
                }
            });
        }
    }

    @Override
    public void sendVerifyCode(String phoneNum) {

    }

    public void setIMLoginListener() {
        mIMLogin.setIMLoginLinstener(this);
    }

    public void removeIMLoginListener() {
        mIMLogin.removeIMLoginListener();
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }

    @Override
    public void onSuccess() {
        UserInfoMgr.getInstance().setUserId(mIMLogin.getLastUserInfo().identifier, new IUserInfoMgrListener() {
            @Override
            public void OnQueryUserInfo(int error, String errorMsg) {
                // TODO: 16/8/10
            }

            @Override
            public void OnSetUserInfo(int error, String errorMsg) {
                if (0 != error) {
                    mLoginView.showMsg("设置 User ID 失败");
                }
            }
        });

        mLoginView.showMsg("登陆成功");
        mIMLogin.removeIMLoginListener();
        mLoginView.dismissLoading();
        mLoginView.loginSuccess();
    }

    @Override
    public void onFailure(int code, String msg) {
        Log.d("log", "IM Login Error errCode:" + code + " msg:" + msg);
        //被踢下线后弹窗显示被踢
        if (6208 == code) {
            OtherUtils.showKickOutDialog(mLoginView.getContext());
        }
        mLoginView.showMsg("登录失败");
        mLoginView.dismissLoading();
        mLoginView.loginFailed(code, msg);
    }
}
