package ruolan.com.livestreaming.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;

import ruolan.com.livestreaming.R;
import ruolan.com.livestreaming.base.BaseActivity;
import ruolan.com.livestreaming.base.BaseFragment;
import ruolan.com.livestreaming.presenter.LiveMainPresenter;
import ruolan.com.livestreaming.widget.PagerSlidingTabStrip;


public class LiveMainFragment extends BaseFragment {

	LiveMainPresenter mLiveMainPresenter;
	private ViewPager mViewPager;
	private PagerSlidingTabStrip mTabStrip;


	@Override
	protected int getLayoutId() {
		return R.layout.fragment_live_main;
	}

	@Override
	protected void initView(View view) {
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
		mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tab_strip);
		mTabStrip.setTextColorResource(R.color.white);
		mTabStrip.setIndicatorColorResource(R.color.white);
		mTabStrip.setDividerColor(Color.TRANSPARENT);
		mTabStrip.setTextSelectedColorResource(R.color.white);
		mTabStrip.setTextSize(getResources().getDimensionPixelSize(R.dimen.h6));
		mTabStrip.setTextSelectedSize(getResources().getDimensionPixelSize(R.dimen.h10));
		mTabStrip.setUnderlineHeight(1);
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void initData() {
		mLiveMainPresenter = new LiveMainPresenter((BaseActivity) getActivity());
		mViewPager.setAdapter(mLiveMainPresenter.getAdapter());
		mTabStrip.setViewPager(mViewPager);
	}

}
