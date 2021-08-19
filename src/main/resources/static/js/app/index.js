let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        let data = {
            userId: 'aa',
            favoritePerfume: $('#favorite_perfume').val(),
            unfavorablePerfume: $('#unfavorable_perfume').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/pre',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
}
main.init();