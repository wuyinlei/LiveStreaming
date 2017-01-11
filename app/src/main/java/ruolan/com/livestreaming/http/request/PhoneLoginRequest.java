package ruolan.com.livestreaming.http.request;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.data.UserInfo;
import ruolan.com.livestreaming.http.response.Response;

/**
 * Created by wuyinlei on 2017/1/11.
 */

public class PhoneLoginRequest extends IRequest {

    public PhoneLoginRequest(int requestId,String userName,String password) {
        mRequestId = requestId;
        mParams.put("action", "phoneLogin");
        mParams.put("mobile", userName);
        mParams.put("verifyCode", password);
    }

    @Override
    public String getUrl() {
        return getHost() + "User";
    }

    @Override
    public Type getParserType() {
        return new TypeToken<Response<UserInfo>>(){}.getType();
    }
}
