<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="container mt-4">
        <h2>Transaction History</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Sender</th>
                    <th>Receiver</th>
                    <th>Sum</th>
                    <th>Type</th>
                    <th>Date</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <#list transactions as transaction >
                    <tr>
                        <td>${transaction.senderAccount}</td>
                        <td>${transaction.receiverAccount}</td>
                        <td>${transaction.amount} som</td>
                        <td>
                            <#if transaction.transactionType == 1>
                                <a href="#" id="blue" class="btn btn-primary">To User</a>
                            <#else >
                                <a href="#" id="blue" class="btn btn-success">To Provider</a>

                            </#if>
                        </td>
                        <td>${transaction.actDate}</td>
                        <td>
                            <#if transaction.senderAccount?c == user.account>
                                <a href="#" id="blue" class="btn btn-primary">Debit</a>
                            <#else>
                                <a href="#" id="green" class="btn btn-success">Transfer</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

</@main.layout>
