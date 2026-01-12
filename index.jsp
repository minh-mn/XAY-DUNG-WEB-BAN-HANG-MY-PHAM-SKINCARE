<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Dangu Shop</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://unpkg.com/flickity@2/dist/flickity.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/web.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Page/css/cartMainCss.css">

</head>

<body>

    <!-- HEADER -->
    <jsp:include page="/Page/web/header.jsp" />

    <!-- SLIDER -->
    <jsp:include page="/Page/component/header/headerCarousel.jsp" />

    <!-- PRODUCT -->
    <div class="product">
        <div class="container">

            <jsp:include page="/Page/component/main/flashDeal.jsp" />
            <jsp:include page="/Page/component/main/allEvent.jsp" />
            <jsp:include page="/Page/component/main/deal1k.jsp" />

            <div class="d-flex justify-content-around mt-5 mb-5">
                <c:forEach items="${categories}" var="cate" begin="0" end="9">
                    <a class="btn"
                       style="background-color:#990000;color:white"
                       href="${pageContext.request.contextPath}/category?id=${cate.id}">
                        ${cate.name}
                    </a>
                </c:forEach>
            </div>

            <jsp:include page="/Page/component/main/saleProduct.jsp" />

            <div class="text-center d-flex justify-content-center" id="seemorebtn">
                <button class="btn btn-dark">Xem thêm</button>
            </div>

            <jsp:include page="/Page/component/main/banner1.jsp" />
            <jsp:include page="/Page/component/main/normMakeup.jsp" />
            <jsp:include page="/Page/component/main/banner2.jsp" />
            <jsp:include page="/Page/component/main/normSkincare.jsp" />

        </div>
    </div>

    <!-- FOOTER -->
    <jsp:include page="/Page/web/footer.jsp" />

    <!-- JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.min.js"></script>
    <script src="${pageContext.request.contextPath}/Page/js/web.js"></script>

    <script>
        $(function () {
            $("#seemorebtn").click(function () {
                let start = $(".product_count_start").length;
                $.post(
                    "${pageContext.request.contextPath}/seemore",
                    { start: start },
                    function (res) {
                        $("#seemore_space").append(res);
                    }
                );
            });
        });
    </script>

</body>
</html>
