package cn.etcstudio.androiddemo.progressbar;

import cn.etcstudio.androiddemo.R;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarFragment extends Fragment {
	
	private ProgressBar progressBar;
	private Button showBtn;
	private Button hintBtn;
	
	private int count = 0;
	
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x111)  {
				progressBar.setProgress(count);
			} 
			
		}
		
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.progress_bar_fragment, container, false);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
		
		showBtn = (Button) rootView.findViewById(R.id.show_btn);
		showBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().setProgressBarIndeterminateVisibility(true);
				getActivity().setProgressBarVisibility(true);
				getActivity().setProgress(4500);
				
			}
		});

		hintBtn = (Button) rootView.findViewById(R.id.hint_btn);
		hintBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().setProgressBarIndeterminateVisibility(false);
				getActivity().setProgressBarVisibility(false);
			}
		});
		
		new Thread() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count++;
					mHandler.sendEmptyMessage(0x111);
				}
			}
		}.start();

		return rootView;
	}

}
