package com.example.minesweepertwo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.gesture.*;
import android.view.GestureDetector;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener, 
GestureDetector.OnDoubleTapListener{
      GridView gridView;
      GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureDetector = new GestureDetector(this, this);
    	gestureDetector.setOnDoubleTapListener(this);
        gridView=new GridView(this);
        setContentView(gridView);
        gridView.requestFocus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public boolean onTouchEvent(MotionEvent event) {
    	return gestureDetector.onTouchEvent(event);
        }
        
        public boolean onDoubleTap(MotionEvent event) {
        	System.out.println("onDoubleTap MainActivity");
        	Log.d("OnDoubleTap","onDoubleTap MainActivity");
    	// reveal double-tapped square, etc. (incl. invalidating view to force re-draw)
    	return true;  // definitely want to establish that you've handled double taps
        }
        
        public boolean onSingleTapConfirmed(MotionEvent event) {
    	// toggle flag, etc. (incl. invalidating view to force re-draw)
    	return true;  // definitely want to establish that you've handled single taps
        }
        
        public boolean onDoubleTapEvent(MotionEvent event) {
    	return false;
        }
        
        public boolean onDown(MotionEvent arg0) {
    	return false;
        }
        
        public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
    	return false;
        }
        
        public void onLongPress(MotionEvent arg0) {
        }
        
        public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
    	return false;
        }

        public void onShowPress(MotionEvent arg0) {
        }
        
        public boolean onSingleTapUp(MotionEvent arg0) {
    	return false;
        }
    
    
}
