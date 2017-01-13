package ruolan.com.livestreaming.http.request;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.response.Response;

/**
 * 验证码请求
 */

public class VerifyCodeRequest extends IRequest {

    public VerifyCodeRequest(int requestId, String mobile) {
        mRequestId = requestId;
        mParams.put("action", "verifyCode");
        mParams.put("mobile", mobile);
    }

    @Override
    public String getUrl() {
        return getHost() + "User";
    }

    @Override
    public Type getParserType() {
        return new TypeToken<Response>() {
        }.getType();
    }
}
