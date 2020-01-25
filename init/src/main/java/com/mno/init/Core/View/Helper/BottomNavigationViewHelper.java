package com.mno.init.Core.View.Helper;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.reflect.Field;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mno.init.Core.Object.MLog;
import com.mno.init.R;

/**
 * Created by pablo on 30/08/17.
 */

public class BottomNavigationViewHelper {

    public static void removeShiftMode(BottomNavigationView view) {
        removeShiftMode(view, false);
    }

    @SuppressWarnings("RestrictedApi")
    public static void removeShiftMode(BottomNavigationView view, boolean hideText) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            BottomNavigationItemView item;
            ImageView icon;
            FrameLayout.LayoutParams params;
            View child, smallText, largeText;
            ViewGroup parent;

            for (int i = 0; i < menuView.getChildCount(); i++) {
                child = menuView.getChildAt(i);
                item = (BottomNavigationItemView) child;

                item.setShifting(false);
                item.setChecked(item.getItemData().isChecked());

                if(hideText) {
                    largeText = item.findViewById(R.id.largeLabel);
                    smallText = item.findViewById(R.id.smallLabel);
                    icon = (ImageView) item.findViewById(R.id.icon);

                    if(largeText != null && smallText != null) {
                        parent = (ViewGroup) smallText.getParent();

                        parent.removeView(smallText);
                        parent.removeView(largeText);
                    }

                    params = (FrameLayout.LayoutParams) icon.getLayoutParams();
                    params.gravity = Gravity.CENTER;
                    params.width = FrameLayout.LayoutParams.MATCH_PARENT;
                    params.height = FrameLayout.LayoutParams.MATCH_PARENT;
                }
            }
        } catch (NoSuchFieldException e) {
            MLog.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            MLog.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

}