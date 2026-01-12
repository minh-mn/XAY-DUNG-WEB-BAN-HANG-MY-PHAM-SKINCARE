<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="header-second">
    <div class="container">
        <div class="row align-items-center">

            <!-- ================= SEARCH ================= -->
            <div class="col-lg-4">
                <div class="input-group header-search">
                    <input
                        type="text"
                        class="form-control"
                        id="search-product"
                        placeholder="Tìm kiếm..."
                    >

                    <select class="form-select">
                        <option selected>Sản phẩm</option>
                        <option value="news">Tin tức</option>
                    </select>

                    <button class="btn btn-outline-secondary" type="button">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
            </div>

            <!-- ================= LOGO ================= -->
            <div class="col-lg-4 text-center">
                <div class="header-logo">
                    <a href="home">
                        <img
                            src="Page/img/logo.png"
                            alt="Logo"
                            width="320"
                            height="80"
                        >
                    </a>
                </div>
            </div>

            <!-- ================= CART ================= -->
            <div class="col-lg-4">
                <div class="header-cart">
                    <ul class="heaeder-cart__list">

                        <!-- FAVORITE -->
                        <li class="header-cart__item">
                            <a href="#" class="header-cart__liked" title="Sản phẩm yêu thích">
                                <i class="far fa-heart"></i>
                                Yêu thích
                                <span class="number-liked">...</span>
                            </a>
                        </li>

                        <!-- CART -->
                        <li class="header-cart__item cart-header-right position-relative">
                            <a
                                href="cart"
                                class="header-cart__added text-danger"
                                title="Giỏ hàng"
                            >
                                <i class="fas fa-shopping-cart"></i>
                                Giỏ hàng
                                <span class="number-added">
                                    <c:choose>
                                        <c:when test="${sessionScope.cart != null}">
                                            ${sessionScope.cart.cartItems.size()}
                                        </c:when>
                                        <c:otherwise>0</c:otherwise>
                                    </c:choose>
                                </span>
                            </a>

                            <%@ include file="headerCartDiv.jsp" %>
                        </li>

                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>
