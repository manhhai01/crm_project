$(document).ready(function () {
    $(".menu-dashboard").click(function () {
        window.location.href = 'http://localhost:8082/crm/home'
    })
    $(".menu-user").click(function () {
        window.location.href = 'http://localhost:8082/crm/user'
    })
    $(".menu-role").click(function () {
        window.location.href = 'http://localhost:8082/crm/role'
    })
    $(".menu-job").click(function () {
        window.location.href = 'http://localhost:8082/crm/groupwork'
    })
    $(".menu-task").click(function () {
        window.location.href = 'http://localhost:8082/crm/task'
    })
    $(".menu-blank").click(function () {
        window.location.href = 'http://localhost:8082/crm/blank'
    })
    $(".menu-error").click(function () {
        window.location.href = 'http://localhost:8082/crm/error'
    })
})