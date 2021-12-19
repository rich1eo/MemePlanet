<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Лента</h1>
    <hr>
    <div class="card-columns">
        <#list entries as entry>
            <a href="/entry/${entry.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <img src="/images/${entry.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${entry.title}</h5>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>