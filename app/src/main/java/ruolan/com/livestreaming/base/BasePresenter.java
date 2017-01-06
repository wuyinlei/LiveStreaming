package ruolan.com.livestreaming.base;

/**
 * MVP 中的presenter基类
 */
public interface BasePresenter {

    /**
     * Presenter 开始处理方法
     */
    void start();

    /**
     * Presenter 结束处理方法
     */
    void finish();
}
