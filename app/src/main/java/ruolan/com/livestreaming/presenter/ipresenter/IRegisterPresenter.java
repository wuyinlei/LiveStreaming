package ruolan.com.livestreaming.presenter.ipresenter;

import ruolan.com.livestreaming.base.BasePresenter;
import ruolan.com.livestreaming.base.BaseView;


public abstract class IRegisterPresenter implements BasePresenter{

    protected BaseView mBaseView;

    public IRegisterPresenter(BaseView baseView) {
        mBaseView = baseView;
    }

    public abstract void sendVerifyCode(String phone);

    public abstract boolean checkNormalRegister(String username,String password,String passwordVerify);

    public abstract boolean checkPhoneRegister(String phone,String verifyCode);

    public abstract void normalRegister(String username,String password,String passwordVerify);

    public abstract void phoneRegister(String phone,String verifyCode);

    public interface IRegisterView extends BaseView{
        /**
         * 注册成功
         */
        void onSuccess(String username);

        /**
         * 注册失败
         *
         * @param code 错误码
         * @param msg  错误信息
         */
        void onFailure(int code, String msg);

        void showPasswordError(String errorMsg);

        void showPhoneError(String errorMsg);

        void showRegisterError(String errorMsg);

        void verifyCodeError(String errorMsg);

        void verifyCodeFailed(String errorMsg);

        void verifyCodeSuccess(int reaskDuration, int expireDuration);

    }




}
