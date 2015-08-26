package doext.circleprogressbar;

public class CircleProgressEntity {
	//进度条样式  normal或cycle
	private String style;
	//进度值
	private float progress;
	//进度颜色
	private int progressColor;
	//进度宽度
	private int progressWidth;
	//文本
	private String text;
	//字体颜色
	private int fontColor;
	//字体大小
	private float fontSize;
	//进度条背景颜色
	private int progressBgColor;
	public CircleProgressEntity() {
	}
	public CircleProgressEntity(String style, float progress, int progressColor, int progressWidth, String text, int fontColor, float fontSize, int progressBgColor) {
		super();
		this.style = style;
		this.progress = progress;
		this.progressColor = progressColor;
		this.progressWidth = progressWidth;
		this.text = text;
		this.fontColor = fontColor;
		this.fontSize = fontSize;
		this.progressBgColor = progressBgColor;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	public int getProgressColor() {
		return progressColor;
	}
	public void setProgressColor(int progressColor) {
		this.progressColor = progressColor;
	}
	public int getProgressWidth() {
		return progressWidth;
	}
	public void setProgressWidth(int progressWidth) {
		this.progressWidth = progressWidth;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFontColor() {
		return fontColor;
	}
	public void setFontColor(int fontColor) {
		this.fontColor = fontColor;
	}
	public float getFontSize() {
		return fontSize;
	}
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}
	public int getProgressBgColor() {
		return progressBgColor;
	}
	public void setProgressBgColor(int progressBgColor) {
		this.progressBgColor = progressBgColor;
	}
	@Override
	public String toString() {
		return "CircleProgressEntity [style=" + style + ", progress=" + progress + ", progressColor=" + progressColor + ", progressWidth=" + progressWidth + ", text=" + text + ", fontColor="
				+ fontColor + ", fontSize=" + fontSize + ", progressBgColor=" + progressBgColor + "]";
	}

}
