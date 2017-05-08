package com.libt.sgoly.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.libt.sgoly.R;
import com.libt.sgoly.adapter.SearchAdapter;
import com.libt.sgoly.db.Fruit;
import com.libt.sgoly.util.ToastUtils;
import com.libt.sgoly.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.R.attr.childIndicator;
import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.libt.sgoly.R.drawable.object;

public class SearchActivity extends BaseActivity {

    private EditText etInput;
    private Button btnSearch;
    private ImageView ivDelete;
    private ImageView searchBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_search);
        etInput = (EditText) findViewById(R.id.search_et_input);
        ivDelete = (ImageView) findViewById(R.id.search_iv_delete);
        btnSearch = (Button) findViewById(R.id.search);
        searchBack= (ImageView) findViewById(R.id.search_back);

        ivDelete.setOnClickListener(clickListener);
        btnSearch.setOnClickListener(clickListener);
        searchBack.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == btnSearch) {

            } else if (view == ivDelete) {

            }else if (view== searchBack){
                onBackPressed();
            }

        }
    };

    //private void initMoments() {
    //    //for (int i = 0; i < 50; i++) {
    //    //    Random random = new Random();
    //    //    int index = random.nextInt(fruits.length);
    //    //    fruitList.add(fruits[index]);
    //    //}
    //    BmobQuery<Fruit> query = new BmobQuery<>();
    //    //返回50条数据，如果不加上这条语句，默认返回10条数据
    //    query.setLimit(10);
    //    //执行查询方法
    //    query.findObjects(new FindListener<Fruit>() {
    //        @Override
    //        public void done(List<Fruit> object, BmobException e) {
    //            if (e == null) {
    //                for (Fruit fruit : object) {
    //                    fruitList.add(fruit);
    //                }
    //                adapter.notifyDataSetChanged();
    //            } else {
    //                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
    //            }
    //        }
    //    });
    //}

}
