<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="userLogin" value="${sessionScope.userLogin}" />

<div class="header-first">
    <div class="container">

        <!-- ================= LEFT ================= -->
        <div class="header-first__contact">
            <ul class="contact-social">
                <li><a href="#" class="icon-social"><i class="fab fa-facebook-f"></i></a></li>
                <li><a href="#" class="icon-social"><i class="fab fa-instagram"></i></a></li>
                <li><a href="#" class="icon-social"><i class="fab fa-twitter"></i></a></li>
                <li><a href="#" class="icon-social"><i class="fab fa-google-plus-g"></i></a></li>
            </ul>

            <div class="contact-product">
                <div class="product-viewed">
                    <i class="icon-down fas fa-chevron-down"></i>
                    <span>Sản phẩm đã xem</span>
                </div>
            </div>
        </div>

        <!-- ================= RIGHT ================= -->
        <div class="header-second__contact">
            <ul class="contact-phone">
                <li class="contact-province">
                    <div class="contact-province__choose">
                        <span>Chọn miền</span>
                        <i class="contact-province__icon fas fa-sort-down"></i>
                    </div>
                </li>
                <li class="contact-hotline">
                    <span>Hotline: <span class="phone">0911 384 114</span></span>
                </li>
                <li class="contact-switchboard">
                    <span>Tổng đài tư vấn: <span class="phone">1900 7013</span></span>
                </li>
            </ul>

            <!-- ================= ACCOUNT ================= -->
            <div class="contact-account">

                <!-- ===== CHƯA ĐĂNG NHẬP ===== -->
                <c:if test="${userLogin == null}">
                    <div class="contact-account__create">
                        <span>Tài khoản</span>
                        <i class="icon-down fas fa-chevron-down"></i>
                    </div>

                    <div class="contact-account__log" style="z-index: 10">
                        <a href="login" class="contact-account__login">Đăng nhập</a>
                        <a href="signup" class="contact-account__signup">Đăng ký</a>
                    </div>
                </c:if>

                <!-- ===== ĐÃ ĐĂNG NHẬP ===== -->
                <c:if test="${userLogin != null}">
                    <div class="dropdown user-dropdown d-none d-lg-block">
                        <a class="nav-link" href="#" data-bs-toggle="dropdown">
                            <img class="img-xs rounded-circle"
                                 src="${userLogin.avatar}"
                                 style="width:30px;height:30px;border:1px solid red"
                                 alt="Avatar">
                        </a>

                        <div class="dropdown-menu dropdown-menu-end m-3 p-4">
                            <div class="dropdown-header text-center">
                                <img class="img-md rounded-circle"
                                     src="${userLogin.avatar}"
                                     style="width:60px;height:60px;border:1px solid red">
                                <p class="mb-1 mt-3 fw-semibold">
                                    ${userLogin.fullname}
                                </p>
                                <p class="text-muted mb-0">
                                    ${userLogin.email}
                                </p>
                            </div>

                            <!-- ADMIN -->
                            <c:if test="${userLogin.role == 1}">
                                <a href="admin/home" class="dropdown-item my-2">
                                    <i class="fas fa-cogs text-danger me-2"></i>
                                    Management Page
                                </a>
                            </c:if>

                            <a href="profile" class="dropdown-item my-2">
                                <i class="far fa-user text-primary me-2"></i>
                                My Profile
                            </a>

                            <a href="history" class="dropdown-item my-2">
                                <i class="far fa-envelope text-primary me-2"></i>
                                History
                            </a>

                            <a href="#" class="dropdown-item my-2">
                                <i class="fas fa-question-circle text-primary me-2"></i>
                                FAQ
                            </a>

                            <a href="signout" class="dropdown-item my-2">
                                <i class="fas fa-sign-out-alt text-warning me-2"></i>
                                Sign Out
                            </a>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</div>
