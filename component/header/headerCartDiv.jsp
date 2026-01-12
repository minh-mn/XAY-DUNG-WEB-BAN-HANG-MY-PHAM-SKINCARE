<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<php:set var="cartItems" value="${sessionScope.cart.getCartItems()}" />
<div class="cart-items-header" style="right: -30%">
	<php:if test="${cartItems != null }">
		<div class="wrapper-cart">
			<h4 class="wrapper-cart-title">Sản phẩm trong giỏ hàng</h4>
			<php:forEach items="${cartItems }" var="cartItem">
				<a href="?viewproduct=2" class="cart-element"> <img
					src="${ cartItem.getProduct().getFirstImage()}" alt=""
					class="cart-element-image"><%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="cart" value="${sessionScope.cart}" />
<c:set var="cartItems" value="${cart != null ? cart.cartItems : null}" />

<div class="cart-items-header" style="right: -30%">

    <!-- ================= CÓ SẢN PHẨM ================= -->
    <c:if test="${cartItems != null && not empty cartItems}">
        <div class="wrapper-cart">
            <h4 class="wrapper-cart-title">Sản phẩm trong giỏ hàng</h4>

            <c:forEach items="${cartItems}" var="item">
                <a href="productDetail?id=${item.product.id}" class="cart-element">
                    <img src="${item.product.firstImage}"
                         alt="${item.product.title}"
                         class="cart-element-image">

                    <p class="cart-element-title">
                        ${item.product.title}
                    </p>

                    <p class="cart-element-price">
                        ${item.product.formatPriceStandard}đ
                        <span class="number_order">x${item.number}</span>
                    </p>
                </a>
            </c:forEach>

            <div class="wrapper_cart_link">
                <a href="cart" class="cart_link_element">
                    Đến giỏ hàng
                </a>
            </div>
        </div>
    </c:if>

    <!-- ================= GIỎ HÀNG TRỐNG ================= -->
    <c:if test="${cartItems == null || empty cartItems}">
        <div class="cart-items-empty">
            <img
                src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/cart/9bdd8040b334d31946f49e36beaf32db.png"
                alt="Empty cart">
            <p>Giỏ hàng trống</p>
        </div>
    </c:if>

</div>
					
					<p class="cart-element-title">${ cartItem.getProduct().getTitle()}</p>
					<p class="cart-element-price">
						${cartItem.getProduct().getFormatPriceStandard()}đ <span class="number_order">x${ cartItem.getNumber()}</span>
					</p>
				</a>
			</php:forEach>
			<div class="wrapper_cart_link">
				<a style="poiter: cursor" href="cart" class="cart_link_element">
					Đến Giỏ Hàng
				</a>
			</div>
		</div>
	</php:if>

	<php:if test="${cartItems.size() == 0 || cartItems == null }">
		<div class="cart-items-empty">
			<img
				src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/cart/9bdd8040b334d31946f49e36beaf32db.png"
				alt="#">
			<p>Nothing here</p>
		</div>
	</php:if>
</div>