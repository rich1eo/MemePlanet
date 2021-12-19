<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои записи</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новую запись
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/entry/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="entryName">Заголовок записи</label>
                <input type="text" required class="form-control"
                       id="entryName" name="title">
            </div>
            <div class="form-group">
                <label for="description">Содержание</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="file">
                    <label class="custom-file-label" for="file">Первая фотография к товару</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list entries as entry>
            <a href="/entry/${entry.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <form action="/entry/delete/${entry.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
                    <img src="/images/${entry.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${entry.title}</h5>
                        <p class="card-text">${entry.description}</p>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>