$(document).ready(function () {
    $(".btn-add").click(function () {
        alert("Add user")
    })
    $(".btn-update").click(function () {
        alert("Update user")
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