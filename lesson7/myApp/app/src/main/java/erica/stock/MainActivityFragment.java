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
 * A placeholder fragment containing a simple view.
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
                JSONArray company = new JSONArray(response.substring(3));
                String stockPrice = extractPriceFromJSON(company);
                price.setText(stockPrice);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // YOUR CODE HERE. DO SOMETHING WHEN A RESPONSE COMES IN.
            // Hint: remove the first three characters, parse the response into a JSONArray,
            // and pass it into your extractPriceFromJSON() function.
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


        final Context c = this.getContext();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUrl = buildSearchURL(input.getText().toString());

                StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl, responseListener,errorListener);

                MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

            }


                // Create a StringRequest using the URL and the listeners declared above.
                // Add the request to your RequestQueue from your MySingleton class
            });

        return view;
    }


    private String buildSearchURL(String companyTicker) {
        Uri.Builder builder = new Uri.Builder();
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
        // Your code here. Extract the price value from the JSON array
        JSONObject company = array.getJSONObject(0);
        String stockPrice = company.getString("l");
        Log.d("erca",stockPrice);

        return stockPrice;
    }

}