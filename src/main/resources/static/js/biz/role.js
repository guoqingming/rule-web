var role = {}

role.tableParams = function (params) {
    var rolename = $("#rolenameInput").val();
    var temp = {
        pageSize: params.limit, //页面大小
        pageNum: params.offset, //页码
        rolename: rolename,

    };
    return temp;
}
role.tableColumns = function () {
    return [

        {
            field: 'id',
            title: 'ID'

        },
        {
            field: 'name',
            title: '角色名称'

        },
        {
            field: 'createAt',
            title: '创建时间'
        }, {
            field: 'createBy',
            title: '创建人'
        },

        {
            field: 'action',
            title: '操作',
            align: 'center',
            formatter: function (value, row, index) {
                var str = "";
                str += "&nbsp;&nbsp;<button class='btn btn-success' onclick='role.showPermissionSettingModal(" + row.id + ")'>权限配置</button>";
                str += "&nbsp;&nbsp;<button class='btn btn-warning' onclick='role.delete(" + row.id + ")'>删除</button>";
                return str;
            }
        }];
}
role.table = $('#roleTable').bootstrapTable({
    url: "role/list/page", //请求后台的URL（*）
    method: 'get', //请求方式（*）
    striped: true, //是否显示行间隔色
    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, //是否显示分页（*）
    sortable: false, //是否启用排序
    queryParams: role.tableParams,
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

    columns: role.tableColumns(),
    responseHandler: pageResponseHandler
});

role.showAddModal = function (obj) {
    if (obj) {
        $("#roleNameInput").val(obj.name)

    }
    $("#addRoleModal").modal("show");
}

role.add = function () {
    var roleName = $("#rolename").val();

    if (!roleName) {
        alert("用户名为空！")
        return;
    }


    var param = {};
    param.name = roleName;


    $.post("role/add", param, function (data) {
        validateResult(data);
        $("#addRoleModal").modal("hide");
        role.table.bootstrapTable('refresh', role.tableParams);
    })
}

role.search = function () {
    role.table.bootstrapTable('refresh', role.tableParams);
}

$.get("menu/list",function (data) {
    var arr = []
    $.each(data.data, function (index, item) {
        var obj = {}
        obj.id = item.id;
        obj.text = item.name;
        arr.push(obj)
    })
    $("#permissionSelect").select2({
        placeholder: '全部',
        allowClear: true,
        language: 'zh-CN',
        width: '100%',
        data: arr
    }).on('select2:select', function (e) {

        console.log("选中 " + this.value)
        var roleId = $("#roleId").val();
        var url = "role/menu/bind";
        var menuId = this.value;
        $.post(url, {roleId: roleId, menuId: menuId}, function (data) {
            validateResult(data)
        })
    }).on("select2:unselect", function (e) {
        var roleId = $("#roleId").val();
        var url = "role/menu/unbind";
        var menuId = e.params.data.id;
        $.post(url, {roleId: roleId, menuId: menuId}, function (data) {
            validateResult(data)
        })
    })
})
// $("#permissionSelect").select2({
//     placeholder: '全部',
//     allowClear: true,
//     language: 'zh-CN',
//     width: '100%',
//     ajax: {
//         url: "menu/list",
//         dataType: 'json',
//         delay: 250,
//         data: function (params) {
//             return {
//                 q: params.term,
//             };
//         },
//         processResults: function (data) {
//
//             var arr = []
//             $.each(data.data, function (index, item) {
//                 var obj = {}
//                 obj.id = item.id;
//                 obj.text = item.name;
//                 arr.push(obj)
//             })
//             console.log(arr)
//             return {
//                 results: arr
//             };
//         },
//         cache: true
//     },
//     escapeMarkup: function (markup) {
//         return markup;
//     },
//     minimumInputLength: 1,
//     templateResult: function (repo) {
//         return repo.text
//     },
//     templateSelection: function (repo) {
//         return repo.text
//     }
// }).on('select2:select', function (e) {

//     console.log("选中 " + this.value)
//     var roleId = $("#roleId").val();
//     var url = "role/menu/bind";
//     var menuId = this.value;
//     $.post(url,{roleId:roleId,menuId:menuId},function (data) {
//         validateResult(data)
//     })
// }).on("select2:unselect", function (e) {
//     var roleId = $("#roleId").val();
//     var url = "role/menu/unbind";
//     var menuId = e.params.data.id;
//     $.post(url,{roleId:roleId,menuId:menuId},function (data) {
//         validateResult(data)
//     })
// });



role.showPermissionSettingModal = function (id) {
    $("#roleId").val(id);

    $.get("role/menuIds",{roleId:id},function (data) {
        var arr = [];
        $.each(data.data,function (index, item) {
            arr.push(""+item)
        })
        $("#permissionSelect").val(arr).trigger("change");
    })
    $("#permissionModal").modal("show")
}

