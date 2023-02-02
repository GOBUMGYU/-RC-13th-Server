package com.example.demo.src.user;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OauthService {

    public String getNaverAccessToken(String code)  {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://nid.naver.com/oauth2.0/token";
        try{
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //HttpURLConnection: http 통신을 수행할 객체
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); //InputStream으로 서버로 부터 응답을 받겠다는 옵션
            //post or put 요청과 같이 요청 본문을 보내려면 true로 설정해야 한다.

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=dPmEzEp2e9Bx6JxPI3qY");
            sb.append("&client_secret=EnEEdVATzL");
            sb.append("&redirect_uri=http://localhost:9000/oauth/naver");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = connection.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null){
                result += line;
            }
            System.out.println("responseBody = " + result);

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(result);

            access_Token = (String) object.get("access_token");
            refresh_Token = (String) object.get("refresh_token");
            System.out.println("access_Token = " + access_Token);
            System.out.println("refresh_Token = " + refresh_Token);

            br.close();
            bw.close();


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return access_Token;
    }
}
