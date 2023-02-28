function addToCart(digitalId){
	$.ajax({
		url:"CartServlet?action=add",
		dataType:"json", 
		async:true,
		data:{"digitalId":digitalId},
		type:"POST",
		success:function(data){
			$("#cart .num").html(data);
		}
			
	})
}



