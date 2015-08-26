package dotest.module.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.doext.module.activity.R;

import core.DoServiceContainer;
import core.object.DoModule;
import core.object.DoUIModule;
import doext.implement.do_ProgressBar2_Model;
import doext.implement.do_ProgressBar2_View;
import dotest.module.frame.debug.DoPage;
import dotest.module.frame.debug.DoPageViewFactory;
import dotest.module.frame.debug.DoService;

/**
 * 测试扩展组件Activity需继承此类，并重写相应测试方法；
 */
public class DoTestActivity extends Activity {
	
	protected DoModule model;
	private int progress = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deviceone_test);
		DoService.Init(this);
		DoPageViewFactory doPageViewFactory = (DoPageViewFactory)DoServiceContainer.getPageViewFactory();
		doPageViewFactory.setCurrentActivity(this);
		try {
			initModuleModel();
			initUIView();
		} catch (Exception e) {
			e.printStackTrace();
		}
		onEvent();
	}

	/**
	 * 初始化UIView，扩展组件是UIModule类型需要重写此方法；
	 */
	protected void initUIView() throws Exception{
		do_ProgressBar2_View view = new do_ProgressBar2_View(this);
        DoPage _doPage = new DoPage();
        ((DoUIModule)this.model).setCurrentUIModuleView(view);
        ((DoUIModule)this.model).setCurrentPage(_doPage);
        
        view.loadView((DoUIModule)this.model);
        LinearLayout uiview = (LinearLayout)findViewById(R.id.view);
        uiview.addView(view);
	}

	/**
	 * 初始化Model对象
	 */
	protected void initModuleModel() throws Exception {
		this.model = new do_ProgressBar2_Model();
		DoService.setPropertyValue(this.model, "style", "cycle");
		DoService.setPropertyValue(this.model, "progress", "30");
		DoService.setPropertyValue(this.model, "progressColor", "FF0000FF");
		DoService.setPropertyValue(this.model, "progressWidth", "20");
		DoService.setPropertyValue(this.model, "text", "文本");
		DoService.setPropertyValue(this.model, "fontSize", "30");
		DoService.setPropertyValue(this.model, "progressBgColor", "FFFF00FF");
		DoService.setPropertyValue(this.model, "fontColor", "000000FF");
	}
	
	/**
	 * 测试属性改变
	 * 
	 * @param view
	 */
	public void setPro(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("请输入要测试属性的序号");
		final EditText _editText = new EditText(this);
		_editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		builder.setView(_editText);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String resultNum = _editText.getText().toString().trim();
				if(resultNum==null||"".equals(resultNum))
					return;
				
				int result = Integer.parseInt(resultNum);
				switch (result) {
				case 1:
					if (progress >= 100) {
						progress = 0;
					}
					DoService.setPropertyValue(DoTestActivity.this.model, "progress", progress + "");
					progress += 10;
					break;
				case 2:
					DoService.setPropertyValue(DoTestActivity.this.model, "progressColor", "aabbccff");
					break;
				case 3:
					DoService.setPropertyValue(DoTestActivity.this.model, "progressWidth", 100 + "");
					break;
				case 4:
					DoService.setPropertyValue(DoTestActivity.this.model, "text", "测试"+progress);
					break;
				case 5:
					DoService.setPropertyValue(DoTestActivity.this.model, "fontSize", "50");
					break;
				case 6:
					DoService.setPropertyValue(DoTestActivity.this.model, "fontColor", "FF0000FF");
					break;
				case 7:
					DoService.setPropertyValue(DoTestActivity.this.model, "progressBgColor", "eeBBCCFF");
					break;
				default:
					Toast.makeText(getApplicationContext(), "输入序号有误", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
	/**
	 * 测试属性
	 * 
	 * @param view
	 */
	public void doTestProperties(View view) {
	
	}

	/**
	 * 测试（同步/异步）方法
	 * 
	 * @param view
	 */
	public void doTestMethod(View view) {
		doTestSyncMethod();
		doTestAsyncMethod();
	}

	/**
	 * 测试同步方法
	 */
	protected void doTestSyncMethod() {

	}

	/**
	 * 测试异步方法
	 */
	protected void doTestAsyncMethod() {

	}

	/**
	 * 测试Module订阅事件消息
	 */
	protected void onEvent() {

	}

	/**
	 * 测试模拟触发一个Module消息事件
	 * 
	 * @param view
	 */
	public void doTestFireEvent(View view) {

	}
}
