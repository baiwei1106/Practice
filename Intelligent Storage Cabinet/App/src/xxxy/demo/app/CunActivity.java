package xxxy.demo.app;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import xxxy.demo.jdbc.BoxDao;
import xxxy.demo.jdbc.MemoryDao;
import xxxy.demo.jdbc.UserDao;
import xxxy.demo.model.Box;
import xxxy.demo.utils.UUIDUtils;

public class CunActivity extends Activity {
	public int flag1,flag2,flag3;
	Button btn_yihao,btn_erhao,btn_sanhao;
	String userid;
	private AlertDialog alertDialog1;
	private AlertDialog alertDialog2;
	private AlertDialog alertDialog3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cun);
		
		Intent intent2=getIntent();
		Bundle bundle2=intent2.getExtras();
		userid=bundle2.getString("userid");
		btn_yihao=(Button)findViewById(R.id.btn_yihao);
		btn_erhao=(Button)findViewById(R.id.btn_erhao);
		btn_sanhao=(Button)findViewById(R.id.btn_sanhao);
		
		initActivity();
		final Handler mHandler1 = new Handler();
		final Runnable mRunnable1 = new Runnable() {
		public void run() {
			btn_sanhao.setEnabled(true);
		}
		};
		btn_sanhao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_sanhao.setEnabled(false);
				mHandler1.postDelayed(mRunnable1, 5000);
				final Runnable runnable = new Runnable() {
					@Override
					public void run() {
						final BoxDao boxdao=new BoxDao();
						final Box box1=boxdao.selectbox("3");
						final UserDao userdao=new UserDao();
						final MemoryDao memorydao=new MemoryDao();
						
						if(box1.getFlag().equals("1")  ||  box1.getFlag().equals("2")  ||  box1.getFlag().equals("3")){
							Toast.makeText(CunActivity.this, "该箱子被占用，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else if(box1.getFlag().equals("4")){
							Toast.makeText(CunActivity.this, "该箱子被维修，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else{
							
							 //创建AlertDialog的构造器的对象
					        AlertDialog.Builder builder=new AlertDialog.Builder(CunActivity.this);
					        //设置构造器标题
					        builder.setTitle("存包");
					        //构造器对应的图标
					        builder.setIcon(R.drawable.ic_launcher);
					        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
					        builder.setMessage("您即将要把包放在"+box1.getBox_id()+"号柜，是否存包？");
					        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
					        builder.setPositiveButton("是", new DialogInterface.OnClickListener()
					        {
					            @Override
					            public void onClick(DialogInterface dialog, int which)
					            {
							boolean b1=boxdao.updateuserid(box1.getBox_id(), userid);
							boolean b2=boxdao.updateflag(box1.getBox_id(), "1");
							boolean b3=userdao.updatebox(userid, box1.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunActivity.this, "存件成功", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunActivity.this, "操作进行中，请稍后", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox("3");

								final Handler mHandler = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){				
											//这里可以进行UI操作，如Toast，Dialog等			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														//这里面进行UI的更新操作
														Intent intent1=new Intent(CunActivity.this,UserInterfaceActivity.class);
														startActivity(intent1);
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
										mHandler.sendMessage(message);
									}
								}, 0, 500);*/
							}else{
								Toast.makeText(CunActivity.this, "存件失败，请联系客服人员", Toast.LENGTH_LONG).show();
								userdao.updatebox(userid, null);
							}
					            }
					        });
					      //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
					        builder.setNegativeButton("否",null);
					        /*//为构造器设置一个比较中性的按钮，比如忽略、稍后提醒等
					        builder.setNeutralButton("稍后提醒",null);*/
					        //利用构造器创建AlertDialog的对象,实现实例化
					        alertDialog1=builder.create();
					        alertDialog1.show();
							
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
		
		
		final Handler mHandler2 = new Handler();
		final Runnable mRunnable2 = new Runnable() {
		public void run() {
			btn_yihao.setEnabled(true);
		}
		};
		btn_yihao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_yihao.setEnabled(false);
				mHandler2.postDelayed(mRunnable2, 5000);
				final Runnable runnable = new Runnable() {
					@Override
					public void run() {
						final BoxDao boxdao=new BoxDao();
						final Box box1=boxdao.selectbox("1");
						final UserDao userdao=new UserDao();
						final MemoryDao memorydao=new MemoryDao();
						
						if(box1.getFlag().equals("1")  ||  box1.getFlag().equals("2")  ||  box1.getFlag().equals("3")){
							Toast.makeText(CunActivity.this, "该箱子被占用，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else if(box1.getFlag().equals("4")){
							Toast.makeText(CunActivity.this, "该箱子被维修，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else{
							
							 //创建AlertDialog的构造器的对象
					        AlertDialog.Builder builder=new AlertDialog.Builder(CunActivity.this);
					        //设置构造器标题
					        builder.setTitle("存包");
					        //构造器对应的图标
					        builder.setIcon(R.drawable.ic_launcher);
					        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
					        builder.setMessage("您即将要把包放在"+box1.getBox_id()+"号柜，是否存包？");
					        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
					        builder.setPositiveButton("是", new DialogInterface.OnClickListener()
					        {
					            @Override
					            public void onClick(DialogInterface dialog, int which)
					            {
							boolean b1=boxdao.updateuserid(box1.getBox_id(), userid);
							boolean b2=boxdao.updateflag(box1.getBox_id(), "1");
							boolean b3=userdao.updatebox(userid, box1.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunActivity.this, "存件成功", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunActivity.this, "操作进行中，请稍后", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox("1");
								
								final Handler mHandler = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){				
											//这里可以进行UI操作，如Toast，Dialog等			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														//这里面进行UI的更新操作
														Intent intent2=new Intent(CunActivity.this,UserInterfaceActivity.class);
														startActivity(intent2);
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
										mHandler.sendMessage(message);
									}
								}, 0, 500);*/
							}else{
								Toast.makeText(CunActivity.this, "存件失败，请联系客服人员", Toast.LENGTH_LONG).show();
								userdao.updatebox(userid, null);
							}
					            }
					        });
					      //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
					        builder.setNegativeButton("否",null);
					        /*//为构造器设置一个比较中性的按钮，比如忽略、稍后提醒等
					        builder.setNeutralButton("稍后提醒",null);*/
					        //利用构造器创建AlertDialog的对象,实现实例化
					        alertDialog2=builder.create();
					        alertDialog2.show();
							
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
		
		
		final Handler mHandler3 = new Handler();
		final Runnable mRunnable3 = new Runnable() {
		public void run() {
			btn_erhao.setEnabled(true);
		}
		};
		btn_erhao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_erhao.setEnabled(false);
				mHandler3.postDelayed(mRunnable3, 5000);
				final Runnable runnable = new Runnable() {
					@Override
					public void run() {
						final BoxDao boxdao=new BoxDao();
						final Box box1=boxdao.selectbox("2");
						final UserDao userdao=new UserDao();
						final MemoryDao memorydao=new MemoryDao();
						
						if(box1.getFlag().equals("1")  ||  box1.getFlag().equals("2")  ||  box1.getFlag().equals("3")){
							Toast.makeText(CunActivity.this, "该箱子被占用，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else if(box1.getFlag().equals("4")){
							Toast.makeText(CunActivity.this, "该箱子被维修，请选择其他箱子", Toast.LENGTH_LONG).show();
						}else{
							
							 //创建AlertDialog的构造器的对象
					        AlertDialog.Builder builder=new AlertDialog.Builder(CunActivity.this);
					        //设置构造器标题
					        builder.setTitle("存包");
					        //构造器对应的图标
					        builder.setIcon(R.drawable.ic_launcher);
					        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
					        builder.setMessage("您即将要把包放在"+box1.getBox_id()+"号柜，是否存包？");
					        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
					        builder.setPositiveButton("是", new DialogInterface.OnClickListener()
					        {
					            @Override
					            public void onClick(DialogInterface dialog, int which)
					            {
							boolean b1=boxdao.updateuserid(box1.getBox_id(), userid);
							boolean b2=boxdao.updateflag(box1.getBox_id(), "1");
							boolean b3=userdao.updatebox(userid, box1.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunActivity.this, "存包成功", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunActivity.this, "操作进行中，请稍后", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox("2");
								
								final Handler mHandler = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){				
											//这里可以进行UI操作，如Toast，Dialog等			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														//这里面进行UI的更新操作
														Intent intent3=new Intent(CunActivity.this,UserInterfaceActivity.class);
														startActivity(intent3);
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
										mHandler.sendMessage(message);
									}
								}, 0, 500);*/
								
							}else{
								Toast.makeText(CunActivity.this, "存包失败，请联系客服人员", Toast.LENGTH_LONG).show();
								userdao.updatebox(userid, null);
							}
					            }
					        });
					      //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
					        builder.setNegativeButton("否",null);
					        /*//为构造器设置一个比较中性的按钮，比如忽略、稍后提醒等
					        builder.setNeutralButton("稍后提醒",null);*/
					        //利用构造器创建AlertDialog的对象,实现实例化
					        alertDialog3=builder.create();
					        alertDialog3.show();
							
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
		
		
		
	}

	private void initActivity() {
		// TODO Auto-generated method stub
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				final BoxDao boxdao=new BoxDao();
				final Box box1=boxdao.selectbox("1");
				final Box box2=boxdao.selectbox("2");
				final Box box3=boxdao.selectbox("3");
				runOnUiThread(new Runnable() {
				      @Override
				      public void run() {
				    	  if(box1.getFlag().equals("0")){
								btn_yihao.setBackgroundColor(Color.parseColor("#00FF00"));
							}else if(box1.getFlag().equals("1")  ||  box1.getFlag().equals("2")  ||  box1.getFlag().equals("3")){
								btn_yihao.setBackgroundColor(Color.parseColor("#FFD700"));
							}else if(box1.getFlag().equals("4")){
								btn_sanhao.setBackgroundColor(Color.parseColor("#FF0000"));
							}
						  
				    	  if(box2.getFlag().equals("0")){
				    		    btn_erhao.setBackgroundColor(Color.parseColor("#00FF00"));
							}else if(box2.getFlag().equals("1")  ||  box2.getFlag().equals("2")  ||  box2.getFlag().equals("3")){
								btn_erhao.setBackgroundColor(Color.parseColor("#FFD700"));
							}else if(box2.getFlag().equals("4")){
								btn_erhao.setBackgroundColor(Color.parseColor("#FF0000"));
							}
				    	  
				    	  if(box3.getFlag().equals("0")){
				    		    btn_sanhao.setBackgroundColor(Color.parseColor("#00FF00"));
							}else if(box3.getFlag().equals("1")  ||  box3.getFlag().equals("2")  ||  box3.getFlag().equals("3")){
								btn_sanhao.setBackgroundColor(Color.parseColor("#FFD700"));
							}else if(box3.getFlag().equals("4")){
								btn_sanhao.setBackgroundColor(Color.parseColor("#FF0000"));
							}
				    	  
				      }
				    });
				
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
