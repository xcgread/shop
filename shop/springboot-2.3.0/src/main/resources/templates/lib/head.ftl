<#macro htmlBase skinid>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
	var basepath = "${request.contextPath}";
</script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="${description}" />
<meta name="keywords" content="${keywords}" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<!-- Required Stylesheets -->
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/text.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/fonts/ptsans/stylesheet.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/fluid.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/mws.style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/icons/icons.css" media="screen" />
<!-- Demo and Plugin Stylesheets -->
<link rel="stylesheet" type="text/css" href="${request.contextPath}/plugins/colorpicker/colorpicker.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/jui/jquery.ui.css" media="screen" />
<!-- Theme Stylesheet -->
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/mws.theme.css" media="screen" />
<!-- JavaScript Plugins -->
<script type="text/javascript" src="${request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${request.contextPath}/plugins/colorpicker/colorpicker.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/mws.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/themer.js"></script>



</head>
<#nested />
</html>
</#macro>
