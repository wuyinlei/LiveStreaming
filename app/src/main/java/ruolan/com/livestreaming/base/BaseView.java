package ruolan.com.livestreaming.base;

import android.content.Context;

/**
 * MVP  中的view的基类
 */

public interface BaseView {

    /**
     * 有网络或者延时操作的时候的弹框提示
     */
    void showLoading();

    /**
     * 弹框消失
     */
    void dismissLoading();

    /**
     * 消息提示
     * @param str
     */
    void showMsg(String str);
    void showMsg(int resId);

    Context getContext();
}
