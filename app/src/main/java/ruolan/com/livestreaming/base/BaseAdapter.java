package ruolan.com.livestreaming.base;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class BaseAdapter<T> extends ArrayAdapter<T> {

    public BaseAdapter(Context context, List<T> dataList) {
        super(context, 0, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        T data = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    getViewLayoutId(), parent, false);
            viewHolder = new ViewHolder(getContext(), convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        initData(viewHolder, data, position);
        // super.getView(position, convertView, parent)

        return convertView;
    }

    protected abstract int getViewLayoutId();

    protected void initItemView(ViewHolder viewHolder, T data) {

    }

    protected abstract void initData(ViewHolder viewHolder, T data, int position);

    public static class ViewHolder {
        private SparseArray<View> mViews;
        private View mItemView;
        private int mLayoutId;

        public ViewHolder(Context context, View itemView) {
            mItemView = itemView;
            mViews = new SparseArray<>();
        }

        /**
         * 通过viewId获取控件
         *
         * @param viewId view控件id
         * @return  View
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mItemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public View getConvertView() {
            return mItemView;
        }

        public int getLayoutId() {
            return mLayoutId;
        }

        /**
         * 关于事件
         */
        public ViewHolder setOnClickListener(int viewId,
                                             View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        public ViewHolder setOnTouchListener(int viewId,
                                             View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        public ViewHolder setOnLongClickListener(int viewId,
                                                 View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }
    }
}
