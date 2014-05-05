
package com.zeroapp.action.activity;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

import com.zeroapp.action.R;
import com.zeroapp.action.database.CategoryDataControler;
import com.zeroapp.action.fragments.DeleteFragment;
import com.zeroapp.action.fragments.LoginFragment;
import com.zeroapp.action.fragments.UserFragment;
import com.zeroapp.action.models.CategoryInfo;
import com.zeroapp.action.models.ZeroAppApplication;
import com.zeroapp.action.view.carousel.CarouselAdapter;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemClickListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemLongClickListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemSelectedListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnScrollListener;
import com.zeroapp.action.view.carousel.CarouselView;
import com.zeroapp.action.view.carousel.CarouselViewAdapter;

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
        OnItemSelectedListener, OnItemLongClickListener, OnScrollListener, Callback {

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
        adapter = new CarouselViewAdapter(this, data, false);
        carousel.setOnItemClickListener(this);
        carousel.setOnItemLongClickListener(this);
        carousel.setOnItemSelectedListener(this);
        carousel.setOnScrollListener(this);
        carousel.setAdapter(adapter);
    }

    private void updateCarouselView() {
        ZeroAppApplication.getInstance().updateViewDatas();
        data = ZeroAppApplication.mDatas;
//        adapter.setImages(data);
        // 以下处理可以解决UI不刷新，但是基本上就是重新加载carousel。
        adapter = new CarouselViewAdapter(this, data, false);
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

        switching(c);
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
        click(c);
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
    private void switching(CategoryInfo c) {
        Log.i(TAG, "switching--->" + c.getMsg());
        setFocusCategory(c);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fl_container, new UserFragment()).commit();

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
    private void click(CategoryInfo c) {
        Log.i(TAG, "check--->" + c.getMsg());
        setFocusCategory(c);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.topfl_container, new LoginFragment()).commit();
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
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.topfl_container, new DeleteFragment()).commit();
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

    /**
     * 处理操作结果
     * <p>
     * 如果获取到用户的名称，则显示名称；否则如果已经授权，则显示 平台名称
     */
    public boolean handleMessage(Message msg) {
        Platform plat = (Platform) msg.obj;
        String text = MainActivity.actionToString(msg.arg2);
        switch (msg.arg1) {
            case 1:
                // 成功
                if (msg.arg2 == 1) {
                    Log.i(TAG, "onComplete " + "ACTION_AUTHORIZING");
                    CategoryDataControler categoryDataControler = new CategoryDataControler(this);
                    categoryDataControler.insert(getFocusCategory());
                    updateCarouselView();
                } else if (msg.arg2 == 8) {
                    Log.i(TAG, "onComplete " + "ACTION_USER_INFOR");
                }

                return false;
            case 2:
                // 失败
                text = plat.getName() + " caught error at " + text;
                Log.i(TAG, "handleMessage 2:" + text);
                return false;

            case 3:
                // 取消
                text = plat.getName() + " canceled at " + text;
                Log.i(TAG, "handleMessage 3:" + text);
                return false;

            default:
                return false;
        }
    }

    /** 将action转换为String */
    public static String actionToString(int action) {
        switch (action) {
            case Platform.ACTION_AUTHORIZING:
                return "ACTION_AUTHORIZING";
            case Platform.ACTION_GETTING_FRIEND_LIST:
                return "ACTION_GETTING_FRIEND_LIST";
            case Platform.ACTION_FOLLOWING_USER:
                return "ACTION_FOLLOWING_USER";
            case Platform.ACTION_SENDING_DIRECT_MESSAGE:
                return "ACTION_SENDING_DIRECT_MESSAGE";
            case Platform.ACTION_TIMELINE:
                return "ACTION_TIMELINE";
            case Platform.ACTION_USER_INFOR:
                return "ACTION_USER_INFOR";
            case Platform.ACTION_SHARE:
                return "ACTION_SHARE";
            default: {
                return "UNKNOWN";
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束ShareSDK的统计功能并释放资源
        ShareSDK.stopSDK(this);
    }
}
