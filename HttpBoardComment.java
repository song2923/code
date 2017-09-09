package com.example.administrator.board.http;

import com.example.administrator.board.model.ModelBoard_Article;
import com.example.administrator.board.model.ModelBoard_Article_Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-08-22.
 */

public class HttpBoardComment {

    public String insertBoard_Article_Comment(String articleno, String userid, String commentmemo) {
        String weburl = "http://10.0.2.1:8080/board/insertBoard_Article_Comment";

        HttpRequest request = null;
        String      response = "";

        int httpCode = 0;

        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("articleno", articleno);
            request.addParameter("userid", userid);
            request.addParameter("commentmemo", commentmemo);
            httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) {

                response = request.getStringResponse();

            } else {
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }

    public List<ModelBoard_Article_Comment> getBoard_Article_CommentList(String articleno, String start){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_Article_CommentList";

        HttpRequest request = null;
        JSONArray response = null;

        List<ModelBoard_Article_Comment> result = new ArrayList<>();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("articleno", articleno);
            request.addParameter("start", start);


            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getJSONArrayResponse();
            }
            else {
                // error
            }

            if (response != null) {
                for (int i=0; i<response.length(); i++){
                    JSONObject obj=(JSONObject)response.get(i);

                    ModelBoard_Article_Comment item = new ModelBoard_Article_Comment();

                    item.setCommentmemo(obj.getString("commentmemo"));
                    item.setUserid(obj.getString("userid"));



                    result.add( item );
                }
            }
            else{

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }  finally {
            request.close();
        }

        return result;
    }
}
