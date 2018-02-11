$(document).ready(function(e){
	
	var usersDiv = $('#usersDiv');
	
	//LOGIN REGISTER BUTTONS
	var loginRegisterButtons = $(".loginRegisterButtons");
	
	//MY PROFILE BUTTON
	var myProfileButton = $(".myProfileButton");
	myProfileButton.hide();
	
	// LOGOUT BUTTON
	var logoutButton = $("#logoutButton");
	logoutButton.hide();
	
	//USER BUTTON
	var usersButton = $("#usersButton");
	usersButton.hide();
	
	var removeButton = null;
	
	getUsers();
	
	function initUsers(users){
		usersDiv.empty();
		for (var i = 0; i < users.length; i++) {
			appendUser(users[i]);
		}
		
		removeButton = $('.removeUser');
		
		removeButton.click(function(e){
			e.preventDefault();
			 var text = $(this).attr('value');
			 var params = $.param({
				 idRemove : text,
				 action : "delete"
			 });
			 console.log(text);
			$.ajax({
				url: 'UserServlet?' + params,
				method: 'POST',
				dataType: 'json',
				success: function(response){
					if(response.status == "success"){
						getUsers();
					}else{
						alert(response.message);
					}
			
				},
				error: function(request, message, error){
					alert(error);
				}
					
				
				
			});
			
			
			
		});
	};
	
	$('#goToProfile').button().click(function(){
		window.location.href = '/Videos/user.html?id=' + loggedInUser.id;
	});

function appendUser(user){
	var tableRow= $('<tr></tr>');
	var userName = $('<td><div class="glyphicon glyphicon-user"><a href="/Videos/user.html?id='+user.id+'">'+" "+ user.username +'</a></div>');
	var name = $('<td>'+user.name+'</td>');
	var surname = $('<td>'+user.surname+'</td>');
	var email = $('<td>'+user.email+'</td>');
	var role = $('<td>'+user.role +'</td>');
	var button= $('<td><button type="button" class="removeUser btn btn-xs" value="'+user.id+'"><span class="glyphicon glyphicon-remove"></span></button></td>')

	tableRow.append(userName);
	tableRow.append(name);
	tableRow.append(surname);
	tableRow.append(email);
	tableRow.append(role);
	tableRow.append(button);
	usersDiv.append(tableRow);
	
}

function showHide(loggedInUser){
	console.log(loggedInUser);
	if(loggedInUser == null){
		usersButton.hide();
		myProfileButton.hide();
	}else{
		myProfileButton.show();
		loginRegisterButtons.hide();
		logoutButton.show();
		if(loggedInUser.role == 'ADMIN'){
			usersButton.show();
		}else if(loggedInUser.role == 'USER'){
			usersButton.hide();
		}
	}
		
};

function getUsers(){
	$.ajax({
		url: 'UsersServlet',
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				initUsers(response.users);
				showHide(response.loggedInUser);
				loggedInUser = response.loggedInUser;
			}else{
				console.log(response)
				alert(response.message);
				window.location.replace("/Videos/index.html");
			}
	
		},
		error: function(request, message, error){
			alert(error);
		}
	});
};
$('#loginForm').submit(function(e){
	e.preventDefault();
	
	var x = $('#inputUsername').val().trim();
	var p = $('#inputPassword').val();
	var params = $.param({
		username: x,
		password: p
	});
	console.log(params)
	$.ajax({
		url: 'LoginServlet?' + params,
		method: 'POST',
		dataType: 'json',
		success: function(response){
			if(response.status == 'failure'){
				alert(response.message)
			} 
			getVideos();
			
		},
		error: function(request, message, error){
			alert(error)
		}
	});

});


$('#usernameAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "username"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
$('#usernameDesc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "DESC",
		orderBy: "username"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
			},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
$('#nameAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "name"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
$('#nameDesc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "DESC",
		orderBy: "name"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
$('#surnameAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "surname"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
$('#surnameDesc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "DESC",
		orderBy: "surname"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});

$('#emailAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "email"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
$('#roleAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "role"
	});
	console.log(params)
	$.ajax({
		url: 'UsersServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initUsers(response.users);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
});