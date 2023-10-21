<!-- BEGIN -->
@import exercise.dto.articles.NewArticlePage
@param NewArticlePage page

@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            title
            <input type="text" name="title" value="${page.getTitle()}" />
        </label>
    </di
    <div>
        <label for="content">content:</label>
    </div>
    <textarea id="content" name="content">
        ${page.getContent()}
    </textarea>
    <div>
        <input type="submit" value="Send" />
    </div>
</form>
<!-- END -->
