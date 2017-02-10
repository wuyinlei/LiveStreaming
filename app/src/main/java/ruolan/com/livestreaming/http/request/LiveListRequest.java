package ruolan.com.livestreaming.http.request;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.response.ResList;
import ruolan.com.livestreaming.http.response.Response;
import ruolan.com.livestreaming.model.LiveInfo;

/**
 * @description: 直播列表请求
 *
 */
public class LiveListRequest extends IRequest {

	public LiveListRequest(int requestId, String userId , int pageIndex, int pageSize) {
		mRequestId = requestId;
//		mParams.put("action","liveList");
		mParams.put("action","liveListTest");//测试加载更多
		mParams.put("userId",userId);
		mParams.put("pageIndex", pageIndex);
		mParams.put("pageSize", pageSize);
	}

	@Override
	public String getUrl() {
		return getHost() + "Live";
	}

	@Override
	public Type getParserType() {
		return new TypeToken<Response<ResList<LiveInfo>>>() {}.getType();
	}
}
