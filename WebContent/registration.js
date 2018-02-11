$(document).ready(function(e){

	
	
	
	$('#confirm').submit(function(e){
		e.preventDefault();
		
		var usernameInput = $('#usr').val();
		var surnameInput = $('#sur').val();
		var passwordInput = $('#pwd').val();
		var passwordRepeatedInput = $('#rpwd').val();
		var descriptionInput = $('#desc').val();
		var emailInput = $('#email').val();
		
		var params = $.param({
			usernameReg : usernameInput,
			surnameReg : surnameInput,
			passwordReg : passwordInput,
			passwordRep : passwordRepeatedInput,
			descriptionReg : descriptionInput,
			emailReg : emailInput
			
		});
		
		$.ajax({
			url: 'RegisterServlet?' + params,
			method: 'POST',
			dataType: 'json',
			success : function(response){
				if(response.status == "success"){
					
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


	

	
