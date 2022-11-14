$(document).ready(function () {
    $(".btn-add").click(function (e) {
        var name = $("#name").val()
        var startDate = $("#startDate").val()
        var endDate = $("#endDate").val()

        if(name == "" || startDate == "" || endDate == "") {
            // Validate here
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/job/add",
            data: { name: name, startDate: startDate, endDate: endDate }
        }).done(function( data ) {
            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/groupwork'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/groupwork'
    })
})