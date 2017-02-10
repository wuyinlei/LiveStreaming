package ruolan.com.livestreaming;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import ruolan.com.livestreaming.base.BaseFragment;

/**
 * @description: 直播列表页面，展示当前直播
 */
public class LiveListFragment extends BaseFragment  {



	public LiveListFragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public static LiveListFragment newInstance(int listType) {
		LiveListFragment fragment = new LiveListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("LISTTYPE", listType);
		fragment.setArguments(bundle);
		return fragment;
	}


	@Override
	protected int getLayoutId() {
		return R.layout.fragment_live_list;
	}

	@Override
	protected void initView(View view) {


	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void initData() {

	}



	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);

	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}






}