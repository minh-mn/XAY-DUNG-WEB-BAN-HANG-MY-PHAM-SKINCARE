<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="makeup-cotton my-5">
    <div class="container">
        <div class="row">

            <!-- ================= SIDEBAR ================= -->
            <aside class="col-lg-3">

                <!-- PRICE RANGE -->
                <div class="pro-cotton__range mb-4">
                    <input type="range" min="0" max="1000000" step="10000" value="0"
                           class="pro-cotton__range-ip w-100">
                    <div class="d-flex justify-content-between mt-2">
                        <span>0đ</span>
                        <span id="pro-range__value">0đ</span>
                    </div>
                </div>

                <!-- BRAND LIST -->
                <h4 class="pro-cotton__title">Danh mục thương hiệu</h4>
                <ul class="pro-menu__list mb-4">
                    <php:forEach items="${brandTopTens}" var="brandItem">
                        <li class="pro-menu__item">
                            <a href="brand?id=${brandItem.getId()}"
                               class="text-danger">
                                <i class="far fa-arrow-alt-circle-right"></i>
                                ${brandItem.getName()}
                            </a>
                        </li>
                    </php:forEach>
                </ul>

                <!-- TOP SELLING -->
                <h4 class="pro-cotton__title">SẢN PHẨM BÁN CHẠY</h4>
                <ul class="pro-selling">
                    <php:forEach items="${topfiveproducts}" var="topProduct">
                        <li class="d-flex mb-3">
                            <a href="product_detail?id=${topProduct.getId()}"
                               class="pro-selling__item me-2">
                                <img src="${topProduct.getFirstImage()}"
                                     style="width:70px;height:70px"
                                     alt="${topProduct.getTitle()}">
                            </a>
                            <div class="pro-selling__detail">
                                <a href="product_detail?id=${topProduct.getId()}">
                                    ${topProduct.getTitle()}
                                </a>
                                <span class="pro-selling__price text-danger">
                                    ${topProduct.getFormatPriceStandard()} ₫
                                </span>
                                <del class="d-block text-muted">
                                    ${topProduct.getFormatPriceDefault()} ₫
                                </del>
                            </div>
                        </li>
                    </php:forEach>
                </ul>

            </aside>

            <!-- ================= PRODUCT LIST ================= -->
            <main class="col-lg-9">

                <!-- SORT -->
                <div class="pro-cotton__sort d-flex align-items-center mb-3">
                    <span class="me-2">Sắp xếp theo:</span>
                    <select class="form-select w-auto">
                        <option selected>Sản phẩm nổi bật</option>
                        <option value="sold">Bán chạy nhất</option>
                        <option value="az">Tên: A-Z</option>
                        <option value="za">Tên: Z-A</option>
                        <option value="priceAsc">Giá tăng dần</option>
                        <option value="priceDesc">Giá giảm dần</option>
                        <option value="new">Mới nhất</option>
                        <option value="old">Cũ nhất</option>
                    </select>
                </div>

                <!-- BRAND NAME -->
                <h3 class="pro-cotton__theme mb-4 text-uppercase">
                    ${brand}
                </h3>

                <!-- PRODUCT GRID -->
                <div class="row">
                    <php:forEach items="${products}" var="product">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="pro-cotton__item h-100 shadow-sm">

                                <div class="pro-cotton__img position-relative">
                                    <a href="product_detail?id=${product.getId()}">
                                        <img src="${product.getFirstImage()}"
                                             class="img-fluid"
                                             alt="${product.getTitle()}">
                                    </a>
                                    <span class="pro-cotton__sale">
                                        -${product.getDiscount()}%
                                    </span>
                                </div>

                                <div class="pro-cotton__detail p-3">
                                    <h6>
                                        <a href="product_detail?id=${product.getId()}"
                                           class="pro-cotton__link">
                                            ${product.getTitle()}
                                        </a>
                                    </h6>

                                    <p class="pro-cotton__brand mb-1">
                                        ${product.getBrandName()}
                                    </p>

                                    <div class="pro-cotton__price">
                                        <span class="pro-cotton__price--curr text-danger fw-bold">
                                            ${product.getFormatPriceStandard()}
                                        </span>
                                        <div class="pro-cotton__quantity">
                                            <del>${product.getFormatPriceDefault()} ₫</del>
                                            <span class="d-block text-muted">
                                                Đã bán ${product.getSold()}
                                            </span>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </php:forEach>
                </div>

            </main>

        </div>
    </div>
</section>
