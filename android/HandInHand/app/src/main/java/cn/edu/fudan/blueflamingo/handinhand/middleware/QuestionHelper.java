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

import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;
import cn.edu.fudan.blueflamingo.handinhand.model.Question;
import cn.edu.fudan.blueflamingo.handinhand.model.Utility;

/**
 * The type Question helper.
 */
public class QuestionHelper {

	private final static String questionURL = "http://121.199.64.117:8888/HandInHand/question.php";

    /**
     * Add int.
     *
     * @param q the q
     * @return the int
     */
    public int add(Question q){
		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(q);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String temp;
		int qid;
		temp = sendPost(questionURL, "op=add&entry=" + entry);
		qid = Integer.valueOf(temp);
		return qid;
	}

    /**
     * Update int.
     *
     * @param q the q
     * @return the int
     */
    public int update(Question q){
		ObjectMapper mapper = new ObjectMapper();
		String entry = "";
		try {
			entry = mapper.writeValueAsString(q);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String temp;
		int num;
		temp = sendPost(questionURL, "op=update&entry=" + entry);
		num = Integer.valueOf(temp);
		return num;
	}

    /**
     * Delete int.
     *
     * @param qId the q id
     * @return the int
     */
    public int delete(int qId){
		String qid = String.valueOf(qId);
		String temp;
		int num;
		temp = sendPost(questionURL, "op=delete&qid=" + qid);
		num = Integer.valueOf(temp);
		return num;

	}

    /**
     * Get by topic.
     *
     * @param tId the t id
     * @return the array list
     */
    public ArrayList<ExQuestion>  getByTopic(int tId){
		String tid = String.valueOf(tId);
		String temp;
		ArrayList<ExQuestion> questions = new ArrayList<ExQuestion>();
		temp = sendPost(questionURL, "op=getByTopic&tid=" + tid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			questions = mapper.readValue(temp, new TypeReference<ArrayList<ExQuestion>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return questions;

	}

    /**
     * Get by qid.
     *
     * @param qId the q id
     * @return the ex question
     */
    public ExQuestion getByQid(int qId){
		String qid = String.valueOf(qId);
		String temp;
		ExQuestion question = new ExQuestion();
		temp = sendPost(questionURL, "op=getByQid&qid=" + qid);

		ObjectMapper mapper = new ObjectMapper();
		try {
			question = mapper.readValue(temp, new TypeReference<ExQuestion>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return question;
	}

    /**
     * Gets hotest.
     *
     * @return the hotest
     */
    public ArrayList<ExQuestion> getHotest() {
		ArrayList<ExQuestion> questions = new ArrayList<>();
		String res = sendPost(questionURL, "op=getHotest");
		ObjectMapper mapper = new ObjectMapper();
		try {
			questions = mapper.readValue(res, new TypeReference<ArrayList<ExQuestion>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

    /**
     * Get latest.
     *
     * @param Count the count
     * @return the array list
     */
    public ArrayList<ExQuestion>  getLatest(int Count){
		String count = String.valueOf(Count);
		String temp;
		ArrayList<ExQuestion> questions = new ArrayList<ExQuestion>();
		temp = sendPost(questionURL, "op=getLatest&count=" + count);

		ObjectMapper mapper = new ObjectMapper();
		try {
			questions = mapper.readValue(temp, new TypeReference<ArrayList<ExQuestion>>() {});
		} catch (Exception e) {

			e.printStackTrace();
		}

		return questions;

	}

    /**
     * Send post.
     *
     * @param url the url
     * @param param the param
     * @return the string
     */
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

    /**
     * Upload file.
     *
     * @param filePath the file path
     * @return the string
     */
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
