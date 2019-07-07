package com.example.sandy.accountingapp.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.sandy.accountingapp.R;

public class FabBehavior extends FloatingActionButton.Behavior {

    private boolean visible = true;


    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

//    @Override
//    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        if (mToolbar != null) {
//            mToolbar = coordinatorLayout.findViewById(R.id.tool_bar);
//        }
//        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
//    }


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 10 && visible) {
            visible = false;
            onHide(child);
        } else if (dyConsumed < -10 && !visible) {
            visible = true;
            onShow(child);
        }
    }

    private void onHide(FloatingActionButton fab) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(fab.getHeight() + ((CoordinatorLayout.LayoutParams)layoutParams).bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    private void onShow(FloatingActionButton fab) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new AccelerateInterpolator(3));
    }
}
