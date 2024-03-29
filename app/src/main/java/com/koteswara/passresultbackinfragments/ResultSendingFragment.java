package com.koteswara.passresultbackinfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultSendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultSendingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultSendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultSendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultSendingFragment newInstance(String param1, String param2) {
        ResultSendingFragment fragment = new ResultSendingFragment();
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
        View view= inflater.inflate(R.layout.fragment_result_sending, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       EditText editText = view.findViewById(R.id.et_data);
        editText.getText().toString();
        view.findViewById(R.id.sendDataBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentframe,new ResultSendingFragment()).commit();
                Bundle result = new Bundle();
                result.putString("bundleKey", editText.getText().toString());
                getParentFragmentManager().setFragmentResult("requestkey",result);
            }
        });


    }

    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        ResultSendingFragment simpleFragment = (ResultSendingFragment) fragmentManager
                .findFragmentById(R.id.contentframe);
        if (simpleFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
       /* // Update the Button text.
        mButton.setText(R.string.open);
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false;*/
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        closeFragment();
    }
}