<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="header-third">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">

                <div class="heaeder-nav">

                    <!-- ================= LEFT MENU ================= -->
                    <ul class="header-nav__menu">

                        <!-- ICON MENU -->
                        <li class="header-nav__parent">
                            <a href="#" class="header-nav__link">
                                <i class="fas fa-bars"></i>
                            </a>
                        </li>

                        <!-- COLLECTION + CATEGORY -->
                        <c:forEach items="${collections}" var="collection">
                            <li class="header-nav__child">
                                <a href="#" class="child-link">
                                    ${collection.name}
                                    <i class="fas fa-chevron-down"></i>
                                </a>

                                <div class="menu-makeup shadow-lg bg-white rounded">
                                    <div class="row">

                                        <c:forEach items="${categories}" var="category">
                                            <c:if test="${category.collection == collection.id}">
                                                <div class="${collection.id == 7 ? 'col-lg-6' : 'col-lg-3'}">
                                                    <ul class="list-makeup">
                                                        <li>
                                                            <a href="category?id=${category.id}">
                                                                ${category.name}
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                    </div>
                                </div>
                            </li>
                        </c:forEach>

                        <!-- BRAND -->
                        <li class="header-nav__child">
                            <a href="#" class="child-link">
                                Thương hiệu <i class="fas fa-chevron-down"></i>
                            </a>

                            <div class="menu-body shadow-lg bg-white rounded">
                                <div class="row">
                                    <c:forEach items="${brands}" var="brand">
                                        <div class="col-lg-3">
                                            <ul class="list-makeup">
                                                <li>
                                                    <a href="brand?id=${brand.id}">
                                                        ${brand.name}
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </li>

                        <!-- EVENT -->
                        <li class="header-nav__child">
                            <a href="#" class="child-link">
                                Khuyến mãi <i class="fas fa-chevron-down"></i>
                            </a>

                            <div class="menu-body shadow-lg bg-white rounded">
                                <div class="row">
                                    <c:forEach items="${events}" var="event">
                                        <div class="col-lg-3">
                                            <ul class="list-makeup">
                                                <li>
                                                    <a href="event?id=${event.id}">
                                                        ${event.name}
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </li>
                    </ul>

                    <!-- ================= RIGHT MENU ================= -->
                    <ul class="header-search-and-cart">

                        <!-- SEARCH -->
                        <li class="header-nav__parent">
                            <a href="#" class="header-search__icon">
                                <i class="fas fa-search"></i>
                            </a>
                        </li>

                        <!-- CART -->
                        <li class="cart-header-right position-relative">
                            <a href="cart" class="header-cart__icon">
                                <i class="fas fa-shopping-cart"></i>
                            </a>

                            <%@ include file="headerCartDiv.jsp" %>
                        </li>

                    </ul>

                </div>

            </div>
        </div>
    </div>
</div>
