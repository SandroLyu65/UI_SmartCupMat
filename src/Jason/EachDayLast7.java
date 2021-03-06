package Jason;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class EachDayLast7 extends GetRequest {
    private final String basicUrl;
    private final ArrayList<String> days;
    private final ArrayList<Double> eachDay;

    public EachDayLast7() {
        super();
        this.basicUrl = "https://studev.groept.be/api/a21ib2d02/water_get_byDay/";
        days = new ArrayList<>();
        eachDay = new ArrayList<>();
    }

    public void setLists(){
        try {
            for(int i = 6; i >= 0; i--){
                String url = basicUrl + getDaysString(i);
                if(i == 0)
                    addToList("today", url);
                else
                    addToList(getDaysString(i).substring(5), url);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void addToList(String displayDate, String url) {
        JSONArray array = new JSONArray(makeGETRequest(url));
        JSONObject curObject = array.getJSONObject(0);

        if(JSONObject.NULL.equals(curObject.get("sumByDay")))
            eachDay.add(0.0);
        else
            eachDay.add(parseDouble(curObject.getString("sumByDay")));
        days.add(displayDate);
    }

    public String getDaysString(int minusDay){
        LocalDate todayDate = LocalDate.now();
        return String.valueOf(todayDate.minusDays(minusDay));
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public ArrayList<Double> getEachDay() {
        return eachDay;
    }
}
