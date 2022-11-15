$(document).ready(function () {
    $(".btn-add").click(function () {
        var name = $("#name").val()
        var startDate = $("#startDate").val()
        var endDate = $("#endDate").val()
        var userId = $("#userId option:selected").val()
        var jobId = $("#jobId option:selected").val()

        if(name=="" || startDate=="" || endDate=="") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }

        $.ajax({
            method: "post",
            url: "http://localhost:8082/crm/api/task/add",
            data: { name: name, startDate: startDate, endDate: endDate, userId: userId, jobId: jobId }
        }).done(function( data ) {
            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href = 'http://localhost:8082/crm/task'
        });
    })

    $(".btn-return").click(function () {
        window.location.href = 'http://localhost:8082/crm/task'
    })
})