$.ajax({
	url:"GetBook",
	dataType:"json", 
	async:true,
	data:{},
	type:"POST",
	success:function(data){
		//分类信息
		if(data.book!=null){
			$.each(data.book,function(i,n){
				$("#book-list").append("<li><a href='MsgList?bookId="+n.bookId+"'>"+n.bookName+"("+n.bookSize+")</a></li>");
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
            	  $("#msg1-list").append("<li><a  href='MsgServlet?chapters="+n.chapters+"&ids=111'>"+n.title+"</a></li>");
            })
        }

    }

})