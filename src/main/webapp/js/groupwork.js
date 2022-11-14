$(document).ready(function () {
    $(".btn-delete").click(function(){
        var id = $(this).attr("jobId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8082/crm/api/job/delete?id=" + id,
            // data: { id: id, methods: methods }
        }).done(function( data ) {
            if(data.success){
                This.closest("tr").remove()
            }else{
                alert("Xoá thất bại")
            }
        });
    })

    $(".btn-insert").click(function () {
        window.location.href = 'http://localhost:8082/crm/groupwork-add';
    })

    $(".btn-update").click(function () {
        var id = $(this).attr("jobId")

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/groupwork-update",
            data: {id: id },
        }) .done(function( data ) {
            window.location.href = 'http://localhost:8082/crm/groupwork-update'
        });
    })
})