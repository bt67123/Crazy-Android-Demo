package cn.etcstudio.androiddemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ExpandableListView mExpandableListView;
	private ActionBarDrawerToggle drawerToggle;
	
	String[] uiGroup;
	String[][] uiChild;
	String mTitle = "";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		uiGroup = this.getResources().getStringArray(R.array.left_main_menu);
		uiChild = new String[][] {
				this.getResources().getStringArray(R.array.adapter_view),
				this.getResources().getStringArray(R.array.progress_bar),
				this.getResources().getStringArray(R.array.view_animator)
		};

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close
				) {

			public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(R.string.drawer_open);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(R.string.drawer_close);
            }	
		};
		mDrawerLayout.setDrawerListener(drawerToggle);

		mExpandableListView = (ExpandableListView) findViewById(R.id.left_drawer);
		mExpandableListView.setAdapter(new ExpandableListViewAdapter(this));
		mExpandableListView.setOnChildClickListener(new ExpandableListItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private class ExpandableListViewAdapter extends BaseExpandableListAdapter {
		
		LayoutInflater groupInflater;
		LayoutInflater childInflater;
		
		public ExpandableListViewAdapter(Context context) {
			groupInflater = LayoutInflater.from(context);
			childInflater = LayoutInflater.from(context);
		}

		@Override
		public int getGroupCount() {
			return uiGroup.length;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return uiChild[groupPosition].length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return uiGroup[groupPosition];
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return uiChild[groupPosition][childPosition];
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			convertView = groupInflater.inflate(R.layout.expandable_list_item, null);
			TextView textView = (TextView) convertView.findViewById(R.id.text_view);
			textView.setText(uiGroup[groupPosition]);
			textView.setTextColor(Color.rgb(255, 255, 255));
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			convertView = childInflater.inflate(R.layout.expandable_list_item, null);
			TextView textView = (TextView) convertView.findViewById(R.id.text_view);
			textView.setText(uiChild[groupPosition][childPosition]);
			textView.setTextColor(Color.rgb(255, 255, 255));
			textView.setTextSize(18);
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
	}
	
	
	private class ExpandableListItemClickListener implements OnChildClickListener {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			mTitle = uiChild[groupPosition][childPosition];
			selectItem(groupPosition, childPosition);
			return false;
		}
		
	}
	
	private void selectItem(int groupPosition, int childPosition) {
	}

}
