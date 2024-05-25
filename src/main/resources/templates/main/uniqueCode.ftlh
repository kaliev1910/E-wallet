<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3 text-center">
                <h1 class="display-4">Your Generated Account Code</h1>
                <p class="lead">Here is your unique account code:</p>
                <h2 class="font-weight-bold">${account}</h2>
                <p class="mt-4">Click the buttons below to proceed:</p>
                <a href="/login" class="btn btn-primary me-2">Login</a>
                <a href="/register" class="btn btn-success">Register</a>
            </div>
        </div>
    </div>

</@main.layout>
