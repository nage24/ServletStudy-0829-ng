
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
	
	tbody.innerHTML = ``; // 매번 새로 불러와야 하기 때문에 초기화 한번 해줌. 
	
	for(let user of userList) {
		tbody.innerHTML += `
					<tr>
						<td>${user.user_code}</td>
						<td>${user.user_id}</td>
						<td>${user.user_password}</td>
						<td>${user.user_name}</td>
						<td>${user.user_email}</td>
						<td>${user.user_phone}</td>
						<td>${user.user_address}</td>
					</tr>
		`;
	}
}


addButton.onclick = () => {
	if (checkSpaceUserInput()) { // true이면 공백이 없다는 것임. 
		if (checkUserId()) {
			alert("추가 가능");
			
		} else {
			alert("아이디 중복으로 인해 추가 불가능");
		}
	}else{
		alert("공백 때문에 추가 불가능");
	}
}

function saveUser() {
	const UserInputs = document.querySelectorAll(".user-input");
	
	const user = {
		userId: userInputs[0].value,
		userPassword: userInputs[1].value,
		userName: userInputs[2].value,
		userEmail: userInputs[3].value,
	}
	
	$.ajax({
		async: false,
		type: post,
		url: "api/v1/user",
		data: user,
		dataType: "json",
		success: (response) => {
				
		},
		error: (error) => {
			
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
