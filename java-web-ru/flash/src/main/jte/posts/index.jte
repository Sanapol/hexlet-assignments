@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page( page = page, content = @`
    <div>
        <ul>
            @for(var pages : page.getPosts())
                <li><a href="${pages.getId()}">${pages.getName()}</a></li>
            @endfor
        </ul>
    </div>
`)