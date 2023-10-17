<!-- BEGIN -->
@import exercise.dto.users.UsersPage;
@param UsersPage page
<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
            @for(var user : page.getUsers())
               <div>
                   <h2><a href="/users/${user.getId()}">${user.getLastName()}</a></h2>
                   <p>${user.getFirstName()}</p>
                   <p>${user.getId()}</p>
               </div>
            @endfor
        @endif
    </body>
</html>
<!-- END -->
