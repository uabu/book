$.ajax({
	url:"GetCatalog",
	dataType:"json", 
	async:true,
	data:{},
	type:"POST",
	success:function(data){
		//分类信息
		if(data.catalog!=null){
			$.each(data.catalog,function(i,n){
				$("#catalog-list").append("<li><a href='DigitalList?catalogId="+n.catalogId+"'>"+n.catalogName+"("+n.catalogSize+")</a></li>");
			})
		}
		
	}
		
});
$.ajax({
    url:"MsgCatalog",
    dataType:"json",
    async:true,
    data:{},
    type:"POST",
    success:function(data){
        //分类信息
        if(data.messageList!=null){
            $.each(data.messageList,function(i,n){
            	  $("#msg-list").append("<li><a  href='MsgServlet?id="+n.id+"&ids=111'>"+n.title+"</a></li>");
            })
        }

    }

})

