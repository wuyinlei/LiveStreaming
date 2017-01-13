package ruolan.com.livestreaming.http.request;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.data.UserInfo;
import ruolan.com.livestreaming.http.response.Response;

/**
 * 普通注册请求
 */

public class RegisterRequest extends IRequest {

    public RegisterRequest(int requestId, String userName, String password) {
        mRequestId = requestId;
        mParams.put("action", "register");
        mParams.put("userName", userName);
        mParams.put("password", password);
        mParams.put("rePassword", password);
    }

    @Override
    public String getUrl() {
        return getHost() + "User";
    }

    @Override
    public Type getParserType() {
        return new TypeToken<Response<UserInfo>>() {
        }.getType();
    }
}
