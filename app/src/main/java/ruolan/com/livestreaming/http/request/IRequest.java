package ruolan.com.livestreaming.http.request;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.IDontObfuscate;


public abstract class IRequest extends IDontObfuscate {

	private boolean DEBUG = false;


//	public static final String HOST_DEBUG = "http://192.168.31.92:8088/Api/";
	public static final String HOST_DEBUG = "http://192.168.31.92:8094/Api/";
	public static final String HOST_PUBLIC = "http://live.demo.cniao5.com/Api/";


	protected RequestParams mParams = new RequestParams();
	public int mRequestId = 0;
	protected int mDraw = 0;
	protected final static Gson mGson = new Gson();

	public IRequest() {
	}

	/**
	 * 接口请求参数
	 *
	 * @return
	 * @throws
	 * @Title:getParams
	 * @return:RequestParams
	 */
	public RequestParams getParams() {
		return mParams;
	}

	/**
	 * http直接post数据
	 *
	 * @return
	 * @throws
	 * @Description:
	 * @Title:getPostData
	 * @return:String
	 */
	public String getPostData() {
		return null;
	}

	/**
	 * 设置接口请求唯一标识
	 *
	 * @param requestId
	 * @throws
	 * @Description:
	 * @Title:setRequestId
	 * @return:void
	 */
	public void setRequestId(int requestId) {
		mRequestId = requestId;
	}

	/**
	 * 返回请求接口唯一标识
	 *
	 * @return
	 * @throws
	 * @Description:
	 * @Title:getRequestId
	 * @return:int
	 */
	public int getRequestId() {
		return mRequestId;
	}

	/**
	 * @return
	 * @throws
	 * @Description:当前接口的url地址
	 * @Title:getUrl
	 * @return:String
	 */
	public abstract String getUrl();

	/**
	 * 获取解析类型
	 *
	 * @return
	 * @throws
	 * @Description:
	 * @Title:getParserType
	 * @return:Type
	 */
	public abstract Type getParserType();

	/**
	 * @return
	 * @throws
	 * @Description:返回服务器接口地址
	 * @Title:getHost
	 * @return:String
	 */
	protected String getHost() {
		return DEBUG ? HOST_DEBUG : HOST_PUBLIC;
	}


	@Override
	public String toString() {
		return "IRequest [DEBUG=" + DEBUG
				+ ", mParams=" + mParams + ", mRequestId=" + mRequestId
				+ ", mDraw=" + mDraw + "]";
	}

	public boolean isCache() {
		return false;
	}


}