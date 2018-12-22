package xxxy.demo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {
	Button register_btn,login_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		register_btn=(Button)findViewById(R.id.btn_register);
		login_btn=(Button)findViewById(R.id.btn_login);
		
		
		final Handler mHandler1 = new Handler();
		final Runnable mRunnable1 = new Runnable() {
		public void run() {
			register_btn.setEnabled(true);
		}
		};
		register_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register_btn.setEnabled(false);
				mHandler1.postDelayed(mRunnable1, 5000);
				Intent intent=new Intent(WelcomeActivity.this,RegisterActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		
		final Handler mHandler2 = new Handler();
		final Runnable mRunnable2 = new Runnable() {
		public void run() {
			login_btn.setEnabled(true);
		}
		};
		login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login_btn.setEnabled(false);
				mHandler2.postDelayed(mRunnable2, 5000);
				Intent intent1=new Intent(WelcomeActivity.this,LoginActivity.class);
				startActivity(intent1);
				finish();
			}
		});
		
		

	}
}
