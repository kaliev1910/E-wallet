<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <form>
            <div class="mb-3">
                <label for="amount" class="form-label">Amount of Money:</label>
                <input type="number" class="form-control" id="amount" name="balance" min="0" required>
            </div>
            <div class="mb-3">
                <label for="account" class="form-label">User Phone</label>
                <input type="text" class="form-control" id="account" name="phone" required>
            </div>
            <button type="submit" class="btn btn-primary">Transfer Money</button>
        </form>
        <div class="mt-3">
            <label for="subscribe" class="form-label">No subscription? <a href="/service/${service.id}/subscribe">Subscribe</a></label>
        </div>
    </div>

    </main>

</@main.layout>
