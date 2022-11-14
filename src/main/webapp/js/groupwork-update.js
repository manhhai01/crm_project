$(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("jobId")
        var name = $("#name").val()
        var startDate = $("#startDate").val()
        var endDate = $("#endDate").val()

        if(name == "" || startDate == "" || endDate == "") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/job/update",
            data: {id: id, name: name, startDate: startDate, endDate: endDate }
        }).done(function( data ) {
            if(data.success){
                alert("Cập nhật thành công")
            }else{
                alert("Cập nhật thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/groupwork'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/groupwork'
    })
})