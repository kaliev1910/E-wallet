<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <form action="/service/${service.id}/subscribe" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone number:</label>
                <input type="text" id="phone" name="phone" class="form-control" placeholder="Enter phone" required>
            </div>
            <button type="submit" class="btn btn-primary">Subscribe</button>
        </form>
    </div>

</@main.layout>
