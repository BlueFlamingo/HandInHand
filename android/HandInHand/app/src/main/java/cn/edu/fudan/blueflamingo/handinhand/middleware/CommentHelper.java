package cn.edu.fudan.blueflamingo.handinhand.middleware;

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

import cn.edu.fudan.blueflamingo.handinhand.model.Comment;
import cn.edu.fudan.blueflamingo.handinhand.model.ExComment;
import cn.edu.fudan.blueflamingo.handinhand.model.Utility;


public class CommentHelper {

	public int add(Comment c){
		String url =  "http://121.199.64.117:8888/HandInHand/comment.php";
		//String url = "http://127.0.0.1/HandInHand/comment.php";
		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(c);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String temp;
		int cid;
		temp = sendPost(url, "op=add&entry=" + entry);
		cid = Integer.valueOf(temp);
		return cid;
	}

	public int update(Comment c){
		String url =   "http://121.199.64.117:8888/HandInHand/comment.php";
		//String url = "http://127.0.0.1/HandInHand/comment.php";
		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(c);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String temp;
		int num;
		temp = sendPost(url, "op=update&entry=" + entry);
		num = Integer.valueOf(temp);
		return num;
	}

	public int delete(int cId){
		String url =   "http://121.199.64.117:8888/HandInHand/comment.php";
		//String url = "http://127.0.0.1/HandInHand/comment.php";
		String cid = String.valueOf(cId);
		String temp;
		int num;
		temp = sendPost(url, "op=delete&cid=" + cid);
		num = Integer.valueOf(temp);
		return num;

	}

	public ArrayList<ExComment>  getByAid(int aId){
		String url =   "http://121.199.64.117:8888/HandInHand/comment.php";
		//String url = "http://127.0.0.1/HandInHand/comment.php";
		String aid = String.valueOf(aId);
		String temp;
		ArrayList<Comment> comment = new ArrayList<Comment>();
		temp = sendPost(url, "op=getByAid&aid=" + aid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			comment = mapper.readValue(temp, new TypeReference<ArrayList<Comment>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return Utility.commentToExComment(comment);

	}

	public ExComment getByCid(int cId){
		String url =   "http://121.199.64.117:8888/HandInHand/comment.php";
		//String url = "http://127.0.0.1/HandInHand/comment.php";
		String cid = String.valueOf(cId);
		String temp;
		Comment comment = new Comment();
		temp = sendPost(url, "op=getByCid&cid=" + cid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			comment = mapper.readValue(temp, new TypeReference<Comment>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return Utility.commentToExComment(comment);

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
		//System.out.println(result);
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
		String BOUNDARY = "---------------------------123821742118716"; //boundary¾ÍÊÇrequestÍ·ºÍÉÏ´«ÎÄ¼þÄÚÈÝµÄ·Ö¸ô·û
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

			// ¶ÁÈ¡·µ»ØÊý¾Ý
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
			//System.out.println("·¢ËÍPOSTÇëÇó³ö´í¡£" + urlStr);
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
