<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h4>Подробная информация о товаре</h4>
    <div class="text-center">
        <#list images as image>
            <img src="/images/${image.id}" class="rounded" width="400px"/>
        </#list>
    </div>
<#--    <img src="/images/${entry.previewImageId}">-->
    <hr>
    <p><h1 style="text-align: center">${entries.title}</h1></p>
    <p style="text-align: center">${entries.description}</p>
    <form action="/addComment" method="post">
        <div class="form-group">
            <label for="CommentName">Оставить комментарий</label>
            <input type="text" required class="form-control" id="CommentName" name="text">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
    </form>
    <#list comments as comment>
        <hr>
        <h1>${comment.user}</h1>
        <p>${comment.text}</p>
    </#list>

</@t.template>