var strategy = {


    deleteInput: function (id) {
        if (!id) {

        }
    },
    deleteOutput: function (id) {

    },


}

strategy.tableColumns = function () {
    return [
        {
            field: '',
            title: '序号',
            formatter: function (value, row, index) {
                return index + 1;

            }

        },
        {
            field: 'strategyName',
            title: '策略英文名'

        }, {
            field: 'strategyDesc',
            title: '策略中文名'
        }, {
            field: 'enable',
            title: '是否启用',
            formatter: function (value, row, index) {
                return value == 1 ? "是" : "否";
            }
        }, {
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
                var str = "<button class='btn btn-success' onclick='strategy.showStrategyConfigModal(" + row.id + ")'>策略配置</button>";

                // if(!row.enable){
                //     str += '<div class="switch" data-animated="false">\n' +
                //         '                    <input type="checkbox"  />\n' +
                //         '                </div>';
                // }else {
                //     str += '<div class="switch" data-animated="false">\n' +
                //         '                    <input type="checkbox" checked />\n' +
                //         '                </div>';
                // }
                if (!row.enable || row.enable == 0) {
                    str += "&nbsp;&nbsp;<button class='btn btn-danger' onclick='strategy.delete(" + row.id + ")'>删除</button>";
                }
                return str;
            }
        }];
}
strategy.tableParams = function (params) {
    var keyword;

    var strategyName = $("#strategyName").val();
    var strategyDesc = $("#strategyDesc").val();
    if (strategyName) {
        keyword = strategyName;
    }
    if (strategyDesc) {
        keyword = strategyDesc;
    }
    var temp = {
        pageSize: params.limit, //页面大小
        pageNum: params.offset, //页码
        keyword: keyword

    };
    return temp;
}

strategy.table = $('#strategyTable').bootstrapTable({
    url: "strategy/list/page", //请求后台的URL（*）
    method: 'get', //请求方式（*）
    striped: true, //是否显示行间隔色
    cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true, //是否显示分页（*）
    sortable: false, //是否启用排序
    queryParams: strategy.tableParams,
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
    columns: strategy.tableColumns(),
    responseHandler: pageResponseHandler
});


strategy.search = function () {
    strategy.table.bootstrapTable('refresh', strategy.tableParams);
}

strategy.showAddModal = function () {

    $("#strategyAddModal").modal("show");
}

strategy.add = function () {
    var strategyName = $("#strategyName").val();
    var strategyDesc = $("#strategyNameDesc").val();
    if (!strategyName) {
        layer.msg("策略名为空", {icon: 2, time: 2000})
    }
    if (!strategyDesc) {
        layer.msg("策略中文名为空", {icon: 2, time: 2000})
    }
    var strategy = {};
    strategy.strategyName = strategyName;
    strategy.strategyDesc = strategyDesc;
    $.ajax({
        type: "post",
        url: "strategy/add",
        data: JSON.stringify(strategy),
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            validateResult(data)
            $("#strategyAddModal").modal("hide");
            strategy.search();

        }
    });

}

strategy.delete = function (id) {
    if (!id) {
        alert("策略ID为空")
    }
    layer.confirm("确认要删除吗", {btn: ["确认", "取消"]}, function (index, layero) {
        $.post("strategy/delete", {id: id}, function (data) {
            validateResult(data);
            strategy.search();
        })
        return false;
    })


}

strategy.showStrategyConfigModal = function (id) {
    $("#strategySettingModal").modal("show");

    $('input.connector').iCheck({
        radioClass: 'iradio_square-blue'
    });
    $("#strategyId").val(id)
    if (!strategy.inputDataTable) {
        strategy.inputDataTable = paramTable("inputTable", "strategy/input/list", id);
    } else {
        strategy.inputDataTable.bootstrapTable("refresh", {strategyId: id})
    }

    if (!strategy.outputDataTable) {
        strategy.outputDataTable = paramTable("outputTable", "strategy/output/list", id);
    } else {
        strategy.outputDataTable.bootstrapTable("refresh", {strategyId: id})
    }


}

function paramTable(domId, url, strategyId) {

    return $('#' + domId).bootstrapTable({
        url: url, //请求后台的URL（*）
        method: 'get', //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false, //是否显示分页（*）
        sortable: false, //是否启用排序
        queryParams: {strategyId: strategyId},//传递参数（*）
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
        columns: [

            {
                field: '',
                title: '序号',
                formatter: function (value, row, index) {
                    return index + 1;

                }

            },
            {
                field: 'name',
                title: '英文名'

            }, {
                field: 'desc',
                title: '中文名'
            }, {
                field: 'type',
                title: '类型'
            },
            {
                field: 'action',
                title: '操作',
                align: 'center',
                formatter: function (row) {
                    // return "<button class='btn btn-info'>删除</button>”
                    return "<button class='btn btn-danger' onclick='strategy.delete()'>删除</button>";
                }
            }],
        responseHandler: responseHandler
    })
}

strategy.showParamModal = function (type) {
    $("#paramFlag").val(type)
    $("#addParamModal").modal("show");
}

strategy.addParam = function () {
    var type = $("#paramFlag").val();
    var strategyId = $("#strategyId").val();
    var paramName = $("#paramName").val();
    var paramDesc = $("#paramDesc").val();
    var paramType = $("#paramType").val();
    if (!paramName) {
        alert("名称为空")
        return;
    }
    if (!paramDesc) {
        alert("中文名为空")
        return;
    }
    if (!paramType) {
        alert("类型为空")
        return;
    }
    var param = {};
    param.name = paramName;
    param.desc = paramDesc;
    param.type = paramType;
    param.strategyId = strategyId;
    if (type == 1) {
        $.post("strategy/input/add", param, function (data) {
            validateResult(data);
            $("#addParamModal").modal("hide");
            strategy.inputDataTable.bootstrapTable("refresh", {strategyId: strategyId})
        })

    } else if (type == 2) {
        $.post("strategy/output/add", param, function (data) {
            validateResult(data);
            strategy.outputDataTable.bootstrapTable("refresh", {strategyId: strategyId})
            $("#addParamModal").modal("hide");
        })
    }
    $("#addParamModal .form-group :input,select").val("");
}

