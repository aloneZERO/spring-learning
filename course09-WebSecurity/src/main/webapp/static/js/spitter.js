$('#registerBtn').click(function () {
    let registerForm = new FormData();
    registerForm.append('firstName', $('input[name=firstName]').val());
    registerForm.append('lastName', $('input[name=lastName]').val());
    registerForm.append('username', $('input[name=username]').val());
    registerForm.append('email', $('input[name=email]').val());
    registerForm.append('password', $('input[name=password]').val());
    registerForm.append('avatar', $('#avatar')[0].files[0]);
    $.ajax({
        type: 'post',
        url: apis.spitter.register,
        data: registerForm,
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(JSON.stringify(data));
            let spitter = data.spitter;
            if (data.result.message === 'success') {
                window.location.href = '/profile?username='+spitter.username;
            } else {
                $('input[name=firstName]').val( excludeUndefined(spitter.firstName) );
                $('input[name=lastName]').val( excludeUndefined(spitter.lastName) );
                $('input[name=username]').val( excludeUndefined(spitter.username) );
                $('input[name=email]').val( excludeUndefined(spitter.email) );
                $('input[name=password]').val( excludeUndefined(spitter.password) );
                showError(data.errors);
            }
        }, error: function (data) {
            console.log(JSON.stringify(data));
        }
    });
});

function showError(errors) {
    $('#firstName-error').text( excludeUndefined(errors.firstName) );
    $('#lastName-error').text( excludeUndefined(errors.lastName) );
    $('#username-error').text( excludeUndefined(errors.username) );
    $('#email-error').text( excludeUndefined(errors.email) );
    $('#password-error').text( excludeUndefined(errors.password) );
}