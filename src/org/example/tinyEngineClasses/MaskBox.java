package org.example.tinyEngineClasses;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;


public class MaskBox extends Mask{

	private int width,height;
	
	public MaskBox(int offsetX, int offsetY, int width, int height){
		super(offsetX, offsetY);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public boolean isInMask(int x, int y) {
		return x >= this.x && x <= width+this.x && y >= this.y && y <= height+this.y;
	}
	
	//  For debug
	public void onDraw(Canvas canvas){
		Paint brush = new Paint();
		brush.setColor(Color.GREEN);
		brush.setStyle(Style.STROKE);
		canvas.drawRect(this.x,this.y, this.x + width, this.y + height, brush);
	}
}
