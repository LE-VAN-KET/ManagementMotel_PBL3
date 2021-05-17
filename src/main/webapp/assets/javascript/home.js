(function($) { "use strict";

    $(document).ready(function () {
        $('body.hero-anime').removeClass('hero-anime');

        $('a.post-sort').click(function(e) {

            $('a.post-sort.active').removeClass('active');

            $(this).addClass('active');
            e.preventDefault();
        });
    });

})(jQuery);