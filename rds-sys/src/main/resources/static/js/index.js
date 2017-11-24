var secondMenuCache = {};
var thirdMenuCache = {};

function mainmenu_over(obj) {
    obj.className = "mainmenuover";
}

function mainmenu_out(obj) {
    obj.className = "mainmenu";
}

function secondmenu_over(obj) {
    obj.className = "secondmenuover";
}

function secondmenu_out(obj) {
    obj.className = "secondmenu";
}

/**
 * 处理异常
 * @param result {"code":200,"msg":"发送位置错误"}
 */
function dealException(result) {
    $.messager.alert({
        title: '警告',
        msg: result.msg,
        fn: function () {

        }
    });
}

function checkData(data) {
    if (data && data.resultCode == "SUCCESS") {
        return true;
    }
    return false;
}

/**
 * 错误提示框
 * @param result {"code":200,"msg":"发送位置错误"}
 */
function errorAlert(msg) {
    $.messager.alert({
        title: '警告',
        msg: msg,
        fn: function () {

        }
    });
}

/**
 * 删除选项卡
 */
function removePanel() {
    var tab = $('#tabs').tabs('getSelected');
    if (tab) {
        var index = $('#tabs').tabs('getTabIndex', tab);
        $('#tabs').tabs('close', index);
    }
}

/**
 * 退出登录
 */
function logout() {
    $.ajax({
        type: "GET",
        url: "/logout",
        success: function (data) {
            if (data.code == 200) {
                window.location.href = "/login.html";
            }
        }
    });
}

function removeTab(text) {
    var tabs = $("#tabs");
    var tab = tabs.tabs("getTab", text);
    if (tab) {
        var index = $('#tabs').tabs('getTabIndex', tab);
        $('#tabs').tabs('close', index);
    }
}

function addTab(text, url) {
    var tabs = $("#tabs");
    var tab = tabs.tabs("getTab", text);
    if (tab) {
        tabs.tabs("select", text);
    } else {
        tabs.tabs('add', {
            title: text,
            href: url,
            closable: true,
            bodyCls: "content",
            border: false,
            onLoad: function (panel) {
                //$.messager.alert({
                //    title: 'My Title',
                //    msg: 'Here is a message!',
                //    fn: function(){
                //        debugger;
                //        window.location.href = "/";
                //    }
                //});
            }
        });
    }
}

/**
 * 点击3级菜单
 * @param id
 * @param text
 * @param url
 */
function onClickThirdMenu(id, text, url) {
    addTab(text, url);
}

/**
 * 加载3级菜单
 * @param id
 * @param data
 */
function loadThirdMenu(id, data) {
    //清空菜单
    $('#thirdMenu-' + id).empty();

    if (data.length % 2 == 0) {
        for (var i = 0; i < data.length; i += 2) {
            var tr =
                '<tr>' +
                '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i].id + ',\'' + data[i].name + '\',\'' + data[i].url + '\')">' +
                '<div style="width:100%;text-align:center;padding-top:6px;">' +
                '<img src="' + data[i].icon + '" width="32" height="32">' +
                '</div>' +
                '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i].name + '</div>' +
                '</td>' +
                '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i + 1].id + ',\'' + data[i + 1].name + '\',\'' + data[i + 1].url + '\')">' +
                '<div style="width:100%;text-align:center;padding-top:6px;">' +
                '<img src="' + data[i + 1].icon + '" width="32" height="32">' +
                '</div>' +
                '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i + 1].name + '</div>' +
                '</td>' +
                '<tr/>';

            $('#thirdMenu-' + id).append(tr);
        }
    } else {
        for (var i = 0; i < data.length - 1; i += 2) {
            var tr =
                '<tr>' +
                '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i].id + ',\'' + data[i].name + '\',\'' + data[i].url + '\')">' +
                '<div style="width:100%;text-align:center;padding-top:6px;">' +
                '<img src="' + data[i].icon + '" width="32" height="32">' +
                '</div>' +
                '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i].name + '</div>' +
                '</td>' +
                '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i + 1].id + ',\'' + data[i + 1].name + '\',\'' + data[i + 1].url + '\')">' +
                '<div style="width:100%;text-align:center;padding-top:6px;">' +
                '<img src="' + data[i + 1].icon + '" width="32" height="32">' +
                '</div>' +
                '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[i + 1].name + '</div>' +
                '</td>' +
                '<tr/>';

            $('#thirdMenu-' + id).append(tr);
        }
        var tr =
            '<tr>' +
            '<td class="secondmenu" onmouseover="secondmenu_over(this)" onmouseout="secondmenu_out(this)" onclick="onClickThirdMenu(' + data[i].id + ',\'' + data[i].name + '\',\'' + data[i].url + '\')">' +
            '<div style="width:100%;text-align:center;padding-top:6px;">' +
            '<img src="' + data[i].icon + '" width="32" height="32">' +
            '</div>' +
            '<div style="width:100%;text-align:center;padding-top:4px;padding-bottom:4px;">' + data[data.length - 1].name + '</div>' +
            '</td>' +
            '<td class="secondmenu">' +
            '</td>' +
            '<tr/>';

        $('#thirdMenu-' + id).append(tr);
    }
}

