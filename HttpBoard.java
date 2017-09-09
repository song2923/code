package com.example.administrator.board.http;

import com.example.administrator.board.model.ModelBoard_Article;
import com.example.administrator.board.model.ModelUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-16.
 */

public class HttpBoard {

    public List<ModelBoard_Article> getBoard_ArticleList1(Integer start){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_ArticleList1";

        HttpRequest request = null;
        JSONArray response = null;

        List<ModelBoard_Article> result = new ArrayList<>();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("start", String.valueOf(start) );


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

                    ModelBoard_Article item = new ModelBoard_Article();

                    item.setUserid(obj.getString("userid"));
                    item.setTitle(obj.getString("title"));

                    result.add( item );
                }
            }
            else{

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return result;
    }

    public List<ModelBoard_Article> getBoard_ArticleList2(Integer start){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_ArticleList2";

        HttpRequest request = null;
        JSONArray response = null;

        List<ModelBoard_Article> result = new ArrayList<>();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("start", String.valueOf(start) );


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

                    ModelBoard_Article item = new ModelBoard_Article();

                    item.setUserid(obj.getString("userid"));
                    item.setTitle(obj.getString("title"));

                    result.add( item );
                }
            }
            else{

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return result;
    }


    public ModelBoard_Article getBoard_Article_Detail1(Integer position){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_Article_Detail1";

        HttpRequest request = null;
        JSONObject response = null;

        ModelBoard_Article result = new ModelBoard_Article();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("position", String.valueOf(position));
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getJSONObjectResponse();
            }
            else {
                // error
            }

            Gson gson = new Gson();
            result = gson.fromJson(String.valueOf(response), ModelBoard_Article.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }

        return result;
    }

    public ModelBoard_Article getBoard_Article_Detail2(Integer position){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_Article_Detail2";

        HttpRequest request = null;
        JSONObject response = null;

        ModelBoard_Article result = new ModelBoard_Article();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("position", String.valueOf(position));
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getJSONObjectResponse();
            }
            else {
                // error
            }

            Gson gson = new Gson();
            result = gson.fromJson(String.valueOf(response), ModelBoard_Article.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }

        return result;
    }


    public String insertBoard_Article1(String userid, String title, String content) {
        String weburl = "http://10.0.2.1:8080/board/insertBoard_Article1";

        HttpRequest request = null;
        String      response = "";

        int httpCode = 0;

        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("userid", userid);
            request.addParameter("title", title);
            request.addParameter("content", content);
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

    public String insertBoard_Article2(String userid, String title, String content) {
        String weburl = "http://10.0.2.1:8080/board/insertBoard_Article2";

        HttpRequest request = null;
        String      response = "";

        int httpCode = 0;

        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("userid", userid);
            request.addParameter("title", title);
            request.addParameter("content", content);
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



    public ModelBoard_Article getBoard_Article_Articleno1(Integer position){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_Article_Articleno1";

        HttpRequest request = null;
        JSONObject response = null;

        ModelBoard_Article result = new ModelBoard_Article();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("position", String.valueOf(position));
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getJSONObjectResponse();
            }
            else {
                // error
            }

            Gson gson = new Gson();
            result = gson.fromJson(String.valueOf(response), ModelBoard_Article.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }

        return result;
    }

    public ModelBoard_Article getBoard_Article_Articleno2(Integer position){

        String URL_ITEMLIST = "http://10.0.2.1:8080/board/getBoard_Article_Articleno2";

        HttpRequest request = null;
        JSONObject response = null;

        ModelBoard_Article result = new ModelBoard_Article();

        try {
            request = new HttpRequest(URL_ITEMLIST).addHeader("charset", "utf-8");
            request.addParameter("position", String.valueOf(position));
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getJSONObjectResponse();
            }
            else {
                // error
            }

            Gson gson = new Gson();
            result = gson.fromJson(String.valueOf(response), ModelBoard_Article.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

        }

        return result;
    }

    public String transDeleteArticle(String articleno) {
        String weburl = "http://10.0.2.1:8080/board/transDeleteArticle";

        HttpRequest request = null;
        String      response = "";

        int httpCode = 0;

        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("articleno", articleno);

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

    public String updateBoard_ArticleList(String content, String articleno) {
        String weburl = "http://10.0.2.1:8080/board/updateBoard_ArticleList";

        HttpRequest request = null;
        String response = "";

        int httpCode = 0;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("content", content);
            request.addParameter("articleno", articleno);

            httpCode = request.post();

            if (HttpURLConnection.HTTP_OK == httpCode) {
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

}
