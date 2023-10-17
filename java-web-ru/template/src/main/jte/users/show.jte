<!-- BEGIN -->
@import exercise.dto.users.UserPage;
@param UserPage page

<html>
    <body>
        <h1>${page.getUser().getLastName()}</h1>
        <h2>${page.getUser().getFirstName()}</h2>
        <p>${page.getUser().getId()}</p>
    </body>
</html>
<!-- END -->
