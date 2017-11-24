package ${EntityInfo.packageInfo.resp};

/**
 * @Description:响应基类 <br/>
 * @Autor: Created by ${EntityInfo.user} on ${EntityInfo.createTime}.
 */
public class RespResult<T> {
    public final static int CODE_OK = 200;
    public final static int CODE_FAIL = 300;

    //业务状态
    protected int code;
    //业务状态消息
    protected String msg;
    //业务数据
    protected T data;

    public RespResult() {
    }

    public RespResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static RespResult ok(String msg) {
        return ok(msg, null);
    }

    public static RespResult ok(String msg, Object data) {
        return new RespResult(RespResult.CODE_OK, msg, data);
    }

    public static RespResult fail(String msg) {
        return fail(msg, null);
    }

    public static RespResult fail(String msg, Object data) {
        return new RespResult(RespResult.CODE_FAIL, msg, data);
    }
}
