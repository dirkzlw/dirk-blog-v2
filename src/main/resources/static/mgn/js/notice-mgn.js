$(function () {  //jquery里的,是当文档载入完毕就执行的意思

    //新增公告--确认
    $('#add_btn').click(function () {
        methods.addHandle()
    })
    $('#xadd_btn').click(function () {
        methods.xaddHandle()
    })

    $('#show_tbody').on('click', '.edit', function () {
        authId = this.id
        aTag = document.getElementById(authId * 1 + 1);
        $('#xrenyuan').modal('show');
    })

})

var authId,
    aTag,
    tdStr='',
    addEnter = true,
    hasNullMes = false;

var methods = {

    addHandle: function (the_index) {
        hasNullMes = false;
        methods.checkMustMes();
        if (hasNullMes) {
            return;
        }
        if (addEnter) {
            var noticeMsg = $('.notice_add').val().trim();
            $.ajax({
                type: "POST",
                url: "/mgn/notice/add",
                data: {
                    'noticeMsg': noticeMsg,
                },
                dataType: "text", //return dataType: text or json
                success: function (json) {
                    json = eval('(' + json + ')');
                    var noticeId=json.obj.id;
                    var message=json.obj.message;
                    var noticeIdTr = noticeId*(-1);
                    // var password=json.password;
                    var rtn = json.rtn;
                    if (rtn == "success") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "添加公告成功！",
                            closeButton: false
                        })

                        //拼接tr
                        tdStr="<td>"+message+"</td>\n" +
                            "                <td>\n" +
                            "                    <a class='edit'>编辑</a>\n" +
                            "                    <a id='" + noticeId + "' onclick='delNotice(this.id)'>删除</a>\n" +
                            "                </td>"
                        $('#show_tbody').append('<tr id=' + noticeIdTr + '>' + tdStr + '</tr>');
                        $('#renyuan').modal('hide');
                    } else if (rtn == "fail") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "添加公告失败，请检查网络！",
                            closeButton: false
                        })
                        return
                    }
                },
                error: function (json) {
                    bootbox.alert({
                        title: "来自DirkBlog的提示",
                        message: "添加公告失败，请检查网络！",
                        closeButton: false
                    })
                    return
                }
            });
        }
    },
    checkMustMes: function () {

        //理由不能为空
        var noticeMsg = $('.notice_add').val().trim()
        if (noticeMsg === '') {
            bootbox.alert({
                title: "来自DirkBlog的提示",
                message: "公告不能为空",
                closeButton: false
            })
            hasNullMes = true;
            return
        }
    },
    xaddHandle: function (the_index) {
        hasNullMes = false;
        methods.xcheckMustMes();
        if (hasNullMes) {
            return;
        }
        if (addEnter) {
            var noPassReason = $('.noPassReason').val().trim();
            $.ajax({
                type: "POST",
                url: "/user/auth/nopass",
                data: {
                    'authId': authId,
                    'noPassReason': noPassReason,
                },
                dataType: "text", //return dataType: text or json
                success: function (json) {
                    if (json == "success") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "用户认证不予通过成功",
                            closeButton: false
                        })
                        aTag.remove();
                        $('#xrenyuan').modal('hide');
                    } else if (rtn == "timeError") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "用户认证不通过失败，请检查网络",
                            closeButton: false
                        })
                        return
                    }
                },
                error: function (json) {
                    bootbox.alert({
                        title: "来自DirkBlog的提示",
                        message: "用户认证不通过失败，请检查网络",
                        closeButton: false
                    })
                    return
                }
            });
        }
    },
    xcheckMustMes: function () {

        //理由不能为空
        var noPassReason = $('.noPassReason').val().trim()
        if (noPassReason === '') {
            bootbox.alert({
                title: "来自DirkBlog的提示",
                message: "不予通过原因不能为空",
                closeButton: false
            })
            hasNullMes = true;
            return
        }
    },
}