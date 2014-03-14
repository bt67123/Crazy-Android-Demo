package cn.etcstudio.androiddemo.adapterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.etcstudio.androiddemo.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.StackView;

public class StackViewFragment extends Fragment {
	
	private StackView stackView;
	private Button prevBtn;
	private Button nextBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.stack_view_fragment, container, false);
		
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			data.add(map);
		}

		stackView = (StackView) rootView.findViewById(R.id.stack_view);
		stackView.setAdapter(new SimpleAdapter(getActivity(), data, R.layout.stack_view_item, new String[]{"image"}, new int[]{R.id.image}));
		
		prevBtn = (Button) rootView.findViewById(R.id.prev_btn);
		prevBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stackView.showPrevious();
			}
		});
		
		nextBtn = (Button) rootView.findViewById(R.id.next_btn);
		nextBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stackView.showNext();
				
			}
		});
		return rootView;
	}

}
