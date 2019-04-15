package com.example.codypollard.shoecollection;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.codypollard.shoecollection.JavaBeans.Ebay;
import com.example.codypollard.shoecollection.JavaBeans.Search;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.codypollard.shoecollection.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     String name;
     String id;
    String imageUrl;
    CustomSearchShoeAdapter adapter;

    ArrayList<Ebay> ebayList = new ArrayList<>();
    RequestQueue requestQueue;
    String APPID = "CodyPoll-SoleColl-PRD-7796addd9-0e9d7894";
    String APITOKEN = "v^1.1#i^1#I^3#p^3#r^0#f^0#t^H4sIAAAAAAAAAOVYb2wTZRhft25kMsDIX1Gw3CRBzLXvXa+9P6FNytq5KWzdOhAwMN/evTeOXe/q3XVbE2fqVEiUZXFBPuhMRkwgxgioQQmKKDF+wQhijBOjIESBhAiJGBJA8L2u27qpwDY+NLEf2rzP+/z7/Z7nefvegUxZ+dLNNZuvTHNMKe7PgEyxw0FNBeVlpY9OLymeX1oE8hQc/ZmHM86uknPLTJhQk0IjMpO6ZiJXR0LVTCErDBApQxN0aCqmoMEEMgVLFGKhlSsE2g2EpKFbuqirhKs2HCAYGkBO9jJ+iWFAXPJhqTbks0kPED6fzPsQJ8W9FCOxcT/eN80UqtVMC2pWgKABxZOAISmmiQYC8AvA6+Z5fh3hWo0MU9E1rOIGRDCbrpC1NfJyvXWq0DSRYWEnRLA2VB2rD9WGI3VNyzx5voI5HmIWtFLm6FWVLiHXaqim0K3DmFltIZYSRWSahCc4GGG0UyE0lMwE0s9Szcs+IHoBoEUOQkDRd4XKat1IQOvWedgSRSLlrKqANEux0rdjFLMR34REK7eqwy5qwy77pyEFVUVWkBEgIstDa1fFIo2EKxaNGnqbIiHJRkrxjJ8BFMfRRFDUk7raTLG5GIOOcgyPCVKla5Ji82W66nRrOcIJo1G0ULzgy6MFK9Vr9UZItuxk8umjh+nzr7PrOVjAlLVRs0uKEpgDV3Z5e/KHumGk/nerH/yQETnAUpCFIiuy7L/3gz3r4+uJoF2WUDTqsXNBcZgmE9BoRVZShSIiRUxvKoEMRRK8Ppn2cjIiJT8vkwwvy2TcJ/lJSkYIIBSPizz3P2kNyzKUeMpCw+0xdiOLL0DYdAoKlAVLb0VaUzqJiLGa2QMn1xMdZoDYaFlJweNpb293t3vdutHioQGgPGtWroiJG1ECEsO6yu2VSSXbHSLCVqYiWDiBANGBmw8H11qIYGOkujESq2luqn8iUjfUuKMyC46V/gfSGK4OiuqqIqYLC6LXkKLQsNIxpKpYMCmQpg2ycODZs25DtH2Y2AlMKm6749yinvDoEB9Ytqg5m7XrTpQ8JibJPTj+mCu3gaCka2p6IsbjsFG0NjxCupGeSMBh43HYQFHUU5o1kXA503FYyClVVlTVPiUmEjDPfDxpalBNW4poDoecVOOHksnaRCJlwbiKaqXCmYDsgHM8SzOThldgqPBlNI2PU5WM6Sqq0lV71lUy2hgmWZb3Q0mS8F8U4iWW4yeHfWWLUmDQKdbn43wczfkA8E4KWxi1FVpdxTiCLMfQJIfwTYrhIEVCwIokI8eByHjjPC1Prp5VqoKPisK7atTopoWkyUHD1+HCApWdx9w4ihKLSAR5iWRYGpJxCn/hWyN/p5BHBE5q7OXyH48UntGP88Gi7IfqcuwDXY73ih0O4AGLqUqwqKxklbOkYr6pWMiNL6JuU2nR8FOqgdytKJ2EilFc5lB6j2/5Lu8FQv96MG/4FUJ5CTU1730CeHBkp5SaMXcaxQOGYjBtfsCsA5Uju05qjnPWq2s3wPINnjPfbrh+fW+kbWfFW688BqYNKzkcpUXOLkfRzJaD95z8vPvG8U5h/daORVf3+w48MOfY9F0nOi+9efWpdx/vuvdSr+/wjF3FR/teeKgZfe08+1I4k3H91P0DcW3gq75Pvzi54OW+08kPzj678Jsvrwx07X7n0l/nlzcWPfKMAsvvO8Rv3XnsQi9dfXn3GmdX5amqssCUPRdrZu3e1jYPCtv3HBz48P65Pd07uOdLTiRTFYd/K91yiKI+m3l0b7h8//ye1xfM3n9jxyfb+jr9Z0oafonMu7jrSffJC93bFzRVHPmjs7n/98NbFv768WU/8eKAsORAz5Im6H27pxd89PS51vMNBknPadj3I7rYfPo58Y2li4/4v7/2fpj98+a+Uz/PbknfLH/N3DRzsHx/A+aL0efaEQAA\";\n";
    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fab.hide();

        Search search = new Search();
        DatabaseHandler db = new DatabaseHandler(getContext());

        Search keyword = db.getSearch(search.getId());
        System.out.println("Search word " + keyword);

        requestQueue = Volley.newRequestQueue(getContext());

       // String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=shoes"
