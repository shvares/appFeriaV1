package com.sai.alpha1.events;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sai.alpha1.R;
import  com.sai.alpha1.instancias.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fevent1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fevent1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fevent1 extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView mostra;
    Button bton;
    ProgressDialog progressDialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    private OnFragmentInteractionListener mListener;

    public Fevent1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fevent1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fevent1 newInstance(String param1, String param2) {
        Fevent1 fragment = new Fevent1();
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
        View vista = inflater.inflate(R.layout.fragment_fevent1, container, false);
        mostra = vista.findViewById(R.id.mostra);
        bton = vista.findViewById(R.id.bton);

        request= Volley.newRequestQueue(getContext());

        mostrarWebview();
/*
        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarWebview();
            }
        });
*/
        return vista;
    }

    private void mostrarWebview() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado..");
        progressDialog.show();
        String url = "http://192.168.0.5/json/ferias.json";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
    progressDialog.hide();
    Toast.makeText(getContext(),"mensaje: "+response,Toast.LENGTH_SHORT).show();
    ferias miferia = new ferias();
        JSONArray json = response.optJSONArray("feria");
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
            miferia.setId(jsonObject.optInt("id"));
            miferia.setLocalidad(jsonObject.optString("localidad"));
            miferia.setFecha(jsonObject.optString("fecha"));
            miferia.setFeria(jsonObject.optString("feria"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mostra.setText(miferia.getId()+" "+miferia.getFecha()+" "+miferia.getFeria()+" "+miferia.getLocalidad());

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        Toast.makeText(getContext(),"no se consulto bro "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
        mostra.setText(error.toString());
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
