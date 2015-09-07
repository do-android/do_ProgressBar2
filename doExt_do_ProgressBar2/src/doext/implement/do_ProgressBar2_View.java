package doext.implement;


import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.FrameLayout;
import core.helper.DoTextHelper;
import core.helper.DoUIModuleHelper;
import core.interfaces.DoIModuleTypeID;
import core.interfaces.DoIScriptEngine;
import core.interfaces.DoIUIModuleView;
import core.object.DoInvokeResult;
import core.object.DoProperty;
import core.object.DoUIModule;
import doext.circleprogressbar.CircleProgressEntity;
import doext.circleprogressbar.CircleProgressView;
import doext.define.do_ProgressBar2_IMethod;
import doext.define.do_ProgressBar2_MAbstract;

/**
 * 自定义扩展UIView组件实现类，此类必须继承相应VIEW类，并实现DoIUIModuleView,Do_Label_IMethod接口；
 * #如何调用组件自定义事件？可以通过如下方法触发事件：
 * this.model.getEventCenter().fireEvent(_messageName, jsonResult);
 * 参数解释：@_messageName字符串事件名称，@jsonResult传递事件参数对象； 获取DoInvokeResult对象方式new
 * DoInvokeResult(this.model.getUniqueKey());
 */
public class do_ProgressBar2_View extends FrameLayout implements DoIUIModuleView, do_ProgressBar2_IMethod, DoIModuleTypeID {

	/**
	 * 每个UIview都会引用一个具体的model实例；
	 */
	private do_ProgressBar2_MAbstract model;
	private Context mContext;
	private CircleProgressView circleProgressView;
	public do_ProgressBar2_View(Context context) {
		super(context);
		this.mContext = context;
	}

	/**
	 * 初始化加载view准备,_doUIModule是对应当前UIView的model实例
	 */
	@Override
	public void loadView(DoUIModule _doUIModule) throws Exception {
		
		this.model = (do_ProgressBar2_MAbstract) _doUIModule;
		DoProperty _propertyStyle = model.getProperty("style");
		CircleProgressEntity entity = null;
		String style = _propertyStyle.getValue();
		if (style == null || TextUtils.isEmpty(style)) {
			style = "normal";
		}
		//初始化数据
		entity = new CircleProgressEntity();
		entity.setStyle(style);
		entity.setProgress( DoTextHelper.strToFloat(model.getProperty("progress").getValue(), 0.0f));
		entity.setProgressColor(DoUIModuleHelper.getColorFromString(model.getProperty("progressColor").getValue(), 0x000000ff));
		entity.setProgressWidth(checkProgressWidth(DoTextHelper.strToInt(model.getProperty("progressWidth").getValue(), 1)));
		entity.setText(model.getProperty("text").getValue());
		
		int color = DoUIModuleHelper.getColorFromString(model.getProperty("fontColor").getValue(), 0x000000ff);
		entity.setFontColor(color);
		int progressBgColor = DoUIModuleHelper.getColorFromString(model.getProperty("progressBgColor").getValue(), 0xffffffff);
		entity.setProgressBgColor(progressBgColor);
		
		String textSize = model.getProperty("fontSize").getValue();
		if(textSize == null||textSize.length()<=0){
			entity.setFontSize(17.0f);
		}else{
			int realFontSize = DoUIModuleHelper.getDeviceFontSize(this.model,textSize+"");
			entity.setFontSize(realFontSize);
		}
		
		FrameLayout.LayoutParams fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		circleProgressView = new CircleProgressView(mContext, entity);
		fParams.gravity = Gravity.CENTER_HORIZONTAL;
		fParams.topMargin = 0;
		fParams.bottomMargin = 0;
		
		circleProgressView.setLayoutParams(fParams);
		this.addView(circleProgressView);
		
	}

	/**
	 * 动态修改属性值时会被调用，方法返回值为true表示赋值有效，并执行onPropertiesChanged，否则不进行赋值；
	 * 
	 * @_changedValues<key,value>属性集（key名称、value值）；
	 */
	@Override
	public boolean onPropertiesChanging(Map<String, String> _changedValues) {
		return true;
	}

