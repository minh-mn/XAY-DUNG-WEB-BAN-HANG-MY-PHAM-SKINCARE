<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="product-norm my-5">

    <!-- ===== HEADER ===== -->
    <div class="product-norm__header text-center mb-3">
        <h4 class="fw-bold">SẢN PHẨM TRANG ĐIỂM</h4>
    </div>

    <!-- ===== CATEGORY NAV ===== -->
    <ul class="product-norm__nav row justify-content-center mb-4">
        <php:forEach items="${saleMakeups}" var="step" varStatus="status">
            <li class="col-md-2 text-center
                ${status.first ? 'product-norm__active' : ''}">
                <p class="mb-0">${step.getName()}</p>
            </li>
        </php:forEach>
    </ul>

    <!-- ===== PRODUCT SLIDER ===== -->
    <div class="product-norm__wrap">
        <div class="carousel"
             data-flickity='{
                "freeScroll": true,
                "wrapAround": true,
                "pageDots": false,
                "autoPlay": true
             }'>

            <php:forEach items="${MakeupProducts}" var="makeup">
                <div class="col-lg-3 carousel-cell">
                    <div class="product-norm__items shadow-sm bg-white rounded h-100">

                        <!-- IMAGE -->
                        <div class="product-norm__img text-center">
                            <a href="product_detail?id=${makeup.getId()}">
                                <img src="${makeup.getFirstImage()}"
                                     class="img-fluid"
                                     alt="${makeup.getTitle()}">
                            </a>
                        </div>

                        <!-- INFO -->
                        <div class="product-norm__detail p-3">

                            <h6 class="product-norm__title">
                                <a href="product_detail?id=${makeup.getId()}"
                                   class="product-norm__link">
                                    [NEW] ${makeup.getTitle()}
                                </a>
                            </h6>

                            <p class="product-norm__brand mb-1">
                                <a href="brand?id=${makeup.getBrand()}"
                                   class="product-norm__link--brand">
                                    ${makeup.getBrandName()}
                                </a>
                            </p>

                            <div class="product-norm__price">
                                <span class="product-norm__price--curr text-danger fw-bold">
                                    ${makeup.getFormatPriceStandard()} ₫
                                </span>

                                <div class="product-norm__quantity">
                                    <p class="text-muted mb-0">
                                        <del>${makeup.getFormatPriceDefault()} ₫</del>
                                    </p>
                                    <span class="product-norm__quantity--selled">
                                        Đã bán ${makeup.getSold()}
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </php:forEach>

        </div>
    </div>

</section>
