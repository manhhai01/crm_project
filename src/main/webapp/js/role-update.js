$(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("roleId")
        var name = $("#name").val()
        var description = $("#description").val()

        if(name == "" || description == "") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/role/update",
            data: {id: id, name: name, description: description }
        }).done(function( data ) {
            if(data.success){
                alert("Cập nhật thành công")
            }else{
                alert("Cập nhật thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/role'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/role'
    })
})