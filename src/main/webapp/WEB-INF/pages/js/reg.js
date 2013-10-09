
function check() {
		var fname = document.getElementById('fnmae').value;
		var pass = document.getElementById('pass').value;
		var pass_confirm = document.getElementById('pass_confirm').value;
	    var email = document.getElementById('email');
	    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				
		// Check the length of each the inpute
		if (fname.length < 6) document.getElementById("fname_valid").style.display = "inline";
		else document.getElementById("fname_valid").style.display = "none";
		
		if (pass.length < 6) document.getElementById("pass_valid").style.display = "inline";
		else document.getElementById("pass_valid").style.display = "none";	
		
		if (pass_confirm.length < 6) document.getElementById("pass_valid2").style.display = "inline";
		else document.getElementById("pass_valid2").style.display = "none";						
		
		// Check that passwords are match 
		if (pass != pass_confirm) 
			document.getElementById("pass_valid2").style.display = "inline";
		else 
			document.getElementById("pass_valid2").style.display = "none";
			
		// Check email validity
	    if (!filter.test(email.value)) document.getElementById("email_valid").style.display = "inline";
		else document.getElementById("email_valid").style.display = "none";	 
			
}
