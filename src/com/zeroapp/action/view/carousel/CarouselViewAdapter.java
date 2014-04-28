/*
 * Copyright (C) 2013 www.418log.org
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
import android.widget.LinearLayout.LayoutParams;

import com.zeroapp.action.R;
import com.zeroapp.action.util.AbViewUtil;

import java.util.List;
// TODO: Auto-generated Javadoc
/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 名称：CarouselViewAdapter.java 
 * 描述：自定义View适配的Carousel
 * @author zhaoqp
 * @date：2013-8-22 下午4:05:09
 * @version v1.0
 */
public class CarouselViewAdapter extends BaseAdapter {

	/** The m context. */
	private Context mContext;
	
	/** The m views. */
	private List<View> mViews;
	
	/** The m reflected. */
	private boolean mReflected = true;
	
	/** The m carousel image views. */
	private CarouselItemView[]  mCarouselItemViews = null;

	/**
	 * Instantiates a new carousel view adapter.
	 *
	 * @param c the c
	 * @param views the views
	 * @param reflected 反射镜面效果
	 */
	public CarouselViewAdapter(Context c,List<View> views,boolean reflected) {
		mContext = c;
		mViews = views;
		mReflected = reflected;
		setImages();
	}

	/**
	 * 描述：TODO.
	 *
	 * @return the count
	 * @see android.widget.Adapter#getCount()
	 * @author: zhaoqp
	 * @date：2013-8-22 下午4:07:39
	 * @version v1.0
	 */
	public int getCount() {
		if (mViews == null){
			return 0;
		}else{
			return mViews.size();
		}
	}

	/**
	 * 描述：TODO.
	 *
	 * @param position the position
	 * @return the item
	 * @see android.widget.Adapter#getItem(int)
	 * @author: zhaoqp
	 * @date：2013-8-22 下午4:07:39
	 * @version v1.0
	 */
	public Object getItem(int position) {
		return position;
	}

	/**
	 * 描述：TODO.
	 *
	 * @param position the position
	 * @return the item id
	 * @see android.widget.Adapter#getItemId(int)
	 * @author: zhaoqp
	 * @date：2013-8-22 下午4:07:39
	 * @version v1.0
	 */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 描述：TODO.
	 *
	 * @param position the position
	 * @param convertView the convert view
	 * @param parent the parent
	 * @return the view
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 * @author: zhaoqp
	 * @date：2013-8-22 下午4:07:39
	 * @version v1.0
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mCarouselItemViews[position];
		return convertView;
	}
	
	/**
	 * Sets the images.
	 */
	public void setImages(){
		mCarouselItemViews = new CarouselItemView[mViews.size()];
		for(int i = 0; i< mViews.size(); i++){
			View view = mViews.get(i);
			CarouselItemView itemView = new CarouselItemView(mContext);
			itemView.setIndex(i);
			
            AbViewUtil.measureView(view);
            int w = view.getMeasuredWidth();
            int h = view.getMeasuredHeight();
            int l = w >= h ? w : h;
            Log.i("zxb", "h = " + h + ";w = " + w);
            LayoutParams mLayoutParams = new LayoutParams(l + 10, l + 10);
            mLayoutParams.gravity = Gravity.CENTER;
            itemView.addView(view, mLayoutParams);

			mCarouselItemViews[i] = itemView;
			if(i==0){
                // 为初始化的第一个图标添加焦点
                itemView.setBackgroundResource(R.drawable.focus_select_item);
			}
		}
		
	}
	
	

}
