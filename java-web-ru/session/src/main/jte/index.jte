@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!-- BEGIN -->
@template.layout.page(
content = @`
    @if(page.getName() != null)
        <div>Hello, ${page.getName().toString()}!</div>
        <div><a href="${NamedRoutes.logoutPath()}">Выйти</a> </div>
    @else
        <div><a href="${NamedRoutes.loginPath()}">Войти</a> </div>
    @endif
`
)
<!-- END -->
