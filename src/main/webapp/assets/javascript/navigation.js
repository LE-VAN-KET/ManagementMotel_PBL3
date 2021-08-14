! function ($) {
    "use strict";
    if ($("#back-to-top").length) {
        let scrollTrigger = 100,
            backToTop = function () {
                var scrollTop;
                $(window).scrollTop() > scrollTrigger ? $("#back-to-top").addClass("show") : $("#back-to-top").removeClass("show")
            };
        backToTop(), $(window).on("scroll", (function () {
            backToTop()
        })), $("#back-to-top").on("click", (function (e) {
            e.preventDefault(), $("html,body").stop().animate({
                scrollTop: 0
            }, 700)
        }))
    }
    $("body").on("mouseenter mouseleave", ".nav-item", (function (e) {
        if ($(window).width() > 750) {
            let _d = $(e.target).closest(".nav-item");
            _d.addClass("show"), setTimeout((function () {
                _d[_d.is(":hover") ? "addClass" : "removeClass"]("show")
            }), 1)
        }
    })), $(document).ready((function () {
        $("nav li a").click((function () {
            $('nav li.active').removeClass('active');
            let _parent = $(this).parent();
            _parent.addClass('active');
        }))
    }))
}(jQuery);