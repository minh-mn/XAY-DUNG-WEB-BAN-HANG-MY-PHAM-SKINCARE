<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Dangu Shop</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS -->
    <link rel="stylesheet" href="https://unpkg.com/flickity@2/dist/flickity.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/web.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/cartMainCss.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/cart33.css">
</head>

<body>

    <!-- HEADER -->
    <jsp:include page="/Page/web/header.jsp" />

    <!-- PRODUCT -->
    <div class="product">
        <div class="container">
            <jsp:include page="/Page/component/main/productGroup.jsp" />
        </div>
    </div>

    <!-- CAROUSEL -->
    <jsp:include page="/Page/component/header/headerCarousel.jsp" />

    <!-- FOOTER -->
    <jsp:include page="/Page/web/footer.jsp" />

    <!-- JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.min.js"></script>
    <script src="${pageContext.request.contextPath}/Page/js/web.js"></script>

</body>
</html>
