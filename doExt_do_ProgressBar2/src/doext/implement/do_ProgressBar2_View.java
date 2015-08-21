package doext.implement;

import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import core.helper.DoTextHelper;
import core.helper.DoUIModuleHelper;
import core.interfaces.DoIModuleTypeID;
import core.interfaces.DoIScriptEngine;
import core.interfaces.DoIUIModuleView;
import core.object.DoInvokeResult;
import core.object.DoProperty;
import core.object.DoUIModule;
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
		circleProgressView = new CircleProgressView(mContext);
		//double radius = (model.getRealHeight()>model.getRealWidth()?model.getRealWidth():model.getRealHeight())/2;
		DoProperty _propertyStyle = model.getProperty("style");
		if (_propertyStyle != null) {
			String style = _propertyStyle.getValue();
			circleProgressView.setStyle(style);
		}
		FrameLayout.LayoutParams fParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		this.addView(circleProgressView, fParams);
		
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
		if (_changedValues.containsKey("style")) {
			String _style = _changedValues.get("style");
			if(TextUtils.isEmpty(_style))
			circleProgressView.setStyle(_style);
		}
		if (_changedValues.containsKey("progress")) {
			float _progress = DoTextHelper.strToFloat(_changedValues.get("progress"), 0.0f);
			circleProgressView.setProgress(_progress);
		}
		if (_changedValues.containsKey("progressColor")) {
			int  _progressColor = DoUIModuleHelper.getColorFromString(_changedValues.get("progressColor"), 0x000000ff);
			circleProgressView.setProgressColor(_progressColor);
		}
		if (_changedValues.containsKey("progressWidth")) {
			int progressWidth = DoTextHelper.strToInt(_changedValues.get("progressWidth"), 0);
			circleProgressView.setProgressWidth(progressWidth);
		}
		if (_changedValues.containsKey("text")) {
			String text = _changedValues.get("text");
			circleProgressView.setText(text);
		}
		if (_changedValues.containsKey("fontColor")) {
			int _fontColor = DoUIModuleHelper.getColorFromString(_changedValues.get("fontColor"), 0x000000ff);
			circleProgressView.setTextColor(_fontColor);
		}
		if (_changedValues.containsKey("fontSize")) {
			int fontSize = DoTextHelper.strToInt(_changedValues.get("fontSize"), 17);
			circleProgressView.setFontSize(fontSize);
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