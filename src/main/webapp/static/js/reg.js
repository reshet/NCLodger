
function check() {
		var uname = document.getElementById('uname').value;
		var pswd1 = document.getElementById('pswd1').value;
		var pswd2 = document.getElementById('pswd2').value;
	    var email = document.getElementById('email').value;
	    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
							
		// Check email validity
	    if (!filter.test(email)) {
			document.getElementById("span_email").style.display = 'inline';		
		}
		else { 
			document.getElementById("span_email").style.display = 'none';
		}
		
		// Check the length of each the inpute
		if (uname.length < 6) {
			document.getElementById("span_uname").style.display = 'inline';					
		}
		else {
			document.getElementById("span_uname").style.display = 'none';
		}
		
		if (pswd1.length < 6) {
			document.getElementById("span_pswd1").style.display = 'inline';		
		}
		else {
			document.getElementById("span_pswd1").style.display = 'none';
		}					
		
		// Check that passwords are match 
		if (pswd2 != pswd1) {			
			document.getElementById("span_pswd2").style.display = 'inline';			
		}
		else {
			document.getElementById("span_pswd2").style.display = 'none';
		}	
			
}
