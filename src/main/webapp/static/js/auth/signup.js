

const signupButton = document.querySelector(".signup-button");

signupButton.onclick = () => {

	const user = {
		userId: document.querySelector(".user-id").value,
		userPassword: document.querySelector(".user-password").value,
		userName: document.querySelector(".user-name").value,
		userEmail: document.querySelector(".user-email").value
	}
	
	send(user);
}

function send(user) {
	$.ajax({
		async: false, 					//async 동기(false), 비동기(true, 기본값)
		type: "post", 					// RequestMethod
		url: "/api/v1/auth/signup",				// 요청 URL
		data: user,						// 전송 데이터 : board 객체
		dataType: "json",				// 응답 데이터의 형식 (ContentType)
		success: (response) => {		// 요청에 대한 응답이 성공했으면 실행되는 function	
			alert("회원가입 성공!")
			console.log(response);
			document.querySelector("body").innerHTML = `
				<h1>${response.userId}</h1>
				<h1>${response.userPassword}</h1>
				<h1>${response.userName}</h1>
				<h1>${response.userEmail}</h1>
			`
		},
		error: (error) => {				// 요청 또는 응답이 실패하였을 때 오류처리
			console.log(error);
		}
		
	})
}

//				<h1>${response.id}</h1>
//				<h1>${response.pw}</h1>
//				<h1>${response.name}</h1>
//				<h1>${response.email}</h1>