//                + keyword.toString()
            //    + "&limit=10&authorization=" + APPID;
//        String url = "https://api.ebay.com/buy/browse/v1/item_summary/search?q=drone&limit=3";{
//            headers: {
//                    Authorization: "Bearer v^1.1#i^1#I^3#p^3#r^0#f^0#t^H4sIAAAAAAAAAOVYb2wTZRhft25kMsDIX1Gw3CRBzLXvXa+9P6FNytq5KWzdOhAwMN/evTeOXe/q3XVbE2fqVEiUZXFBPuhMRkwgxgioQQmKKDF+wQhijBOjIESBhAiJGBJA8L2u27qpwDY+NLEf2rzP+/z7/Z7nefvegUxZ+dLNNZuvTHNMKe7PgEyxw0FNBeVlpY9OLymeX1oE8hQc/ZmHM86uknPLTJhQk0IjMpO6ZiJXR0LVTCErDBApQxN0aCqmoMEEMgVLFGKhlSsE2g2EpKFbuqirhKs2HCAYGkBO9jJ+iWFAXPJhqTbks0kPED6fzPsQJ8W9FCOxcT/eN80UqtVMC2pWgKABxZOAISmmiQYC8AvA6+Z5fh3hWo0MU9E1rOIGRDCbrpC1NfJyvXWq0DSRYWEnRLA2VB2rD9WGI3VNyzx5voI5HmIWtFLm6FWVLiHXaqim0K3DmFltIZYSRWSahCc4GGG0UyE0lMwE0s9Szcs+IHoBoEUOQkDRd4XKat1IQOvWedgSRSLlrKqANEux0rdjFLMR34REK7eqwy5qwy77pyEFVUVWkBEgIstDa1fFIo2EKxaNGnqbIiHJRkrxjJ8BFMfRRFDUk7raTLG5GIOOcgyPCVKla5Ji82W66nRrOcIJo1G0ULzgy6MFK9Vr9UZItuxk8umjh+nzr7PrOVjAlLVRs0uKEpgDV3Z5e/KHumGk/nerH/yQETnAUpCFIiuy7L/3gz3r4+uJoF2WUDTqsXNBcZgmE9BoRVZShSIiRUxvKoEMRRK8Ppn2cjIiJT8vkwwvy2TcJ/lJSkYIIBSPizz3P2kNyzKUeMpCw+0xdiOLL0DYdAoKlAVLb0VaUzqJiLGa2QMn1xMdZoDYaFlJweNpb293t3vdutHioQGgPGtWroiJG1ECEsO6yu2VSSXbHSLCVqYiWDiBANGBmw8H11qIYGOkujESq2luqn8iUjfUuKMyC46V/gfSGK4OiuqqIqYLC6LXkKLQsNIxpKpYMCmQpg2ycODZs25DtH2Y2AlMKm6749yinvDoEB9Ytqg5m7XrTpQ8JibJPTj+mCu3gaCka2p6IsbjsFG0NjxCupGeSMBh43HYQFHUU5o1kXA503FYyClVVlTVPiUmEjDPfDxpalBNW4poDoecVOOHksnaRCJlwbiKaqXCmYDsgHM8SzOThldgqPBlNI2PU5WM6Sqq0lV71lUy2hgmWZb3Q0mS8F8U4iWW4yeHfWWLUmDQKdbn43wczfkA8E4KWxi1FVpdxTiCLMfQJIfwTYrhIEVCwIokI8eByHjjPC1Prp5VqoKPisK7atTopoWkyUHD1+HCApWdx9w4ihKLSAR5iWRYGpJxCn/hWyN/p5BHBE5q7OXyH48UntGP88Gi7IfqcuwDXY73ih0O4AGLqUqwqKxklbOkYr6pWMiNL6JuU2nR8FOqgdytKJ2EilFc5lB6j2/5Lu8FQv96MG/4FUJ5CTU1730CeHBkp5SaMXcaxQOGYjBtfsCsA5Uju05qjnPWq2s3wPINnjPfbrh+fW+kbWfFW688BqYNKzkcpUXOLkfRzJaD95z8vPvG8U5h/daORVf3+w48MOfY9F0nOi+9efWpdx/vuvdSr+/wjF3FR/teeKgZfe08+1I4k3H91P0DcW3gq75Pvzi54OW+08kPzj678Jsvrwx07X7n0l/nlzcWPfKMAsvvO8Rv3XnsQi9dfXn3GmdX5amqssCUPRdrZu3e1jYPCtv3HBz48P65Pd07uOdLTiRTFYd/K91yiKI+m3l0b7h8//ye1xfM3n9jxyfb+jr9Z0oafonMu7jrSffJC93bFzRVHPmjs7n/98NbFv768WU/8eKAsORAz5Im6H27pxd89PS51vMNBknPadj3I7rYfPo58Y2li4/4v7/2fpj98+a+Uz/PbknfLH/N3DRzsHx/A+aL0efaEQAA";
//                }
//        };
            String url = "https://api.ebay.com/shopping?callname=FindProducts&responseencoding=XML&appid=" + APPID + "&siteid=0&QueryKeywords=nikeshoes&version=863";

