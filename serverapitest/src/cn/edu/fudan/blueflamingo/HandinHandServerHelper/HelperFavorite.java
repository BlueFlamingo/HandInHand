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

public class HelperFavorite {
    
	public int showQuestionStatus(int idu, int idq){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php .php";
			
			String uid = String.valueOf(idu);
			String qid = String.valueOf(idq);
			String temp;
			int isfav;
			temp = sendPost(url, "op=showQuestionStatus&uid=" + uid +"&qid=" + qid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
		
	public int showAnswerStatus(int idu, int ida){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php .php";
			
			String uid = String.valueOf(idu);
			String aid = String.valueOf(ida);
			String temp;
			int isfav;
			temp = sendPost(url, "op=showAnswerStatus&uid=" + uid +"&aid=" + aid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
	
	public int toggleQuestion(int idu, int idq){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php .php";
			
			String uid = String.valueOf(idu);
			String qid = String.valueOf(idq);
			String temp;
			int isfav;
			temp = sendPost(url, "op=toggleQuestion&uid=" + uid +"&qid=" + qid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
		
	public int toggleAnswer(int idu, int ida){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php .php";
			
			String uid = String.valueOf(idu);
			String aid = String.valueOf(ida);
			String temp;
			int isfav;
			temp = sendPost(url, "op=toggleAnswer&uid=" + uid +"&aid=" + aid);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
	
	public ArrayList<Integer>  listQuestions(int id){
		String url =   "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(id);
			String temp;
			ArrayList<Integer> qidlist = new ArrayList<Integer>();
			temp = sendPost(url, "op=listQuestions&uid=" + uid);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				qidlist = mapper.readValue(temp, new TypeReference<ArrayList<Integer>>() {});
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return qidlist;
		
		}

	public ArrayList<Integer>  listAnswers(int id){
		String url =   "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(id);
			String temp;
			ArrayList<Integer> aidlist = new ArrayList<Integer>();
			temp = sendPost(url, "op=listQuestions&uid=" + uid);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				aidlist = mapper.readValue(temp, new TypeReference<ArrayList<Integer>>() {});
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return aidlist;
		
		}

	public int showUserStatus(int id, int id2){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php .php";
			
			String uid = String.valueOf(id);
			String uid2 = String.valueOf(id2);
			String temp;
			int isfav;
			temp = sendPost(url, "op=showUserStatus&uid=" + uid +"&uid2=" + uid2);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
	
	public int toggleUser(int id, int id2){
		String url =  "http://121.199.64.117:8888/HandInHand/favorite.php .php";
			
			String uid = String.valueOf(id);
			String uid2 = String.valueOf(id2);
			String temp;
			int isfav;
			temp = sendPost(url, "op=toggleUser&uid=" + uid +"&uid2=" + uid2);
			isfav = Integer.valueOf(temp);
			return isfav;
		  }
		
	public ArrayList<Integer>  listUsers(int id){
		String url =   "http://121.199.64.117:8888/HandInHand/favorite.php";
			
			String uid = String.valueOf(id);
			String temp;
			ArrayList<Integer> uidlist = new ArrayList<Integer>();
			temp = sendPost(url, "op=listUsers&uid=" + uid);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				uidlist = mapper.readValue(temp, new TypeReference<ArrayList<Integer>>() {});
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return uidlist;
		
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
        System.out.println(result);
        return result;
    }    
	
    public String uploadFile(String filePath) {
    	
    	String urlStr = "http://121.199.64.117:8888/HandInHand/upload.php";
    	Map<String, String> textMap = new HashMap<String, String>();
        textMap.put("name", "testname");  
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("file", filePath);  
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn  
                    .setRequestProperty("User-Agent",  
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type",  
                    "multipart/form-data; boundary=" + BOUNDARY);  
  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            // text  
            if (textMap != null) {  
                StringBuffer strBuf = new StringBuffer();  
                Iterator iter = textMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
                            "\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\""  
                            + inputName + "\"\r\n\r\n");  
                    strBuf.append(inputValue);  
                }  
                out.write(strBuf.toString().getBytes());  
            }  
  
            // file  
            if (fileMap != null) {  
                Iterator iter = fileMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    File file = new File(inputValue);  
                    String filename = file.getName();  
                    String contentType = new MimetypesFileTypeMap()  
                            .getContentType(file);  
                    if (filename.endsWith(".png")) {  
                        contentType = "image/png";  
                    }  
                    if (contentType == null || contentType.equals("")) {  
                        contentType = "application/octet-stream";  
                    }  
  
                    StringBuffer strBuf = new StringBuffer();  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
                            "\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\""  
                            + inputName + "\"; filename=\"" + filename  
                            + "\"\r\n");  
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  
  
                    out.write(strBuf.toString().getBytes());  
  
                    DataInputStream in = new DataInputStream(  
                            new FileInputStream(file));  
                    int bytes = 0;  
                    byte[] bufferOut = new byte[1024];  
                    while ((bytes = in.read(bufferOut)) != -1) {  
                        out.write(bufferOut, 0, bytes);  
                    }  
                    in.close();  
                }  
            }  
  
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  
  
            // 读取返回数据  
            StringBuffer strBuf = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            res = strBuf.toString();  
            reader.close();  
            reader = null;  
        } catch (Exception e) {  
            System.out.println("发送POST请求出错。" + urlStr);  
            e.printStackTrace();  
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  
        System.out.println(res);
        return res;  
    }  
	
}