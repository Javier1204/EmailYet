function login(username, password, cbtrue, cberror) {
    var path = endpoints.login + "username=" + username + "&password=" + password;
    ajax("POST", path, cbtrue, cberror)
}

function ajax(type, url, success, cberror)
{
    $.ajax({
        type: type,
        url: url,
        success: function (data, status, request)
        {
            success(data, status, request);
        },
        error: function (request, textStatus, error) {
            cberror(request, textStatus, error);
        }
    });
}