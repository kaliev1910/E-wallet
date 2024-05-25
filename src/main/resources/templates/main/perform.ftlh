<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <form onsubmit="submitForm(event)" id="paymentForm" name="payment">
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

    <script>
        function submitForm(event) {
            event.preventDefault();

            const form = document.getElementById('paymentForm');
            const formData = new FormData(form);

            const data = {
                providerId: formData.get('providerId'),
                balance: formData.get('balance'),
                phone: formData.get('phone')
            };

            fetch(form.action, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': document.getElementById('csrfToken').value
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.redirected) {
                        window.location.href = response.url;
                    } else {
                        return response.json();
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    </script>



</@main.layout>
