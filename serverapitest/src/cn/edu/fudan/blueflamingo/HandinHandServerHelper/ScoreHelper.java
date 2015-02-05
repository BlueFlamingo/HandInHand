package cn.edu.fudan.blueflamingo.HandinHandServerHelper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
public class ScoreHelper {

	public int isApprove(int uId, int aId){
		String url =  "http://121.199.64.117:8888/HandInHand/score.php";
			
			String uid = String.valueOf(uId);
			String aid = String.valueOf(aId);
			String temp;
			int up;
			temp = sendPost(url, "op=showScore1Status&uid=" + uid +"&aid=" + aid);
			up = Integer.valueOf(temp);
			return up;
		  }
		
	public int isOppose(int uId, int aId){
		String url =  "http://121.199.64.117:8888/HandInHand/score.php";
			
			String uid = String.valueOf(uId);
			String aid = String.valueOf(aId);
			String temp;
			int down;
			temp = sendPost(url, "op=showScore2Status&uid=" + uid +"&aid=" + aid);
			down = Integer.valueOf(temp);
			return down;
		  }
	
	public int switchApprove(int uId, int aId){
		String url =  "http://121.199.64.117:8888/HandInHand/score.php";
			
			String uid = String.valueOf(uId);
			String aid = String.valueOf(aId);
			String temp;
			int up;
			temp = sendPost(url, "op=toggleScore1&uid=" + uid +"&aid=" + aid);
			up = Integer.valueOf(temp);
			return up;
		  }
		
	public int switchOppose(int uId, int aId){
		String url =  "http://121.199.64.117:8888/HandInHand/score.php";
			
			String uid = String.valueOf(uId);
			String aid = String.valueOf(aId);
			String temp;
			int down;
			temp = sendPost(url, "op=toggleScore2&uid=" + uid +"&aid=" + aid);
			down = Integer.valueOf(temp);
			return down;
		  }
	
	public String sendPost(String url, String param) {
		System.out.println(param);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            
            URLConnection conn = realUrl.openConnection();
            
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            out = new PrintWriter(conn.getOutputStream());
            
            out.print(param);
            
            out.flush();
            
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
       // System.out.println(result);
        return result;
    }    
}
