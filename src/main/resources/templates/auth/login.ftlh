<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <h2><@spring.message 'login'/></h2>
        <form action="/login" method="post" id="login-form" class="needs-validation" novalidate>
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="mb-3">
                <label for="email" class="form-label"><@spring.message 'account.name'/></label>
                <input type="text" id="email" name="username" class="form-control" placeholder="<@spring.message 'text.email'/>" required>
                <div class="invalid-feedback">
                    Please enter your email.
                </div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"><@spring.message 'password'/></label>
                <input type="password" id="password" name="password" class="form-control" placeholder="<@spring.message 'text.password'/>" required>
                <div class="invalid-feedback">
                    Please enter your password.
                </div>
            </div>
            <button type="submit" class="btn btn-primary w-100"><@spring.message 'login'/></button>
        </form>

        <div class="mt-3">
            <p><@spring.message 'no.account'/> <a href="/register"><@spring.message 'navbar.user.signup'/></a></p>
        </div>
        <div class="mt-3">
            <p><@spring.message 'forgot'/> <a href="/forgot"><@spring.message 'reset'/></a></p>
        </div>
    </div>

    <script>
        document.getElementById("login-form").addEventListener('submit', onLoginHandler);

        let user = {};

        function onLoginHandler(event) {
            event.preventDefault(); // Prevent default form submission
            let form = event.target;
            if (!form.checkValidity()) {
                event.stopPropagation();
                form.classList.add('was-validated');
            } else {
                let userFormData = new FormData(form);
                user = Object.fromEntries(userFormData);
                saveUser(user);
                form.submit(); // Manually submit the form
            }
        }

        function saveUser(user) {
            let userAsJson = JSON.stringify(user);
            localStorage.setItem('user', userAsJson);
        }
    </script>

</@main.layout>
