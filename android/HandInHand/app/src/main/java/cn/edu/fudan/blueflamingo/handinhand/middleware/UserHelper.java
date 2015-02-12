package cn.edu.fudan.blueflamingo.handinhand.middleware;

import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import cn.edu.fudan.blueflamingo.handinhand.model.Answer;
import cn.edu.fudan.blueflamingo.handinhand.model.ExAnswer;
import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;
import cn.edu.fudan.blueflamingo.handinhand.model.Message;
import cn.edu.fudan.blueflamingo.handinhand.model.Question;
import cn.edu.fudan.blueflamingo.handinhand.model.User;
import cn.edu.fudan.blueflamingo.handinhand.model.Utility;

public class UserHelper {
	public  int register(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String temp;
		int uid;
		temp = sendPost(url, "op=register&entry=" + entry);
		uid = Integer.valueOf(temp);
		return uid;
	}

	public int update(User u){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(u);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String temp;
		int num;
		temp = sendPost(url, "op=update&entry=" + entry);
		num = Integer.valueOf(temp);
		return num;
	}

	public int count(String username){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		String temp;
		int count;
		temp = sendPost(url, "op=count&username=" + username);
		count = Integer.valueOf(temp);
		return count;
	}

	public int countFollowers(int uid) {
		String url = "http://121.199.64.117:8888/HandInHand/favorite.php";
		String res = sendPost(url, "op=countFollowers&uid=" + String.valueOf(uid));
		return Integer.valueOf(res);
	}

	public ArrayList<User> listFollowers(int uid) {
		String url = "http://121.199.64.117:8888/HandInHand/favorite.php";
		ArrayList<Integer> uids = new ArrayList<>();
		String res = sendPost(url, "op=listFollowers&uid=" + uid);
		ObjectMapper mapper = new ObjectMapper();
		try {
			uids = mapper.readValue(res, new TypeReference<ArrayList<Integer>>() {
			});
		} catch (Exception e) {
			Log.d("get followers", e.toString());
		}
		return Utility.uidtoUser(uids);
	}

	public int countFollowUsers(int uid) {
		String url = "http://121.199.64.117:8888/HandInHand/favorite.php";
		String res = sendPost(url, "op=countUsers&uid=" + String.valueOf(uid));
		return Integer.valueOf(res);
	}

	public int authenticate(String username, String password){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";

		String temp;
		int ismatch;
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

		try {
			user = mapper1.readValue(temp, new TypeReference<User>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}
		return user;

	}

	public int countQuestions(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		String uid = String.valueOf(uId);
		String temp;
		int countQuestions;


		temp = sendPost(url, "op=countQuestions&uid=" + uid);
		countQuestions = Integer.valueOf(temp);
		return countQuestions;
	}

	public ArrayList<ExQuestion> getQuestions(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		String uid = String.valueOf(uId);
		String temp;

		ArrayList<Question> questions = new ArrayList<Question>();

		temp = sendPost(url, "op=getQuestions&uid=" + uid);

		ObjectMapper mapper1 = new ObjectMapper();
		try {
			questions = mapper1.readValue(temp, new TypeReference<ArrayList<Question>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}


		return Utility.questionToExQuestion(questions);
	}

	public int countAnswers(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		String uid = String.valueOf(uId);
		String temp;
		int countAnswers;
		temp = sendPost(url, "op=countAnswers&uid=" + uid);
		countAnswers = Integer.valueOf(temp);
		return countAnswers;
	}

	public ArrayList<ExAnswer> getAnswers(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		String uid = String.valueOf(uId);
		String temp;

		ArrayList<Answer> answers = new ArrayList<Answer>();

		temp = sendPost(url, "op=getAnswers&uid=" + uid);

		ObjectMapper mapper = new ObjectMapper();

		try {
			answers = mapper.readValue(temp, new TypeReference<ArrayList<Answer>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return Utility.answerToExAnswer(answers);
	}

	public User getByUid(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/user.php";
		//String url = "http://127.0.0.1/HandInHand/user.php";
		String uid = String.valueOf(uId);
		String temp;

		User user = new User();

		temp = sendPost(url, "op=getByUid&uid=" + uid);

		ObjectMapper mapper = new ObjectMapper();

		try {
			user = mapper.readValue(temp, new TypeReference<User>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return user;
	}


	public  int SendMsgs(Message m){
		String url = "http://121.199.64.117:8888/HandInHand/message.php";

		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(m);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String temp;
		int mid;
		temp = sendPost(url, "op=send&entry=" + entry);
		mid = Integer.valueOf(temp);
		return mid;
	}

	public ArrayList<Message> getSentMsgs(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/message.php";

		String uid = String.valueOf(uId);
		String temp;

		ArrayList<Message> message = new ArrayList<Message>();

		temp = sendPost(url, "op=getSentMsgs&uid=" + uid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			message = mapper.readValue(temp, new TypeReference<ArrayList<Message>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return message;
	}

	public ArrayList<Message> getAllReceivedMsgs(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/message.php";

		String uid = String.valueOf(uId);
		String temp;

		ArrayList<Message> message = new ArrayList<Message>();

		temp = sendPost(url, "op=getAllReceivedMsgs&uid=" + uid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			message = mapper.readValue(temp, new TypeReference<ArrayList<Message>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return message;
	}

	public ArrayList<Message> getUnhandledMsgs(int uId){
		String url = "http://121.199.64.117:8888/HandInHand/message.php";

		String uid = String.valueOf(uId);
		String temp;

		ArrayList<Message> message = new ArrayList<Message>();

		temp = sendPost(url, "op=getUnhandledMsgs&uid=" + uid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			message = mapper.readValue(temp, new TypeReference<ArrayList<Message>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return message;
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

	public String uploadFile(String filePath) {

		String urlStr = "http://121.199.64.117:8888/HandInHand/upload.php";
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("name", "testname");
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", filePath);
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716";
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
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		//System.out.println(res);
		return res;
	}
}
