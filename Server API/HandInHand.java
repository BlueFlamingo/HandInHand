import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class HandInHand {
	
	public static void main(String[] args) {
		//System.out.println(new HandInHand().sendPost());
		//new HandInHand().userTest();
		//new HandInHand().answerTest();
		//new HandInHand().commentTest();
		new HandInHand().messageTest();
	}
	
	public void messageTest() {
		String url = "http://121.199.64.117:8888/HandInHand/message.php";
		//String url = "http://127.0.0.1/HandInHand/message.php";
		ObjectMapper mapper = new ObjectMapper();
		Message m = new Message();
		m.sender = 1;
		m.receiver = 3;
		m.content = "C2";
		String entry = "";
		try {
			entry = mapper.writeValueAsString(m);
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
		sendGet(url, "op=send&entry=" + entry);
	}
	
	public void commentTest() {
		String url = "http://121.199.64.117:8888/HandInHand/comment.php";
		//String url = "http://127.0.0.1/HandInHand/comment.php";
		ObjectMapper mapper = new ObjectMapper();
		Comment c = new Comment();
		//c.id = 2;
		c.content = "C2";
		String entry = "";
		try {
			entry = mapper.writeValueAsString(c);
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
	
	public void answerTest() {
		String url = "http://121.199.64.117:8888/HandInHand/answer.php";
		//String url = "http://127.0.0.1/HandInHand/answer.php";
		ObjectMapper mapper = new ObjectMapper();
		Answer a = new Answer();
		//a.id = 2;
		a.content = "A2";
		String entry = "";
		try {
			entry = mapper.writeValueAsString(a);
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
	
	public void questionTest() {
		String url = "http://121.199.64.117:8888/HandInHand/question.php";
		//String url = "http://127.0.0.1/HandInHand/question.php";
		ObjectMapper mapper = new ObjectMapper();
		Question q = new Question();
		q.id = 13;
		q.title = "question2";
		q.uid = 9;
		q.createdTime = 3;
		ArrayList<Integer> topics = new ArrayList<Integer>();
		topics.add(2);
		topics.add(1);
		q.topics = topics;
		//q.createdTime = new Date().toString();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(q);
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
	
	public void userTest() {
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
		sendGet(url, "op=register&entry=" + entry);
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
