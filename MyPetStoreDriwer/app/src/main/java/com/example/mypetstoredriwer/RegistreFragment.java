package com.example.mypetstoredriwer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnRegiter, btnModify;
    private View vista;
    private EditText txtName, txtPassword;


    private OnFragmentInteractionListener mListener;

    public RegistreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistreFragment newInstance(String param1, String param2) {
        RegistreFragment fragment = new RegistreFragment();
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
       vista = inflater.inflate(R.layout.fragment_registre, container, false);
        txtName= vista.findViewById(R.id.txtName);
        txtPassword = vista.findViewById(R.id.txtPassword);
        btnRegiter= vista.findViewById(R.id.button1);

        btnRegiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminSQLiteOpenHelper admin = new adminSQLiteOpenHelper(getActivity(),"andminitracion", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                String name = txtName.getText().toString();
                String password = txtPassword.getText().toString();

                if (!password.isEmpty()){
                    ContentValues registros = new ContentValues();
                    registros.put("password", password);
                    registros.put("name", name);


                    BaseDeDatos.insert("registro", null, registros);
                    BaseDeDatos.close();

                    txtPassword.setText("");
                    txtName.setText("");
                    // txtDireccion.setText("");
                    Toast.makeText(getContext(),"corret", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getContext(),"Unregistered user", Toast.LENGTH_LONG).show();
                }

            }
        });

       /*btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminSQLiteOpenHelper admin = new adminSQLiteOpenHelper(getActivity(),"andminitracion", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                String name= txtName.getText().toString();
                String password = txtPassword.getText().toString();


                if (!name.isEmpty() && !password.isEmpty() ){
                    ContentValues registrar = new ContentValues();

                    registrar.put("password", password);
                    registrar.put("name", name);


                    int cantidad = BaseDeDatos.update("registro", registrar,  "password="+password, null);
                    BaseDeDatos.close();

                    if (cantidad ==1){
                        Toast.makeText(getContext(), "Usuario modificado correctamente", Toast.LENGTH_SHORT).show();
                        txtName.setText("");
                        txtPassword.setText("");

                    }else{
                        Toast.makeText(getContext(), "Usuariono existe", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "ingrese todos los dats", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
        return vista ;
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
