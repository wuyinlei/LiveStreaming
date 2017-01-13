package ruolan.com.livestreaming.http.request;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import ruolan.com.livestreaming.http.response.Response;

/**
 * 手机号码注册请求
 */
public class PhoneRegisterRequest extends IRequest {
    public PhoneRegisterRequest(int requestId, String mobile, String verifyCode) {
        mRequestId = requestId;
        mParams.put("action", "phoneRegister");
        mParams.put("mobile", mobile);
        mParams.put("verifyCode", verifyCode);
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
