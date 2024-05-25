<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <section class="hero-section d-flex justify-content-center align-items-center" id="section_1">
        <div class="container text-center">
            <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
                    <#if username??>
                        <h1 class="display-4"><@spring.message 'welcome'/> ${username.balance} <@spring.message 'som'/></h1>
                    </#if>
                    <#if username?? >
                        <div class="container">
                            <div id="warning" class="text-danger"></div>
                            <div id="success" class="text-success"></div>
                            <div id="your" class="text-danger"></div>
                            <form action="/" method="post">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}"/>
                                </#if>
                                <div class="form-group">
                                    <label for="receiver"><@spring.message 'account.name'/> </label>
                                    <input type="text" class="form-control" id="receiver" name="receiver"
                                           placeholder="<@spring.message 'enter.account.name'/> "
                                           required>
                                </div>
                                <div class="form-group">
                                    <label for=""><@spring.message 'amount'/> :</label>
                                    <input type="number" class="form-control" id="transactionType" name="transactionType"
                                           placeholder="<@spring.message 'amount'/>" required
                                           min="1" max="2">
                                </div>
                                <div class="form-group">
                                    <label for="amount"><@spring.message 'amount'/> :</label>
                                    <input type="number" class="form-control" id="amount" name="amount"
                                           placeholder="<@spring.message 'amount'/>" required
                                           min="0">
                                </div>
                                <button type="submit" class="btn btn-primary"><@spring.message 'send'/></button>
                            </form>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </section>


    <script>
        function getQueryParam(name) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(name);
        }

        const accountValue = getQueryParam("account");

        if (accountValue === "user") {
            const warningDiv = document.getElementById("warning");
            warningDiv.textContent = "<@spring.message 'invalidUser'/>";
        } else if (accountValue === "balance") {
            const warningDiv = document.getElementById("warning");
            warningDiv.textContent = "<@spring.message 'notE'/>";
        } else if (accountValue === "success") {
            const successDiv = document.getElementById("success");
            successDiv.textContent = "<@spring.message 'success'/>";
        } else if (accountValue === "yourself") {
            const yourselfDiv = document.getElementById("your");
            yourselfDiv.textContent = "<@spring.message 'noTransfer'/>";
        }
    </script>

</@main.layout>
