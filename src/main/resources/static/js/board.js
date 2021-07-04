let index= {
    init: function () {
        $("#btn-save").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-delete").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.deleteById();
        });
        $("#btn-update").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.update();
        });
        $("#btn-reply-save").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.replySave();
        });
        $("#btn-like-save").on("click", () => {// function(){}, ()=> {} this를 바인딩하기 위해서
            this.likeUpdate();
            this.likeListUpdate();
        });

    },
    save: function () {
        // alert('user의 save함수 호출됨');
        let data={
            title:$("#title").val(),
            content:$("#content").val(),
        };
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("글쓰기가 완료되었습니다.");
            // console.log(resp)
            location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            alert("글 삭제가 완료되었습니다.");
            // console.log(resp)
            location.href = "/";

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $("#id").val();
        // alert('user의 save함수 호출됨');
        let data={
            title:$("#title").val(),
            content:$("#content").val(),
        };
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("글 수정이 완료되었습니다.");
            // console.log(resp)
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    replySave: function () {
        // alert('user의 save함수 호출됨');
        let data={
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content:$("#reply-content").val(),
        };
        console.log(data);
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("댓글작성이 완료되었습니다.");
            // console.log(resp)
            location.href = `/board/${data.boardId}`;

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    replyDelete: function (boardId,replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            alert("댓글삭제가 완료되었습니다.");
            // console.log(resp)
            location.href = `/board/${boardId}`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    likeUpdate: function (boardId,boardLike) {
        let id = $("#id").val();
        // alert('user의 save함수 호출됨');
        let data={
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            boardLike:boardLike,
        };
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "PUT",
            url: `/api/board/${boardId}/like`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            // console.log(resp)
            alert("나도 했졍")
            location.href = `/board/${data.boardId}`;
            let test = document.getElementById("like-button");
            test.textContent="♥";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    likeListUpdate: function () {
        let id = $("#id").val();
        // alert('user의 save함수 호출됨');
        let data={
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
        };
        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 json을 리턴해주면 서버가 자동으로 자바 오브젝트로 변환
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/like`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열로오는데
            // 생긴게 json이라면 => javascript 오브젝트로 변경 해줌
        }).done(function (resp) {
            // console.log(resp)
            alert("listUpdate "+data.userId);
            alert("List Update " + data.boardId);
            location.href = `/board/${data.boardId}`;
            let test = document.getElementById("like-button");
            test.textContent="♥";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
}

index.init()