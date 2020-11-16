package webISS;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenNotifyWebService implements ISSWebService {


  
  static String parseJSON(String response) {

    JSONObject json = new JSONObject(response);
    if (json.getString("message").equals("success")) {
      JSONArray responseArray = json.getJSONArray("response");
      return String.valueOf(responseArray.getJSONObject(0)
        .getInt("risetime"));
    }
    else {
      return json.getString("reason");
    }
  }

  static long fetchIssFlyOverData(double latitude, double longitude) throws Exception
  {
    String requestURL = "http://api.open-notify.org/iss-pass.json?lat=" + latitude + "&lon=" + longitude + "&n=1";

    URL request = new URL(requestURL);
    HttpURLConnection connection = (HttpURLConnection) request.openConnection();

    connection.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuffer response = new StringBuffer();
    String inputLine;

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }


    return Long.parseLong(
      OpenNotifyWebService.parseJSON(response.toString()));
  }
}