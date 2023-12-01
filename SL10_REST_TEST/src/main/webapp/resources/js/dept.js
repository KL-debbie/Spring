console.log("Dept module");

var deptService = (function(){

	//js 함수 선언 : add() 부서 추가 + ajax
	function add(dept, callback, error){
		console.log("add dept");
		
		$.ajax({
			url:"/scott/dept/new" // HomeAjaxController.java
			, method:"POST"		// get(조회),post(수정),put(삽입),delete 등
			, data: JSON.stringify(dept) // js Object > json 변환
			, contentType:"application/json; charset=utf-8"
			// , dataType:"json"
			, cache : false
			, success : function (result,status, xhr) {
				if(callback){
				callback(result);
				}
			}
			, error: function(xhr, status, er) {
				if(error){
				error(er);
				}
			}
		});
		
	}
	
	//js 함수 선언 : remove() 부서 삭제 + ajax
	function remove(deptno, callback, error){
		console.log("remove dept" + deptno);
		
		$.ajax({
			url:"/scott/dept/"+deptno // HomeAjaxController.java
			, method:"DELETE"
			, success : function (result,status, xhr) {
				if(callback){
				callback(result);
				}
			}
			, error: function(xhr, status, er) {
				if(error){
				error(er);
				}
			}
		});
	}
	
	return {
		add : add
		, remove : remove
	}

})();