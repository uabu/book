$(function(){
	var form=$("#messageAddForm").Validform({
		tiptype:2,//validform初始化
	});

	form.addRule([
		{
			ele:"#title",
		    datatype:"*2-20",
		    ajaxurl:"jsp/admin/MsgServlet?action=find",
		    nullmsg:"请输入商品名称！",
		    errormsg:"商品名称至少2个字符,最多20个字符！"
		},
		{
			ele:"#book",
			datatype:"*",
			nullmsg:"请选择图书分类！",
			errormsg:"请选图书分类！"
		}
		]);
});

