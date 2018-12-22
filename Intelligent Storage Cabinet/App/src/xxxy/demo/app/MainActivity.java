package xxxy.demo.app;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
				finish();
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		}, 2000);
		
	}
}
