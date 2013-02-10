package com.android.musicnow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.musicnowapp.R;
import com.shared.app.messages.LogIn;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	private static final String BASE_URL = "http://music-now.appspot.com/";
	private static final String LOGIN = "musicnow";

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section1),
								getString(R.string.title_section2),
								getString(R.string.title_section3), }), this);

		// perform test query
		testQuery();
		
		//LogIn login = new LogIn("erin", "password");
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
		return true;
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}

//	protected void onGetAuthToken(Bundle bundle) {
//		System.out.println("onGetAuthToken()");
//		String auth_token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
//		new GetCookieTask().execute(auth_token);
//	}

	protected void testQuery() {
		System.out.println("testQuery");
		new QueryRequest().execute();
	}

	class QueryRequest extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			new Thread(new Runnable() {
				public void run() {
					try {
						// create a request to test Google App Engine connection
						HttpGet getMethod = new HttpGet(BASE_URL + LOGIN);
						System.out.println("Requesting a query...");
						HttpResponse response = httpClient.execute(getMethod);

						// print out response...
						InputStreamReader is = new InputStreamReader(response
								.getEntity().getContent());
						StringBuilder sb = new StringBuilder();
						BufferedReader br = new BufferedReader(is);
						String read = br.readLine();
						while (read != null) {
							sb.append(read);
							read = br.readLine();
						}

						System.out.println("Query result: " + sb.toString());
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("yay");
				}
			}).start();
			return null;
		}

	}

//	class GetAuthTokenCallback implements AccountManagerCallback {
//		public void run(AccountManagerFuture result) {
//			System.out.println("GetAuthTokenCallback");
//			Bundle bundle;
//			try {
//				bundle = (Bundle) result.getResult();
//				Intent intent = (Intent) bundle.get(AccountManager.KEY_INTENT);
//				if (intent != null) {
//					// User input required
//					startActivity(intent);
//				} else {
//					onGetAuthToken(bundle);
//				}
//			} catch (OperationCanceledException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (AuthenticatorException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	};
//
//	class GetCookieTask extends AsyncTask {
//		@Override
//		protected Object doInBackground(Object... tokens) {
//			try {
//				// Don't follow redirects
//				httpClient.getParams().setBooleanParameter(
//						ClientPNames.HANDLE_REDIRECTS, false);
//
//				HttpGet http_get = new HttpGet(
//						"https://music-now.appspot.com/_ah/login?continue=http://localhost/&auth="
//								+ tokens[0]);
//				HttpResponse response;
//				response = httpClient.execute(http_get);
//				if (response.getStatusLine().getStatusCode() != 302)
//					// Response should be a redirect
//					return false;
//
//				for (Cookie cookie : httpClient.getCookieStore().getCookies()) {
//					if (cookie.getName().equals("ACSID"))
//						return true;
//				}
//			} catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				httpClient.getParams().setBooleanParameter(
//						ClientPNames.HANDLE_REDIRECTS, true);
//			}
//			return false;
//		}
//
//		protected void onPostExecute(Boolean result) {
//			new AuthenticatedRequestTask()
//					.execute("http://music-now.appspot.com/admin/");
//		}
//	}
//
//	class AuthenticatedRequestTask extends AsyncTask {
//		@Override
//		protected HttpResponse doInBackground(Object... urls) {
//			try {
//				HttpGet http_get = new HttpGet((URI) urls[0]);
//				return httpClient.execute(http_get);
//			} catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		protected void onPostExecute(HttpResponse result) {
//			try {
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(result.getEntity().getContent()));
//				String first_line = reader.readLine();
//				Toast.makeText(getApplicationContext(), first_line,
//						Toast.LENGTH_LONG).show();
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

}
