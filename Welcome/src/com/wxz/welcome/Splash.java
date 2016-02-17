package com.wxz.welcome;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity{

	
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	
		mp=MediaPlayer.create(this,R.raw.music);
		mp.start();
	Thread timer =new Thread(){
		public void run(){
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}finally{
				Intent openMainActivity=new Intent("android.intent.action.MAINACTIVITY");
				startActivity(openMainActivity);
			}
		}
	};
	timer.start();
	
	}

	@Override
	protected void onPause() {
		// TODO �Զ����ɵķ������
		super.onPause();
		mp.release();
		finish();
	}

}
