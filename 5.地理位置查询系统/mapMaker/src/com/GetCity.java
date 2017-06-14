package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import net.sf.json.JSONObject;

/*
 * 数据逻辑处理类
 * 从文件中读取用户所在的城市
 * 获取城市的经纬度
 */
public class GetCity {
	// 初始化文件、xlxs表格处理、表格行处理类
	private File file1 = null;
	private XSSFWorkbook xssfWorkbook = null;
	private XSSFRow xssfRow = null;
	
	// 通过用户名查找城市名，以列表形式表示：一个用户默认最多有三个城市
	public List<City> findByUsername(String username) throws Exception {
		// 1.list用于存储城市名（String类型）
		List<String> list = new LinkedList<String>();
		
		// 2.pointlist用于存储城市（City类型）
		List<City> pointlist = new LinkedList<City>();
		list = getCityName(username);
		
		// 3.遍历城市名列表list，查找城市所在的经纬度，并将数据保存在City中，同时将City保存在pointlist中
		for (int i = 0; i < list.size(); i++) {
			// 获取城市经纬度
			Map<String, Double> map = GetCity.getLngAndLat(list.get(i));
			City city = new City();
			
			// 保存经纬度
			city.setLatitude(map.get("lat"));
			city.setLongitude(map.get("lng"));
			pointlist.add(city);
		}
		return pointlist;
	}

	// 获取用户名所在的城市列表
	public List<String> getCityName(String userName) {
		List<String> array = new LinkedList<String>();
		
		// 读取文件信息
		try {
			//读取文件，注意文件为绝对路径！！！
			file1 = new File("E:/JavaEE_WorkSpace/mapMaker/工作簿1.xlsx");
			InputStream instream = new FileInputStream(file1);
			
			// .xlxs文件读取
			xssfWorkbook = new XSSFWorkbook(instream);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			// 读取一个sheet，并按行遍历，最多读取三个城市！
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				// 获取一行信息
				xssfRow = xssfSheet.getRow(rowNum);
				
				// 将文件内容格式转为String类型
				if (xssfRow.getCell(0) != null) {
					xssfRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				}
				if (xssfRow.getCell(4) != null) {
					xssfRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				}
				if (xssfRow.getCell(5) != null) {
					xssfRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				}
				if (xssfRow.getCell(6) != null) {
					xssfRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				}
				
				// 读取文件信息，并将城市名添加到array中
				if (xssfRow.getCell(0).getStringCellValue().equals(userName)) {
					if (xssfRow.getCell(4) != null) {
						array.add(xssfRow.getCell(4).getStringCellValue());
					}
					if (xssfRow.getCell(5) != null) {
						array.add(xssfRow.getCell(5).getStringCellValue());
					}
					if (xssfRow.getCell(6) != null) {
						array.add(xssfRow.getCell(6).getStringCellValue());
					}
				}
			}
			// 关闭输入流
			instream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return array;
	}

	// 获取所在城市的经纬度，保存在map中
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		
		// 百度地图的查询接口（查询城市经纬度）
		// 说明：所给的ak不能使用，我这里使用的是我注册的，提交使用的json
		// ak=F454f8a5efe5e577997931cc01de3974
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
				+ "&output=json&ak=F454f8a5efe5e577997931cc01de3974";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		
		// 查询成功，保存经纬度,否则默认为空（表示百度地图没有这个城市记录）
		if (obj.get("status").toString().equals("0")) {
			double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			map.put("lng", lng);
			map.put("lat", lat);
		} else {
			// System.out.println("未找到相匹配的经纬度！");
		}
		return map;
	}

	// Json对url进行提交
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}

}
