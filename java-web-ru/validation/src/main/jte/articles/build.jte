@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(content = @`
    @if(page.getError() != null)
        <ul>
            @for(var errors : page.getError().values())
                @for(var error : errors)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif
    <form action="/articles" method="post">
        <div>
            <label>
                title
                    <input class="mb-3" type="text" name="title" value="${page.getTitle()}">
                    </label>
        </div>
        <div>
            <label>
                content
                    <input class="mb-3" type="text" name="content" value="${page.getContent()}">
            </label>
        </div>
        <input class="btn btn-primary" type="submit" value="Создать">
    </form>
`)
