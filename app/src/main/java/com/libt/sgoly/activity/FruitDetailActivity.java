package com.libt.sgoly.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.libt.sgoly.R;

public class FruitDetailActivity extends BaseActivity {
    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentNutrients = (TextView) findViewById(R.id.fruit_content_nutrients);
        TextView fruitContentEffect= (TextView) findViewById(R.id.fruit_content_effect);
        TextView fruitContentValue= (TextView) findViewById(R.id.fruit_content_value);
        TextView fruitContentHealth= (TextView) findViewById(R.id.fruit_content_health);
        TextView fruitContentQuestion= (TextView) findViewById(R.id.fruit_content_question);
        TextView fruitContentHistory= (TextView) findViewById(R.id.fruit_content_history);

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
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruitName);
        fruitContentNutrients.setText("苹果是美容佳品，既能减肥，又可使皮肤润滑柔嫩。苹果是种低热量食物，每100克只产生60千卡热量；苹果中营养成份可溶性大，易被人体吸收，故有“活水”之称，有利于溶解硫元素，使皮肤润滑柔嫩。苹果中还有铜、碘、锰、锌、 钾等元素，人体如缺乏这些元素，皮肤就会发生干燥、易裂、奇痒。苹果中的维生素C是心血管的保护神、心脏病患者的健康元素。");
        fruitContentEffect.setText("苹果含有较多的钾，能与人体过剩的钠盐结合，使之排出体外。当人体摄入钠盐过多时，吃些苹果，有利于平衡体内电解质。苹果中含有的磷和铁等元素，易被肠壁吸收，有补脑养血、宁神安眠作用。临床使用证明，让精神压抑患者嗅苹果香气后，心境大有好转，精神轻松愉快，压抑感消失。");
        fruitContentValue.setText("苹果的性味温和，含有丰富的碳水化合物、维生素和微量元素，有糖类、有机酸、果胶、蛋白质、钙、磷、钾、铁、维生素A、维生素B、维生素C和膳食纤维，另含有苹果酸，酒石酸，胡萝卜素，是所有蔬果中营养价值最接近完美的一个。苹果有“智慧果”、“记忆果”的美称。人们早就发现，多吃苹果有增进记忆、提高智能的效果。苹果不仅含有丰富的糖、维生素和矿物质等大脑必需的营养素，而且更重要的是富含锌元素。据研究，锌是人体内许多重要酶的组成部分，是促进生长发育的关键元素，锌还是构成与记忆力息息相关的核酸与蛋白质的必不可少的元素，锌还与产生抗体、提高人体免疫力等有密切关系。");
        fruitContentHealth.setText("苹果不可与胡萝卜同食，易产生诱发甲状腺肿的物质。苹果不和牛奶同食，果酸与牛奶中的蛋白质反应会生成钙沉淀，引起结石。苹果不可与干贝同食，能引起腹痛。苹果核里你会发现氰化物。把一个苹果核全吃了虽然不会致死，但也绝对对身体不好。当然，如果你吃太多的苹果核，(前提是要咀嚼并吞下)就有可能有并发症。");
        fruitContentQuestion.setText("很多人的身体情况并不是很好的，因此说早上最好不要空腹吃苹果的，尤其是对于一些肠道不好的人来说更是。当然还有一些戒冷的，那么如果早上空腹吃了苹果会引起胃部不舒服。对于身体很好的人来说，早上空腹吃苹果虽然有好处的，但是也不能过度的去吃苹果的，如果早上吃的苹果过多的话，那么是很容易导致身体的不舒服的。还是建议吃一个即可。在早上的时候，尤其我们身体是处于空腹的状态中，吃了苹果是可以补充很多的糖分的，因此说早上吃苹果对于我们身体好处很大。苹果中含有大量的半乳糖荃酸，这个对于我们排毒是很有益处的，而且早上吃苹果还是有利于防止胆结石。当然苹果不能代替饭的，我们早上吃了苹果之后还是吃点早餐的，要不不到中午就饿了。");
        fruitContentHistory.setText("苹果原产于欧洲和中亚及中国新疆地区。哈萨克的阿拉木图与新疆阿力麻里有苹果城的美誉。中国古代的林檎、柰、花红等水果被认为是中国土生苹果品种或与苹果相似的水果。苹果在中国的栽培记录可以追溯至西汉时期，汉武帝时，上林苑中曾栽培林檎和柰，当时多用于熏香衣裳等，亦有置于床头当香熏或置于衣服，最初作为香囊，较少食用。但也有看法认为，林檎和柰是现在的沙果，曾被误认为苹果，真正意义上的苹果是元朝时期从中亚地区传入中国，当时只有在宫廷才可享用。");

    }

    private void init(){

    }
    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
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
