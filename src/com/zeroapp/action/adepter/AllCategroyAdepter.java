/* 
 * Copyright (C) 2011 zeroapp Electric Co., Ltd. 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY zeroapp ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH zeroapp ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.action.adepter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.zeroapp.action.R;
import com.zeroapp.action.util.AbViewUtil;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@zeroapp.com) 2014-5-14.
 * @version $Id$
 */

public class AllCategroyAdepter implements ListAdapter {

    private Context context;
    private int[] icons;
    private View[] views;
    public AllCategroyAdepter(Context context, int[] icons) {
        this.context = context;
        this.icons = icons;
        setView();
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return icons[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = View.inflate(context, R.layout.category_select_item, null);
//        ImageView icon = (ImageView) view.findViewById(R.id.category_item_icon);
//        CheckBox checkBox = (CheckBox) view.findViewById(R.id.category_item_box);
//        icon.setImageResource(icons[position]);
//        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.i("zxb", "isChecked--" + isChecked);
//            }
//        });
//        AbViewUtil.measureView(icon);
//        AbViewUtil.measureView(checkBox);
//        int iconHeight = icon.getMeasuredHeight();
//        int iconWidth = icon.getMeasuredWidth();
//        int checkBoxHeight = checkBox.getMeasuredHeight();
//        int checkBoxWidth = checkBox.getMeasuredWidth();
//        LayoutParams params = new LayoutParams(iconWidth + checkBoxWidth, iconHeight
//                + checkBoxHeight);
//
//        view.setLayoutParams(params);
        return views[position];
    }

    private void setView() {
        views = new View[icons.length];
        for (int i = 0; i < icons.length; i++) {
            View view = View.inflate(context, R.layout.category_select_item, null);
            ImageView icon = (ImageView) view.findViewById(R.id.category_item_icon);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.category_item_box);
            icon.setImageResource(icons[i]);
            checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.i("zxb", "isChecked--" + isChecked);
                }
            });
            AbViewUtil.measureView(icon);
            AbViewUtil.measureView(checkBox);
            int iconHeight = icon.getMeasuredHeight();
            int iconWidth = icon.getMeasuredWidth();
            int checkBoxHeight = checkBox.getMeasuredHeight();
            int checkBoxWidth = checkBox.getMeasuredWidth();
//            Log.i("zxb", "icon[" + position + "] Height:" + icon.getMeasuredHeight());
//            Log.i("zxb", "icon[" + position + "] Width:" + icon.getMeasuredWidth());
//            Log.i("zxb", "checkBox Height:" + checkBox.getMeasuredHeight());
//            Log.i("zxb", "checkBox Width:" + checkBox.getMeasuredWidth());

            LayoutParams params = new LayoutParams(iconWidth + checkBoxWidth, iconHeight
                    + checkBoxHeight);

            view.setLayoutParams(params);
            views[i] = view;
        }
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return
     */
    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 1;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return
     */
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
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
     * @return
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
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
     * @param observer
     */
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param observer
     */
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return
     */
    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
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
     * @param position
     * @return
     */
    @Override
    public boolean isEnabled(int position) {
        CheckBox box = (CheckBox) ((ViewGroup) views[position]).getChildAt(1);
        return box.isChecked();
    }

}
