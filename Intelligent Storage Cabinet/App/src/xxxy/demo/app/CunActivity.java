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
							Toast.makeText(CunActivity.this, "�����ӱ�ռ�ã���ѡ����������", Toast.LENGTH_LONG).show();
						}else if(box1.getFlag().equals("4")){
							Toast.makeText(CunActivity.this, "�����ӱ�ά�ޣ���ѡ����������", Toast.LENGTH_LONG).show();
						}else{
							
							 //����AlertDialog�Ĺ������Ķ���
					        AlertDialog.Builder builder=new AlertDialog.Builder(CunActivity.this);
					        //���ù���������
					        builder.setTitle("���");
					        //��������Ӧ��ͼ��
					        builder.setIcon(R.drawable.ic_launcher);
					        //����������,Ϊ�Ի��������ı���(֮�����б��������)
					        builder.setMessage("������Ҫ�Ѱ�����"+box1.getBox_id()+"�Ź��Ƿ�����");
					        //Ϊ����������ȷ����ť,��һ������Ϊ��ť��ʾ���ı���Ϣ���ڶ�������Ϊ�����ļ����¼����������ڲ���ʵ��
					        builder.setPositiveButton("��", new DialogInterface.OnClickListener()
					        {
					            @Override
					            public void onClick(DialogInterface dialog, int which)
					            {
							boolean b1=boxdao.updateuserid(box1.getBox_id(), userid);
							boolean b2=boxdao.updateflag(box1.getBox_id(), "1");
							boolean b3=userdao.updatebox(userid, box1.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunActivity.this, "���������У����Ժ�", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox("3");

								final Handler mHandler = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){				
											//������Խ���UI��������Toast��Dialog��			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														//���������UI�ĸ��²���
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
								Toast.makeText(CunActivity.this, "���ʧ�ܣ�����ϵ�ͷ���Ա", Toast.LENGTH_LONG).show();
								userdao.updatebox(userid, null);
							}
					            }
					        });
					      //Ϊ����������ȡ����ť,�������ť����Ҫ���κβ�����ֱ��Ϊ�ڶ���������ֵnull
					        builder.setNegativeButton("��",null);
					        /*//Ϊ����������һ���Ƚ����Եİ�ť��������ԡ��Ժ����ѵ�
					        builder.setNeutralButton("�Ժ�����",null);*/
					        //���ù���������AlertDialog�Ķ���,ʵ��ʵ����
					        alertDialog1=builder.create();
					        alertDialog1.show();
							
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
							Toast.makeText(CunActivity.this, "�����ӱ�ռ�ã���ѡ����������", Toast.LENGTH_LONG).show();
						}else if(box1.getFlag().equals("4")){
							Toast.makeText(CunActivity.this, "�����ӱ�ά�ޣ���ѡ����������", Toast.LENGTH_LONG).show();
						}else{
							
							 //����AlertDialog�Ĺ������Ķ���
					        AlertDialog.Builder builder=new AlertDialog.Builder(CunActivity.this);
					        //���ù���������
					        builder.setTitle("���");
					        //��������Ӧ��ͼ��
					        builder.setIcon(R.drawable.ic_launcher);
					        //����������,Ϊ�Ի��������ı���(֮�����б��������)
					        builder.setMessage("������Ҫ�Ѱ�����"+box1.getBox_id()+"�Ź��Ƿ�����");
					        //Ϊ����������ȷ����ť,��һ������Ϊ��ť��ʾ���ı���Ϣ���ڶ�������Ϊ�����ļ����¼����������ڲ���ʵ��
					        builder.setPositiveButton("��", new DialogInterface.OnClickListener()
					        {
					            @Override
					            public void onClick(DialogInterface dialog, int which)
					            {
							boolean b1=boxdao.updateuserid(box1.getBox_id(), userid);
							boolean b2=boxdao.updateflag(box1.getBox_id(), "1");
							boolean b3=userdao.updatebox(userid, box1.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunActivity.this, "���������У����Ժ�", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox("1");
								
								final Handler mHandler = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){				
											//������Խ���UI��������Toast��Dialog��			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														//���������UI�ĸ��²���
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
								Toast.makeText(CunActivity.this, "���ʧ�ܣ�����ϵ�ͷ���Ա", Toast.LENGTH_LONG).show();
								userdao.updatebox(userid, null);
							}
					            }
					        });
					      //Ϊ����������ȡ����ť,�������ť����Ҫ���κβ�����ֱ��Ϊ�ڶ���������ֵnull
					        builder.setNegativeButton("��",null);
					        /*//Ϊ����������һ���Ƚ����Եİ�ť��������ԡ��Ժ����ѵ�
					        builder.setNeutralButton("�Ժ�����",null);*/
					        //���ù���������AlertDialog�Ķ���,ʵ��ʵ����
					        alertDialog2=builder.create();
					        alertDialog2.show();
							
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
							Toast.makeText(CunActivity.this, "�����ӱ�ռ�ã���ѡ����������", Toast.LENGTH_LONG).show();
						}else if(box1.getFlag().equals("4")){
							Toast.makeText(CunActivity.this, "�����ӱ�ά�ޣ���ѡ����������", Toast.LENGTH_LONG).show();
						}else{
							
							 //����AlertDialog�Ĺ������Ķ���
					        AlertDialog.Builder builder=new AlertDialog.Builder(CunActivity.this);
					        //���ù���������
					        builder.setTitle("���");
					        //��������Ӧ��ͼ��
					        builder.setIcon(R.drawable.ic_launcher);
					        //����������,Ϊ�Ի��������ı���(֮�����б��������)
					        builder.setMessage("������Ҫ�Ѱ�����"+box1.getBox_id()+"�Ź��Ƿ�����");
					        //Ϊ����������ȷ����ť,��һ������Ϊ��ť��ʾ���ı���Ϣ���ڶ�������Ϊ�����ļ����¼����������ڲ���ʵ��
					        builder.setPositiveButton("��", new DialogInterface.OnClickListener()
					        {
					            @Override
					            public void onClick(DialogInterface dialog, int which)
					            {
							boolean b1=boxdao.updateuserid(box1.getBox_id(), userid);
							boolean b2=boxdao.updateflag(box1.getBox_id(), "1");
							boolean b3=userdao.updatebox(userid, box1.getBox_id());
							if(b1 && b2 && b3){
								Toast.makeText(CunActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
								finish();
								/*Toast.makeText(CunActivity.this, "���������У����Ժ�", Toast.LENGTH_LONG).show();
								BoxDao boxdao1=new BoxDao();
								final Box box2=boxdao1.selectbox("2");
								
								final Handler mHandler = new Handler(){
									@Override		
									public void handleMessage(Message msg) {
										// TODO Auto-generated method stub			
										if(msg.what == 0){				
											//������Խ���UI��������Toast��Dialog��			
											if(box2.getFlag().equals("2")){
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														//���������UI�ĸ��²���
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
								Toast.makeText(CunActivity.this, "���ʧ�ܣ�����ϵ�ͷ���Ա", Toast.LENGTH_LONG).show();
								userdao.updatebox(userid, null);
							}
					            }
					        });
					      //Ϊ����������ȡ����ť,�������ť����Ҫ���κβ�����ֱ��Ϊ�ڶ���������ֵnull
					        builder.setNegativeButton("��",null);
					        /*//Ϊ����������һ���Ƚ����Եİ�ť��������ԡ��Ժ����ѵ�
					        builder.setNeutralButton("�Ժ�����",null);*/
					        //���ù���������AlertDialog�Ķ���,ʵ��ʵ����
					        alertDialog3=builder.create();
					        alertDialog3.show();
							
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
				new Handler().post(runnable);//�����߳���ֱ��ȥnew һ��handler
				Looper.loop();
				//��������£�Runnable���������������߳��еģ����Խ����������������ǲ��ܸ���UI
				
			}
		}.start();
		
		
	}
}
