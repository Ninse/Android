package com.wxz.save;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText editText;
    private TextView showTextView;
    // Ҫ������ļ���
    private String fileName = "chenzheng_java.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        // ��ȡҳ���е����
        editText = (EditText) findViewById(R.id.addText);
        showTextView = (TextView) findViewById(R.id.showText);
        Button addButton = (Button) this.findViewById(R.id.addButton);
        Button showButton = (Button) this.findViewById(R.id.showButton);
        // �󶨵����¼�
        addButton.setOnClickListener(listener);
        showButton.setOnClickListener(listener);
	}

    // ����������
    private View.OnClickListener listener = new OnClickListener() {
        public void onClick(View v) {
            Button view = (Button) v;
            switch (view.getId()) {
            case R.id.addButton:
                save();
                break;
            case R.id.showButton:
                read();
                break;
            }
        }
    };	
	
    /**
     *@author chenzheng_Java 
     *�����û���������ݵ��ļ�
     */
    private void save() {

        String content = editText.getText().toString();
        try {
            /* �����û��ṩ���ļ������Լ��ļ���Ӧ��ģʽ����һ�������.�ļ�����ϵͳ��Ϊ�㴴��һ���ģ�
             * ����Ϊʲô����ط�����FileNotFoundException�׳�����Ҳ�Ƚ����ơ���Context�������������
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * ��һ�������������ļ����ƣ�ע��������ļ����Ʋ��ܰ����κε�/����/���ַָ�����ֻ�����ļ���
             *          ���ļ��ᱻ������/data/data/Ӧ������/files/chenzheng_java.txt
             * �ڶ��������������ļ��Ĳ���ģʽ
             *             MODE_PRIVATE ˽�У�ֻ�ܴ�������Ӧ�÷��ʣ� �ظ�д��ʱ���ļ�����
             *             MODE_APPEND  ˽��   �ظ�д��ʱ�����ļ���ĩβ����׷�ӣ������Ǹ��ǵ�ԭ�����ļ�
             *             MODE_WORLD_READABLE ����  �ɶ�
             *             MODE_WORLD_WRITEABLE ���� �ɶ�д
             *  */
            FileOutputStream outputStream = openFileOutput(fileName,
                    Activity.MODE_APPEND);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(MainActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
    /**
     * @author chenzheng_java 
     * ��ȡ�ղ��û����������
     */
    private void read() {
        try {
            FileInputStream inputStream = this.openFileInput(fileName);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            String content = new String(arrayOutputStream.toByteArray());
            showTextView.setText(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
