package cn.etcstudio.androiddemo.adapterview;

import cn.etcstudio.androiddemo.R;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BaseAdapterFragment extends Fragment {
	
	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.adapter_view_fragment, container, false);
		listView = (ListView) rootView.findViewById(R.id.list_view);
		listView.setAdapter(new ListViewBaseAdpater(getActivity()));
		return rootView;
	}
	
	private class ListViewBaseAdpater extends BaseAdapter {
		
		private LayoutInflater mInflater;
		private String[] data = {"aaa", "bbb", "abc!!!"};
		
		public ListViewBaseAdpater(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return data.length;
		}

		@Override
		public Object getItem(int position) {
			return data[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = mInflater.inflate(R.layout.expandable_list_item, null);
			TextView textView = (TextView) convertView.findViewById(R.id.text_view);
			textView.setText(data[position]);
			return convertView;
		}
		
	}

}
