<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <div class="text-center">
            <h2><@spring.message 'reset'/></h2>
            <#if error??>
                <b class="text-danger">${error}</b>
            </#if>
        </div>

        <div class="row d-flex align-items-center justify-content-center mt-5">
            <div class="col-md-7 col-lg-5 col-xl-5">
                <form action="/reset" method="post" class="needs-validation" novalidate>
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <input type="hidden" name="token" value="${token!}">
                    <div class="mb-3">
                        <input type="password" name="<@spring.message 'password'/>" class="form-control"
                               placeholder="<@spring.message 'text.password'/>" required autofocus/>
                        <div class="invalid-feedback">
                            Please enter your password.
                        </div>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary mt-3"><@spring.message 'submit'/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@main.layout>
