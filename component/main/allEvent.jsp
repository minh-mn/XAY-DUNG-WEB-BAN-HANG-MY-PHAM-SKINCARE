<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="d-flex justify-content-around flex-wrap mt-5 mb-5">

    <c:if test="${not empty events}">
        <c:forEach items="${events}" var="event">
            <a
                href="event?id=${event.id}"
                class="text-center text-danger mx-3"
                style="text-decoration: none;"
            >
                <img
                    src="${event.icon}"
                    alt="${event.name}"
                    style="max-width: 80px;"
                >
                <p class="text-danger mt-2">
                    ${event.name}
                </p>
            </a>
        </c:forEach>
    </c:if>

    <c:if test="${empty events}">
        <p class="text-muted text-center">
            Không có sự kiện nào
        </p>
    </c:if>

</div>
