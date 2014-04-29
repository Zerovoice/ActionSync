
package com.zeroapp.action.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;

import com.zeroapp.action.R;
import com.zeroapp.action.fragments.DeleteFragment;
import com.zeroapp.action.fragments.LoginFragment;
import com.zeroapp.action.models.CategoryInfo;
import com.zeroapp.action.models.ZeroAppApplication;
import com.zeroapp.action.view.carousel.CarouselAdapter;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemClickListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemLongClickListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemSelectedListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnScrollListener;
import com.zeroapp.action.view.carousel.CarouselView;
import com.zeroapp.action.view.carousel.CarouselViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: MainActivity.
 * </p>
 * <p>
 * Description: MainActivity.
 * </p>
 * 
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-29.
 * @version $Id$
 */
public class MainActivity extends FragmentActivity implements OnItemClickListener,
        OnItemSelectedListener, OnItemLongClickListener, OnScrollListener {

    private static final String TAG = "MainActivity";
    private CarouselView carousel;
    private TextView actionBarTitle;
    private List<CategoryInfo> data;
    private CarouselViewAdapter adapter;
    public static FrameLayout topFrameLayout;
    private LinearLayout mainLinearLayout;
    /**
     * if TopFrameLayout is not shown, scroll won't make an animotion.
     */
    private boolean isTopFrameLayoutShowing = false;
    /**
     * the Category which is selected or clicked or longclicked.
     */
    private CategoryInfo focusCategory;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = ZeroAppApplication.mDatas;
        // 初始化ShareSDK
        ShareSDK.initSDK(this);

        initView();
        initCarousel();

	}

    /**
     * 初始化数据
     */
    private void initView() {
        mainLinearLayout = (LinearLayout) findViewById(R.id.main_liner_layout);
        carousel = (CarouselView) mainLinearLayout.findViewById(R.id.carousel);
        actionBarTitle = (TextView) mainLinearLayout.findViewById(R.id.action_bar_title_tv);
        topFrameLayout = (FrameLayout) findViewById(R.id.topfl_container);
	}

    /**
     * <p>
     * Title: initCarousel.
     * </p>
     * <p>
     * Description: initCarousel.
     * </p>
     * 
     */
    private void initCarousel() {
        // 不支持的动态添加adapter.notifyDataSetChanged()增强滑动的流畅
        List<View> mViews = new ArrayList<View>();
        for (int i = 0; i < data.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.item_carousel_view, null);
            ImageView icon = (ImageView) view.findViewById(R.id.itemsIcon);
            icon.setImageResource(data.get(i).getIcon());
            // 删除图标下的标示分类名称的TextView
//            TextView msg = (TextView) view.findViewById(R.id.itemsText);
//            msg.setText(data.get(i).getMsg());
            mViews.add(view);
        }

        adapter = new CarouselViewAdapter(this, mViews, false);
        carousel.setOnItemClickListener(this);
        carousel.setOnItemLongClickListener(this);
        carousel.setOnItemSelectedListener(this);
        carousel.setOnScrollListener(this);
        carousel.setAdapter(adapter);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    /**
     * <p>
     * Title: onItemSelected.
     * </p>
     * <p>
     * Description: onItemSelected.
     * </p>
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(CarouselAdapter<?> parent, View view, int position, long id) {
        CategoryInfo c = data.get(position);
        actionBarTitle.setText(c.getMsg());
        update(c);
    }

    /**
     * <p>
     * Title: onNothingSelected.
     * </p>
     * <p>
     * Description: onNothingSelected.
     * </p>
     * 
     * @param parent
     */
    @Override
    public void onNothingSelected(CarouselAdapter<?> parent) {

    }

    /**
     * <p>
     * Title: onItemClick.
     * </p>
     * <p>
     * Description: onItemClick.
     * </p>
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(CarouselAdapter<?> parent, View view, int position, long id) {
        CategoryInfo c = data.get(position);
        check(c);
    }

    /**
     * <p>
     * Title: onItemLongClick.
     * </p>
     * <p>
     * Description: onItemLongClick.
     * </p>
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     * @return
     */
    @Override
    public boolean onItemLongClick(CarouselAdapter<?> parent, View view, int position, long id) {
        CategoryInfo c = data.get(position);
        delete(c);
        return false;
    }

    /**
     * <p>
     * Title: onScroll.
     * </p>
     * <p>
     * Description: onScroll.
     * </p>
     * 
     */
    @Override
    public void onScroll() {
        hideFragment();

    }

    /**
     * <p>
     * Title: update.
     * </p>
     * <p>
     * Description: update.
     * </p>
     * 
     * @param c
     */
    private void update(CategoryInfo c) {
        Log.i(TAG, "updateData--->" + c.getMsg());
        setFocusCategory(c);
        // TODO get data via SDK.

    }

    /**
     * <p>
     * Title: check.
     * </p>
     * <p>
     * Description: check.
     * </p>
     * 
     * @param c
     */
    private void check(CategoryInfo c) {
        Log.i(TAG, "check--->" + c.getMsg());
        setFocusCategory(c);
        // TODO get data via SDK.
        showFragment(0);

    }

    /**
     * <p>
     * Title: delete.
     * </p>
     * <p>
     * Description: delete.
     * </p>
     * 
     * @param c
     */
    private void delete(CategoryInfo c) {
        Log.i(TAG, "delete--->" + c.getMsg());
        setFocusCategory(c);
        // TODO get data via SDK.
        showFragment(1);
    }

    /**
     * <p>
     * Title: showFragment.
     * </p>
     * <p>
     * Description: showFragment.
     * </p>
     * 
     * @param i
     *            0-show login fragment;1-show delete fragment;
     */
    private void showFragment(int i) {
        Log.i(TAG, "showFragment:" + i);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        switch (i) {
            case 0:
                t.replace(R.id.topfl_container, new LoginFragment()).commit();
                break;
            case 1:
                t.replace(R.id.topfl_container, new DeleteFragment()).commit();
                break;  
            default:
                
                break;
        }
        int h = topFrameLayout.getHeight();
        Animation inAnimotion = new TranslateAnimation(0, 0, -h, 0);
        inAnimotion.setFillAfter(true);
        inAnimotion.setDuration(1000);
        topFrameLayout.startAnimation(inAnimotion);
        isTopFrameLayoutShowing = true;
        
    }

    /**
     * <p>
     * Title: hideFragment.
     * </p>
     * <p>
     * Description: hideFragment.
     * </p>
     * 
     */
    private void hideFragment() {
        if (isTopFrameLayoutShowing) {
            int h = topFrameLayout.getHeight();
            Animation inAnimotion = new TranslateAnimation(0, 0, 0, -h);
            inAnimotion.setFillAfter(true);
            inAnimotion.setDuration(1000);
            topFrameLayout.setAnimation(inAnimotion);
            isTopFrameLayoutShowing = false;
        }
    }

    public CategoryInfo getFocusCategory() {
        return focusCategory;
    }

    public void setFocusCategory(CategoryInfo focusCategory) {
        this.focusCategory = focusCategory;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束ShareSDK的统计功能并释放资源
        ShareSDK.stopSDK(this);
    }

}
