<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${productDetail.title}</title>

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

<div class="view-pro">
    <div class="container">

        <!-- BREADCRUMB -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
                </li>
                <li class="breadcrumb-item active">
                    ${productDetail.title}
                </li>
            </ol>
        </nav>

        <div class="row">
            <!-- IMAGE -->
            <div class="col-lg-5">
                <div class="view-img">
                    <div class="view-img__pro-main">
                        <img src="${productDetail.firstImage}" class="img-main">
                    </div>

                    <div class="view-img__list">
                        <c:forEach items="${listImages}" var="img">
                            <div class="view-img__list-item">
                                <img src="${img.image}">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!-- INFO -->
            <div class="col-lg-7">
                <div class="view-info">
                    <h4 class="view-info__title">${productDetail.title}</h4>

                    <div class="view-rate">
                        <i class="fas fa-star star--gold"></i>
                        <i class="fas fa-star star--gold"></i>
                        <i class="fas fa-star star--gold"></i>
                        <i class="fas fa-star star--gold"></i>
                        <i class="fas fa-star star--gold"></i>
                        <span>${productDetail.rating} sao</span>
                    </div>

                    <div class="view-price">
                        <span class="curr-price">
                            ${productDetail.formatPriceStandard}đ
                        </span>
                        <del class="old-price">
                            ${productDetail.formatPriceDefault}đ
                        </del>
                        <span class="percent-sale">
                            -${productDetail.discount}%
                        </span>
                    </div>

                    <p class="view-info__text">
                        ${productDetail.description}
                    </p>

                    <form action="${pageContext.request.contextPath}/addProductToCart" method="post">
                        <input type="hidden" name="productId" value="${productDetail.id}">

                        <div class="choose-quantity">
                            <span>Số lượng:</span>
                            <input type="number" name="quantity" value="1" min="1" max="20">
                        </div>

                        <button class="btn btn-danger mt-3">
                            Thêm vào giỏ hàng
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- FOOTER -->
<jsp:include page="/Page/web/footer.jsp" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/Page/js/web.js"></script>

</body>
</html>
