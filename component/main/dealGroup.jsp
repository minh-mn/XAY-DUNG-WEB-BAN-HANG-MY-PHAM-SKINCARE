<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<div class="makeup-cotton my-5">
    <div class="container">
        <div class="row">

            <!-- ================= SIDEBAR ================= -->
            <aside class="col-lg-3">

                <!-- PRICE RANGE -->
                <div class="mb-4">
                    <label class="fw-bold mb-2 d-block">Khoảng giá</label>
                    <input type="range"
                           class="form-range"
                           min="0"
                           max="1000000"
                           step="10000"
                           value="0"
                           oninput="document.getElementById('priceValue').innerText = this.value + 'đ'">
                    <small class="text-muted">0đ - <span id="priceValue">0đ</span></small>
                </div>

                <!-- EVENTS -->
                <h5 class="fw-bold mb-3">Sự kiện đang diễn ra</h5>
                <ul class="list-unstyled mb-4">
                    <php:forEach items="${events}" var="e">
                        <li class="mb-2">
                            <a href="event?id=${e.getId()}"
                               class="text-danger text-decoration-none">
                                <i class="far fa-arrow-alt-circle-right me-1"></i>
                                ${e.getName()}
                            </a>
                        </li>
                    </php:forEach>
                </ul>

                <!-- BEST SELLERS -->
                <h5 class="fw-bold mb-3">Sản phẩm bán chạy</h5>
                <ul class="list-unstyled">
                    <php:forEach items="${products}" var="top" begin="0" end="4">
                        <li class="d-flex mb-3">
                            <img src="${top.getFirstImage()}"
                                 style="width:70px;height:70px;object-fit:cover"
                                 class="me-2 rounded">

                            <div>
                                <a href="product_detail?id=${top.getId()}"
                                   class="d-block text-decoration-none text-dark">
                                    ${top.getTitle()}
                                </a>
                                <span class="text-danger fw-bold">
                                    ${top.getFormatPriceStandard()}₫
                                </span>
                                <del class="text-muted small ms-1">
                                    ${top.getFormatPriceDefault()}₫
                                </del>
                            </div>
                        </li>
                    </php:forEach>
                </ul>
            </aside>

            <!-- ================= MAIN CONTENT ================= -->
            <section class="col-lg-9">

                <!-- SORT -->
                <div class="d-flex align-items-center mb-4">
                    <span class="me-2 fw-semibold">Sắp xếp theo:</span>
                    <select class="form-select w-auto">
                        <option selected>Sản phẩm nổi bật</option>
                        <option>Bán chạy nhất</option>
                        <option>Tên A - Z</option>
                        <option>Tên Z - A</option>
                        <option>Giá tăng dần</option>
                        <option>Giá giảm dần</option>
                        <option>Mới nhất</option>
                        <option>Cũ nhất</option>
                    </select>
                </div>

                <!-- TITLE -->
                <h3 class="fw-bold mb-4 text-danger">
                    ${event.getName()}
                </h3>

                <!-- PRODUCT GRID -->
                <div class="row g-4">
                    <php:forEach items="${products}" var="product">
                        <div class="col-md-6 col-lg-4">

                            <div class="border rounded h-100 p-2 position-relative">

                                <!-- IMAGE -->
                                <a href="product_detail?id=${product.getId()}">
                                    <img src="${product.getFirstImage()}"
                                         class="img-fluid w-100"
                                         style="height:220px;object-fit:cover">
                                </a>

                                <!-- DISCOUNT -->
                                <span class="badge bg-danger position-absolute top-0 start-0 m-2">
                                    -${product.getDiscount()}%
                                </span>

                                <!-- INFO -->
                                <div class="mt-3">
                                    <a href="product_detail?id=${product.getId()}"
                                       class="text-decoration-none text-dark fw-semibold">
                                        ${product.getTitle()}
                                    </a>

                                    <p class="text-muted small mb-1">
                                        ${product.getBrandName()}
                                    </p>

                                    <div>
                                        <span class="text-danger fw-bold">
                                            ${product.getFormatPriceStandard()}
                                        </span>
                                        <del class="text-muted ms-2">
                                            ${product.getFormatPriceDefault()}₫
                                        </del>
                                    </div>

                                    <small class="text-muted">
                                        Đã bán ${product.getSold()}
                                    </small>
                                </div>

                            </div>

                        </div>
                    </php:forEach>
                </div>

            </section>
        </div>
    </div>
</div>
