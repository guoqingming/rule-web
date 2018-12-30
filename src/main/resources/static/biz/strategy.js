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
            strategy.search;

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

    // $('input.connector').iCheck({
    //     radioClass: 'iradio_square-blue'
    // });
    $("#strategyId").val(id)
    if (!strategy.inputDataTable) {
        strategy.inputDataTable = inputParamTable(id);
    } else {
        strategy.inputDataTable.bootstrapTable("refresh", {strategyId: id})
    }

    if (!strategy.outputDataTable) {
        strategy.outputDataTable = outputParamTable(id);
    } else {
        strategy.outputDataTable.bootstrapTable("refresh", {strategyId: id})
    }


}


function inputParamTable(strategyId) {

    return $('#inputTable').bootstrapTable({
        url: "strategy/input/list", //请求后台的URL（*）
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
                formatter: function (value, row, index) {
                    // return "<button class='btn btn-info'>删除</button>”
                    return "<button class='btn btn-danger' onclick='strategy.deleteInput("+row.strategyId+")'>删除</button>";
                }
            }],
        responseHandler: function (data) {
            if (data && data.success) {
                strategy.inputParams = data.data;
                return data.data || [];
            }

        }
    })
}

function outputParamTable(strategyId) {

    return $('#outputTable').bootstrapTable({
        url: "strategy/output/list", //请求后台的URL（*）
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
                formatter: function (value, row, index) {
                    // return "<button class='btn btn-info'>删除</button>”
                    return "<button class='btn btn-danger' onclick='strategy.deleteOutput("+row.strategyId+")'>删除</button>";
                }
            }],
        responseHandler: function (data) {
            if (data && data.success) {
                strategy.outputParams = data.data;
                return data.data || [];
            }

        }
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


//
// $("input.connector").on("click",function (e) {
//     console.log("radio click ----",e)
// })


// $('input.connector').on('ifClicked', function (event) { //ifCreated 事件应该在插件初始化之前绑定
//     var connector = $(event.currentTarget).parents("td").find('input.connector:checked').val();
//     // var connector = $('input[name="connector"]:checked').val();
//     // $(event.currentTarget).iCheck("check");
//     if (!connector) {
//         var connectorName = $(event.currentTarget).parents("tr").clone(true).find("input.connector").attr("name");
//         $(event.currentTarget).parents("tr").clone(true).find("input.connector").attr("name", connectorName + "1").parents("tr").appendTo("#filterTable tbody");
//     }
// });

strategy.createRules = function () {
    var params = [];
    var flag = true;
    $(".rule-panel").each(function () {
        var ruleParam = {}
        var ruleDesc = $(this).find(".ruleDesc").val();
        if(!ruleDesc){
            alert("规则描述为空！")
            flag = false;
            return false;
        }
        ruleParam.ruleDesc = ruleDesc;
        var conditons = [];
        $(this).find(".filterTable tbody tr").each(function (i) {
            {
                var obj = {}
                obj.group = $(this).find("td:eq(0)").text();
                obj.groupFlag = $(this).find("td:eq(0)").attr("group-flag");
                var paramName = $(this).find("td:eq(1) select").val();
                var paramDesc = $(this).find("td:eq(1) select option:selected").text()
                var paramType = $(this).find("td:eq(1) select option:selected").attr("data-type");
                obj.left = {
                    paramName: paramName,
                    paramDesc: paramDesc,
                    paramType: paramType
                };
                var operator = {}
                operator.value = $(this).find("td:eq(2) select").val();
                operator.name = $(this).find("td:eq(2) select option:selected").text();
                obj.relationOperator = operator;
                var right = {}
                right.type = $(this).find("td:eq(3) select").val();
                right.value = $(this).find("td:eq(4) input").val();
                obj.right = right;

                var logicConnector = {}
                logicConnector.value = $(this).find("td:eq(5) input:checked").val()
                obj.logicOperator = logicConnector;
                conditons.push(obj)
            }
        })
        ruleParam.conditions = conditons;
        var outputSettings = [];
        $(this).find(".wrapOutputTable tbody tr").each(function (i) {
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
    })

    if(!flag){
        return;
    }
    $.post("strategy/createRules", {
        strategyId: $("#strategyId").val(), strategyName: "test",
        data: JSON.stringify(params)
    }, function (data) {
        validateResult(data)
    })

}

strategy.previewRules = function () {
    $("#ruleContentP").empty()
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
    var flag = true;
    $("#inputParamDiv .form-control").each(function () {
        var name = $(this).attr("name");
        var value = $(this).val();
        var label = $(this).prev().text();
        if (!value) {
            alert(label + "不能为空！")
            flag = false;
            return false;
        }
        obj[name] = value;
    })
    if (!flag) {
        return;
    }
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
strategy.refreshRuleSettings = function () {
    var strategyId = $("#strategyId").val();
    strategy.defaultConditionTr = '<tr>\n' +
                                '      <td >1</td>'+
                                '      <td>\n' +
                                '          <select  class="form-control inputSelect">\n' +
                                '              <option value="">请选择</option>\n';

    if(strategy.inputParams ){
        $.each(strategy.inputParams, function (index, item) {
            strategy.defaultConditionTr += '<option data-type="' + item.type + '" value="' + item.name + '">' + item.desc + '</option>';
        })
    }
    strategy.defaultConditionTr += '       </select>\n' +
                                        '      </td>\n' +
                                        '      <td>\n' +
                                        '          <select class="form-control">\n' +
                                        '              <option value="">请选择</option>\n' +
                                        '              <option value="==">等于</option>\n' +
                                        '              <option value=">">大于</option>\n' +
                                        '              <option value=">=">大于等于</option>\n' +
                                        '              <option value="<">小于</option>\n' +
                                        '              <option value="<=">小于等于</option>\n' +
                                        '              <option value="!=">不等于</option>\n' +
                                        '          </select>\n' +
                                        '      </td>\n' +
                                        '         \n' +
                                        '      <td>\n' +
                                        '          <select class="form-control">\n' +
                                        '              <option value="">请选择类型</option>\n' +
                                        '              <option value="1">常量</option>\n' +
                                        '              <option value="2">自定义</option>\n' +
                                        '          </select>\n' +
                                        '      </td>\n' +
                                        '      <td>\n' +
                                        '          <input class="form-control">\n' +
                                        '      </td>\n' +
                                        '      <td>\n' +
                                        '           <input  type="radio"  onclick="strategy.showConditionGroupModal(this)" name="connector" class="connector" value="&&">\n' +
                                        '           <label >并且</label>\n' +
                                        '           <input onclick="strategy.showConditionGroupModal(this)" type="radio" name="connector" class="connector" value="||" >\n' +
                                        '           <label >或者</label>\n' +
                                        '       </td>'+
                                        '<td> <button class="btn btn-danger" onclick="strategy.deleteConditionTr(this)">删除</button></td>' +
                                        ' </tr>'

    strategy.outputResultTr = '<tr>\n' +
                            '        <td>\n' +
                            '            <select  class="form-control outputSelect">\n' +
                            '                <option value="">请选择</option>\n';
    if(strategy.outputParams){
        $.each(strategy.outputParams, function (index, item) {
            strategy.outputResultTr += '<option data-type="' + item.type + '" value="' + item.name + '">' + item.desc + '</option>';
        })
    }
    strategy.outputResultTr += '          </select>\n' +
                            '        </td>\n' +
                            '        <td>\n' +
                            '            <select class="form-control">\n' +
                            '                <option value="">请选择</option>\n' +
                            '                <option value="==">等于</option>\n' +
                            '            </select>\n' +
                            '        </td>\n' +
                            '       <n></n>\n' +
                            '        <td>\n' +
                            '            <select class="form-control">\n' +
                            '                <option value="">请选择类型</option>\n' +
                            '                <option value="1">常量</option>\n' +
                            '                <option value="2">自定义</option>\n' +
                            '            </select>\n' +
                            '        </td>\n' +
                            '        <td>\n' +
                            '            <input class="form-control">\n' +
                            '        </td>\n' +
                            '        <td>\n' +
                            '            <button class="btn btn-danger" onclick="strategy.deteleOutputTr(this)">删除</button>\n' +
                            '        </td>\n' +
                            '    </tr>';

$.get("strategy/get",{id: strategyId},function (data) {
    if(data){
        if (!data.success) {
            alert(data.msg)
            return;
        }
        if (!data.data||!data.data.ruleParams||JSON.parse(data.data.ruleParams).length == 0) {
            strategy.initRuleSettings();
            return;
        }

        var params = JSON.parse(data.data.ruleParams);
        strategy.renderRuleSettings(params);
        
    }
})
}
 strategy.getDefaultRulePanelHtml = function(){
     var defaultRuleSettingsHtml = '<div class="panel panel-primary rule-panel">\n' +
         '                                            <div class="panel-heading">\n' +
         '                                                <h3 class="panel-title">规则</h3>\n' +
         '                                            </div>\n' +
         '                                            <div class="panel-body">\n' +
         // '                                               <div class="col-md-12">' +
         '                                                   <div class="form-group ">' +
         '                                                       <label class="form-label">规则描述</label>' +
         '                                                       <input class="form-control ruleDesc" style="width: 40%">' +
         '                                                   </div>' +
         // '                                               </div>' +
         '                                                <div class="panel panel-info">\n' +
         '                                                    <div class="panel-heading">\n' +
         '                                                        <h3 class="panel-title">过滤条件</h3>\n' +
         '\n' +
         '                                                    </div>\n' +
         '                                                    <div class="panel-body">\n' +
         '                                                        <form class="form-inline" role="form">\n' +
         '                                                            <table class="table filterTable">\n' +
         '                                                                <thead>\n' +
         '                                                                <th>组</th>\n' +
         '                                                                <th>输入参数</th>\n' +
         '                                                                <th>比较符</th>\n' +
         '                                                                <th>类型</th>\n' +
         '                                                                <th>值</th>\n' +
         '                                                                <th>连接符</th>\n' +
         '                                                                <th>操作</th>\n' +
         '                                                                </thead>\n' +
         '                                                                <tbody>\n';
     defaultRuleSettingsHtml += strategy.defaultConditionTr;
     defaultRuleSettingsHtml += '                                                                </tbody>\n' +
         '                                                            </table>\n' +
         '\n' +
         '\n' +
         '\n' +
         '                                                        </form>\n' +
         '\n' +
         '                                                    </div>\n' +
         '                                                </div>\n' +
         '                                                <div class="panel panel-info">\n' +
         '                                                    <div class="panel-heading">\n' +
         '                                                        <h3 class="panel-title">输出定义</h3>\n' +
         '                                                    </div>\n' +
         '                                                    <div class="panel-body">\n' +
         '                                                        <form class="form-inline" role="form">\n' +
         '\n' +
         '                                                            <table class="table wrapOutputTable" >\n' +
         '                                                                <thead>\n' +
         '                                                                <th>输出参数</th>\n' +
         '                                                                <th>赋值</th>\n' +
         '                                                                <th>类型</th>\n' +
         '                                                                <th>值</th>\n' +
         '                                                                </thead>\n' +
         '                                                                <tbody>\n'
     defaultRuleSettingsHtml += strategy.outputResultTr;
     defaultRuleSettingsHtml += '                                         </tbody>\n' +
         '                                                            </table>\n' +
         '\n' +
         '                                                        </form>\n' +
         '\n' +
         '                                                    </div>\n' +
         '                                                </div>\n' +
         '                                            </div>\n' +
         '                                        </div>';
     return defaultRuleSettingsHtml;
 }
strategy.initRuleSettings = function () {

    $("#ruleSettingParentDiv").empty();
    $("#ruleSettingParentDiv").append(strategy.getDefaultRulePanelHtml());

}

strategy.renderRuleSettings = function (ruleParams) {
    $("#ruleSettingParentDiv").empty();
    $.each(ruleParams,function (index, item) {
        var defaultRuleSettingsHtml = '<div class="panel panel-primary rule-panel">\n' +
            '                                            <div class="panel-heading">\n' +
            '                                                <h3 class="panel-title">规则 &nbsp;&nbsp;<a href="#" onclick="strategy.deleteRulePanel(this)" class="addOutputTr glyphicon glyphicon-minus"></a></h3>\n' +
            '                                            </div>\n' +
            '                                            <div class="panel-body">\n' +
            // '                                               <div class="col-md-12">' +
            '                                                   <div class="form-group ">' +
            '                                                       <label class="form-label">规则描述</label>' +
            '                                                       <input class="form-control ruleDesc" style="width: 40%" value="'+item.ruleDesc+'">' +
            '                                                   </div>' +
            // '                                               </div>' +
            '                                                <div class="panel panel-info">\n' +
            '                                                    <div class="panel-heading">\n' +
            '                                                        <h3 class="panel-title">过滤条件</h3>\n' +
            '\n' +
            '                                                    </div>\n' +
            '                                                    <div class="panel-body">\n' +
            '                                                        <form class="form-inline" role="form">\n' +
            '                                                            <table class="table filterTable">\n' +
            '                                                                <thead>\n' +
            '                                                                <th>组</th>\n' +
            '                                                                <th>输入参数</th>\n' +
            '                                                                <th>比较符</th>\n' +
            '                                                                <th>类型</th>\n' +
            '                                                                <th>值</th>\n' +
            '                                                                <th>连接符</th>\n' +
            '                                                                </thead>\n' +
            '                                                                <tbody>\n';
        defaultRuleSettingsHtml += strategy.defaultConditionTr;
        defaultRuleSettingsHtml += '                                                                </tbody>\n' +
            '                                                            </table>\n' +
            '\n' +
            '\n' +
            '\n' +
            '                                                        </form>\n' +
            '\n' +
            '                                                    </div>\n' +
            '                                                </div>\n' +
            '                                                <div class="panel panel-info">\n' +
            '                                                    <div class="panel-heading">\n' +
            '                                                        <h3 class="panel-title">输出定义 &nbsp;&nbsp;<a href="#" onclick="strategy.addOutputTr(this)" class="addOutputTr glyphicon glyphicon-plus"></a></h3>\n' +
            '                                                    </div>\n' +
            '                                                    <div class="panel-body">\n' +
            '                                                        <form class="form-inline" role="form">\n' +
            '\n' +
            '                                                            <table class="table wrapOutputTable" >\n' +
            '                                                                <thead>\n' +
            '                                                                <th>输出参数</th>\n' +
            '                                                                <th>赋值</th>\n' +
            '                                                                <th>类型</th>\n' +
            '                                                                <th>值</th>\n' +
            '                                                                <th>操作</th>\n' +
            '                                                                </thead>\n' +
            '                                                                <tbody>\n'
        defaultRuleSettingsHtml += strategy.outputResultTr;
        defaultRuleSettingsHtml += '                                         </tbody>\n' +
            '                                                            </table>\n' +
            '\n' +
            '                                                        </form>\n' +
            '\n' +
            '                                                    </div>\n' +
            '                                                </div>\n' +
            '                                            </div>\n' +
            '                                        </div>';


        $("#ruleSettingParentDiv").append(defaultRuleSettingsHtml);
    })

    $.each(ruleParams,function (index, item) {

        $.each(item.conditions,function (i,item) {
            $("#ruleSettingParentDiv .filterTable:eq("+index+") tbody tr:eq("+i+") td:eq(1) select").val(item.left.paramName);
            $("#ruleSettingParentDiv .filterTable:eq("+index+") tbody tr:eq("+i+") td:eq(2) select").val(item.relationOperator.value);
            $("#ruleSettingParentDiv .filterTable:eq("+index+") tbody tr:eq("+i+") td:eq(3) select").val(item.right.type);
            $("#ruleSettingParentDiv .filterTable:eq("+index+") tbody tr:eq("+i+") td:eq(4) input").val(item.right.value);
        })
        $.each(item.outputSettings,function (i,item) {
            $("#ruleSettingParentDiv .wrapOutputTable:eq("+index+") tbody tr:eq("+i+") td:first select").val(item.left.paramName);
            $("#ruleSettingParentDiv .wrapOutputTable:eq("+index+") tbody tr:eq("+i+") td:eq(1) select").val(item.relationOperator.value);
            $("#ruleSettingParentDiv .wrapOutputTable:eq("+index+") tbody tr:eq("+i+") td:eq(2) select").val(item.right.type);
            $("#ruleSettingParentDiv .wrapOutputTable:eq("+index+") tbody tr:eq("+i+") td:eq(3) input").val(item.right.value);
        })
    })

}

strategy.showConditionGroupModal = function (obj) {
    strategy.logicRadioObj = obj;
    var group = $(obj).parents("tr").find("td:first").text();
    $("#groupSelect option[value != '']").remove();
    $("#groupSelect").append("<option value='"+group+"'>"+group+"</option>");
    $("#groupSelect").append("<option value='0'>新建组</option>");
    $("#ConditionGroupModal").modal("show")
};

strategy.addConditionTr = function(){
    var obj = strategy.logicRadioObj;
    var group = $(obj).parents("tr").find("td:first").text();
    var newGroup = $("#groupSelect").val();
    if (!newGroup) {
        alert("请选择组！")
        return;
    }

    if(newGroup == "0"){
        newGroup = (+group) + 1 + "";
    }else {
        var groupFloag = $(obj).parents("tr").find("td:first").attr("group-flag");
        if(groupFloag == ")"){
            $(obj).parents("tr").find("td:first").removeAttr("group-flag")
        }
        $(obj).parents("tbody").find("tr").each(function () {
            if(newGroup ==$(this).find("td:first").text()){
                $(this).find("td:first").attr("group-flag","(");
                return false
            }
        })
    }
    var name = $(obj).attr("name");
    var reg = new RegExp("connector", "g");
    name = name.replace(reg,"");
    var newName = "";
    if(!name){
        newName = "connector" + 1;
    }else {
        newName = "connector" + (+name + 1);
    }
    var num = $(obj).parents("tr").next().length;
    if(num == 0){
        var html = $(strategy.defaultConditionTr).find(":radio").attr("name",newName).parents("tr").find("td:first").text(newGroup).attr("group-flag",")").parent().html()
        var tr = "<tr>" +html+ "</tr>";
        $(obj).parents("table tbody").append(tr)
    }
    $("#ConditionGroupModal").modal("hide")

}
strategy.addOutputTr = function (obj) {
    console.log("添加------",obj)
    $(obj).parent().parent().next().find("tbody").append(strategy.outputResultTr)
}

strategy.deteleOutputTr = function (obj) {
    $(obj).parents("tr").remove()
}

strategy.deleteConditionTr = function (obj) {
    $(obj).parents("tr").prev().find("input.connector").prop("checked",false)
    var group = $(obj).parents("tr").find("td:first").text();
    var groupFlag =  $(obj).parents("tr").find("td:first").attr("group-flag")
    var num = 0;
    $(obj).parents("tbody").find("tr").each(function () {
        if(group == $(this).find("td:first").text()){
            num ++;
        }
    })
    if(num == 2){
        if(groupFlag == ")"){
            $(obj).parents("tr").prev().find("td:first").removeAttr("group-flag")
        }
        if(groupFlag == "("){
            $(obj).parents("tr").next().find("td:first").removeAttr("group-flag")
        }

    }
    if (num > 2) {
        if(groupFlag == ")"){
            $(obj).parents("tr").prev().find("td:first").attr("group-flag",")")
        }
        if(groupFlag == "("){
            $(obj).parents("tr").next().find("td:first").attr("group-flag","(")
        }
    }

    $(obj).parents("tr").remove();

}

strategy.addRulePanel = function () {
    $("#ruleSettingParentDiv").append(strategy.getDefaultRulePanelHtml());
}

$("body").on("change",".inputSelect",function () {
    var dataType = $(this).find("option:selected").attr("data-type")
    if(dataType=="String"){
        $(this).parent("td").next().find("select option[value='==']").attr("selected",true)
    }else {
        $(this).parent("td").next().find("select option[value='']").attr("selected",true)
    }
})
$("body").on("change",".outputSelect",function () {
    var dataType = $(this).find("option:selected").attr("data-type")
    if(dataType=="String"){
        $(this).parent("td").next().find("select option[value='=']").attr("selected",true)
    }else {
        $(this).parent("td").next().find("select option[value='']").attr("selected",true)
    }
})

strategy.deleteRulePanel = function (obj) {
    $(obj).parents(".rule-panel").remove()
}