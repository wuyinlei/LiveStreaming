package ruolan.com.livestreaming.http.request;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.data.UserInfo;
import ruolan.com.livestreaming.http.response.Response;

/**
 * 普通用户登录请求
 */

public class LoginRequest extends IRequest {

    public LoginRequest(int requestId,String userName,String password) {
        mRequestId = requestId;
        mParams.put("action", "login");
        mParams.put("userName", userName);
        mParams.put("password", password);
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
