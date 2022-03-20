package com.example.eduorigin.learningresourcefragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.eduorigin.R;
import com.example.eduorigin.controllers.WebViewController;


public class JavaTPointFragment extends Fragment {

    public JavaTPointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_java_t_point, container, false);

        WebView webView=view.findViewById(R.id.javaTPointWebViewId);
        webView.loadUrl("https://www.javatpoint.com/");
        webView.setWebViewClient(new WebViewController());

        return view;
    }
}