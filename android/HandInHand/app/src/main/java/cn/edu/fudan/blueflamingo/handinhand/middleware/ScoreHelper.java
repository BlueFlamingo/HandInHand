package cn.edu.fudan.blueflamingo.handinhand.middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * The type Score helper.
 */
public class ScoreHelper {

    /**
     * Is approve.
     *
     * @param uId the u id
     * @param aId the a id
     * @return the int
     */
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

    /**
     * Is oppose.
     *
     * @param uId the u id
     * @param aId the a id
     * @return the int
     */
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

    /**
     * Switch approve.
     *
     * @param uId the u id
     * @param aId the a id
     * @return the int
     */
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

    /**
     * Switch oppose.
     *
     * @param uId the u id
     * @param aId the a id
     * @return the int
     */
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
