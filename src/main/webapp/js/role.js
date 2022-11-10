//$ => jquery
$(document).ready(function(){

    //$("selector") :
    // id : # (Dấu thăng)
    // class :  . (Dấu chấm)
    //find("selector")
    //this: đại diện cho thẻ đang xảy ra sự kiện click
    //parent(): Đi 1 cấp ra thằng cha của selector gọi
    //closest(): Đi ra n cấp chỉ định ( đi ra tổ tiên )
    //String template : `` để gọi code ${code}
    $(".btn-delete").click(function(){
        var id = $(this).attr("roleId")
        var This = $(this)
        $.ajax({
            method: "get",
            url: "http://localhost:8082/crm/api/role/delete?id=" + id,
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
        window.location.href = 'http://localhost:8082/crm/role-add';
    })

    $(".btn-update").click(function () {
        var id = $(this).attr("roleId")
        var name = $(this).parent().prev().prev().text()
        var description = $(this).parent().prev().text()

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/role-update",
            data: {id: id, name: name, description: description },
        }) .done(function( data ) {
            window.location.href = 'http://localhost:8082/crm/role-update'
        });
    })
})