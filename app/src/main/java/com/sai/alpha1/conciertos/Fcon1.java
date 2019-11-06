package com.sai.alpha1.conciertos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sai.alpha1.R;
import com.sai.alpha1.instancias.ferias;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fcon1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fcon1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fcon1 extends Fragment  implements Response.Listener<JSONObject>, Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imageView;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    TextView textView;

    ferias miferia = new ferias();
    ProgressDialog progressDialog;


    private OnFragmentInteractionListener mListener;

    public Fcon1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fcon1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fcon1 newInstance(String param1, String param2) {
        Fcon1 fragment = new Fcon1();
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
        View vista = inflater.inflate(R.layout.fragment_fcon1, container, false);
        imageView = vista.findViewById(R.id.imageView);
        textView = vista.findViewById(R.id.pruebas);
        request= Volley.newRequestQueue(getContext());

        reortnarurl();
        //textView.setText(miferia.getUrlimage());
        String url = miferia.getUrlimage();

        Picasso.get().load(url).into(imageView);

        return vista;

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(getContext(),"no se consulto bro "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());


    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();

        JSONArray json = response.optJSONArray("feria");
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            miferia.setId(jsonObject.optInt("id"));
            miferia.setLocalidad(jsonObject.optString("localidad"));
            miferia.setFecha(jsonObject.optString("fecha"));
            miferia.setFeria(jsonObject.optString("feria"));
            miferia.setUrlimage(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void reortnarurl(){

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado..");
        progressDialog.show();
        String url = "http://192.168.0.5/json/ferias.json";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, this,this);
        request.add(jsonObjectRequest);



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
