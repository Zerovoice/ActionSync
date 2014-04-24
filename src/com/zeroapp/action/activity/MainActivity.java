
package com.zeroapp.action.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends FragmentActivity implements OnItemClickListener,
        OnItemSelectedListener, OnItemLongClickListener {

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

        initView();
        initCarousel();

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
            TextView msg = (TextView) view.findViewById(R.id.itemsText);
            msg.setText(data.get(i).getMsg());
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
//        carousel.getChildAt(position).setBackgroundColor(Color.RED);
        actionBarTitle.setText(ZeroAppApplication.mDatas.get(position).getMsg());
        // Test code
        Toast.makeText(this, " select position=" + position, Toast.LENGTH_SHORT).show();

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

}