//                "https://api.ebay.com/buy/browse/v1/item_summary/search?q=shoes"
//                //+ keyword.toString()
//                + "&limit=10/oauth2/authorize?" + TOKEN ;
        System.out.println("Ebay API " + url);


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                System.out.println(response);
                                String name = object.getString("name");
                                String itemId = object.getString("itemId");
                                String image = object.getString("imageUrl");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Catch Error" + e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley Error " + error.getLocalizedMessage());
            }

            //Adding custom headers
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Authoization: Bearer", "v^1.1#i^1#I^3#p^3#r^0#f^0#t^H4sIAAAAAAAAAOVYb2wTZRhft25kMsDIX1Gw3CRBzLXvXa+9P6FNytq5KWzdOhAwMN/evTeOXe/q3XVbE2fqVEiUZXFBPuhMRkwgxgioQQmKKDF+wQhijBOjIESBhAiJGBJA8L2u27qpwDY+NLEf2rzP+/z7/Z7nefvegUxZ+dLNNZuvTHNMKe7PgEyxw0FNBeVlpY9OLymeX1oE8hQc/ZmHM86uknPLTJhQk0IjMpO6ZiJXR0LVTCErDBApQxN0aCqmoMEEMgVLFGKhlSsE2g2EpKFbuqirhKs2HCAYGkBO9jJ+iWFAXPJhqTbks0kPED6fzPsQJ8W9FCOxcT/eN80UqtVMC2pWgKABxZOAISmmiQYC8AvA6+Z5fh3hWo0MU9E1rOIGRDCbrpC1NfJyvXWq0DSRYWEnRLA2VB2rD9WGI3VNyzx5voI5HmIWtFLm6FWVLiHXaqim0K3DmFltIZYSRWSahCc4GGG0UyE0lMwE0s9Szcs+IHoBoEUOQkDRd4XKat1IQOvWedgSRSLlrKqANEux0rdjFLMR34REK7eqwy5qwy77pyEFVUVWkBEgIstDa1fFIo2EKxaNGnqbIiHJRkrxjJ8BFMfRRFDUk7raTLG5GIOOcgyPCVKla5Ji82W66nRrOcIJo1G0ULzgy6MFK9Vr9UZItuxk8umjh+nzr7PrOVjAlLVRs0uKEpgDV3Z5e/KHumGk/nerH/yQETnAUpCFIiuy7L/3gz3r4+uJoF2WUDTqsXNBcZgmE9BoRVZShSIiRUxvKoEMRRK8Ppn2cjIiJT8vkwwvy2TcJ/lJSkYIIBSPizz3P2kNyzKUeMpCw+0xdiOLL0DYdAoKlAVLb0VaUzqJiLGa2QMn1xMdZoDYaFlJweNpb293t3vdutHioQGgPGtWroiJG1ECEsO6yu2VSSXbHSLCVqYiWDiBANGBmw8H11qIYGOkujESq2luqn8iUjfUuKMyC46V/gfSGK4OiuqqIqYLC6LXkKLQsNIxpKpYMCmQpg2ycODZs25DtH2Y2AlMKm6749yinvDoEB9Ytqg5m7XrTpQ8JibJPTj+mCu3gaCka2p6IsbjsFG0NjxCupGeSMBh43HYQFHUU5o1kXA503FYyClVVlTVPiUmEjDPfDxpalBNW4poDoecVOOHksnaRCJlwbiKaqXCmYDsgHM8SzOThldgqPBlNI2PU5WM6Sqq0lV71lUy2hgmWZb3Q0mS8F8U4iWW4yeHfWWLUmDQKdbn43wczfkA8E4KWxi1FVpdxTiCLMfQJIfwTYrhIEVCwIokI8eByHjjPC1Prp5VqoKPisK7atTopoWkyUHD1+HCApWdx9w4ihKLSAR5iWRYGpJxCn/hWyN/p5BHBE5q7OXyH48UntGP88Gi7IfqcuwDXY73ih0O4AGLqUqwqKxklbOkYr6pWMiNL6JuU2nR8FOqgdytKJ2EilFc5lB6j2/5Lu8FQv96MG/4FUJ5CTU1730CeHBkp5SaMXcaxQOGYjBtfsCsA5Uju05qjnPWq2s3wPINnjPfbrh+fW+kbWfFW688BqYNKzkcpUXOLkfRzJaD95z8vPvG8U5h/daORVf3+w48MOfY9F0nOi+9efWpdx/vuvdSr+/wjF3FR/teeKgZfe08+1I4k3H91P0DcW3gq75Pvzi54OW+08kPzj678Jsvrwx07X7n0l/nlzcWPfKMAsvvO8Rv3XnsQi9dfXn3GmdX5amqssCUPRdrZu3e1jYPCtv3HBz48P65Pd07uOdLTiRTFYd/K91yiKI+m3l0b7h8//ye1xfM3n9jxyfb+jr9Z0oafonMu7jrSffJC93bFzRVHPmjs7n/98NbFv768WU/8eKAsORAz5Im6H27pxd89PS51vMNBknPadj3I7rYfPo58Y2li4/4v7/2fpj98+a+Uz/PbknfLH/N3DRzsHx/A+aL0efaEQAA");
//                params.put("Content-Type", "application/json");
//                return params;
//            }

        });
        requestQueue.add(request);
        System.out.println("Request: " + request);


//        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JsonObject>()
//                {
//                    @Override
//                    public void onResponse(JsonObject response)
//                    {
//                        //final Ebay ebay = new Ebay();
//                        //JSONObject object;
//
//                        try
//                        {
//                            JsonObject object = new JsonObject(response);
//
//                            JSONArray jsonArray = object.getAsJsonArray(response);
//
//                            for (int i = 0; i < jsonArray.length(); i++){
//
//                                JSONObject ebay = jsonArray.getJSONObject(i);
//
//                                ebayList.add(new Ebay(
//                                        ebay.getString("title"),
//                                        ebay.getString("itemId"),
//                                        ebay.getString("imageUrl")
//                                ));
//                            }
//                        }
//                        catch (JSONException e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println(error.getLocalizedMessage());
//            }
//        });


        //DatabaseHandler db = new DatabaseHandler(getContext());
        RecyclerView list = view.findViewById(R.id.searchList);
        ArrayList<Ebay> ebayList = db.getAllEbays();
        db.close();
        CustomSearchShoeAdapter adapter = new CustomSearchShoeAdapter(ebayList, getContext());
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
