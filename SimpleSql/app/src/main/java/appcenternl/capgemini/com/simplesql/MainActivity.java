package appcenternl.capgemini.com.simplesql;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;

import appcenternl.capgemini.com.simplesql.adapters.UserAdapter;
import appcenternl.capgemini.com.simplesql.services.UserService;
import appcenternl.capgemini.com.simplesql.services.domain.User;
import appcenternl.capgemini.com.simplesql.services.interfaces.UserServiceListener;

public class MainActivity extends AppCompatActivity implements UserServiceListener{

    private TextView mNumberOfUsers;
    private UserService mUserService;
    private ListView mListView;
    private ArrayList<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.mNumberOfUsers = (TextView) findViewById(R.id.numberOfUsers);

        // Get listview and add itemclicklistener.
        this.mListView = (ListView) findViewById(R.id.list);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User user = (User) mListView.getItemAtPosition(position);

                try
                {
                    // Try and open Google Maps with user location.
                    String latitude = user.getAddress().getGeo().getLat();
                    String longitude = user.getAddress().getGeo().getLng();
                    Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + user.getName() + ")&z=0");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Get users.
        this.mUserService = new UserService(this, this);
        this.mUserService.getUsers();
    }

    /**
     * We got users from the service.
     */
    @Override
    public void onUsersLoaded(User[] users) {
        this.mUsers = new ArrayList<>(Arrays.asList(users));

        this.mNumberOfUsers.setText(getString(R.string.number_of_users) + this.mUsers.size());

        UserAdapter adapter = new UserAdapter(MainActivity.this, 0, this.mUsers);
        this.mListView.setAdapter(adapter);
        this.mListView.invalidate();
    }

    /**
     * Something has gone wrong with loading users.
     */
    @Override
    public void onUsersLoadedError(VolleyError error) {
        this.mNumberOfUsers.setText(R.string.no_users_check_internet);

        Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
    }
}
