var parammeterTypeDict = {
    "Integer":"整型",
    "String":"字符串",
    "Double":"浮点型",
    "Boolean":"布尔型"
}
layui.use(['layer'], function () {
    var layer = layui.layer
        

});
var pageResponseHandler = function (data) {
    console.log("返回数据： {}", data);
    if (data && data.success) {
        var result = {};
        result.total = data.data.total || 0;
        result.rows = data.data.list || [];
        return result;
    }
}
var responseHandler = function (data) {
    console.log("返回数据： {}", data);
    if (data && data.success) {
        return data.data || [];
    }
}


function validateResult(data) {
    if(data){
        if(data.success){
            layer.msg("操作成功",{icon:1,time:2000})
        }else {
            layer.msg(data.msg,{icon:2,time:2000})
        }
    }
}

function alert(msg) {
    layer.msg(msg,{icon:2,time:1000})
}