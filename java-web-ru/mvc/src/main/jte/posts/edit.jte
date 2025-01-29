@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@template.layout.page(content = @`
    @if(page.getErrors() != null)
        <div>
            <ul>
                @for(var errors : page.getErrors().values())
                    @for(var error : errors)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        </div>
    @endif
    <div>
        <form action="${NamedRoutes.postPath(page.getId())}" method="post">
            <div>
                <label>
                    Название
                    <input type="text" name="name" value="${page.getName()}">
                </label>
            </div>
            <div>
                <label>Содержание</label>
                <textarea rows="20" cols="70" type="text" name="body">${page.getBody()}</textarea>
            </div>
            <input type="submit" value="Сохранить">
        </form>
    </div>
`)
