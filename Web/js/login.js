$(function(){
    $('#button').click(function(){
         $.ajax({
             type: "POST",
             url: "../login",
             data: {"mobile":$("#mobile").val(),"password":$("#password").val()},
             dataType: "json",
             success: function(data){
            	       console.log(data);
                      window.location.href = "index.html";
             }
         });
    });
});