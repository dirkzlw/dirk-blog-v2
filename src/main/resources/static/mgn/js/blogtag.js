$(function () {  //jquery里的,是当文档载入完毕就执行的意思

    //新增公告--确认
    $('#add_btn').click(function () {
        methods.addHandle()
    })
    $('#xadd_btn').click(function () {
        methods.xaddHandle()
    })

    $('#show_tbody').on('click', '.edit', function () {
        typeId = this.id
        trIndex = $('.edit', '#show_tbody').index($(this));
        addEnter = true;
        $(this).parents('tr').addClass('has_case');
        methods.editHandle(trIndex);
    })

})

var typeId,
    tdStr = '',
    trIndex,
    addEnter = true,
    hasNullMes = false,
    xtarInp = $('#xxztb input'),
    xtarSel = $('#xxztb select');

var methods = {

    addHandle: function (the_index) {
        hasNullMes = false;
        methods.checkMustMes();
        if (hasNullMes) {
            return;
        }
        if (addEnter) {
            var typeName = $('.blogtag_add').val().trim();
            $.ajax({
                type: "POST",
                url: "/mgn/blogtag/save",
                data: {
                    'typeName': typeName,
                },
                dataType: "text", //return dataType: text or json
                success: function (json) {
                    json = eval('(' + json + ')');
                    var typeId = json.obj.id;
                    var typeName = json.obj.typeName;
                    var typeIdTr = typeId * (-1);
                    var rtn = json.rtn;
                    if (rtn == "success") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "添加公告成功！",
                            closeButton: false
                        })
                        //拼接tr
                        tdStr = "<td>" + typeName + "</td>\n" +
                            "                <td>\n" +
                            "                    <a class='edit' id='" + typeId + "'>编辑</a>\n" +
                            "                    <a id='" + typeId + "' onclick='delBlogTag(this.id)'>删除</a>\n" +
                            "                </td>"
                        $('#show_tbody').append('<tr id=' + typeIdTr + '>' + tdStr + '</tr>');
                        //将input置空
                        $('#xztb input').val(' ');
                        $('#renyuan').modal('hide');
                    } else if (rtn == "fail") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "添加标签失败，请检查网络！",
                            closeButton: false
                        })
                        return
                    }
                },
                error: function (json) {
                    bootbox.alert({
                        title: "来自DirkBlog的提示",
                        message: "添加标签失败，请检查网络！",
                        closeButton: false
                    })
                    return
                }
            });
        }
    },
    xaddHandle: function (the_index) {
        hasNullMes = false;
        methods.xcheckMustMes();
        if (hasNullMes) {
            return;
        }
        if (addEnter) {
            var typeName = $('.blogtag_edit').val().trim();
            $.ajax({
                type: "POST",
                url: "/mgn/blogtag/save",
                data: {
                    'typeId': typeId,
                    'typeName': typeName,
                },
                dataType: "text", //return dataType: text or json
                success: function (json) {
                    json = eval('(' + json + ')');
                    var typeId = json.obj.id;
                    var typeName = json.obj.typeName;
                    var typeIdTr = typeId * (-1);
                    var rtn = json.rtn;
                    if (rtn == "success") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "修改标签成功！",
                            closeButton: false
                        })

                        //拼接tr
                        xtdStr = "<td>" + typeName + "</td>\n" +
                            "                <td>\n" +
                            "                    <a class='edit' id='" + typeId + "'>编辑</a>\n" +
                            "                    <a id='" + typeId + "' onclick='delBlogTag(this.id)'>删除</a>\n" +
                            "                </td>"
                        $('#show_tbody tr').eq(trIndex).empty().append(xtdStr);
                        $('#xrenyuan').modal('hide');
                    } else if (rtn == "fail") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "修改标签失败，请检查网络！",
                            closeButton: false
                        })
                        return
                    }
                },
                error: function (json) {
                    bootbox.alert({
                        title: "来自DirkBlog的提示",
                        message: "修改标签失败，请检查网络！",
                        closeButton: false
                    })
                    return
                }
            });
        }
    },
    checkMustMes: function () {

        //理由不能为空
        var typeName = $('.blogtag_add').val().trim()
        if (typeName === '') {
            bootbox.alert({
                title: "来自DirkBlog的提示",
                message: "标签不能为空",
                closeButton: false
            })
            hasNullMes = true;
            return
        }
    },
    xcheckMustMes: function () {

        //理由不能为空
        var typeName = $('.blogtag_edit').val().trim()
        if (typeName === '') {
            bootbox.alert({
                title: "来自DirkBlog的提示",
                message: "标签不能为空",
                closeButton: false
            })
            hasNullMes = true;
            return
        }
    },
    editHandle: function (the_index) {

        var tar = $('#show_tbody tr').eq(the_index);
        var nowConArr = [];
        for (var i = 0; i < tar.find('td').length - 1; i++) {
            var a = tar.children('td').eq(i).html();
            nowConArr.push(a);
        }

        $('#xrenyuan').modal('show');

        for (var j = 0; j < xtarInp.length; j++) {
            xtarInp.eq(j).val(nowConArr[j])
        }
        for (var p = 0; p < xtarSel.length; p++) {
            var the_p = p + xtarInp.length;
            xtarSel.eq(p).val(nowConArr[the_p]);
        }
    },
}