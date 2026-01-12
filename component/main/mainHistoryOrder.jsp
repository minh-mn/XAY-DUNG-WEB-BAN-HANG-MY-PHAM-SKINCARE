<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="order-history my-5">
    <div class="container">

        <!-- ===== HEADER ===== -->
        <div class="d-flex align-items-center mb-4">
            <a href="home">
                <img src="Page/img/logo.png" style="height:70px" alt="Logo">
            </a>
            <h3 class="ms-3 text-success fw-bold">Lịch sử mua hàng</h3>
        </div>

        <!-- ===== EMPTY ===== -->
        <php:if test="${historyTransactions == null || historyTransactions.isEmpty()}">
            <h4 class="text-danger text-center mt-5">
                Bạn chưa có đơn hàng nào
            </h4>
        </php:if>

        <!-- ===== ORDER LIST ===== -->
        <php:forEach items="${historyTransactions}" var="order">

            <div class="border rounded mb-4 p-3 shadow-sm">

                <!-- ORDER INFO -->
                <div class="d-flex justify-content-between mb-2">
                    <div>
                        <strong>Mã đơn hàng:</strong> ${order.getId()} <br>
                        <strong>Ngày đặt:</strong> ${order.getTimeOrderString()}
                    </div>
                    <div class="text-danger fw-bold fs-5">
                        ${order.getFormatTotal()}₫
                    </div>
                </div>

                <!-- TABLE HEADER -->
                <div class="row fw-bold text-center border-bottom py-2">
                    <div class="col-md-2">Ảnh</div>
                    <div class="col-md-4">Tên sản phẩm</div>
                    <div class="col-md-2">Giá</div>
                    <div class="col-md-2">Số lượng</div>
                    <div class="col-md-2">Thành tiền</div>
                </div>

                <!-- PRODUCT LIST -->
                <php:forEach items="${historySingleTransactions}" var="item">
                    <php:if test="${item.getOrderTotal().equals(order.getId())}">
                        <div class="row text-center align-items-center py-2 border-bottom">
                            <div class="col-md-2">
                                <img src="${item.getProductById().getFirstImage()}"
                                     style="width:60px" alt="">
                            </div>
                            <div class="col-md-4 text-start">
                                ${item.getProductById().getTitle()}
                            </div>
                            <div class="col-md-2">
                                ${item.getProductById().getFormatPriceStandard()}₫
                            </div>
                            <div class="col-md-2">
                                ${item.getNumber()}
                            </div>
                            <div class="col-md-2 fw-bold">
                                ${item.getFormatPrice()}₫
                            </div>
                        </div>
                    </php:if>
                </php:forEach>

                <!-- SHIPPING & PHONE -->
                <div class="row mt-3 text-center">
                    <div class="col-md-4 text-danger">
                        <strong>SĐT:</strong> ${order.getPhone()}
                    </div>
                    <div class="col-md-4">
                        <strong>Vận chuyển:</strong> ${order.getTransportName()}
                    </div>
                    <div class="col-md-4 text-primary fw-bold">
                        ${order.getTransportFee()}₫
                    </div>
                </div>

                <!-- DISCOUNT -->
                <php:if test="${order.getDiscount() != 0}">
                    <div class="row mt-2 justify-content-end">
                        <div class="col-md-4 text-danger text-end">
                            <del>${order.getFormatTotalAfterDiscount()}₫</del>
                        </div>
                    </div>
                </php:if>

                <!-- STATUS -->
                <div class="row mt-3 align-items-center justify-content-end">

                    <div class="col-md-3 text-end">
                        <form action="recieveOrder" method="post">

                            <php:if test="${order.getStatus() == 0}">
                                <button disabled class="btn btn-warning w-100">
                                    Chờ xác nhận
                                </button>
                            </php:if>

                            <php:if test="${order.getStatus() == 1}">
                                <button disabled class="btn btn-info w-100">
                                    Đang giao hàng
                                </button>
                            </php:if>

                            <php:if test="${order.getStatus() == 2}">
                                <input type="hidden" name="recieve" value="${order.getId()}">
                                <button class="btn btn-success w-100">
                                    Đã nhận hàng
                                </button>
                            </php:if>

                            <php:if test="${order.getStatus() == -1}">
                                <button disabled class="btn btn-secondary w-100">
                                    Hoàn tất
                                </button>
                            </php:if>

                        </form>
                    </div>

                </div>

            </div>

        </php:forEach>

    </div>
</section>
