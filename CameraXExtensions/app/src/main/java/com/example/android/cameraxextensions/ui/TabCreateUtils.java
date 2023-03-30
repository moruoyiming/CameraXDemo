package com.example.android.cameraxextensions.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.android.cameraxextensions.R;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

//菜单指示器创建工具类
public class TabCreateUtils {
    /**
     * 类型：不关联ViewPager
     * 字：选中橘色，未选中黑色，加粗
     * 指示器：指示器长度和文字长度相同，橘色
     */
    public interface onTitleClickListener{
        void onTitleClick(int index);
    }
    public static void setOrangeTab(Context context, MagicIndicator magicIndicator, String[] tabNames , onTitleClickListener listener) {
        FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setSkimOver(true);
        int padding = UIUtil.getScreenWidth(context) / 2;
        commonNavigator.setRightPadding(padding);
        commonNavigator.setLeftPadding(padding);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return tabNames == null ? 0 : tabNames.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.WHITE);
                colorTransitionPagerTitleView.setSelectedColor(Color.YELLOW);
                colorTransitionPagerTitleView.setTextSize(16);
                colorTransitionPagerTitleView.setText(tabNames[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        if (listener!=null)listener.onTitleClick(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                indicator.setColors(ContextCompat.getColor(context, R.color.tab_orange));
//                indicator.setRoundRadius(3);
                return null;
            }
        });

        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
    }
}