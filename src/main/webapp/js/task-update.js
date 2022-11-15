$(document).ready(function () {
    $(".btn-update").click(function () {
        var id = $(this).attr("taskId")
        var name = $("#name").val()
        var startDate = $("#startDate").val()
        var endDate = $("#endDate").val()
        var userId = $("#userId option:selected").val()
        var jobId = $("#jobId option:selected").val()
        var statusId = $("#statusId option:selected").val()

        if(name=="" || startDate=="" || endDate=="") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/task/update",
            data: {id: id, name: name, startDate: startDate, endDate: endDate, userId: userId, jobId: jobId, statusId: statusId}
        }).done(function( data ) {
            if(data.success){
                alert("Cập nhật thành công")
            }else{
                alert("Cập nhật thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/task'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/task'
    })
})