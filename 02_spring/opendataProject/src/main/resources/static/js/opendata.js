// 모든 요소가 로드되었을 때
onload = () => {

	// 아이디 속성이 btn1인 요소가 클릭되었을 때 (이벤트 핸들러)
	document.getElementById("btn1").addEventListener("click", ()=>{

		// alert("클릭@@");
		// getAirPollution();
		getAirPollutionV2();

	});

}

// 대기 오염 정보 조회 (+ 페이지 정보 포함)
const getAirPollutionV2 = async (page = 1) => {
	// [GET] /airPollution/v2?location=지역정보&currPage=페이지번호

	const location = document.getElementById("location").value;

	try {

		// * 대기 오염 정보 요청 => 비동기 요청(fetch)
		const response = await fetch("/airPollution/v2?location="+location+"&currPage="+page)
		const data = await response.json();

		console.log(data); // => { items: [..], totalCount: 125, numOfRows: 10, pageNo: 1 }

		// * 조회 결과를 화면에 표시
		displayAirPollutionData(data.items);
		// * 페이징바 변경
		displayPagination(data.totalCount, data.pageNo, data.numOfRows);

	} catch (error) {

		console.error(error);

	}
}

// 대기 오염 정보 조회
const getAirPollution = () => {
	// [GET] /airPollution?location=선택된지역정보

	// * 요청 전 작업 => 선택된 지역 정보 값을 가져오기
	const location = document.getElementById("location").value;
	// console.log(location);

	// * 지역 정보를 담아 조회 요청 => 비동기 요청(fetch)
	fetch("/airPollution?location="+location)
		.then(response=>response.json())
		// response.text() => 문자열, 숫자,... 응답 데이터 추출
		// response.json() => 객체 형태로 응답 데이터 추출
		// => .then ((response)=> {return response.json(); })
		.then(data=> {
			// 응답 결과 확인
			console.log(data);
			// 응답 데이터를 화면에 표시
			displayAirPollutionData(data);
		})
		.catch(error=>{
			// 오류 내용 확인
			console.log(error);
		});
}

const displayAirPollutionData = (data) => {
	// 전달된 데이터가 배열인지 아닌지 체크
	if (!Array.isArray(data)) {
		console.error("data is not array.");
		return;
	}

	// console.log(data);

	// tbody 요소에 접근
	const target = document.querySelector("#air-list tbody");

	// 전달된 데이터를 활용하여 tr 요소 생성
	for(const item of data){
		// console.log(Object.keys(item));
		const itemArr = Object.keys(item);

		let trTag = document.createElement("tr");

		/* TODO ...
		for (const keys of itemArr){
			console.log(keys);
			let keys = createTd();
			let text = createText(keys);
			createRows(keys, text, trTag, target);
		}
		*/

		const stationName = createTd();
		let text = createText(item.stationName);
		createRows(stationName, text, trTag, target);

		const dataTime = createTd();
		text = createText(item.dataTime);
		createRows(dataTime, text, trTag, target);

		const khaiValue = createTd();
		text = createText(item.khaiValue);
		createRows(khaiValue, text, trTag, target);

		const pm10Value = createTd();
		text = createText(item.pm10Value);
		createRows(pm10Value, text, trTag, target);

		const so2Value = createTd();
		text = createText(item.so2Value);
		createRows(so2Value, text, trTag, target);

		const coValue = createTd();
		text = createText(item.coValue);
		createRows(coValue, text, trTag, target);

		const no2Value = createTd();
		text = createText(item.no2Value);
		createRows(no2Value, text, trTag, target);

		const o3Value = createTd();
		text = createText(item.o3Value);
		createRows(o3Value, text, trTag, target);

		console.log(trTag);
	}

	// let tbodyData = "";
	// for (const item of data) {
	// 	tbodyData += "<tr>"
	// 		+ "<td>" + item.stationName + "</td>"
	// 		+ "<td>" + item.dataTime + "</td>"
	// 		+ "<td>" + item.khaiValue + "</td>"
	// 		+ "<td>" + item.pm10Value + "</td>"
	// 		+ "<td>" + item.so2Value + "</td>"
	// 		+ "<td>" + item.coValue + "</td>"
	// 		+ "<td>" + item.no2Value + "</td>"
	// 		+ "<td>" + item.o3Value + "</td>"
	// 		+ "</tr>";
	// }
	//
	// target.innerHTML = tbodyData;
}
/**
 * totalCount : 전체 개수
 * pageNo     : 현재 페이지 번호
 * numOfRows  : 페이지 당 개수
 */
const displayPagination = (totalCount, pageNo, numOfRows) => {
	// 전체 페이지 수
	const totalPage = Math.ceil(totalCount / numOfRows);

	// 페이징 바 요소 접근
	const paginationArea = document.getElementById("pagination-area");

	let pageItems = "";
	// 이전 버튼 부분
	pageItems += `<li class="page-item ${pageNo === 1 ? "disabled" : ""}">
					<a class="page-link" href="#" onclick="getAirPollutionV2(${pageNo-1})">Previous</a>
				  </li>`;

	// 페이지 번호 부분
	for(let i=1; i<=totalPage; i++) {
		pageItems += `<li class="page-item ${pageNo === i ? "active" : ""}">
						<a class="page-link" href="#" onclick="getAirPollutionV2(${i})">${i}</a>
					  </li>`;
	}

	// 다음 버튼 부분
	pageItems += `<li class="page-item ${pageNo === totalPage ? "disabled" : ""}">
	  				<a class="page-link" href="#" onclick="getAirPollutionV2(${pageNo+1})">Next</a>
				  </li>`;

	paginationArea.innerHTML = pageItems;
}

const createTd = () => {
	return document.createElement("td");
}

const createText = (itemsValue) => {
	return document.createTextNode(itemsValue);
}

const createRows = (colName, text, trTag, target) => {
	colName.appendChild(text);
	trTag.appendChild(colName);
	target.append(trTag);
}

