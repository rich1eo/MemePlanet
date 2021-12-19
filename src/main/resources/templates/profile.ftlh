<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Личный кабинет</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электоронная почта: <b>${user.email}</b>
        <br>
        <br>
        <form action="/profile" method="post">
            <div class="form-group">
                <div class="custom-file" style="margin-bottom: 5px" >
                    <input required type="file"  name="file" id="file">
                    <label class="custom-file-label"  for="file">Загрузить аватарку</label>
                </div>
                <br>
                <button type="submit" style="width: 100%" class="btn btn-info">Добавить</button>
            </div>
        </form>
        <#if user.isAdmin()>
            <a class="btn btn-info mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
        </#if>
        <a class="btn btn-info mb-3" style="text-align: center; width: 100%"
           href="/enrtyInfo">
            Мои публикации
        </a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>