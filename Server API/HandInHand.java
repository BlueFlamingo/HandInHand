import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class HandInHand {
	
	public static void main(String[] args) {
		//System.out.println(new HandInHand().sendPost());
		new HandInHand().sendJson();
	}

	public String sendPost() {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
		String param = "username=zst";
		String url = "http://127.0.0.1/HandInHand/upload.php";
        DataOutputStream ds = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
         
            // 获取URLConnection对象对应的输出流
            ds = new DataOutputStream(conn.getOutputStream());
            
            String fileName = "/Users/HurricaneTong/code/HelloJava/HandInHand/src/test.jpg";
            FileInputStream fStream = new FileInputStream(fileName);
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file\";filename=\"" +
                    fileName + "\"" + end);
            ds.writeBytes(end);
            
            int bufferSize = 8192;
            byte[] buffer = new byte[bufferSize];   //8k
            int length = -1;
          /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1)
            {
            /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
          /* 关闭流，写入的东西自动生成Http正文*/
            fStream.close();
          /* 关闭DataOutputStream */
            ds.close();
            
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(ds!=null){
                    ds.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
	
	public void sendJson() {
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		ObjectMapper mapper = new ObjectMapper();
		User u = new User();
		u.id = 9;
		u.username = "zst";
		u.password = "zst2";
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
		sendGet(url, "op=add&entry=" + entry);
	}
	
	public void getJson() {
		String url = "http://121.199.64.117:8888/HandInHand/demo.php";
		String res = sendGet(url, "tag=set");
		System.out.println(res);
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Tag> ts = new ArrayList<Tag>();
		try {
			ts = mapper.readValue(res, new TypeReference<ArrayList<Tag>>() {});
			System.out.println(ts);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String sendGet(String url, String param) {
        String result = "";
        System.out.println(param);
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        System.out.println(result);
        return result;
    }


}

class Tag {
	public int id;
	public String tag;
	
	public Tag(int id, String tag) {
		this.id = id;
		this.tag = tag;
	}
	
	public String toString() {
		return String.valueOf(id) + "#" + tag;
	}
}
