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
		tv_user_name.setText("�𾴵�"+username+",��ӭ��");
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
							Toast.makeText(UserInterfaceActivity.this, "���������û��ȡ������ȡ�����ٲ���", Toast.LENGTH_LONG).show();
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
						new Handler().post(runnable);//�����߳���ֱ��ȥnew һ��handler
						Looper.loop();
						//��������£�Runnable���������������߳��еģ����Խ����������������ǲ��ܸ���UI
						
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
					Toast.makeText(UserInterfaceActivity.this, "����û�д����������ȡ", Toast.LENGTH_LONG).show();
				}else{
					runOnUiThread(new Runnable() {
					      @Override
					      public void run() {
					      //���������UI�ĸ��²���
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
						 //����AlertDialog�Ĺ������Ķ���
				        AlertDialog.Builder builder=new AlertDialog.Builder(UserInterfaceActivity.this);
				        //���ù���������
				        builder.setTitle("ȡ��");
				        //��������Ӧ��ͼ��
				        builder.setIcon(R.drawable.ic_launcher);
				        //����������,Ϊ�Ի��������ı���(֮�����б��������)
				        builder.setMessage("���İ�����"+box.getBox_id()+"�Ź��Ƿ�ȡ����");
				        //Ϊ����������ȷ����ť,��һ������Ϊ��ť��ʾ���ı���Ϣ���ڶ�������Ϊ�����ļ����¼����������ڲ���ʵ��
				        builder.setPositiveButton("��", new DialogInterface.OnClickListener()
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
									Toast.makeText(UserInterfaceActivity.this, "ȡ���ɹ�����ӭ�´�ʹ��", Toast.LENGTH_LONG).show();
									/*Toast.makeText(UserInterfaceActivity.this, "���������У����Ժ�", Toast.LENGTH_LONG).show();
									Box box3=boxdao.selectbox(box.getBox_id());
									
									final Handler mHandler1 = new Handler(){
										@Override		
										public void handleMessage(Message msg) {
											// TODO Auto-generated method stub			
											if(msg.what == 0){				
												//������Խ���UI��������Toast��Dialog��			
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
									Toast.makeText(UserInterfaceActivity.this, "ȡ��ʧ�ܣ�����ϵ�ͷ���Ա", Toast.LENGTH_LONG).show();
									userdao.updatebox(id, box.getBox_id());
								}
				            }
				        });
				        //Ϊ����������ȡ����ť,�������ť����Ҫ���κβ�����ֱ��Ϊ�ڶ���������ֵnull
				        builder.setNegativeButton("��",null);
				        /*//Ϊ����������һ���Ƚ����Եİ�ť��������ԡ��Ժ����ѵ�
				        builder.setNeutralButton("�Ժ�����",null);*/
				        //���ù���������AlertDialog�Ķ���,ʵ��ʵ����
				        alertDialog=builder.create();
				        alertDialog.show();
					}
					
					
					};
					
					
					
					
					new Thread(){

						@Override
						public void run() {
							Looper.prepare();
							new Handler().post(runnable);//�����߳���ֱ��ȥnew һ��handler
							Looper.loop();
							//��������£�Runnable���������������߳��еģ����Խ����������������ǲ��ܸ���UI
							
						}
					}.start();
			}
		});
		

	}

	
}
