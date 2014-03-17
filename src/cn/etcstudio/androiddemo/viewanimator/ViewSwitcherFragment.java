package cn.etcstudio.androiddemo.viewanimator;

import java.util.ArrayList;

import cn.etcstudio.androiddemo.R;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class ViewSwitcherFragment extends Fragment {
	
	public static final int NUMBER_PRE_SCREEN = 12;
	
	private ViewSwitcher switcher;
	private Button prevBtn;
	private Button nextBtn;
	
	public static class DataItem {
		public String name;
		public Drawable drawable;
	}
	
	private ArrayList<DataItem> items = new ArrayList<DataItem>();
	
	private int screenNo = -1;
	
	private int screenCount;
	
	private LayoutInflater mInflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mInflater = LayoutInflater.from(getActivity());

		View rootView = inflater.inflate(R.layout.view_switcher_fragment, container, false);
		
		for (int i = 0; i < 40; i++) {
			DataItem item = new DataItem();
			item.name = i + "";
			item.drawable = getActivity().getResources().getDrawable(R.drawable.ic_launcher);
			items.add(item);
		}
		
		screenCount = items.size() % NUMBER_PRE_SCREEN == 0 ?
				      items.size() / NUMBER_PRE_SCREEN : 
				      items.size() / NUMBER_PRE_SCREEN + 1; 
		
		switcher = (ViewSwitcher) rootView.findViewById(R.id.switcher);
		switcher.setFactory(new ViewSwitcher.ViewFactory() {
			
			@Override
			public View makeView() {
				return mInflater.inflate(R.layout.grid_view, null);
			}
		});
		
		prevBtn = (Button) rootView.findViewById(R.id.prev_btn);
		prevBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				prev();
			}
		});
		
		nextBtn = (Button) rootView.findViewById(R.id.next_btn);
		nextBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				next();
			}
		});
		
		next();
		
		return rootView;
	}
	
	private void next() {
		if (screenNo < screenCount - 1) {
			screenNo++;
			switcher.setInAnimation(getActivity(), R.anim.slide_in_right);
			switcher.setOutAnimation(getActivity(), R.anim.slide_out_left);
			((GridView) switcher.getNextView()).setAdapter(adapter);
			switcher.showNext();
		}
	} 
	
	private void prev() {
		if (screenNo > 0) {
			screenNo--;
			switcher.setInAnimation(getActivity(), R.anim.slide_in_left);
			switcher.setOutAnimation(getActivity(), R.anim.slide_out_right);
			((GridView) switcher.getNextView()).setAdapter(adapter);
			switcher.showPrevious();
		}
	}
	
	private BaseAdapter adapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
    			convertView = mInflater.inflate(R.layout.labelicon, null);
			}
			ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
			imageView.setImageDrawable(getItem(position).drawable);
			TextView textView = (TextView) convertView.findViewById(R.id.text);
			textView.setText(getItem(position).name);
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public DataItem getItem(int position) {
			return items.get(screenNo * NUMBER_PRE_SCREEN + position);
		}
		
		@Override
		public int getCount() {
			if (screenNo == screenCount - 1 && items.size() % NUMBER_PRE_SCREEN != 0) {
				return items.size() % NUMBER_PRE_SCREEN;
			}
			return NUMBER_PRE_SCREEN;
		}
	};

}
