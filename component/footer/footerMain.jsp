<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer>

    <!-- ================= CONTACT ================= -->
    <div class="footer-contact">
        <div class="container">
            <div class="row">

                <!-- Hotline -->
                <div class="col-lg-3">
                    <div class="footer-hotline">
                        <i class="fas fa-phone-alt footer-hotline__icon"></i>
                        <p>Hotline: 1900 636 510 (9:00 - 21:30)</p>

                        <!-- Hiển thị tên nếu đã đăng nhập -->
                        <c:if test="${not empty sessionScope.loginSession}">
                            <p>
                                Xin chào,
                                <strong>${sessionScope.loginSession.fullname}</strong>
                            </p>
                        </c:if>
                    </div>
                </div>

                <!-- Support -->
                <div class="col-lg-3">
                    <div class="footer-user">
                        <i class="fas fa-user footer-user__icon"></i>
                        <p>Hỗ trợ đơn hàng: thegioiskinfood@gmail.com</p>
                    </div>
                </div>

                <!-- Mail -->
                <div class="col-lg-3">
                    <div class="footer-mail">
                        <i class="far fa-envelope footer-mail__icon"></i>
                        <p>Hợp tác: sales@thegioiskinfood.com</p>
                    </div>
                </div>

                <!-- Address -->
                <div class="col-lg-3">
                    <div class="footer-address">
                        <i class="fas fa-map-marker-alt footer-address__icon"></i>
                        <p>
                            365 Lê Văn Sỹ, P.12, Q.3<br>
                            100 Hoàng Hoa Thám, P.12, Q.Tân Bình
                        </p>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- ================= COMPANY INFO ================= -->
    <div class="footer-info">
        <div class="container">
            <div class="row">

                <!-- Company -->
                <div class="col-lg-4">
                    <ul class="company">
                        <li>CÔNG TY TNHH MTV TM DV BLUE OCEAN</li>
                        <li>CNĐKDN: 0316037655</li>
                        <li>TP.HCM – Cấp ngày 25/11/2019</li>
                    </ul>
                </div>

                <!-- Policy -->
                <div class="col-lg-4">
                    <ul class="policy">
                        <li><a href="#">Đại lý chính hãng</a></li>
                        <li><a href="#">Thanh toán</a></li>
                        <li><a href="#">Đổi trả – Hoàn tiền</a></li>
                        <li><a href="#">Giao hàng</a></li>
                        <li><a href="#">Bảo mật</a></li>
                        <li><a href="#">Điều khoản</a></li>
                        <li><a href="#">Liên hệ</a></li>
                    </ul>
                </div>

                <!-- Social -->
                <div class="col-lg-4">
                    <div class="social">
                        <h5>Kết nối với chúng tôi</h5>

                        <div class="social-nw__link">
                            <img src="Page/img/facebook-icon.webp" alt="Facebook">
                            <img src="Page/img/instargram.webp" alt="Instagram">
                            <img src="Page/img/youtube-icon-new.webp" alt="YouTube">
                        </div>

                        <div class="social-payment">
                            <img src="Page/img/image_pay_ft_momo_file.webp" alt="Momo">
                            <img src="Page/img/image_pay_ft_vnpay_file.webp" alt="VNPay">
                            <img src="Page/img/image_pay_ft_air_file.webp" alt="ShopeePay">
                        </div>

                        <div class="social-recognized">
                            <img src="Page/img/logosalenoti.webp" alt="Bộ công thương">
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</footer>
