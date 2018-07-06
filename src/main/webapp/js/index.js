layui.use(['element','laytpl'], function(){
    var element = layui.element;
    var laytpl = layui.laytpl;
    loadMenu(laytpl,function () {
        element.render('nav');
        $('.a-tab-add').on('click', function () {
            var val = $(this).data('val');
            var url = $(this).data('url');
            var id = $(this).data('id');
            var type = $(this).data('type');
            active[type] ? active[type].call(this,val,url,id) : '';
        });
    });

    var active = {
        tabAdd: function (val,url,id) {
            if(url === "#"){
                url = basePath+'/page/error';
            };
            var tabNum = ".layui-tab-title li[lay-id='"+ id +"']";
            if ( $(tabNum).length > 0 ) {

            } else{
                element.tabAdd('demo', {
                    title:val + '<i class="layui-icon layui-unselect layui-tab-close">&#x1006;</i>'
                    , content: '<iframe id='+ id + ' style="width:100%;height:800px;border:0;" src="'+basePath+ url +'"></iframe>'
                    , id:id
                });
                //增加点击关闭事件
                var r = $(".layui-tab-title").find("li");
                r.eq(r.length - 1).children("i").on("click", function () {
                    element.tabDelete("demo", $(this).parent("li").attr('lay-id'));
                }), element.tabChange("demo", r.length - 1);
                //element.init();
                //element.render('nav');
            }
            //跳转到最新添加的选项卡
            element.tabChange("demo",id);
        }
    }
});

function loadMenu(laytpl,cb) {
    $.ajax({
        url:basePath+'/menu/list',
        type:'get',
        success:function (res) {
            var data = {result:res};
            var gettpl = document.getElementById('menu_scr').innerHTML;
            laytpl(gettpl).render(data,function(html) {
                document.getElementById('layui-ul').innerHTML = html;
            });
            if (typeof cb === 'function') cb();
        }
    });

}