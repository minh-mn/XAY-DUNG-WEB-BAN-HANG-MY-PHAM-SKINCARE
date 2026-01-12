<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<div class="product my-5">
    <div class="container">

        <!-- ===== HEADER ===== -->
        <div class="product-sale mb-3">

            <div class="d-flex align-items-center justify-content-between flex-wrap gap-3">

                <!-- Title -->
                <div>
                    <img src="Page/img/donggia1k.png" alt="Deal 1K"
                         style="max-width: 280px;" class="img-fluid">
                </div>

                <!-- Countdown -->
                <div class="d-flex align-items-center text-danger fw-bold fs-5">
                    <span id="countdown-h">00</span>
                    <span class="mx-1">:</span>
                    <span id="countdown-m">00</span>
                    <span class="mx-1">:</span>
                    <span id="countdown-s">00</span>
                </div>

                <!-- View all -->
                <div>
                    <a href="event?id=8" class="text-danger fw-semibold">
                        XEM TẤT CẢ DEAL →
                    </a>
                </div>

            </div>
        </div>

        <!-- ===== BODY ===== -->
        <div class="product-sale__body">
            <div class="carousel"
                 data-flickity='{
                     "freeScroll": true,
                     "wrapAround": true,
                     "pageDots": false,
                     "cellAlign": "left",
                     "contain": true,
                     "autoPlay": 3000
                 }'>

                <php:forEach items="${flashdeal2s}" var="item">
                    <div class="carousel-cell px-2" style="width: 200px;">

                        <a href="product_detail?id=${item.getId()}"
                           class="product-sale__item d-block text-decoration-none text-dark">

                            <!-- Image -->
                            <div class="position-relative mb-2">
                                <img
                                    src="${item.getFirstImage()}"
                                    alt="${item.getTitle()}"
                                    class="img-fluid w-100"
                                    style="height: 180px; object-fit: cover;"
                                >

                                <!-- Discount -->
                                <div class="position-absolute top-0 start-0 bg-danger text-white px-2 py-1 small">
                                    -${item.getDiscount()}%
                                </div>
                            </div>

                            <!-- Name -->
                            <p class="product-sale__item-name text-truncate">
                                ${item.getTitle()}
                            </p>

                            <!-- Price -->
                            <div class="d-flex align-items-center gap-2">
                                <span class="fw-bold text-danger">1K</span>
                                <del class="text-muted small">
                                    ${item.getFormatPriceDefault()}
                                </del>
                            </div>

                            <!-- Progress -->
                            <div class="mt-2">
                                <div class="progress" style="height: 8px;">
                                    <div class="progress-bar bg-danger" style="width: 80%"></div>
                                </div>
                                <small class="text-danger fw-semibold">
                                    ĐANG DIỄN RA
                                </small>
                            </div>

                        </a>
                    </div>
                </php:forEach>

            </div>
        </div>

    </div>
</div>
