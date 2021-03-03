/** layuiAdmin.pro-v1.4.0 LPPL License By https://www.layui.com/admin/ */
;
layui.define(function(e) {
	
	layui.use("table",
	function() {
		var e = (layui.$, layui.table);
		e.render({
			elem: "#LAY-index-topSearch",
			url: "/todo.js",
			height : 'full-220',
			page: !0,
			cols: [[{
				type: "numbers",
				fixed: "left"
			},
			{
				field: "title",
				title: "标题",
				minWidth: 300,
				templet: '<div><a href="{{ d.href }}" target="_blank" class="layui-table-link">{{ d.title }}</div>'
			},
			{
				field: "username",
				title: "发送人"
			},
			{
				field: "channel",
				title: "类别"
			},
			{
				field: "approvedate",
				title: "送达日期",
			}]],
			skin: "line"
		}),
		e.render({
			elem: "#LAY-index-topCard",
			url: "/hand.js",
			height : 'full-220',
			page: !0,
			cellMinWidth: 120,
			cols: [[{
				type: "numbers",
				fixed: "left"
			},
			{
				field: "title",
				title: "标题",
				minWidth: 300,
				templet: '<div><a href="{{ d.href }}" target="_blank" class="layui-table-link">{{ d.title }}</div>'
			},
			{
				field: "username",
				title: "发送人"
			},
			{
				field: "channel",
				title: "类别"
			},
			{
				field: "approvedate",
				title: "送达日期",
			}]],
			skin: "line"
		})
	}),
	e("console", {})
});