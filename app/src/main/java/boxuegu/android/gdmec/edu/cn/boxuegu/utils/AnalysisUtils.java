package boxuegu.android.gdmec.edu.cn.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shinelon on 2018/4/1.
 */

public class AnalysisUtils{
    //读取用户名
    public static String readloginUserName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("loginUserName", "");
        return userName;
    }
    //读取登录状态
    public static boolean readLoginStatus(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("loginUserName", false);
        return isLogin;
    }
    //清除登录状态
    public static void clearLoginStatus(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();

    }
}
