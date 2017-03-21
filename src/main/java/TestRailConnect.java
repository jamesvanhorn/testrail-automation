package main.java;

import java.util.List;

import org.json.simple.JSONObject;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Case;
import com.codepine.api.testrail.model.CaseField;


public class TestRailConnect {
	
	public static void main(String[] args) throws Exception
	{
		final String username = "james.vanhorn@weather.com";
		final String password = "U8WPzCY2zllu/AYXNVO/-df36mVybFBCJ.hIUnnEc"; //Go to TestRail and create an api key to prevent plaintext password
		final String testRailServer = "https://twcqa.testrail.com/"; 
		
		//Using library suggested on TestRail's website. Classes are APIClient and APIException
		APIClient client = new APIClient(testRailServer);
		client.setUser(username);
		client.setPassword(password);
		
		JSONObject c = (JSONObject) client.sendGet("get_case/610660");
		System.out.println("Title for test case 610660: " + c.get("title"));
		
		//Using test-rail-java-api-client that was in Nexus to use already. Need to check if the MIT License is ok for this dependency.
		//Didn't bother setting up log4j property file for this little proof of concept. Ignore those warnings.
		TestRail testRail = TestRail.builder(testRailServer, username, password).build();
		List<CaseField> fields = testRail.caseFields().list().execute();
		Case testCase = testRail.cases().get(610660, fields).execute();
		
		System.out.println("Title for test case 610660: " + testCase.getTitle());
	}
}
