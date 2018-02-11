
	
	var password = document.getElementById("pwd")
	  , confirm_password = document.getElementById("rpwd");

	function validatePassword(){
	  if(pwd.value != rpwd.value) {
	    confirm_password.setCustomValidity("Whoops, these passwords don't match");
	  } else {
	    confirm_password.setCustomValidity('');
	  }
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;	

	
	
