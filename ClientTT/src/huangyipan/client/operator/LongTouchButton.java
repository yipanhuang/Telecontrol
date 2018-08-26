package huangyipan.client.operator;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class LongTouchButton extends Button {
	
		private boolean clickDown=false;
		private LongTouchListener longtouchlistener;
		private int mtime;
		public LongTouchButton(Context context,AttributeSet attrs){
			super(context,attrs);
		}
		public LongTouchButton(Context context)
		{
			super(context,null);
		}
		public boolean onTouchEvent(MotionEvent event){
			if(event.getAction()==MotionEvent.ACTION_DOWN)
			{
				clickDown=true;
				new LongTouchTask().execute();
			}
			else if(event.getAction()==MotionEvent.ACTION_UP)
			{
				clickDown=false;
			}
			return super.onTouchEvent(event);
			
		}
		private void sleep(int time)
		{
			try{
				Thread.sleep(time);
			}
			catch(Exception e){
				e.printStackTrace();
			}	
		}
		class LongTouchTask extends AsyncTask<Void, Integer, Void>
		{
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				while(clickDown)
				{
					sleep(mtime);
					publishProgress(0);
				}
				return null;
			}
			protected void onPostExecute(Void result)
			{
			}
			protected void onProgressUpdate(Integer... values)
			{
				longtouchlistener.onLongTouch();
			}
		}
		
		public void setOnLongTouchListener(LongTouchListener listener,int time)
		{
			this.longtouchlistener=listener;
			this.mtime=time;
			
		}
		public interface LongTouchListener{
			void onLongTouch();
		}
	

}
