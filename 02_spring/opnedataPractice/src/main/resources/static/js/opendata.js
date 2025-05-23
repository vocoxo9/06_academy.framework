onload = () => {
    document.getElementById("getOpendataList").addEventListener("click", () => {
        getOpendataList();
    });
}

// 지진해일 대피장소 정보 조회
const getOpendataList = () => {
    // [GET] /getEmergencyShelterList
    fetch("/getEmergencyShelterList")
        .then((response)=>response.json())
        .then((data)=>{
            console.log(data);

            const tbody = document.querySelector("#listSection tbody");
            tbody.textContent = "";

            for(const item of data){

                const trTag = document.createElement("tr");

                const keys = Object.keys(item);
                // console.log(keys);

                for(const key of keys){
                    const tdTag = document.createElement("td");
                    tdTag.textContent = item[key];
                    trTag.appendChild(tdTag);
                }

                tbody.appendChild(trTag);

            }
        })
        .then((error)=>{
            console.error(error);
        })
}