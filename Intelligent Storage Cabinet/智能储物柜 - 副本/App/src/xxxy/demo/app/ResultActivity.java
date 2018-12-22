package xxxy.demo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		String usl=bundle.getString("payUrl");
		TextView textview=(TextView)findViewById(R.id.tv_test);
		textview.setText(usl);
	}
}
