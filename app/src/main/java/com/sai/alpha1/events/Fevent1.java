package com.sai.alpha1.events;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sai.alpha1.MainActivity;
import com.sai.alpha1.R;
import  com.sai.alpha1.instancias.*;
import com.squareup.picasso.Picasso;

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
    private ImageView imageView;
    private TextView titulo;
    private  TextView descrip;
    String tit;
    String image;
    String des;
    int pantalla;

    TextView mostra;
    ProgressDialog progressDialog;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public static class info {

        public String texto;
        public String img2;
        public String img1;
        public String img3;

        public info(){

        }
    }
    public class  feria{
        private String url;
        feria(){
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
    private OnFragmentInteractionListener mListener;

    /*public Fevent1(String nombre_event, String url, String act) {
        // Required empty public constructor
        tit = nombre_event;
        image = url;
        des = act;
    }*/
    public Fevent1( int pantalla) {
        this.pantalla=pantalla;
        // Required empty public constructor
    }

    feria pantalla1 = new feria();
    feria pantalla2 = new feria();
    feria pantalla3= new feria();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment Fevent1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fevent1 newInstance(int param1) {
        Fevent1 fragment = new Fevent1(param1);
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fevent1, container, false);
        imageView = vista.findViewById(R.id.imageEvents);
        titulo = vista.findViewById(R.id.txt_titulo_events);
        descrip = vista.findViewById(R.id.descript_event);

        titulo.setText("Hola");
        descrip.setText("Ejemplo");
        String url = "";

        switch (pantalla){
            case 1: pantalla1.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1vQ4oaSCVsDvQsppuoG-9Rt2kdy9F6ji9vjtoBub_mEBUjWUT8w&s"); url = pantalla1.getUrl(); break;
            case 2: pantalla2.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1vQ4oaSCVsDvQsppuoG-9Rt2kdy9F6ji9vjtoBub_mEBUjWUT8w&s"); url = pantalla2.getUrl(); break;
            case 3: pantalla3.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1vQ4oaSCVsDvQsppuoG-9Rt2kdy9F6ji9vjtoBub_mEBUjWUT8w&s"); url = pantalla3.getUrl(); break;
        }


        Picasso.get().load(url).into(imageView);

        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();
        // [START write_message]
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/ferias/ejemplo/img1");

        //myRef.setValue("Hello, World!"); //Commented because we won't writing operation on the database
        // [END write_message]

        // [START read_message]
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1vQ4oaSCVsDvQsppuoG-9Rt2kdy9F6ji9vjtoBub_mEBUjWUT8w&s";
                switch (pantalla){
                    case 1: pantalla1.setUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1vQ4oaSCVsDvQsppuoG-9Rt2kdy9F6ji9vjtoBub_mEBUjWUT8w&s");
                        Picasso.get().load(value).into(imageView); break;
                    case 2: pantalla2.setUrl(url); Picasso.get().load(pantalla2.getUrl()).into(imageView); break;
                    case 3: pantalla3.setUrl(url); Picasso.get().load(pantalla3.getUrl()).into(imageView); break;
                }
                //Picasso.get().load(url).into(imageView)
                //Picasso.get().load(value).into(imageView);
                // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fevent1", "Failed to read value.", error.toException());
            }



        });
        final Query Ref = database.getReference("ferias");
        Ref.orderByChild("img1").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                info inform = dataSnapshot.getValue(info.class);
                System.out.println(dataSnapshot.getKey() + " has " + inform.img2 + " *** onChildAdded");
                System.out.println(dataSnapshot.getKey() + " has " + inform.img1 + " *** onChildAdded");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                info inform = dataSnapshot.getValue(info.class);
                System.out.println(dataSnapshot.getKey() + " has " + inform.img2 + " *** onChildChanged");
                System.out.println(dataSnapshot.getKey() + " has " + inform.img1 + " *** inChildChanged");

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            // ...
        });

    }

    private void mostrarWebview() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado..");
        progressDialog.show();
        String url = "http://192.168.0.10/json/ferias.json";
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
        mostra.setText(" "+miferia.getId()+" "+miferia.getFecha()+" "+miferia.getFeria()+" "+miferia.getLocalidad());

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