	/**
	 * 属性赋值成功后被调用，可以根据组件定义相关属性值修改UIView可视化操作；
	 * 
	 * @_changedValues<key,value>属性集（key名称、value值）；
	 */
	@Override
	public void onPropertiesChanged(Map<String, String> _changedValues) {
		DoUIModuleHelper.handleBasicViewProperChanged(this.model, _changedValues);
		
		if (_changedValues.containsKey("progress")) {
			float _progress = DoTextHelper.strToFloat(_changedValues.get("progress"), 0.0f);
			circleProgressView.setProgress(_progress);
		}
		if (_changedValues.containsKey("progressColor")) {
			int color = DoUIModuleHelper.getColorFromString(_changedValues.get("progressColor"), 0x000000ff);
			circleProgressView.setProgressColor(color);
		}
		if (_changedValues.containsKey("progressWidth")) {
			int progressWidth = DoTextHelper.strToInt(_changedValues.get("progressWidth"), 0);
			progressWidth = checkProgressWidth(progressWidth);
			circleProgressView.setProgressWidth(progressWidth);
		}
		if (_changedValues.containsKey("text")) {
			String text = _changedValues.get("text");
			if(text!=null){
				circleProgressView.setText(text);
			}
		}
		if (_changedValues.containsKey("fontColor")) {
			int _fontColor = DoUIModuleHelper.getColorFromString(_changedValues.get("fontColor"), 0x000000ff);
			circleProgressView.setFontColor(_fontColor);
		}
		if (_changedValues.containsKey("progressBgColor")) {
			int _progressBgColor = DoUIModuleHelper.getColorFromString(_changedValues.get("progressBgColor"), 0xffffffff);
			circleProgressView.setProgressBgColor(_progressBgColor);
		}
		if (_changedValues.containsKey("fontSize")) {
			float fontSize = DoTextHelper.strToFloat(_changedValues.get("fontSize"), 17.0f);
			//int realFontSize = DoUIModuleHelper.getDeviceFontSize(this.model, fontSize+"");
			circleProgressView.setFontSize(fontSize);
		}
	}

	private int checkProgressWidth(int progressWidth) {
		if(progressWidth<1){
			return 1;
		}else if(progressWidth>100){
			return 100;
		}else{
			return progressWidth;
		}
	}

	/**
	 * 同步方法，JS脚本调用该组件对象方法时会被调用，可以根据_methodName调用相应的接口实现方法；
	 * 
	 * @_methodName 方法名称
	 * @_dictParas 参数（K,V）
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public boolean invokeSyncMethod(String _methodName, JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		
		return false;
	}

	/**
	 * 异步方法（通常都处理些耗时操作，避免UI线程阻塞），JS脚本调用该组件对象方法时会被调用， 可以根据_methodName调用相应的接口实现方法；
	 * 
	 * @_methodName 方法名称
	 * @_dictParas 参数（K,V）
	 * @_scriptEngine 当前page JS上下文环境
	 * @_callbackFuncName 回调函数名 #如何执行异步方法回调？可以通过如下方法：
	 *                    _scriptEngine.callback(_callbackFuncName,
	 *                    _invokeResult);
	 *                    参数解释：@_callbackFuncName回调函数名，@_invokeResult传递回调函数参数对象；
	 *                    获取DoInvokeResult对象方式new
	 *                    DoInvokeResult(this.model.getUniqueKey());
	 */
	@Override
	public boolean invokeAsyncMethod(String _methodName, JSONObject _dictParas, DoIScriptEngine _scriptEngine, String _callbackFuncName) {
		// ...do something
		return false;
	}

	/**
	 * 释放资源处理，前端JS脚本调用closePage或执行removeui时会被调用；
	 */
	@Override
	public void onDispose() {
		// ...do something
	}

	/**
	 * 重绘组件，构造组件时由系统框架自动调用；
	 * 或者由前端JS脚本调用组件onRedraw方法时被调用（注：通常是需要动态改变组件（X、Y、Width、Height）属性时手动调用）
	 */
	@Override
	public void onRedraw() {
		this.setLayoutParams(DoUIModuleHelper.getLayoutParams(this.model));
	}

	/**
	 * 获取当前model实例
	 */
	@Override
	public DoUIModule getModel() {
		return model;
	}

	@Override
	public String getTypeID() {
		return model.getTypeID();
	}

}
