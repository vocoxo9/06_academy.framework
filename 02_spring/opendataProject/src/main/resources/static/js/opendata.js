// 모든 요소가 로드되었을 때
onload = () => {

	// 아이디 속성이 btn1인 요소가 클릭되었을 때 (이벤트 핸들러)
	document.getElementById("btn1").addEventListener("click", ()=>{

		// alert("클릭@@");
		// getAirPollution();
		// getAirPollutionV2();
		getAirPollutionV3();
	});

}

// 프론트(브라우저)에서 직접 공공데이터 서버로 요청 { 요청방식, 요청주소(URL), 요청파라미터(서비스키, 시도명) }
const getAirPollutionV3 = () => {
	const location = document.getElementById("location").value;

	// * 요청 파라미터
	const serviceKey = "6ts24t1asdIdIcsFCu%2BLKytanC50N5KMlMorvtTVVJre1sD6hwAPbkYt1BXjteTdaooJb0vB0ocyXk1BicepqA%3D%3D";
	const sidoName = location;

	// * 요청주소
	const url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=" + serviceKey 
		+ "&sidoName=" + encodeURI(sidoName)
		// encodeURI(text) :  {text} 문자를 UTF-8로 인코딩 처리해 줌
		// decodeURI(text) :  encodeURI 처리된 {text}의 원본으로 디코딩 처리해 줌
		+ "&returnType=xml";

	// * 요청 방식 (GET) -> fetch/ajax/axios 사용 시 설정 또는 확인
	fetch(url)
		.then(response => response.text())
		.then(data => {
			// console.log(data);				// -> 데이터가 text 형태로 확인 됨
			try {
				/*	[1] json 타입으로 처리
				data = JSON.parse(data);		// text로 뽑은 데터를 json 형태로 파싱 작업(text가 json 형태여야 함)
				// console.log(data);				// -> 데이터가 객체 형태로 확인됨

				const bodyData = data.response.body;
				console.log(bodyData);

				// 표시할 항목 목록을 전달
				const displayList = ["stationName", "dataTime", "khaiValue", "pm10Value", "so2Value", "coValue", "no2Value", "o3Value"];
				displayAirPollutionData(bodyData.items, displayList);		// bodyData 중 배열 형태인 items를 전달
				displayPagination(bodyData.totalCount, bodyData.pageNo, bodyData.numOfRows);
				*/

				//------------------------------------------------------------------
				// [2] xml 타입으로 처리
				// * DOMParser 객체를 사용하여 파싱 처리
				const xmlParser = new DOMParser();
				data = xmlParser.parseFromString(data, "text/xml");		// data가 어떤 형식인지 두번째 인자값으로 지정

				console.log(data);
				const totalCount = data.querySelector("totalCount").textContent;
				const pageNo = data.querySelector("pageNo").textContent;
				const numOfRows = data.querySelector("numOfRows").textContent;
				console.log(totalCount, pageNo, numOfRows);

				displayAirPollutionXmlData(data);
				displayPagination(totalCount, pageNo, numOfRows);
			} catch (e){
				console.log(e);
			}

		})
		.catch(error => console.error(error));

}

/* 대기오염 정보를 화면에 표시 (xml 형식)
	data : XML 형식
 */
