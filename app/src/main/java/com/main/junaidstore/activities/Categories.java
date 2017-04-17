package com.main.junaidstore.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.main.junaidstore.R;
import com.main.junaidstore.adapters.CategoriesAdapter;
import com.main.junaidstore.fragments.AddCategoryDialog;
import com.main.junaidstore.interfaces.AsyncCallback;
import com.main.junaidstore.libraries.NetworkInterface;
import com.main.junaidstore.libraries.SimpleDividerItemDecoration;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Muazzam on 4/15/2017.
 */

public class Categories extends AppCompatActivity implements AsyncCallback{

    public  @BindView(R.id.categories_listview) RecyclerView mRecyclerView;

    public NetworkInterface networkInterface;

    public static final int CODE_INSERT_CATEGORY = 1;
    public static final int CODE_DELETE_CATEGORY = 2;
    public static final int CODE_GET_CATEGORIES = 3;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Categories");
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        // specify an adapter (see also next example)

        this.networkInterface = new NetworkInterface(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.category_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog(){

        FragmentManager fm = getSupportFragmentManager();
        AddCategoryDialog newFragment = new AddCategoryDialog();
        newFragment.show(fm,"Dialog");
    }

    @Override
    protected void onResume(){
        super.onResume();

        this.networkInterface.getCategories(getResources().getString(R.string.userid),
                getResources().getString(R.string.access_token),
                CODE_GET_CATEGORIES);
    }



//    private ArrayList<com.main.junaidstore.models.Categories> dummyData(){
//        ArrayList<com.main.junaidstore.models.Categories> cat = new ArrayList<>();
//        cat.add(new com.main.junaidstore.models.Categories("Cat1",1));
//        cat.add(new com.main.junaidstore.models.Categories("Cat2",2));
//        cat.add(new com.main.junaidstore.models.Categories("Cat3",3));
//        cat.add(new com.main.junaidstore.models.Categories("Cat4",4));
//        cat.add(new com.main.junaidstore.models.Categories("Cat5",5));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//        cat.add(new com.main.junaidstore.models.Categories("Cat6",6));
//
//        return cat;
//    }

    @Override
    public void AsyncCallback(int resultCode, Parcelable rf) {

        if(CODE_INSERT_CATEGORY == resultCode){

        }
        else if(CODE_DELETE_CATEGORY == resultCode){

        }
        else if(CODE_GET_CATEGORIES == resultCode){

            com.main.junaidstore.models.Categories categories = Parcels.unwrap(rf);
//            mAdapter = new CategoriesAdapter(dummyData(),this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
