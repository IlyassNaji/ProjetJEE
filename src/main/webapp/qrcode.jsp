<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Setup 2FA</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center">
            <h2>Setup Two-Factor Authentication</h2>
            <p>1. Install Google Authenticator on your phone</p>
            <p>2. Scan this QR code with the app:</p>
            <img src="qrcode" alt="QR Code" class="img-fluid mb-3">
            <p>3. Your secret key (if needed): <%= session.getAttribute("secretKey") %></p>
            <a href="login.jsp" class="btn btn-primary">Proceed to Login</a>
        </div>
    </div>
</div>
</body>
</html>
