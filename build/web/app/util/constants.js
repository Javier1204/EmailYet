var origin=window.location.origin;
var context="/YET/";
var baseUri=origin+context;
var endpointBase=baseUri+"handler"

var endpoints={
    login:endpointBase+"?action=login&",
    send_email:endpointBase+"?action=send_email&"
}

