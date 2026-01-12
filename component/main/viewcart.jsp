<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="cart">
    <div class="container">

        <!-- ================= BREADCRUMB ================= -->
        <nav aria-label="breadcrumb" class="mb-4">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="home" class="text-danger">Trang chủ</a>
                </li>
                <li class="breadcrumb-item active">Giỏ hàng</li>
            </ol>
        </nav>

        <!-- ================= HEADER TABLE ================= -->
        <div class="cart-header row text-center fw-bold mb-3">
            <div class="col-lg-4">Sản phẩm</div>
            <div class="col-lg-2">Danh mục</div>
            <div class="col-lg-2">Đơn giá</div>
            <div class="col-lg-2">Số lượng</div>
            <div class="col-lg-1">Tổng</div>
            <div class="col-lg-1">Xóa</div>
        </div>

        <php:set var="cartItems" value="${sessionScope.cart.getCartItems()}" />

        <!-- ================= EMPTY CART ================= -->
        <php:if test="${cartItems == null || cartItems.size() == 0}">
            <h4 class="text-center text-danger mt-5">Giỏ hàng trống</h4>
        </php:if>

        <!-- ================= CART ITEMS ================= -->
        <php:forEach items="${cartItems}" var="item">
            <div class="cart-item row align-items-center mb-4">

                <!-- PRODUCT -->
                <div class="col-lg-4">
                    <a href="product_detail?id=${item.getProduct().getId()}"
                       class="d-flex align-items-center text-decoration-none">
                        <img src="${item.getProduct().getFirstImage()}"
                             style="width:80px;height:80px"
                             alt="">
                        <p class="ms-3 text-dark mb-0">
                            ${item.getProduct().getTitle()}
                        </p>
                    </a>
                </div>

                <!-- CATEGORY -->
                <div class="col-lg-2 text-center">
                    ${item.getProduct().getCategoryName()}
                </div>

                <!-- PRICE -->
                <div class="col-lg-2 text-center">
                    <span class="text-danger fw-bold">
                        ${item.getProduct().getFormatPriceStandard()}đ
                    </span>
                    <br>
                    <del class="text-muted">
                        ${item.getProduct().getFormatPriceDefault()}đ
                    </del>
                </div>

                <!-- QUANTITY -->
                <div class="col-lg-2 text-center">
                    <div class="d-flex justify-content-center">
                        <button type="button"
                                class="btn btn-success rounded-0"
                                onclick="
                                    sub(${item.getProduct().getId()}, ${item.getProduct().getPrice()});
                                    subAjax(${item.getProduct().getId()}, ${item.getNumber()})
                                ">
                            -
                        </button>

                        <input type="text"
                               class="form-control text-center mx-1"
                               style="width:60px"
                               id="${item.getProduct().getId()}"
                               value="${item.getNumber()}">

                        <button type="button"
                                class="btn btn-success rounded-0"
                                onclick="
                                    add(${item.getProduct().getId()}, ${item.getProduct().getPrice()});
                                    addAjax(${item.getProduct().getId()}, ${item.getNumber()})
                                ">
                            +
                        </button>
                    </div>
                </div>

                <!-- TOTAL -->
                <div class="col-lg-1 text-center">
                    <input class="form-control cart_main_element_title_total"
                           id="totalPrice${item.getProduct().getId()}"
                           value="${item.getTotalSingle()}"
                           readonly>
                </div>

                <!-- DELETE -->
                <div class="col-lg-1 text-center">
                    <a href="removeCartItem?id=${item.getProduct().getId()}"
                       class="btn btn-danger btn-sm">
                        Xóa
                    </a>
                </div>
            </div>
        </php:forEach>

        <!-- ================= TOTAL & ORDER ================= -->
        <div class="row justify-content-end mt-4">
            <form action="preview"
                  method="POST"
                  class="col-lg-6 d-flex align-items-center justify-content-end">

                <!-- hidden quantity -->
                <php:forEach items="${cartItems}" var="item">
                    <input type="hidden"
                           id="s${item.getProduct().getId()}"
                           name="${item.getProduct().getId()}"
                           value="${item.getNumber()}">
                </php:forEach>

                <input type="hidden"
                       id="total_price_hidden"
                       name="total_price"
                       value="${sessionScope.cart.getTotal()}">

                <h5 class="me-3 mb-0">Tổng tiền:</h5>

                <input type="text"
                       class="form-control text-center text-danger fw-bold me-3"
                       style="width:200px"
                       id="total_price"
                       value="${sessionScope.cart.getTotal()}"
                       readonly>

                <button type="submit"
                        class="btn btn-success">
                    Đặt hàng
                </button>
            </form>
        </div>

    </div>
</section>
