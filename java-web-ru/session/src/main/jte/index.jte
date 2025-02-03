@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@import gg.jte.Content
@param Content content
@param MainPage page

@template.layout.page(content = @`
    <div>
        @if(page.getName() != null)
            <h1>${String.valueOf(page.getName())}</h1>
            <a href="${NamedRoutes.logoutPath()}">Выйти</a>
        @else
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
    </div>
`)



