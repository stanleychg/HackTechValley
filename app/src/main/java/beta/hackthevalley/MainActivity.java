package beta.hackthevalley;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class MainActivity extends AppCompatActivity {

	private static final int PERMISSION_REQUEST = 1;

	private FusedLocationProviderClient mFusedLocationClient;

	private AsyncTask<Location, Void, List<FormattedPost>> helperTask;
	private Toolbar toolbar;
	private ListView lv;
	private PostAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(MainActivity.this,
					new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
					PERMISSION_REQUEST);
			return;
		}
		init();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PERMISSION_REQUEST) {
			if (resultCode == RESULT_OK) {
				init();
			} else {

			}
		}
	}

	@SuppressLint("MissingPermission")
	private void init() {
		toolbar = findViewById(R.id.mainToolbar);
		lv = findViewById(R.id.mainList);
		adapter = new PostAdapter(this, 0, new LinkedList<FormattedPost>());

		helperTask = new AsyncTask<Location, Void, List<FormattedPost>>() {

			String subAdminArea;
			String adminArea;

			@Override
			protected List<FormattedPost> doInBackground(Location... locations) {

				// Make pull request
				try {
					List<User> users = API.getUsers().execute().body();
					List<Post> posts = API.getPosts().execute().body();
					List<Business> businesses = API.getBusinesses().execute().body();

					List<FormattedPost> results = new LinkedList<>();
					//Inefficient algo
					for (Post p : posts) {
						boolean isFound = false;

						//Check that if a location is specified that it is valid
						Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
						List<Address> addr = geocoder.getFromLocation(locations[0].getLatitude(), locations[0].getLongitude(), 1);
						subAdminArea = addr.get(0).getSubAdminArea();
						adminArea = addr.get(0).getAdminArea();

						//Check if business
						for (Business b : businesses) {
							if (p.getId().equals(b.getId())) {
								results.add(new FormattedPost(p, b.getBusinessName()));
								isFound = true;
								break;
							}
						}

						if (isFound) continue;

						//Check which user
						for (User u : users) {
							if (u.getId().equals(p.getId())) {
								results.add(new FormattedPost(p, u.getName()));
								break;
							}
						}
					}

					return results;
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

			@Override
			protected void onPostExecute(List<FormattedPost> formattedPosts) {
				super.onPostExecute(formattedPosts);
				adapter.clear();
				adapter.addAll(formattedPosts);
				adapter.notifyDataSetChanged();
				toolbar.setTitle(String.format("%s, %s", subAdminArea, adminArea));
			}
		};

		// Grab location
		mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
		mFusedLocationClient.getLastLocation()
				.addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
					@Override
					public void onSuccess(Location location) {
						// Got last known location. In some rare situations this can be null.
						if (location != null) {
							// Logic to handle location object
							helperTask.execute(location);
						}
					}
				});

		lv.setAdapter(adapter);
	}
}
