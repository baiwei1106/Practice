package xxxy.demo.app;

import java.util.Timer;
import java.util.TimerTask;

import com.karics.library.zxing.android.CaptureActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import xxxy.demo.jdbc.BoxDao;
import xxxy.demo.jdbc.MemoryDao;
import xxxy.demo.jdbc.UserDao;
import xxxy.demo.model.Box;
import xxxy.demo.utils.UUIDUtils;

public class CunChangeActivity extends Activity {
	protected static final int REQUEST_CODE_SCAN = 0;
	Button btn_shoudongcun,btn_erweimacun;
	String id;
	private AlertDialog alertDialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cun_change);
		
		btn_shoudongcun=(Button)findViewById(R.id.btn_shoudongcun);
		btn_erweimacun=(Button)findViewById(R.id.btn_erweimacun);
		
		Intent intent1=getIntent();
		Bundle bundle=intent1.getExtras();
		id=bundle.getString("userid");
		
		
		final Handler mHandler1 = new Handler();
		final Runnable mRunnable1 = new Runnable() {
		public void run() {
			btn_shoudongcun.setEnabled(true);
		}
		};
		btn_shoudongcun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_shoudongcun.setEnabled(false);
				mHandler1.postDelayed(mRunnable1, 5000);
				Bundle bundle1=new Bundle();
				Intent intent =new Intent(CunChangeActivity.this,CunActivity.class);
				bundle1.putString("userid", id);
				intent.putExtras(bundle1);
				startActivity(intent);
				finish();
			}
		});
		
		
		final Handler mHandler2 = new Handler();
		final Runnable mRunnable2 = new Runnable() {
		public void run() {
			btn_erweimacun.setEnabled(true);
		}
		};
		
		btn_erweimacun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_erweimacun.setEnabled(false);
				mHandler2.postDelayed(mRunnable2, 5000);
				Intent intent1=new Intent(CunChangeActivity.this,CaptureActivity.class);
				startActivityForResult(intent1, REQUEST_CODE_SCAN);
			}
		});
		
		
		
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_SCAN ) {
			if (data != null) {
				String content = data.getStringExtra("codedContent");
				
				final String boxid=content.substring(content.length()-1, content.length());
				
				final Runnable runnable = new Runnable() {
					@Override
					public void run() {
				final BoxDao boxdao=new BoxDao();
				final Box box=boxdao.selectbox(boxid);
				final UserDao userdao=new UserDao();
				final MemoryDao memorydao=new MemoryDao();
				 //创建AlertDialog的构造器的对象
		        AlertDialog.Builder builder=new AlertDialog.Builder(CunChangeActivity.this);
		        //设置构造器标题
		        builder.setTitle("存包");
		        //构造器对应的图标
		        builder.setIcon(R.drawable.ic_launcher);
		        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
		        builder.setMessage("您即将要把包放在"+boxid+"号柜，是否存包？");
		        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
		        builder.setPositiveButton("是", new DialogInterface.OnClickListener()
		        {
		            @Override
		            public void onClick(DialogInterface dialog, int which)
		            {
		            	if(box.getFlag().equals("1")  ||  box.getFlag().equals("2")  ||  box.getFlag().equals("3")){
							Toast.makeText(CunChangeActivity.this, "该箱子被占用，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else if(box.getFlag().equals("4")){
							Toast.makeText(CunChangeActivity.this, "该箱子被维修，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else{
							boolean b1=boxdao.updateuserid(box.getBox_id(), id);
							boolean b2=boxdao.updateflag(box.getBox_id(), "1");
							boolean b3=userdao.updatebox(id, box.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunChangeActivity.this, "存件成功", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunChangeActivity.this, "操作进行中，请稍后", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox(boxid);
								
								final Handler mHandler1 = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){	
											//这里可以进行UI操作，如Toast，Dialog等			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														Intent intent=new Intent(CunChangeActivity.this,UserInterfaceActivity.class);
														startActivity(intent);	
													}
												});
												
											}
											}		
										}		
								};
								Timer timer = new Timer();
								timer.schedule(new TimerTask() {
									@Override			
									public void run() {
										// TODO Auto-generated method stub
										Message message=new Message();
										message.what=0;
										mHandler1.sendMessage(message);
									}
								}, 0, 500);*/
							}else{
								Toast.makeText(getApplicationContext()," " + b1 + b2 + b3, Toast.LENGTH_LONG).show();
								//Toast.makeText(CunChangeActivity.this, "存件失败，请联系客服人员", Toast.LENGTH_LONG).show();
								userdao.updatebox(id, null);

							}
						}
		            	
		            }
		        });
		        //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
		        builder.setNegativeButton("否",null);
		        /*//为构造器设置一个比较中性的按钮，比如忽略、稍后提醒等
		        builder.setNeutralButton("稍后提醒",null);*/
		        //利用构造器创建AlertDialog的对象,实现实例化
		        alertDialog=builder.create();
		        alertDialog.show();
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
		};
		
		
		
		
		

	}
}
