$(document).ready(function () {
    $(".btn-add").click(function () {
        window.location.href = 'http://localhost:8082/crm/user-add'
    })
    $(".btn-update").click(function () {
        var id = $(this).attr("userId")

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/user-update",
            data: {id: id },
        }) .done(function( data ) {
            window.location.href = 'http://localhost:8082/crm/user-update'
        });
    })

    $(".btn-delete").click(function () {
        var id = $(this).attr("userId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8082/crm/api/user/delete?id=" + id,
            // data: { id: id, methods: methods }
        }).done(function( data ) {
            if(data.success){
                This.closest("tr").remove()
            }else{
                alert("Xoá thất bại")
            }
        });
    })
})