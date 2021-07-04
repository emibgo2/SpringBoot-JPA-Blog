<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id==principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br><br>
    <div>
        글 번호:<span id="id"><i>${board.id} </i></span> <div class="float-right">조회수: ${board.count}</div>
        작성자 :<span><i>${board.user.nickName} </i></span>
    </div>
    <h3>${board.title}</h3>
    <hr>
    <div>
        ${board.content}
    </div>
    <hr>
    <div>
        <%--            type="button" id="btn-like-save"--%>
<%--        <c:set var="like2" value="${board.boardLikes}">--%>
<%--            <c:if test="${like2.user.id!=principal.user.id}"  >--%>
<%--                <button class="btn btn-white" onClick="index.likeUpdate(${board.id},${board.boardLike}), index.likeListUpdate()" id="like-button"><h5>♡</h5></button>&#160: ${board.boardLike}--%>
<%--            </c:if>--%>
<%--        </c:set>--%>
            <c:if test="${like.user.id==principal.user.id}"  >
                <button class="btn btn-white" onClick="index.likeUpdate(${board.id},${board.boardLike}), index.likeListUpdate()" id="like-button"><h5>♥</h5></button>&#160: ${board.boardLike}
            </c:if>
            <button class="btn btn-white" onClick="index.likeUpdate(${board.id},${board.boardLike}), index.likeListUpdate()"><h5>♡</h5></button>&#160: ${board.boardLike}

            <hr>
    <c:forEach var="like" items="${board.boardLikes}">
        ${like.user.nickName},${like.user.id}

    </c:forEach>

</div>
    <hr>
    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}">
            <input type="hidden" id="boardId" value="${board.id}">


            <div class="card-body">
                Comment<textarea id="reply-content" class="form-control"  rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                        <c:if test="${board.user.id==principal.user.id}">
                            <button onClick="index.replyDelete(${board.id},${reply.id})" class="badge">삭제</button>
                        </c:if>
                    </div>
                </li>

            </c:forEach>

        </ul>
    </div>



</div>

<script src="/js/board.js"></script>


<%@include file="../layout/footer.jsp"%>