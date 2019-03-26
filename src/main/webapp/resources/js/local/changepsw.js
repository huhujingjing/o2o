$(function() {
    var url = '/o2o/local/changelocalpwd';
    var usertype = getQueryString('usertype');
    $('#submit').click(function() {
        var userName = $('#userName').val();
        var password = $('#password').val();
        var newPassword = $('#newPassword').val();
        var confirmPassword = $('#confirmPassword').val();
        if (newPassword != confirmPassword){
            $.toast('两次输入的新密码不一致！');
            return;
        }
        var formData = new FormData();
        formData.append('userName', userName);
        formData.append('password', password);
        formData.append('newPassword', newPassword);
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码！');
            return;
        }
        formData.append("verifyCodeActual", verifyCodeActual);
        $.ajax({
            url : url,
            type : 'POST',
            data : formData,
            contentType : false,
            processData : false,
            cache : false,
            success : function(data) {
                if (data.success) {
                    $.toast('提交成功！');
                    $.toast('绑定成功！');
                    if (usertype == 1){
                        //若用户在前端展示系统页面则自动退回到前端展示系统首页
                        window.location.href = '/o2o/frontend/index';
                    } else {
                        //若用户是在店家管理系统页面则自动退回到店铺列表页中
                        window.location.href = '/o2o/shopadmin/shoplist';
                    }
                } else {
                    $.toast('提交失败！' + data.errMsg);
                    $('#captcha_img').click();
                }
            }
        });
    });

    $('#back').click(function() {
        window.location.href = '/o2o/shopadmin/shoplist';
    });
});
