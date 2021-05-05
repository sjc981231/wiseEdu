<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file="../common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
  
  <link rel="stylesheet" type="text/css" href="${basePath}/themes/material/easyui.css">
  <link rel="stylesheet" type="text/css" href="${basePath}/themes/icon.css">

  <script type="text/javascript" src="${basePath}/default/jquery.min.js"></script>
  <script type="text/javascript" src="${basePath}/default/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${basePath}/js/util.js"></script>
  <script src="${basePath}/locale/easyui-lang-zh_CN.js"></script> 
  
    
    <link href="${basePath}/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" >


    <script type="text/javascript" src="${basePath}/ztree/jquery.ztree.all.min.js"></script>

    <script type="text/javascript" src="${basePath}/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${basePath}/js/util.js"></script>
<script type="text/javascript">
	$(function(){
		$("#quesList").datagrid({
			url : "quesFindByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "quesId",
			rownumbers : true,
			//singleSelect:true,
			columns : [[
				{field:"quesId",title:"选择",checkbox:true},
				{field:"quesIntro",title:"题目简介",sortable:true,width:20},
				{field:"level",title:"难度",width:15},
				{field:"score",title:"分数",width:20},
				{field:"type",title:"题目类型",formatter:function(value,rowData,index){
					if(value == 1){
						return "填空题";
					}else if(value == 2){
						return "选择题";
					}else if(value == 3){
						return "简答题";
					}else{
						return "";
					}
				}},
				{field:"status",title:"题目状态",formatter:function(value,rowData,index){
					if(value == 1){
						return "可用";
					}else if(value == 0){
						return "禁用";
					}else if(value == 2){
						return "已删除";
					}else{
						return "";
					}
				}},
				{field:"opt",title:"操作",width:30,formatter:function(value,rowData,index){
					var btn='<a href="javascript:void(0);" onclick="return detail(\'quesDetail.do\','+index+')"'
					+' class="easyui-linkbutton" data-options="iconCls:\'icon-add\',plain:true">详情</a>';
							console.log(btn);
					return btn;
				}}
				
			]],
			loadFilter:function(data){
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {"total":data.total,"rows":data.rows};
			}
		});
		
		setTimeout(function(){
			$.parser.parse(); 
			}, 100);

		
	})
	
	function add(url){
		//调用父页面的弹出页面的方法
		parent.openTopWindow({
			width:700,
			height:500,
			title:"添加用户",
			url:url,
			close:function(){
				$("#quesList").datagrid("reload");
				setTimeout(function(){
					$.parser.parse(); 
					}, 100);
			}
		});
	}
	
	function detail(url,index){
		$('#quesList').datagrid('selectRow',index);
		var row= $("#quesList").datagrid("getSelected");
		var id = row.quesId;
		var type=row.type;
		var result="?id="+id+"&?type="+type;
		console.log(result);
		//调用父页面的弹出页面的方法
		parent.openTopWindow({
			width:700,
			height:500,
			title:"题目详情",
			url:url+"?id="+id+"&type="+type,
			close:function(){
				$("#quesList").datagrid("reload");
				setTimeout(function(){
					$.parser.parse(); 
					}, 400);
			},
			isScrolling:"yes"
		});

	}
	
	function del(url){
		//获取选中的记录的条数数组
		var rows = $("#quesList").datagrid("getChecked");
		if(rows.length == 0){
			$.messager.alert("警告","必须选中一条要删除的记录！");
			return;
		}
		if(rows.length >= 1){
			$.messager.confirm("警告","数据删除将无法恢复，确认删除选中的信息吗?",function(b){
				if(b){
					var ids = new Array();
					$.each(rows,function(index,obj){
						ids.push(obj.quesId);
					});
					//以逗号进行分割
					ids = ids.join(",");
					//通过ajax提交删除操作
					$.post(
						url,
						{"ids":ids},
						function(data){
							alert(data.msg);
							//删除成功之后，刷新列表页面
							if(data.success){
								$("#quesList").datagrid("reload");
								return;
							}
						},
						"json"
					);
				}
			});
		}
	}
	
	function edit(url){
		//选中修改的记录
		var rows = $("#quesList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录修改！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var id = rows[0].quesId;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"修改问题",
				url:url+"?id="+id,
				close:function(){
					$("#quesList").datagrid("reload");
					setTimeout(function(){
						$.parser.parse(); 
						}, 100);
				}
			});
		}
	}
	
	function assign(url){
		//选中修改的记录
		var rows = $("#quesList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录！");
			return;
		}else{
			var id = rows[0].quesId;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"分配知识点",
				url:url+"?id="+id,
				close:function(){
					$("#quesList").datagrid("reload");
					setTimeout(function(){
						$.parser.parse(); 
						}, 100);
				},
				isScrolling:"yes"
			});
	
		}
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('addQues.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> <a
			href="javascript:void(0);"
			onclick="return del('delQues.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> <a
			href="javascript:void(0);"
			onclick="return edit('updateQues.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a
			href="javascript:void(0);"
			onclick="return assign('assignKnow.do')"
			id="setBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-man',plain:true">分配知识点</a>
	</div>
	<table id="quesList"></table>
</body>
</html>