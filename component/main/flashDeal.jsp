<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="flash-deal my-5">
    <div class="container">

        <!-- ===== HEADER ===== -->
        <div class="d-flex align-items-center justify-content-between mb-3">

            <!-- TITLE -->
            <div class="d-flex align-items-center">
                <img src="https://app2.jeoway.net/61/hotdeal/images/icon_flashdeal_2.png"
                     alt="Flash Deal"
                     style="height:40px">
            </div>

            <!-- COUNTDOWN -->
            <div class="d-flex align-items-center text-danger fw-bold">
                <span id="counth">00</span>
                <span class="mx-1">:</span>
                <span id="countm">00</span>
                <span class="mx-1">:</span>
                <span id="counts">00</span>
            </div>

            <!-- VIEW ALL -->
            <a href="event?id=8" target="_blank" class="text-danger fw-semibold">
                XEM TẤT CẢ DEAL →
            </a>
        </div>

        <!-- ===== BODY ===== -->
        <div class="carousel"
             data-flickity='{
                 "cellAlign": "left",
                 "contain": true,
                 "wrapAround": true,
                 "pageDots": false,
                 "autoPlay": 3000
             }'>

            <php:forEach items="${flashdeal1s}" var="product">

                <div class="carousel-cell me-3" style="width: 200px;">

                    <a href="product_detail?id=${product.getId()}"
                       class="text-decoration-none text-dark">

                        <div class="border rounded p-2 position-relative h-100">

                            <!-- IMAGE -->
                            <img src="${product.getFirstImage()}"
                                 class="img-fluid w-100"
                                 style="height:170px;object-fit:cover">

                            <!-- DISCOUNT -->
                            <span class="badge bg-danger position-absolute top-0 start-0 m-2">
                                -${product.getDiscount()}%
                            </span>

                            <!-- NAME -->
                            <p class="mt-2 mb-1 fw-semibold small text-truncate">
                                ${product.getTitle()}
                            </p>

                            <!-- PRICE -->
                            <div>
                                <span class="text-danger fw-bold">
                                    ${product.getFormatPriceStandard()}đ
                                </span>
                                <del class="text-muted small ms-1">
                                    ${product.getFormatPriceDefault()}
                                </del>
                            </div>

                            <!-- STATUS BAR -->
                            <div class="mt-2">
                                <div class="progress" style="height: 6px;">
                                    <div class="progress-bar bg-danger"
                                         style="width:80%"></div>
                                </div>
                                <small class="text-danger fw-bold d-block mt-1">
                                    ĐANG DIỄN RA
                                </small>
                            </div>

                        </div>

                    </a>

                </div>

            </php:forEach>

        </div>

    </div>
</section>
