$.ajax({
	url:"ShopIndex",
	dataType:"json",
	async:true,
	data:{},
	type:"POST",
	success:function(data){
		datalist(data);
	}
		
})

function datalist(data){
	
	
	//推荐商品
	if(data.recDigitals!=null){
		$.each(data.recDigitals,function(i,n){ 
			var tag="<li class='col-md-3'><div class='list'>" +
			"<a href='digitaldetail?digitalId="+n.digitalId+"'><img class='img-responsive' src='"+n.upLoadImg.imgSrc+"'/></a>"+
			"<div class='proinfo'><h2><a class='text-center' href='digitaldetail?digitalId="+n.digitalId+"'>"+n.digitalName+"</a></h2>"+
			"<p><i>￥"+n.price+"</i><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart("+n.digitalId+")' " +
				"data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";
			
			$("#recDigitals ul").append(tag);
		})
	}
	
	//新增加的书
	if(data.newDigitals!=null){
		$.each(data.newDigitals,function(i,n){ 
			var tag="<li class='col-md-3'><div class='list'>" +
			"<a href='digitaldetail?digitalId="+n.digitalId+"'><img class='img-responsive' src='"+n.upLoadImg.imgSrc+"'/></a>"+
			"<div class='proinfo'><h2><a class='text-center' href='digitaldetail?digitalId="+n.digitalId+"'>"+n.digitalName+"</a></h2>"+
			"<p><i>￥"+n.price+"</i><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart("+n.digitalId+")' " +
				"data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";
			
			$("#newDigitals ul").append(tag);
			
		})
	}
	
	
}
