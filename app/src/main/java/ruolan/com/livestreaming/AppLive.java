package ruolan.com.livestreaming;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLivePusher;

import java.util.Locale;

import ruolan.com.livestreaming.logic.IMInitMgr;
import ruolan.com.livestreaming.utils.LogUitils;

/**
 * Created by wuyinlei on 2017/2/11.
 */

public class AppLive extends Application {


    private static final String BUGLY_APPID = "14888762894";

    private static AppLive instance;

    public static AppLive getInstance() {
        if (instance == null){
            synchronized (AppLive.class){
                instance = new AppLive();
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initSDK();

    }

    /**
     * 初始化SDK，包括Bugly，IMSDK，RTMPSDK等
     */
    public void initSDK() {


        //注册crash上报 bugly组件
        int[] sdkVer = TXLivePusher.getSDKVersion(); //这里调用TXLivePlayer.getSDKVersion()也是可以的
        if (sdkVer != null && sdkVer.length >= 3) {
            if (sdkVer[0] > 0 && sdkVer[1] > 0) {
                //启动bugly组件，bugly组件为腾讯提供的用于crash上报和分析的开放组件，如果您不需要该组件，可以自行移除
                CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
                strategy.setAppVersion(String.format(Locale.US, "%d.%d.%d",sdkVer[0],sdkVer[1],sdkVer[2]));
                CrashReport.initCrashReport(getApplicationContext(), BUGLY_APPID, true, strategy);
            }
        }

        IMInitMgr.init(getApplicationContext());

        //设置rtmpsdk log回调，将log保存到文件
        TXLiveBase.getInstance().listener = new LogUitils(getApplicationContext());

        //初始化httpengine
//        HttpEngine.getInstance().initContext(getApplicationContext());

        Log.w("LogUitils","app init sdk");
    }

}
