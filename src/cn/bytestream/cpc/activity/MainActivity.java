package cn.bytestream.cpc.activity;

import cn.bytestream.cpc.R;
import cn.bytestream.cpc.service.CPCService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_open;
	private Button btn_close;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_open = (Button) findViewById(R.id.btn_open);
		btn_close = (Button) findViewById(R.id.btn_close);
		btn_open.setOnClickListener(this);
		btn_close.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent cpcService = new Intent(this, CPCService.class);
		switch(v.getId()) {
		case R.id.btn_open:
			startService(cpcService);
			Toast.makeText(getApplicationContext(), "服务已启动", Toast.LENGTH_SHORT)
			.show();
			break;
		case R.id.btn_close:
			stopService(cpcService);
			Toast.makeText(getApplicationContext(), "服务已关闭", Toast.LENGTH_SHORT)
			.show();
			break;
		}
	}

	
}
