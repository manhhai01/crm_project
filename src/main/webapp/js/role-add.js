$(document).ready(function () {
    $(".btn-add").click(function (e) {
        var name = $("#name").val()
        var description = $("#description").val()

        if(name == "" || description == "") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/role/add",
            data: { name: name, description: description }
        }).done(function( data ) {
            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Xoá thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/role'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/role'
    })
})