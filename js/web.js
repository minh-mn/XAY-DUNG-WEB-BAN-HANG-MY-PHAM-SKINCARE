/* =====================================================
   COUNTDOWN (SAFE)
===================================================== */
(function () {
    const daysEl = document.getElementById("countdown-days");
    const hoursEl = document.getElementById("countdown-hours");
    const minutesEl = document.getElementById("countdown-minutes");
    const secondsEl = document.getElementById("countdown-seconds");

    if (!daysEl || !hoursEl || !minutesEl || !secondsEl) return;

    const endTime = new Date("Dec 31, 2025 23:59:59").getTime();

    setInterval(() => {
        const now = new Date().getTime();
        const distance = endTime - now;

        if (distance <= 0) {
            daysEl.innerHTML =
                hoursEl.innerHTML =
                minutesEl.innerHTML =
                secondsEl.innerHTML = "EXPIRED";
            return;
        }

        daysEl.innerHTML = Math.floor(distance / (1000 * 60 * 60 * 24));
        hoursEl.innerHTML = Math.floor((distance / (1000 * 60 * 60)) % 24);
        minutesEl.innerHTML = Math.floor((distance / (1000 * 60)) % 60);
        secondsEl.innerHTML = Math.floor((distance / 1000) % 60);
    }, 1000);
})();

/* =====================================================
   FORM VALIDATOR
===================================================== */
function Validator(options) {
    const form = document.querySelector(options.form);
    if (!form) return;

    const selectorRules = {};

    function validate(input, rule) {
        const errorEl = input.parentElement.querySelector(options.errorSelector);
        const rules = selectorRules[rule.selector];
        let errorMsg;

        for (let test of rules) {
            errorMsg = test(input.value);
            if (errorMsg) break;
        }

        if (errorMsg) {
            errorEl.innerText = errorMsg;
            input.parentElement.classList.add("invalid");
        } else {
            errorEl.innerText = "";
            input.parentElement.classList.remove("invalid");
        }
        return !errorMsg;
    }

    options.rules.forEach(rule => {
        selectorRules[rule.selector] = selectorRules[rule.selector] || [];
        selectorRules[rule.selector].push(rule.test);

        const input = form.querySelector(rule.selector);
        if (!input) return;

        input.onblur = () => validate(input, rule);
        input.oninput = () => {
            const errorEl = input.parentElement.querySelector(options.errorSelector);
            errorEl.innerText = "";
            input.parentElement.classList.remove("invalid");
        };
    });

    form.onsubmit = function (e) {
        let isValid = true;
        options.rules.forEach(rule => {
            const input = form.querySelector(rule.selector);
            if (!validate(input, rule)) isValid = false;
        });

        if (!isValid) e.preventDefault();
    };
}

/* ========= RULES ========= */
Validator.isRequired = (selector, msg) => ({
    selector,
    test: value => value.trim() ? undefined : msg || "Vui lòng nhập trường này"
});

Validator.isEmail = (selector, msg) => ({
    selector,
    test: value =>
        /^\S+@\S+\.\S+$/.test(value) ? undefined : msg || "Email không hợp lệ"
});

Validator.minLength = (selector, min, msg) => ({
    selector,
    test: value =>
        value.length >= min ? undefined : msg || `Ít nhất ${min} ký tự`
});

Validator.isConfirmed = (selector, getConfirm, msg) => ({
    selector,
    test: value =>
        value === getConfirm() ? undefined : msg || "Giá trị không khớp"
});

/* =====================================================
   IMAGE SWITCH (PRODUCT DETAIL)
===================================================== */
(function () {
    const mainImg = document.querySelector(".img-main");
    const thumbs = document.querySelectorAll(".img-desc");

    if (!mainImg || thumbs.length === 0) return;

    thumbs.forEach(img => {
        img.onclick = function () {
            mainImg.src = img.src;

            const active = document.querySelector(".view-img__list-item.act");
            if (active) active.classList.remove("act");

            const parent = img.closest(".view-img__list-item");
            if (parent) parent.classList.add("act");
        };
    });
})();

/* =====================================================
   QUANTITY CONTROL
===================================================== */
(function () {
    document.querySelectorAll(".minus1").forEach(btn => {
        btn.onclick = function () {
            const input = btn.nextElementSibling;
            input.value = Math.max(1, Number(input.value) - 1);
        };
    });

    document.querySelectorAll(".add1").forEach(btn => {
        btn.onclick = function () {
            const input = btn.previousElementSibling;
            input.value = Number(input.value) + 1;
        };
    });
})();

/* =====================================================
   RANGE PRICE
===================================================== */
(function () {
    const range = document.querySelector('input[type="range"]');
    const valueEl = document.getElementById("pro-range__value");

    if (!range || !valueEl) return;

    range.oninput = () => {
        valueEl.innerText = range.value + "đ";
    };
})();

/* =====================================================
   CART TOTAL
===================================================== */
function updateCartTotal() {
    let sum = 0;
    document.querySelectorAll(".cart_main_element_title_total").forEach(el => {
        sum += Number(el.value || 0);
    });

    const totalEl = document.getElementById("total_price");
    const hiddenEl = document.getElementById("total_price_hidden");

    if (totalEl) totalEl.value = sum.toLocaleString();
    if (hiddenEl) hiddenEl.value = sum;
}

/* =====================================================
   ADD / SUB CART
===================================================== */
function sub(id, price) {
    const input = document.getElementById(id);
    if (!input || Number(input.value) <= 1) return;

    input.value--;
    document.getElementById("totalPrice" + id).value = input.value * price;
    document.getElementById("s" + id).value = input.value;
    updateCartTotal();
}

function add(id, price) {
    const input = document.getElementById(id);
    if (!input) return;

    input.value++;
    document.getElementById("totalPrice" + id).value = input.value * price;
    document.getElementById("s" + id).value = input.value;
    updateCartTotal();
}
