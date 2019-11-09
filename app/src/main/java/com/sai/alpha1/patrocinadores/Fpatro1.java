package com.sai.alpha1.patrocinadores;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sai.alpha1.R;
import com.sai.alpha1.ubiconcert1;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class Fpatro1 extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imageView;
    MapView mapView;
    GoogleMap mMap;
    Double lat = 14.8382166;
    Double longi = -91.5067797;
    String mensaje = "Aqui trabajo";
    Button btnfb;
    TextView textView;

    private OnFragmentInteractionListener mListener;

    public Fpatro1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fpatro1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fpatro1 newInstance(String param1, String param2) {
        Fpatro1 fragment = new Fpatro1();
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
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.fragment_fpatro1, container, false);
        imageView = vista.findViewById(R.id.imageViewPa1);
        String url = "https://www.xda-developers.com/files/2019/07/mi_health_featured.png";
        Picasso.get()
                .load(url)
              //  .resize(70,50)
              // .centerCrop()
                .into(imageView);
        mapView = vista.findViewById(R.id.mapa);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        btnfb = vista.findViewById(R.id.btnfb);
        textView = vista.findViewById(R.id.textView4);
        textView.setText("esta es una descripcion larga para corroborar el funcionamiento y o utilidad de la descripcion de las empresas aparte es para determinar de que tamaño de letra y que color de letra se puede hacer");
        btnfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });

        return vista;
    }

    public static String FACEBOOK_URL = "https://facebook.com/Shvares-Co-105058757601310/";
    public static String FACEBOOK_PAGE_ID = "105058757601310";

    //método que obtiene la verdadera URL
    public  String getFacebookPageURL(Context context) {
        try {
            int versionCode = context.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //versiones nuevas de facebook
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //versiones antiguas de fb
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (Exception e) {
            return FACEBOOK_URL; //normal web url
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat,longi );
        mMap.addMarker(new MarkerOptions().position(sydney).title(mensaje).icon(BitmapDescriptorFactory.fromResource(R.drawable.icono)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));

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
