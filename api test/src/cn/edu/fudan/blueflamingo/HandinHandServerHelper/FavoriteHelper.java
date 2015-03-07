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

public class FavoriteHelper {

	public int isQuestionFavorite(int uId, int qId){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String qid = String.valueOf(qId);
			String temp;
			int isfav;
			temp = sendPost(url, "op=showQuestionStatus&uid=" + uid +"&qid=" + qid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
		
	public int isAnswerFavorite(int uId, int aId){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String aid = String.valueOf(aId);
			String temp;
			int isfav;
			temp = sendPost(url, "op=showAnswerStatus&uid=" + uid +"&aid=" + aid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
	
	public int switchQuestionFavorite(int uId, int qId){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String qid = String.valueOf(qId);
			String temp;
			int isfav;
			temp = sendPost(url, "op=toggleQuestion&uid=" + uid +"&qid=" + qid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
		
	public int switchAnswerFavorite(int uId, int aId){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String aid = String.valueOf(aId);
			String temp;
			int isfav;
			temp = sendPost(url, "op=toggleAnswer&uid=" + uid +"&aid=" + aid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
	
	public ArrayList<ExQuestion>  listQuestions(int uId){
		String url =   "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String temp;
			ArrayList<Integer> qidlist = new ArrayList<Integer>();
			temp = sendPost(url, "op=listQuestions&uid=" + uid);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				qidlist = mapper.readValue(temp, new TypeReference<ArrayList<Integer>>() {});
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			Utility utility = new Utility();
			ArrayList<ExQuestion> exqlist = utility.qidtoExQuestion(qidlist);
			
			return exqlist;
		
		}

	public ArrayList<ExAnswer>  listAnswers(int uId){
		String url =   "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String temp;
			ArrayList<Integer> aidlist = new ArrayList<Integer>();
			temp = sendPost(url, "op=listAnswers&uid=" + uid);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				aidlist = mapper.readValue(temp, new TypeReference<ArrayList<Integer>>() {});
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
			return Utility.aidtoExAnswer(aidlist);
		
		}

	public int isUserFavorite(int uId, int uId2){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String uid2 = String.valueOf(uId2);
			String temp;
			int isfav;
			temp = sendPost(url, "op=showUserStatus&uid=" + uid +"&uid2=" + uid2);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
	
	public int switchUserFavorite(int uId, int uId2){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String uid2 = String.valueOf(uId2);
			String temp;
			int isfav;
			temp = sendPost(url, "op=toggleUser&uid=" + uid +"&uid2=" + uid2);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
		
	public ArrayList<User>  listUsers(int uId){
		String url =   "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(uId);
			String temp;
			ArrayList<Integer> uidlist = new ArrayList<Integer>();
			temp = sendPost(url, "op=listUsers&uid=" + uid);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				uidlist = mapper.readValue(temp, new TypeReference<ArrayList<Integer>>() {});
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
			return Utility.uidtoUser(uidlist);
		
		}
	
	public String sendPost(String url, String param) {
		//System.out.println(param);
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
