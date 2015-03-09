package cn.edu.fudan.blueflamingo.handinhand.middleware;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.handinhand.model.ExAnswer;
import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;
import cn.edu.fudan.blueflamingo.handinhand.model.User;
import cn.edu.fudan.blueflamingo.handinhand.model.Utility;

/**
 * The type Favorite helper.
 */
public class FavoriteHelper {

    /**
     * Is question favorite.
     *
     * @param uId the u id
     * @param qId the q id
     * @return the int
     */
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

    /**
     * Is answer favorite.
     *
     * @param uId the u id
     * @param aId the a id
     * @return the int
     */
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

    /**
     * Switch question favorite.
     *
     * @param uId the u id
     * @param qId the q id
     * @return the int
     */
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

    /**
     * Switch answer favorite.
     *
     * @param uId the u id
     * @param aId the a id
     * @return the int
     */
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

    /**
     * List questions.
     *
     * @param uId the u id
     * @return the array list
     */
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

    /**
     * List answers.
     *
     * @param uId the u id
     * @return the array list
     */
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

    /**
     * Is user favorite.
     *
     * @param uId the u id
     * @param uId2 the u id 2
     * @return the int
     */
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

    /**
     * Switch user favorite.
     *
     * @param uId the u id
     * @param uId2 the u id 2
     * @return the int
     */
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

    /**
     * List users.
     *
     * @param uId the u id
     * @return the array list
     */
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
		// System.out.println(result);
		return result;
	}
}
