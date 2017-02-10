package ruolan.com.livestreaming.presenter.ipresenter;

import ruolan.com.livestreaming.base.BasePresenter;
import ruolan.com.livestreaming.base.BaseView;

/**
 * Created by wuyinlei on 2017/2/10.
 */

public abstract class IMainPresenter implements BasePresenter{

    public BaseView mBaseView;

    public IMainPresenter(BaseView baseView) {
        mBaseView = baseView;
    }

    /**
     * 检测缓存
     */
    protected abstract void checkCacheLogin();

    public interface IMainView extends BaseView {
    }
}
