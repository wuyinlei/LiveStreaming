package ruolan.com.livestreaming;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TabHost;

import ruolan.com.livestreaming.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private FragmentTabHost mFragmentTabHost;

    private final Class mTabFangment[] = {LiveMainFragment.class, LiveListFragment.class, UserInfoFragment.class};
    private int mTabIcons[] = {R.drawable.tab_live_selector, R.drawable.tab_room_selector, R.drawable.tab_me_selector};
    private String mTabNames[] = {"video", "publish", "user"};


    @Override
    protected void setListener() {
        mFragmentTabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublishSettingActivity.invoke(MainActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        int fragmentCount = mTabFangment.length;
        TabHost.TabSpec mTabSpec;
        for (int i = 0; i < fragmentCount; i++) {
            mTabSpec = mFragmentTabHost.newTabSpec(mTabNames[i]).setIndicator(getTabItemView(i));
            mFragmentTabHost.addTab(mTabSpec,mTabFangment[i],null);
        }
    }

    private View getTabItemView(int index) {
        View view;
        if (index % 2 == 0) {
            view = LayoutInflater.from(this).inflate(R.layout.tab_button1, null);
        } else {
            view = LayoutInflater.from(this).inflate(R.layout.tab_button, null);
        }
        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
        icon.setImageResource(mTabIcons[index]);
        return view;
    }

    @Override
    protected void initView() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.contentPanel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public static void invoke(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
