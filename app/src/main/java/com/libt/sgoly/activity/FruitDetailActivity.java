package com.libt.sgoly.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libt.sgoly.R;
import com.libt.sgoly.db.Fruit;

public class FruitDetailActivity extends BaseActivity {
    TextView fruitContentNutrients;
    TextView fruitContentEffect;
    TextView fruitContentValue;
    TextView fruitContentHealth;
    TextView fruitContentQuestion;
    TextView fruitContentAnswer;
    TextView fruitContentHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);
        Fruit fruit = (Fruit) getIntent().getSerializableExtra("fruit");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        fruitContentNutrients = (TextView) findViewById(R.id.fruit_content_nutrients);
        fruitContentEffect = (TextView) findViewById(R.id.fruit_content_effect);
        fruitContentValue = (TextView) findViewById(R.id.fruit_content_value);
        fruitContentHealth = (TextView) findViewById(R.id.fruit_content_health);
        fruitContentQuestion = (TextView) findViewById(R.id.fruit_content_question);
        fruitContentAnswer = (TextView) findViewById(R.id.fruit_content_answer);
        fruitContentHistory = (TextView) findViewById(R.id.fruit_content_history);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_collection);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        collapsingToolbar.setTitle(fruit.getName());
        Glide.with(this).load(fruit.getPicture().getFileUrl()).into(fruitImageView);
        fruitContentValue.setText(fruit.getValue());
        fruitContentNutrients.setText(fruit.getNutrients());
        fruitContentEffect.setText(fruit.getEffect());
        fruitContentHealth.setText(fruit.getHealth());
        fruitContentQuestion.setText(fruit.getQuestion());
        fruitContentAnswer.setText(fruit.getAnswer());
        fruitContentHistory.setText(fruit.getHistory());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
