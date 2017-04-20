package cn.duduinchina.android_framework.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dzc on 16/2/17.
 * json 的工具类
 */
public class JsonUtil {

    private static final int JSON_INDENT = 4;

    /**
     * json格式化
     * @param json
     * @return
     */
    public static String jsonFormat(String json) {
        if (TextUtils.isEmpty(json)) {
            return "";
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = null;
                jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                return message;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                return message;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Json 解析失败";

    }

}
