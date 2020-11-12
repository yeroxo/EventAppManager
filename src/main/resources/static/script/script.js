window.onload = function() {
   $(".item__check-btn").click((e) => {
      var btn = $(e.currentTarget);
      var postId = btn.attr("data-post-id");
      $.post("/item/" + postId + "/status");
      $("#"+ postId + "-status-button").text() == '+' ? $("#"+ postId + "-status-button").text("-") : $("#"+ postId + "-status-button").text("+");
   });

   $(".item__delete-btn").click((e) => {
         var btn = $(e.currentTarget);
         var postId = btn.attr("data-post-id");
         $.post("/item/" + ($("#"+ postId + "-item").index() - 1) + "/delete");
         $("#"+ postId + "-item").remove();
   });

   $("#item_add").click((e) => {
            $(e.currentTarget).css('display', 'none');
            $(".add-new-item-form").css('display', 'block');
      });
};