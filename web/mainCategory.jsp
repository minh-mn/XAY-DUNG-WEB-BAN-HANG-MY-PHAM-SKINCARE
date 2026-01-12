<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="makeup-cotton">
    <div class="container">
        <div class="row">

            <!-- SIDEBAR -->
            <div class="col-lg-3">

                <!-- RANGE PRICE -->
                <div class="pro-cotton__range">
                    <input type="range" min="0" max="1000000" step="1000" value="0"
                           class="pro-cotton__range-ip">
                    <span>0đ - </span>
                    <div id="pro-range__value">0đ</div>
                </div>

                <!-- CATEGORY -->
                <h4 class="pro-cotton__title">Danh mục sản phẩm</h4>
                <ul class="pro-menu__list">
                    <li class="pro-menu__item">
                        <a href="${pageContext.request.contextPath}/category?type=skincare">
                            <i class="far fa-arrow-alt-circle-right"></i> Chăm sóc da
                        </a>
                    </li>
                    <li class="pro-menu__item">
                        <a href="${pageContext.request.contextPath}/category?type=makeup">
                            <i class="far fa-arrow-alt-circle-right"></i> Trang điểm
                        </a>
                    </li>
                    <li class="pro-menu__item">
                        <a href="${pageContext.request.contextPath}/category?type=body">
                            <i class="far fa-arrow-alt-circle-right"></i> HAND - BODY - HAIR
                        </a>
                    </li>
                </ul>

                <!-- BEST SELL -->
                <h4 class="pro-cotton__title">SẢN PHẨM BÁN CHẠY</h4>
                <ul class="pro-selling">
                    <li>
                        <img src="${pageContext.request.contextPath}/Page/img/b1p1.png"
                             width="70" height="70">
                        <div class="pro-selling__detail">
                            <a href="#">Bông Tẩy Trang Ipek Cotton</a>
                            <span class="pro-selling__price">25,000₫</span>
                            <del>220,000₫</del>
                            <span class="pro-selling__sale">SALE</span>
                        </div>
                    </li>
                </ul>
            </div>

            <!-- PRODUCT LIST -->
            <div class="col-lg-9">

                <!-- SORT -->
                <div class="pro-cotton__sort">
                    <span>Sắp xếp theo:</span>
                    <select class="form-select">
                        <option selected>Nổi bật</option>
                        <option>Giá tăng dần</option>
                        <option>Giá giảm dần</option>
                        <option>Mới nhất</option>
                    </select>
                </div>

                <h3 class="pro-cotton__theme">BÔNG TẨY TRANG</h3>

                <div class="row">

                    <!-- PRODUCT ITEM -->
                    <div class="col-lg-4">
                        <div class="pro-cotton__item">
                            <div class="pro-cotton__img">
                                <img src="${pageContext.request.contextPath}/Page/img/b2p1.png">
                                <span class="pro-cotton__sale">-55%</span>
                            </div>

                            <div class="pro-cotton__detail">
                                <h6>Bông Tẩy Trang Ipek Cotton Pads</h6>
                                <p class="pro-cotton__brand">MERZY</p>

                                <div class="pro-cotton__price">
                                    <span>128,000₫ - 148,000₫</span>
                                    <p>299,000₫</p>
                                    <span>Đã bán 10,7k</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- copy thêm item -->

                </div>
            </div>

        </div>
    </div>
</div>
