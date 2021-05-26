let theEditor;
ClassicEditor.create(document.querySelector('textarea'), {
    toolbar: {
        items: [
            'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList',
            '|', 'outdent', 'redo', 'alignment', 'blockQuote', 'fontSize', 'fontFamily'
        ]
    },
    language: 'vi',
    licenseKey: '',
})
.then(editor => {
    window.editor = editor;
    theEditor = editor;
})
.catch(error => {
    console.error('Oops, something went wrong!');
});

const isEmptyField = function () {
    let count = $(".img-list-img").children().length;
    if (theEditor.getData() === "") {
        $(".error-description").css("display", "block");
        return false;
    }
    if (count !== 0) {
        return true;
    } else {
        $(".error-file").css("display", "block");
        return false;
    }
}

const imagesPreview = function (input, placeToInsertImagePreview) {
    if (input.files) {
        let filesAmount = input.files.length;
        for (i = 0; i < filesAmount; i++) {
            let reader = new FileReader();

            reader.onload = function (event) {
                $(`<div class="col-2"><img class="img-thumbnail" src="${event.target.result}">
                            <a class="delete" ><i class="far fa-trash-alt"></i></a></div>`).appendTo(
                    placeToInsertImagePreview);
                $('a.delete').click(function () {
                    $(this).parent(".col-2").remove();
                })
            }
            reader.readAsDataURL(input.files[i]);
        }
    }
};

$(document).ready(function () {
    if (window.File && window.FileList && window.FileReader) {
        $('#files').on('change', function () {
            imagesPreview(this, '.img-list-img');
        });
    }

})