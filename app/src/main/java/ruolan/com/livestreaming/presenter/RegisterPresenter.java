package ruolan.com.livestreaming.presenter;


import ruolan.com.livestreaming.http.AsyncHttp;
import ruolan.com.livestreaming.http.data.UserInfo;
import ruolan.com.livestreaming.http.request.PhoneLoginRequest;
import ruolan.com.livestreaming.http.request.RegisterRequest;
import ruolan.com.livestreaming.http.request.RequestComm;
import ruolan.com.livestreaming.http.request.VerifyCodeRequest;
import ruolan.com.livestreaming.http.response.Response;
import ruolan.com.livestreaming.presenter.ipresenter.IRegisterPresenter;
import ruolan.com.livestreaming.utils.OtherUtils;

/**
 * p层 逻辑处理类
 */
public class RegisterPresenter extends IRegisterPresenter {

    private IRegisterView mIRegisterView;
    public static final String TAG = RegisterPresenter.class.getSimpleName();

    public RegisterPresenter(IRegisterView baseView) {
        super(baseView);
        if (mBaseView != null)
            mIRegisterView = baseView;
    }

    @Override
    public void sendVerifyCode(String phone) {
        if (OtherUtils.isPhoneNumValid(phone)) {
            if (OtherUtils.isNetworkAvailable(mIRegisterView.getContext())) {
                final VerifyCodeRequest request = new VerifyCodeRequest(1000, phone);
                AsyncHttp.instance().postJson(request, new AsyncHttp.IHttpListener() {
                    @Override
                    public void onStart(int requestId) {
                        mIRegisterView.showLoading();
                    }

                    @Override
                    public void onSuccess(int requestId, Response response) {
                        if (response.status == RequestComm.SUCCESS) {
                            UserInfo userInfo = (UserInfo) response.data;
                            if (null != mIRegisterView) {
                                mIRegisterView.verifyCodeSuccess(60, 60);
                            }
                        } else {
                            mIRegisterView.verifyCodeFailed("获取台验证码失败");
                        }
                        mIRegisterView.dismissLoading();
                    }

                    @Override
                    public void onFailure(int requestId, int httpStatus, Throwable error) {
                        if (null != mIRegisterView) {
                            mIRegisterView.verifyCodeFailed("获取台验证码失败");
                        }
                        mIRegisterView.dismissLoading();
                    }
                });
            } else {
                mIRegisterView.showMsg("当前无网络连接");
            }
        } else {
            mIRegisterView.showRegisterError("手机号码不符合规范");
        }
    }

    @Override
    public boolean checkNormalRegister(String username, String password, String passwordVerify) {
        if (OtherUtils.isUsernameVaild(username)) {
            if (OtherUtils.isPasswordValid(password)) {
                if (password.equals(passwordVerify)) {
                    if (OtherUtils.isNetworkAvailable(mIRegisterView.getContext())) {
                        return true;
                    } else {
                        mIRegisterView.showRegisterError("当前网络不可用");
                    }
                } else {
                    mIRegisterView.showRegisterError("两次输入的密码不一样");
                }
            } else {
                mIRegisterView.showRegisterError("密码长度应为6-16位");
            }
        } else {
            mIRegisterView.showRegisterError("用户名不符合规范");
        }
        return false;
    }

    @Override
    public boolean checkPhoneRegister(String phone, String verifyCode) {
        if (OtherUtils.isPhoneNumValid(phone)) {
            if (OtherUtils.isVerifyCodeValid(verifyCode)) {
                if (OtherUtils.isNetworkAvailable(mIRegisterView.getContext())) {
                    return true;
                } else {
                    mIRegisterView.showMsg("当前网络不可用");
                }
            } else {
                mIRegisterView.showPasswordError("验证码格式错误");
            }
        } else {
            mIRegisterView.showPhoneError("手机号码不符合规范");
        }
        return false;
    }

    @Override
    public void normalRegister(final String username, String password, String passwordVerify) {
        if (checkNormalRegister(username, password, passwordVerify)) {
            RegisterRequest registerRequest = new RegisterRequest(RequestComm.register, username, password);
            AsyncHttp.instance().postJson(registerRequest, new AsyncHttp.IHttpListener() {
                @Override
                public void onStart(int requestId) {
                    mIRegisterView.showLoading();
                }

                @Override
                public void onSuccess(int requestId, Response response) {
                    if (response.status == RequestComm.SUCCESS) {
                        UserInfo userInfo = (UserInfo) response.data;
                            mIRegisterView.onSuccess(username);
                    } else {
                        mIRegisterView.onFailure(response.status, response.msg);
                    }
                    mIRegisterView.dismissLoading();
                }

                @Override
                public void onFailure(int requestId, int httpStatus, Throwable error) {
                        mIRegisterView.onFailure(httpStatus, error.toString());
                        mIRegisterView.dismissLoading();
                }
            });
        }
    }

    @Override
    public void phoneRegister(String phone, String verifyCode) {
        if (checkPhoneRegister(phone, verifyCode)) {
            PhoneLoginRequest request = new PhoneLoginRequest(RequestComm.register, phone, verifyCode);
            AsyncHttp.instance().postJson(request, new AsyncHttp.IHttpListener() {
                @Override
                public void onStart(int requestId) {
                    mIRegisterView.showLoading();
                }

                @Override
                public void onSuccess(int requestId, Response response) {
                    if (response.status == RequestComm.SUCCESS) {
                            mIRegisterView.onSuccess(null);
                    } else {
                        mIRegisterView.verifyCodeError("验证码不正确");
                    }
                    mIRegisterView.dismissLoading();
                }

                @Override
                public void onFailure(int requestId, int httpStatus, Throwable error) {
                        mIRegisterView.onFailure(httpStatus, "网络异常");
                    mIRegisterView.dismissLoading();
                }
            });
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }
}
