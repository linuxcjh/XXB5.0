package rongfeng.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addBt,refreshBt;
    private ListView mListView;
    private MainAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBt=(Button)findViewById(R.id.add);
        refreshBt=(Button)findViewById(R.id.refresh);
        addBt.setOnClickListener(this);
        refreshBt.setOnClickListener(this);
        mListView=(ListView)findViewById(R.id.listView);

        mAdapter=new MainAdapter(this);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                startActivity(new Intent(this,AddNewActivity.class));
                break;
            case R.id.refresh:
                break;
            default:

        }

    }
}
