let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        let favorite = $('#favorite_perfume').val().split(' ').filter((word) => word.length > 1);
        let unfavorable = $('#unfavorable_perfume').val().split(' ').filter((word) => word.length > 1);

        let data = {
            userId: $('#user_id').text(),
            favoritePerfume: favorite,
            unfavorablePerfume: unfavorable
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/perfume',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function (check) {
            if(check){
                alert('글이 등록되었습니다. ');
                window.location.href = '/';
            } else{alert('이미 등록한 향수명 입니다.')}
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
}
main.init();