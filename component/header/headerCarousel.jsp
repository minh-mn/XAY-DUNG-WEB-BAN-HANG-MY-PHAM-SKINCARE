<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="slide-bar">
    <div class="container">
        <div class="row">
            <div class="col-12">

                <div id="mainCarousel" class="carousel slide" data-bs-ride="carousel">

                    <!-- INDICATORS -->
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#mainCarousel" data-bs-slide-to="0"
                                class="active" aria-current="true"></button>
                        <button type="button" data-bs-target="#mainCarousel" data-bs-slide-to="1"></button>
                        <button type="button" data-bs-target="#mainCarousel" data-bs-slide-to="2"></button>
                        <button type="button" data-bs-target="#mainCarousel" data-bs-slide-to="3"></button>
                        <button type="button" data-bs-target="#mainCarousel" data-bs-slide-to="4"></button>
                        <button type="button" data-bs-target="#mainCarousel" data-bs-slide-to="5"></button>
                    </div>

                    <!-- SLIDES -->
                    <div class="carousel-inner">

                        <div class="carousel-item active">
                            <img src="Page/img/img1.jpg" class="d-block w-100" alt="Slide 1">
                        </div>

                        <div class="carousel-item">
                            <img src="Page/img/img2.jpg" class="d-block w-100" alt="Slide 2">
                        </div>

                        <div class="carousel-item">
                            <img src="Page/img/img3.jpg" class="d-block w-100" alt="Slide 3">
                        </div>

                        <div class="carousel-item">
                            <img src="Page/img/img4.jpg" class="d-block w-100" alt="Slide 4">
                        </div>

                        <div class="carousel-item">
                            <img src="Page/img/img5.jpg" class="d-block w-100" alt="Slide 5">
                        </div>

                        <div class="carousel-item">
                            <img src="Page/img/img6.jpg" class="d-block w-100" alt="Slide 6">
                        </div>

                    </div>

                    <!-- CONTROLS -->
                    <button class="carousel-control-prev" type="button"
                            data-bs-target="#mainCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>

                    <button class="carousel-control-next" type="button"
                            data-bs-target="#mainCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon"></span>
                        <span class="visually-hidden">Next</span>
                    </button>

                </div>

            </div>
        </div>
    </div>
</div>
