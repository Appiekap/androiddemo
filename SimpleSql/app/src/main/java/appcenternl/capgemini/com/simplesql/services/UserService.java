package appcenternl.capgemini.com.simplesql.services;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.File;

import appcenternl.capgemini.com.simplesql.R;
import appcenternl.capgemini.com.simplesql.services.domain.User;
import appcenternl.capgemini.com.simplesql.services.interfaces.UserServiceListener;

/**
 * Created by appiekap on 03/10/2017.
 */

public class UserService {
    private RequestQueue mRequestQueue;
    private Context mContext;
    private UserServiceListener mListener;

    public UserService(Context context, UserServiceListener listener) {
        mContext = context;
        mListener = listener;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(new File("cache"));

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        this.mRequestQueue = new RequestQueue(cache, network);

        // Start the queue
        this.mRequestQueue.start();
    }

    public void getUsers()
    {
        addUserRequestToQueue();
    }

    /**
     * Create user request and add it to the queue to execute
     */
    private void addUserRequestToQueue(){

        final String productEndpoint = mContext.getString(R.string.service_endpoint_users);

        // Create request with response listener.
        final StringRequest request = new StringRequest(Request.Method.GET, productEndpoint, new Response.Listener<String>() {
            /**
             * Valid response
             */
            @Override
            public void onResponse(String response) {
                // Transform json to java objects.
                Gson gson = new Gson();
                User[] responseObject = gson.fromJson(response, User[].class);
                onUsersLoaded(responseObject);

                // TODO Save users to file for offline use?
            }
        }, new Response.ErrorListener() {
            /**
             * Error response
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);

                // TODO Load previously saved users from file?
            }
        });

        //add to the request queue
        this.mRequestQueue.add(request);
    }

    /**
     * Check if listener is not null and pass on error
     */
    private void onError(VolleyError error){
        if(mListener != null){
            mListener.onUsersLoadedError(error);
        }
    }

    /**
     * Check if listener is not null and pass on users
     */
    private void onUsersLoaded(User[] users){
        if(mListener != null){
            mListener.onUsersLoaded(users);
        }
    }
}
