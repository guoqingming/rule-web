var company ={}

company.tableParams = function (params) {
    var companyName = $("#companyName").val();
    var temp = {
        pageSize: params.limit, //页面大小
        pageNum: params.offset, //页码
        companyName: companyName

    };
    return temp;
}
company.tableColumns = function () {
    return [

        {
            field: 'id',
            title: 'ID'

        },
        {
            field: 'name',
            title: '公司名称'

        }, {
            field: 'remark',
            title: '描述'
        },  {
            field: 'createAt',
            title: '创建时间'
        }, {
            field: 'createBy',
            title: '创建人'
        }, {
            field: 'updateAt',
            title: '更新时间'
        }, {
            field: 'updateBy',
            title: '更新操作人'
        },

        {
            field: 'action',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var str = "";

                str += "&nbsp;&nbsp;<button class='btn btn-warning' onclick='company.delete(" + row.id + ")'>删除</button>";
                return str;
            }
        }];
}
debugger
company.table = $('#companyTable').bootstrapTable({
    url: "company/list/page", //请求后台的URL（*）
    method: 'get', //请求方式（*）
    striped: true, //是否显示行间隔色
    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, //是否显示分页（*）
    sortable: false, //是否启用排序
    queryParams: company.tableParams,
    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
    pageNumber: 1, //初始化加载第一页，默认第一页
    pageSize: 10, //每页的记录行数（*）
    pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
    search: false, //是否显示表格搜索
    strictSearch: false,
    showColumns: false, //是否显示所有的列
    showRefresh: true, //是否显示刷新按钮
    minimumCountColumns: 2, //最少允许的列数
    clickToSelect: true, //是否启用点击选中行
    showToggle: true, //是否显示详细视图和列表视图的切换按钮
    cardView: false, //是否显示详细视图
    detailView: false, //是否显示父子表

    columns: company.tableColumns(),
    responseHandler: pageResponseHandler
});

company.showAddModal = function () {
    $("#addCompanyModal").modal("show");
}

company.add = function () {
    var companyName = $("#companyNameInput").val();
    var remark = $("#remarkInput").val();
    if (!companyName) {
        alert("公司名称为空！")
        return;
    }

    var param = {};
    param.name = companyName;
    param.remark = remark;

    $.post("company/add",param,function (data) {
        validateResult(data);
        $("#addCompanyModal").modal("hide");
    })
}

company.search = function () {
    company.table.bootstrapTable('refresh', company.tableParams);
}