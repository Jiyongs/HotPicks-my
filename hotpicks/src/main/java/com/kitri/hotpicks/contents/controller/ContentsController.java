package com.kitri.hotpicks.contents.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.hotpicks.contents.model.ContentsDto;
import com.kitri.hotpicks.contents.model.SidoDto;
import com.kitri.hotpicks.contents.model.SigunguDto;
import com.kitri.hotpicks.contents.service.ContentsService;

@Controller
@RequestMapping("/contents")
public class ContentsController {

	private static final Logger logger = LoggerFactory.getLogger(ContentsController.class);
	
	@Autowired
	private ContentsService contentsService;

	// 조원 api키
	String takapikey = "qldeV%2BL5Ff%2BFi%2BJisZxRFyc1KDitxcPmNkhuwOjk6c7xQDVITEe0oDrh3XFd98iqnW89ky8RMDhQkQIb48h3%2BQ%3D%3D";
	String shzyapikey = "V4CdZIa4lvepCWdXBIawOOghJwjrvjCI5fHDGnlILqJR4oVDLlEj8KRQYj19EM6l72okVEcfDPBbAtFdkokM4Q%3D%3D";
	String pshapikey = "YE3kxGJ%2FLwnnhz1ZCffuLBsyh1RuRshI6USdUAbCfggj5%2B8AwdEt7WV8MW%2FhbUDrRWOEGXHW53TeOiZggnqCNw%3D%3D";
	String lsdapikey = "W%2BjXnjTFTna8E3xfLQFWfoM5qwBKjUVr8lOiM5snThqHGHwxJas1l1hoZXADRDRD4smrfWhiBtFxfORMgAyYxg%3D%3D";

	@RequestMapping(value = "/enter", method = RequestMethod.GET)
	public String enter(Model model) {
		System.out.println("entered");

		String areaUrlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "numOfRows=10000&" + "pageNo=1&" + "MobileOS=ETC&" + "MobileApp=AppTest&" + "listYN=Y&" + "arrange=P&"
				+ "contentTypeId=15&" + "_type=json&" + "ServiceKey=" + shzyapikey;

		// SelectLocation@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		List<ContentsDto> contentsList = contentsService.selectContentsList('m', null);

		model.addAttribute("contentsList", contentsList);

		logger.info("set----------------------------------");

		// SelectLocation@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		List<SidoDto> sidoList = contentsService.selectSido();
		model.addAttribute("sidoList", sidoList);

		// PullApi@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		// List<Map<String, String>> list = contentsService.apiexc(areaUrlStr);
		// model.addAttribute("apiContentsList", list);

		return "contents/result";

	}

	@RequestMapping(value = "/contentsbysearch", method = RequestMethod.GET)
	public String selcectContentsBySearh(@RequestParam Map<String, Object> parameter) {
		List<ContentsDto> contentsList = contentsService.selectContentsList('s', parameter);
		System.out.println("search : " + contentsList.toString());
		org.json.JSONArray contentsJson = new org.json.JSONArray(contentsList);
		System.out.println(contentsJson.toString());
		return "search/searchresult";
	}
	
	
	@RequestMapping(value = "/contentsbylocation", method = RequestMethod.GET)
	public @ResponseBody String selectContentsByLocation(@RequestParam Map<String,Object> parameter) {
		System.out.println(parameter.toString());
		List<ContentsDto> contentsList = contentsService.selectContentsList('l',parameter);
		System.out.println(contentsList.toString());
		org.json.JSONArray contentsJson = new org.json.JSONArray(contentsList);
		System.out.println(contentsJson.toString());
		
		return contentsJson.toString();
	}

	@RequestMapping(value = "/changesgg", method = RequestMethod.GET)
	public @ResponseBody String changeLocation(@RequestParam int sdCode) {

		System.out.println("sdcode : " + sdCode);
		List<SigunguDto> sigunguList = contentsService.selectSigungu(sdCode);
//		System.out.println(sigunguList.toString());
//		JSONArray sigunguArr = (JSONArray)sigunguList;
		org.json.JSONArray sigunguArr = new org.json.JSONArray(sigunguList);
//		System.out.println(sigunguArr.toJSONString());
//		JSONObject sigunguJson = new JSONObject();
		org.json.JSONObject sigunguJson = new org.json.JSONObject(sigunguArr);
//		sigunguJson.put("sigunguJson", sigunguArr);
		sigunguJson.put("sigunguJson", sigunguArr);
		return sigunguJson.toString();
	}

	@RequestMapping(value = "/locationinsert", method = RequestMethod.GET)
	public @ResponseBody String locationInsertProcess() {

		// locationInsertProcess		
		String locationUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?" + "MobileOS=ETC&"
				+ "MobileApp=AppTest&" + "numOfRows=50&" + "_type=json&" + "ServiceKey=" + shzyapikey;

		System.out.println(locationUrl);
		String resultMsg = contentsService.locationProcess(locationUrl);
		logger.info("insert location process complete");

		return resultMsg;
	}

	@RequestMapping(value = "/contentsinsert", method = RequestMethod.GET)
	public @ResponseBody String contentsInsertProcess() {
		// contentscateInsertProcess
		// contentsService.insertContentsCate();
		
		// apiInsertProcess
		String areaUrlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				+ "numOfRows=10000&" + "pageNo=1&" + "MobileOS=ETC&" + "MobileApp=AppTest&" + "listYN=Y&" + "arrange=P&"
				+ "contentTypeId=15&" + "_type=json&" + "ServiceKey=" + shzyapikey;
		// InsertApicontents
		contentsService.insertApiProcess(areaUrlStr);
		logger.info("insert api process complete");

		return "successed Contents api";
	}

}
