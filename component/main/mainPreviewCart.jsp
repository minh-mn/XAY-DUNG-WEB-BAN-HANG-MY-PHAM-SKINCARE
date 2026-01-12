<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<main class="container my-5">

    <!-- ===== TITLE ===== -->
    <h3 class="text-center text-success mb-4 fw-bold">
        Xác nhận đơn mua của bạn
    </h3>

    <!-- ===== CART TABLE ===== -->
    <div class="card mb-4">
        <div class="card-body">

            <div class="row fw-bold text-center border-bottom pb-2">
                <div class="col-md-1">Ảnh</div>
                <div class="col-md-5">Tên sản phẩm</div>
                <div class="col-md-2">Số lượng</div>
                <div class="col-md-2">Đơn giá</div>
                <div class="col-md-2">Thành tiền</div>
            </div>

            <php:set var="cartItems" value="${sessionScope.cart.getCartItems()}" />

            <php:forEach items="${cartItems}" var="item">
                <div class="row align-items-center text-center border-bottom py-2">
                    <div class="col-md-1">
                        <img src="${item.getProduct().getFirstImage()}"
                             style="width:60px" alt="">
                    </div>
                    <div class="col-md-5 text-start">
                        ${item.getProduct().getTitle()}
                    </div>
                    <div class="col-md-2">
                        ${item.getNumber()}
                    </div>
                    <div class="col-md-2">
                        ${item.getProduct().getFormatPriceStandard()}₫
                    </div>
                    <div class="col-md-2 fw-bold text-danger">
                        ${item.getTotalSingleFormat()}₫
                    </div>
                </div>
            </php:forEach>

        </div>
    </div>

    <!-- ===== FORM CONFIRM ===== -->
    <form action="confirmOrder" method="POST" class="row g-4">

        <!-- LEFT -->
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">

                    <h5 class="mb-3 fw-bold">Thông tin người nhận</h5>

                    <div class="mb-3">
                        <label class="form-label">Họ tên</label>
                        <input type="text" class="form-control"
                               name="orderName"
                               value="${userLogin.getFullname()}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ</label>
                        <input type="text" class="form-control"
                               name="orderAddress"
                               value="${userLogin.getDefaultAddress()}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" class="form-control"
                               name="orderPhone"
                               id="phoneNumberCheck"
                               value="${userLogin.getPhone()}"
                               onchange="checkPhone()">
                        <small id="phoneNumberCheckMess" class="text-danger"></small>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="text" class="form-control"
                               value="${userLogin.getEmail()}" disabled>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Đơn vị vận chuyển</label>
                        <select name="transport"
                                class="form-select"
                                onchange="transferRequest(this.value)">
                            <php:forEach items="${transports}" var="t">
                                <option value="${t.getId()}">
                                    ${t.getTransportNameById()} - ${t.getTransportFeeById()}
                                </option>
                            </php:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mã giảm giá</label>
                        <div class="input-group">
                            <input type="text" id="magiamgia" class="form-control"
                                   placeholder="Nhập mã">
                            <button type="button"
                                    class="btn btn-danger"
                                    onclick="discountCoupon()">Áp mã</button>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ghi chú</label>
                        <textarea name="orderNote"
                                  class="form-control"
                                  rows="4"
                                  placeholder="Nhập ghi chú..."></textarea>
                    </div>

                    <php:if test="${message != null && !message.isEmpty()}">
                        <p class="text-danger fw-bold">${message}</p>
                    </php:if>

                </div>
            </div>
        </div>

        <!-- RIGHT -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-end">

                    <p class="mb-1">Tổng thanh toán (VND)</p>
                    <del id="total_after_del" class="text-danger"></del>

                    <input disabled
                           id="totalFormat_cart"
                           class="form-control text-end fw-bold fs-5 mb-3"
                           value="${totalFormat}">

                    <input type="submit"
                           class="btn btn-success w-100"
                           value="Xác nhận đặt hàng">
                </div>
            </div>
        </div>

        <!-- HIDDEN -->
        <input type="hidden" name="hidden_transport" id="hidden_transport"
               value="${transports.get(0).getId()}">
        <input type="hidden" name="hidden_total" id="hidden_total"
               value="${totalOrder}">
        <input type="hidden" name="oldtotal" value="${totalOrder}">
        <input type="hidden" name="hidden_coupon" id="hidden_coupon">
        <input type="hidden" name="userLogin" value="${userLoginId}">

    </form>

</main>
