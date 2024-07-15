package kr.co.sist.service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserMemberService {

	public int addMember(Map<String, String> userMap, String userId, String name) {
		userMap.put(userId, name);
		//JSON은 Service에서 만들어야 함
		return 1;
	}//addMember
	
	public String searchMember(Map<String, String> userMap, String userId) {
		JSONObject jsonObj = new JSONObject();
		
			jsonObj.put("flag", userMap.containsKey(userId));
			jsonObj.put("name", userMap.getOrDefault(userId, "계정 없음"));
			
		return jsonObj.toJSONString();
	}
	
	public String searchAllMember(Map<String, String> userMap) {
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("flag", !userMap.isEmpty());
		jsonObj.put("name", userMap.size());
		
		if( !userMap.isEmpty() ) {
			JSONArray jsonArr = new JSONArray();
			JSONObject jsonTemp = null;
			
			for(String key : userMap.keySet() ) {
				jsonTemp = new JSONObject();
				jsonTemp.put("userId", key);
				jsonTemp.put("name", userMap.get(key));
				
				jsonArr.add(jsonTemp);
			}//end for
			jsonObj.put("data", jsonArr);
			
		}//end if
		System.out.println( jsonObj );
		
		return jsonObj.toJSONString();
	}
	
	public String updateMember(Map<String, String> userMap, String userId, String name) {
		
		boolean flag = userMap.containsKey(userId);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("flag", flag);
		
		if( flag ) {
			userMap.put(userId, name);
		}//end if
		
		return jsonObj.toJSONString();
	}//updateMember
	
	public String deleteMember(Map<String, String> userMap, String userId) {
		
		boolean flag = userMap.containsKey(userId);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("flag", flag);
		
		if( flag ) {
			userMap.remove(userId);
		}//end if
		
		return jsonObj.toJSONString();
	}//updateMember
}
