package xxxy.demo.app;

import java.util.Timer;
import java.util.TimerTask;

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
import android.widget.TextView;
import android.widget.Toast;
import xxxy.demo.jdbc.BoxDao;
import xxxy.demo.jdbc.MemoryDao;
import xxxy.demo.jdbc.UserDao;
import xxxy.demo.model.Box;
import xxxy.demo.model.Users;
import xxxy.demo.utils.UUIDUtils;

public class UserInterfaceActivity extends Activity {
	TextView tv_user_name;
	Button btn_cun,btn_qu;
	String username,id,userpwd,phone,box_id,crdate;
	private AlertDialog alertDialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_interface);
		Intent inetnt=getIntent();
		Bundle bundle=inetnt.getExtras();
		id=bundle.getString("id");
		username=bundle.getString("username");
		userpwd=bundle.getString("userpwd");
		phone=bundle.getString("phone");
		box_id=bundle.getString("box_id");
		crdate=bundle.getString("crdate");
		
		tv_user_name=(TextView)findViewById(R.id.tv_user_name);
		tv_user_name.setText("尊敬的"+username+",欢迎您");
		btn_cun=(Button)findViewById(R.id.btn_cun);
		btn_qu=(Button)findViewById(R.id.btn_qu);
		
		
		final Handler mHandler1 = new Handler();
		final Runnable mRunnable1 = new Runnable() {
		public void run() {
			btn_cun.setEnabled(true);
		}
		};
		btn_cun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_cun.setEnabled(false);
				mHandler1.postDelayed(mRunnable1, 5000);
				final Runnable runnable = new Runnable() {
					@Override
					public void run() {
						UserDao userdao=new UserDao();
						Users users=userdao.iscun(id);
						if(users.getBox_id()!=null){
							Toast.makeText(UserInterfaceActivity.this, "您还有物件没有取出，请取出后再操作", Toast.LENGTH_LONG).show();
						}else{
						Bundle bundle = new Bundle();
						Intent intent=new Intent(UserInterfaceActivity.this,CunChangeActivity.class);
						bundle.putString("userid", id);
						intent.putExtras(bundle);
						startActivity(intent);
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
		});
		
		final Handler mHandler = new Handler();
		final Runnable mRunnable = new Runnable() {
		public void run() {
			btn_qu.setEnabled(true);
		}
		};
		btn_qu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final Runnable runnable = new Runnable() {
					@Override
					public void run() {
				UserDao userdao=new UserDao();
				Users users=userdao.iscun(id);
				if(users.getBox_id()==null){
					Toast.makeText(UserInterfaceActivity.this, "您还没有存包，请存后再取", Toast.LENGTH_LONG).show();
				}else{
					runOnUiThread(new Runnable() {
					      @Override
					      public void run() {
					      //这里面进行UI的更新操作
					    	  btn_qu.setEnabled(false);
					    	  mHandler.postDelayed(mRunnable, 5000);
					      }
					    });
					initDialog();
				}
					}

					public void initDialog() {
						
						final BoxDao boxdao=new BoxDao();
						final Box box=boxdao.selectboxbyid(id);
						 //创建AlertDialog的构造器的对象
				        AlertDialog.Builder builder=new AlertDialog.Builder(UserInterfaceActivity.this);
				        //设置构造器标题
				        builder.setTitle("取包");
				        //构造器对应的图标
				        builder.setIcon(R.drawable.ic_launcher);
				        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
				        builder.setMessage("您的包放在"+box.getBox_id()+"号柜，是否取包？");
				        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
				        builder.setPositiveButton("是", new DialogInterface.OnClickListener()
				        {
				            @Override
				            public void onClick(DialogInterface dialog, int which)
				            {
				            	boolean flag1=boxdao.updateflag(box.getBox_id(),"3");
				            	boolean flag4=boxdao.updateuserid(box.getBox_id(), null);
				            	final UserDao userdao=new UserDao();
				            	boolean flag2=userdao.updatebox(id, null);
								MemoryDao me=new MemoryDao();
								if(flag1  &&  flag2  &&  flag4){
									Toast.makeText(UserInterfaceActivity.this, "取件成功，欢迎下次使用", Toast.LENGTH_LONG).show();
									/*Toast.makeText(UserInterfaceActivity.this, "操作进行中，请稍后", Toast.LENGTH_LONG).show();
									Box box3=boxdao.selectbox(box.getBox_id());
									
									final Handler mHandler1 = new Handler(){
										@Override		
										public void handleMessage(Message msg) {
											// TODO Auto-generated method stub			
											if(msg.what == 0){				
												//这里可以进行UI操作，如Toast，Dialog等			
												if(box.getFlag().equals("0")){
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
									Toast.makeText(UserInterfaceActivity.this, "取件失败，请联系客服人员", Toast.LENGTH_LONG).show();
									userdao.updatebox(id, box.getBox_id());
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
		});
		

	}

	
}
