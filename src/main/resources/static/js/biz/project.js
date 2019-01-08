var project ={}

project.tableParams = function (params) {
    var companyId = $("#companySelect").val();
    var projectName = $("#projectName").val();
    var temp = {
        pageSize: params.limit, //页面大小
        pageNum: params.offset, //页码
        companyId: companyId,
        projectName: projectName,

    };
    return temp;
}
project.tableColumns = function () {
    return [

        {
            field: 'id',
            title: 'ID'

        },
        {
            field: 'companyName',
            title: '公司名称'

        },
        {
            field: 'name',
            title: '项目名称'

        },

        {
            field: 'privateKey',
            title: '私钥'

        },{
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

                str += "&nbsp;&nbsp;<button class='btn btn-warning' onclick='project.delete(" + row.id + ")'>删除</button>";
                return str;
            }
        }];
}
project.table = $('#projectTable').bootstrapTable({
    url: "project/list", //请求后台的URL（*）
    method: 'get', //请求方式（*）
    striped: true, //是否显示行间隔色
    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, //是否显示分页（*）
    sortable: false, //是否启用排序
    queryParams: project.tableParams,
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

    columns: project.tableColumns(),
    responseHandler: pageResponseHandler
});

project.showAddModal = function (obj) {
    if(obj){
        $("#companyIdSelect").val(obj.companyId)
        $("#projectNameInput").val(obj.name)
        $("#appId").val(obj.privateKey)

    }
    $("#addProjectModal").modal("show");
}

project.add = function () {
    var projectName = $("#projectNameInput").val();
    var companyId = $("#companyIdSelect").val();
    var appId = $("#appId").val();
    var remark = $("#remarkInput").val();
    if (!projectName) {
        alert("项目名称为空！")
        return;
    }
    if(!appId){
        alert("应用ID为空")
        return;
    }
    if(!companyId){
        alert("公司为空")
        return;
    }

    var param = {};
    param.name = projectName;
    param.remark = remark;
    param.companyId = companyId;
    param.privateKey = appId;

    $.post("project/add",param,function (data) {
        validateResult(data);
        $("#addProjectModal").modal("hide");
        project.table.bootstrapTable('refresh', project.tableParams);
    })
}

project.search = function () {
    project.table.bootstrapTable('refresh', project.tableParams);
}

$(function () {
    $.get("company/list",{},function (data) {
        if (data) {
            if(data.success){
                $(".companySelect option[value!='']").remove();
                $.each(data.data,function (index, item) {
                    $(".companySelect").append("<option value='"+item.id+"'>"+item.name+"</option>")
                })
            }
        }
    })
})

project.generateKey = function () {
    $.get("project/generateAppId",{},function (data) {
        $("#appId").val(data.data)
    })
}