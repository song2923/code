package com.example.administrator.board.board;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.board.R;
import com.example.administrator.board.model.ModelBoard_Article;
import com.example.administrator.board.model.ModelUser;

import java.util.List;

/**
 * Created by Administrator on 2017-08-01.
 */

public class BoardAdapterEx extends ArrayAdapter<ModelBoard_Article>  {

    public Context context = null;


    class ViewHolder {
        TextView txt_userid;
        TextView txt_title;
    }

    public BoardAdapterEx(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelBoard_Article> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {



        View itemLayout = super.getView(position, convertView, parent);

        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if (viewHolder == null) {

            viewHolder = new ViewHolder();
            viewHolder.txt_userid = (TextView) itemLayout.findViewById(R.id.tv_userid);
            viewHolder.txt_title = (TextView) itemLayout.findViewById(R.id.tv_title);

            itemLayout.setTag(viewHolder); // 한번 찾은 뷰를 저장해 둠 // 찾는 속도 최적화

        }

        viewHolder.txt_userid.setText(this.getItem(position).getUserid());
        viewHolder.txt_title.setText(this.getItem(position).getTitle());

        return itemLayout;
    }


}

