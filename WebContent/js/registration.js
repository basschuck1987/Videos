$(document).ready(function(e){
	
	$('#confirm').submit(function(e){
		e.preventDefault();
		
		var usernameInput = $('#usr').val();
		var surnameInput = $('#sur').val();
		var nameInput = $('#name').val();
		var passwordInput = $('#pwd').val();
		var passwordRepeatedInput = $('#rpwd').val();
		var descriptionInput = $('#desc').val();
		var emailInput = $('#email').val();
		
		var params = $.param({
			usernameReg : usernameInput,
			surnameReg : surnameInput,
			nameReg : nameInput,
			passwordReg : passwordInput,
			passwordRep : passwordRepeatedInput,
			descriptionReg : descriptionInput,
			emailReg : emailInput
			
		});
		console.log(params);
		$.ajax({
			url: 'RegisterServlet?' + params,
			method: 'POST',
			dataType: 'json',
			success : function(response){
				if(response.status == "success"){
					window.location.replace("/Videos/user.html?id=" + response.user.id);
					console.log(response);
				}else{
					alert(response.message);
				}
			},
			error: function(request, message, error){
				alert(error);
			}
			
		});
	});
	
});


	

	