const displayAirPollutionXmlData = (data) => {
	// data => Document 문서 형태. item 태그에 해당하는 부분만 추출
	const itemArr = data.getElementsByTagName("item");
	// -> HTML 요소에 접근할 때와 마찬가지로 XML도 객체 형태로 저장되어 있음

	let tbodyText = "";
	for(const item of itemArr){
		tbodyText += `<tr>
						<td>${item.querySelector('stationName').textContent}</td>
						<td>${item.querySelector('dataTime').textContent}</td>
						<td>${item.querySelector('khaiValue').textContent}</td>
						<td>${item.querySelector('pm10Value').textContent}</td>
						<td>${item.querySelector('coValue').textContent}</td>
						<td>${item.querySelector('no2Value').textContent}</td>
						<td>${item.querySelector('so2Value').textContent}</td>
						<td>${item.querySelector('o3Value').textContent}</td>
					</tr>`;
	}

	document.querySelector("#air-list tbody").innerHTML = tbodyText;
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

const displayAirPollutionData = (data, keyList) => {
	// 전달된 데이터가 배열인지 아닌지 체크
	if (!Array.isArray(data)) {
		console.error("data is not array.");
		return;
	}

	// console.log(data);

	// tbody 요소에 접근
	const target = document.querySelector("#air-list tbody");
	// target 초기화
	target.textContent = "";

	for(const item of data){
		// tr 요소 생성
		const trElement = document.createElement("tr");

		const keys = keyList || Object.keys(item);				// 전달 받은 keyList 가 없으면? item에서 key값을 추출해서 사용하도록

		for(const key of keys){
			const tdElement = document.createElement("td");
			tdElement.textContent = item[key];
			trElement.appendChild(tdElement);
		}

		target.appendChild(trElement);

		/*
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
	*/
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
	paginationArea.textContent = "";		// -> 페이징 바 영역 초기화 해주기

	/* [1]
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

	*/

	/**
	 *
	 * @param pageNo 			페이지 번호
	 * @param text				표시할 텍스트
	 * @param liOptionalClass	추가적으로 설정할 클래스명
	 */
	const makeListItem = (pageNo, text, liOptionalClass = "") => {				// option으로 받을 항목은 뒷 쪽에 배치하기
		// li 노드 생성
		const liElement = document.createElement("li");
		liElement.classList.add("page-item");

		if(liOptionalClass != "") liElement.classList.add(liOptionalClass);

		// a 노드 생성
		const aElement = document.createElement("a");
		aElement.classList.add("page-link");
		aElement.setAttribute("href", "#");
		aElement.addEventListener("click", () => {
			getAirPollutionV2(pageNo);
		});
		aElement.textContent = text;

		liElement.appendChild(aElement);

		return liElement;
	}

	/* [2]
	// 이전 버튼 부분
	const liElement = document.createElement("li");
	liElement.classList.add("page-item");
	if ( pageNo === 1) liElement.classList.add("disabled");						// 조건에 따른 class 속성 추가

	// a 태그
	const aElement = document.createElement("a");
	aElement.classList.add("page-link");
	aElement.setAttribute("href", "#");						// 속성 추가 => 속성명, 값

	// onclick 속성 추가
	aElement.addEventListener("click", () => {
		getAirPollutionV2(pageNo-1);
	});
	aElement.textContent = "Previous";

	liElement.appendChild(aElement);											// li 태그에 a 태그 자식으로 추가

	paginationArea.appendChild(liElement);
	*/

	// 이전 버튼
	const prevPageItem = makeListItem(pageNo-1, "Previous", pageNo === 1? "disabled": "");			// 연산식 변수로 분리해 놓으면 좋음
	paginationArea.appendChild(prevPageItem);

	// 페이지 번호 부분
	for(let i=1; i<=totalPage; i++){
		const pageNoItem = makeListItem(i, i, pageNo === i? "active": "");
		paginationArea.appendChild(pageNoItem);
	}

	// 다음 버튼
	const nextPageItem = makeListItem(pageNo+1, "Next", pageNo === totalPage? "disabled": "");
	paginationArea.appendChild(nextPageItem);
	
	
}




/* ---------------------------------------------------------
25-05-15) innerHTML 대체해보기

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
*/

// 25-05-16) pagination에서 반복되는 부분 함수로 빼기
const createClass = (totalCount, pageNo, numOfRows) => {
	liElements = document.createElement("li");
	liElements.classList.add("page-item");
	if(pageNo === 1 ){
		liElements.classList.add("disabled");
	} else if (pageNo === pageNo){

	}
}