<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<section class="profile container my-5">

    <h2 class="text-primary mb-4">Thông tin cá nhân</h2>

    <form action="profile" method="post"
          class="row bg-white shadow-sm p-4 rounded">

        <!-- ================= LEFT: FORM ================= -->
        <div class="col-md-8">

            <h5 class="text-danger">Quản lý tài khoản</h5>
            <p class="text-success mb-4">
                Cập nhật thông tin cá nhân và bảo mật
            </p>

            <!-- USER ID -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">User ID</label>
                <div class="col-sm-8">
                    <input type="text"
                           class="form-control"
                           value="${userLogin.getId()}"
                           disabled>
                    <input type="hidden" name="id"
                           value="${userLogin.getId()}">
                </div>
            </div>

            <!-- USERNAME -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">Username</label>
                <div class="col-sm-8">
                    <input type="text"
                           class="form-control"
                           value="${userLogin.getUsername()}"
                           disabled>
                </div>
            </div>

            <!-- PASSWORD -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">Password</label>
                <div class="col-sm-8">
                    <input type="password"
                           name="password"
                           class="form-control"
                           placeholder="Nhập mật khẩu mới (để trống nếu không đổi)">
                </div>
            </div>

            <!-- EMAIL -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">Email</label>
                <div class="col-sm-8">
                    <input type="email"
                           name="email"
                           class="form-control"
                           value="${userLogin.getEmail()}">
                </div>
            </div>

            <!-- FULL NAME -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">Họ và tên</label>
                <div class="col-sm-8">
                    <input type="text"
                           name="fullname"
                           class="form-control"
                           value="${userLogin.getFullname()}">
                </div>
            </div>

            <!-- PHONE -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">Số điện thoại</label>
                <div class="col-sm-8">
                    <input type="text"
                           name="phone"
                           class="form-control"
                           value="${userLogin.getPhone()}">
                </div>
            </div>

            <!-- AVATAR LINK -->
            <div class="mb-3 row">
                <label class="col-sm-4 col-form-label">Avatar URL</label>
                <div class="col-sm-8">
                    <input type="text"
                           name="avatar"
                           class="form-control"
                           value="${userLogin.getAvatar()}">
                </div>
            </div>

            <!-- MESSAGE -->
            <php:if test="${mess != null}">
                <p class="text-danger mt-2">${mess}</p>
            </php:if>

            <!-- SUBMIT -->
            <div class="mt-4">
                <button type="submit"
                        class="btn btn-danger px-5">
                    Lưu thay đổi
                </button>
            </div>

        </div>

        <!-- ================= RIGHT: AVATAR ================= -->
        <div class="col-md-4 text-center">
            <p class="fw-bold">Ảnh đại diện</p>
            <img src="${userLogin.getAvatar()}"
                 alt="Avatar"
                 class="img-fluid rounded-circle border"
                 style="width:180px;height:180px;object-fit:cover">
        </div>

    </form>

</section>
