package ruolan.com.livestreaming.presenter.ipresenter;

import ruolan.com.livestreaming.base.BasePresenter;
import ruolan.com.livestreaming.base.BaseView;


/**
 * 登录逻辑P层
 */
public abstract class ILoginPresenter implements BasePresenter{

    protected BaseView mBaseView;

    public ILoginPresenter(BaseView baseView) {
        mBaseView = baseView;
    }

    /**
     * 检测手机号  验证码的正确性
     * @param phone  手机号
     * @param vertifyCode  验证码
     * @return  false  true
     */
    public abstract boolean checkPhoneLogin(String phone,String vertifyCode);


    /**
     * 检测用户名和密码的合理性
     * @param username  用户名
     * @param password  密码
     * @return  true  false
     */
    public abstract boolean checkUserNameLogin(String username,String password);


    /**
     * 手机号码登陆
     * @param phone
     * @param verifyCode
     */
    public abstract void phoneLogin(String phone, String verifyCode);

    /**
     * 用户名密码登陆
     * @param userName
     * @param password
     */
    public abstract void usernameLogin(String userName, String password);


    public interface LoginView extends BaseView{
        /**
         * 登陆成功
         */
        public void loginSuccess();

        /**
         * 登陆失败
         */
        public void loginFailed(String msg);

        /**
         * 错误信息
         * @param errorMsg
         */
        public void checkError(String errorMsg);

        /**
         * 验证码
         * @param code
         */
        public void verifyCodeError(String code);

        /**
         * 手机号出错
         * @param code
         */
        public void phoneError(String code);
    }
}
