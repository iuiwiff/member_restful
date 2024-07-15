package kr.co.sist.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.sist.service.UserMemberService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController //모든 응답은 JSP가 아닌 내용 자체가 응답된다. @ResponseBody를 사용한 것과 동일
@RequestMapping("/api/users")
public class UserMemberController {
	
	private Map<String, String> usersMap = new HashMap<String, String>();
	
	@Autowired(required = false)
	private UserMemberService ums;
	
	@PostMapping
	public String createUser(@RequestParam String userId, String name) {
		int cnt = ums.addMember(usersMap, userId, name);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("msg", "계정 생성 실패");
		if(cnt == 1) {
			jsonObj.put("msg", "계정 생성 성공");
		}//end if
		
		return jsonObj.toJSONString();
	}//createUser
	
	@GetMapping("/{userId}")
	public String getUser(@PathVariable String userId) {
		String jsonObj = ums.searchMember(usersMap, userId);
		return jsonObj;
	}
	
	@GetMapping("/all")
	public String getAllUser() {
		String jsonObj = ums.searchAllMember(usersMap);
		return jsonObj;
	}
	
	@PutMapping("/{userId}")
	public String updateUser(@PathVariable String userId, @RequestParam String name) {
		String jsonObj = ums.updateMember(usersMap, userId, name);
		return jsonObj;
	}//updateUser
	
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable String userId) {
		String jsonObj = ums.deleteMember(usersMap, userId);
		return jsonObj;
	}//updateUser
	
}
