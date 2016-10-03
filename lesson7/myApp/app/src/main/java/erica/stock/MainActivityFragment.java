package erica.stock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The user can enter a stock ticket and the app will display the current stock price
*/
public class MainActivityFragment extends Fragment {

    @BindView(R.id.button) Button button;
    @BindView(R.id.input) EditText input;
    @BindView(R.id.price) TextView price;

    private final String TAG = this.getClass().getName();

    private Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONArray company = new JSONArray(response.substring(3));               // builds the JSON array based on the response
                String stockPrice = extractPriceFromJSON(company);                      //gets the price using function defined below
                price.setText(stockPrice);                                              // sets the price as the text of the textview
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "A VolleyError occurred.");
            error.printStackTrace();
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

//        final Context c = this.getContext();              //Never used

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUrl = buildSearchURL(input.getText().toString());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl, responseListener,errorListener);         //string request that gets the response from the url

                MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);            //adds the request to the request queue

            }
        });

        return view;
    }

    private String buildSearchURL(String companyTicker) {
        Uri.Builder builder = new Uri.Builder();           //Builds a url based on the input of the user and returns the url as a string
        builder.scheme("http")
                .authority("finance.google.com")
                .appendPath("finance")
                .appendPath("info")
                .appendQueryParameter("client", "iq")
                .appendQueryParameter("q", companyTicker);

        String myUrl = builder.build().toString();

        return myUrl;
    }

    private String extractPriceFromJSON(JSONArray array) throws JSONException {
        JSONObject company = array.getJSONObject(0);        // There is currently only one element in the array; this is made into an object
        String stockPrice = company.getString("l");         //Gets the price from the object

        return stockPrice;
    }

}