$.ajax({
    type: "get",
    url: apis.spittles.find,
    data: {},
    success: function (data) {
        if (data.result.message === 'success') {
            let spittleList = data.spittleList;
            for (i = 0; i < spittleList.length; i++) {
                let spittle = spittleList[i];
                $('.spittleList').append(
                    "<li class='spittle'>" +
                    "   <a id='spittle_" + spittle.id + "' onclick='showSpittle(this)' href='#'>" +
                    "       <div class='spittleMessage'>" + spittle.message + "</div>" +
                    "   <a/>" +
                    "   <div>" +
                    "       <span class='spittleTime'>" + spittle.time + "</span>" +
                    "       <span class='spittleLocation'>(" + spittle.latitude + ", " + spittle.longitude + ")</span>" +
                    "   </div>" +
                    "</li>");
            }
        }
    },
    error: function (data) {
        console.log(JSON.stringify(data));
    }
});

$('#spittleFormBtn').click(function () {
    let spittleForm = {};
    spittleForm.message = $('#message').val();
    spittleForm.latitude = 0.0;
    spittleForm.longitude = 0.0;
    $.ajax({
        type: 'post',
        url: apis.spittles.save,
        data: spittleForm,
        success: function (data) {
            if (data.result.message === 'success') {
                let spittle = data.spittle;
                $('.spittleList').prepend(
                    "<li class='spittle'>" +
                    "   <a id='spittle_" + spittle.id + "' onclick='showSpittle(this)' href='#' >" +
                    "       <div class='spittleMessage'>" + spittle.message + "</div>" +
                    "   <a/>" +
                    "   <div>" +
                    "       <span class='spittleTime'>" + spittle.time + "</span>" +
                    "       <span class='spittleLocation'>(" + spittle.latitude + ", " + spittle.longitude + ")</span>" +
                    "   </div>" +
                    "</li>");
            }
        }, error: function (data) {
            console.log(JSON.stringify(data));
        }
    });
});

function showSpittle(node) {
    let spittleId = node.id.split('_')[1];
    window.location.href='/spittle.html?spittleId='+spittleId;
}