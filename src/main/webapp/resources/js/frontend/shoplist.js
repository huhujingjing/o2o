$(function () {
    var loading = false;
    //分页允许返回的最大条数，超过此数禁止访问后台
    var maxItems = 999;
    //一页返回的最大条数
    var pageSize = 3;
    //获取店铺列表的URL
    var listUrl = '/o2o/frontend/listshops';
    //获取店铺类别表以及区域列表的URL
    var searchDivUrl = '/o2o/frontend/listshopspageinfo';
    //页码
    var pageNum = 1;
    //从地址栏URL里尝试获取parent shop category id
    var parentId = getQueryString('parentId');
    var areaId = '';
    var shopCategoryId = '';
    var shopName = '';

    //渲染出店铺类别列表以及区域列表以供搜索
    function getSearchDivData() {
        var url = searchDivUrl + '?' + 'parentId=' + parentId;
        $.getJSON(
            url,
            function (data) {
                if (data.success) {
                    var shopCategoryList = data.shopCategoryList;
                    var html = '';
                    html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
                    shopCategoryList
                        .map(function (item, index) {
                            html += '<a href="#" class="button" data-category-id='
                                + item.shopCategoryId
                                + '>'
                                + item.shopCategoryName
                                + '</a>';
                        });
                    $('#shoplist-search-div').html(html);
                    var selectOptions = '<option value="">全部街道</option>';
                    var areaList = data.areaList;
                    areaList.map(function (item, index) {
                        selectOptions += '<option value="'
                            + item.areaId + '">'
                            + item.areaName + '</option>';
                    });
                    $('#area-search').html(selectOptions);
                }
            });
    }

    getSearchDivData();

    function addItems(pageSize, pageIndex) {
        // 生成新条目的HTML
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&parentId=' + parentId + '&areaId=' + areaId
            + '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;
        loading = true;
        $.getJSON(url, function (data) {
            if (data.success) {
                maxItems = data.count;
                var html = '';
                data.shopList.map(function (item, index) {
                    html += '' + '<div class="card" data-shop-id="'
                        + item.shopId + '">' + '<div class="card-header">'
                        + item.shopName + '</div>'
                        + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.shopImg + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.shopDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + '<p class="color-gray">'
                        + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        + '</div>';
                });
                //将卡片集合添加到目标HTML组件里
                $('.list-div').append(html);
                //获取目前为止已显示的卡片总数，包含之前已经加载的
                var total = $('.list-div .card').length;
                //若总数达到跟按照此查询条件列出来的总数一致，则停止后台的加载
                if (total >= maxItems) {
                    // 加载完毕，则注销无限加载事件，以防不必要的加载
                    // $.detachInfiniteScroll($('.infinite-scroll'));
                    // 删除加载提示符
                    $('.infinite-scroll-preloader').hide();
                } else {
                    $('.infinite-scroll-preloader').show();
                }
                //否则页码加1，继续load出新的店铺
                pageNum += 1;
                //加载结束，可以再次加载了
                loading = false;
                //刷新页面，显示新加载的店铺
                $.refreshScroller();
            }
        });
    }

    // 预先加载20条
    addItems(pageSize, pageNum);
    //下滑屏幕自动进行分页搜索
    $(document).on('infinite', '.infinite-scroll-bottom', function () {
        if (loading)
            return;
        addItems(pageSize, pageNum);
    });
    //点击店铺的卡片进入该店铺的详情页
    $('.shop-list').on('click', '.card', function (e) {
        var shopId = e.currentTarget.dataset.shopId;
        window.location.href = '/o2o/frontend/shopdetail?shopId=' + shopId;
    });
    //选择新的店铺类别之后，重置页码，清空原先的店铺列表，按照新的类别去查询
    $('#shoplist-search-div').on(
        'click',
        '.button',
        function (e) {
            if (parentId) {// 如果传递过来的是一个父类下的子类
                shopCategoryId = e.target.dataset.categoryId;
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    shopCategoryId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
            } else {// 如果传递过来的父类为空，则按照父类查询
                parentId = e.target.dataset.categoryId;
                if ($(e.target).hasClass('button-fill')) {
                    $(e.target).removeClass('button-fill');
                    parentId = '';
                } else {
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
                parentId = '';
            }

        });

    $('#search').on('change', function (e) {
        shopName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    $('#area-search').on('change', function () {
        areaId = $('#area-search').val();
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageSize, pageNum);
    });

    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $.init();
});
