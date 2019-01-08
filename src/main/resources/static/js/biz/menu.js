var menu ={}

menu.tableParams = function (params) {
    var menuname = $("#menunameInput").val();
    var temp = {
        pageSize: params.limit, //页面大小
        pageNum: params.offset, //页码
        menuName: menuname,

    };
    return temp;
}
menu.tableColumns = function () {
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
            field: 'icon',
            title: '图标'

        },
        {
            field: 'order',
            title: '顺序'

        },{
            field: 'url',
            title: '路径'

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
               var dataStr = JSON.stringify(row);
                var str = "";
                str += "<button class='btn btn-success' onclick='menu.showAddModal("+dataStr+")'>修改</button>";

                str += "&nbsp;&nbsp;<button class='btn btn-warning' onclick='menu.delete(" + row.id + ")'>删除</button>";
                return str;
            }
        }];
}
menu.table = $('#menuTable').bootstrapTable({
    url: "menu/list/page", //请求后台的URL（*）
    method: 'get', //请求方式（*）
    striped: true, //是否显示行间隔色
    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, //是否显示分页（*）
    sortable: false, //是否启用排序
    queryParams: menu.tableParams,
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

    columns: menu.tableColumns(),
    responseHandler: pageResponseHandler
});

menu.showAddModal = function (obj) {
    $("#addMenuModal :input").val("")
    if(obj){
        $("#menuId").val(obj.id)
        $("#menuName").val(obj.name)
        $("#menuIcon").val(obj.icon)
        $("#menuUrl").val(obj.url)
        $("#menuOrder").val(obj.order)

    }
    $("#addMenuModal").modal("show");
}

menu.add = function () {

    var menuName = $("#menuName").val();
    var menuIcon = $("#menuIcon").val();
    var menuUrl = $("#menuUrl").val();
    var menuOrder = $("#menuOrder").val();

    if (!menuName) {
        alert("菜单为空！")
        return;
    }
    if (!menuUrl) {
        alert("URL为空！")
        return;
    }


    var param = {};
    param.name = menuName;
    param.url = menuUrl;
    param.icon = menuIcon;
    param.order = +menuOrder;
    var menuId = $("#menuId").val();
    var url = "menu/add";
    if (menuId) {
        param.id = menuId;
        url = "menu/update"
    }
    $.post(url,param,function (data) {
        validateResult(data);
        $("#addMenuModal").modal("hide");
        menu.table.bootstrapTable('refresh', menu.tableParams);
    });
}

menu.search = function () {
    menu.table.bootstrapTable('refresh', menu.tableParams);
}

menu.delete = function (id) {
    if (!id) {
        alert("传入ID为空");
        return;
    }
    layer.confirm("确认要删除吗", {btn: ["确认", "取消"]}, function (index, layero) {
        $.post("menu/delete", {id: id}, function (data) {
            validateResult(data);
            strategy.search();
        })
        return false;
    })
}


