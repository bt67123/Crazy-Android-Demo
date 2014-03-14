package cn.etcstudio.androiddemo.adapterview;

import cn.etcstudio.androiddemo.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterFragment extends Fragment {
	
	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.adapter_view_fragment, container, false);
		listView = (ListView) rootView.findViewById(R.id.list_view);
		listView.setAdapter(new ArrayAdapter<String>(getActivity(), 
				R.layout.expandable_list_item, 
				new String[]{"aaa", "bbb", "ccc"}));
		return rootView;
	}

}
