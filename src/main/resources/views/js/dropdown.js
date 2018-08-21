//最后需要执行的方法
function lastExeFunc(){
	/* Nomenclature **************/
	var icon=$('.nav li div.icon');
	var text=$('.nav li div.text');
	
	/* Default values ***********/
	$('ul.menu').hide();
	$(text).hide();
	
	
	/* Mouse over on (.nav li)****/
	$('.nav li').hover(
	function(){
		/* Nomenclature **************/
		var tOpenMenu= $(this).children('ul.menu');
		var tIcon=$(this).children('.icon');
		var tText=$(this).children('.text');
		
		/*Open menu and show icon*/
		$(tOpenMenu).show();
		$(tText).show()
		$(tIcon).hide();
	},
	/* Mouse leave****/
	function(){
		/* Nomenclature **************/
		var tOpenMenu= $(this).children('ul.menu');
		var tIcon=$(this).children('.icon');
		
		/*Close menu and hide icon*/
		$(tOpenMenu).hide();
		$(tIcon).show();
		
	});
	
	/* Mouse over on (.menu li)****/
	$('.menu li').hover(
	function(){
		/* Nomenclature **************/
		var tSubMenu=$(this).children('.menu');
		
		/*Open sub menu*/
		$(tSubMenu).css({
			'left':'100%',
			'top':'-25%'
		})
	});
}
/*

<!--   三级目录 -->
<li>
<div class="text"><a>Portfolio</a></div>
<div class="icon"><i class="icon-user" ></i></div>
<ul class="menu">
  <li><a href="#">sample 1</a></li>
  
  <!-- Start Portfolio 2 -->
  <li><a>二级目录二级<i class="icon-chevron-right"></i></a>
    <ul class="menu">
      <li><a href="#">Page 1</a></li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </li>
  <li ><a href="#">sample 3</a></li>
</ul>
</li>

<!--   二级目录 -->
<li>
<div class="text"><a>Portfolio</a></div>
<div class="icon"><i class="icon-user" ></i></div>
<ul class="menu">
  <li><a href="#">sample 1</a></li>
  <li ><a href="#">sample 3</a></li>
</ul>
</li>

<!--   一级目录 -->
<li>
    <div class="text"><a>tttt</a></div>
    <div class="icon"><i class="icon-columns" ></i></div>
</li>


<!--   最右侧的署名 -->
   <div class="navbar-right">  
   	<P class="text-info">&nbsp;天气情况：{{myWeather}}</P>
   	<B class="text-info">&nbsp;当前账号：{{myName}}</B>
   </div> 
<!--    退出登录按钮 -->
   <a class="navbar-right" href="/gotoLogoutPage">
	   <button type="button" class="btn btn-primary btn-lg ">  
	      <span class="glyphicon glyphicon-off"></span> 安全退出   
	   </button> 
   </a>
  
*/

//组装导航 菜单的方法
function generalNav(O,myName,myWeather){
	
	/* {"id":1,
	"pId":0,
	"name":"name1",
	"path":"path"}
	*/
	
//	 			alert(O.length);
//	 			alert(O[0]);//jquery解析json格式数据为string
//	 			alert(JSON.stringify(O));//jquery解析json格式数据为string
//	 			alert(O[0].name);
		var navIcon=new Array("icon-home","icon-user","icon-columns","icon-th-list","icon-th-large","icon-table","icon-cog");//每个一级导航的图标
		var menu1index = 1;var manu1 = new Array();//一级目录数组
		var menu2index = 0;var manu2 = new Array();//二级目录数组
		var menu3index = 0;var manu3 = new Array();//三级目录数组
		
		
		manu1[0]='<li id="0"><div class="text"><a href="/">返回首页</a></div> <div class="icon"><i class="'+navIcon[0]+'"></i></div></li>';
		for(var i = 0;i<O.length;i++){
			var len = O[i].id.toString().length;
			if(len == 1){
				manu1[menu1index++]='<li id="'+O[i].id+'"><div class="text"><a>'+O[i].name+'</a></div><div class="icon"><i class="'+navIcon[10]+'" ></i></div></li>';
			}
			if(len == 2){
				manu2[menu2index++]='<li id="'+O[i].id+'"><a href="'+O[i].path+'">'+O[i].name+'</a></li>';
			}
			if(len == 3){
				manu3[menu3index++]='<li id="'+O[i].id+'"><a href="'+O[i].path+'">'+O[i].name+'</a></li>';
			}
		}
		
		var subNode = '<ul class="menu"></ul>';
		var rightArrowNode = '<i class="icon-chevron-right">';
		
//	 	构建一级目录
		for(var i = 0;i<manu1.length;i++){
			$("#id_nav").append(manu1[i]);
			
			if(i>=1){
				var id = $(manu1[i]).attr("id");
				$("li#"+id).append(subNode);
				$("li#"+id+" div i").addClass(navIcon[id]);
			}
		}
		$("#id_nav").append('<div class="navbar-right"><P class="text-info">&nbsp;天气情况：'+myWeather+'</P><B class="text-info">&nbsp;当前账号：'+myName+'</B></div>');
		$("#id_nav").append('<a class="navbar-right" href="/gotoLogoutPage"><button type="button" class="btn btn-primary btn-lg "><span class="glyphicon glyphicon-off"></span> 安全退出</button></a>');
		  
		   
		   
		
//		构建二级目录
		for(var i = 0;i<manu2.length;i++){
			var id = $(manu2[i]).attr("id");
			var pid = id.substring(0,1);
			$("li#"+pid+" ul.menu").append(manu2[i]);
		}
		
//		构建三级目录
		for(var i = 0;i<manu3.length;i++){
			var id = $(manu3[i]).attr("id");
			var pid = id.substring(0,2);
			if($("li#"+pid+" a[href]").children().length==0){
				$("li#"+pid+" a[href]").append(rightArrowNode);
				$("li#"+pid).append(subNode);
			}
			
			$("li#"+pid+" ul.menu").append(manu3[i]);
		}
}