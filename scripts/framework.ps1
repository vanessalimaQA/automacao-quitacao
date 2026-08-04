param(
    [Parameter(Mandatory = $true, Position = 0)]
    [ValidateSet(
        "class",
        "page",
        "service",
        "repository",
        "validator",
        "model",
        "test"
    )]
    [string]$Type,

    [Parameter(Mandatory = $true, Position = 1)]
    [string]$Name,

    [Parameter(Position = 2)]
    [string]$SubPackage
)

$ErrorActionPreference = "Stop"

function Validar-NomeJava {
    param(
        [string]$ClassName
    )

    if ($ClassName -notmatch '^[A-Z][A-Za-z0-9_]*$') {
        throw "Nome de classe inválido: '$ClassName'. Use PascalCase, por exemplo: LoginPage."
    }
}

function Criar-Arquivo {
    param(
        [string]$FilePath,
        [string]$Content
    )

    if (Test-Path $FilePath) {
        throw "O arquivo já existe e não será sobrescrito: $FilePath"
    }

    $folder = Split-Path $FilePath -Parent

    if (-not (Test-Path $folder)) {
        New-Item `
            -ItemType Directory `
            -Path $folder `
            -Force |
            Out-Null
    }

   $utf8SemBom = New-Object System.Text.UTF8Encoding($false)

   [System.IO.File]::WriteAllText(
       $FilePath,
       $Content,
       $utf8SemBom
   )

    Write-Host ""
    Write-Host "Arquivo criado com sucesso!" -ForegroundColor Green
    Write-Host $FilePath
}

Validar-NomeJava -ClassName $Name

$mainBase = "src\main\java\com\automation"
$testBase = "src\test\java\com\automation\tests"

switch ($Type) {

    "class" {
        if ([string]::IsNullOrWhiteSpace($SubPackage)) {
            throw "Para o tipo 'class', informe o package. Exemplo: .\scripts\framework.ps1 class MinhaClasse utils"
        }

        $packagePath = $SubPackage.Replace(".", "\")
        $packageName = "com.automation.$SubPackage"
        $filePath = Join-Path $mainBase "$packagePath\$Name.java"

        $content = @"
package $packageName;

public class $Name {

}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }

    "page" {
        $filePath = Join-Path $mainBase "pages\$Name.java"

        $content = @"
package com.automation.pages;

import com.microsoft.playwright.Page;

public final class $Name extends BasePage {

    public $Name(Page page) {
        super(page);
    }
}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }

    "service" {
        $filePath = Join-Path $mainBase "service\$Name.java"

        $content = @"
package com.automation.service;

public final class $Name {

    public $Name() {
    }
}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }

    "repository" {
        $filePath = Join-Path $mainBase "repository\$Name.java"

        $content = @"
package com.automation.repository;

import com.automation.database.DatabaseExecutor;

public final class $Name {

    private final DatabaseExecutor databaseExecutor;

    public $Name() {
        this.databaseExecutor = new DatabaseExecutor();
    }
}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }

    "validator" {
        $filePath = Join-Path $mainBase "validator\$Name.java"

        $content = @"
package com.automation.validator;

public final class $Name {

    private $Name() {
        // Impede instanciação.
    }
}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }

    "model" {
        $filePath = Join-Path $mainBase "model\$Name.java"

        $content = @"
package com.automation.model;

public record $Name() {

}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }

    "test" {
        if ([string]::IsNullOrWhiteSpace($SubPackage)) {
            throw "Para o tipo 'test', informe o subpackage. Exemplo: .\scripts\framework.ps1 test LoginTest web"
        }

        $packagePath = $SubPackage.Replace(".", "\")
        $packageName = "com.automation.tests.$SubPackage"
        $filePath = Join-Path $testBase "$packagePath\$Name.java"

        $content = @"
package $packageName;

import org.junit.jupiter.api.Test;

class $Name {

    @Test
    void deveExecutarCenario() {

    }
}
"@

        Criar-Arquivo -FilePath $filePath -Content $content
    }
}