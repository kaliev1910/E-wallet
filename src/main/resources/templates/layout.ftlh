<#import "/spring.ftl" as spring>
<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">E-wallet</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#"><@spring.message 'navbar.navi.home'/></a>
                    </li>
                    <#if username??>
                        <li class="nav-item">
                            <a class="nav-link" href="/service"><@spring.message 'service'/></a>
                        </li>
                    </#if>
                </ul>

                <ul class="navbar-nav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/"><@spring.message 'navbar.navi.home'/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/service"><@spring.message 'service'/></a>
                        </li>
                    </ul>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="langDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message 'navbar.navi.lang'/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="langDropdown">
                            <li><a class="dropdown-item" href="?lang=en">English</a></li>
                            <li><a class="dropdown-item" href="?lang=ru">Русский</a></li>
                        </ul>
                    </li>
                    <#if username??>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout"><@spring.message 'logout'/></a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/login?lang="><@spring.message 'navbar.user.signin'/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register"><@spring.message 'navbar.user.signup'/></a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <#nested>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>
