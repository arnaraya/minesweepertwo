package com.example.minesweepertwo;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import java.util.Random;
import java.math.*;
import android.util.Log;
import android.gesture.*;
class GridView extends View  implements GestureDetector.OnGestureListener, 
GestureDetector.OnDoubleTapListener {
	private float width; // width of one tile
	private float height; // height of one tile
	private GestureDetector gestureDetector;
	int InternalArrayOne[]=new int[16];//[16];
	int InternalArrayTwo[]=new int[16];
	int totrow=4;
	int totcol=4;
	private int selX; // X index of selection
	private int selY; // Y index of selection
	private final Rect selRect = new Rect();
	public GridView(Context context)
	{
		super(context);
		gestureDetector = new GestureDetector(context,this);
		gestureDetector.setOnDoubleTapListener(this);
		setFocusable(true);
		setFocusableInTouchMode(true);
		 for(int i=0;i<totrow;i++)
	        {
	            for(int j=0;j<totcol;j++)
	            {
	                int pos= i*totrow+j;
	                InternalArrayOne[pos]=0;
	                InternalArrayTwo[pos]=0;
	            }
	        }
		 for(int j=0;j<2;j++)
	        {
			   
			   Random rnd = new Random();
	            int posx=rnd.nextInt(4);//Math.random()%16;
	            int posy=rnd.nextInt(4);//Math.random()%16;
	            int pos=posx*totrow+posy;
	            //doLog
	            
	          //  Log.i("App_Name", pos //);
	            InternalArrayOne[pos]=9;
	            
	        }
		 for(int i=0;i<totrow;i++)
	     {
	         for(int j=0;j<totcol;j++)
	         {
	             int pos= i*totrow+j;
	             int mineCount=0;
	             if(InternalArrayOne[pos]!=9)//item1!=var9)
	             {
	                 for(int internalI=i-1;internalI<=i+1;internalI++)
	                 {
	                     if(internalI<0||internalI>totrow-1)
	                     {
	                         continue;
	                     }
	                     for(int internalJ=j-1;internalJ<=j+1;internalJ++)
	                     {
	                         if(internalJ<0||internalJ>totcol-1)
	                         {
	                             continue;
	                         }
	                         
	                         int internalPos=internalI*totrow+internalJ;
	                         
	                      //   id internalItem1=[array1 objectAtIndex:internalPos];
	                         
	                         if(InternalArrayOne[internalPos]==9)
	                         {
	                             mineCount++;
	                             
	                         }
	                         
	                     }
	                     
	                 }
	                 InternalArrayOne[pos]=mineCount;
	             }
	             
	         }
	     }
	}
	
	//OnsizeChange
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	width = w / 4f;//4f;
	height = h / 4f;//4f;
	getRect(selX, selY, selRect);
	super.onSizeChanged(w, h, oldw, oldh);
	}
	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height), (int) (x
		* width + width), (int) (y * height + height));
		}
	
	protected void drawBoard(Canvas canvas)
	{
		Paint outerBoundary=new Paint();
		outerBoundary.setColor(getResources().getColor(R.color.Base));
		canvas.drawRect(0, 0, getWidth(), getHeight(), outerBoundary);
		
		Paint GridLine = new Paint();
		GridLine.setColor(getResources().getColor(R.color.Line));
		
		//Drawing Grid
		 for (int i = 0; i < 4; i++) {//4; i++) {
	         canvas.drawLine(0, i * height, getWidth(), i * height,
	        		 GridLine);
	         canvas.drawLine(0, i * height + 1, getWidth(), i * height
	               + 1,GridLine);
	         canvas.drawLine(i * width, 0, i * width, getHeight(),
	        		 GridLine);
	         canvas.drawLine(i * width + 1, 0, i * width + 1,
	               getHeight(),GridLine);
		 }
	
	}
	
	 public boolean onTouchEvent(MotionEvent event) {
		 System.out.println("onTouchEvent is clicked");
			return gestureDetector.onTouchEvent(event);
		    }
	 private void setRect(int col, int row, Rect selectedRect) {
			selectedRect.set((int) (col * width),
				         (int) (row * height),
				         (int) (col * width + width), 
				         (int) (row * height + height));
		    }
	 private void showImage(Canvas canvas, int row, int col, int resID) {
			setRect(row, col, selRect);
			Bitmap bm = BitmapFactory.decodeResource(getResources(), resID);
			canvas.drawBitmap(bm, null, selRect, null);
		    }
		    public boolean onDoubleTap(MotionEvent event) {
			// reveal double-tapped square, etc. (incl. invalidating view to force re-draw)
		    	System.out.println("Double Tap is clicked");
		    	Log.d("OnDoubleTap","onDoubleTap MainActivity");
		    	int rowVal=(int) (event.getX()/width);
		    	int colVal=(int)(event.getY()/height);
		    	int pos=(int) (rowVal*4+colVal);
		    	if(InternalArrayOne[pos]==9)
		    	{
		    		InternalArrayTwo[pos]=10;
		    		invalidate();	
		    	return true;
		    	}
		    manipulateValues(rowVal,colVal);
		       // int pos=(int) (rowVal*4+colVal);
		    	//int val=this.InternalArrayOne[pos];
		    	//this.InternalArrayTwo[pos]=10;
		    	invalidate();	    	
		    	
			return true;  // or false, if you want activity to handle
		    }
		    
		    public void manipulateValues(int i,int j){
		    	{
		    	   // id var0D=[NSNumber numberWithInteger:0];
		    	   // id var9D=[NSNumber numberWithInteger:9];
		    	    for(int internalI=i-1;internalI<=i+1;internalI++)
		    	    {
		    	        if(internalI<0||internalI>totrow-1)
		    	        {
		    	            continue;
		    	        }
		    	        for(int internalJ=j-1;internalJ<=j+1;internalJ++)
		    	        {
		    	            if(internalJ<0||internalJ>totcol-1)
		    	            {
		    	                continue;
		    	            }
		    	            
		    	            int internalPos=internalI*totrow+internalJ;
		    	            int ArrayOneVal=InternalArrayOne[internalPos];
		    	            int ArrayTwoVal=InternalArrayTwo[internalPos];
		    	            //id internalItem1=[array1 objectAtIndex:internalPos];
		    	            
		    	           // id internalItem2=[array2 objectAtIndex:internalPos];
		    	            if(ArrayOneVal==0 && ArrayTwoVal!=10)
		    	            {
		    	            	InternalArrayTwo[internalPos]=10;
		    	            	manipulateValues(internalI,internalJ);
		    	                //[self rec1 :internalI:internalJ];
		    	            }
		    	            if(ArrayOneVal!=0 && ArrayOneVal!=9)
		    	            {
		    	            	InternalArrayTwo[internalPos]=10;
		    	                continue;
		    	            }
		    	            
		    	            
		    	            
		    	            
		    	        }
		    	    }
		    	    
		    	}
		    }
		    
		    public boolean onSingleTapConfirmed(MotionEvent event) {
			// toggle flag, etc. (incl. invalidating view to force re-draw)
		    	System.out.println("onSingleTapConfirmed is clicked");
		    	int rowVal=(int) (event.getX()/width);
		    	int colVal=(int)(event.getY()/height);
		    	int pos=(int) (rowVal*4+colVal);
		    	if(InternalArrayTwo[pos]==0)
		    	{
		    		InternalArrayTwo[pos]=12;
		    		invalidate();
					return true; 
		    	}
		    	if(InternalArrayTwo[pos]==12)
		    	{
		    		InternalArrayTwo[pos]=0;
		    		invalidate();
					return true; 
		    	}
		    	invalidate();
			return true;  // or false, if you want activity to handle
		    }
		    
		    public boolean onDoubleTapEvent(MotionEvent event) {
		    	System.out.println("onDoubleTapEvent is clicked");
		    	
			return false;
		    }
		    
		    public boolean onDown(MotionEvent arg0) {
		    	System.out.println("onDown is clicked");
			return true;
		    }
		    
		    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
		    	System.out.println("onFling is clicked");
			return false;
		    }
		    
		    public void onLongPress(MotionEvent arg0) {
		    	System.out.println("onLongPress is clicked");
		    }
		    
		    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
		    	System.out.println("onScroll is clicked");
			return false;
		    }

		    public void onShowPress(MotionEvent arg0) {
		    	System.out.println("onShowPress is clicked");
		    }
		    
		    public boolean onSingleTapUp(MotionEvent arg0) {
		    	System.out.println("onSingleTapUp is clicked");
		    	
			return false;
		    }
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		drawBoard(canvas);
		
		
		
		//canvas.drawCircle(20, 20, 15, exPaint);
		
		
		//int totrow=16,totcol=16;
		
		  
		Paint Entry = new Paint();
		Entry.setColor(getResources().getColor(R.color.Line));
		Entry.setStyle(Style.FILL);
		Entry.setTextSize(height * 0.75f);
		Entry.setTextScaleX(width / height);
		Entry.setTextAlign(Paint.Align.RIGHT);
		FontMetrics fm = Entry.getFontMetrics();
		   // Centering in X: use alignment (and X at midpoint)
		   float x = width / 2;
		   // Centering in Y: measure ascent/descent first
		   float y = height / 2 - (fm.ascent + fm.descent) / 2;
	            
