
const validateForm = () => {
    let check = true;
    var username = $('#username'),
    password = $('#password'),
    udiv = $('#u'),
    pdiv = $('#p');
    if (username.val().trim() === '') {
        udiv.attr('errr', '');
        check = false;
    } else {
        udiv.removeAttr('errr');
    }

        if (password.val().trim() === '') {
        pdiv.attr('errr', '');
        check = false;
    } else {
        pdiv.removeAttr('errr');
    }
    return check;
}