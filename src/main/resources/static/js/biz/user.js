var user ={}

user.tableParams = function (params) {
    var username = $("#usernameInput").val();
    var email = $("#emailInput").val();
    var temp = {
        pageSize: params.limit, //页面大小
        pageNum: params.offset, //页码
        email: email,
        username: username,

    };
    return temp;
}
user.tableColumns = function () {
    return [

        {
            field: 'id',
            title: 'ID'

        },
        {
            field: 'userName',
            title: '用户名'

        },
        {
            field: 'userNameCn',
            title: '中文名'

        },
        {
            field: 'email',
            title: '邮箱'

        },

        {
            field: 'companyName',
            title: '公司名'

        },{
            field: 'status',
            title: '状态'
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

                str += "<button class='btn btn-success' onclick='user.showAddModal(" + JSON.stringify(row) + ")'>修改</button>";
                str += "&nbsp;&nbsp;<button class='btn btn-success' onclick='user.showRoleSettingModal(" + row.id + ")'>角色配置</button>";
                str += "&nbsp;&nbsp;<button class='btn btn-warning' onclick='user.delete(" + row.id + ")'>删除</button>";
                return str;
            }
        }];
}
user.table = $('#userTable').bootstrapTable({
    url: "user/list/page", //请求后台的URL（*）
    method: 'get', //请求方式（*）
    striped: true, //是否显示行间隔色
    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, //是否显示分页（*）
    sortable: false, //是否启用排序
    queryParams: user.tableParams,
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

    columns: user.tableColumns(),
    responseHandler: pageResponseHandler
});

user.showAddModal = function (obj) {
    $("#addUserModal :input").val("");
    console.log("传入参数：",obj)
    if(obj){
        $("#userId").val(obj.id);
        $("#username").val(obj.userName)
        $("#usernameCn").val(obj.userNameCn)
        $("#email").val(obj.email)
        $("#companyIdSelect").val(obj.companyId)

    }
    $("#addUserModal").modal("show");
}

user.add = function () {
    var userName = $("#username").val();
    var usernameCn = $("#usernameCn").val();
    var email = $("#email").val();
    var companyId = $("#companyIdSelect").val();
    var companyName = $("#companyIdSelect option:selected").text();
    if (!userName) {
        alert("用户名为空！")
        return;
    }
    if(!usernameCn){
        alert("中文名为空")
        return;
    }
    if(!companyId){
        alert("公司为空")
        return;
    }
    if(!email){
        alert("邮箱为空")
        return;
    }

    var param = {};
    param.userName = userName;
    param.userNameCn = usernameCn;
    param.companyId = companyId;
    param.companyName = companyName;
    param.email = email;

    var userId = $("#userId").val();
    var url = "user/add";
    if(userId){
        param.id = userId;
        url = "user/update"
    }

    $.post(url,param,function (data) {
        validateResult(data);
        $("#addUserModal").modal("hide");
        user.table.bootstrapTable('refresh', user.tableParams);
    })
}
$.get("role/list",function (data) {
    var arr = []
    $.each(data.data, function (index, item) {
        var obj = {}
        obj.id = item.id;
        obj.text = item.name;
        arr.push(obj)
    })
    $("#roleSelect").select2({
        placeholder: '全部',
        allowClear: true,
        language: 'zh-CN',
        width: '100%',
        data: arr
    }).on('select2:select', function (e) {

        console.log("选中 " + this.value)
        var userId = $("#userId").val();
        var url = "role/user/bind";
        var roleId = this.value;
        $.post(url, {userId: userId, roleId: roleId}, function (data) {
            validateResult(data)
        })
    }).on("select2:unselect", function (e) {
        var userId = $("#userId").val();
        var url = "role/user/unbind";
        var roleId = e.params.data.id;
        $.post(url, {userId: userId, roleId: roleId}, function (data) {
            validateResult(data)
        })
    })
})
user.delete = function (id) {
    if (!id) {
        alert("策略ID为空")
        return
    }
    layer.confirm("确认要删除吗", {btn: ["确认", "取消"]}, function (index, layero) {
        $.post("user/delete", {id: id}, function (data) {
            validateResult(data);
            user.search();
        })
        return false;
    })

};
user.search = function () {
    user.table.bootstrapTable('refresh', user.tableParams);
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

user.generateKey = function () {
    $.get("user/generateAppId",{},function (data) {
        $("#appId").val(data.data)
    })
}

user.showRoleSettingModal = function (id) {
    $("#userId").val(id)

    $.get("user/roleIds",{userId:id},function (data) {
        var arr = [];
        $.each(data.data,function (index, item) {
            arr.push(""+item)
        })
        debugger
        $("#roleSelect").val(arr).trigger("change");
    })
    $("#roleSettingModal").modal("show")
}

