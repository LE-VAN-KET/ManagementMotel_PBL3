ClassicEditor.create(document.querySelector('.editor'), {
    toolbar: {
        items: [
            'heading',
            '|',
            'bold',
            'italic',
            'link',
            'bulletedList',
            'numberedList',
            '|',
            'outdent',
            'redo',
            'alignment',
            'blockQuote',
            'fontSize',
            'fontFamily'
        ]
    },
    language: 'vi',
    licenseKey: '',


})
    .then(editor => {
        window.editor = editor;


    })
    .catch(error => {
        console.error('Oops, something went wrong!');
        console.error('Please, report the following error on https://github.com/ckeditor/ckeditor5/issues with the build id and the error stack trace:');
        console.warn('Build id: ivnhwqynk9aw-3fqxvojx359x');
        console.error(error);
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

let files = [];
$(document).ready(function () {
    if (window.File && window.FileList && window.FileReader) {
        $('#files').on('change', function () {
            imagesPreview(this, '.img-list-img');
        });
    }

    const imagesPreview = function (input, placeToInsertImagePreview) {
        if (input.files) {
            files.push(...input.files);
            $('#files').val(null);
            input.files = new FileListItems(files);
            $(".delete_file_add").parent(".col-2").remove();

            let length = files.length;
            for (let i = 0; i < length; i++) {
                let reader = new FileReader();

                reader.onload = function (event) {
                    $(`<div class="col-2"><img class="img-thumbnail" src="${event.target.result}">
                    <a class="delete_file_add" data-name="${files[i].name}" ><i class="far fa-trash-alt"></i></a></div>`)
                        .appendTo(placeToInsertImagePreview);
                    $(".delete_file_add").click(function () {
                        for (let index = 0; index < files.length; index++) {
                            if (files[index].name == $(this).data("name")) {
                                files.splice(index, 1);
                                $('#files').val(null);
                                input.files = new FileListItems(files);
                            }
                        }
                        $(this).parent(".col-2").remove();
                    })
                };
                reader.readAsDataURL(files[i]);
            }
            ;
        }
    };

    function FileListItems(files) {
        let b = new ClipboardEvent("").clipboardData || new DataTransfer()
        for (let i = 0, len = files.length; i < len; i++) {
            b.items.add(files[i]);
        }
        return b.files;
    }
})

