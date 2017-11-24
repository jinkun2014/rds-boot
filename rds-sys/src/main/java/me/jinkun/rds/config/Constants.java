package me.jinkun.rds.config;

/**
 * Created by huguoju on 2016/12/30.
 */
public interface Constants {

    String SESSION_USER_KEY = "user";

    int MAX_FILE_UPLOAD_SIZE = 5242880;
    String MOBILE_NUMBER_SESSION_KEY = "sessionMobileNumber";
    String USER_CODE_SESSION_KEY = "userCode";
    String SESSION_KEY = "sessionId";
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

}