/**
 * 点击2级菜单
 * @param id
 */
function onClickSecondMenu(id) {
    //从缓存加载3级菜单
    if (thirdMenuCache['' + id] != null) {
        loadThirdMenu(id, thirdMenuCache['' + id]);
        return;
    }

    //从网络加载3级菜单
    $.ajax({
        type: "GET",
        url: "/menu/" + id + "/children",
        success: function (data) {
            if (data.code == 200) {
                //加入缓存
                thirdMenuCache['' + id] = data.data;

                loadThirdMenu(id, data.data);
            }
        }
    });
}

/**
 * 加载2级菜单
 * @type {Array}
 */
var secondMenuIds = new Array();

function loadSecondMenu(data) {
    //清空选择事件，防止在移除时触发选择事件
    $('#secondMenu').accordion({
        onSelect: function (title, index) {

        }
    });

    //清空菜单
    for (var i = secondMenuIds.length - 1; i >= 0; i--) {
        $('#secondMenu').accordion('remove', i);
    }

    //初始化选择事件
    $('#secondMenu').accordion({
        onSelect: function (title, index) {
            onClickSecondMenu(secondMenuIds[index]);
        }
    });

    secondMenuIds = new Array();
    for (var i = 0; i < data.length; i++) {
        //缓存2级菜单的id
        secondMenuIds[i] = data[i].id;

        //新增2级菜单
        $('#secondMenu').accordion('add', {
            title: data[i].name,
            content: '<table id="thirdMenu-' + data[i].id + '" width="100%" cellpadding="2" cellspacing="5"></table>',
            selected: false
        });
    }

    //默认选择第1个
    //$('#secondMenu').accordion('select', 0);
}

/**
 * 点击1级菜单
 * @param id
 */
function onClickTopMenu(id) {
    //从缓存加载2级菜单
    if (secondMenuCache['' + id] != null) {
        loadSecondMenu(secondMenuCache['' + id]);
        return;
    }

    //从网络加载2级菜单
    $.ajax({
        type: "GET",
        url: "/menu/" + id + "/children",
        success: function (data) {
            if (data.code == 200) {
                //加入缓存
                secondMenuCache['' + id] = data.data;

                loadSecondMenu(data.data);
            }
        }
    });
}

function initChats1() {
    $.ajax({
        type: "GET",
        url: "/sys/logs/pv",
        success: function (data) {
            if (data.code == 200) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main1'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: 'PV 统计'
                    },
                    tooltip: {},
                    legend: {
                        data: ['PV']
                    },
                    xAxis: {
                        data: data.data.xList
                    },
                    yAxis: {},
                    series: [{
                        name: 'PV',
                        type: 'bar',
                        data: data.data.yList
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        }
    });
}

function initChats2() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main2'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function initChats3() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main3'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '未来一周气温变化',
            subtext: '纯属虚构'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['最高气温', '最低气温']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} °C'
            }
        },
        series: [
            {
                name: '最高气温',
                type: 'line',
                data: [11, 11, 15, 13, 12, 13, 10],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '最低气温',
                type: 'line',
                data: [1, -2, 2, 5, 3, 2, 0],
                markPoint: {
                    data: [
                        {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'},
                        [{
                            symbol: 'none',
                            x: '90%',
                            yAxis: 'max'
                        }, {
                            symbol: 'circle',
                            label: {
                                normal: {
                                    position: 'start',
                                    formatter: '最大值'
                                }
                            },
                            type: 'max',
                            name: '最高点'
                        }]
                    ]
                }
            }
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function initChats4() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main4'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '某地区蒸发量和降水量',
            subtext: '纯属虚构'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['蒸发量', '降水量']
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '蒸发量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '降水量',
                type: 'bar',
                data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint: {
                    data: [
                        {name: '年最高', value: 182.2, xAxis: 7, yAxis: 183},
                        {name: '年最低', value: 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


/**
 * 页面初始化
 */
$(function () {
    //加载当前用户的1级菜单
    $.ajax({
        type: 'GET',
        url: '/menu/-1/children',
        success: function (data) {
            if (data.code == 200) {
                //清空菜单
                //$('#topMenu').empty();

                for (var i = data.data.length - 1; i >= 0; i--) {
                    var div =
                        '<div class="mainmenu" onclick="onClickTopMenu(' + data.data[i].id + ')" onmouseover="mainmenu_over(this)" onmouseout="mainmenu_out(this)">' +
                        '<div style="margin:10px 5px 10px 5px">' +
                        '<img src="' + data.data[i].icon + '" width="35" height="35"/>' +
                        '</div>' +
                        '<div style="font-size:13px">' + data.data[i].name + '</div>' +
                        '</div>';
                    $("#topMenu").append(div);
                }

                //默认选择第一个
                onClickTopMenu(data.data[data.data.length - 1].id);
            }
        }
    });

    //默认收起右侧面板
    $("div.easyui-layout").layout('collapse', 'east');

    initChats1();
    initChats2();
    initChats3();
    initChats4();
});

