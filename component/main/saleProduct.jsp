<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="product-sale my-5">

    <!-- ================= HEADER ================= -->
    <div class="product-sale__header d-flex justify-content-between align-items-center">

        <!-- TITLE -->
        <div class="product-sale__title">
            <h4 class="mb-0">SẢN PHẨM KHUYẾN MÃI</h4>
        </div>

        <!-- COUNTDOWN -->
        <div class="product-sale__countdown d-flex align-items-center">
            <img src="Page/img/icon-deals-of.png" alt="deal" class="me-2">
            <span class="me-2">KẾT THÚC TRONG</span>

            <div class="countdown d-flex">
                <div id="countdown-days" class="mx-1"></div>
                <div id="countdown-hours" class="mx-1"></div>
                <div id="countdown-minutes" class="mx-1"></div>
                <div id="countdown-seconds" class="mx-1"></div>
            </div>
        </div>

        <!-- NAV BUTTON -->
        <div class="product-sale__direct">
            <button type="button" class="btn btn-outline-success me-1">
                <i class="fas fa-chevron-left"></i>
            </button>
            <button type="button" class="btn btn-outline-success">
                <i class="fas fa-chevron-right"></i>
            </button>
        </div>
    </div>

    <!-- ================= BODY ================= -->
    <div class="product-sale__body mt-4">
        <div class="row" id="seemore_space">

            <php:forEach items="${saleProducts}" var="product">
                <div class="col-lg-2 col-md-3 col-sm-6 mb-4 product_count_start">

                    <a href="product_detail?id=${product.getId()}"
                       class="product-sale__item d-block text-decoration-none">

                        <!-- IMAGE -->
                        <div class="product-sale__item-img text-center"
                             style="height:220px; display:flex; align-items:center; justify-content:center;">
                            <img src="${product.getFirstImage()}"
                                 alt="${product.getTitle()}"
                                 class="img-fluid">
                        </div>

                        <!-- NAME -->
                        <p class="product-sale__item-name mt-2 text-dark">
                            ${product.getTitle()}
                        </p>

                        <!-- PRICE -->
                        <div class="product-sale__item-price">
                            <span class="product-sale__item-price-curr text-danger fw-bold">
                                ${product.getFormatPriceStandard()}đ
                            </span>
                            <br>
                            <del class="product-sale__item-price-old text-muted">
                                ${product.getFormatPriceDefault()}đ
                            </del>
                        </div>

                    </a>
                </div>
            </php:forEach>

        </div>
    </div>

</section>
