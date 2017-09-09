package com.example.administrator.board.board;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.board.R;
import com.example.administrator.board.http.HttpBoard;
import com.example.administrator.board.http.HttpBoardComment;
import com.example.administrator.board.model.ModelBoard_Article;
import com.example.administrator.board.model.ModelUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-08-21.
 */

public class free_BoardActivity extends AppCompatActivity {

    private ListView lv_freeboard = null;
    private ImageView img_writer = null;

    private BoardAdapterEx adapter = null;

    private boolean calling  = false;

    private ModelUser modelUser;
    private ModelBoard_Article modelBoard_article;

    private List<ModelBoard_Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_free);

        lv_freeboard = (ListView) findViewById(R.id.lv_freeboard);
        img_writer = (ImageView) findViewById(R.id.img_writer);

        //로그인 사용자 이름 가져옴
        Bundle bundle = getIntent().getExtras();
        modelUser = bundle.getParcelable("data");

        // 데이터 생성
        list = new ArrayList<ModelBoard_Article>();
        adapter = new BoardAdapterEx(this, R.layout.acitivity_free_list, R.id.tv_title, list);
        lv_freeboard.setAdapter(adapter);

        lv_freeboard.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                // Sample calculation to determine if the last item is fully visible.
                final int lastItem = firstVisibleItem + visibleItemCount;
                if(lastItem == totalItemCount) {

                    if( !calling ) {
                        calling = true;
                        //to avoid multiple calls for last item
                        new HttpfreeBoardList().execute(adapter.getCount(), adapter.getCount()+2);
                    }
                }
            }
        });

        img_writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(free_BoardActivity.this, free_WritingActivity.class);
                intent.putExtra("data", modelUser);
                startActivityForResult(intent,10000);
            }
        });

        lv_freeboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(free_BoardActivity.this, free_DetailActivity.class);
                ModelBoard_Article modelBoard_article = list.get(position);
                String value = String.valueOf(position);
                intent.putExtra("data", (Serializable) modelBoard_article);
                intent.putExtra("value", value);
                intent.putExtra("data", modelUser);
                startActivityForResult(intent,10000);



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        new HttpfreeBoardList().execute(adapter.getCount(), adapter.getCount());
    }



    public class HttpfreeBoardList extends AsyncTask<Integer, Integer, List<ModelBoard_Article>> {

        private ProgressDialog waitDlg = null;
        private boolean calling  = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_BoardActivity.this );
            waitDlg.setMessage("게시글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected List<ModelBoard_Article> doInBackground(Integer... params) {

            List<ModelBoard_Article> list = new HttpBoard().getBoard_ArticleList2(1);
            return list;

        }


        @Override
        protected void onPostExecute(List<ModelBoard_Article> result) {
            super.onPostExecute(result);

            //서버 요청 완료 후 Waiting dialog를 제거한다.
            if( waitDlg != null ) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            //서버 연결 성공 시
            Intent intent = new Intent(free_BoardActivity.this, free_DetailActivity.class);

            intent.putExtra("data",modelUser);


            calling = false;
            adapter.addAll(result);

        }
    }

}
