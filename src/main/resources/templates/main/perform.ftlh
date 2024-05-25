<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <form  action="/service/${serviceId}" method="post" id="paymentForm" name="payment">
            <#if _csrf??>
                <input type="hidden" id="csrfToken" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>
            <div class="mb-3">
                <label for="balance" class="form-label">Amount of Money:</label>
                <input type="number" class="form-control" id="balance" name="balance" min="0" required>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">User Phone of Provider</label>
                <input type="number" class="form-control" id="phone" name="phone" required>
            </div>
            <input type="number" class="form-control" id="providerId" name="providerId" hidden="hidden" value="${serviceId}" required>
            <button type="submit" class="btn btn-primary">Transfer Money</button>
        </form>
        <div class="mt-3">
            <label for="subscribe" class="form-label">No subscription? <a href="/service/${service.id}/subscribe">Subscribe</a></label>
        </div>
    </div>



</@main.layout>
