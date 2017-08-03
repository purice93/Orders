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
 * �����߼�������
 * ���ļ��ж�ȡ�û����ڵĳ���
 * ��ȡ���еľ�γ��
 */
public class GetCity {
	// ��ʼ���ļ���xlxs���������д�����
	private File file1 = null;
	private XSSFWorkbook xssfWorkbook = null;
	private XSSFRow xssfRow = null;
	
	// ͨ���û������ҳ����������б���ʽ��ʾ��һ���û�Ĭ���������������
	public List<City> findByUsername(String username) throws Exception {
		// 1.list���ڴ洢��������String���ͣ�
		List<String> list = new LinkedList<String>();
		
		// 2.pointlist���ڴ洢���У�City���ͣ�
		List<City> pointlist = new LinkedList<City>();
		list = getCityName(username);
		
		// 3.�����������б�list�����ҳ������ڵľ�γ�ȣ��������ݱ�����City�У�ͬʱ��City������pointlist��
		for (int i = 0; i < list.size(); i++) {
			// ��ȡ���о�γ��
			Map<String, Double> map = GetCity.getLngAndLat(list.get(i));
			City city = new City();
			
			// ���澭γ��
			city.setLatitude(map.get("lat"));
			city.setLongitude(map.get("lng"));
			pointlist.add(city);
		}
		return pointlist;
	}

	// ��ȡ�û������ڵĳ����б�
	public List<String> getCityName(String userName) {
		List<String> array = new LinkedList<String>();
		
		// ��ȡ�ļ���Ϣ
		try {
			//��ȡ�ļ���ע���ļ�Ϊ����·��������
			file1 = new File("E:/JavaEE_WorkSpace/mapMaker/������1.xlsx");
			InputStream instream = new FileInputStream(file1);
			
			// .xlxs�ļ���ȡ
			xssfWorkbook = new XSSFWorkbook(instream);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			// ��ȡһ��sheet�������б���������ȡ�������У�
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				// ��ȡһ����Ϣ
				xssfRow = xssfSheet.getRow(rowNum);
				
				// ���ļ����ݸ�ʽתΪString����
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
				
				// ��ȡ�ļ���Ϣ��������������ӵ�array��
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
			// �ر�������
			instream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return array;
	}

	// ��ȡ���ڳ��еľ�γ�ȣ�������map��
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		
		// �ٶȵ�ͼ�Ĳ�ѯ�ӿڣ���ѯ���о�γ�ȣ�
		// ˵����������ak����ʹ�ã�������ʹ�õ�����ע��ģ��ύʹ�õ�json
		// ak=F454f8a5efe5e577997931cc01de3974
		String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address
				+ "&output=json&ak=F454f8a5efe5e577997931cc01de3974";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		
		// ��ѯ�ɹ������澭γ��,����Ĭ��Ϊ�գ���ʾ�ٶȵ�ͼû��������м�¼��
		if (obj.get("status").toString().equals("0")) {
			double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			map.put("lng", lng);
			map.put("lat", lat);
		} else {
			// System.out.println("δ�ҵ���ƥ��ľ�γ�ȣ�");
		}
		return map;
	}

	// Json��url�����ύ
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
