;(function(window, $, undefined) {
    "use strict";
    
    // Changeボタンの押下: Radioの項目を変更
    $("#change_radio_button").on('click', function() {
        var templateHtml = $('#animal_radio_template').html();
        $('#radio_wrapper').html(templateHtml);
    });
    
    

    // FormのSubmitイベントが発生した時の動作を定義
    $('#users_form').on('submit', function(e) {

        // submitイベントのデフォルトの動作(画面遷移)をキャンセル(おまじない)
        e.preventDefault();

        // Userの配列を作成 => 個々のPersonオブジェクト
        var users = [];
        $('tr.user').each(function() {
            var $tr = $(this);
            users.push({
                "name":     $tr.find("input[name=name]").val(),
                "email":    $tr.find("input[name=email]").val(),
                "password": $tr.find("input[name=password]").val()
            });
        });

        // POSTデータ
        var postData = {
            "users": users
        };

        // AjaxでPOST
        $.ajax("users", {
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(postData),
            success: function(data, textStatus, jqXHR) {
                console.log(data);
                if (!data) {
                    return;
                }

                // Userの<select>を動的に生成

                var i = 0,
                    l = data.length,
                    user;
                var $select = $('<select></select>');
                for (; i < l; i++) {
                    user = data[i];
                    // <option value=${user.id}>user.name(user.email)</option>
                    $select.append(["<option value=", user.id, ">", user.name, "(", user.email, ")", "</option>"].join(''));
                }
                $('body').append($select);
            }

        });
    });

})(this, this.jQuery);