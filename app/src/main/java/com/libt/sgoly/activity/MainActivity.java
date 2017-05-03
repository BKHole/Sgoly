package com.libt.sgoly.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libt.sgoly.R;
import com.libt.sgoly.fragment.main.FruitFragment;
import com.libt.sgoly.fragment.main.MineFragment;
import com.libt.sgoly.fragment.main.MomentsFragment;
import com.libt.sgoly.manager.ActivityManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    /**
     * 底部Tab布局显示3个RelativeLayout
     */
    LinearLayout tab_fruit;
    LinearLayout tab_finding;
    LinearLayout tab_mine;

    private ImageView findingImage;
    private TextView fruitText;
    private TextView mineText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bindAdapter();
        fruitText.setSelected(true);
    }

    private void init() {
        tab_fruit= (LinearLayout) findViewById(R.id.ly_tab_fruit);
        tab_finding = (LinearLayout) findViewById(R.id.ly_tab_finding);
        tab_mine = (LinearLayout) findViewById(R.id.ly_tab_mine);

        tab_fruit.setOnClickListener(this);
        tab_finding.setOnClickListener(this);
        tab_mine.setOnClickListener(this);

        findingImage = (ImageView) findViewById(R.id.tab_iv_finding);
        mineText = (TextView) findViewById(R.id.tab_tv_mine);
        fruitText= (TextView) findViewById(R.id.tab_tv_fruit);

        FruitFragment fruitFragment = new FruitFragment();
        MineFragment mineFragment = new MineFragment();
        MomentsFragment momentsFragment = new MomentsFragment();
        fragments.add(fruitFragment);
        fragments.add(momentsFragment);
        fragments.add(mineFragment);
    }

    //为ViewPager绑定适配器
    private void bindAdapter() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        };
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //每次选中之前，先清除掉上一次的选中状态
                switch (position) {
                    case 0:
                        fruitText.setSelected(true);
                        findingImage.setSelected(false);
                        mineText.setSelected(false);
                        break;
                    case 1:
                        findingImage.setSelected(true);
                        fruitText.setSelected(false);
                        mineText.setSelected(false);
                        break;
                    case 2:
                        mineText.setSelected(true);
                        fruitText.setSelected(false);
                        findingImage.setSelected(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_tab_fruit:
            /*当点击了消息tab时，选中第1个tab*/
                viewPager.setCurrentItem(0);
                break;
            case R.id.ly_tab_finding:
            /*当点击了发现tab时，选中第2个tab*/
                viewPager.setCurrentItem(1);
                break;
            case R.id.ly_tab_mine:
            /*当点击了我的tab时，选中第3个tab*/
                viewPager.setCurrentItem(2);
                break;
            default:
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ActivityManager.exitBy2Click(this);
        }
        return false;
    }


}
