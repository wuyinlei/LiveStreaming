package ruolan.com.livestreaming.presenter;

import java.util.ArrayList;

import ruolan.com.livestreaming.http.AsyncHttp;
import ruolan.com.livestreaming.http.request.LiveListRequest;
import ruolan.com.livestreaming.http.request.RequestComm;
import ruolan.com.livestreaming.http.response.ResList;
import ruolan.com.livestreaming.http.response.Response;
import ruolan.com.livestreaming.model.LiveInfo;
import ruolan.com.livestreaming.presenter.ipresenter.ILiveListPresenter;
import ruolan.com.livestreaming.utils.AsimpleCache.ACache;
import ruolan.com.livestreaming.utils.Constants;

/**
 * Created by wuyinlei on 2017/2/10.
 */

public class LiveListPresenter extends ILiveListPresenter {

    private static final String TAG = LiveListPresenter.class.getSimpleName();
    private boolean mHasMore;
    private boolean isLoading;
    private ArrayList<LiveInfo> mLiveInfoList = new ArrayList<>();

    private ILiveListView mILiveListView;

    public LiveListPresenter(ILiveListView baseView) {
        super(baseView);
        mILiveListView = baseView;
    }


    /**
     * 获取内存中缓存的直播列表
     *
     * @return 完整列表
     */
    public ArrayList<LiveInfo> getLiveListFormCache() {
        return mLiveInfoList;
    }

    /**
     * 分页获取完整直播列表
     */
    public boolean reloadLiveList() {
        mLiveInfoList.clear();
        fetchLiveList(RequestComm.live_list, ACache.get(mBaseView.getContext()).getAsString("user_id"), 1, Constants.PAGESIZE);
        return true;
    }

    @Override
    public boolean loadDataMore() {		if (mHasMore) {
        int pageIndex = mLiveInfoList.size() / Constants.PAGESIZE + 1;
        fetchLiveList(RequestComm.live_list_more, ACache.get(mBaseView.getContext()).getAsString("user_id"), pageIndex, Constants.PAGESIZE);
    }
        return true;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isHasMore() {
        return mHasMore;
    }


    /**
     * 获取直播列表
     *
     * @param type      1:拉取在线直播列表 2:拉取7天内录播列表 3:拉取在线直播和7天内录播列表，直播列表在前，录播列表在后
     * @param pageIndex 页数
     * @param pageSize  每页个数
     */
    public void fetchLiveList(final int type, final String userId, final int pageIndex, final int pageSize) {
        LiveListRequest request = new LiveListRequest(type, userId, pageIndex, pageSize);

        AsyncHttp.instance().postJson(request, new AsyncHttp.IHttpListener() {
            @Override
            public void onStart(int requestId) {
                isLoading = true;
            }

            @Override
            public void onSuccess(int requestId, Response response) {
                if (response.status == RequestComm.SUCCESS) {
                    ResList<LiveInfo> resList = (ResList<LiveInfo>) response.data;
                    if (resList != null) {
                        ArrayList<LiveInfo> result = (ArrayList<LiveInfo>) resList.items;
                        if (result != null && !result.isEmpty()) {
                            mLiveInfoList.addAll(result);
                            mHasMore = (mLiveInfoList.size() >= pageIndex * Constants.PAGESIZE);
                            if (mILiveListView != null) {
                                mILiveListView.onLiveList(0, mLiveInfoList, pageIndex == 1);
                            }
                        } else {
                            mHasMore = false;
                            if (mILiveListView != null) {
                                mILiveListView.onLiveList(0, mLiveInfoList, pageIndex == 1);
                            }
                        }
                    } else {
                        if (mILiveListView != null) {
                            mILiveListView.onLiveList(1, null, true);
                        }
                    }
                } else {
                    if (mILiveListView != null) {
                        mILiveListView.onLiveList(1, null, true);
                    }
                }
                isLoading = false;
            }

            @Override
            public void onFailure(int requestId, int httpStatus, Throwable error) {
                if (mILiveListView != null) {
                    mILiveListView.onLiveList(1, null, false);
                }
                isLoading = false;
            }
        });
    }


    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }
}
