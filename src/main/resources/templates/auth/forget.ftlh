<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-7 col-lg-5 col-xl-5">
                <div class="form-container">
                    <h2>Forgot Password</h2>
                    <#if error??>
                        <div class="alert alert-danger text-center">${error}</div>
                    </#if>
                    <#if message??>
                        <div class="alert alert-warning text-center">${message}</div>
                    </#if>
                    <form action="/forgot" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label for="email" class="form-label"><@spring.message 'text.email'/></label>
                            <input type="email" name="email" id="email" class="form-control"
                                   placeholder="<@spring.message 'text.email'/>" required autofocus/>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary"><@spring.message 'send'/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</@main.layout>
