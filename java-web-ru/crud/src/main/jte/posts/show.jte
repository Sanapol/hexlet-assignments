@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(content = @`
    <div>
        <h1>${page.getPost().getName()}</h1>
        <p>${page.getPost().getBody()}</p>
    </div>
    <a href="${NamedRoutes.postsPath()}">Вернутся к списку пользователей</a>
`)
