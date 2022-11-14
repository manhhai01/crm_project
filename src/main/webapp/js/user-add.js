$(document).ready(function () {
    $(".btn-add").click(function () {
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
            url: "http://localhost:8082/crm/api/user/add",
            data: { firstname: firstname, lastname: lastname, email: email, password: password, roleId: roleId }
        }).done(function( data ) {
            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/user'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/user'
    })
})