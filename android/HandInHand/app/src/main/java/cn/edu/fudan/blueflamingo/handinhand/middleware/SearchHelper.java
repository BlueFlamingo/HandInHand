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

import cn.edu.fudan.blueflamingo.handinhand.model.Utility;

public class SearchHelper {

	public ArrayList< ArrayList<Object> > Search (String pattern){
		String url =   "http://121.199.64.117:8888/HandInHand/search.php";

		String temp;
		ArrayList< ArrayList<Integer> > list = new ArrayList< ArrayList<Integer> >();
		temp = sendPost(url, pattern);

		ObjectMapper mapper = new ObjectMapper();
		try {
			list = mapper.readValue(temp, new TypeReference<ArrayList< ArrayList<Integer> >>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Utility.slistToObjectlist(list);

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
