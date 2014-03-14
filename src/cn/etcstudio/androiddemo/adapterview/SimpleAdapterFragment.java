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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleAdapterFragment extends Fragment {

	private ListView listView;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.adapter_view_fragment, container, false);
		
    	String[] data ={"aaa", "bbb", "ccc" };
		List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>(); 
		for (int i = 0; i < data.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("text", data[i]);
			items.add(map);
		}

		listView = (ListView) rootView.findViewById(R.id.list_view);
		listView.setAdapter(new SimpleAdapter(
				getActivity(), 
				items, 
				R.layout.simple_adapter_list_item, 
				new String[]{"image", "text"}, 
				new int[]{R.id.image_view, R.id.text_view}));
		return rootView;
	}

}
