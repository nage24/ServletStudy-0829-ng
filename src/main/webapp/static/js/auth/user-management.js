/**
 * 
 */
const addButton = document.querySelector(".add-button");

load();

function load() {
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/user/management/users",
		// data: ,	// 데이터를 가져오는 용도의 load 함수에는 데이터가 필요없고 타입만 지정해준다. 
		dataType: "json",
		success: (response) => {
			console.log(response);
			getUserList(response);
		},
		error: (error) => {	
			console.log(error);
		}
	});
	
}

function getUserList(userList) {
	const tbody = document.querySelector("tbody");
	
	tbody.innerHTML = ""; // 매번 새로 불러와야 하기 때문에 초기화 한번 해줌. 
	
	for(let user of userList) {
		tbody.innerHTML += `
					<tr>
						<td class="usercode-text">${user.user_code}</td>
						<td class="userid-text">${user.user_id}</td>
						<td>${user.user_password}</td>
						<td>${user.user_name}</td>
						<td>${user.user_email}</td>
						<td>
							<span class="phone-text">${user.user_phone}</span>
							<input type="text" class="phone-update-input visible" value="${user.user_phone}">
						</td>
						<td>
							<span class="address-text">${user.user_address}</span>
							<input type="text" class="address-update-input visible" value="${user.user_address}">
						</td>
						<td>
							<button type="button" class="update-button">수정</button>
							<button type="button" class="update-ok-button visible">확인</button>
						</td>
						<td><button type="button" class="delete-button">삭제</button></td>
					</tr>
		`;
	}
	
	
	const updateButtons = document.querySelectorAll(".update-button");
	
	for(let i = 0; i < userList.length; i++) {
		updateButtons[i].onclick = () => {
			// alert("버튼 번호: " + i);
			const phoneText = document.querySelectorAll(".phone-text")[i];
			const addressText = document.querySelectorAll(".address-text")[i];
			const phoneUpdateInput = document.querySelectorAll(".phone-update-input")[i];
			const addressUpdateInput = document.querySelectorAll(".address-update-input")[i];
			const updateOkButton = document.querySelectorAll(".update-ok-button")[i];
			
			const userCodeText = document.querySelectorAll(".usercode-text")[i].textContent;
			
			updateButtons[i].classList.toggle("visible");
			phoneText.classList.toggle("visible");
			addressText.classList.toggle("visible");
			phoneUpdateInput.classList.toggle("visible");
			addressUpdateInput.classList.toggle("visible");
			updateOkButton.classList.toggle("visible");
			
			// console.log();
			
			updateOkButton.onclick = () => {
				
				$.ajax({
					async: false,
					type: "post",
					url: "/api/v1/user/update",
					data : {
						userCode : userCodeText,
						phone : phoneUpdateInput.value,
						address : addressUpdateInput.value
					},
					dataType : "json",
					success: (response) => {
						alert("수정완료");
						load();
					},
					error: (error) => {
						console.log(error);
					}
					
				});
			}
		}
		
	}
	
	
	const deleteButtons = document.querySelectorAll(".delete-button");
	
	for(let i = 0; i < userList.length; i++) {
		deleteButtons[i].onclcik = () => {
			
			const userIdText = document.querySelectorAll(".userid-text")[i].textContent;
			const userCodeText = document.querySelectorAll(".usercode-text")[i].textContent;
			
			if(confirm("${userIdText}의 정보를 정말 지우시겠습니까?")) {
				$.ajax({
					async: false,
					type: "post",
					url: "/api/v1/user/delete",
					data: {
						userCode : userCodeText
					},
					dataType: "json",
					success: (response) => {
						alert("삭제되었습니다.");
						load();
					},
					error: (error) => {
						console.log(error);
					}
				});
				}	
			}					
		}
}

addButton.onclick = () => {
	if (checkSpaceUserInput()) { // true이면 공백이 없다는 것임. 
		if (checkUserId()) {
			alert("추가 가능");
			saveUser();
		} else {
			alert("아이디 중복으로 인해 추가 불가능");
		}
	}else{
		alert("공백 때문에 추가 불가능");
	}
}

function saveUser() {
	const userInputs = document.querySelectorAll(".user-input");
	
	const user = {
		userId: userInputs[0].value,
		userPassword: userInputs[1].value,
		userName: userInputs[2].value,
		userEmail: userInputs[3].value
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/user",
		data: user,
		dataType: "json",
		success: (response) => {
			if(response.status) {
				alert("추가 성공");
				load();
			}else{
				alert("추가 실패");
			}
		},
		error: (error) => {
			console.log(error);
		}
	});
}

function checkUserId() {
	const userId = document.querySelectorAll(".user-input")[0].value;
	let result = false;
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/user/overlap/user-id",
		data: {
			"userId" : userId
		},
		dataType: "json",
		success: (response) => {
			result = response.checkFlag;
		},
		error: (error) => {
			console.log(error);
		}
	});
	
	return result;
}


function checkSpaceUserInput() {
	const userInputs = document.querySelectorAll(".user-input");
	let result = true;
	
	for(let input of userInputs) {
		// alert(input.value);
		if(isEmpty(input.value)) {
			alert("모든 값을 입력해주세요.");
			return false;
		}
	}
	
	return result;
}

function isEmpty(str) {
	return str == null || typeof str == undefined || str == "" || str.replace(" ", "") == "" || str.length == 0;
}
