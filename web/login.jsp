<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Login | DANGU Shop</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet">

    <!-- CSS local (dùng contextPath) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/web.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/cartMainCss.css">
</head>

<body>

<!-- HEADER -->
<jsp:include page="/Page/web/header.jsp" />

<div class="body-login">
    <div class="container">
        <div class="main">
            <form action="${pageContext.request.contextPath}/login"
                  method="post"
                  class="form"
                  id="form-login">

                <h3 class="login-title">Đăng nhập</h3>

                <div class="form-group">
                    <input type="text" name="username" placeholder="Tên đăng nhập" required>
                </div>

                <div class="form-group">
                    <input type="password" name="password" placeholder="Mật khẩu" required>
                </div>

                <!-- THÔNG BÁO LỖI -->
                <c:if test="${not empty mess}">
                    <p class="text-danger">${mess}</p>
                </c:if>

                <a class="login-forget" onclick="forgetPassword()">Quên mật khẩu</a>

                <div class="login-btn">
                    <button type="submit" class="btn btn-outline-secondary">
                        Đăng nhập
                    </button>
                </div>

                <a href="${pageContext.request.contextPath}/home"
                   class="login-not btn btn-success w-25">
                    Quay về trang chủ
                </a>

            </form>
        </div>
    </div>
</div>

<!-- FOOTER -->
<jsp:include page="/Page/web/footer.jsp" />

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function forgetPassword() {
        alert("Chức năng quên mật khẩu đang được phát triển");
    }
</script>

</body>
</html>
