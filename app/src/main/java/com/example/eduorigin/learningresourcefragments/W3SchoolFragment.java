package com.example.eduorigin.learningresourcefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.eduorigin.R;
import com.example.eduorigin.controllers.WebViewController;


public class W3SchoolFragment extends Fragment {

    public W3SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_w3_school, container, false);
        WebView webView=view.findViewById(R.id.w3SchoolBBCWebViewId);
        webView.loadUrl("https://www.w3schools.com/");
        webView.setWebViewClient(new WebViewController());





        return view;
    }
}