//		   for(int j=0;j<2;j++)
//	        {
//			   
//			   Random rnd = new Random();
//	            int posx=rnd.nextInt(16);//Math.random()%16;
//	            int posy=rnd.nextInt(16);//Math.random()%16;
//	            int pos=posx*totrow+posy;
//	            //doLog
//	            
//	          //  Log.i("App_Name", pos //);
//	            InternalArrayOne[pos]=9;
//	            
//	        }
		   
		   for (int i = 0; i < totrow; i++) 
		   {
		   for (int j = 0; j < totcol; j++) 
		   {
			   int pos= i*totrow+j;
			   if(InternalArrayTwo[pos]==10)
			   {
				   canvas.drawText(Integer.toString(InternalArrayOne[pos]), i
						   * width + x, j * height + y, Entry);
			   }
			   if(InternalArrayTwo[pos]==12)
			   {
				   this.showImage(canvas, i, j, R.drawable.flag);
			   }
			   
		   }
		   }
		  
	        
		   
			   
	}
	
	protected void drawText(Canvas canvas,int InternalArrayOne[])
	{
		int totrow=4;
		int totcol=4;
		Paint Entry = new Paint();
		Entry.setColor(getResources().getColor(R.color.Line));
		Entry.setStyle(Style.FILL);
		Entry.setTextSize(height * 0.75f);
		Entry.setTextScaleX(width / height);
		Entry.setTextAlign(Paint.Align.RIGHT);
		
		// Draw the number in the center of the tile
		   FontMetrics fm = Entry.getFontMetrics();
		   // Centering in X: use alignment (and X at midpoint)
		   float x = width / 2;
		   // Centering in Y: measure ascent/descent first
		   float y = height / 2 - (fm.ascent + fm.descent) / 2;
		   for (int i = 0; i < totrow; i++) 
		   {
		   for (int j = 0; j < totcol; j++) 
		   {
			   int pos= i*totrow+j;
			   if(InternalArrayOne[pos]==9)
		   canvas.drawText("9", i
		   * width + x, j * height + y, Entry);
		   }
		   }
		   
//		   for(int i=0;i<totrow;i++)
//	     {
//	         for(int j=0;j<totcol;j++)
//	         {
//	             int pos= i*totrow+j;
//	             int mineCount=0;
//	             if(InternalArrayOne[pos]!=9)//item1!=var9)
//	             {
//	                 for(int internalI=i-1;internalI<=i+1;internalI++)
//	                 {
//	                     if(internalI<0||internalI>totrow-1)
//	                     {
//	                         continue;
//	                     }
//	                     for(int internalJ=j-1;internalJ<=j+1;internalJ++)
//	                     {
//	                         if(internalJ<0||internalJ>totcol-1)
//	                         {
//	                             continue;
//	                         }
//	                         
//	                         int internalPos=internalI*totrow+internalJ;
//	                         
//	                      //   id internalItem1=[array1 objectAtIndex:internalPos];
//	                         
//	                         if(InternalArrayOne[internalPos]==9)
//	                         {
//	                             mineCount++;
//	                             
//	                         }
//	                         
//	                     }
//	                     
//	                 }
//	                 InternalArrayOne[pos]=mineCount;
//	             }
//	             
//	         }
//	     }
		   
		   for(int i=0;i<totrow;i++)
			{
				for(int j=0;j<totcol;j++)
				{
					 int pos= i*totrow+j;
					 if(InternalArrayOne[pos]==0)
					 {
						 canvas.drawText("0", i
								   * width + x, j * height + y, Entry);
					 }
					 if(InternalArrayOne[pos]==1)
					 {
						 canvas.drawText("1", i
								   * width + x, j * height + y, Entry);
					 }
					 if(InternalArrayOne[pos]==2)
					 {
						 canvas.drawText("2", i
								   * width + x, j * height + y, Entry);
					 }
					 if(InternalArrayOne[pos]==3)
					 {
						 canvas.drawText("3", i
								   * width + x, j * height + y, Entry);
					 }
					 if(InternalArrayOne[pos]==4)
					 {
						 canvas.drawText("4", i
								   * width + x, j * height + y, Entry);
					 }
					 if(InternalArrayOne[pos]==5)
					 {
						 canvas.drawText("5", i
								   * width + x, j * height + y, Entry);
					 }
					 if(InternalArrayOne[pos]==6)
					 {
						 canvas.drawText("6", i
								   * width + x, j * height + y, Entry);
					 }
					 
					 if(InternalArrayOne[pos]==7)
					 {
						 canvas.drawText("7", i
								   * width + x, j * height + y, Entry);
					 }
					 
					 if(InternalArrayOne[pos]==8)
					 {
						 canvas.drawText("8", i
								   * width + x, j * height + y, Entry);
					 }
					 
					 if(InternalArrayOne[pos]==9)
					 {
						 canvas.drawText("9", i
								   * width + x, j * height + y, Entry);
					 }
					 
				}
			}

	}
	
	
		    	
	
}





