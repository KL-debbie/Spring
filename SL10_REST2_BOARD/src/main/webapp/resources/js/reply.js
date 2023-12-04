console.log("Reply module");

var replyService = (function (){

	function add(reply,callback, error){
		console.log("app reply");
		console.log("reply.bno");
		$.ajax({
			type : 'post'
			, url : `/replies/new`
			, data : JSON.stringify(reply)   //js Obj > JSON 변환
			, contentType : 'application/json; charset=utf-8'
			, cache : false
			, success : function(result, status, xhr){
				if(callback){
					callback(result)
				}//if
			},
			error : function(xhr, status, er){
				if(error) {
					error(er);
				}//if
			}
		}); //ajax
	}//add
	
	function getList(param, callback, error){
		var bno  = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page +".json",
			function(data){
			if(callback){
			// callback(data) //댓글 목록만 가져오기
				callback(data.replyCnt, data.list); // 댓글 숫자 + 목록
			}
		}).fail(function(xhr,status,err){
			if(error){
				error();
			}
		});
	}//getList
	
	function remove(rno, callback, error){
	
		$.ajax({
			url :'/replies/' + rno
			, type : 'delete'
			, success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult)
				}//if
			},
			error : function(xhr, status, er){
				if(error) {
					error(er);
				}//if
			}
			
		});
	}
	
	function update(reply, callback, error){
	
		$.ajax({
			type : 'put'
			, url : `/replies/update`
			, data : JSON.stringify(reply)   //js Obj > JSON 변환
			, contentType : 'application/json; charset=utf-8'
			, cache : false
			, success : function(result, status, xhr){
				if(callback){
					callback(result)
				}//if
			},
			error : function(xhr, status, er){
				if(error) {
					error(er);
				}//if
			}
		}); //ajax
	}
	
	function get(rno, callback, error) {

      $.get("/replies/" + rno + ".json", function(result) {

         if (callback) {
            callback(result);
         }//if

      }).fail(function(xhr, status, err) {
         if (error) {
            error();
         }//if
      });
   }
	
	function displayTime(timeValue){
		
		var today = new Date();
      var gap = today.getTime() - timeValue;
      var dateObj = new Date(timeValue);
      var str = "";

//      if (gap < (1000 * 60 * 60 * 24)) {
      if (gap < (1000 * 60 * 60 * 1)) { //한시간 지남유무
         var hh = dateObj.getHours();
         var mi = dateObj.getMinutes();
         var ss = dateObj.getSeconds();
         
         return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
               ':', (ss > 9 ? '' : '0') + ss ].join('');
      } else {
         var yy = dateObj.getFullYear();
         var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
         var dd = dateObj.getDate();

         return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
               (dd > 9 ? '' : '0') + dd ].join('');
      }
		
	}
	
	return {
		add : add
		, get : get
		, getList : getList
		, remove : remove
		, update : update
		, displayTime : displayTime
		};

})();