// edit_notebook.js  与笔记有关的代码

/*
 * 封装与笔记本有关的操作方法 
 */

function loadNotebooksAction(){
	//console.log('loadNotebooksAction()');
	var url='notebook/list.do';
	var data={userId:getCookie('userId')};
	//console.log(data);
	//console.log(url);
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var list=result.data;
			console.log(list); 
			model.updateNotebooks(list);
		}
	});
}

/*
 * <li class="online">
	<a  class="checked">
	<i class="fa fa-book" title="online" rel="tooltip-bottom">
	</i> 默认笔记本</a></li>
 */
model.updateNotebooks=function(list){
	var template='<li class="online">'+
		'<a>'+
		'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+
		'[notebook.name]</a></li>';
	
	this.notebooks=list;
	//console.log(this);
	var ul=$('#notebooks').empty();
	for(var i=0;i<this.notebooks.length;i++){
		var notebook=this.notebooks[i];
		// id name
		var li=template.replace(
			'[notebook.name]', notebook.name);
		//在DOM对象上绑定数据 index
		li = $(li).data('index',i);
		ul.append(li);
	}
};















