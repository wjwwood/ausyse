package net.classSample.GoogleMaps;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;

public class SocketCamera {
 
 private static final String LOG_TAG = "SocketCamera:";
 private static final int SOCKET_TIMEOUT = 1000;
 
 static private SocketCamera socketCamera;
 private CameraCapture capture;
 private Camera parametersCamera;
 private SurfaceHolder surfaceHolder;
 
 //Set the IP address of your pc here!!
 private final String address = "192.168.2.104";
 private final int port = 8080;
 
 private final boolean preserveAspectRatio = true;
 private final Paint paint = new Paint();

 
 private int width = 240;
 private int height = 320;
 private Rect bounds = new Rect(0, 0, width, height);
 
 private SocketCamera() {
  //Just used so that we can pass Camera.Paramters in getters and setters
  parametersCamera = Camera.open();
 }
 
 static public SocketCamera open()
 {
  if (socketCamera == null) {
   socketCamera = new SocketCamera();
  }
  
  Log.i(LOG_TAG, "Creating Socket Camera");
  return socketCamera;
 }

 public void startPreview() {
  capture = new CameraCapture();
  capture.setCapturing(true);
  capture.start(); 
  Log.i(LOG_TAG, "Starting Socket Camera");
  
 }
 
 public void stopPreview(){
  capture.setCapturing(false);
  Log.i(LOG_TAG, "Stopping Socket Camera");
 }
 
 public void setPreviewDisplay(SurfaceHolder surfaceHolder) throws IOException {
  this.surfaceHolder = surfaceHolder;
 }
 
 public void setParameters(Camera.Parameters parameters) {
  //Bit of a hack so the interface looks like that of
  Log.i(LOG_TAG, "Setting Socket Camera parameters");
  parametersCamera.setParameters(parameters);
  Size size = parameters.getPreviewSize();
  bounds = new Rect(0, 0, size.width, size.height);
 }
 public Camera.Parameters getParameters() { 
  Log.i(LOG_TAG, "Getting Socket Camera parameters");
  return parametersCamera.getParameters(); 
 } 
 
 public void release() {
  Log.i(LOG_TAG, "Releasing Socket Camera parameters");
  //TODO need to implement this function
 } 
 
 
 private class CameraCapture extends Thread  {
  
  private boolean capturing = false;
  private SurfaceHolder nSurface = surfaceHolder;
  
  public boolean isCapturing() {
   return capturing;
  }

  public void setCapturing(boolean capturing) {
   this.capturing = capturing;
  }

  @Override

  public void run() 
  {
	  while (capturing) 
	  {
		  Canvas c = null;
		  try
		  {
			  c = nSurface.lockCanvas(null);
			  synchronized (nSurface)
			  {
				  Socket socket = null;
				  try
				  {
					  socket = new Socket();
					  socket.bind(null);
					  socket.setSoTimeout(SOCKET_TIMEOUT);
					  socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
					  //obtain the bitmap
					  InputStream in = socket.getInputStream();
					  Bitmap bitmap = BitmapFactory.decodeStream(in);
					  //render it to canvas, scaling if necessary
					  if (bounds.right == bitmap.getWidth() && bounds.bottom == bitmap.getHeight())
					  {
						  c.drawBitmap(bitmap, 0, 0, null);
					  }
					  else
					  {
						  Rect dest;
						  if (preserveAspectRatio)
						  {
							  dest = new Rect(bounds);
							  dest.bottom = bitmap.getHeight() * bounds.right / bitmap.getWidth();
							  dest.offset(0, (bounds.bottom - dest.bottom)/2);
						  }
						  else
						  {
							  dest = bounds;
						  }
						  if (c != null)
						  {
							  c.drawBitmap(bitmap, null, dest, paint);
						  }
					  }
				  } catch (RuntimeException e)
				  {
					  e.printStackTrace();
				  } catch (IOException e)
				  {
					  e.printStackTrace();
				  }
				  finally
				  {
					  try
					  {
						  socket.close();
					  } catch (IOException e)
					  {
						  /* ignore */
					  }
				  }
			  }
		  } catch (Exception e)
		  {
			  e.printStackTrace();
		  } finally
		  {
			  // do this in a finally so that if an exception is thrown
			  // during the above, we don't leave the Surface in an
			  // inconsistent state
			  if (c != null)
			  {
				  nSurface.unlockCanvasAndPost(c);
			  }
		  }
	  }
	  Log.i(LOG_TAG, "Socket Camera capture stopped");
  }
}

}