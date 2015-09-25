package doext.define;

import core.object.DoUIModule;
import core.object.DoProperty;
import core.object.DoProperty.PropertyDataType;


public abstract class do_ProgressBar2_MAbstract extends DoUIModule{

	protected do_ProgressBar2_MAbstract() throws Exception {
		super();
	}
	
	/**
	 * 初始化
	 */
	@Override
	public void onInit() throws Exception{
        super.onInit();
        //注册属性
		this.registProperty(new DoProperty("fontColor", PropertyDataType.String, "000000FF", false));
		this.registProperty(new DoProperty("progressBgColor", PropertyDataType.String, "FFFFFFFF", false));
		this.registProperty(new DoProperty("fontSize", PropertyDataType.Number, "17", false));
		this.registProperty(new DoProperty("progress", PropertyDataType.Number, "", false));
		this.registProperty(new DoProperty("progressColor", PropertyDataType.String, "", false));
		this.registProperty(new DoProperty("progressWidth", PropertyDataType.String, "1", false));
		this.registProperty(new DoProperty("style", PropertyDataType.String, "normal", true));
		this.registProperty(new DoProperty("text", PropertyDataType.String, "", false));

	}
}