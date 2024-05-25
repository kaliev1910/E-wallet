<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <h2><@spring.message 'navbar.user.signup'/></h2>
        <form action="/register" method="post" class="needs-validation" novalidate>
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="mb-3">
                <label for="email" class="form-label"><@spring.message 'email'/></label>
                <input type="email" id="email" name="email" class="form-control" placeholder="<@spring.message 'text.email'/>" required>
                <div class="invalid-feedback">
                    Please enter a valid email.
                </div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"><@spring.message 'password'/></label>
                <input type="password" id="password" name="password" class="form-control" placeholder="<@spring.message 'text.password'/>" required>
                <div class="invalid-feedback">
                    Please enter your password.
                </div>
            </div>
            <button type="submit" class="btn btn-primary w-100"><@spring.message 'register'/></button>
        </form>

        <div class="mt-3">
            <p><@spring.message 'have.account'/> <a href="/login"><@spring.message 'login'/></a></p>
        </div>
    </div>

    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict';

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.querySelectorAll('.needs-validation');

            // Loop over them and prevent submission
            Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                        }

                        form.classList.add('was-validated');
                    }, false);
                });
        })();
    </script>

</@main.layout>
