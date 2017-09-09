package com.example.administrator.board.board;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.board.R;
import com.example.administrator.board.http.HttpBoard;
import com.example.administrator.board.http.HttpBoardComment;
import com.example.administrator.board.model.ModelBoard_Article;
import com.example.administrator.board.model.ModelBoard_Article_Comment;
import com.example.administrator.board.model.ModelUser;

import java.util.ArrayList;
import java.util.List;


public class free_DetailActivity extends AppCompatActivity {


    private TextView tv_id, tv_title, tv_submit;
    private EditText edt_content, edt_comment;

    private ImageView img_comment;
    private ModelUser modelUser;
    private ModelBoard_Article modelBoard_article;

    private ListView lv_freecomment;

    private List<ModelBoard_Article_Comment> list;

    private boolean calling  = false;

    private CommnetAdapterEx adapter = null;

    private AlertDialog.Builder alert_confirm;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_free);

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

        edt_content = (EditText) findViewById(R.id.edt_content);
        edt_comment = (EditText) findViewById(R.id.edt_comment);

        img_comment = (ImageView) findViewById(R.id.img_comment);

        lv_freecomment = (ListView) findViewById(R.id.lv_freecomment);

        //로그인 사용자 이름 가져옴
        Bundle bundle = getIntent().getExtras();
        modelUser = bundle.getParcelable("data");


        Intent intent = getIntent();
        ModelBoard_Article modelBoard_article = new ModelBoard_Article();
        modelBoard_article = (ModelBoard_Article) intent.getSerializableExtra("data");
        final int r = Integer.parseInt(intent.getStringExtra("value"));



        final int number = r + 1;
        final String strnumber = String.valueOf(number);

        new HttpDetail().execute(r+1);
        new HttpfreeCommnetList().execute(strnumber, "1");


        // 데이터 생성
        list = new ArrayList<ModelBoard_Article_Comment>();
        adapter = new CommnetAdapterEx(this, R.layout.acitivity_free_list_commnet, R.id.tv_commentmemo, list);
        lv_freecomment.setAdapter(adapter);

        lv_freecomment.setOnScrollListener(new AbsListView.OnScrollListener() {

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
                        String a = String.valueOf(adapter.getCount());
                        new HttpfreeCommnetList().execute(a, a);
                    }
                }
            }
        });

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetArticleno_comment().execute(number);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.writer_update:
                Intent intent = getIntent();
                final int r = Integer.parseInt(intent.getStringExtra("value"));
                int number = r + 1;
                String strnumber = String.valueOf(number);
                new HttpGetArticleno_update().execute(number);
                finish();
                break;

            case R.id.writer_delete:
                alert_confirm = new AlertDialog.Builder(this);
                alert_confirm.setMessage("삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = getIntent();
                                final int r = Integer.parseInt(intent.getStringExtra("value"));
                                int number = r + 1;
                                String strnumber = String.valueOf(number);
                                new HttpGetArticleno_delete().execute(number);
                                finish();
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert = alert_confirm.create();
                alert.show();
                break;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String a = String.valueOf(adapter.getCount());
        new HttpfreeCommnetList().execute(a, a);
    }


    public class HttpDetail extends AsyncTask<Integer, Integer, ModelBoard_Article> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("게시글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected ModelBoard_Article doInBackground(Integer... params) {
            Integer position = params[0];

            ModelBoard_Article result = new HttpBoard().getBoard_Article_Detail2(position);
            return result;
        }

        @Override
        protected void onPostExecute(ModelBoard_Article result) {
            super.onPostExecute(result);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            //서버 저장 성공 시
            if (result != null) {
                ModelBoard_Article modelBoard_article = result;

                tv_id.setText(modelBoard_article.getUserid());
                tv_title.setText(modelBoard_article.getTitle());
                edt_content.setText(modelBoard_article.getContent());
            }
        }
    }

    private class HttpfreeCommnetList extends AsyncTask<String, String, List<ModelBoard_Article_Comment>> {

        private ProgressDialog waitDlg = null;
        private boolean calling  = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("댓글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected List<ModelBoard_Article_Comment> doInBackground(String... params) {
            String articleno = params[0];
            List<ModelBoard_Article_Comment> list = new HttpBoardComment().getBoard_Article_CommentList(articleno, "1");
            return list;
        }

        @Override
        protected void onPostExecute(List<ModelBoard_Article_Comment> result) {
            super.onPostExecute(result);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            calling = false;
            adapter.addAll(result);

        }
    }

    private class HttpfreeCommnetinsert extends AsyncTask<String, String, String> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("댓글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String articleno = params[0];
            String userid = params[1];
            String commentmemo = params[2];
            String result = new HttpBoardComment().insertBoard_Article_Comment(articleno, userid, commentmemo);
            return result;
        }

        @Override
        protected void onPostExecute( String response ) {
            super.onPostExecute(response);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }


        }
    }

    private class HttpfreeBoardDelete extends AsyncTask<String, String, String> {

        private ProgressDialog waitDlg = null;
        private boolean calling  = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("게시글 삭제하는 중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String articleno = params[0];
            String result = new HttpBoard().transDeleteArticle(articleno);
            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    public class HttpGetArticleno_delete extends AsyncTask<Integer, Integer, ModelBoard_Article> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("게시글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected ModelBoard_Article doInBackground(Integer... params) {
            Integer position = params[0];

            ModelBoard_Article result = new HttpBoard().getBoard_Article_Articleno2(position);
            return result;
        }

        @Override
        protected void onPostExecute(ModelBoard_Article result) {
            super.onPostExecute(result);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            //서버 저장 성공 시
            if (result != null) {
                ModelBoard_Article modelBoard_article = result;

                String articleno = String.valueOf(modelBoard_article.getArticleno());
                new HttpfreeBoardDelete().execute(articleno);
            }
        }
    }

    public class HttpGetArticleno_update extends AsyncTask<Integer, Integer, ModelBoard_Article> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("업데이트하는 중");
            waitDlg.show();
        }

        @Override
        protected ModelBoard_Article doInBackground(Integer... params) {
            Integer position = params[0];

            ModelBoard_Article result = new HttpBoard().getBoard_Article_Articleno2(position);
            return result;
        }

        @Override
        protected void onPostExecute(ModelBoard_Article result) {
            super.onPostExecute(result);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            //서버 저장 성공 시
            if (result != null) {
                ModelBoard_Article modelBoard_article = result;

                String articleno = String.valueOf(modelBoard_article.getArticleno());

                String content = edt_content.getText().toString();

                new HttpArticleUpdate().execute(content, articleno);

            }
        }
    }

    public class HttpGetArticleno_comment extends AsyncTask<Integer, Integer, ModelBoard_Article> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("댓글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected ModelBoard_Article doInBackground(Integer... params) {
            Integer position = params[0];

            ModelBoard_Article result = new HttpBoard().getBoard_Article_Articleno2(position);
            return result;
        }

        @Override
        protected void onPostExecute(ModelBoard_Article result) {
            super.onPostExecute(result);

            // 프로그래스바 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            //서버 저장 성공 시
            if (result != null) {
                ModelBoard_Article modelBoard_article = result;

                String articleno = String.valueOf(modelBoard_article.getArticleno());
                String userid = modelUser.getUserid().toString();
                String commentmemo = edt_comment.getText().toString();

                new HttpfreeCommnetinsert().execute(articleno, userid, commentmemo);

                edt_comment.setText("");
            }
        }
    }

    public class HttpArticleUpdate extends AsyncTask<String, String, String> {

        private ProgressDialog waitDlg = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( free_DetailActivity.this );
            waitDlg.setMessage("게시판 수정글 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String content = params[0];
            String articleno = params[1];

            String result = new HttpBoard().updateBoard_ArticleList(content, articleno);
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //서버 요청 완료 후 Waiting dialog를 제거한다.
            if( waitDlg != null ) {
                waitDlg.dismiss();
                waitDlg = null;
            }



        }
    }
}
