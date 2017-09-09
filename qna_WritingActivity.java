package com.example.administrator.board.board;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.board.R;
import com.example.administrator.board.http.HttpBoard;
import com.example.administrator.board.model.ModelUser;


public class qna_WritingActivity extends AppCompatActivity {

    private ImageView btn_cancle;
    private TextView btn_completed;

    private EditText edt_title;
    private EditText edt_content;

    private SQLiteDatabase db = null;

    private ModelUser modelUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_qna);

        btn_cancle = (ImageView) findViewById(R.id.btn_cancle);
        btn_completed = (TextView) findViewById(R.id.btn_completed);

        edt_title = (EditText) findViewById(R.id.edt_title);
        edt_content = (EditText) findViewById(R.id.edt_content);

        //로그인 사용자 이름 가져옴
        Bundle bundle = getIntent().getExtras();
        modelUser = bundle.getParcelable("data");

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //새창 띄우기
                //getApplicationContext()는 현재 프로그램의 환경 정보
                Intent intent = new Intent(qna_WritingActivity.this, qna_BoardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 게시판 글 작성 후 완료버튼 클릭 시
        btn_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = modelUser.getUserid().toString();
                String title = edt_title.getText().toString();
                String content = edt_content.getText().toString();

                // 서버 저장
                new HttpRequestAsyncTask().execute(userid, title, content);

            }
        });
    }

    public class HttpRequestAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog waitDlg = null;

        // 작업을 시작하기 전에 필요한 UI를 화면에 보여준다 // 시계
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(qna_WritingActivity.this);
            waitDlg.setMessage("게시글 저장중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String title = params[1];
            String content = params[2];
            String result = new HttpBoard().insertBoard_Article1(userid, title, content);
            return result;
        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            // 프로그래스바 감추기
            if(waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }

            //서버 저장 성공 시
            if(response != null){


                finish();
            }
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);

            // 프로그래스바 감추기
            if(waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }



}
