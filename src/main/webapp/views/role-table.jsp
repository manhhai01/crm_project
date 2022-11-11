
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Danh sách quyền</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                <a href="#" class="btn btn-sm btn-success btn-insert">Thêm mới</a>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /row -->
        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="table-responsive">
                        <table class="table" id="example">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên Quyền</th>
                                <th>Mô Tả</th>
                                <th>Hành Động</th>
                            </tr>
                            </thead>
                            <tbody id="role-body">
                            <c:forEach items="${roles}" var="role" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${role.getName()}</td>
                                    <td>${role.getDescription()}</td>
                                    <td>
                                        <a href="#" roleId="${role.getId()}" class="btn btn-sm btn-primary btn-update">Sửa</a>
                                        <a href="#" roleId="${role.getId()}" class="btn btn-sm btn-danger btn-delete">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
</div>
</body>
</html>
