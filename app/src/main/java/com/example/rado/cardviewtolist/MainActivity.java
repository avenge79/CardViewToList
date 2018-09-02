package com.example.rado.cardviewtolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        mListView = (ListView) findViewById(R.id.listView);

        ArrayList<Cards> list = new ArrayList<>();
        list.add(new Cards("drawable://" + R.drawable.a, "Lake in the Mountain"));
        list.add(new Cards("drawable://" + R.drawable.aa, "Mountains"));
        list.add(new Cards("drawable://" + R.drawable.b, "Alien Cave"));
        list.add(new Cards("drawable://" + R.drawable.bb, "Purple flowers"));
        list.add(new Cards("drawable://" + R.drawable.c, "Mountains beach"));
        list.add(new Cards("drawable://" + R.drawable.cc, "Green peaks"));
        list.add(new Cards("drawable://" + R.drawable.d, "Mountains big river"));
        list.add(new Cards("drawable://" + R.drawable.dd, "Rocky sunset"));
        list.add(new Cards("drawable://" + R.drawable.e, "Pines river"));
        list.add(new Cards("drawable://" + R.drawable.ee, "Rocky view"));
        list.add(new Cards("drawable://" + R.drawable.f, "Pines"));
        list.add(new Cards("drawable://" + R.drawable.g, "Deserted"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.activity_main, list);
        mListView.setAdapter(adapter);
    }
}
