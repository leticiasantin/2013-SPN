<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<jsp:useBean id="category" class="br.uel.entity.Category"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
        <script  src="js/jquery.js"></script>
        <script  type="text/javascript">
            $(document).ready(function() {
                $("#submit").click(function() {
                    $.ajax({
                        type: "POST",
                        url: "Controller",
                        data: {
                            c: "doCategoryCrud",
                            m: "save",
                            parentId: $("#parentSelect").val(),
                            name: $("#catName").val(),
                            catId: $("#catId").val()
                        }
                    }).success(function(data) {
                        alert('Categoria salva com sucesso');
                        location.reload();
                        $("#catName").val("");
                        $("#catId").val("");
                        $("#parentSelect").val(0);
                    }).error(function() {
                        alert('Falha ao salvar');
                    });
                });
                $("#reset").click(function() {
                    $("#catName").val('');
                    $("#parentSelect").val(0);
                    $("#catId").val(0);
                });

            });

            function cDelete(cId) {
                $.ajax({
                    type: "POST",
                    url: "Controller",
                    data: {
                        c: "doCategoryCrud",
                        m: "delete",
                        catId: cId
                    }
                }).success(function(data) {
                    alert('Categoria excluida com sucesso. Favor atualize a página');
                }).error(function() {
                    alert('Falha ao salvar');
                });
            }

            function cEdit(cId, pId, name) {
                $("#catName").val(name);
                $("#parentSelect").val(pId);
                $("#catId").val(cId);
            }

            function openChild(cId) {
                childId = "#child" + cId;
                var objects = "";
            <c:forEach var="category" items="${catList}">
                if (${category.parentId} === cId) {
                    objects = objects.concat("<dl border='1'>");
                    objects = objects.concat(getDl(${category.catId},${category.parentId}, "${category.name}"));
                    objects = objects.concat("</dl>");
                }
            </c:forEach>
                    $(childId).append(objects);
                    more = "#more" + cId;
                    $(more).hide();
                    less = "#less" + cId;
                    $(less).show();
                }

                function closeChild(cId) {
                    childId = "#child" + cId;
                    $(childId).text("");
                    less = "#less" + cId;
                    $(less).hide();
                    more = "#more" + cId;
                    $(more).show();
                }

                function getDl(catId, parentId, catName) {
                    dl = "<dt id=" + catId + ">" ;
                    dl += "<img id='more" + catId + "' src='icons/add.png' onclick='javascript: openChild(" + catId + ");'/>";
                    dl += "<img hidden='hidden' id='less" + catId + "' src='icons/minus.png' onclick='javascript: closeChild(" + catId + ");'/>  ";
                    dl += catName;
                    dl += "<img id='edit" + catId + "' src='icons/edit.png' onclick='javascript: cEdit(" + catId + "," + parentId + ",\"" + catName + "\");'/>";
                    dl += "<img id='delete" + catId + "' src='icons/delete.png' onclick='javascript: cDelete(" + catId + ");'/>";

                    dl += "</dt><dd id='child" + catId + "'></dd></dl>";
                    return dl;
                }
        </script>
        <form id="categoryForm" name="categoryForm">
            <h2 id="title">  Adicionar Nova Categoria </h2>

            <br/>
            Nome da Nova Categoria
            <input type="hidden" id="catId" name="catId" value="0"/>
            <input type="text" id="catName" name="catName" maxlenght="50" />
            <br/><br/>
            Sub Categoria de:
            <select name="parentSelect" id="parentSelect">
                <option id="parentId" name="parentId" value="0"></option>    
                <c:forEach var="category" items="${catList}">
                    <option id="parentId" name="parentId" value="${category.catId}">${category.name}</option>    
                </c:forEach>
            </select>
            <br/><br/>
            <input type="button" id="submit" name="submit" value="Adicionar" /> 
            <input type="button" id="reset" name="reset" value="Cancelar" />
               </form>
            <br/><br/>
            <hr><hr>
            <br/>
            <h2 id="title">   Editar/Excluir Categoria</h2>
            <dl>
                <c:forEach var="category" items="${catList}">
                    <c:if test="${category.parentId == 0}">
                        <dt id="${category.catId}">
                        <img id="more${category.catId}" src="icons/add.png" onclick="javascript: openChild(${category.catId});"/>
                        <img hidden="hidden" id="less${category.catId}" src="icons/minus.png" onclick="javascript: closeChild(${category.catId});"/>

                         ${category.name}    
                        <img id="edit${category.catId}" src="icons/edit.png" onclick="javascript: cEdit(${category.catId},${category.parentId}, '${category.name}');"/>
                        <img id="delete${category.catId}" src="icons/delete.png" onclick="javascript: cDelete(${category.catId});"/>
                        </dt>
                        <dd id="child${category.catId}"></dd>
                    </c:if>
                </c:forEach>
            </dl>