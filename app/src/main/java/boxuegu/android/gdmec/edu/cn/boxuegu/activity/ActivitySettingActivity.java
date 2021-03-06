package boxuegu.android.gdmec.edu.cn.boxuegu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.android.gdmec.edu.cn.boxuegu.ActivityModifyPswActivity;
import boxuegu.android.gdmec.edu.cn.boxuegu.R;
import boxuegu.android.gdmec.edu.cn.boxuegu.utils.AnalysisUtils;

public class ActivitySettingActivity extends Activity implements View.OnClickListener{

    public static ActivitySettingActivity instance=null;
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_save;
    private  RelativeLayout title_bar;
    private RelativeLayout rl_modify_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        instance=null;
        
    }

    private void initView() {
        tv_back= ((TextView) findViewById(R.id.tv_back));
        tv_main_title= ((TextView) findViewById(R.id.tv_main_title));
        title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_modify_psw = (RelativeLayout)findViewById(R.id.rl_modify_psw);
        rl_security_setting = (RelativeLayout)findViewById(R.id.rl_security_setting);
        rl_exit_login = (RelativeLayout)findViewById(R.id.rl_exit_login);

        tv_main_title.setText("设置");
        title_bar.setBackgroundColor(Color.parseColor("#30b4ff"));

        tv_back.setOnClickListener(this);
        rl_modify_psw.setOnClickListener(this);
        rl_security_setting.setOnClickListener(this);
        rl_exit_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                ActivitySettingActivity.this.finish();
                break;
            case R.id.rl_modify_psw:
                //修改密码界面
                Intent intent = new Intent(ActivitySettingActivity.this, ActivityModifyPswActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_security_setting:
                //设置密保
                Intent intent2 = new Intent(ActivitySettingActivity.this,ActivityFindPswActivity.class);
                intent2.putExtra("from","security");
                startActivity(intent2);
                break;
            case R.id.rl_exit_login:
                //退出登录，即清除登录状态
                Toast.makeText(this,"退出登录成功",Toast.LENGTH_SHORT).show();
                AnalysisUtils.clearLoginStatus(this);
                Intent data = new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                finish();
                break;
        }
    }
}
