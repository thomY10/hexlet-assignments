@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes;
@param PostsPage page


<!-- BEGIN -->
<table class="table table-striped">
    @for(var post : page.getPosts())
        <ul>
            <li><a href="${NamedRoutes.postsPath()}/${post.getId()}">>${post.getName()} </a></li>
        </ul>

    @endfor
</table>
<div>
    <a href="/posts?page=${page.getPageNum() - 1}">Previous</a>
    <a href="/posts?page=${page.getPageNum() + 1}">Next</a>
</div>

<!-- END -->
