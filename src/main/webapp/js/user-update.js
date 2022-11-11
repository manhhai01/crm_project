$(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("userId")
        var firstname = $("#firstname").val()
        var lastname = $("#lastname").val()
        var email = $("#email").val()
        var password = $("#password").val()
        var roleId = $("#roleId option:selected").val()

        if(firstname == "" || lastname == "" || email == "" || password == "" || roleId == -1) {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/user/update",
            data: {id: id, firstname: firstname, lastname: lastname, email: email, password: password, roleId: roleId }
        }).done(function( data ) {
            if(data.success){
                alert("Cập nhật thành công")
            }else{
                alert("Cập nhật thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/user'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/user'
    })
})