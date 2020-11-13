window.onload = function() {
   $(".item__check-btn").click((e) => {
      var btn = $(e.currentTarget);
      var postId = btn.attr("data-post-id");
      var status = btn.attr("data-status")
       var xhr = new XMLHttpRequest();
       xhr.open("PATCH", "/item/" + "status/"+ postId)
       xhr.send();
       xhr.responseType = "json"
       xhr.onload = function() {
           change(Boolean(xhr.response));
       }
       xhr.send();
       function change(state){
           state == false ? $("#"+ postId + "-status-button").text("-") : $("#"+ postId + "-status-button").text("+");
       }

   });

   $(".item__delete-btn").click((e) => {
         var btn = $(e.currentTarget);
         var postId = btn.attr("data-post-id");
         var xhr = new XMLHttpRequest();
         xhr.open("DELETE", "/item/"  + "delete/" + postId)
         xhr.send();
         // $.post("/item/" + postId + "/delete");
         $("#"+ postId + "-item").remove();
   });

   $("#item_add").click((e) => {
            $(e.currentTarget).css('display', 'none');
            $(".add-new-item-form").css('display', 'block');
      });
};