
package com.zeroapp.action.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.weibo.TencentWeibo;

import com.zeroapp.action.R;
import com.zeroapp.action.database.CategoryDataControler;
import com.zeroapp.action.models.CategoryInfo;
import com.zeroapp.action.models.ZeroAppApplication;
import com.zeroapp.action.view.carousel.CarouselAdapter;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemClickListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemLongClickListener;
import com.zeroapp.action.view.carousel.CarouselAdapter.OnItemSelectedListener;
import com.zeroapp.action.view.carousel.CarouselView;
import com.zeroapp.action.view.carousel.CarouselViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends FragmentActivity implements OnItemClickListener,
        OnItemSelectedListener, OnItemLongClickListener {

    private static final String TAG = "MainActivity";
    private CarouselView carousel;
    private TextView actionBarTitle;
    private List<CategoryInfo> data;
    private CategoryDataControler categoryDataControler;
    private CarouselViewAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = ZeroAppApplication.mDatas;
        categoryDataControler = new CategoryDataControler(this);
        // 初始化ShareSDK
        ShareSDK.initSDK(this);

        initView();
        initCarousel();

        // Test Code:获取所有支持平台实例
//        Platform[] platformList = ShareSDK.getPlatformList(this);
//        if (platformList != null) {
//            for (int i = 0; i < platformList.length; i++) {
//                String name = platformList[i].getName();
//                Log.i(TAG, "platformList name" + i + ":" + name);
//            }
//        }
        // Test Code:获取所有支持平台实例结束

        // Test Code:获取单个支持平台实例
        Platform weibo = ShareSDK.getPlatform(this, TencentWeibo.NAME);
        if (weibo != null) {
            final String name = weibo.getName();
            Log.i(TAG, "plat name" + ":" + name);
            weibo.setPlatformActionListener(new PlatformActionListener() {

                @Override
                public void onError(Platform arg0, int arg1, Throwable onError) {
                    Log.i(TAG, "plat name" + ":" + name + "onError");
                    onError.printStackTrace();

                }

                @Override
                public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                    Log.i(TAG, "plat name" + ":" + name + "onComplete");
                    Set<String> keyset = arg2.keySet();
                    if (keyset != null) {
                        Iterator iterator = keyset.iterator();
                        while(iterator.hasNext()){
                            String key = (String) iterator.next();
                            String value = arg2.get(key).toString();
                            Log.i(TAG, "key" + ":" + key);
                            Log.i(TAG, "value" + ":" + value);

                        }
                    }

                }

                @Override
                public void onCancel(Platform arg0, int arg1) {
                    Log.i(TAG, "plat name" + ":" + name + "onCancel");

                }
            });
//            weibo.authorize();
        }
        // Test Code:获取单个支持平台实例结束

	}

    /**
     * 初始化数据
     */
    private void initView() {
        carousel = (CarouselView) findViewById(R.id.carousel);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_title_tv);
	}

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
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
        carousel.setAdapter(adapter);
    }
	
	
	/**
	 * 替换fragment
	 */
	public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_Container, fragment)
                .commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(CarouselAdapter<?> parent, View view, int position, long id) {
        CategoryInfo c = ZeroAppApplication.mDatas.get(position);
        actionBarTitle.setText(c.getMsg());
        updateData(c);
        // Test code
//        Toast.makeText(this, " select position=" + position, Toast.LENGTH_SHORT).show();

        // Test code
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param c
     */
    private void updateData(CategoryInfo c) {
        Log.i(TAG, "updateData--->" + c.getType());
        // TODO get data via SDK.

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param parent
     */
    @Override
    public void onNothingSelected(CarouselAdapter<?> parent) {

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(CarouselAdapter<?> parent, View view, int position, long id) {

        // Test code
        Toast.makeText(this, " onclick position=" + position, Toast.LENGTH_SHORT).show();
        if (!data.get(position).isLogin()) {
            categoryDataControler.insert(data.get(position));
            ImageView icon = (ImageView) view.findViewById(R.id.itemsIcon);
            icon.setImageResource(0);
            adapter.notifyDataSetChanged();
        }

//        if (data.get(position).isLogin()) {
//            categoryDataControler.delete(data.get(position));
//        }
        // Test code

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
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
        // Test code
        Toast.makeText(this, " onLongclick position=" + position, Toast.LENGTH_SHORT).show();
        if (data.get(position).isLogin()) {
            categoryDataControler.delete(data.get(position));
        }
        // Test code
        return false;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束ShareSDK的统计功能并释放资源
        ShareSDK.stopSDK(this);
    }
}
