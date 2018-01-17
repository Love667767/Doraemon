package com.elson.moviecomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/movie/activity2")
public class MovieMain2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity_main);
        TextView  tv = (TextView) findViewById(R.id.tv_movie);
        tv.setText("我是MovieMain2Activity");
    }


}
