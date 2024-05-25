<#import "/spring.ftl" as spring>
<#import "../layout.ftlh" as main>
<@main.layout>

<div class="text-center">
  <h2><@spring.message 'reset'/></h2>
  <p class="text-warning">${message}</p>
</div>
</@main.layout>
