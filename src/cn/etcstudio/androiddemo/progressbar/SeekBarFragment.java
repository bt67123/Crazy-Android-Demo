package cn.etcstudio.androiddemo.progressbar;

import cn.etcstudio.androiddemo.R;
import android.R.integer;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class SeekBarFragment extends Fragment {
	
	private ImageView imageView;
	private SeekBar seekBar;
	private RatingBar ratingBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.seek_bar_fragment, container, false);
		imageView = (ImageView) rootView.findViewById(R.id.image);
		seekBar = (SeekBar) rootView.findViewById(R.id.seek_bar);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				imageView.setAlpha(progress);
				
			}
		});
		
		
		ratingBar = (RatingBar) rootView.findViewById(R.id.rating_bar);
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				int progress = (int) (rating / 5 * 255);
				imageView.setAlpha(progress);
				seekBar.setProgress(progress);
			}
		});
		return rootView;
	}

}
