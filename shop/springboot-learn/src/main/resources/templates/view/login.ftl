<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CMS Admin - Login Page</title>
<script>
	var basepath = "${request.contextPath}";
</script>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/text.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/fonts/ptsans/stylesheet.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/fluid.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/mws.style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/icons/icons.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/icons/icons.css" media="screen" />
<script type="text/javascript" src="${request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script>
	function dologin() {
		jQuery.ajax({
			url : '${request.contextPath}/login',
			type : "POST",
			success : function(data) {
				location.href = "${request.contextPath}/dashboard";
			}
		});
	}
</script>
</head>
<body>
	<div id="mws-login">
    	<h1>Login</h1>
        <div class="mws-login-lock"><img src="${request.contextPath}/css/icons/24/locked-2.png" alt="" /></div>
    	<div id="mws-login-form">
        	<div class="mws-form">
                <div class="mws-form-row">
                	<div class="mws-form-item large">
                    	<input type="text" class="mws-login-username mws-textinput" placeholder="username" />
                    </div>
                </div>
                <div class="mws-form-row">
                	<div class="mws-form-item large">
                    	<input type="password" class="mws-login-password mws-textinput" placeholder="password" />
                    </div>
                </div>
                <div class="mws-form-row">
                	<input type="submit" onclick="dologin();" value="Login" class="mws-button green mws-login-button" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>
