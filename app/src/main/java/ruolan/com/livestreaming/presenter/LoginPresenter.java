package ruolan.com.livestreaming.presenter;


import ruolan.com.livestreaming.http.AsyncHttp;
import ruolan.com.livestreaming.http.request.PhoneLoginRequest;
import ruolan.com.livestreaming.http.response.Response;
import ruolan.com.livestreaming.presenter.ipresenter.ILoginPresenter;
import ruolan.com.livestreaming.utils.RegularUtils;


public class LoginPresenter extends ILoginPresenter {

    ILoginPresenter.LoginView mLoginView;

    public LoginPresenter(LoginView loginView) {
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

        if (vertifyCode.length() != 4 && vertifyCode == null){
            mLoginView.verifyCodeError("验证码输入错误");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkUserNameLogin(String username, String password) {
        return false;
    }

    @Override
    public void phoneLogin(String phone, String verifyCode) {
        if (checkPhoneLogin(phone, verifyCode)) {
            final PhoneLoginRequest request = new PhoneLoginRequest(1000, phone, verifyCode);
            AsyncHttp.instance().postJson(request, new AsyncHttp.IHttpListener() {
                @Override
                public void onStart(int requestId) {

                }

                @Override
                public void onSuccess(int requestId, Response response) {
                    if (response.status == 0) {
                        mLoginView.loginSuccess();
                    } else {
                        mLoginView.loginFailed(response.msg);
                    }
                }

                @Override
                public void onFailure(int requestId, int httpStatus, Throwable error) {
                    mLoginView.loginFailed(error.toString());
                }
            });
        }
    }

    @Override
    public void usernameLogin(String userName, String password) {

    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }
}
