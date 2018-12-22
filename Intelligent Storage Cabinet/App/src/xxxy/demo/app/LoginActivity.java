package xxxy.demo.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import xxxy.demo.jdbc.UserDao;
import xxxy.demo.model.Users;

public class LoginActivity extends Activity {
	private EditText et_login_phone,et_login_pwd;
	private Button btn_LoginActivity_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		et_login_phone=(EditText)findViewById(R.id.et_login_phone);
		et_login_pwd=(EditText)findViewById(R.id.et_login_pwd);
		btn_LoginActivity_login=(Button)findViewById(R.id.btn_LoginActivity_login);
		
		
		final Handler mHandler1 = new Handler();
		final Runnable mRunnable1 = new Runnable() {
		public void run() {
			btn_LoginActivity_login.setEnabled(true);
		}
		};
		btn_LoginActivity_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_LoginActivity_login.setEnabled(false);
				mHandler1.postDelayed(mRunnable1, 5000);
				if(et_login_phone.getText().toString().trim().equals("")){
					Toast.makeText(LoginActivity.this, "电话号码不能为空", Toast.LENGTH_LONG).show();
				}else if(et_login_pwd.getText().toString().trim().equals("")){
					Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
				}else{
					final Runnable runnable = new Runnable() {
							@Override
							public void run() {
								UserDao userdao=new UserDao();
								Users users=userdao.login(et_login_phone.getText().toString().trim(),et_login_pwd.getText().toString().trim(), getApplicationContext());
								if(users.getUsername()!=null){
									Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
									Users users1=userdao.getusername(et_login_phone.getText().toString().trim());
									Bundle bundle = new Bundle();
						            Intent intent=new Intent(LoginActivity.this,UserInterfaceActivity.class);
						            bundle.putString("id", users1.getId());
						            bundle.putString("username",users1.getUsername());
						            bundle.putString("userpwd", users1.getUserpwd());
						            bundle.putString("phone",users1.getPhone());
						            bundle.putString("box_id", users1.getBox_id());
						            bundle.putString("crdate", users1.getCrdate());
						            intent.putExtras(bundle);
						            startActivity(intent);
								}else{
									Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_LONG).show();
								}
							}

							
					};
					
					
					new Thread(){

						@Override
						public void run() {
							Looper.prepare();
							new Handler().post(runnable);//在子线程中直接去new 一个handler
							Looper.loop();
							//这种情况下，Runnable对象是运行在子线程中的，可以进行联网操作，但是不能更新UI
							
						}
					}.start();
					
					
					
					
					
					
				}
			}
		});
		
	}
	
	
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches()+"---");
		return m.matches();
		}
}
