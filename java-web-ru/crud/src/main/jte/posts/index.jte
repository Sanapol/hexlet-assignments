@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(content = @`
    @for(var post : page.getPosts())
        <div>
            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
        </div>
    @endfor
    <p>${page.getNumberOfPage()}</p>
    <a href="${NamedRoutes.postsPath(page.nextPage())}">Вперед</a>
    @if(page.getNumberOfPage() > 1)
        <a href="${NamedRoutes.postsPath(page.previousPage())}">Назад</a>
    @endif
`)
