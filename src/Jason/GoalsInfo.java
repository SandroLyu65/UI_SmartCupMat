package Jason;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalsInfo extends GetRequest{
    private final String getUrl;
    private final String updateUrl;

    public GoalsInfo() {
        this.getUrl = "https://studev.groept.be/api/a21ib2d02/goal_get";
        this.updateUrl = "https://studev.groept.be/api/a21ib2d02/goal_set/";
    }

    public double getGoal() {
        JSONArray array = new JSONArray(makeGETRequest(getUrl));
        JSONObject curObject = array.getJSONObject(0);

        return Double.parseDouble(curObject.getString("day_goal"));
    }

    public int getTimer() {
        JSONArray array = new JSONArray(makeGETRequest(getUrl));
        JSONObject curObject = array.getJSONObject(0);

        return Integer.parseInt(curObject.getString("timer"));
    }

    public void updateGoal(double goal, int timer){
        makeGETRequest(updateUrl + goal + "/" + timer);
    }
}