// 모든 요소가 로드 되었을 때 

onload = () => {
	
	// id속성이 btn1인 요소가 클릭되었을 때 (이벤트 핸들러)
	document.getElementById("btn1").addEventListener("click", () => {
		// alert("클릭");
		getAirPollution();
	});
	
	
}


// 대기 오염 정보 조회
const getAirPollution = () => {
	// [GET] /airPollution?location=선택된지역정보 전달
	
	// * 요청 전 작업 => 선택된 지역 정보 값을 가져와야 함
	const location = document.getElementById("location").value;
	// console.log(location);
	
	// * 지역 정보를 담아 조회 요청 => 비동기 요청(fetch)
	fetch("/airPollution?location="+location).
	then(response=>response.json())	
	// response.text() => 문자열, 숫자, ... 응답 데이터 추출
	// response.json() => 객체 형태로 응답 데이터 추출
	// => .then( (response)=> {return response.json(); })
	.then(data => {
		// 응답 결과 확인
		console.log(data);
		//console.log(data[0]);
		//console.log(data[0].stationName);
		//console.log(data.length);
		
		// total 개수
		document.getElementById("total").innerText = data.length;
		
		let ele = "";
		
		for(let i=0; i<data.length; i++){
			// console.log(data[i]);
			// console.log(data[i].stationName);
			
			ele +=
				"<tr>"
					+ "<td>" + data[i].stationName + "</td>"
					+ "<td>" + data[i].dataTime + "</td>"
					+ "<td>" + data[i].khaiValue + "</td>"
					+ "<td>" + data[i].pm10Value + "</td>"
					+ "<td>" + data[i].so2Value + "</td>"
					+ "<td>" + data[i].coValue + "</td>"
					+ "<td>" + data[i].no2Value + "</td>"
					+ "<td>" + data[i].o3Value + "</td>"
				+ "</tr>";
		}
		
		document.querySelector("table tbody").innerHTML = ele;
	})
	.catch(error=>{
		// 오류 내용 확인
		console.log(error);
	});
}