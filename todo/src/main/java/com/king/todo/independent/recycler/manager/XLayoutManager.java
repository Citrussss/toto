package com.king.todo.independent.recycler.manager;

import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/8 8:33 PM
 * 只有编译器可能不骗你。
 */
public class XLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        offsetChildrenHorizontal(-dx);
        scrapOutSetViews(recycler);
        fill(recycler, state);
        return dx;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0 && state.isPreLayout()) return;
        detachAndScrapAttachedViews(recycler);
        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int itemCount = state.getItemCount();
        int xOffset = 15, yOffset = 15;

        for (int i = 0; i <= 10; i++) {
            RectF rectF = new RectF();
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 15, 15);
            int width = getDecoratedMeasuredWidth(view);
            int heght = getDecoratedMeasuredHeight(view);
            layoutDecorated(view, xOffset, yOffset, width - xOffset / 2, heght - yOffset);
            xOffset = yOffset += 150;
        }
    }

    private void scrapOutSetViews(RecyclerView.Recycler recycler) {
        int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View view = getChildAt(i);
            if (!RectF.intersects(new RectF(0, 0, getWidth(), getHeight()), new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))) {
                int position = getPosition(view);
                removeAndRecycleViewAt(i, recycler);
            }
        }
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

}
