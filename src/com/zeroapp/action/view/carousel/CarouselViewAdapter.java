/*
 * Copyright (C) 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zeroapp.action.view.carousel;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.zeroapp.action.R;
import com.zeroapp.action.models.CategoryInfo;
import com.zeroapp.action.util.AbViewUtil;

import java.util.ArrayList;
import java.util.List;
public class CarouselViewAdapter extends BaseAdapter {

	/** The m context. */
	private Context mContext;
	
	/** The m views. */
    private List<CategoryInfo> data;
	
	/** The m reflected. */
	private boolean mReflected = true;
	
    /** The m carousel image views. */
    private CarouselItemView[] mCarouselItemViews = null;

    public CarouselViewAdapter(Context c, List<CategoryInfo> data, boolean reflected) {
		mContext = c;
        mReflected = reflected;
        setImages(data);
	}
	public int getCount() {
        return data == null ? 0 : data.size();
	}

	public Object getItem(int position) {
        return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mCarouselItemViews[position];
        return convertView;
    }

    /**
     * Sets the images.
     * 
     * @param data
     */
    public synchronized void setImages(List<CategoryInfo> data) {
        this.data = data;
        List<View> mViews = new ArrayList<View>();
        for (int i = 0; i < data.size(); i++) {
            View view = View.inflate(mContext, R.layout.item_carousel_view, null);
            ImageView icon = (ImageView) view.findViewById(R.id.itemsIcon);
            icon.setImageResource(data.get(i).getIcon());
            // 删除图标下的标示分类名称的TextView
//            TextView msg = (TextView) view.findViewById(R.id.itemsText);
//            msg.setText(data.get(i).getMsg());
            mViews.add(view);
        }

        mCarouselItemViews = new CarouselItemView[mViews.size()];
        for (int i = 0; i < mViews.size(); i++) {
            View view = mViews.get(i);
            CarouselItemView itemView = new CarouselItemView(mContext);
            itemView.setIndex(i);

            AbViewUtil.measureView(view);
            int w = view.getMeasuredWidth();
            int h = view.getMeasuredHeight();
            if (w != h) {
                w = w >= h ? w : h;
                Log.i("zxb", "h = " + h + ";w = " + w);
            }
            LayoutParams mLayoutParams = new LayoutParams(w + 10, w + 10);
            mLayoutParams.gravity = Gravity.CENTER;
            itemView.addView(view, mLayoutParams);

            mCarouselItemViews[i] = itemView;
//            notifyDataSetChanged();
//            Log.i("zxb", "notifyDataSetChanged");
        }
	}

}
