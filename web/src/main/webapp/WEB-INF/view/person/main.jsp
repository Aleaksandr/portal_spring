<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr>


  <td class="menu">
    <div class="panel panel-default">
      <div class="panel-heading">Top news today</div>
      <ul class="list-group">
        <c:forEach var="nw" items="${newslist}">
          <a class="list-group-item" href="/portal/item_id.form?param=${nw.id}">${nw.title4menu}</a>
        </c:forEach>
      </ul>
      <form style="width: 49%" action="/portal/next.form" method="post">
        <button style="width: 100%" class="btn btn-default" id="prev">Prev</button>
        <input type="hidden" name="page" value="prev">
      </form>
      <form style="width: 49%" action="/portal/next.form" method="post">
        <button style="width: 100%" class="btn btn-default" id="next">Next</button>
        <input type="hidden" name="page" value="next">
      </form>
    </div>

  </td>

  <td class="item">

    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">${newsitem.title}</h3>
      </div>
      <div class="panel-body">

        <p>${newsitem.item}</p>

         <c:if test="${usertype eq '[admin]'}">
           <form action="/news/deletenews.form" method="post">
             <input type="hidden" name="comId" value="${comment.id}">
             <input class="btn btn-default" type="submit" name="deleteitem" value="Delete News">
           </form>
           <form action="/portal/correctitem.form" method="post">
             <input class="btn btn-default" type="submit" name="correctitem" value="Correct">
           </form>
         </c:if>
       </div>
     </div>

  </td>
</tr>
<tr>
  <td>
    <%--<c:if test="${usertype eq 'GUEST'}">
      <form style="margin-bottom: 4px" action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=LoginCommand.NAME%>">
        <input class="btn btn-default" type="submit" name="login" value="Sign In">
      </form>
    </c:if>
    <c:if test="${usertype eq 'GUEST'}">
      <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=RegisterCommand.NAME%>">
        <input class="btn btn-default" type="submit" name="register" value="Register">
      </form>
    </c:if>--%>
    <c:if test="${usertype eq '[user]' || usertype eq '[admin]'}">
      <form style="margin-bottom: 4px" action="/news/addnewsform.form" method="post">
        <input class="btn btn-default" type="submit" name="addnews" value="Add News">
      </form>
    </c:if>
    <c:if test="${usertype eq '[user]' || usertype eq '[admin]'}">
      <form action="/portal/usersetting.form" method="post">
        <input class="btn btn-default" type="submit" name="userSetting" value="User setting">
      </form>
    </c:if>
    <c:if test="${usertype eq '[user]' || usertype eq '[admin]'}">
      <form action="/portal/addcomment.form" method="post">
        <input type="hidden" name="newsIdForComment" value="${nw.id}">
        <input class="btn btn-default" type="submit" name="addcomment" value="Add comment">
      </form>
    </c:if>
  </td>
  <td>
     <blockquote>
       <ul class="list-unstyled">
         <c:forEach var="comment" items="${commentlist}">
           <li>
             <h5><p>${comment.comment}</p></h5>
             <footer>

               <c:if test="${usertype eq '[admin]'}">
               <form action="/portal/delcomment.form" method="post">
                 <input type="hidden" name="comId" value="${comment.id}">
                 <input class="btn btn-default" type="submit" name="deletecomment"
                        value="Delete comment">
               </form>
               <form action="/portal/correctcomment.form" method="post">
                 <input type="hidden" name="comId" value="${comment.id}">
                 <input class="btn btn-default" type="submit" name="correctcomment"
                        value="Correct">
               </form>
             </c:if>

               <p class="text-right">
                 <c:forEach var="comuser" items="${userlist}">
                   <c:if test="${comment.user_id == comuser.id}">
                     ${comuser.userDetail}
                   </c:if>
                 </c:forEach>
               </p>
             </footer>
           </li>
         </c:forEach>
       </ul>
     </blockquote>

  </td>
</tr>
