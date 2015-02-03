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

public class HelperUser {

	public  Integer register(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String entry = "";
			try {
				entry = mapper.writeValueAsString(u);
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
			String temp;
			Integer uid;
			temp = sendPost(url, "op=register&entry=" + entry);
			uid = Integer.valueOf(temp);
			return uid;
		  }
		
	    public Integer update(User u){
	    String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			ObjectMapper mapper = new ObjectMapper();
			String entry = "";
			try {
				entry = mapper.writeValueAsString(u);
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
			String temp;
			Integer num;
	        temp = sendPost(url, "op=update&entry=" + entry);
	        num = Integer.valueOf(temp);
			return num;
	    }

	    public Integer count(String username){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			String temp;
			Integer count;
			temp = sendPost(url, "op=count&username=" + username);
			count = Integer.valueOf(temp);
			return count;
		
		}
	 	
		public Integer authenticate(String username, String password){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			
			String temp;
			Integer ismatch;
			temp = sendPost(url, "op=authenticate&username=" + username + "&password=" + password);
			ismatch = Integer.valueOf(temp);
			return ismatch;
		}
		
		public  User getbasic(String username){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			
			String temp;
			User user = new User();
			ObjectMapper mapper1 = new ObjectMapper();
			temp = sendPost(url, "op=get&username=" + username);
			System.out.print(temp);
			try {
				user = mapper1.readValue(temp, new TypeReference<User>() {});
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
			return user;
		
		}
		
		public Integer countQuestions(int id){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
			String uid = String.valueOf(id);
			String temp;
			Integer countQuestions;
			System.out.print(uid);

			temp = sendPost(url, "op=countQuestions&uid=" + uid);
			countQuestions = Integer.valueOf(temp);
			return countQuestions;
		}
		
		public ArrayList<Question> getQuestions(int id){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
		    String uid = String.valueOf(id);
			String temp;
			
			ArrayList<Question> questions = new ArrayList<Question>();
			
			temp = sendPost(url, "op=getQuestions&uid=" + uid);
			
			ObjectMapper mapper1 = new ObjectMapper();
			try {
				questions = mapper1.readValue(temp, new TypeReference<ArrayList<Question>>() {});
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
			
			return questions;
		}
		
		public Integer countAnswers(int id){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
		    String uid = String.valueOf(id);
			String temp;
			Integer countAnswers;
			temp = sendPost(url, "op=countAnswers&uid=" + uid);
			countAnswers = Integer.valueOf(temp);
			return countAnswers;
		}
		
		public ArrayList<Answer> getAnswers(int id){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
			//String url = "http://127.0.0.1/HandInHand/user.php";
		    String uid = String.valueOf(id);
			String temp;
			
			ArrayList<Answer> answers = new ArrayList<Answer>();
			
			temp = sendPost(url, "op=getQuestions&uid=" + uid);
			
			ObjectMapper mapper1 = new ObjectMapper();
			
			try {
				answers = mapper1.readValue(temp, new TypeReference<ArrayList<Answer>>() {});
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
			
			return answers;
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
    	
    	String urlStr = "http://127.0.0.1/HandInHand/upload.php";
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