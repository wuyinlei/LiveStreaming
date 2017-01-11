package ruolan.com.livestreaming.http.response;


import ruolan.com.livestreaming.http.IDontObfuscate;

/**
 * @description: 基础返回数据
 *
 */
public class Response<T>  extends IDontObfuscate {

	public int status;
	public String msg;
	public T data;
	@Override
	public String toString() {
		return "Response [code=" + status + ", msg=" + msg + ", data=" + data
				+ "]";
	}

}

