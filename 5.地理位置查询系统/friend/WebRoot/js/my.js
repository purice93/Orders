function $(id){
	return document.getElementById(id);
}
function $F(id){
	return document.getElementById(id).value;
}
function getXhr(){
			var xhr;
			if(window.XMLHttpRequest){
				//非ie浏览器
				xhr = new XMLHttpRequest();
			}else{
				//ie
				xhr = new ActiveXObject('Microsoft.XMLHttp');
			}
			return xhr;
		}