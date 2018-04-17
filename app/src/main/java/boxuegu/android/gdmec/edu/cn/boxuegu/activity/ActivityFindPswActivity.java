package boxuegu.android.gdmec.edu.cn.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.android.gdmec.edu.cn.boxuegu.FindPswActivity;
import boxuegu.android.gdmec.edu.cn.boxuegu.R;
import boxuegu.android.gdmec.edu.cn.boxuegu.utils.AnalysisUtils;
import boxuegu.android.gdmec.edu.cn.boxuegu.utils.MD5Utils;

public class ActivityFindPswActivity extends Activity implements View.OnClickListener {

    private TextView tvUserName;
    private TextView tvResetPsw;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private TextView tv_user_name;
    private EditText et_user_name1;
    private EditText et_validate_name;
    private TextView tv_reset_psw;
    private Button btn_validate;

    private String from;
    private EditText et_validate_reset_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);

        from = getIntent().getStringExtra("from");
        //Log.i("from",from);
        initView();

//        tvUserName = (TextView) findViewById(R.id.tv_user_name);
//        tvResetPsw = (TextView) findViewById(R.id.tv_reset_psw);
//        findViewById(R.id.btn_validate).setOnClickListener(this);
    }

//    private EditText getEtUserName() {
//        return (EditText) findViewById(R.id.et_user_name);
//    }
//
//    private EditText getEtValidateName() {
//        return (EditText) findViewById(R.id.et_validate_name);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_validate:
                //TODO implement
                break;

        }
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        et_user_name1 = (EditText) findViewById(R.id.et_user_name1);
        et_validate_name = (EditText) findViewById(R.id.et_validate_name);
        tv_reset_psw = (TextView) findViewById(R.id.tv_reset_psw);
        btn_validate = (Button) findViewById(R.id.btn_validate);

        et_validate_reset_name = (EditText) findViewById(R.id.et_validate_reset_name);

        if ("security".equals(from)){
            tv_main_title.setText("设置密保");
            btn_validate.setText("设置");
        }else{
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name1.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityFindPswActivity.this.finish();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String validateName= et_validate_name.getText().toString().trim();
                if ("security".equals(from)){
                    if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(ActivityFindPswActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(ActivityFindPswActivity.this, "密保设置成功", Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        ActivityFindPswActivity.this.finish();
                    }
                }else{
                    String userName= et_user_name1.getText().toString().trim();
                    String sp_security=readSecurity(userName);
                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(ActivityFindPswActivity.this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(isExistUserName(userName)){
                        Toast.makeText(ActivityFindPswActivity.this, "您的用户名不存在 ", Toast.LENGTH_SHORT).show();
                        return;
                    }else if(TextUtils.isEmpty(validateName)){
                        Toast.makeText(ActivityFindPswActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    }if (!validateName.equals(sp_security)){
                        Toast.makeText(ActivityFindPswActivity.this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        /*tv_reset_psw.setVisibility(View.VISIBLE);
                        tv_reset_psw.setText("初始密码：123456");
                        savePsw(userName);*/
                        tv_reset_psw.setVisibility(View.VISIBLE);
                        et_validate_reset_name.setVisibility(View.VISIBLE);
                        btn_validate.setText("确认修改");
                        String nPsw=et_validate_reset_name.getText().toString().trim();
                        if (!TextUtils.isEmpty(nPsw)){
                            savePsw(userName,nPsw);
                            Intent data =new Intent();
                            data.putExtra("isLogin",true);
                            setResult(RESULT_OK,data);
                            ActivityFindPswActivity.this.finish();

                        }

                    }
                }
            }
        });
    }


    /*private void submit() {
       *//* // validate
        String name = et_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = et_validate_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something*//*
        String validateName = et_validate_name.getText().toString().trim();
        if ("security".equals(from)){
            if (TextUtils.isEmpty(validateName)){
                Toast.makeText(this,"请输入要验证的名字",Toast.LENGTH_SHORT).show();
                return;
            }else{
                Toast.makeText(this,"密保设置成功",Toast.LENGTH_SHORT).show();
                saveSecurity(validateName);
                ActivityFindPswActivity.this.finish();
            }
        }else{
            String name = et_user_name.getText().toString().trim();
            String sp_security = readSecurity(name);
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
                return;
            }else if (!isExistUserName(name)){
                Toast.makeText(this, "您输入的用户名不存在", Toast.LENGTH_SHORT).show();
                return;
            }else if (TextUtils.isEmpty(validateName)){
                Toast.makeText(this,"请输入要验证的名字",Toast.LENGTH_SHORT).show();
                return;
            }else if (!validateName.equals(sp_security)){
                Toast.makeText(this,"输入的密保不正确",Toast.LENGTH_SHORT).show();
                return;
            }else{
                *//*tv_reset_psw.setVisibility(View.VISIBLE);
                tv_reset_psw.setText("初始密码：123456");
                savePsw(name);*//*
                tv_reset_psw.setVisibility(View.VISIBLE);
                et_validate_reset_name.setVisibility(View.VISIBLE);
                btn_validate.setText("确认修改");
                String nPsw=et_validate_reset_name.getText().toString().trim();
                if (!TextUtils.isEmpty(nPsw)){
                    savePsw(name,nPsw);
                    Intent data =new Intent();
                    data.putExtra("isLogin",true);
                    setResult(RESULT_OK,data);
                   ActivityFindPswActivity.this.finish();

                }
            }
        }

    }*/

    private void savePsw(String name,String newPsw) {
        String md5Psw = MD5Utils.md5(newPsw);
        SharedPreferences sp =getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name,md5Psw);
        editor.commit();
    }

    private boolean isExistUserName(String userName){
        boolean hasUserName = false;
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sharedPreferences.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            hasUserName = true;
        }
        return hasUserName;
    }

    private String readSecurity(String name){
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String security = sharedPreferences.getString(name+"_security","");
        return security;
    }

    private void saveSecurity(String validateName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }
}
