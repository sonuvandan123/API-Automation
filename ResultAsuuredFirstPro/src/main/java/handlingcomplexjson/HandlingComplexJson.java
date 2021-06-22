package handlingcomplexjson;

import basic.ResultAsuuredFirstPro.PayLoad;
import io.restassured.path.json.JsonPath;

public class HandlingComplexJson {

	public static void main(String[] args)
	{
		String response=PayLoad.complexJson();
		JsonPath js=PayLoad.rawtoJson(response);
		int ammount=js.get("dashboard.purchaseAmount");
		//System.out.println(ammount);
		//System.out.println(js.get("courses[1].title"));
		//System.out.println(js.get("courses.size()"));
		int size=js.get("courses.size()");
		for(int i=0;i<size;i++)
		{
			System.out.println(js.get("courses["+i+"].title"));
		}
		
	}

}
