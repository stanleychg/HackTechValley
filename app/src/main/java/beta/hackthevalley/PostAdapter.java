package beta.hackthevalley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class PostAdapter extends ArrayAdapter<FormattedPost> {
	public PostAdapter(@NonNull Context context, int resource, @NonNull List<FormattedPost> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		if (view == null) {
			view = ((LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_post, null);
		}

		TextView title = view.findViewById(R.id.postTitle);
		TextView desc = view.findViewById(R.id.postDesc);
		TextView date = view.findViewById(R.id.postDate);
		FormattedPost p = getItem(i);
		title.setText(p.getName());
		desc.setText(p.getDesc());

		if (p.getEventDate() == null) {
			date.setText("");
		} else {
			SimpleDateFormat s = new SimpleDateFormat("MMM dd");
			date.setText(s.format(p.getEventDate()));
		}

		return view;
	}
}
