package com.example.lab04_propuesto_java;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    private OnRegisterListener mListener;
    public interface OnRegisterListener {
        void onRegister(AccountEntity account);
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public RegistroFragment() {
        // Required empty public constructor
    }
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterListener) {
            mListener = (OnRegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterListener");
        }
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
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        EditText edtFirstname = view.findViewById(R.id.edtFirstname);
        EditText edtLastname = view.findViewById(R.id.edtLastname);
        EditText edtEmail = view.findViewById(R.id.edtEmail);
        EditText edtPhone = view.findViewById(R.id.edtPhone);
        EditText edtUsername2 = view.findViewById(R.id.edtUsername2);
        EditText edtPassword2 = view.findViewById(R.id.edtPassword2);
        Button btnAceptar = view.findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountEntity account =  new AccountEntity();
                account.setFirstname(edtFirstname.getText().toString());
                account.setLastname(edtLastname.getText().toString());
                account.setEmail(edtEmail.getText().toString());
                account.setPhone(edtPhone.getText().toString());
                account.setUsername(edtUsername2.getText().toString());
                account.setPassword(edtPassword2.getText().toString());
                mListener.onRegister(account);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}