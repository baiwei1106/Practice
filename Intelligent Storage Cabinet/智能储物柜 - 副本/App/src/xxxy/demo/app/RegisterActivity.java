package xxxy.demo.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import xxxy.demo.jdbc.UserDao;
import xxxy.demo.utils.UUIDUtils;

public class RegisterActivity extends Activity {
	
	EditText et_name,et_phone,et_pwd;
	Button btn_RegisterActivity_register;
	int flag=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		register();
	}

	private void register() {
		// TODO Auto-generated method stub
		et_name=(EditText)findViewById(R.id.et_name);
		et_phone=(EditText)findViewById(R.id.et_phone);
		et_pwd=(EditText)findViewById(R.id.et_pwd);
		btn_RegisterActivity_register=(Button)findViewById(R.id.btn_RegisterActivity_register);
		
		final Handler mHandler = new Handler();
		final Runnable mRunnable = new Runnable() {
		public void run() {
		btn_RegisterActivity_register.setEnabled(true);
		}
		};
		
		btn_RegisterActivity_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_RegisterActivity_register.setEnabled(false);
				mHandler.postDelayed(mRunnable, 5000);
				if(et_name.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "用户名不能为空！", Toast.LENGTH_LONG).show();
				}else if(et_pwd.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
				}else if(et_phone.getText().toString().trim().equals("")){
					Toast.makeText(RegisterActivity.this, "手机号不能为空！", Toast.LENGTH_LONG).show();
				}else{
					
					 final Runnable runnable = new Runnable() {
						@Override
						public void run() {
							//执行耗时操作
							//Long phone=Long.parseLong(et_phone.getText().toString().trim());
							UserDao userdao=new UserDao();
							if(!userdao.isselectbyphone(et_phone.getText().toString().trim())){
							boolean t=userdao.insertuser(UUIDUtils.getUUID(), et_name.getText().toString(), et_pwd.getText().toString(), et_phone.getText().toString().trim());
							if(t){
								Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
								runOnUiThread(new Runnable() {
								      @Override
								      public void run() {
								      }
								    });
								Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
								startActivity(intent);
								}else{
									Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();
								}
							}else{
								Toast.makeText(RegisterActivity.this, "该手机号已存在，请重新输入", Toast.LENGTH_LONG).show();
								flag=0;
							}
							}
						};
					if(flag==0){
						et_name.setText("");
						et_phone.setText("");
						et_pwd.setText("");
					}
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

