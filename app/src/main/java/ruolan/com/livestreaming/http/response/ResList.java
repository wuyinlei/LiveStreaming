package ruolan.com.livestreaming.http.response;



import java.util.List;

import ruolan.com.livestreaming.http.IDontObfuscate;

/**
 * @description: 列表返回数据
 */
public class ResList<T>  extends IDontObfuscate {

	public int currentPage;
	public int totalRow;
	public int totalPage;

	public List<T> items;

	@Override
	public String toString() {
		return "ResList{" +
				"currentPage=" + currentPage +
				", totalRow=" + totalRow +
				", totalPage=" + totalPage +
				", items=" + items +
				'}';
	}
}
