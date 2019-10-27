$(function () {  //jquery里的,是当文档载入完毕就执行的意思

    //新增关注--确认
    $('#add_btn').click(function () {
        methods.addHandle()
    })
    $('#xadd_btn').click(function () {
        methods.xaddHandle()
    })

    $('#show_tbody').on('click', '.edit', function () {
        qrId = this.id
        trIndex = $('.edit', '#show_tbody').index($(this));
        addEnter = true;
        $(this).parents('tr').addClass('has_case');
        methods.editHandle(trIndex);
    })

})

var qrId,
    tdStr = '',
    xtdStr = '',
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
            var qrImg = document.getElementById("file0").files[0];
            var formData = new FormData();
            formData.append("qrImg", qrImg)
            $.ajax({
                type: "POST",
                url: "/mgn/qrcode/save",
                data: formData,
                dataType: "text",
                processData: false,
                contentType: false,
                success: function (json) {
                    json = eval('(' + json + ')');
                    var qrId = json.obj.id;
                    var qrTr = qrId * (-1);
                    var qrUrl = json.obj.qrUrl;
                    var rtn = json.rtn;
                    if (rtn == "success") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "新增关注成功！",
                            closeButton: false
                        })
                        //拼接tr
                        tdStr = "<td>\n" +
                            "                    <img style='\n" +
                            "                    position: relative;\n" +
                            "                    top: 4px;\n" +
                            "                    display: inline;\n" +
                            "                    width: 34px;\n" +
                            "                    height: 34px;\n" +
                            "                    /*display: flex;*/\n" +
                            "                    align-items: center;\n" +
                            "                    justify-content: center;\n" +
                            "                    overflow: hidden;\n" +
                            "                    cursor: pointer;'\n" +
                            "                     class='dialog'\n" +
                            "                     alt='关注'\n" +
                            "                     src='"+qrUrl+"'>\n" +
                            "                </td>\n" +
                            "                <td>\n" +
                            "                    <a class='edit' id='"+qrId+"'>编辑</a>\n" +
                            "                    <a id='"+qrId+"' onclick='delQrCode("+qrId+")'>删除</a>"+
                            "                </td>"
                        $('#show_tbody').append('<tr id='+qrTr+'>' + tdStr + '</tr>');
                        //将input置空
                        $('#renyuan').modal('hide');
                    }else {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "新增失败，请检查网络！",
                            closeButton: false
                        })
                        return
                    }
                },
                error: function (json) {
                    bootbox.alert({
                        title: "来自DirkBlog的提示",
                        message: "新增失败，请检查网络！",
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
            var qrImg = document.getElementById("file1").files[0];
            var formData = new FormData();
            formData.append("qrId",qrId);
            formData.append("qrImg", qrImg)
            $.ajax({
                type: "POST",
                url: "/mgn/qrcode/save",
                data: formData,
                dataType: "text",
                processData: false,
                contentType: false,
                success: function (json) {
                    json = eval('(' + json + ')');
                    var qrId = json.obj.id;
                    var qrUrl = json.obj.qrUrl;
                    var rtn = json.rtn;
                    if (rtn == "success") {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "修改关注成功！",
                            closeButton: false
                        })
                        //拼接tr
                        xtdStr = "<td>\n" +
                            "                    <img style='\n" +
                            "                    position: relative;\n" +
                            "                    top: 4px;\n" +
                            "                    display: inline;\n" +
                            "                    width: 34px;\n" +
                            "                    height: 34px;\n" +
                            "                    /*display: flex;*/\n" +
                            "                    align-items: center;\n" +
                            "                    justify-content: center;\n" +
                            "                    overflow: hidden;\n" +
                            "                    cursor: pointer;'\n" +
                            "                     class='dialog'\n" +
                            "                     alt='关注'\n" +
                            "                     src='"+qrUrl+"'>\n" +
                            "                </td>\n" +
                            "                <td>\n" +
                            "                    <a class='edit' id='"+qrId+"'>编辑</a>\n" +
                            "                    <a id='"+qrId+"' onclick='delQrCode("+qrId+")'>删除</a>"+
                            "                </td>"
                        $('#show_tbody tr').eq(trIndex).empty().append(xtdStr);
                        //将input置空
                        $('#xrenyuan').modal('hide');
                    }else {
                        bootbox.alert({
                            title: "来自DirkBlog的提示",
                            message: "修改失败，请检查网络！",
                            closeButton: false
                        })
                        return
                    }
                },
                error: function (json) {
                    bootbox.alert({
                        title: "来自DirkBlog的提示",
                        message: "修改失败，请检查网络！",
                        closeButton: false
                    })
                    return
                }
            });
        }
    },
    checkMustMes: function () {
        var img0 = $('#img0').attr('src').trim();
        if(img0 == ""){
            bootbox.alert({
                title: "来自DirkBlog的提示",
                message: "尚未选择关注的二维码！",
                closeButton: false
            })
            hasNullMes = true;
            return
        }
    },
    xcheckMustMes: function () {
        var img1 = $('#img1').attr('src').trim();
        if(img1 == ""){
            bootbox.alert({
                title: "来自DirkBlog的提示",
                message: "尚未选择关注的二维码！",
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