<%@page import="java.util.*,java.text.*,com.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=bpgdkqFAZegPfh8FTgolo0hImBp5k45j"></script>
<title>位置定位标注</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">

	// 百度地图API功能
	var map = new BMap.Map("allmap");// 初始化全景图
	
	<%	
		// 判断城市是否为空，为空，则返回主界面index.jsp
		Object obj = session.getAttribute("cityList");
		if (obj == null) {
			response.sendRedirect("index.jsp");
			return;
		}
	%>
	<%
		// 获取后台数据，并解析
		List<City> cities = (List<City>) request.getAttribute("cityList");
		for (int i = 0; i < cities.size(); i++) {
			City city = cities.get(i);
	%>
	// 获取经纬度
	var lng = <%=city.getLongitude()%>;
	var lat = <%=city.getLatitude()%>;
	var point = new BMap.Point(lng, lat); // 地位一个点（经纬度）
	map.centerAndZoom(point, 5.5);	// 设置地图中心位置，多个城市，则以最后一个为准
	var marker = new BMap.Marker(point); // 创建标注
	map.addOverlay(marker); // 将标注添加到地图中
	<%
		}
	%>
</script>
