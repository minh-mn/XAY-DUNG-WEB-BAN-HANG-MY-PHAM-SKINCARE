<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký | Dangu Shop</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/web.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/cartMainCss.css">
</head>

<body>

    <%@ include file="header.jsp" %>

    <div class="body-login">
        <div class="container">
            <div class="main">

                <form action="${pageContext.request.contextPath}/signup"
                      method="post"
                      class="form"
                      id="form-signup">

                    <h3 class="login-title">Đăng ký thành viên</h3>

                    <div class="form-group">
                        <input type="text" name="fullname" id="name" placeholder="Họ và tên">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <input type="text" name="username" id="surname" placeholder="Tên đăng nhập">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <input type="email" name="email" id="email" placeholder="Email">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <input type="password" name="password" id="password" placeholder="Mật khẩu">
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <input type="password" name="repassword" id="repassword" placeholder="Nhập lại mật khẩu">
                        <span class="form-message"></span>
                    </div>

                    <c:if test="${not empty mess}">
                        <p class="text-danger">${mess}</p>
                    </c:if>

                    <button type="submit" class="btn btn-success w-100">Đăng ký</button>

                    <a href="${pageContext.request.contextPath}/home"
                       class="btn btn-outline-secondary mt-3 w-100">
                        Quay về trang chủ
                    </a>
                </form>

            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/Page/js/web.js"></script>

</body>
</html>
