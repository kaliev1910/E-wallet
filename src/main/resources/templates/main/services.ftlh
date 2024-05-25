<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-5">
        <h1>List of Services</h1>
        <div class="row">
            <#list providers as provider>
                <div class="col-md-4">
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${provider.provider}</h5>
                            <p class="card-text">Description of bank service.</p>
                            <a href="/service/${provider.id}" class="btn btn-primary">Perform</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</@main.layout>
