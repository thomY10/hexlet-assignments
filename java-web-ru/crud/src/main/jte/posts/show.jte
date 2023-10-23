@import exercise.dto.posts.PostPage
@param PostPage page


<!-- BEGIN -->
<div>${page.getPost().getName()}</div>
<div>${page.getPost().getBody()}</div>
<!-- END -->