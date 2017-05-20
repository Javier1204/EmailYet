$(document).ready(function () {
    $("#form_login").submit(function (e) {
        var username = $("#txt_username").val()
        var password = $("#txt_password").val()
        login(username, password,
                function (data, status, request) {
                    if (data.status == 1)
                    {

                        window.location.href = "inicio";
                    } else
                    {
                        eModal.alert("Usuario o Contraseña Incorrecta")
                    }
                    return false;

                },
                function (request, textStatus, error) {
                    eModal.alert("Ha ocurrido un error </br>" + textStatus)
                    return false;
                }
        );
        e.preventDefault()
        e.stopPropagation();
        return false

    })
})