// $('input').on('ifChanged', function(event){ //ifCreated 事件应该在插件初始化之前绑定
//     alert(event.type + ' callback');
// });
//
// $('input').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
//     alert(event.type + ' callback');
// });
// $('input').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定
//     alert(event.type + ' callback');
// });

$('input.connector').on('ifClicked', function (event) { //ifCreated 事件应该在插件初始化之前绑定
    var connector = $(event.currentTarget).parents("td").find('input.connector:checked').val();
    // var connector = $('input[name="connector"]:checked').val();
    // $(event.currentTarget).iCheck("check");
    if (!connector) {
        var connectorName = $(event.currentTarget).parents("tr").clone(true).find("input.connector").attr("name");
        $(event.currentTarget).parents("tr").clone(true).find("input.connector").attr("name", connectorName + "1").parents("tr").appendTo("#filterTable tbody");
    }
});

strategy.createRules = function () {
    var params = [];
    var ruleParam = {}
    var conditons = [];
    $("#filterTable tbody tr").each(function (i) {
        {
            var obj = {}
            var paramName = $(this).find("td:first select").val();
            var paramDesc = $(this).find("td:first select option:selected").text()
            var paramType = $(this).find("td:first select option:selected").attr("data-type");
            obj.left = {
                paramName: paramName,
                paramDesc: paramDesc,
                paramType: paramType
            };
            var operator = {}
            operator.value = $(this).find("td:eq(1) select").val();
            operator.name = $(this).find("td:eq(1) select option:selected").text();
            obj.relationOperator = operator;
            var right = {}
            right.type = $(this).find("td:eq(2) select").val();
            right.value = $(this).find("td:eq(3) input").val();
            obj.right = right;

            var logicConnector = {}
            logicConnector.value = $(this).find("td:eq(4) input:checked").val()
            obj.logicOperator = logicConnector;
            conditons.push(obj)
        }
    })
    ruleParam.conditions = conditons;
    var outputSettings = [];
    $("#wrapOutputTable tbody tr").each(function (i) {
        {
            var obj = {}
            var paramName = $(this).find("td:first select").val();
            var paramDesc = $(this).find("td:first select option:selected").text()
            var paramType = $(this).find("td:first select option:selected").attr("data-type");
            obj.left = {
                paramName: paramName,
                paramDesc: paramDesc,
                paramType: paramType
            };
            var operator = {}
            operator.value = $(this).find("td:eq(1) select").val();
            operator.name = $(this).find("td:eq(1) select option:selected").text();
            obj.relationOperator = operator;
            var right = {}
            right.type = $(this).find("td:eq(2) select").val();
            right.value = $(this).find("td:eq(3) input").val();
            obj.right = right;

            var logicConnector = {}
            logicConnector.value = $(this).find("td:eq(4) input:checked").val()
            obj.logicOperator = logicConnector;
            outputSettings.push(obj)
        }
    })
    ruleParam.outputSettings = outputSettings;

    params.push(ruleParam)
    $.post("strategy/createRules", {
        strategyId: $("#strategyId").val(), strategyName: "test",
        data: JSON.stringify(params)
    }, function (data) {
        validateResult(data)
    })

}

strategy.previewRules = function () {
    var strategyId = $("#strategyId").val();
    $.get("strategy/get", {id: strategyId}, function (data) {
        var reg = new RegExp("\t", "g")
        if (data) {
            if (data.success) {
                var arr = data.data.ruleContent.split("\n");
                $.each(arr, function (index, item) {
                    item = item.replace(reg, "&nbsp;&nbsp;&nbsp;&nbsp;");
                    var p = "<p class='lead'>" + item + "</p";
                    $("#ruleContentP").append(p)
                })
                $("#ruleContentModal").modal("show")
            } else {
                alert(data.msg)
            }
        }
    })
};
strategy.showTestModal = function () {

    $.get("strategy/input/list", {strategyId: $("#strategyId").val()}, function (data) {
        if (data) {
            $("#inputParamDiv .form-group").empty();
            var str = "";
            if (data.success) {
                $.each(data.data, function (index, item) {
                    str += '<div class="form-group">\n' +
                        '<label class="form-label" for="paramName">\n' +
                        item.desc +
                        '</label>\n' +
                        '<input class="form-control" name="' + item.name + '" >\n' +
                        '</div>';
                })
                $("#inputParamDiv").append(str);
            }
        }
    })
    $("#testRuleModal").modal("show");
}


strategy.testRules = function () {
    var obj = {}
    $("#inputParamDiv .form-control").each(function () {
        var name = $(this).attr("name");
        var value = $(this).val();
        var label = $(this).prev().text();
        if (!value) {
            alert(label + "不能为空！")
            return;
        }
        obj[name] = value;
    })

    var strategyId = $("#strategyId").val();
    var param = {};
    param.data = JSON.stringify(obj)
    param.strategyId = strategyId;
    $.post("strategy/rule/test", param, function (data) {
        if (data) {
            if (data.success) {
                $("#outputResult").empty()

                // var options = {
                //     dom: '#outputResult'
                // };
                // var jf = new JsonFormatter(options)
                // jf.doFormat(JSON.stringify(data.data))
                $("#outputResult").append(JSON.stringify(data.data))
            } else {
                alert(data.msg)
            }
        }
    })
};
strategy.refreshRuleItems = function () {


}