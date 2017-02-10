package ruolan.com.livestreaming.utils;

/**
 * @description: 静态函数
 */
public class Constants {

//	public static final int PAGESIZE = 10; //测试加载更多
	public static final int PAGESIZE = 20;

	//云通信服务相关配置
	public static int IMSDK_ACCOUNT_TYPE = 9744;
	public static int IMSDK_APPID = 1400022288;

	/**
	 * 常量字符串
	 */
	public static final String USER_INFO = "user_info";
	public static final String USER_ID = "user_id";
	public static final String USER_SIG = "user_sig";
	public static final String USER_NICK = "user_nick";
	public static final String USER_SIGN = "user_sign";
	public static final String USER_HEADPIC = "user_headpic";
	public static final String USER_COVER = "user_cover";
	public static final String USER_LOC = "user_location";
	public static final String SVR_RETURN_CODE = "status";
	public static final String SVR_RETURN_MSG = "returnMsg";
	public static final String SVR_RETURN_DATA = "returnData";

	//主播退出广播字段
	public static final String EXIT_APP = "EXIT_APP";

	public static final int USER_INFO_MAXLEN = 20;
	public static final int TV_TITLE_MAX_LEN = 30;
	public static final int NICKNAME_MAX_LEN = 20;

	//直播类型
	public static final int RECORD_TYPE_CAMERA = 991;
	public static final int RECORD_TYPE_SCREEN = 992;


	//码率
	public static final int BITRATE_SLOW = 900;
	public static final int BITRATE_NORMAL = 1200;
	public static final int BITRATE_FAST = 1600;

	//直播端右下角listview显示type
	public static final int AVIMCMD_TEXT_TYPE = 0;
	public static final int AVIMCMD_EnterLive = 1;          // 用户加入直播, Group消息  1
	public static final int AVIMCMD_ExitLive = 2;         // 用户退出直播, Group消息  2
	public static final int AVIMCMD_Praise = 3;           // 点赞消息, Demo中使用Group消息  3
	public static final int AVIMCMD_Host_Leave = 4;         // 主播离开, Group消息  4
	public static final int AVIMCMD_Host_Back = 5;         // 主播回来, Demo中使用Group消息  5
	public static final int AVIMCMD_ENTERLIVE_FILL_DATA = 6;  // 马甲 假数据 用户加入群 6
	public static final int AVIMCMD_SET_SILENCE = 7;  // 禁言消息、设置场控消息  7 // userId，userImage,nickName,type //1、禁言，2、场控 3、取消场控
	public static final int AVIMCMD_FOLLOW = 8;           // 关注    8
	public static final int AVIMCMD_Praise_first = 9;         // 观众第一次点心时发送该消息  9
	public static final int AVIMCMD_SYSTEM_NOTIFY = 10;         // 官方提示消息   10
	public static final int AVIMCMD_ZHONGSOUBI = 11;         // 打赏消息   11
	public static final int AVIMCMD_GIFT = 12;         // 礼物消息   12
	public static final int AVIMCMD_SYNCHRONIZING_INFORMATION = 13;         // 同步信息13  :  watchCount,timeSpan,charmCount
	public static final int AVIMCMD_SEND_MESSAGE = 14;         // 马甲发送消息 14  :  userId,nickname,message
	public static final int AVIMCMD_ROOM_EXIT = 15;         // 强制游客解散房间 roomId,liveId
	public static final int AVIMCMD_USER_OPEAR = 16;      //  显示消息 userId,nickname,liveId,type(1关注,2点赞第一次，3点赞,4踢人，5禁言)
	public static final int AVIMCMD_SHOW_INFO = 17;       // 显示消息  message ,color ,type （1 评论列中显示， 2 评论之上显示）
	public static final int AVIMCMD_WITH_USER_ROLE = 18;       // 文本消息带有角色 message, role
	public static final int AVIMCMD_UPDATE_LIVE_STATE = 19;       // 直播回调状态消息。
	public static final int AVIMCMD_DANMU = 19;       // 直播回调状态消息。
	public static final int MSG_SHOW_ADMIN = 71;       // 场控相关评论样式


	public static final int LOCATION_PERMISSION_REQ_CODE = 1;
	public static final int WRITE_PERMISSION_REQ_CODE = 2;

	public static final String PUBLISH_URL = "publish_url";
	public static final String ROOM_ID = "room_id";
	public static final String ROOM_TITLE = "room_title";
	public static final String COVER_PIC = "cover_pic";
	public static final String BITRATE = "bitrate";
	public static final String GROUP_ID = "group_id";
	public static final String PLAY_URL = "play_url";
	public static final String PLAY_TYPE = "play_type";
	public static final String PUSHER_AVATAR = "pusher_avatar";
	public static final String PUSHER_ID = "pusher_id";
	public static final String PUSHER_NAME = "pusher_name";
	public static final String MEMBER_COUNT = "member_count";
	public static final String HEART_COUNT = "heart_count";
	public static final String FILE_ID = "file_id";
	public static final String ACTIVITY_RESULT = "activity_result";
	public static final String LIVEID = "live_id";

	public static final String CMD_KEY = "userAction";
	public static final String DANMU_TEXT = "actionParam";

	public static final String NOTIFY_QUERY_USERINFO_RESULT = "notify_query_userinfo_result";


	//ERROR CODE TYPE
	public static final int ERROR_GROUP_NOT_EXIT = 10010;
	public static final int ERROR_QALSDK_NOT_INIT = 6013;
	public static final int ERROR_JOIN_GROUP_ERROR = 10015;
	public static final int SERVER_NOT_RESPONSE_CREATE_ROOM = 1002;
	public static final int NO_LOGIN_CACHE = 1265;


	//网络类型
	public static final int NETTYPE_WIFI = 0;
	public static final int NETTYPE_NONE = 1;
	public static final int NETTYPE_2G = 2;
	public static final int NETTYPE_3G = 3;
	public static final int NETTYPE_4G = 4;
}
