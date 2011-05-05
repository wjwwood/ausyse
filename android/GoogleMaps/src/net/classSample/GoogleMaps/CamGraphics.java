package net.classSample.GoogleMaps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CamGraphics extends View 
{
	Bitmap p;
	Display display;
	int textSize = 10;
	boolean isActive;

	public CamGraphics(Context context) 
	{
		super(context);
		display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		init();
	}

	public CamGraphics(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		init();
	}

	private void init() 
	{
		this.setFocusableInTouchMode(true);
		this.setFocusable(true);
		p = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
		this.isActive = true;
	}

	@Override
	protected void onDraw(Canvas canvas) 
	{
		canvas.drawBitmap(p, 0, 0, null);
		/*
		//CameraSource cs = new HttpCamera("http://commonsware.com/misc/test2.3gp", 160, 120, true);
		CameraSource cs = new HttpCamera("http://192.168.2.10:8080/stream?topic=/camera/rgb/image_color", 160, 120, true);
		//SocketCamera cs;
		if (!cs.open()) { 
			/* deal with failure to obtain camera */ 
		/*
			this.isActive=false;
			}
		boolean didCap;
		int h = cs.getHeight();
		int w = cs.getWidth();
		didCap = cs.capture(canvas); //capture the frame onto the canvas
		didCap =  cs.capture(canvas);
		  cs.capture(canvas);
		  cs.capture(canvas);
		  cs.capture(canvas);
		cs.close();
		*/
	}
}
